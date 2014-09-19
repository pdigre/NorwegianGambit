package norwegiangambit.engine;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.base.MOVEDATA;
import norwegiangambit.engine.base.Movegen;
import norwegiangambit.util.FEN;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.IDivide;

public class RunPerftFast implements IDivide{
	int levels;
	long count;
	private Position pos;
	public NodeGen root;
	

	@Override
	public Map<String, Integer> divide(String fen, int depth) {
		start(depth, new StartGame(fen));
		return divide();
	}

	public void start(int levels, Position pos) {
		this.levels = levels;
		this.pos = pos;
		root = new NodeGen();
		root.setPos(pos);
	}

	public long perft() {
        root.generate();
		long[] count=new long[root.iAll];
		if(levels<4){
			long total=0L;
	        for (int i = 0; i < root.iAll; i++) {
	        	MOVEDATA md = root.moves[i];
    			runSimple(count, i, pos.move(md));
    			total+=count[i];
			}
	        return total;
		}
		CountTask[] tasks = concurrent(count);
        try {
			for (RecursiveTask<Long> task : tasks)
				task.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
        long total=0;
        for (int i = 0; i < count.length; i++)
			total+=count[i];
        return total;
	}
	
	public Map<String,Integer> divide() {
		LinkedHashMap<String, Integer> map=new LinkedHashMap<String, Integer>();
        root.generate();
		long[] count=new long[root.iAll];
		if(levels<4){
			if(levels==1){
		        for (int i = 0; i < root.iAll; i++) {
		        	MOVEDATA md = root.moves[i];
		    		map.put(FEN.move2literal(md.bitmap),1);
				}
		        return map;
			}
	        for (int i = 0; i < root.iAll; i++) {
	        	MOVEDATA md = root.moves[i];
    			runSimple(count, i, pos.move(md));
	    		map.put(FEN.move2literal(md.bitmap),(int)count[i]);
			}
	        return map;
		}
		CountTask[] tasks = concurrent(count);
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

	public void runSimple(long[] count, int i, Position p) {
		NodeGen[] movegen = new NodeGen[levels-1];
		assign(p,movegen,levels,count,i);
		movegen[0].run();
	}

	public CountTask[] concurrent(long[] count) {
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        CountTask[] tasks = new CountTask[root.iAll];
		for (int i = 0; i < root.iAll; i++) {
			Position p = pos.move(root.moves[i]);
			CountTask task=new CountTask(p,count,i);
			tasks[i]=task;
			pool.execute(task);
		}
		return tasks;
	}

	private final class CountTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = -2743566188067414328L;
		NodeGen[] movegen;

		public CountTask(Position pos,long[] count,int i) {
			movegen = new NodeGen[levels-1];
			assign(pos,movegen,levels,count,i);
		}

		@Override
		protected Long compute() {
			movegen[0].run();
			return count;
		}
	}

	public void assign(Position pos,NodeGen[] movegen,int levels,long[] count,int inum) {
		for (int i = 0; i < movegen.length; i++) {
			NodeGen m = i < movegen.length - 1 ? new NodeGen() : new LeafGen(count,inum);
			movegen[i] = m;
			NodeGen parent = i>0?movegen[i - 1]:root;
			m.parent = parent;
			parent.child = m;
		}
		movegen[0].setPos(pos);
	}

	public class NodeGen extends Movegen {
		NodeGen parent = null, child = null;

		public void run() {
			generate();
			for (int i = 0; i < iAll; i++) {
				MOVEDATA md = moves[i];
				child.make(md);
				child.run();
			}
		}
	}

	public class LeafGen extends NodeGen {
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
