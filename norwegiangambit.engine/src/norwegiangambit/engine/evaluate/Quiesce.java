package norwegiangambit.engine.evaluate;

public class Quiesce extends Evaluate {

	@Override
	public int alphabeta(int alpha, int beta) {
		return score();
	}
	
}
