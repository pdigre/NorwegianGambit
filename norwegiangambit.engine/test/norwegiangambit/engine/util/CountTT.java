package norwegiangambit.engine.util;

import java.util.concurrent.RecursiveTask;

import norwegiangambit.engine.base.KingSafe;
import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public class CountTT extends RecursiveTask<Counter[]> {

	private static final long serialVersionUID = -3058348234963748664L;

	final protected Position pos;

	private PerftResults perft;

	protected final Counter[] counters;

	public CountTT(Position pos, int depth) {
		this.pos = pos;
		counters = new Counter[depth];
		for (int i = 0; i < depth; i++)
			counters[i] = new Counter();
	}

	protected void count(Position pos) {
		CountTT.registerTT(pos);
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
				Counter[] compute = new CountTT(next, depth - 1).compute();
				for (int i1 = 0; i1 < compute.length; i1++) {
					Counter count = compute[i1];
					counters[i1 + 1].add(count);
				}
				perft.rootcount[i] += compute[compute.length - 1].moves;
			}
		}
		perft.counters = counters;
		return perft;
	}

	@Override
	public Counter[] compute() {
		for (Position next : NodeGen.getLegalMoves64(pos)) {
			count(next);
			if (counters.length > 1)
				Counter.total(counters, new CountTT(next, counters.length - 1).compute());
		}
		return counters;
	}

	public static final int TTSIZE = 1 << 24;
	public static final Position[] TT = new Position[TTSIZE];
	public static long hits = 0;
	public static long miss = 0;
	public static long coll = 0;
	public static long err = 0;
	public static final long MASK = TTSIZE - 1;

	private static final void registerTT(Position next) {
		long zobrist = next.getZobristKey();
		long k1 = (zobrist) & MASK;
		long k2 = (zobrist >> 1) & MASK;
		Position pos = TT[(int) k1];
		if (pos == null) {
			miss++;
			TT[(int) k1] = next;
		} else {
			if (pos.getZobristKey() == zobrist) {
				hits++;
				if (pos.getBKpos() != next.getBKpos())
					err++;
			} else {
				Position pos2 = TT[(int) k2];
				if (pos2 == null) {
					miss++;
					TT[(int) k2] = TT[(int) k1];
					TT[(int) k1] = next;
				} else {
					if (pos2.getZobristKey() == zobrist) {
						hits++;
						if (pos2.getBKpos() != next.getBKpos())
							err++;
					} else {
						coll++;
						TT[(int) k2] = TT[(int) k1];
						TT[(int) k1] = next;
					}
				}
			}
		}
	}

}
