package norwegiangambit.profile;

import norwegiangambit.engine.evaluate.IEvaluator;
import norwegiangambit.engine.fen.FEN;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.engine.fen.PositionScore;
import norwegiangambit.engine.iterate.AlphaBeta;
import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.iterate.IterateEnd;
import norwegiangambit.engine.util.IterateScores;

public class Hard extends Player {

    @Override
    public String getDescription() {
        return "Medium AlphaBeta timed to 9 secs with Tactical evaluation";
    }

    @Override
    public void run() {
        int n = checkPolyglot();
        if (n > 0) {
            int pick = (int) (Math.random() * Math.random() * Math.random() * n);
            makeMove(((Position) moves.toArray()[pick]).getBitmap());
            return;
        }
        IIterator iter0 = new IterateEnd();
        IIterator iter1 = new AlphaBeta(iter0);
        IIterator iter2 = new AlphaBeta(iter1);
        IIterator iter3 = new AlphaBeta(iter2);

        printTestHeader();
        setTimeout(20000);

        for (int run = 1; run < 10; run++) {
//            System.out.println("**** Phase " + run + " ****");
            Position pos = getPosition();
            recursive(pos, moves, iter3, run, "");
//            printScore(moves, "---- Phase " + run + " ----");
        }
        makeMove();
    }

    public void recursive(Position parent, IterateScores children, IIterator iterator, int run, String prefix) {

        // if (state != RunState.RUNNING)
        // return;
        // phase=0 1.0.0 first third
        // phase=1 2.0.0 second third
        // phase=2 2.1.0 first next third
        // phase=3 3.1.0 last third
        // phase=4 3.2.0 second next third
        // phase=5 3.2.1 first next next third
        // phase=6 3.3.1 first next next third
        // phase=7 3.3.2 first next next third
        int phase = run;
        int factor = 1;
        int loop = 1;
        while (phase > 3) {
            factor *= 4;
            phase = run / factor;
            loop++;
        }
        int remainder = run - factor * phase+1;
        if (phase == 1 || phase == 2) {
            int n = children.size() / 3;
            for (Object m : children.toArray()) {
                PositionScore move = (PositionScore) m;
                int r = move.getQuality();
                if (run < 4) {
                    if (r < 1) {
//                        if (!prefix.isEmpty())
//                            System.out.println("Processing " + prefix + " " + FEN.notation(move));
                        runThinker(move, children, iterator);
                        if (n-- < 1)
                            break;
                    }
                } else {
                    if (r < loop) {
                        if (move.children == null)
                            move.children = new IterateScores(parent, IEvaluator.FULL);
                        IterateScores moves = move.children;
                        recursive(move, moves, iterator, remainder, prefix + FEN.notation(move) + ", ");
                        moves.improveScore(move, move.score);
                        if (n-- < 1)
                            break;
                    }
                }
            }
        } else if (phase == 3) {
            for (Object m : children.toArray()) {
                PositionScore move = (PositionScore) m;
                int r = move.getQuality();
                if (run < 4) {
                    if (r < 1) {
//                        if (!prefix.isEmpty())
//                            System.out.println("Processing " + prefix + " " + FEN.notation(move));
                        runThinker(move, children, iterator);
                    }
                } else {
                    if (r < loop) {
                        if (move.children == null)
                            move.children = new IterateScores(parent, IEvaluator.FULL);
                        IterateScores moves = move.children;
                        recursive(move, moves, iterator, remainder, prefix + FEN.notation(move) + ", ");
                        moves.improveScore(move, move.score);
                    }
                }
            }
        }
    }
}
