package norwegiangambit.engine.evaluate;

public class PawnHashTable {
//    long zobrist;
//    int score;         // Positive score means good for white
//    int passedBonusW;
//    int passedBonusB;
//    long passedPawnsW;     // The most advanced passed pawns for each file
//    long passedPawnsB;

	
	
	final public static int TTLog2SIZE = 16;   // 10= 1024, 20= 1 million,
	final public static int TTSIZE=1<<TTLog2SIZE;
	final public static int TTMASK=TTSIZE-1;

	final public static long[] hash=new long[TTSIZE];
	final public static long[] data=new long[TTSIZE];
	final public static long[] passedW=new long[TTSIZE];
	final public static long[] passedB=new long[TTSIZE];
	
    final public static int get(long zobrist) {
		int i1=(int)zobrist&TTMASK;
		if(hash[i1]==zobrist){
			return i1;
		}
		return -1;
	}
    
    final static public int getPassedBonusW(long data){
    	return (int) ((data >> 16) & 0xFFFFL);
    }

    final static public int getPassedBonusB(long data){
    	return (int) ((data >> 32) & 0xFFFFL);
    }

    final static public int getScore(long data){
    	return (int) ( data & 0xFFFFL);
    }

	public static int set(int score, long zobrist, int passedBonusW, int passedBonusB, long passedPawnsW, long passedPawnsB) {
		int i1=(int)zobrist&TTMASK;
		hash[i1]=zobrist;
		passedW[i1]=passedPawnsW;
		passedB[i1]=passedPawnsB;
		data[i1]=((long)score)|(((long)passedBonusW)<<16)|(((long)passedBonusB)<<32);
		return 0;
	}

}
