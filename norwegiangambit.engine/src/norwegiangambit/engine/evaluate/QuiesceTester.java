package norwegiangambit.engine.evaluate;



public class QuiesceTester extends AbstractTester{

	public static boolean useTransposition=true;
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
			return TranspositionTable.get(getZobrist(), bb_bit1);
		}

		public void setTT(int md,int score,int type) {
			TranspositionTable.set(getZobrist(),bb_bit1,depth,type,md,score);
		}
	}

	class HorizonGen extends Quiesce {

		final Eval eval;

		public HorizonGen(Eval eval) {
			super(eval);
			this.eval=eval;
			Quiesce[] movegen = new Quiesce[20];
			int totdepth = movegen.length;
			for (int ply = 0; ply < totdepth; ply++) {
				Quiesce m = new Quiesce(eval);
				movegen[ply] = m;
				Evaluate parent = ply>0?movegen[ply - 1]:this;
				m.parent = parent;
				parent.deeper = m;
				m.depth=totdepth-ply;
				m.ply=ply;
				m.testCheckers=ply<6;
			}
		}
		
		@Override
		public int search(int alfa, int beta) {
			eval.count++;
			int score2 = score();
			int quiesce=super.search(alfa, beta);
			if(quiesce>beta)
				return beta;
//			if(quiesce>score2)
//				score2=quiesce;
			int type=TranspositionTable.T_EXACT;
			if(score2<=alfa)
				type=TranspositionTable.T_LE;
			else if(score2>=beta)
				type=TranspositionTable.T_GE;
			TranspositionTable.set(getZobrist(),bb_bit1,depth,type,curr_move,score2);
			return score2;
		}
		
		@Override
		public void setPath(int[] mm) {
		}
	}

	class Quiesce extends Evaluate {
		Eval eval;
		boolean testCheckers=false;
		
		public Quiesce(Eval eval){
			this.eval=eval;
		}
		
		@Override
		public void evaluate(int i) {
			super.evaluate(i);
		}

		@Override
		public void make(int md) {
			super.make(md);
			((Evaluate)deeper).evaluate(md);
		}

		public int search(int alpha, int beta) {
			generate();
			if(iAll==0)
				return checkers==0L?STALE:MATE;  // (STALE)MATE
			if(checkers!=0L){
				for (int i = 0; i < iAll; i++) {
					eval.quiesce++;
					int md = moves[i];
					make(md);
					int score = -deeper.search(-beta, -alpha);
					if (score >= beta)
						return beta; // fail hard beta-cutoff
					if (score > alpha){
						alpha = score; // alpha acts like max in MiniMax
					}
				}
			} else {
				for (int i = 0; i < lvl1; i++) {
					eval.quiesce++;
					int md = moves[i];
					make(md);
					int score = -deeper.search(-beta, -alpha);
					if (score >= beta)
						return beta; // fail hard beta-cutoff
					if (score > alpha){
						alpha = score; // alpha acts like max in MiniMax
					}
				}
				if(testCheckers){
					sortCheckers();
//					if(lvl2>lvl1)
//						System.out.println("Checkers:"+(lvl2-lvl1));
					for (int i = lvl1; i < lvl2; i++) {
						int md = moves[i];
						make(md);
						int score = -deeper.search(-beta, -alpha);
						if (score >= beta)
							return beta; // fail hard beta-cutoff
						if (score > alpha){
							alpha = score; // alpha acts like max in MiniMax
						}
					}
				}
			}
			return alpha;
		}
	}
}
