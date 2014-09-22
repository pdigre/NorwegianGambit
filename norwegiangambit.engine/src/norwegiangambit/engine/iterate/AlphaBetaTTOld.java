package norwegiangambit.engine.iterate;

import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;

public class AlphaBetaTTOld implements IIterator {

	private IIterator deeper;
	private int quality;

	public AlphaBetaTTOld(IIterator deeper) {
		this.deeper = deeper;
		quality = deeper.getQuality() + 1;
	}

	@Override
	public String toString() {
		return "AlphaBetaTT";
	}

	@Override
	public int white(Position pos, int alpha, int beta) {
		Position[] moves = NodeGen.getLegalMoves64(pos);
		for (int i = moves.length - 1; i >= 0; i--) {
			Position next = moves[i];
			Position tt = Transposition.find(next, quality);
			int score=0;
			if(tt!=null){
				score=tt.getScore();
			} else {
				score = deeper.black(next, alpha, beta);
				next.quality=quality;
				next.score=score;
				Transposition.register(next);
			}
			if (score >= beta)
				return beta;
			if (score > alpha)
				alpha = score;
		}
		return alpha;
	}

	@Override
	public int black(Position pos, int alpha, int beta) {
		Position[] moves = NodeGen.getLegalMoves64(pos);
		for (int i = 0; i < moves.length; i++) {
			Position next = moves[i];
			Position tt = Transposition.find(next, quality);
			int score=0;
			if(tt!=null){
				score=tt.getScore();
			} else {
				score = deeper.white(next, alpha, beta);
				next.quality=quality;
				next.score=score;
				Transposition.register(next);
			}
			if (score <= alpha)
				return alpha;
			if (score < beta)
				beta = score;
		}
		return beta;
	}

	@Override
	public int getQuality() {
		return quality;
	}
}
