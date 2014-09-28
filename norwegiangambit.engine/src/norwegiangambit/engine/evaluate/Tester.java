package norwegiangambit.engine.evaluate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movegen.BASE;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IDivide;

public abstract class Tester implements IDivide{

	public static boolean useConcurrency = true;
	
	@Override
	public List<Eval> divide(String fen, int levels) {
		boolean isConcurrent = levels>2 && useConcurrency;
		StartGame pos = new StartGame(fen);
		Evaluate root = new Evaluate();
		root.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		ArrayList<Eval> map=new ArrayList<Eval>();
		root.generate();
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		CountTask[] tasks = new CountTask[root.iAll];
		for (int i1 = 0; i1 < root.iAll; i1++) {
			int md = root.moves[i1];
        	Eval eval = new Eval(FEN.move2literal(BASE.ALL[md].bitmap),0,0);
        	map.add(eval);
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

	public Evaluate[] init(int md, Evaluate root, int levels, Eval eval) {
		Evaluate[] movegen = new Evaluate[levels];
		for (int i1 = 0; i1 < movegen.length; i1++) {
			Evaluate m = insert(eval, movegen.length, i1);
			movegen[i1] = m;
			Evaluate parent = i1>0?movegen[i1 - 1]:root;
			m.parent = parent;
			parent.deeper = m;
		}
		Evaluate start = movegen[0];
		start.set(root.isWhite,root.bitmap,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
		start.set(BASE.ALL[md]);
		start.evaluate(md);
		return movegen;
	}

	final private class CountTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = -2743566188067414328L;
		Evaluate start;
		Eval eval;

		public CountTask(int md,Eval eval,int levels,Evaluate root) {
			this.eval=eval;
			start=init(md, root, levels,eval )[0];
		}

		@Override
		protected Long compute() {
			eval.value=-start.alphabeta(-20000, 20000);
			return 0L;
		}
	}

	public abstract Evaluate insert(Eval eval, int depth, int level);
}
