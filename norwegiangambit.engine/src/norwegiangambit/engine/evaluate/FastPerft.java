package norwegiangambit.engine.evaluate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IDivide;
import norwegiangambit.util.PSQT_SEF;
import norwegiangambit.util.polyglot.ZobristJLKISS64;

public class FastPerft implements IDivide{

	public static boolean useConcurrency;
	public static boolean useTransposition;
	public static boolean testTransposition = false;
	
	public FastPerft(boolean concurrency,boolean transposition){
		useConcurrency=concurrency;
		useTransposition=transposition;
		MBase.psqt=new PSQT_SEF();
		MBase.zobrist=new ZobristJLKISS64();
	}
	
	@Override
	public List<Eval> divide(String fen, int levels) {
		StartGame pos = new StartGame(fen);
		PerftGen root = new PerftGen(null,0);
		root.set(pos.whiteNext(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3(), pos.getBitmap());
		root.evaluate();
		root.generate();
		ArrayList<Eval> map=new ArrayList<Eval>();
		long[] count=new long[root.iAll];
		if(levels<4 || !useConcurrency){
			if(levels==1){
		        for (int i = 0; i < root.iAll; i++)
		        	map.add(new Eval(FEN.move2literal(MOVEDATA.ALL[root.moves[i]].bitmap),1,0));
		        return map;
			}
		    for (int i = 0; i < root.iAll; i++) {
		    	int md = root.moves[i];
				PerftGen[] movegen = new PerftGen[levels-1];
				int totdepth = movegen.length;
				for (int ply = 0; ply < totdepth; ply++) {
					PerftGen m = ply < totdepth - 1 ? new PerftGen(count,i) : new PerftHorizon(count,i);
					movegen[ply] = m;
					PerftGen parent = ply>0?movegen[ply - 1]:root;
					m.parent = parent;
					parent.deeper = m;
					m.ply=ply;
					m.depth=totdepth-ply;
				}
				movegen[0].make(!root.wNext,root.aMinor,root.aMajor,root.aSlider,root.bOccupied,root.castling,md);
				movegen[0].evaluate(md);
				movegen[0].run();
				map.add(new Eval(FEN.move2literal(MOVEDATA.ALL[md].bitmap),(int)count[i],0));
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
			map.add(new Eval(FEN.move2literal(MOVEDATA.ALL[root.moves[i]].bitmap),(int) count[i],0));
		}
		return map;
	}

	final private class CountTask extends RecursiveTask<Long> {
		private static final long serialVersionUID = -2743566188067414328L;
		PerftGen[] movegen;

		public CountTask(int md,long[] count,int i,int levels,PerftGen root) {
			movegen = new PerftGen[levels-1];
			int totdepth = movegen.length;
			for (int ply = 0; ply < totdepth; ply++) {
				PerftGen m = ply < totdepth - 1 ? new PerftGen(count,i) : new PerftHorizon(count,i);
				movegen[ply] = m;
				PerftGen parent = ply>0?movegen[ply - 1]:root;
				m.parent = parent;
				parent.deeper = m;
				m.ply=ply;
				m.depth=totdepth-ply;
			}
			movegen[0].make(!root.wNext,root.aMinor,root.aMajor,root.aSlider,root.bOccupied,root.castling,md);
			movegen[0].evaluate(md);
		}

		@Override
		protected Long compute() {
			movegen[0].run();
			return 0L;
		}
	}

	class PerftGen extends FastEval {
		final long[] count;
		final int inum;
		public PerftGen(long[] count, int inum) {
			this.count=count;
			this.inum=inum;
		}

		public void run() {
			if(useTransposition){
				if(!testTransposition){
					long data = TranspositionTable.get(getZobrist(),aMinor);
					if(data!=0L){
						if(TranspositionTable.getDepth(data)==depth){
							count[inum]+=TranspositionTable.getCount(data);
							return;
						}
					}
				}
				long t=count[inum];
				generate();
				for (int i = 0; i < iAll; i++) {
					int md = moves[i];
					deeper.make(!wNext,aMinor,aMajor,aSlider,bOccupied,castling,md);
					deeper.evaluate(md);
					((PerftGen)deeper).run();
				}
				long cnt = count[inum]-t;
				if(testTransposition){
					long data = TranspositionTable.get(getZobrist(),aMinor);
					if(data!=0L){
						if(TranspositionTable.getDepth(data)==depth){
							long cnt2 = TranspositionTable.getCount(data);
							if(cnt!=cnt2){
								System.out.println("Error in Count:"+cnt2+"/"+cnt+" "+getFen());
							}
						}
					}
				}
				TranspositionTable.set(getZobrist(),aMinor,depth,cnt);
			} else {
				generate();
				for (int i = 0; i < iAll; i++) {
					int md = moves[i];
					deeper.make(wNext,aMinor,aMajor,aSlider,bOccupied,castling,md);
					deeper.evaluate(md);
					((PerftGen)deeper).run();
				}
			}
		}

	}

	class PerftHorizon extends PerftGen {
		public PerftHorizon(long[] count, int inum) {
			super(count,inum);
		}

		@Override
		public void run() {
			generate();
			count[inum]+=iAll;
		}
	}
}
