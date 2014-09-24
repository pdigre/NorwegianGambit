package norwegiangambit.engine.evaluate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.base.MOVEDATA;
import norwegiangambit.engine.base.Movegen;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IDivide;

public class RunPerftFast implements IDivide{

	public static boolean useConcurrency = true;
	
	@Override
	public Map<String, Integer> divide(String fen, int levels) {
		StartGame pos = new StartGame(fen);
		NodeGen root = new NodeGen();
		root.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		LinkedHashMap<String, Integer> map=new LinkedHashMap<String, Integer>();
		root.generate();
		long[] count=new long[root.iAll];
		if(levels<4 || !useConcurrency){
			if(levels==1){
		        for (int i = 0; i < root.iAll; i++)
		        	map.put(FEN.move2literal(root.moves[i].bitmap),1);
		        return map;
			}
		    for (int i = 0; i < root.iAll; i++) {
		    	MOVEDATA md = root.moves[i];
				NodeGen[] movegen = new NodeGen[levels-1];
				for (int i1 = 0; i1 < movegen.length; i1++) {
					NodeGen m = i1 < movegen.length - 1 ? new NodeGen() : new LeafGen(count,i);
					movegen[i1] = m;
					NodeGen parent = i1>0?movegen[i1 - 1]:root;
					m.parent = parent;
					parent.child = m;
				}
				movegen[0].set(root.isWhite,root.bitmap,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
				movegen[0].set(md);;
				movegen[0].run();
				map.put(FEN.move2literal(md.bitmap),(int)count[i]);
			}
		    return map;
		}
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		CountTask[] tasks = new CountTask[root.iAll];
		for (int i1 = 0; i1 < root.iAll; i1++) {
			MOVEDATA md = root.moves[i1];
			CountTask task=new CountTask(md,count,i1,levels,root);
			tasks[i1]=task;
			pool.execute(task);
		}
		for (int i = 0; i < tasks.length; i++){
		    try {
		    	tasks[i].get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			map.put(FEN.move2literal(root.moves[i].bitmap),(int) count[i]);
		}
		return map;
	}

	final private class CountTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = -2743566188067414328L;
		NodeGen[] movegen;

		public CountTask(MOVEDATA md,long[] count,int i,int levels,NodeGen root) {
			movegen = new NodeGen[levels-1];
			for (int i1 = 0; i1 < movegen.length; i1++) {
				NodeGen m = i1 < movegen.length - 1 ? new NodeGen() : new LeafGen(count,i);
				movegen[i1] = m;
				NodeGen parent = i1>0?movegen[i1 - 1]:root;
				m.parent = parent;
				parent.child = m;
			}
			movegen[0].set(root.isWhite,root.bitmap,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
			movegen[0].set(md);
		}

		@Override
		protected Long compute() {
			movegen[0].run();
			return 0L;
		}
	}

	class NodeGen extends Movegen {
		NodeGen parent = null, child = null;

		public void run() {
			generate();
			for (int i = 0; i < iAll; i++) {
				MOVEDATA md = moves[i];
				child.set(isWhite,bitmap,wking,bking,bb_black,bb_bit1,bb_bit2,bb_bit3);
				child.set(md);
				child.run();
			}
		}
	}

	class LeafGen extends NodeGen {
		final long[] count;
		final int inum;
		public LeafGen(long[] count, int inum) {
			this.count=count;
			this.inum=inum;
		}

		@Override
		public void run() {
			generate();
			count[inum]+=iAll;
		}
	}
}
