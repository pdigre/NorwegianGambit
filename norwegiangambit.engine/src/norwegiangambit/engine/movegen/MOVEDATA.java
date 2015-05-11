package norwegiangambit.engine.movegen;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.polyglot.IZobristKey;
import static norwegiangambit.util.ShortIntPairs.*;

public class MOVEDATA {
	
	//     1 0         NULL-MOVE
	//     8 1-8       ENPASSANT
	//   366 9-374     BREAKERS
	// 22724 375-23099 NORMAL
	
	public final static int ENP_BEGIN=1;
	public final static int ENP_END=9;
	public final static int BRK_END=375;
	public final static int BLACK_OFFSET=25000; 

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

	public static void main(String[] args) {
		StartGame pos = new StartGame("n1R5/PP1k4/1n6/8/8/8/4Kppp/5N1N b - - 2 3");
		System.out.println(pos);
		pos.make(MOVEDATAX.cpromote(14,7, IConst.BB, IConst.BP, IConst.WN),pos.bitmap);
		System.out.println(pos);
	}

	public static int create(long bitmap){
		return MOVEDATA.add(new MOVEDATA(bitmap));
	}
	
	public static int capture(long bitmap,int victim){
		return MOVEDATA.add(new MOVEDATA(bitmap | ((victim & 7) << IConst._CAPTURE)));
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

	final public static MOVEDATA[] ALL;

	static{
		ALL = new MOVEDATA[BLACK_OFFSET*2];
		init();
	}


	public static int enp_cnt = 0, nrm_cnt = 0, brk_cnt = 0;

	public static int cw = 0, cb = 0, mw = 0, mb = 0, mhw = 0, mhb = 0, ew = 0, eb = 0, cpw = 0, cpb = 0, mpw = 0, mpb = 0,mcw=0,mcb=0;
	public static int cww = 0, cwb = 0,cew = 0, ceb = 0,clw = 0, clb = 0;
	public static int xmw = 0, xmb = 0,xcw = 0, xcb = 0;

	final public static long getBTo(int md){
		return ALL[md].bto;
	}
	
	public static void stats() {
		System.out.println("White:"+enp_cnt+",Black:"+nrm_cnt);

		System.out.println("CW:"+cw+",CB:"+cb);
		System.out.println("EW:"+ew+",EB:"+eb);
		System.out.println("CPW:"+cpw+",CPB:"+cpb);

		System.out.println("MW:"+mw+",MB:"+mb);

		System.out.println("MPW:"+mpw+",MPB:"+mpb);
		System.out.println("MCW:"+mcw+",MCB:"+mcb);

		System.out.println("CWW:"+cww+",CWB:"+cwb);
		System.out.println("CEW:"+cew+",CEB:"+ceb);
		System.out.println("CLW:"+clw+",CLB:"+clb);

//		System.out.println("XMW:"+xmw+",XMB:"+xmb+" = 1/"+(mb/xmb));
//		System.out.println("XCW:"+xcw+",XCB:"+xcb+" = 1/"+(cb/xcb));
	}

	public static int add(MOVEDATA md) {
		long bitmap = md.bitmap;
		boolean isWhite = BITS.white(bitmap);
		boolean isPromo = BITS.isPromotion(bitmap);
		int type = BITS.getType(bitmap);
		if (BITS.isCapture(bitmap)) {
			boolean isEnpassant = BITS.isEnpassant(bitmap);
			int captured = BITS.getCapturedType(bitmap);
			
			if (isWhite) {
				cw++;
				if(md instanceof MOVEDATAX)
					xcw++;
				if (isEnpassant)
					ew++;
				if (isPromo)
					cpw++;
				if(captured>type)
					cww++;
				if(captured==type)
					cew++;
				if(captured<type)
					clw++;
			} else {
				cb++;
				if(md instanceof MOVEDATAX)
					xcb++;
				if (isEnpassant)
					eb++;
				if (isPromo)
					cpb++;
				if(captured>type)
					cwb++;
				if(captured==type)
					ceb++;
				if(captured<type)
					clb++;
			}
		} else {
			boolean isCastling = BITS.isCastling(bitmap);
			boolean isHalf=type!=WP&&!isPromo&&!isCastling;
			if (isWhite) {
				mw++;
				if(md instanceof MOVEDATAX)
					xmw++;
				if (isCastling)
					mcw++;
				if (isPromo)
					mpw++;
				if(isHalf)
					mhw++;
			} else {
				mb++;
				if(md instanceof MOVEDATAX)
					xmb++;
				if (isCastling)
					mcb++;
				if (isPromo)
					mpb++;
				if(isHalf)
					mhw++;
			}

		}
		if(md instanceof ENPASSANT){
			ALL[enp_cnt++] = md;
			return enp_cnt-1;
		} else if(md instanceof MOVEDATAX){
			ALL[brk_cnt++] = md;
			return brk_cnt-1;
		} else {
			ALL[nrm_cnt++] = md;
			return nrm_cnt-1;
		}
	}

	
	
	public static void init() {
		enp_cnt=ENP_BEGIN;
		brk_cnt=ENP_END;
		nrm_cnt=BRK_END;
		MWP.init();
		MWK.init();
		MWQ.init();
		MWR.init();
		MWB.init();
		MWN.init();
//		System.out.println("ENP="+enp_cnt);
//		System.out.println("SPECIAL="+cbx_cnt);
//		System.out.println("NORMAL="+(nrm_cnt-cbx_cnt));

		enp_cnt=ENP_BEGIN+BLACK_OFFSET;
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