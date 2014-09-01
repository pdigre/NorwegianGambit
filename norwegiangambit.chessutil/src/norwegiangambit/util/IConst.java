package norwegiangambit.util;

/**
 * 4 1-4 - Piecetype
 * 
 * 3 5-7 - Capture
 * 
 * 6 8-13 - From 6 14-19 - To
 * 
 * 4 20 - Castling 6 24 - Halfmoves
 */
public interface IConst {

	int BITS1 = 1;

	int BITS2 = 3;

	int BITS3 = 7;

	int BITS4 = 15;

	int BITS6 = 63;

	// Moving Piece
	int _PIECE = 0;

	int PIECETYPE = BITS3 << _PIECE;

	int PIECE = BITS4 << _PIECE;

	int BLACK = BITS1 << _PIECE + 3;

	// From to
	int _FROM = 4;

	int FROM = BITS6 << _FROM;

	int _TO = 10;

	int TO = BITS6 << _TO;

	// **** Special move ****
	// capture of
	// promotion special bit
	// en-passant special bit
	int _CAPTURE = 16;

	int CAPTURE = BITS3 << _CAPTURE;

	int SPECIAL = BITS1 << _CAPTURE + 3;

	// Game state
	int _CASTLING = 20;

	long CASTLING_STATE = BITS4 << _CASTLING;

	long CANCASTLE_WHITEQUEEN = BITS1 << (_CASTLING);

	long CANCASTLE_WHITEKING = BITS1 << (_CASTLING + 1);

	long CANCASTLE_WHITE = CANCASTLE_WHITEQUEEN | CANCASTLE_WHITEKING;

	long CANCASTLE_BLACKQUEEN = BITS1 << (_CASTLING + 2);

	long CANCASTLE_BLACKKING = BITS1 << (_CASTLING + 3);

	long CANCASTLE_BLACK = CANCASTLE_BLACKQUEEN | CANCASTLE_BLACKKING;

	int _HALFMOVES = 24;

	long HALFMOVES = BITS6 << _HALFMOVES;

	// piecetype
	int NONE = 0;

	int SLIDER = 4;
	
	int HIGHER = 2;
	
	int WP = 1;

	int WN = 2;

	int WK = 3;

	int WB = 5;

	int WR = 6;

	int WQ = 7;

	int BP = WP | BLACK;

	int BN = WN | BLACK;

	int BB = WB | BLACK;

	int BR = WR | BLACK;

	int BQ = WQ | BLACK;

	int BK = WK | BLACK;

	// game state
	int CHECK = 1;

	int MATE = 2;

	int GOAL_LINE = 56;
	int WK_STARTPOS = 4;
	int BK_STARTPOS = WK_STARTPOS + GOAL_LINE;
	int BR_KING_STARTPOS = 63;
	int BR_QUEEN_STARTPOS = 56;
	int WR_KING_STARTPOS = 7;
	int WR_QUEEN_STARTPOS = 0;

	long CWQ = 7L << WK_STARTPOS - 3;
	long CWK = 3L << WK_STARTPOS + 1;
	long CBQ = 7L << BK_STARTPOS - 3;
	long CBK = 3L << BK_STARTPOS + 1;

	long LEFTLANE = 0x0101010101010101L;
	long RIGHTLANE = 0x8080808080808080L;
	long LEFTMASK = ~LEFTLANE;
	long RIGHTMASK = ~RIGHTLANE;
	
	class BITS {

		final public static long assemble2(int piece, int from, int to, long extra) {
			int score = 0;
			return (piece << _PIECE) | (from << _FROM) | (to << _TO) | extra | ((score | 0L) << 32);
		}

		final public static long getCastlingState(final long bitmap) {
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

		final public static boolean isCapture(final long bitmap) {
			return (bitmap & CAPTURE) != 0;
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
			return (bitmap & SPECIAL) != 0 && BITS.getType(bitmap) == WK;
		}

		final static public boolean isEnpassant(final long bitmap) {
			return (bitmap & SPECIAL) != 0 && BITS.getType(bitmap) == WP;
		}

		final public static boolean isPromotion(final long bitmap) {
			return ((bitmap & SPECIAL) != 0) && BITS.getType(bitmap) != WK && BITS.getType(bitmap) != WP;
		}

		final public static int getPiece(final long bitmap) {
			return (int) ((bitmap & PIECE) >> _PIECE);
		}

		final public static int getType(final long bitmap) {
			return (int) ((bitmap & PIECETYPE) >> _PIECE);
		}

		final public static int halfMoves(final long bitmap) {
			return (int) ((bitmap >> _HALFMOVES) & BITS6);
		}

		public static int getEnpassant(final long bitmap) {
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
}
