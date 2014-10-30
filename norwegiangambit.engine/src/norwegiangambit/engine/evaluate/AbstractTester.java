package norwegiangambit.engine.evaluate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.evaluate.EloTester.RootGen;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IDivide;
import norwegiangambit.util.PSQT;
import norwegiangambit.util.PSQT_SEF;

public abstract class AbstractTester implements IDivide, IThinker{

	public static boolean useConcurrency;

	public AbstractTester(boolean concurrent){
		useConcurrency=concurrent;
		MBase.psqt=new PSQT_SEF();
	}
	
	public AbstractTester(boolean concurrent,PSQT psqt){
		useConcurrency=concurrent;
		MBase.psqt=psqt;
	}
	
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
        	RootEval eval = new RootEval(FEN.move2literal(MBase.ALL[md].bitmap),0,0);
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

	public Evaluate[] init(int md, Evaluate root, int levels, RootEval eval) {
		Evaluate[] movegen = new Evaluate[levels];
		int totdepth = movegen.length;
		for (int ply = 0; ply < totdepth; ply++) {
			Evaluate m = insert(eval, totdepth, ply);
			movegen[ply] = m;
			Evaluate parent = ply>0?movegen[ply - 1]:root;
			m.parent = parent;
			parent.deeper = m;
			m.depth=totdepth-ply;
			m.ply=ply;
		}
		Evaluate start = movegen[0];
		start.make(md,root.wNext,root.castling,root.wkingpos,root.bkingpos,root.aMinor,root.aMajor,root.aSlider,root.bOccupied);
		start.evaluate(md);
		return movegen;
	}

	final class CountTask extends RecursiveTask<Long> implements Comparable<CountTask>{
		private static final long serialVersionUID = -2743566188067414328L;
		Evaluate start;
		Eval eval;
		int[] path;

		public CountTask(int md,RootEval eval,int levels,Evaluate root) {
			this.eval=eval;
			path=new int[levels];
			((RootEval)eval).path=path;
			path[0]=md;
			start=init(md, root, levels,eval )[0];
		}

		@Override
		protected Long compute() {
			eval.value=-start.search(-20000, 20000);
			start.setPath(path);
			return 0L;
		}

		@Override
		public int compareTo(CountTask other) {
			if(start instanceof RootGen){
				RootGen start1 = (RootGen) start;
				RootGen start2 = (RootGen) other.start;
				return Integer.compare(start1.bestscore, start2.bestscore);
			}
			return 0;
		}
	}

	public abstract Evaluate insert(RootEval eval, int depth, int level);
	

	
	protected boolean isRunning=false;

	protected Runnable listener=new Runnable() {
		
		@Override
		public void run() {
			//
		}
	};
	
	@Override
	public void start(String fen) {
		this.isRunning=true;
	}

	@Override
	public void stop() {
		this.isRunning=false;
	}

	@Override
	public String bestPath() {
		return null;
	}

	@Override
	public void foundBetter(Runnable runnable) {
		this.listener=runnable;
	}
}
