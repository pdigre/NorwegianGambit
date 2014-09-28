package norwegiangambit.engine.evaluate;

import norwegiangambit.util.IDivide;

public class PerftTesterSlow extends Tester implements IDivide{

	@Override
	public Evaluate insert(Eval eval, int depth, int level) {
		boolean isLeaf = level == depth - 1;
		return isLeaf ? new LeafGen(eval):new NodeGen();
	}

	class NodeGen extends Evaluate {

		@Override
		public void evaluate(int md) {
			//
		}

		@Override
		public int alphabeta(int alpha, int beta) {
			generate();
			for (int i = 0; i < iAll; i++) {
				int md = moves[i];
				make(md);
				deeper.alphabeta(alpha, beta);
			}
			return 0;
		}
	}

	class LeafGen extends NodeGen {
		final Eval eval;
		public LeafGen(Eval eval) {
			this.eval=eval;
		}
		
		@Override
		public int alphabeta(int alpha, int beta) {
			generate();
			eval.count+=iAll;
			return 0;
		}
	}

}
