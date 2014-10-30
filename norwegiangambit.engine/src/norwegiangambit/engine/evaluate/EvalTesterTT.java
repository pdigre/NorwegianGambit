package norwegiangambit.engine.evaluate;

import norwegiangambit.util.PSQT;



public class EvalTesterTT extends AbstractTester{

	public static boolean useTransposition=true;

	public EvalTesterTT(boolean concurrent,boolean transposition) {
		super(concurrent);
		useTransposition=transposition;
	}

	public EvalTesterTT(boolean concurrent,boolean transposition,PSQT psqt) {
		super(concurrent,psqt);
		useTransposition=transposition;
	}

	@Override
	public Evaluate insert(RootEval eval, int depth, int ply) {
		if(ply == depth - 1)
			return new HorizonGen(eval);
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
		public int search(int alfa,int beta) {
			if(useTransposition){
				int tt = getTT();
				if(tt!=-1){
					long data=TranspositionTable.data[tt];
					if(TranspositionTable.getDepth(data)==depth){
						int score = TranspositionTable.getScore(data);
						if((data&TranspositionTable.T_EXACT)==TranspositionTable.T_EXACT){
							return score;
						} else if((data&TranspositionTable.T_LE)==TranspositionTable.T_LE)
							if(score<=alfa)
								return score;
						if((data&TranspositionTable.T_GE)==TranspositionTable.T_GE){
							if(score>=beta){
								setKiller(TranspositionTable.getMove(data));
								return score;
							}
						}
					}
				}
				generate();
				if(iAll==0)
					return checkers==0L?STALE:MATE;  // (STALE)MATE
				if(tt!=-1){
					long data=TranspositionTable.data[tt];
					sortHash(TranspositionTable.getMove(data));
				}
			} else {
				generate();
				if(iAll==0)
					return checkers==0L?STALE:MATE;  // (STALE)MATE
			}
			sortKillers();
			int md0 = moves[0];
			make(md0);
			int bestscore = -deeper.search(-beta, -alfa);
			if( bestscore > alfa ) {
				if( bestscore >= beta ){
					setKiller(md0);
					setTT(md0,bestscore,TTEntry.T_GE);
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
						setTT(md,score,TTEntry.T_GE);
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

		public int getTT() {
			return TranspositionTable.get(getZobrist(), aMinor);
		}

		public void setTT(int md,int score,int type) {
			TranspositionTable.set(getZobrist(),aMinor,depth,type,md,score);
		}
	}

	class HorizonGen extends Evaluate {
		final Eval eval;
		public HorizonGen(Eval eval) {
			this.eval=eval;
		}
		
		@Override
		public int search(int alpha, int beta) {
			eval.count++;
			int score2 = score();
			int type=TranspositionTable.T_EXACT;
			if(score2<=alpha)
				type=TranspositionTable.T_LE;
			else if(score2>=beta)
				type=TranspositionTable.T_GE;
			TranspositionTable.set(getZobrist(),aMinor,depth,type,curr_move,score2);
			return score2;
		}
	}

}
