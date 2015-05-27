package norwegiangambit.engine.movegen;

import static norwegiangambit.util.ShortIntPairs.SS;
import norwegiangambit.engine.IMovedata;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.polyglot.IZobristKey;

public class MOVEDATA implements IMovedata{


	public long bitmap,bOccupied,aMinor,aMajor,aSlider;
	public long bto;
	public int mescore;
	public long zobrist;
	public long pawnhash;


	
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
	    } else {
	    	bitmap|=IConst.CASTLING_STATE;
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

	final public static MOVEDATA[] ALL= new MOVEDATA[BLACK_OFFSET*2];

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

	
	
	static boolean MD_INITIALIZED=false;

	public static void initialize() {
		if(MD_INITIALIZED)
			return;
		
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
		
		for (int i = 0; i < SIZE; i++) {
			MOVEDATA md=ALL[i];
			if(md!=null){
				BITMAP[i]=md.bitmap;
				BOCCUPIED[i]=md.bOccupied;
				AMINOR[i]=md.aMinor;
				AMAJOR[i]=md.aMajor;
				ASLIDER[i]=md.aSlider;
				BTO[i]=md.bto;
				MESCORE[i]=md.mescore;
				ZOBRIST[i]=md.zobrist;
				PAWNHASH[i]=md.pawnhash;
			}
		}	
		MD_INITIALIZED=true;
	}

}