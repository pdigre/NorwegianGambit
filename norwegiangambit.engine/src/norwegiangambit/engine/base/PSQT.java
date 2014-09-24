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

	final static int[][] get(int[][][] psqt,int piece,int mid,int end){
		int[][] ret=new int[64][2];
		for (int i = 0; i < 64; i++){
			ret[i][0]=psqt[piece][i][0]+mid;
			ret[i][1]=psqt[piece][i][1]+end;
		}
		return ret;
	}
	
	public final static int[][] PAWN = fill(0,100,100); 
	public final static int[][] KNIGHT = fill(1,320,320);
    public final static int[][] BISHOP = fill(2,330,330);
    public final static int[][] ROOK = fill(3,500,500);
    public final static int[][] QUEEN = fill(4,900,900);
    public final static int[][] KING = fill(5,20000,2000);

	public static int[][] fill(int nfill,int mid,int end) {
		return get(PSQT,nfill,mid,end);
	}

    // P = 100
    // N = 320
    // B = 330
    // R = 500
    // Q = 900
    // K = 20000

    public final static int[][] BPAWN 	= invert(PAWN);
    public final static int[][] BKNIGHT = invert(KNIGHT);
    public final static int[][] BBISHOP = invert(BISHOP);
    public final static int[][] BROOK 	= invert(ROOK);
    public final static int[][] BQUEEN 	= invert(QUEEN);
    public final static int[][] BKING 	= invert(KING);

	public static int[][] invert(int[][] w) {
        int[][] b = new int[64][2];
        for (int s = 0; s < 2; s++) {
	        for (int r = 0; r < 8; r++)
	            for (int c = 0; c < 8; c++)
	                b[8 * (7 - r) + c][s] = -w[8 * r + c][s];
		}
        return b;
	}

    public final static int[] pVal(int sq, int piece) {
        switch (piece) {
            case IConst.WP:
                return PAWN[sq];
            case IConst.WN:
                return KNIGHT[sq];
            case IConst.WB:
                return BISHOP[sq];
            case IConst.WR:
                return ROOK[sq];
            case IConst.WQ:
                return QUEEN[sq];
            case IConst.WK:
                return KING[sq];
            case IConst.BP:
                return BPAWN[sq];
            case IConst.BN:
                return BKNIGHT[sq];
            case IConst.BB:
                return BBISHOP[sq];
            case IConst.BR:
                return BROOK[sq];
            case IConst.BQ:
                return BQUEEN[sq];
            case IConst.BK:
                return BKING[sq];
        }
        return new int[]{0,0};
    }

	final public static long assemble(int piece, int from, int to, long extra) {
		return (piece << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
	}

	final public static long assemblePromote(int pawn, int promote, int from, int to, long extra) {
		return (promote << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
	}

}
