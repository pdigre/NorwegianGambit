package norwegiangambit.engine.iterate;

import norwegiangambit.engine.fen.Position;

public interface IIterator{

    final static int MAX = 32000;

    final static int MIN = -MAX;

    /**
     * MIN - Find best black score
     * 
     * @param pos
     * @param min = Alpha for AlphaBeta
     * @param max = Beta  for AlphaBeta
     * @return
     */
    int black(Position pos, int min, int max);

    /**
     * MAX - Find best white score
     * 
     * @param pos
     * @param min = Alpha for AlphaBeta
     * @param max = Beta for AlphaBeta
     * @return
     */
    int white(Position pos, int min, int max);

    int getQuality();
}
