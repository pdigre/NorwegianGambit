package norwegiangambit.engine.evaluate;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.util.FEN;

public class EloTester extends EvalTesterTT {

	private CountTask[] tasks;
	private RootEval[] evals;
	private int[] bestpath;
	int bscore=-32000;
	private Evaluate rooteval;
	
	public final class RootGen extends PVS {
	
		public int bestscore=0;
		RootEval eval;
		
		@Override
		public int search(int alfa, int beta) {
			if (isRunning) {
				bestscore = - super.search(alfa, beta);
				if(bestscore>bscore){
					bscore=bestscore;
					bestpath=eval.path;
					setPath(bestpath);
					listener.run();
				}
				return bestscore;
			}
			return 0;
		}
	}

	@Override
	public Evaluate insert(RootEval eval, int depth, int ply) {
		if (ply == 0)
			return new RootGen();
		return super.insert(eval, depth, ply);
	}

	@Override
	public void start(String fen) {
		super.start(fen);
		StartGame pos = new StartGame(fen);
		rooteval = new Evaluate();
		rooteval.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(),
				pos.get64bit3());
		rooteval.generate();
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		evals = new RootEval[rooteval.iAll];
		tasks = new CountTask[evals.length];
		for (int i = 0; i < evals.length; i++) {
			evals[i]=new RootEval(FEN.move2literal(MBase.ALL[rooteval.moves[i]].bitmap), 0, 0);
		}
		int depth=6;
		while(isRunning){
			iterate(pool,depth++);
		}
	}

	public void iterate(ForkJoinPool pool,int depth) {
		bscore=-32000;
		System.out.println("Depth="+depth);
		for (int i1 = 0; i1 < evals.length; i1++) {
			createTask(i1,depth);
		}
		CountTask[] clone = tasks.clone();
		Arrays.sort(clone);
		for (CountTask task : clone) {
			pool.execute(task);
		}
		for (CountTask task : clone) {
			try {
				task.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void createTask(int i, int levels) {
		RootEval eval = evals[i];
		CountTask task = new CountTask(rooteval.moves[i], eval, levels, rooteval);
		((RootGen)task.start).eval=eval;
		tasks[i] = task;
	}
	
	@Override
	public String bestPath() {
		return bscore+"   "+RootEval.pv2string(bestpath);
	}
}
