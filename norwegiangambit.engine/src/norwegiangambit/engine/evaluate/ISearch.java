package norwegiangambit.engine.evaluate;

/**
 * Interface for chess engine time control
 * <br> Used for ELO testing
 *
 */
public interface ISearch {

	/**
	 * Start engine
	 * @param fen - FEN string
	 * @return
	 */
	void start(String fen);
	
	/**
	 * Stop engine
	 */
	void stop(); 

	/**
	 * @return String for best path found
	 */
	String bestPath();
	
	/**
	 * Use to notify tester that a better move has been found
	 * @param runner 
	 */
	void foundBetter(Runnable runnable);

}
