package norwegiangambit.engine.evaluate;


public class EvalTester extends AbstractTester{

	@Override
	public Evaluate insert(RootEval eval, int depth, int level) {
		if(level == depth - 1)
			return new HorizonGen(eval);
		return new AlphaBeta();
//		return new PVS();
//		return depth-level>4 ? new PVS():new AlphaBeta();
	}


	class MiniMax extends Evaluate {

		@Override
		public void make(int md) {
			super.make(md);
			((Evaluate)deeper).evaluate(md);
		}

		// Minimax
		public int search(int alpha, int beta) {
			generate();
			if(iAll==0)
				return checkers==0L?STALE:MATE;  // (STALE)MATE
			for (int i = 0; i < iAll; i++) {
				int md = moves[i];
				make(md);
				int score = -deeper.search(-beta, -alpha);
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
		public int search(int alpha, int beta) {
			generate();
			if(iAll==0)
				return checkers==0L?STALE:MATE;  // (STALE)MATE
			for (int i = 0; i < iAll; i++) {
				int md = moves[i];
				make(md);
				int score = -deeper.search(-beta, -alpha);
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
		public int search(int alfa,int beta) {
			generate();
			if(iAll==0)
				return checkers==0L?STALE:MATE;  // (STALE)MATE
			sortKillers();
			int md0 = moves[0];
			make(md0);
			int bestscore = -deeper.search(-beta, -alfa);
			if( bestscore > alfa ) {
				if( bestscore >= beta ){
					setKiller(md0);
					return bestscore;
				}
				alfa = bestscore;
				setBest(md0, alfa);
			}
			for (int i = 1; i < iAll; i++) {
				int md = moves[i];
				make(md);
				int score = -deeper.search(-alfa-1, -alfa);
				if( score > alfa && score < beta ) {
					score = -deeper.search(-beta, -alfa);
					if( score > alfa ){
						alfa = score;
						setBest(md, alfa);
					}
				}
				if( score > bestscore ) {
					if( score >= beta ){
						setKiller(md);
						return score;
					}
					bestscore = score;
				}
			}
			return bestscore;
		}

		private void sortKillers() {
			if(parent instanceof Evaluate){
				Evaluate pp=parent.parent;
				if(pp instanceof PVS){
					sortKiller(((PVS) pp).killer2);
					sortKiller(((PVS) pp).killer1);
				}
			}
			sortKiller(killer2);
			sortKiller(killer1);
		}

		private void setKiller(int md) {
			if(killer1!=md){
				killer2=killer1;
				killer1=md;
			}
		}
	}

	class HorizonGen extends Evaluate {
		final RootEval eval;
		public HorizonGen(RootEval eval) {
			this.eval=eval;
		}
		
		@Override
		public int search(int alpha, int beta) {
			eval.count++;
			return score();
		}
		
		@Override
		public void notifyPV(Evaluate child, int depth, boolean lowerBound, boolean upperBound, int score) {
			parent.notifyPV(this, ply, lowerBound, upperBound, score);
		}
	}

}
