package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.base.MOVEDATA;


public class AlphaBeta extends Evaluate {

	// Minimax
	public int alphabeta3(int alpha, int beta) {
		generate();
		for (int i = 0; i < iAll; i++) {
			MOVEDATA md = moves[i];
			make(md);
			int score = -next.alphabeta(-beta, -alpha);
			if (score > alpha)
				alpha = score; // alpha acts like max in MiniMax
		}
		return alpha;
	}

	// Alphabeta
	public int alphabeta2(int alpha, int beta) {
		generate();
		for (int i = 0; i < iAll; i++) {
			MOVEDATA md = moves[i];
			make(md);
			int score = -next.alphabeta(-beta, -alpha);
			if (score >= beta)
				return beta; // fail hard beta-cutoff
			if (score > alpha)
				alpha = score; // alpha acts like max in MiniMax
		}
		return alpha;
	}

	// PVS
	public int alphabeta(int alfa,int beta) {
		generate();
		make(moves[0]);
		int bestscore = -next.alphabeta(-beta, -alfa);
		if( bestscore > alfa ) {
			if( bestscore >= beta )
				return bestscore;
			alfa = bestscore;
		}
		for (int i = 1; i < iAll; i++) {
			MOVEDATA md = moves[i];
			make(md);
			int score = -next.alphabeta(-alfa-1, -alfa);
			if( score > alfa && score < beta ) {
				score = -next.alphabeta(-beta, -alfa);
				if( score > alfa )
					alfa = score;
			}
			if( score > bestscore ) {
				if( score >= beta )
					return score;
				bestscore = score;
			}
		}
		return bestscore;
	}
}
