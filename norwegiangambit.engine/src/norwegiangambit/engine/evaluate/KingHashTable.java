package norwegiangambit.engine.evaluate;

public class KingHashTable {
	final public static int TTLog2SIZE = 16;   // 10= 1024, 20= 1 million,
	final public static int TTSIZE=1<<TTLog2SIZE;
	final public static int TTMASK=TTSIZE-1;

	final public static long[] hash=new long[TTSIZE];
	final public static long[] data=new long[TTSIZE];
	
    final public static int get(long zobrist) {
		int i1=(int)zobrist&TTMASK;
		if(hash[i1]==zobrist)
			return i1;
		return -1;
	}
    
    final static public int getScore(long data){
    	return (int) ( data & 0xFFFFL);
    }

	public static void set(long zobrist, int score) {
		int i1=(int)zobrist&TTMASK;
		hash[i1]=zobrist;
		data[i1]=(long) score;
	}

}
