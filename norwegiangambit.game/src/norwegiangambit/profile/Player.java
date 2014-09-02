package norwegiangambit.profile;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import norwegiangambit.engine.base.ZobristKey;
import norwegiangambit.engine.evaluate.IEvaluator;
import norwegiangambit.engine.fen.FEN_POS;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.engine.fen.PositionScore;
import norwegiangambit.engine.fen.PositionWithLog;
import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.polyglot.BookMove;
import norwegiangambit.engine.polyglot.Polyglot;
import norwegiangambit.engine.util.IterateScores;
import norwegiangambit.util.BITS;

public abstract class Player implements IPlayer {

    public volatile RunState state = RunState.READY;

    public static boolean debug = true;

    public IterateScores moves;

    protected GameData game;

    public void setGameData(GameData game) {
        this.game = game;
    }

    @Override
    public ArrayList<Marking> getMarkers() {
        return new ArrayList<Marking>();
    }

    @Override
    public int clickSquare(int i) {
        return -1;
    }

    public int checkPolyglot() {
        PositionWithLog pos = game.pos;
        boolean white=pos.whiteNext();
        ArrayList<BookMove> list = Polyglot.get(ZobristKey.getKey(pos));
        int best = moves.first().getScore();
        Position[] array = moves.toArray(new Position[moves.size()]);
        for (BookMove book: list) {
            int bitmap = book.move;
            int f1 = Polyglot.getFrom(bitmap);
            int t1 = Polyglot.getTo(bitmap);
            for (Position p : array) {
                if (BITS.getFrom(p.getBitmap()) == f1 && BITS.getTo(p.getBitmap()) == t1){
                    moves.remove(p);
                    ((PositionScore)p).score=white?best+book.weight:best-book.weight;
                    moves.add(p);
                }
            }
        }
        return list.size();
    }

    protected void makeMove(long bitmap) {
        game.makeMove(bitmap);
    }

    public void makeMove() {
        game.makeMove(moves.first().getBitmap());
    }

    protected void printFEN() {
        System.out.println(FEN_POS.getFen(getPosition()));
    }

    protected Position getPosition() {
        return game.pos;
    }

    public static void printScore(IterateScores moves, String txt) {
        if (debug) {
            System.out.println("\n**** " + txt + " ****");
            for (Position m : moves)
                System.out.println(m.getQuality() + " "+m.getScore() + ":" + (m.whiteNext() ? "b " : "w ") + FEN_POS.notation(m));
        }
    }

    public static void runThinker(Position move, IterateScores moves, IIterator iter) {
        int score = 0;
        score = move.whiteNext() ? iter.white(move, IIterator.MIN, IIterator.MAX) : iter.black(move,
            IIterator.MIN, IIterator.MAX);
        moves.improveScore((PositionScore) move,score);
    }

    public static void initRun(Position pos) {
        if (debug)
            System.out.println(norwegiangambit.util.FEN.board2string(pos.bb_bit1, pos.bb_bit2, pos.bb_bit3, pos.bb_black));
    }

    @Override
    public String getDescription() {
        return "<No description>";
    }

    public void prepare() {
        moves = new IterateScores(getPosition(), IEvaluator.FULL);
    }

    public void processAndMove(IIterator iterator) {
        IterateScores copy = (IterateScores) moves.clone();
        for (Position m : copy)
            runThinker(m, moves, iterator);
        makeMove();
    }

    public void printTestHeader() {
    	Position pos = getPosition();
        System.out.println("**********************************************");
        System.out.println("START:" + FEN_POS.getFen(pos));
        initRun(pos);
    }

    public void setTimeout(int timeout_ms) {
        state = RunState.RUNNING;
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                state=RunState.TIMEOUT;
            }
        }, timeout_ms);
    }

    public void processUntilTimeout(IIterator iterator) {
        for (Position m : moves.toArray(new Position[moves.size()])) {
            if (state != RunState.RUNNING)
                break;
//            System.out.println("Processing:"+FEN.notation(m));
            runThinker(m, moves, iterator);
        }
    }

    public void processSimple(IIterator iterator) {
        for (Position m : (IterateScores) moves.clone())
            runThinker(m, moves, iterator);
    }


    
}
