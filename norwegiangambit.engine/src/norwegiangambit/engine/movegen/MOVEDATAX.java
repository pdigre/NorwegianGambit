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
		MOVEDATAX md = new MOVEDATAX((bm | CASTLING_STATE) ^cstl,cstl);
		return MOVEDATA.add(md);
	}

	public static MOVEDATAX capture3(long bitmap,int victim){
		long bm = bitmap | ((victim & 7) << _CAPTURE);
		long cstl = findCastlingTo(bitmap);
		return new MOVEDATAX((bm | CASTLING_STATE) ^cstl,cstl);
	}

	public static int capture2(long bitmap,int victim,int offset){
		long bm = bitmap | ((victim & 7) << _CAPTURE);
		long cstl = findCastlingTo(bitmap);
		return create2((bm | CASTLING_STATE) ^cstl,cstl,offset);
	}

	private MOVEDATAX(long bitmap,long cstl) {
		super((bitmap | CASTLING_STATE) ^cstl);
		this.castling=cstl;
	}

	public static int create(long bitmap,long cstl){
		return MOVEDATA.add(new MOVEDATAX(bitmap,cstl));
	}
	
	public static int create2(long bitmap,long cstl,int offset){
		return MOVEDATA.add2(new MOVEDATAX(bitmap,cstl),offset);
	}
	
	public static int createRook(long bitmap){
		long cstl = findCastling(bitmap);
		return MOVEDATA.add(new MOVEDATAX((bitmap | CASTLING_STATE) ^cstl,cstl));
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

	public static int cpromote2(int from,int to, int promote, int pawn, int victim,int offset) {
		long promo = MBase.assemblePromote(pawn, promote, from, to, CASTLING_STATE | SPECIAL);
		return capture2(promo, victim,offset);
	}

	@Override
	public String toString() {
		String string = super.toString();
		
		return FEN.addHorizontal(string, FEN.board2string(castling));
	}
}
