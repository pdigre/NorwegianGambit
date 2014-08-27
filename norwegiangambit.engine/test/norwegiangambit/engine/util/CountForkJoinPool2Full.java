package norwegiangambit.engine.util;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;

public class CountForkJoinPool2Full extends CountFull {

    private static final long serialVersionUID = -3058348904963758664L;

    public CountForkJoinPool2Full(Position pos, int depth) {
        super(depth, pos);
    }

    @Override
    public Counter[] compute() {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ArrayList<CountFull> tasks = new ArrayList<CountFull>();
        for (Position next : NodeGen.getLegalMoves64(pos)) {
            count(next);
            if (counters.length > 1) {
                CountFull task = new CountForkJoinPoolFull(next, counters.length - 1);
                tasks.add(task);
                pool.execute(task);
            }
        }
        for (CountFull task : tasks)
        	PerftResults.total(counters, task.join());
        return counters;
    }

}
