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
}
