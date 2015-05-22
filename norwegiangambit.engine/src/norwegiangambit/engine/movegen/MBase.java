package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;
import norwegiangambit.util.PSQT;
import norwegiangambit.util.polyglot.IZobristKey;

public abstract class MBase implements IConst{
	
	final static int[] WPROMOTES=new int[]{IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	final static int[] BPROMOTES=new int[]{IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] WCAPTURES=new int[]{IConst.BP,IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] BCAPTURES=new int[]{IConst.WP,IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	
	public static PSQT psqt; 
	public static IZobristKey zobrist;

	public static int psqt(int sq, int piece) {
		return psqt.psqt(sq, piece);
	}

	public static long getZobrist(int piece, int sq){
		return zobrist.get(piece, sq);
	}

	public static long getZobrist(int i){
		return zobrist.get(i);
	}

	public static long keyCastling(long castling) {
		return zobrist.keyCastling(castling);
	}

	public int Q,K;
	public MOVEDATAX q,k;
	
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
			x[i]=MOVEDATAX.create(MOVEDATA.ALL[M[i]].bitmap^castling,castling);
		return x;
	}

	public static void castlingKing(int B, int E,long castling) {
		for(int i=B;i<E;i++)
			MOVEDATAX.create((MOVEDATA.ALL[i].bitmap),castling);
	}
	
	public static int[][] castlingKing(int[][] M,long castling) {
		int[][] x=new int[M.length][];
		for (int i = 0; i < M.length; i++)
			x[i]=castling(M[i],castling);
		return x;
	}
	
	private static int[] castling(int[] m,long mask) {
		int[] x=new int[m.length];
		x[m.length-1]=MOVEDATAX.create((MOVEDATA.ALL[m[m.length-1]].bitmap),mask);
		for (int i = 0; i < m.length-1; i++)
			x[i]=MOVEDATAX.create((MOVEDATA.ALL[m[i]].bitmap),mask);
		return x;
	}

	public void rookCapture2(int to, long bitmap, int captured) {
		if (captured == IConst.BR) {
			if (to == IConst.BR_KING_STARTPOS){
				k=MOVEDATAX.capture3(bitmap, captured);
			} else if (to == IConst.BR_QUEEN_STARTPOS){
				q=MOVEDATAX.capture3(bitmap,captured);
			}
		}
		if (captured == IConst.WR) {
			if (to == IConst.WR_KING_STARTPOS){
				k=MOVEDATAX.capture3(bitmap, captured);
			} else if (to == IConst.WR_QUEEN_STARTPOS){
				q=MOVEDATAX.capture3(bitmap, captured);
			}
		}
	}

	public void rookCapture(int to, long bitmap, int captured) {
		if (captured == IConst.BR) {
			if (to == IConst.BR_KING_STARTPOS){
				k=MOVEDATAX.capture3(bitmap, captured);
				K=MOVEDATA.add(k);
			} else if (to == IConst.BR_QUEEN_STARTPOS){
				q=MOVEDATAX.capture3(bitmap,captured);
				Q=MOVEDATA.add(q);
			}
		}
		if (captured == IConst.WR) {
			if (to == IConst.WR_KING_STARTPOS){
				k=MOVEDATAX.capture3(bitmap, captured);
				K=MOVEDATA.add(k);
			} else if (to == IConst.WR_QUEEN_STARTPOS){
				q=MOVEDATAX.capture3(bitmap,captured);
				Q=MOVEDATA.add(q);
			}
		}
	}

	final public static long assemble(int piece, int from, int to, long extra) {
		return (piece << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
	}

	final public static long assemblePromote(int pawn, int promote, int from, int to, long extra) {
		return (promote << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
	}

	/**
	 * is it inside the board = legal move
	 * 
	 * @param to
	 * @param from
	 * @return
	 */
	static boolean inside(int to, int from) {
		if (to < 0 || to > 63)
			return false;
		int x1 = to % 8;
		int x2 = from % 8;
		if ((x1 < 3 && x2 > 4) || (x2 < 3 && x1 > 4))
			return false;
		return true;
	}

	

}
