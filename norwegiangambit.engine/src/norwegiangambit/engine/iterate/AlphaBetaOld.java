package norwegiangambit.engine.iterate;

import norwegiangambit.engine.fen.FEN_POS;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.FEN;

public class AlphaBetaOld implements IIterator {

	private IIterator deeper;
	private Position pos;

	public AlphaBetaOld(IIterator deeper) {
		this.deeper = deeper;
	}

	@Override
	public String toString() {
		return FEN.board2fen(pos.getBoard()) + "\n" + FEN_POS.notation(pos.getFen());
	}

	@Override
	public int white(Position pos, int alpha, int beta) {
		Position[] moves = NodeGen.getLegalMoves64(pos);
		for (int i = moves.length - 1; i >= 0; i--) {
			Position n = moves[i];
			int score = deeper.black(n, alpha, beta);
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
			Position n = moves[i];
			int score = deeper.white(n, alpha, beta);
			if (score <= alpha)
				return alpha;
			if (score < beta)
				beta = score;
		}
		return beta;
	}
	
	@Override
	public int getQuality() {
		return deeper.getQuality()+1;
	}

}
