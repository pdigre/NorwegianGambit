package norwegiangambit.engine.evaluate;

public interface IThinker {

	/**
	 * 
	 * @param fen
	 * @return
	 */
	void start(String fen);
	
	void stop(); 

	String bestPath();
	
	/**
	 * When a bette move has been found
	 * @param runner 
	 */
	void foundBetter(Runnable runnable);

}
