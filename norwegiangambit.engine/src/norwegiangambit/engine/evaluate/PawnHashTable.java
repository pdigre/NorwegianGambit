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
	final public static long[] validate=new long[TTSIZE];
	
    final public static int get(long zobrist, long key2) {
		int i1=(int)zobrist&TTMASK;
		if(hash[i1]==(zobrist^key2)){
			if(validate[i1]!=key2){
//				System.out.println("Key collision:");
				return -1;
			}
			return i1;
		}
		return -1;
	}
    
    final public int getPassedBonusW(long data){
    	return (int) ((data >> 16) & 0xFFFFL);
    }

    final public int getPassedBonusB(long data){
    	return (int) ((data >> 32) & 0xFFFFL);
    }

    final public int getScore(long data){
    	return (int) ( data & 0xFFFFL);
    }

}
