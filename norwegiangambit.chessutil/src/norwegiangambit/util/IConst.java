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

	int BITS1 = 1,BITS2 = 3,BITS3 = 7,BITS4 = 15,BITS6 = 63;

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
	int NONE = 0,SLIDER = 4,HIGHER = 2;
	int WP = 1,WN = 2,WK = 3,WB = 5,WR = 6,WQ = 7,BP = 9,BN = 10,BK = 11,BB = 13,BR = 14,BQ = 15;

	// game state
	int CHECK = 1,MATE = 2;

	int GOAL_LINE = 56;
	int WK_STARTPOS = 4;
	int BK_STARTPOS = WK_STARTPOS + GOAL_LINE;
	int BR_KING_STARTPOS = 63;
	int BR_QUEEN_STARTPOS = 56;
	int WR_KING_STARTPOS = 7;
	int WR_QUEEN_STARTPOS = 0;

	// Spaces next to King that must be empty for castling
	long CWQ_FREE = 7L << WK_STARTPOS - 3;
	long CWK_FREE = 3L << WK_STARTPOS + 1;
	long CBQ_FREE = 7L << BK_STARTPOS - 3;
	long CBK_FREE = 3L << BK_STARTPOS + 1;

	// Space next to King that cannot be attacked for castling
	long CWQ_MASK=(1L<<WK_STARTPOS - 1) | (1L<<WK_STARTPOS - 2);
	long CWK_MASK=(1L<<WK_STARTPOS + 1) | (1L<<WK_STARTPOS + 2);
	long CBQ_MASK=(1L<<BK_STARTPOS - 1) | (1L<<BK_STARTPOS - 2);
	long CBK_MASK=(1L<<BK_STARTPOS + 1) | (1L<<BK_STARTPOS + 2);

	long MaskAFile = 0x0101010101010101L;
	long MaskHFile = 0x8080808080808080L;
	long MaskRow2 = 0xFF00L;
	long MaskRow7 = 0x00FF000000000000L;
	long MaskGoal = 0xFF000000000000FFL;
	long MaskBToHFiles = ~MaskAFile;
	long MaskAToGFiles = ~MaskHFile;
    long MaskDarkSq    = 0xAA55AA55AA55AA55L;
    long MaskLightSq   = 0x55AA55AA55AA55AAL;

    public static final long maskCorners   = 0x8100000000000081L;

	int LEFT = -1,RIGHT = 1,UP = 8,DOWN = -8;

	int[] DIAGONAL_MOVES = new int[] { UP + LEFT, UP + RIGHT, DOWN + LEFT, DOWN + RIGHT };
	int[] LINE_MOVES = new int[] { UP, DOWN, LEFT, RIGHT };
	int[] KNIGHT_MOVES = new int[] { UP + LEFT + LEFT, UP + UP + LEFT, UP + RIGHT + RIGHT, UP + UP + RIGHT, DOWN + LEFT + LEFT,
			DOWN + DOWN + LEFT, DOWN + RIGHT + RIGHT, DOWN + DOWN + RIGHT };

}
