package norwegiangambit.util.polyglot;


public interface IZobristKey {
	public final static int ZOBRIST_CWK = 768;
	public final static int ZOBRIST_CWQ = 769;
	public final static int ZOBRIST_CBK = 770;
	public final static int ZOBRIST_CBQ = 771;
	public final static int ZOBRIST_ENP = 772;
	public final static int ZOBRIST_NXT = 780;
	long get(int piece, int sq);
	long get(int i);
	long getKey(boolean isWhite, long castling, int enpassant, int[] brd);
	long keyCastling(long castling);
}
