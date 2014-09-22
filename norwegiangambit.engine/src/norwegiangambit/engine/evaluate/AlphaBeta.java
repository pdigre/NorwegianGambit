package norwegiangambit.engine.evaluate;


public class AlphaBeta extends Evaluate {

	public int alphabeta(int alpha, int beta) {
		generate();
		for (int i = 0; i < iAll; i++) {
			setPos(i);
			int score = -child.alphabeta(-beta, -alpha);
			if (score >= beta)
				return beta; // fail hard beta-cutoff
			if (score > alpha)
				alpha = score; // alpha acts like max in MiniMax
		}
		return alpha;
	}

}
