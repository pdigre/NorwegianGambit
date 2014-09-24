package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.base.MOVEDATA;


public class AlphaBeta extends Evaluate {

	public int alphabeta(int alpha, int beta) {
		generate();
		for (int i = 0; i < iAll; i++) {
			MOVEDATA md = moves[i];
			make(md);
			int score = -child.alphabeta(-beta, -alpha);
			if (score >= beta)
				return beta; // fail hard beta-cutoff
			if (score > alpha)
				alpha = score; // alpha acts like max in MiniMax
		}
		return alpha;
	}

}
