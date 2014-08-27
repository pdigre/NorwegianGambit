package norwegiangambit.engine.util;

import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;

public class CountSimple extends RecursiveTask<int[]> {

    private static final long serialVersionUID = -3058348234963748664L;

    final protected Position pos;

	protected final int[] counters;

    public CountSimple(Position pos, int depth) {
        this.pos = pos;
		counters=new int[depth];
    }

    protected void count(Position pos) {
        counters[0]++;
    }

    @Override
    public int[] compute() {
        for (Position next : NodeGen.getLegalMoves64(pos)) {
            count(next);
            if (counters.length > 1){
            	int[] add=new CountSimple(next, counters.length - 1).compute();
                for (int i = 0; i < add.length; i++)
                	counters[i+1]+=add[i];
            }
        }
        return counters;
    }

}
