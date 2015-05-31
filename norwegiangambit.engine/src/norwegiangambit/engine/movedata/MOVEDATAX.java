package norwegiangambit.engine.movedata;

import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;

/**
 * When castling is broken
 */
public class MOVEDATAX extends MOVEDATA implements IConst{

	public static MOVEDATAX rookCapture(long bitmap){
		long bm = bitmap | ((WR & 7) << _CAPTURE);
		long cstl = findCastlingTo(bitmap);
		return new MOVEDATAX((bm | CASTLING_STATE) ^cstl,cstl);
	}

	MOVEDATAX(long bitmap,long cstl) {
		super((bitmap | CASTLING_STATE) ^cstl);
	}

	public static int create(long bitmap,long cstl){
		return MOVEDATA.add(new MOVEDATAX(bitmap,cstl));
	}
	
	public static int create2(long bitmap,long cstl,int offset){
		return MOVEDATA.add2(new MOVEDATAX(bitmap,cstl),offset);
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

	public static int capturePromote(int from,int to, int promote, int pawn, int victim,int offset) {
		long promo = MBase.assemblePromote(pawn, promote, from, to, CASTLING_STATE | SPECIAL);
		long bm = promo | ((victim & 7) << _CAPTURE);
		long cstl = findCastlingTo(promo);
		return create2((bm | CASTLING_STATE) ^cstl,cstl,offset);
	}

	@Override
	public String toString() {
		String string = super.toString();
		
		return FEN.addHorizontal(string, FEN.board2string((bitmap&CASTLING_STATE)^CASTLING_STATE));
	}
}
