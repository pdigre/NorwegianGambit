package norwegiangambit.engine.movegen;

import static norwegiangambit.util.ShortIntPairs.SS;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.polyglot.IZobristKey;

public class MOVEDATA {

	// Pawn moves
	public final static int MD_P2=0; 		// 8 lanes Opening for enpassant
	public final static int MD_PEL=8;   	// 8 lanes enpassant left
	public final static int MD_PER=16;  	// 8 lanes enpassant right
	public final static int MD_PP=24;   	// 32 - 8 lanes * 4 types
	public final static int MD_PPL=56;  	// 160 - 8 lanes * 20 types - promotion left
	public final static int MD_PPR=216;  	// 160- 8 lanes * 20 types - promotion right
	public final static int MD_P1=376;  	// 64 move 1 step
	public final static int MD_PCL=440;		// 320 - 64*5 types - left
	public final static int MD_PCR=760;		// 320 - 64*5 types - right
	public final static int MD_PQ=1080;		// 4 - promotion 4 types
	public final static int MD_PK=1084; 	// 4 - promotion 4 types

		// Castling
	public final static int MD_KCQ=1088;
	public final static int MD_KCK=1089;
	public final static int MD_KCQ2=1090;
	public final static int MD_KCK2=1091;
	public final static int	MD_KX=1092;		// 32 King moves - could castle both sides
	public final static int MD_KXQ=1122;	// 32 King moves - could castle queen sides
	public final static int MD_KXK=1152;	// 32 King moves - could castle king sides
	public final static int MD_RQ=1182;	// 86 - Rook move - could castle	
	public final static int MD_RK=1268;	// 86 - Rook move - could castle	
	

	// Simple - King & kNight
	// B->E (Q,K follows)
	
	// Sliders
	// 4/8* (B->E) (Q,K follows)

	public static int[] MD_K=new int[128];	// 64 - Simple set (B[64] E[64])
	public static int[] MD_N=new int[128];	// 64 - Simple set (B[64] E[64])

	public static int[] MD_Q=new int[1024];	// 64 - 8Slider set (B[64*8] E[64*8])
	public static int[] MD_B=new int[512];	// 64 - 4Slider set (B[64*4] E[64*4])
	public static int[] MD_R=new int[512];	// 64 - 4Slider set (B[64*4] E[64*4])

	public static int[] MD_RQW=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	public static int[] MD_RKW=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	public static int[] MD_RQB=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	public static int[] MD_RKB=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	
	//     1 0         NULL-MOVE
	//     8 1-8       ENPASSANT
	//   366 9-374     BREAKERS
	// 22724 375-23099 NORMAL
	
	public final static int ENP_END=2000;
	public final static int BRK_END=3000;
	public final static int BLACK_OFFSET=32*1024; 

	final public long bitmap,bOccupied,aMinor,aMajor,aSlider;
	final public long bto;
	final public int mescore;
	final public long zobrist;
	final public long pawnhash;


	
	protected MOVEDATA(long bits) {
		int score=0;
		this.bitmap = bits;
		int piece = BITS.getPiece(bits);
		int type = BITS.getType(bits);
		int from = BITS.getFrom(bits);
		int to = BITS.getTo(bits);
		bto = 1L << to;
		long bfrom = 1L << from;
		long bfromto = bto|bfrom;
		long b_bit1 = ((piece & 1) == 0 ? 0 : bfromto);
		long b_bit2 = ((piece & 2) == 0 ? 0 : bfromto);
		long b_bit3 = ((piece & 4) == 0 ? 0 : bfromto);
		long b_black = (white(piece) ? 0 : bfromto);
		long zobrist=MBase.getZobrist(IZobristKey.ZOBRIST_NXT)^MBase.getZobrist(piece,to);
		long pawnhash=type==IConst.WP?MBase.getZobrist(piece,to):0L;
		if(BITS.isPromotion(bits)){
			int pawn = white(piece)?IConst.WP:IConst.BP;
			b_bit1 ^= ((piece & 1) != 0 ? 0 : bfrom);
			b_bit2 ^= ((piece & 2) == 0 ? 0 : bfrom);
			b_bit3 ^= ((piece & 4) == 0 ? 0 : bfrom);
			score-=sub(pawn,from);
			zobrist^=MBase.getZobrist(pawn,from);
			pawnhash^=MBase.getZobrist(pawn,from);
		} else {
			score-=sub(piece,from);
			zobrist^=MBase.getZobrist(piece,from);
			if(type==IConst.WP)
				pawnhash^=MBase.getZobrist(piece,from);
		}
		score+=add(piece,to);
	    if (BITS.isEnpassant(bits)) {
			int victim=BITS.getCaptured(bits);
			int enp = to + (to > from ? -8 : 8);
			long e = 1L << enp;
			b_bit1 ^= ((piece & 1) == 0 ? 0 : e);
			b_black ^= ((piece & 8) != 0 ? 0 : e);
			score-=sub(victim,enp);
			zobrist^=MBase.getZobrist(victim,enp);
			pawnhash^=MBase.getZobrist(victim,enp);
	    } else if(BITS.isCapture(bits)){
			int victim=BITS.getCaptured(bits);
			b_bit1 ^= ((victim & 1) == 0 ? 0 : bto);
			b_bit2 ^= ((victim & 2) == 0 ? 0 : bto);
			b_bit3 ^= ((victim & 4) == 0 ? 0 : bto);
			b_black ^= (white(victim) ? 0 : bto);
			score-=sub(victim,to);
			zobrist^=MBase.getZobrist(victim,to);
			if(victim%8==IConst.WP)
				pawnhash^=MBase.getZobrist(victim,to);
		} else if (BITS.isCastling(bits)) {
			if (from > to) {
				to = from - 1;
				from = from - 4;
			} else {
				to = from + 1;
				from = from + 3;
			}
			long cfrom = 1L << from;
			long cto = 1L << to;
			long cfromto =cto|cfrom;
			b_bit2 ^= cfromto;
			b_bit3 ^= cfromto;
			b_black ^= (white(piece) ? 0 : cfromto);
			int rook=white(piece)?IConst.WR:IConst.BR;
			score-=sub(rook,from);
			score+=add(rook,to);
			zobrist^=MBase.getZobrist(rook,from);
			zobrist^=MBase.getZobrist(rook,to);
		}
	    if(this instanceof MOVEDATAX){
	    	long castling = (IConst.CASTLING_STATE&bitmap)^IConst.CASTLING_STATE;
			zobrist^=MBase.keyCastling(castling);
	    }
		this.aMinor=b_bit1;
		this.aMajor=b_bit2;
		this.aSlider=b_bit3;
		this.bOccupied=b_black;
		this.mescore=score;
		this.zobrist=zobrist;
		this.pawnhash=pawnhash;
	}

