package norwegiangambit.engine.evaluate;


public class EvalTester extends Tester{

	@Override
	public Evaluate insert(Eval eval, int depth, int level) {
		if(level == depth - 1)
			return new LeafGen(eval);
		return new PVS();
//		return depth-level>4 ? new PVS():new AlphaBeta();
	}


	class MiniMax extends Evaluate {

		@Override
		public void make(int md) {
			super.make(md);
			((Evaluate)deeper).evaluate(md);
		}

		// Minimax
		public int alphabeta(int alpha, int beta) {
			generate();
			for (int i = 0; i < iAll; i++) {
				int md = moves[i];
				make(md);
				int score = -deeper.alphabeta(-beta, -alpha);
				if (score > alpha)
					alpha = score; // alpha acts like max in MiniMax
			}
			return alpha;
		}
	}

	class AlphaBeta extends Evaluate {

		@Override
		public void make(int md) {
			super.make(md);
			((Evaluate)deeper).evaluate(md);
		}

		// Alphabeta
		public int alphabeta(int alpha, int beta) {
			generate();
			for (int i = 0; i < iAll; i++) {
				int md = moves[i];
				make(md);
				int score = -deeper.alphabeta(-beta, -alpha);
				if (score >= beta)
					return beta; // fail hard beta-cutoff
				if (score > alpha)
					alpha = score; // alpha acts like max in MiniMax
			}
			return alpha;
		}
	}

	class PVS extends Evaluate {

		int killer1=-1;
		int killer2=-1;
		
		@Override
		public void make(int md) {
			super.make(md);
			((Evaluate)deeper).evaluate(md);
		}

		// PVS
		public int alphabeta(int alfa,int beta) {
			generate();
			sortKillers();
			make(moves[0]);
			int bestscore = -deeper.alphabeta(-beta, -alfa);
			if( bestscore > alfa ) {
				if( bestscore >= beta ){
					setKiller(moves[0]);
					return bestscore;
				}
				alfa = bestscore;
			}
			for (int i = 1; i < iAll; i++) {
				int md = moves[i];
				make(md);
				int score = -deeper.alphabeta(-alfa-1, -alfa);
				if( score > alfa && score < beta ) {
					score = -deeper.alphabeta(-beta, -alfa);
					if( score > alfa ){
						alfa = score;
					}
				}
				if( score > bestscore ) {
					if( score >= beta ){
						setKiller(moves[0]);
						return score;
					}
					bestscore = score;
				}
			}
			return bestscore;
		}

		private void sortKillers() {
//			if(parent instanceof Evaluate){
//				IIterate pp=((Evaluate)parent).parent;
//				if(pp instanceof PVS){
//					sortKiller(((PVS) pp).killer2);
//					sortKiller(((PVS) pp).killer1);
//				}
//			}
//			sortKiller(killer2);
			sortKiller(killer1);
		}

		private void setKiller(int md) {
			if(killer1!=md){
				killer2=killer1;
				killer1=md;
			}
		}
	}

	class LeafGen extends Evaluate {
		final Eval eval;
		public LeafGen(Eval eval) {
			this.eval=eval;
		}
		
		@Override
		public int alphabeta(int alpha, int beta) {
			eval.count++;
			return score();
		}
	}
}
