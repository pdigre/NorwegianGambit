package norwegiangambit.engine.util;

import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.base.KingSafe;
import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public class CountFull extends RecursiveTask<Counter[]> {

    private static final long serialVersionUID = -3058348234963748664L;

    final protected Position pos;

	private PerftResults perft;

	protected final Counter[] counters;

    public CountFull(int depth, Position pos) {
        this.pos = pos;
		counters=new Counter[depth];
        for (int i = 0; i < depth; i++)
            counters[i] = new Counter();
    }

    protected void count(Position pos) {
        counters[0].moves++;
        long bitmap = pos.getBitmap();
        if (BITS.isCapture(bitmap)) {
            counters[0].captures++;
            if (BITS.isEnpassant(bitmap))
                counters[0].enpassants++;
        } else {
            if (BITS.isCastling(bitmap))
                counters[0].castlings++;
        }
        if (BITS.isPromotion(bitmap))
            counters[0].promotions++;
        switch (KingSafe.getCheckState(pos)) {
            case IConst.CHECK:
                counters[0].checks++;
                break;
            case IConst.MATE:
                counters[0].checks++;
                counters[0].mates++;
                break;
        }
    }

    public PerftResults perft() {
    	Position[] mvs = NodeGen.getLegalMoves64(pos);
    	perft = new PerftResults(mvs);
        int depth = counters.length;
        for (int i = 0; i < mvs.length; i++) {
			Position next = mvs[i];
            count(next);
			if (depth > 1) {
				Counter[] compute = new CountFull(depth - 1, next).compute();
				for (int i1 = 0; i1 < compute.length; i1++) {
					Counter count = compute[i1];
					counters[i1+1].add(count);
				}
				perft.rootcount[i]+=compute[compute.length-1].moves;
			}
        }
    	perft.counters=counters;
        return perft;
    }

    @Override
    public Counter[] compute() {
        for (Position next : NodeGen.getLegalMoves64(pos)) {
            count(next);
            if (counters.length > 1)
                Counter.total(counters, new CountFull(counters.length - 1, next).compute());
        }
        return counters;
    }

}
