package norwegiangambit.engine.movegen;

import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;

/**
 * When castling is broken
 */
public class MOVEDATAX extends MOVEDATA implements IConst{

	final public long castling;
	
	public static int capture(long bitmap,int victim){
		long bm = bitmap | ((victim & 7) << _CAPTURE);
		long cstl = findCastlingTo(bitmap);
		return create((bm | CASTLING_STATE) ^cstl,cstl);
	}

	private MOVEDATAX(long bitmap,long castling) {
		super(bitmap);
		this.castling=castling;
	}

	public static int create(long bitmap,long castling){
		return BASE.add(new MOVEDATAX(bitmap,castling));
	}
	
	public static int createRook(long bitmap){
		long cstl = findCastling(bitmap);
		return BASE.add(new MOVEDATAX((bitmap | CASTLING_STATE) ^cstl,cstl));
	}
	
	private static long findCastling(long bitmap) {
		int from=BITS.getFrom(bitmap);
		int pt=BITS.getPiece(bitmap);
		long castling=findCastlingTo(bitmap);
		if(pt==WR){
			if(from==WR_QUEEN_STARTPOS)
				castling|=CANCASTLE_WHITEQUEEN;
			else if(from==WR_KING_STARTPOS)
				castling|=CANCASTLE_WHITEKING;
		} else if(pt==BR){
			if(from==BR_QUEEN_STARTPOS)
				castling|=CANCASTLE_BLACKQUEEN;
			else if(from==BR_KING_STARTPOS)
				castling|=CANCASTLE_BLACKKING;
		} else if(pt==WK){
			if(from==WK_STARTPOS)
				castling|=CANCASTLE_WHITE;
		} else if(pt==BK){
			if(from==BK_STARTPOS)
				castling|=CANCASTLE_BLACK;
		}
		return castling;
	}

	private static long findCastlingTo(long bitmap) {
		int to=BITS.getTo(bitmap);
		long castling=0L;
		if(to==WR_KING_STARTPOS)
			castling|=CANCASTLE_WHITEKING;
		else if(to==WR_QUEEN_STARTPOS)
			castling|=CANCASTLE_WHITEQUEEN;
		else if(to==BR_KING_STARTPOS)
			castling|=CANCASTLE_BLACKKING;
		else if(to==BR_QUEEN_STARTPOS)
			castling|=CANCASTLE_BLACKQUEEN;
		return castling;
	}

	public static int cpromote(int from,int to, int promote, int pawn, int victim) {
		long promo = MBase.assemblePromote(pawn, promote, from, to, CASTLING_STATE | SPECIAL);
		return capture(promo, victim);
	}

	@Override
	public String toString() {
		String string = super.toString();
		
		return FEN.addHorizontal(string, FEN.board2string(castling));
	}
}
