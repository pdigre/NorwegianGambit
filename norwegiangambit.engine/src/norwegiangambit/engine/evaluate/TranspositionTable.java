package norwegiangambit.engine.evaluate;


public class TranspositionTable {

	final public static int TTLog2SIZE = 20;   // 10= 1024, 20= 1 million,
	final public static int TTSIZE=1<<TTLog2SIZE;
	final public static int TTMASK=TTSIZE-1;
	final public static int currGen = 0; // Generation number
	final public static TTEntry[] ALL=new TTEntry[TTSIZE];

	final public static int T_EMPTY=0;
	final public static int T_GE=1;
	final public static int T_LE=2;
	final public static int T_EXACT=3;

}
