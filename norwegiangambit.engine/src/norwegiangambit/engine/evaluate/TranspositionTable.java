package norwegiangambit.engine.evaluate;


public class TranspositionTable {

	/*
DATA-BITS 64
============
2   Type    
1   Slot 1 or 0
5   Depth <32
8   generation <256
16  MoveData

16  evalscore 64k
16  score 64k

KEY 64
======
Zobrist ^ bit1

	 */
	
	final public static int TTLog2SIZE = 20;   // 10= 1024, 20= 1 million,
	final public static int TTSIZE=1<<TTLog2SIZE;
	final public static int TTMASK=TTSIZE-1;
	final public static int currGen = 0; // Generation number
	final public static TTEntry[] ALL=new TTEntry[TTSIZE];


}
