package norwegiangambit.engine.evaluate;



public class EvalTesterTT extends Tester{

	public static boolean useTransposition=true;
	@Override
	public Evaluate insert(RootEval eval, int depth, int ply) {
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
			if(useTransposition){
				int tt = getTT();
//				if(tt!=-1){
//					long data=TranspositionTable.data[tt];
//					if(TranspositionTable.getDepth(data)==depth && TranspositionTable.isExact(data)){
//						return TranspositionTable.getScore(data);
//					}
//				}
				generate();
				if(iAll==0)
					return -20000;  // MATE
				if(tt!=-1){
					long data=TranspositionTable.data[tt];
					sortHash(TranspositionTable.getMove(data));
				}
			} else {
				generate();
				if(iAll==0)
					return -20000;  // MATE
			}
			sortKillers();
			int md0 = moves[0];
			make(md0);
			int bestscore = -deeper.alphabeta(-beta, -alfa);
			if( bestscore > alfa ) {
				if( bestscore >= beta ){
					setKiller(md0);
					setTT(md0,bestscore,TTEntry.T_EXACT);
					return bestscore;
				}
				alfa = bestscore;
				best_move=md0;
			}
			for (int i = 1; i < iAll; i++) {
				int md = moves[i];
				make(md);
				int score = -deeper.alphabeta(-alfa-1, -alfa);
				if( score > alfa && score < beta ) {
					score = -deeper.alphabeta(-beta, -alfa);
					if( score > alfa ){
						alfa = score;
						best_move=md;
					}
				}
				if( score > bestscore ) {
					if( score >= beta ){
						setKiller(md);
						setTT(md,score,TTEntry.T_EXACT);
						return score;
					}
					bestscore = score;
				}
			}
			setTT(md0,bestscore,TTEntry.T_EXACT);
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

		public int getTT() {
			return TranspositionTable.get(getZobrist(), bb_bit1);
		}

		public void setTT(int md,int score,int type) {
			TranspositionTable.set(getZobrist(),bb_bit1,depth,type,md,score);
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
