package norwegiangambit.util;

import static norwegiangambit.util.ShortIntPairs.E;
import static norwegiangambit.util.ShortIntPairs.M;
import static norwegiangambit.util.ShortIntPairs.S;

public class ShortIntPairs implements IConst {

	final static public int I2MSK = 0xFFFF;
	final static public int I2MAX = 0x8000;
	final static public int I2OFF = 0x80008000;
	final static public int ZERO  = S(0,0);

	/**
	 * Mid-game part of ShortInt Pair
	 * @param b
	 * @return
	 */
	final static public int M(int b) {
		return (b&I2MSK)-I2MAX;
	}
	/**
	 * End-game part of ShortInt Pair
	 * @param b
	 * @return
	 */
	final static public int E(int b) {
		return ((b>>16)&I2MSK)-I2MAX;
	}
	/**
	 * Prepare for add/minus of a ShortInt Pair
	 * @param b
	 * @return
	 */
	final static public int SS(int b) {
		return b-I2OFF;
	}

	final static public int addSS(int a,int b){
		return S(M(a)+M(b),E(a)+E(b));
	}
	
	final static public int minusSS(int a,int b){
		return S(M(a)-M(b),E(a)-E(b));
	}
	
	final static public int addSS(int a,int b,int c){
		return S(M(a)+M(b)+M(c),E(a)+E(b)+E(c));
	}
	
	/**
	 * Short for SS(S(mid,end))
	 * @param mid
	 * @param end
	 * @return
	 */
	final static public int SS(int mid, int end) {
		return S(mid,end)-I2OFF;
	}

	/**
	 * ShortInt Pair to int storage form
	 * @param mid
	 * @param end
	 * @return
	 */
	final static public int S(int mid, int end) {
		return (mid+I2MAX)&I2MSK | (((end+I2MAX)&I2MSK)<<16);
	}
	
	
	
	
	final static public long assemble2(int piece, int from, int to, long extra) {
		int score = 0;
		return (piece << _PIECE) | (from << _FROM) | (to << _TO) | extra | ((score | 0L) << 32);
	}

	final static public long getCastlingState(final long bitmap) {
		return bitmap & CASTLING_STATE;
	}

	final static public int getFrom(final long bitmap) {
		return (int) ((bitmap >> _FROM) & BITS6);
	}

	final static public long bitsFrom(final long bitmap) {
		return 1L << ((bitmap >> _FROM) & BITS6);
	}

	final static public int getTo(final long bitmap) {
		return (int) ((bitmap >> _TO) & BITS6);
	}

	final static public long bitsTo(final long bitmap) {
		return 1L << ((bitmap >> _TO) & BITS6);
	}

	final static public boolean isCapture(final long bitmap) {
		return (bitmap & CAPTURE) != 0;
	}

	/**
	 * Did white make the last move
	 * 
	 * @param bitmap
	 * @return
	 */
	final static public boolean white(final long bitmap) {
		return (bitmap & BLACK) == 0;
	}

	/**
	 * Did black make the last move
	 * 
	 * @param bitmap
	 * @return
	 */
	final static public boolean black(final long bitmap) {
		return (bitmap & BLACK) != 0;
	}

	/**
	 * Is white next to move
	 * 
	 * @param bitmap
	 * @return
	 */
	final static public boolean whiteNext(final long bitmap) {
		return (bitmap & BLACK) > 0;
	}

	final static public boolean isCastling(final long bitmap) {
		return (bitmap & SPECIAL) != 0 && ShortIntPairs.getType(bitmap) == WK;
	}

	final static public boolean isEnpassant(final long bitmap) {
		return (bitmap & SPECIAL) != 0 && ShortIntPairs.getType(bitmap) == WP;
	}

	final static public boolean isPromotion(final long bitmap) {
		return ((bitmap & SPECIAL) != 0) && ShortIntPairs.getType(bitmap) != WK && ShortIntPairs.getType(bitmap) != WP;
	}

	final static public int getPiece(final long bitmap) {
		return (int) ((bitmap & PIECE) >> _PIECE);
	}

	final static public int getType(final long bitmap) {
		return (int) ((bitmap & PIECETYPE) >> _PIECE);
	}

	final static public int halfMoves(final long bitmap) {
		return (int) ((bitmap >> _HALFMOVES) & BITS6);
	}

	final static public int getEnpassant(final long bitmap) {
		int from2 = getFrom(bitmap);
		int to2 = getTo(bitmap);
		switch (getPiece(bitmap)) {
		case WP:
			if (from2 - to2 == -16)
				return from2 + 8;
			return -1;
		case BP:
			if (from2 - to2 == 16)
				return from2 - 8;
			return -1;
		default:
			return -1;
		}
	}

	final static public int getCapturedType(long bitmap) {
		return (int) ((bitmap & CAPTURE) >>> _CAPTURE);
	}

	final static public int getCaptured(long bitmap) {
		return (int) (((bitmap & CAPTURE) >>> _CAPTURE) | (~bitmap & BLACK));
	}

	final static public int score(long bitmap) {
		return (int) (bitmap >> 32);
	}

	final static public long getCastlingState(String castling) {
		return (castling.contains("K") ? CANCASTLE_WHITEKING:0)
				| (castling.contains("Q") ? CANCASTLE_WHITEQUEEN:0)
				| (castling.contains("k") ? CANCASTLE_BLACKKING:0)
				| (castling.contains("q") ? CANCASTLE_BLACKQUEEN:0);
	}


}