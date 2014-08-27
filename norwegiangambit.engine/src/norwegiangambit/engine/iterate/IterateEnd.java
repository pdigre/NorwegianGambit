package norwegiangambit.engine.iterate;

import norwegiangambit.engine.fen.Position;

public class IterateEnd implements IIterator {

    @Override
    public final int black(Position pos, int alpha, int beta) {
        return pos.getScore();
    }

    @Override
    public final int white(Position pos, int alpha, int beta) {
        return pos.getScore();
    }

    @Override
	public int getQuality() {
		return 0;
	}
}
