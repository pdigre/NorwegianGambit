package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;
import norwegiangambit.util.PSQT;
import norwegiangambit.util.polyglot.IZobristKey;

public abstract class MBase implements IConst{
	
	final static int[] WPROMOTES=new int[]{WN,WB,WR,WQ};
	final static int[] BPROMOTES=new int[]{BN,BB,BR,BQ};
	final static int[] WCAPTURES=new int[]{BP,BN,BB,BR,BQ};
	final static int[] BCAPTURES=new int[]{WP,WN,WB,WR,WQ};
	
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

	public static void castlingKing(int B, int E,long castling,int offset) {
		for(int i=B;i<E;i++)
			MOVEDATAX.create2((MOVEDATA.ALL[i].bitmap),castling,offset+i-B);
	}
	
	public void rookCapture(int to, long bitmap, int captured) {
		if (captured == BR) {
			if (to == BR_KING_STARTPOS){
				k=MOVEDATAX.rookCapture(bitmap);
			} else if (to == BR_QUEEN_STARTPOS){
				q=MOVEDATAX.rookCapture(bitmap);
			}
		}
		if (captured == WR) {
			if (to == WR_KING_STARTPOS){
				k=MOVEDATAX.rookCapture(bitmap);
			} else if (to == WR_QUEEN_STARTPOS){
				q=MOVEDATAX.rookCapture(bitmap);
			}
		}
	}

	final public static long assemble(int piece, int from, int to, long extra) {
		return (piece << _PIECE) | (from << _FROM) | (to << _TO) | extra;
	}

	final public static long assemblePromote(int pawn, int promote, int from, int to, long extra) {
		return (promote << _PIECE) | (from << _FROM) | (to << _TO) | extra;
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
