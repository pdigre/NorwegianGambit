package norwegiangambit.engine.util;

import java.util.Comparator;
import java.util.TreeSet;

import norwegiangambit.engine.base.NodeGen;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.engine.fen.PositionScore;
import norwegiangambit.engine.iterate.IEvaluator;

public final class IterateScores extends TreeSet<Position> {

    public static class Ascending implements Comparator<Position> {

        @Override
        public int compare(Position in, Position other) {
            int r1 = in.getQuality();
            int r2 = other.getQuality();
            if (r1 > r2)
                return -1;
            if (r1 < r2)
                return 1;
            int s1 = in.getScore();
            int s2 = other.getScore();
            if (s1 > s2)
                return -1;
            if (s1 < s2)
                return 1;
            return Long.compare(in.getBitmap(), other.getBitmap());
        }
    }

    public static class Descending implements Comparator<Position> {

        @Override
        public int compare(Position in, Position other) {
            int r1 = in.getQuality();
            int r2 = other.getQuality();
            if (r1 > r2)
                return -1;
            if (r1 < r2)
                return 1;
            int s1 = in.getScore();
            int s2 = other.getScore();
            if (s1 > s2)
                return 1;
            if (s1 < s2)
                return -1;
            return Long.compare(in.getBitmap(), other.getBitmap());
        }
    }

    private static final long serialVersionUID = -6843554025281632383L;

    public IterateScores(Position pos, IEvaluator evaluator) {
        super(pos.whiteNext() ? new Ascending() : new Descending());
        for (Position next : NodeGen.getLegalMoves64(pos)) {
            add(next);
        }
    }

    public long[] getBitmaps() {
        long[] bitmaps = new long[size()];
        int i = 0;
        for (Position score : this) {
            bitmaps[i++] = score.getBitmap();
        }
        return bitmaps;
    }

    public void improveScore(PositionScore move, int score) {
        remove(move);
        move.run++;
        move.score = score;
        add(move);
    }

    public Position[] getSortedArray() {
        return toArray(new Position[size()]);
    }

}