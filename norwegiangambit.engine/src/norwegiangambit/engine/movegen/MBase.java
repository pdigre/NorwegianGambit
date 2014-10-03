package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public abstract class MBase implements IConst{
	
	final static int[] WPROMOTES=new int[]{IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	final static int[] BPROMOTES=new int[]{IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] WCAPTURES=new int[]{IConst.BP,IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] BCAPTURES=new int[]{IConst.WP,IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	
	int Q, K; // Break castling
	
	final public int from;
	
	public MBase(int from) {
		this.from = from;
	}

	public int[] makeArray(ArrayList<Integer> list) {
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		return arr;
	}

	final static long purge(long bitmap, int subtract) {
		int score = BITS.score(bitmap) - subtract;
		return (((long) score) << 32) | ((int) bitmap);
	}

	public static int[] checkRook(int[] M,long castling) {
		int[] x=new int[M.length];
		for (int i = 0; i < M.length; i++)
			x[i]=MOVEDATAX.create(BASE.ALL[M[i]].bitmap^castling,castling);
		return x;
	}

	public static int[][] castlingKing(int[][] M,long castling) {
		int[][] x=new int[M.length][];
		for (int i = 0; i < M.length; i++)
			x[i]=castling(M[i],castling);
		return x;
	}
	
	private static int[] castling(int[] m,long mask) {
		int[] x=new int[m.length];
		for (int i = 0; i < m.length; i++)
			x[i]=MOVEDATAX.create((BASE.ALL[m[i]].bitmap),mask);
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
	
	public void rookCapture(int to, long bitmap, int captured) {
		if (captured == IConst.BR) {
			if (to == IConst.BR_KING_STARTPOS){
				K=MOVEDATAX.capture(bitmap, captured);
			} else if (to == IConst.BR_QUEEN_STARTPOS){
				Q=MOVEDATAX.capture(bitmap,captured);
			}
		}
		if (captured == IConst.WR) {
			if (to == IConst.WR_KING_STARTPOS){
				K=MOVEDATAX.capture(bitmap, captured);
			} else if (to == IConst.WR_QUEEN_STARTPOS){
				Q=MOVEDATAX.capture(bitmap, captured);
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
