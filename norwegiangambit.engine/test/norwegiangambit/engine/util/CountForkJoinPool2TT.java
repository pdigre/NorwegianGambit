package norwegiangambit.engine.util;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;

public class CountForkJoinPool2TT extends CountTT {

    private static final long serialVersionUID = -3058348904963758664L;

    public CountForkJoinPool2TT(Position pos, int depth) {
        super(pos, depth);
    }

    @Override
    public Counter[] compute() {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ArrayList<CountTT> tasks = new ArrayList<CountTT>();
        for (Position next : NodeGen.getLegalMoves64(pos)) {
            count(next);
            if (counters.length > 1) {
            	CountTT task = new CountForkJoinPoolTT(next, counters.length - 1);
                tasks.add(task);
                pool.execute(task);
            }
        }
        for (CountTT task : tasks)
        	PerftResults.total(counters, task.join());
        return counters;
    }

}
