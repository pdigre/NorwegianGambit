package norwegiangambit.engine.iterate;

import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;

public class Quiescence implements IIterator {

	public static long quiesce=0;
	
    @Override
    public String toString() {
        return "Quiescence";
    }

	@Override
	public int white(Position pos, int alpha, int beta) {
		long time1=System.currentTimeMillis();
		int score = qwhite((Position) pos, alpha, beta);
		quiesce+=System.currentTimeMillis()-time1;
		return score;
	}

	@Override
	public int black(Position pos, int alpha, int beta) {
		long time1=System.currentTimeMillis();
		int score = qblack((Position) pos, alpha, beta);
		quiesce+=System.currentTimeMillis()-time1;
		return score;
	}

	private static int qwhite(Position pos, int alpha, int beta) {
		Position[] moves = NodeGen.getQuiescence64(pos);
		if(moves.length==0)
			return pos.getScore();
		for (int i = moves.length - 1; i >= 0; i--) {
			Position next = moves[i];
			int score = qblack(next, alpha, beta);
			if (score >= beta)
				return beta;
			if (score > alpha)
				alpha = score;
		}
		return alpha;
	}
	
	private static int qblack(Position pos, int alpha, int beta) {
		Position[] moves = NodeGen.getQuiescence64(pos);
		if(moves.length==0)
			return pos.getScore();
		for (int i = 0; i < moves.length; i++) {
			Position next = moves[i];
			int score = qwhite(next, alpha, beta);
			if (score <= alpha)
				return alpha;
			if (score < beta)
				beta = score;
		}
		return beta;
	}

	@Override
	public int getQuality() {
		return 0;
	}
}