	public boolean white(int piece) {
		return (piece & 8) == 0;
	}

	private int sub(int piece, int sq) {
		return SS(MBase.psqt(sq, piece));
	}

	private int add(int piece, int sq) {
		return SS(MBase.psqt(sq, piece));
		
	}

	public static int create(long bitmap){
		return MOVEDATA.add(new MOVEDATA(bitmap));
	}

	public static int create2(long bitmap,int offset){
		return MOVEDATA.add2(new MOVEDATA(bitmap),offset);
	}
	
	public static int capture(long bitmap,int victim){
		return MOVEDATA.add(new MOVEDATA(bitmap | ((victim & 7) << IConst._CAPTURE)));
	}

	public static int capture2(long bitmap,int victim,int offset){
		return MOVEDATA.add2(new MOVEDATA(bitmap | ((victim & 7) << IConst._CAPTURE)),offset);
	}

	@Override
	public String toString() {
		StringBuffer sb=new StringBuffer();
		sb.append(FEN.type2fen(BITS.getPiece(bitmap)));
		sb.append(" ");
		sb.append(FEN.move2literal(bitmap));
		if(BITS.isCapture(bitmap)){
			sb.append(" x");
			sb.append(FEN.type2fen(BITS.getCaptured(bitmap)));
		}
		return FEN.board2string(aMinor,aMajor,aSlider,bOccupied)+" "+sb.toString();
	}

	public String id() {
		return FEN.move2literal(bitmap);
	}
	
	public static int cpromote(int from,int to, int promote, int pawn, int victim) {
		long promo = MBase.assemblePromote(pawn, promote, from, to, IConst.CASTLING_STATE | IConst.SPECIAL);
		return capture(promo, victim);
	}

	public static int cpromote2(int from,int to, int promote, int pawn, int victim, int offset) {
		long promo = MBase.assemblePromote(pawn, promote, from, to, IConst.CASTLING_STATE | IConst.SPECIAL);
		return capture2(promo, victim,offset);
	}

	final public static MOVEDATA[] ALL;

	static{
		ALL = new MOVEDATA[BLACK_OFFSET*2];
		init();
	}


	public static int nrm_cnt = 0, brk_cnt = 0, color_offset=0;


	final public static long getBTo(int md){
		return ALL[md].bto;
	}
	
	public static void stats() {
//		System.out.println("hi");
	}

	public static int add(MOVEDATA md) {
		ALL[nrm_cnt++] = md;
		return nrm_cnt-1;
	}

	public static int add3(MOVEDATA md) {
		ALL[nrm_cnt++] = md;
		return nrm_cnt-1;
	}


	public static int add2(MOVEDATA md,int pos) {
		int offset=pos+color_offset;
		ALL[offset]=md;
		return offset;
	}

	
	public static long[] REV_WP,REV_BP;
	
	public static void init() {
		REV_WP=new long[64];
		REV_BP=new long[64];

		color_offset=0;
		brk_cnt=ENP_END;
		nrm_cnt=BRK_END;
		MWP.init();
		MWK.init();
		MWQ.init();
		MWR.init();
		MWB.init();
		MWN.init();

		color_offset=BLACK_OFFSET;
		brk_cnt=ENP_END+BLACK_OFFSET;
		nrm_cnt=BRK_END+BLACK_OFFSET;
		MBP.init();
		MBK.init();
		MBQ.init();
		MBR.init();
		MBB.init();
		MBN.init();
	}
	

}