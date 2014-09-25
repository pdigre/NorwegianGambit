package norwegiangambit.engine.evaluate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.base.MOVEDATA;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.FEN;

public class RunAlphaBeta implements IThinker{

	public static boolean useConcurrency = true;
	
	
	public class Eval{
		int count=0;
		int value=0;

		public Eval(int count, int value) {
			this.count = count;
			this.value = value;
		}
	}
	
	@Override
	public Map<String, Eval> evaluate(String fen, int levels) {
		boolean isConcurrent = levels>3 && !useConcurrency;
		StartGame pos = new StartGame(fen);
		NodeGen root = new NodeGen();
		root.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		LinkedHashMap<String, Eval> map=new LinkedHashMap<String, Eval>();
		root.generate();
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		CountTask[] tasks = new CountTask[root.iAll];
		for (int i1 = 0; i1 < root.iAll; i1++) {
			MOVEDATA md = root.moves[i1];
        	Eval eval = new Eval(0,0);
			map.put(FEN.move2literal(md.bitmap),eval);
			CountTask task=new CountTask(md,eval,levels,root);
			tasks[i1]=task;
			if(isConcurrent)
				pool.execute(task);
			else
				task.compute();
		}
		if(isConcurrent){
			for (int i = 0; i < tasks.length; i++){
			    try {
			    	tasks[i].get();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	public NodeGen[] init(MOVEDATA md, NodeGen root, int levels, Eval eval) {
		NodeGen[] movegen = new NodeGen[levels];
		for (int i1 = 0; i1 < movegen.length; i1++) {
			NodeGen m = i1 < movegen.length - 1 ? new NodeGen() : new LeafGen(eval);
			movegen[i1] = m;
			NodeGen parent = i1>0?movegen[i1 - 1]:root;
			m.parent = parent;
			parent.next = m;
		}
		NodeGen start = movegen[0];
		start.set(root.isWhite,root.bitmap,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
		start.set(md);
		start.evaluate(md);
		return movegen;
	}


	final private class CountTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = -2743566188067414328L;
		NodeGen start;
		Eval eval;

		public CountTask(MOVEDATA md,Eval eval,int levels,NodeGen root) {
			this.eval=eval;
			start=init(md, root, levels,eval )[0];
		}

		@Override
		protected Long compute() {
			eval.value=-start.alphabeta(-20000, 20000);
			return 0L;
		}
	}

	class NodeGen extends AlphaBeta {

		@Override
		public void make(MOVEDATA md) {
			super.make(md);
			((NodeGen)next).evaluate(md);
		}
		
	}

	class LeafGen extends NodeGen {
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
