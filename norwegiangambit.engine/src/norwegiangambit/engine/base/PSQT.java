package norwegiangambit.engine.base;

import norwegiangambit.util.IConst;


public class PSQT {
	final static int[][][] PSQT = new int[][][]{ //
		{ // Pawn
		 S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0),
		 S(   5,   5), S(  10,  10), S(  10,  10), S( -20, -20), S( -20, -20), S(  10,  10), S(  10,  10), S(   5,   5),
		 S(   5,   5), S(  -5,  -5), S( -10, -10), S(   0,   0), S(   0,   0), S( -10, -10), S(  -5,  -5), S(   5,   5),
		 S(   0,   0), S(   0,   0), S(   0,   0), S(  20,  20), S(  20,  20), S(   0,   0), S(   0,   0), S(   0,   0),
		 S(   5,   5), S(   5,   5), S(  10,  10), S(  25,  25), S(  25,  25), S(  10,  10), S(   5,   5), S(   5,   5),
		 S(  10,  10), S(  10,  10), S(  20,  20), S(  30,  30), S(  30,  30), S(  20,  20), S(  10,  10), S(  10,  10),
		 S(  50,  50), S(  50,  50), S(  50,  50), S(  50,  50), S(  50,  50), S(  50,  50), S(  50,  50), S(  50,  50),
		 S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0)
		},
		{ // Knight
		 S( -50, -50), S( -40, -40), S( -30, -30), S( -30, -30), S( -30, -30), S( -30, -30), S( -40, -40), S( -50, -50),
		 S( -40, -40), S( -20, -20), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -20, -20), S( -40, -40),
		 S( -30, -30), S(   0,   0), S(  10,  10), S(  15,  15), S(  15,  15), S(  10,  10), S(   0,   0), S( -30, -30),
		 S( -30, -30), S(   5,   5), S(  15,  15), S(  20,  20), S(  20,  20), S(  15,  15), S(   5,   5), S( -30, -30),
		 S( -30, -30), S(   5,   5), S(  15,  15), S(  20,  20), S(  20,  20), S(  15,  15), S(   5,   5), S( -30, -30),
		 S( -30, -30), S(   0,   0), S(  10,  10), S(  15,  15), S(  15,  15), S(  10,  10), S(   0,   0), S( -30, -30),
		 S( -40, -40), S( -20, -20), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -20, -20), S( -40, -40),
		 S( -50, -50), S( -40, -40), S( -30, -30), S( -30, -30), S( -30, -30), S( -30, -30), S( -40, -40), S( -50, -50)
		},
		{ // Bishop
		 S( -20, -20), S( -10, -10), S( -10, -10), S( -10, -10), S( -10, -10), S( -10, -10), S( -10, -10), S( -20, -20),
		 S( -10, -10), S(   5,   5), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   5,   5), S( -10, -10),
		 S( -10, -10), S(  10,  10), S(  10,  10), S(  10,  10), S(  10,  10), S(  10,  10), S(  10,  10), S( -10, -10),
		 S( -10, -10), S(   0,   0), S(  10,  10), S(  10,  10), S(  10,  10), S(  10,  10), S(   0,   0), S( -10, -10),
		 S( -10, -10), S(   5,   5), S(   5,   5), S(  10,  10), S(  10,  10), S(   5,   5), S(   5,   5), S( -10, -10),
		 S( -10, -10), S(   0,   0), S(   5,   5), S(  10,  10), S(  10,  10), S(   5,   5), S(   0,   0), S( -10, -10),
		 S( -10, -10), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -10, -10),
		 S( -20, -20), S( -10, -10), S( -10, -10), S( -10, -10), S( -10, -10), S( -10, -10), S( -10, -10), S( -20, -20)
		},
		{ // Rook
		 S(   0,   0), S(   0,   0), S(   0,   0), S(   5,   5), S(   5,   5), S(   0,   0), S(   0,   0), S(   0,   0),
		 S(  -5,  -5), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(  -5,  -5),
		 S(  -5,  -5), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(  -5,  -5),
		 S(  -5,  -5), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(  -5,  -5),
		 S(  -5,  -5), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(  -5,  -5),
		 S(  -5,  -5), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(  -5,  -5),
		 S(   5,   5), S(  10,  10), S(  10,  10), S(  10,  10), S(  10,  10), S(  10,  10), S(  10,  10), S(   5,   5),
		 S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0)
		},
		{ // Queen
		 S( -20, -20), S( -10, -10), S( -10, -10), S(  -5,  -5), S(  -5,  -5), S( -10, -10), S( -10, -10), S( -20, -20),
		 S( -10, -10), S(   0,   0), S(   5,   5), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -10, -10),
		 S( -10, -10), S(   5,   5), S(   5,   5), S(   5,   5), S(   5,   5), S(   5,   5), S(   0,   0), S( -10, -10),
		 S(  -5,  -5), S(   0,   0), S(   5,   5), S(   5,   5), S(   5,   5), S(   5,   5), S(   0,   0), S(  -5,  -5),
		 S(  -5,  -5), S(   0,   0), S(   5,   5), S(   5,   5), S(   5,   5), S(   5,   5), S(   0,   0), S(  -5,  -5),
		 S( -10, -10), S(   0,   0), S(   5,   5), S(   5,   5), S(   5,   5), S(   5,   5), S(   0,   0), S( -10, -10),
		 S( -10, -10), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S( -10, -10),
		 S( -20, -20), S( -10, -10), S( -10, -10), S(  -5,  -5), S(  -5,  -5), S( -10, -10), S( -10, -10), S( -20, -20)
		},
		{ // King
		 S(  20, -50), S(  30, -30), S(  10, -30), S(   0, -30), S(   0, -30), S(  10, -30), S(  30, -30), S(  20, -50),
		 S(  20, -30), S(  20, -30), S(   0,   0), S(   0,   0), S(   0,   0), S(   0,   0), S(  20, -30), S(  20, -30),
		 S( -10, -30), S( -20, -10), S( -20,  20), S( -40,  30), S( -40,  30), S( -20,  20), S( -20, -10), S( -10, -30),
		 S( -20, -30), S( -30, -10), S( -30,  30), S( -40,  40), S( -40,  40), S( -30,  30), S( -30, -10), S( -20, -30),
		 S( -30, -30), S( -40, -10), S( -40,  30), S( -50,  40), S( -50,  40), S( -40,  30), S( -40, -10), S( -30, -30),
		 S( -30, -30), S( -40, -10), S( -40,  20), S( -50,  30), S( -50,  30), S( -40,  20), S( -40, -10), S( -30, -30),
		 S( -30, -30), S( -40, -20), S( -40, -10), S( -50,   0), S( -50,   0), S( -40, -10), S( -40, -20), S( -30, -30),
		 S( -30, -50), S( -40, -40), S( -40, -30), S( -50, -20), S( -50, -20), S( -40, -30), S( -40, -40), S( -30, -50)
		}
	};
	
	final static int[] S(int m,int e){
		return new int[]{m,e};
	}

	final static int[] get(int[][][] psqt,int piece,int phase,int offset){
		int[] ret=new int[64];
		for (int i = 0; i < 64; i++)
			ret[i]=psqt[piece][i][phase]+offset;
		return ret;
	}
	
	final static int[] PAWN = get(PSQT,0,0,100); 
    final static int[] KNIGHT = get(PSQT,1,0,320);
    final static int[] BISHOP = get(PSQT,2,0,330);
    final static int[] ROOK = get(PSQT,3,0,500);
    final static int[] QUEEN = get(PSQT,4,0,900);
    public final static int[][] KING = new int[][]{get(PSQT,5,0,20000),get(PSQT,5,1,20000)};

    // P = 100
    // N = 320
    // B = 330
    // R = 500
    // Q = 900
    // K = 20000

    private static int[] invert(int[] w) {
        int[] b = new int[64];
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                b[8 * (7 - r) + c] = -w[8 * r + c];
        return b;
    }

    final static int[] BPAWN = invert(PAWN);
    final static int[] BKNIGHT = invert(KNIGHT);
    final static int[] BBISHOP = invert(BISHOP);
    final static int[] BROOK = invert(ROOK);
    final static int[] BQUEEN = invert(QUEEN);

    public final static int[][] BKING = new int[][]{invert(KING[0]),invert(KING[1])};

    public final static int pVal(int p, int type) {
        switch (type) {
            case IConst.WP:
                return PAWN[p];
            case IConst.WN:
                return KNIGHT[p];
            case IConst.WB:
                return BISHOP[p];
            case IConst.WR:
                return ROOK[p];
            case IConst.WQ:
                return QUEEN[p];
            case IConst.WK:
                return KING[0][p];
            case IConst.BP:
                return BPAWN[p];
            case IConst.BN:
                return BKNIGHT[p];
            case IConst.BB:
                return BBISHOP[p];
            case IConst.BR:
                return BROOK[p];
            case IConst.BQ:
                return BQUEEN[p];
            case IConst.BK:
                return BKING[0][p];
        }
        return 0;
    }

	final public static long assemble(int piece, int from, int to, long extra) {
		int score = pVal(to, piece) - pVal(from, piece);
		return (piece << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra | ((score | 0L) << 32);
	}

	final public static long assemblePromote(int pawn, int promote, int from, int to, long extra) {
		int score = pVal(to, promote) - pVal(from, pawn);
		return (promote << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra | ((score | 0L) << 32);
	}

}
