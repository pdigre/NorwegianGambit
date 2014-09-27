package norwegiangambit.engine.movegen;

import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public abstract class MBase implements IConst{
	
	final static int[] WPROMOTES=new int[]{IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	final static int[] BPROMOTES=new int[]{IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] WCAPTURES=new int[]{IConst.BP,IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] BCAPTURES=new int[]{IConst.WP,IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	
	MOVEDATA Q, K; // Break castling
	
	final public int from;
	
	public MBase(int from) {
		this.from = from;
	}

	final static long purge(long bitmap, int subtract) {
		int score = BITS.score(bitmap) - subtract;
		return (((long) score) << 32) | ((int) bitmap);
	}

	public static MOVEDATA[] castlingRook(MOVEDATA[] M) {
		MOVEDATA[] x=new MOVEDATA[M.length];
		for (int i = 0; i < M.length; i++)
			x[i]=MOVEDATAX.create(M[i].bitmap);
		return x;
	}

	public static MOVEDATA[][] castlingKing(MOVEDATA[][] M,long castling) {
		MOVEDATA[][] x=new MOVEDATA[M.length][];
		for (int i = 0; i < M.length; i++)
			x[i]=castling(M[i],castling);
		return x;
	}
	
	private static MOVEDATA[] castling(MOVEDATA[] m,long mask) {
		MOVEDATA[] x=new MOVEDATA[m.length];
		for (int i = 0; i < m.length; i++)
			x[i]=MOVEDATAX.create(m[i].bitmap^mask);
		return x;
	}

	public void genLegal(Movegen gen){
		//
	}

	public static <X extends MBase> void genLegal(Movegen gen,long b, X[] arr) {
		int bits = Long.bitCount(b);
		for (int j = 0; j < bits; j++) {
			int from = Long.numberOfTrailingZeros(b);
			b ^= 1L << from;
			arr[from].genLegal(gen);
		}
	}
	
	public void rookCapture(int to, long bitmap, int c) {
		if (c == IConst.BR) {
			if (to == IConst.BR_KING_STARTPOS){
				K=MOVEDATAX.capture(bitmap, c);
			} else if (to == IConst.BR_QUEEN_STARTPOS){
				Q=MOVEDATAX.capture(bitmap,c);
			}
		}
		if (c == IConst.WR) {
			if (to == IConst.WR_KING_STARTPOS){
				K=MOVEDATAX.capture(bitmap, c);
			} else if (to == IConst.WR_QUEEN_STARTPOS){
				Q=MOVEDATAX.capture(bitmap, c);
			}
		}
	}

	final public static long assemble(int piece, int from, int to, long extra) {
		return (piece << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
	}

	final public static long assemblePromote(int pawn, int promote, int from, int to, long extra) {
		return (promote << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
	}

}
