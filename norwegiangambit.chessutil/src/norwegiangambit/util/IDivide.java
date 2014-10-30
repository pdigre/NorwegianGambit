package norwegiangambit.util;

import java.util.List;

/**
 * Interface for PERFT / DIVIDE engine
 */
public interface IDivide {

	/**
	 * Eval results to return
	 * <br> 1. PERFT - fill (move, count)
	 * <br> 2. EVAL  - fill (move, count, value)
	 */
	public class Eval implements Comparable<Eval>{
		/**
		 * 
		 */
		public String move;
		public long count=0;
		public long quiesce=0;
		public int value=0;

		/**
		 * @param move - startmove notation of type e2e4, a7a8q (for queen promotion)
		 * @param count - leaf-node count for portion of startmove
		 * @param value - if value to return for other tests
		 */
		public Eval(String move,int count, int value) {
			this.move=move;
			this.count = count;
			this.value = value;
		}

		@Override
		public int compareTo(Eval e2) {
			return move.compareTo(e2.move);
		}
		
	}

	/**
	 * Method to implement in PERFT/DIVIDE engine
	 * @param fen - FEN string
	 * @param depth - Depth to count PERFT
	 * @return list of Evals - one per start-moves
	 */
	List<Eval> divide(String fen, int depth);

	
}
