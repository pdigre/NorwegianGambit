package norwegiangambit.engine.evaluate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movegen.BASE;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IDivide;

public class FastPerft implements IDivide{

	public static boolean useConcurrency = false;
	public static boolean useTransposition = true;
	public static boolean testTransposition = true;
	
	@Override
	public List<Eval> divide(String fen, int levels) {
		StartGame pos = new StartGame(fen);
		NodeGen root = new NodeGen(null,0);
		root.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		root.evaluate();
		root.generate();
		ArrayList<Eval> map=new ArrayList<Eval>();
		long[] count=new long[root.iAll];
		if(levels<4 || !useConcurrency){
			if(levels==1){
		        for (int i = 0; i < root.iAll; i++)
		        	map.add(new Eval(FEN.move2literal(BASE.ALL[root.moves[i]].bitmap),1,0));
		        return map;
			}
		    for (int i = 0; i < root.iAll; i++) {
		    	int md = root.moves[i];
				NodeGen[] movegen = new NodeGen[levels-1];
				int totdepth = movegen.length;
				for (int ply = 0; ply < totdepth; ply++) {
					NodeGen m = ply < totdepth - 1 ? new NodeGen(count,i) : new LeafGen(count,i);
					movegen[ply] = m;
					NodeGen parent = ply>0?movegen[ply - 1]:root;
					m.parent = parent;
					parent.deeper = m;
					m.ply=ply;
					m.depth=totdepth-ply;
				}
				movegen[0].make(md,root.isWhite,root.castling,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
				movegen[0].evaluate(md);
				movegen[0].run();
				map.add(new Eval(FEN.move2literal(BASE.ALL[md].bitmap),(int)count[i],0));
			}
		    return map;
		}
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		CountTask[] tasks = new CountTask[root.iAll];
		for (int i1 = 0; i1 < root.iAll; i1++) {
			int md = root.moves[i1];
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
			map.add(new Eval(FEN.move2literal(BASE.ALL[root.moves[i]].bitmap),(int) count[i],0));
		}
		return map;
	}

	final private class CountTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = -2743566188067414328L;
		NodeGen[] movegen;

		public CountTask(int md,long[] count,int i,int levels,NodeGen root) {
			movegen = new NodeGen[levels-1];
			int totdepth = movegen.length;
			for (int ply = 0; ply < totdepth; ply++) {
				NodeGen m = ply < totdepth - 1 ? new NodeGen(count,i) : new LeafGen(count,i);
				movegen[ply] = m;
				NodeGen parent = ply>0?movegen[ply - 1]:root;
				m.parent = parent;
				parent.deeper = m;
				m.ply=ply;
				m.depth=totdepth-ply;
			}
			movegen[0].make(md,root.isWhite,root.castling,root.wking,root.bking,root.bb_black,root.bb_bit1,root.bb_bit2,root.bb_bit3);
			movegen[0].evaluate(md);
		}

		@Override
		protected Long compute() {
			movegen[0].run();
			return 0L;
		}
	}

	class NodeGen extends Evaluate {
		final long[] count;
		final int inum;
		public NodeGen(long[] count, int inum) {
			this.count=count;
			this.inum=inum;
		}

		public void run() {
			if(useTransposition && depth>0){
				int tt = getTT();
				if(tt!=-1 && !testTransposition){
					long data=TranspositionTable.data[tt];
					if(TranspositionTable.getDepth(data)==depth){
						count[inum]+=TranspositionTable.getCount(data);
						return;
					}
				}
				long t=count[inum];
				generate();
				for (int i = 0; i < iAll; i++) {
					int md = moves[i];
					deeper.make(md,isWhite,castling,wking,bking,bb_black,bb_bit1,bb_bit2,bb_bit3);
					deeper.evaluate(md);
					((NodeGen)deeper).run();
				}
				long cnt = count[inum]-t;
				if(testTransposition){
					if(tt!=-1){ 
						long data=TranspositionTable.data[tt];
						if(TranspositionTable.getDepth(data)==depth){
							long cnt2 = TranspositionTable.getCount(data);
							if(cnt!=cnt2)
								System.out.println("Error in Count:"+cnt2+"/"+cnt+" "+getFen());
						}
					}
				}
				setTT(cnt);
			} else {
				generate();
				for (int i = 0; i < iAll; i++) {
					int md = moves[i];
					deeper.make(md,isWhite,castling,wking,bking,bb_black,bb_bit1,bb_bit2,bb_bit3);
					deeper.evaluate(md);
					((NodeGen)deeper).run();
				}
			}
		}

		public int getTT() {
			long zob = getZobrist();
			int i=(int)zob&TranspositionTable.TTMASK;
			long hash=TranspositionTable.hash[i];
			if(hash!=(zob^bb_bit1))
				return -1;
			long validate=TranspositionTable.validate[i];
			if(validate!=bb_bit1){
				System.out.println("Key collision:"+getFen());
				return -1;
			}
			return i;
		}

		public int setTT(long count) {
			return TranspositionTable.set(getZobrist(),depth,count,bb_bit1);
		}
	}

	class LeafGen extends NodeGen {
		public LeafGen(long[] count, int inum) {
			super(count,inum);
		}

		@Override
		public void run() {
			generate();
			count[inum]+=iAll;
		}
	}
}
