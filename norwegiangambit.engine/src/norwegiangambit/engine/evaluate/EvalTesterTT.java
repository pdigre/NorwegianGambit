package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.evaluate.EvalTester.PVS;
import norwegiangambit.engine.evaluate.TranspositionTable.TTEntry;


public class EvalTesterTT extends Tester{

	@Override
	public Evaluate insert(Eval eval, int depth, int ply) {
		if(ply == depth - 1)
			return new LeafGen(eval);
		return new PVS();
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
			if(iAll==0)
				return -20000;  // MATE
			TTEntry ent=getTT();
			sortKillers();
			int md0 = moves[0];
			make(md0);
			int bestscore = -deeper.alphabeta(-beta, -alfa);
			if( bestscore > alfa ) {
				if( bestscore >= beta ){
					setKiller(md0);
					setTT(md0);
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
						setKiller(md);
						setTT(md);
						return score;
					}
					bestscore = score;
				}
			}
			return bestscore;
		}

		private void sortKillers() {
			if(parent instanceof Evaluate){
				IIterate pp=((Evaluate)parent).parent;
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

		private TTEntry getTT() {
			return null;
		}

		public void setTT(int i) {
			
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
