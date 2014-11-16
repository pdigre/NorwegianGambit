package norwegiangambit.util;

import static norwegiangambit.util.ShortIntPairs.*;

/**
 * Simplified Evaluation Function 
 *
 */
public class PSQT_Stockfish extends PSQT{

	public PSQT_Stockfish(){
		super(
			new int[]{WP,WN,WB,WR,WQ,WK}, 
			new int[]{S(198,258),S(817,846),S(836,857),S(1270,1278),S(2521,2558),S(9999,9999)}, 
			new int[][]{ //
			{ // Pawn
				 S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0),
				 S( -20,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -20,   0),
				 S( -20,   0), S(   0,   0), S(  10,   0), S(  20,   0), S(  20,   0), S(  10,   0), S(   0,   0), S( -20,   0),
				 S( -20,   0), S(   0,   0), S(  20,   0), S(  40,   0), S(  40,   0), S(  20,   0), S(   0,   0), S( -20,   0),
				 S( -20,   0), S(   0,   0), S(  10,   0), S(  20,   0), S(  20,   0), S(  10,   0), S(   0,   0), S( -20,   0),
				 S( -20,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -20,   0),
				 S( -20,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -20,   0),
				 S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0)
				},
				{ // Knight
				 S(-144, -98), S(-109, -83), S( -85, -51), S( -73, -16), S( -73, -16), S( -85, -51), S(-109, -83), S(-144, -98),
				 S( -88, -68), S( -43, -53), S( -19, -21), S(  -7,  14), S(  -7,  14), S( -19, -21), S( -43, -53), S( -88, -68),
				 S( -69, -53), S( -24, -38), S(   0,  -6), S(  12,  29), S(  12,  29), S(   0,  -6), S( -24, -38), S( -69, -53),
				 S( -28, -42), S(  17, -27), S(  41,   5), S(  53,  40), S(  53,  40), S(  41,   5), S(  17, -27), S( -28, -42),
				 S( -30, -42), S(  15, -27), S(  39,   5), S(  51,  40), S(  51,  40), S(  39,   5), S(  15, -27), S( -30, -42),
				 S( -10, -53), S(  35, -38), S(  59,  -6), S(  71,  29), S(  71,  29), S(  59,  -6), S(  35, -38), S( -10, -53),
				 S( -64, -68), S( -19, -53), S(   5, -21), S(  17,  14), S(  17,  14), S(   5, -21), S( -19, -53), S( -64, -68),
				 S(-200, -98), S( -65, -83), S( -41, -51), S( -29, -16), S( -29, -16), S( -41, -51), S( -65, -83), S(-200, -98)
				},
				{ // Bishop
				 S( -54, -65), S( -27, -42), S( -34, -44), S( -43, -26), S( -43, -26), S( -34, -44), S( -27, -42), S( -54, -65),
				 S( -29, -43), S(   8, -20), S(   1, -22), S(  -8,  -4), S(  -8,  -4), S(   1, -22), S(   8, -20), S( -29, -43),
				 S( -20, -33), S(  17, -10), S(  10, -12), S(   1,   6), S(   1,   6), S(  10, -12), S(  17, -10), S( -20, -33),
				 S( -19, -35), S(  18, -12), S(  11, -14), S(   2,   4), S(   2,   4), S(  11, -14), S(  18, -12), S( -19, -35),
				 S( -22, -35), S(  15, -12), S(   8, -14), S(  -1,   4), S(  -1,   4), S(   8, -14), S(  15, -12), S( -22, -35),
				 S( -28, -33), S(   9, -10), S(   2, -12), S(  -7,   6), S(  -7,   6), S(   2, -12), S(   9, -10), S( -28, -33),
				 S( -32, -43), S(   5, -20), S(  -2, -22), S( -11,  -4), S( -11,  -4), S(  -2, -22), S(   5, -20), S( -32, -43),
				 S( -49, -65), S( -22, -42), S( -29, -44), S( -38, -26), S( -38, -26), S( -29, -44), S( -22, -42), S( -49, -65)
				},
				{ // Rook
				 S( -22,   3), S( -17,   3), S( -12,   3), S(  -8,   3), S(  -8,   3), S( -12,   3), S( -17,   3), S( -22,   3),
				 S( -22,   3), S(  -7,   3), S(  -2,   3), S(   2,   3), S(   2,   3), S(  -2,   3), S(  -7,   3), S( -22,   3),
				 S( -22,   3), S(  -7,   3), S(  -2,   3), S(   2,   3), S(   2,   3), S(  -2,   3), S(  -7,   3), S( -22,   3),
				 S( -22,   3), S(  -7,   3), S(  -2,   3), S(   2,   3), S(   2,   3), S(  -2,   3), S(  -7,   3), S( -22,   3),
				 S( -22,   3), S(  -7,   3), S(  -2,   3), S(   2,   3), S(   2,   3), S(  -2,   3), S(  -7,   3), S( -22,   3),
				 S( -22,   3), S(  -7,   3), S(  -2,   3), S(   2,   3), S(   2,   3), S(  -2,   3), S(  -7,   3), S( -22,   3),
				 S( -11,   3), S(   4,   3), S(   9,   3), S(  13,   3), S(  13,   3), S(   9,   3), S(   4,   3), S( -11,   3),
				 S( -22,   3), S( -17,   3), S( -12,   3), S(  -8,   3), S(  -8,   3), S( -12,   3), S( -17,   3), S( -22,   3)
				},
				{ // Queen
				 S(  -2, -80), S(  -2, -54), S(  -2, -42), S(  -2, -30), S(  -2, -30), S(  -2, -42), S(  -2, -54), S(  -2, -80),
				 S(  -2, -54), S(   8, -30), S(   8, -18), S(   8,  -6), S(   8,  -6), S(   8, -18), S(   8, -30), S(  -2, -54),
				 S(  -2, -42), S(   8, -18), S(   8,  -6), S(   8,   6), S(   8,   6), S(   8,  -6), S(   8, -18), S(  -2, -42),
				 S(  -2, -30), S(   8,  -6), S(   8,   6), S(   8,  18), S(   8,  18), S(   8,   6), S(   8,  -6), S(  -2, -30),
				 S(  -2, -30), S(   8,  -6), S(   8,   6), S(   8,  18), S(   8,  18), S(   8,   6), S(   8,  -6), S(  -2, -30),
				 S(  -2, -42), S(   8, -18), S(   8,  -6), S(   8,   6), S(   8,   6), S(   8,  -6), S(   8, -18), S(  -2, -42),
				 S(  -2, -54), S(   8, -30), S(   8, -18), S(   8,  -6), S(   8,  -6), S(   8, -18), S(   8, -30), S(  -2, -54),
				 S(  -2, -80), S(  -2, -54), S(  -2, -42), S(  -2, -30), S(  -2, -30), S(  -2, -42), S(  -2, -54), S(  -2, -80)
				},
		});
		init("a=4"); 
	}

}
