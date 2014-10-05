package norwegiangambit.engine.evaluate;



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
			TTEntry ent=getTT();
			if(ent!=null && ent.getDepth()==depth && ent.isExact())
					return ent.score;
			generate();
			if(iAll==0)
				return -20000;  // MATE
			if(ent!=null)
				sortHash(ent.move);
			sortKillers();
			int best = moves[0];
			make(best);
			int bestscore = -deeper.alphabeta(-beta, -alfa);
			if( bestscore > alfa ) {
				if( bestscore >= beta ){
					setKiller(best);
					setTT(best,bestscore,TranspositionTable.T_EXACT);
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
						setTT(md,score,TranspositionTable.T_EXACT);
						return score;
					}
					bestscore = score;
					best=md;
				}
			}
			setTT(best,bestscore,TranspositionTable.T_EXACT);
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

		public TTEntry getTT() {
			long zob = getZobrist();
			long i=zob&TranspositionTable.TTMASK;
			TTEntry ent=TranspositionTable.ALL[(int)i];
			if(ent==null)
				return null;
			if(ent.zobrist!=zob)
				return null;
			if(ent.validate!=bb_bit1)
				return null;
			return ent;
		}

		public void setTT(int md,int score,int type) {
			long zob = getZobrist();
			int i=(int)(zob&TranspositionTable.TTMASK);
			TTEntry ent=TranspositionTable.ALL[i];
			if(ent==null){
				ent = new TTEntry();
				TranspositionTable.ALL[i]=ent;
			}
			ent.setDepth(depth);
			ent.score=score;
			ent.move=md;
			ent.zobrist=zob;
			ent.validate=bb_bit1;
			ent.setType(type);
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
