package norwegiangambit.engine.movegen;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.polyglot.IZobristKey;
import static norwegiangambit.util.ShortIntPairs.*;

public class MOVEDATA {
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
		return MBase.add(new MOVEDATA(bitmap));
	}
	
	public static int capture(long bitmap,int victim){
		return MBase.add(new MOVEDATA(bitmap | ((victim & 7) << IConst._CAPTURE)));
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

}