package norwegiangambit.engine.iterate;

import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.FEN;
import norwegiangambit.engine.fen.Position;

public class MiniMax implements IIterator {

    private IIterator deeper;

    private Position pos;
    
    public MiniMax(IIterator deeper) {
        this.deeper = deeper;
    }

    @Override
    public String toString() {
        return FEN.board2fen(pos) + "\n" + FEN.printMove(pos);
    }

	@Override
	public int black(Position pos, int min, int max) {
		Position[] moves = NodeGen.getLegalMoves64(pos);
		for (int i = 0; i < moves.length; i++) {
			Position next = moves[i];
			int score = deeper.white(next, min, max);
			if (score < max)
				max = score;
		}
		return max;
	}

	@Override
	public int white(Position pos, int min, int max) {
		Position[] moves = NodeGen.getLegalMoves64(pos);
		for (int i = moves.length - 1; i >= 0; i--) {
			Position next = moves[i];
			int score = deeper.black(next, min, max);
			if (score > min)
				min = score;
		}
		return min;
	}
	
	@Override
	public int getQuality() {
		return deeper.getQuality()+1;
	}


}
