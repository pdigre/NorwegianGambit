package norwegiangambit.engine.evaluate;


public class TranspositionTable {

	final public static int TTLog2SIZE = 16;
	final public static int TTSIZE=1<<TTLog2SIZE;
	final public static int currGen = 0; // Generation number
	final public static TTEntry[] ALL=new TTEntry[TTSIZE];

	final public static int T_EMPTY=0;
	final public static int T_GE=1;
	final public static int T_LE=2;
	final public static int T_EXACT=3;
	
	public class TTEntry{
		public long zobrist;
		public int generation;
		public int move;
		public int evalScore;  // Score from static evaluation 
		public int score;      // Score from search
		public int depthSlot;  // Search depth (bit 0-14) and hash slot (bit 15).
        
        public int getType(){
        	return generation&3;
        }
        
        /** Return true if this object is more valuable than the other, false otherwise. */
        public final boolean betterThan(TTEntry other, int currGen) {
            if ((generation < currGen) != (other.generation == currGen)) {
                return generation == currGen;   // Old entries are less valuable
            }
            if ((type() == T_EXACT) != (other.type() == T_EXACT)) {
                return type() == T_EXACT;         // Exact score more valuable than lower/upper bound
            }
            if (getDepth() != other.getDepth()) {
                return getDepth() > other.getDepth();     // Larger depth is more valuable
            }
            return false;   // Otherwise, pretty much equally valuable
        }

        public final int type(){
        	return generation&3;
        }
        
//        /** Return true if entry is good enough to spend extra time trying to avoid overwriting it. */
//        public final boolean valuable(int currGen) {
//            if (generation != currGen)
//                return false;
//            return (type() == T_EXACT) || (getDepth() > 3 * Search.plyScale);
//        }

        /** Get depth from the hash entry. */
        public final int getDepth() {
            return depthSlot & 0x7fff;
        }

        /** Set depth. */
        public final void setDepth(int d) {
            depthSlot &= 0x8000;
            depthSlot |= ((short)d) & 0x7fff;
        }

	}

}
