package norwegiangambit.util;

public class BITS {

	final public static long assemble2(int piece, int from, int to, long extra) {
		int score = 0;
		return (piece << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra | ((score | 0L) << 32);
	}

	final public static long getCastlingState(final long bitmap) {
		return bitmap & IConst.CASTLING_STATE;
	}

	final static public int getFrom(final long bitmap) {
		return (int) ((bitmap >> IConst._FROM) & IConst.BITS6);
	}

	final static public long bitsFrom(final long bitmap) {
		return 1L << ((bitmap >> IConst._FROM) & IConst.BITS6);
	}

	final static public int getTo(final long bitmap) {
		return (int) ((bitmap >> IConst._TO) & IConst.BITS6);
	}

	final static public long bitsTo(final long bitmap) {
		return 1L << ((bitmap >> IConst._TO) & IConst.BITS6);
	}

	final public static boolean isCapture(final long bitmap) {
		return (bitmap & IConst.CAPTURE) != 0;
	}

	/**
	 * Did white make the last move
	 * 
	 * @param bitmap
	 * @return
	 */
	final static public boolean white(final long bitmap) {
		return (bitmap & IConst.BLACK) == 0;
	}

	/**
	 * Did black make the last move
	 * 
	 * @param bitmap
	 * @return
	 */
	final static public boolean black(final long bitmap) {
		return (bitmap & IConst.BLACK) != 0;
	}

	/**
	 * Is white next to move
	 * 
	 * @param bitmap
	 * @return
	 */
	final static public boolean whiteNext(final long bitmap) {
		return (bitmap & IConst.BLACK) > 0;
	}

	final static public boolean isCastling(final long bitmap) {
		return (bitmap & IConst.SPECIAL) != 0 && BITS.getType(bitmap) == IConst.WK;
	}

	final static public boolean isEnpassant(final long bitmap) {
		return (bitmap & IConst.SPECIAL) != 0 && BITS.getType(bitmap) == IConst.WP;
	}

	final public static boolean isPromotion(final long bitmap) {
		return ((bitmap & IConst.SPECIAL) != 0) && BITS.getType(bitmap) != IConst.WK && BITS.getType(bitmap) != IConst.WP;
	}

	final public static int getPiece(final long bitmap) {
		return (int) ((bitmap & IConst.PIECE) >> IConst._PIECE);
	}

	final public static int getType(final long bitmap) {
		return (int) ((bitmap & IConst.PIECETYPE) >> IConst._PIECE);
	}

	final public static int halfMoves(final long bitmap) {
		return (int) ((bitmap >> IConst._HALFMOVES) & IConst.BITS6);
	}

	public static int getEnpassant(final long bitmap) {
		int from2 = getFrom(bitmap);
		int to2 = getTo(bitmap);
		switch (getPiece(bitmap)) {
		case IConst.WP:
			if (from2 - to2 == -16)
				return from2 + 8;
			return -1;
		case IConst.BP:
			if (from2 - to2 == 16)
				return from2 - 8;
			return -1;
		default:
			return -1;
		}
	}

	public static int getCapturedType(long bitmap) {
		return (int) ((bitmap & IConst.CAPTURE) >>> IConst._CAPTURE);
	}

	public static int getCaptured(long bitmap) {
		return (int) (((bitmap & IConst.CAPTURE) >>> IConst._CAPTURE) | (~bitmap & IConst.BLACK));
	}

	public static int score(long bitmap) {
		return (int) (bitmap >> 32);
	}

}