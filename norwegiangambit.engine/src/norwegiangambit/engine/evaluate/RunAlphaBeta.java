package norwegiangambit.engine.evaluate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.base.MOVEDATA;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.FEN;

public class RunAlphaBeta implements IThinker{

	public static boolean useConcurrency = false;
	
	
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
		StartGame pos = new StartGame(fen);
		NodeGen root = new NodeGen();
		root.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		LinkedHashMap<String, Eval> map=new LinkedHashMap<String, Eval>();
		root.generate();
		long[] count=new long[root.iAll];
		if(levels<4 || !useConcurrency){
			if(levels==1){
		        for (int i = 0; i < root.iAll; i++) {
			    	MOVEDATA md = root.moves[i];
		        	Eval eval = new Eval(1,0);
					NodeGen m = new LeafGen(eval);
					m.parent = root;
					m.set(root.isWhite,root.bitmap,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
					m.set(md);
					m.evaluate(md);
		        	eval.value=m.whiteScore();
					map.put(FEN.move2literal(root.moves[i].bitmap),eval);
				}
		        return map;
			}
		    for (int i = 0; i < root.iAll; i++) {
		    	MOVEDATA md = root.moves[i];
				NodeGen[] movegen = new NodeGen[levels];
	        	Eval eval = new Eval(0,0);
				for (int i1 = 0; i1 < movegen.length; i1++) {
					NodeGen m = i1 < movegen.length - 1 ? new NodeGen() : new LeafGen(eval);
					movegen[i1] = m;
					NodeGen parent = i1>0?movegen[i1 - 1]:root;
					m.parent = parent;
					parent.child = m;
				}
				movegen[0].set(root.isWhite,root.bitmap,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
				movegen[0].set(md);
				movegen[0].evaluate(md);
				eval.value=-movegen[0].alphabeta(-20000, +20000);
				map.put(FEN.move2literal(md.bitmap),eval);
			}
		    return map;
		}
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		CountTask[] tasks = new CountTask[root.iAll];
		for (int i1 = 0; i1 < root.iAll; i1++) {
			MOVEDATA md = root.moves[i1];
        	Eval eval = new Eval(0,0);
			map.put(FEN.move2literal(root.moves[i1].bitmap),eval);
			CountTask task=new CountTask(md,eval,levels,root);
			tasks[i1]=task;
			pool.execute(task);
		}
		for (int i = 0; i < tasks.length; i++){
		    try {
		    	tasks[i].get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	final private class CountTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = -2743566188067414328L;
		NodeGen[] movegen;
		Eval eval;

		public CountTask(MOVEDATA md,Eval eval,int levels,NodeGen root) {
			this.eval=eval;
			movegen = new NodeGen[levels-1];
			for (int i1 = 0; i1 < movegen.length; i1++) {
				NodeGen m = i1 < movegen.length - 1 ? new NodeGen() : new LeafGen(eval);
				movegen[i1] = m;
				NodeGen parent = i1>0?movegen[i1 - 1]:root;
				m.parent = parent;
				parent.child = m;
			}
			movegen[0].set(root.isWhite,root.bitmap,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
			movegen[0].set(md);
			movegen[0].evaluate(md);
		}

		@Override
		protected Long compute() {
			eval.value=movegen[0].alphabeta(-20000, 20000);
			return 0L;
		}
	}

	class NodeGen extends AlphaBeta {

		@Override
		public void make(MOVEDATA md) {
			super.make(md);
			((NodeGen)child).evaluate(md);
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
