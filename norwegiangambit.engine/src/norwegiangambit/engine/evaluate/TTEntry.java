package norwegiangambit.engine.evaluate;

public class TTEntry{
		public long zobrist;
		public int generation;
		public int move;
		public int evalScore;  // Score from static evaluation 
		public int score;      // Score from search
		public int depthSlot;  // Search depth (bit 0-14) and hash slot (bit 15).
		public long validate;
        
        /** Return true if this object is more valuable than the other, false otherwise. */
        public final boolean betterThan(TTEntry other, int currGen) {
            if ((generation < currGen) != (other.generation == currGen)) {
                return generation == currGen;   // Old entries are less valuable
            }
            if ((getType() == TranspositionTable.T_EXACT) != (other.getType() == TranspositionTable.T_EXACT)) {
                return getType() == TranspositionTable.T_EXACT;         // Exact score more valuable than lower/upper bound
            }
            if (getDepth() != other.getDepth()) {
                return getDepth() > other.getDepth();     // Larger depth is more valuable
            }
            return false;   // Otherwise, pretty much equally valuable
        }

        public final int getType(){
        	return generation&3;
        }
        public final void setType(int type){
        	generation=(generation&~3)& type;
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

		public boolean isExact() {
			return (generation&3)==TranspositionTable.T_EXACT;
		}

	}