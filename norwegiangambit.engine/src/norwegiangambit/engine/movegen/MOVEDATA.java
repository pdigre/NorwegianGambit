package norwegiangambit.engine.movegen;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.PSQT_SEF;
import norwegiangambit.util.polyglot.ZobristKey;

public class MOVEDATA {
	final public long bitmap,b_black,b_bit1,b_bit2,b_bit3;
	final public long bto;
	final public int mscore,escore;
	final public long zobrist;
	final public long pawnhash;

	protected MOVEDATA(long bits) {
		int score[]=new int[2];
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
		long zobrist=ZobristKey.ZOBRIST_NXT^ZobristKey.KEYS[piece][to];
		long pawnhash=type==IConst.WP?ZobristKey.KEYS[piece][to]:0L;
		if(BITS.isPromotion(bits)){
			int pawn = white(piece)?IConst.WP:IConst.BP;
			b_bit1 ^= ((piece & 1) != 0 ? 0 : bfrom);
			b_bit2 ^= ((piece & 2) == 0 ? 0 : bfrom);
			b_bit3 ^= ((piece & 4) == 0 ? 0 : bfrom);
			sub(pawn,from,score);
			zobrist^=ZobristKey.KEYS[pawn][from];
			pawnhash^=ZobristKey.KEYS[pawn][from];
		} else {
			sub(piece,from,score);
			zobrist^=ZobristKey.KEYS[piece][from];
			if(type==IConst.WP)
				pawnhash^=ZobristKey.KEYS[piece][from];
		}
		add(piece,to,score);
	    if (BITS.isEnpassant(bits)) {
			int victim=BITS.getCaptured(bits);
			int enp = to + (to > from ? -8 : 8);
			long e = 1L << enp;
			b_bit1 ^= ((piece & 1) == 0 ? 0 : e);
			b_black ^= ((piece & 8) != 0 ? 0 : e);
			sub(victim,enp,score);
			zobrist^=ZobristKey.KEYS[victim][enp];
			pawnhash^=ZobristKey.KEYS[victim][enp];
	    } else if(BITS.isCapture(bits)){
			int victim=BITS.getCaptured(bits);
			b_bit1 ^= ((victim & 1) == 0 ? 0 : bto);
			b_bit2 ^= ((victim & 2) == 0 ? 0 : bto);
			b_bit3 ^= ((victim & 4) == 0 ? 0 : bto);
			b_black ^= (white(victim) ? 0 : bto);
			sub(victim,to,score);
			zobrist^=ZobristKey.KEYS[victim][to];
			if(victim%8==IConst.WP)
				pawnhash^=ZobristKey.KEYS[victim][to];
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
			sub(rook,from,score);
			add(rook,to,score);
			zobrist^=ZobristKey.KEYS[rook][from];
			zobrist^=ZobristKey.KEYS[rook][to];
		}
	    if(this instanceof MOVEDATAX){
	    	zobrist^=ZobristKey.keyCastling((IConst.CASTLING_STATE&bitmap)^IConst.CASTLING_STATE);
	    }
		this.b_bit1=b_bit1;
		this.b_bit2=b_bit2;
		this.b_bit3=b_bit3;
		this.b_black=b_black;
		this.mscore=score[0];
		this.escore=score[1];
		this.zobrist=zobrist;
		this.pawnhash=pawnhash;
	}

	public boolean white(int piece) {
		return (piece & 8) == 0;
	}

	private void sub(int piece, int sq, int[] score) {
		int[] pv = PSQT_SEF.psqt(sq, piece);
		score[0]-=pv[0];
		score[1]-=pv[1];
	}

	private void add(int piece, int sq, int[] score) {
		int[] pv = PSQT_SEF.psqt(sq, piece);
		score[0]+=pv[0];
		score[1]+=pv[1];
	}

	public static void main(String[] args) {
		new BASE();
		StartGame pos = new StartGame("n1R5/PP1k4/1n6/8/8/8/4Kppp/5N1N b - - 2 3");
		System.out.println(pos);
		pos.make(MOVEDATAX.cpromote(14,7, IConst.BB, IConst.BP, IConst.WN),pos.bitmap);
		System.out.println(pos);
	}

	public static int create(long bitmap){
		return BASE.add(new MOVEDATA(bitmap));
	}
	
	public static int capture(long bitmap,int victim){
		return BASE.add(new MOVEDATA(bitmap | ((victim & 7) << IConst._CAPTURE)));
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
		return FEN.board2string(b_bit1,b_bit2,b_bit3,b_black)+" "+sb.toString();
	}

	public String id() {
		return FEN.move2literal(bitmap);
	}
	
	public static int cpromote(int from,int to, int promote, int pawn, int victim) {
		long promo = MBase.assemblePromote(pawn, promote, from, to, IConst.CASTLING_STATE | IConst.SPECIAL);
		return capture(promo, victim);
	}

}