package norwegiangambit.profile;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import norwegiangambit.engine.fen.FEN_POS;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.engine.fen.PositionScore;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.iterate.MovegenUtil;
import norwegiangambit.engine.util.IterateScores;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.polyglot.BookMove;
import norwegiangambit.util.polyglot.Polyglot;

public abstract class Player implements IPlayer {

	public volatile RunState state = RunState.READY;

	public static boolean debug = true;

	String[] mvs;
	
	public IterateScores moves;

	protected GameData game;

	public void setGameData(GameData game) {
		this.game = game;
	}

	public String[] getMoves(){
		return mvs;
	}
	
	public String getFen(){
		return game.fen;
	}
	
	@Override
	public ArrayList<Marking> getMarkers() {
		return new ArrayList<Marking>();
	}

	@Override
	public String clickSquare(int i) {
		return null;
	}

	public int checkPolyglot() {
		boolean white = FEN.whiteNext(game.fen);
		long zobristKey = new StartGame(game.fen).getZobristKey();

		ArrayList<BookMove> list = Polyglot.get(zobristKey);
		int best = moves.first().getScore();
		Position[] array = moves.toArray(new Position[moves.size()]);
		for (BookMove book : list) {
			int bitmap = book.move;
			int f1 = Polyglot.getFrom(bitmap);
			int t1 = Polyglot.getTo(bitmap);
			for (Position p : array) {
				if (BITS.getFrom(p.getBitmap()) == f1 && BITS.getTo(p.getBitmap()) == t1) {
					moves.remove(p);
					((PositionScore) p).score = white ? best + book.weight : best - book.weight;
					moves.add(p);
				}
			}
		}
		return list.size();
	}

	protected void makeMove(String bitmap) {
		game.makeMove(bitmap);
	}

	public void makeMove() {
		game.makeMove(mvs[0]);
	}

	protected void printFEN() {
		System.out.println(game.fen);
	}

	public static void printScore(IterateScores moves, String txt) {
		if (debug) {
			System.out.println("\n**** " + txt + " ****");
			for (Position m : moves)
				System.out.println(m.getQuality() + " " + m.getScore() + ":" + (m.whiteNext() ? "b " : "w ") + FEN_POS.notation(m.getFen()));
		}
	}

	public static void runThinker(Position move, IterateScores moves, IIterator iter) {
		int score = 0;
		score = move.whiteNext() ? iter.white(move, IIterator.MIN, IIterator.MAX) : iter.black(move, IIterator.MIN, IIterator.MAX);
		moves.improveScore((PositionScore) move, score);
	}

	public static void initRun(String fen) {
		if (debug){
			System.out.println(FEN.board2string(fen));
		}
	}

	@Override
	public String getDescription() {
		return "<No description>";
	}

	public void prepare() {
		mvs=MovegenUtil.getLegalMoves(game.fen);
	}

	public void processAndMove(IIterator iterator) {
		IterateScores copy = (IterateScores) moves.clone();
		for (Position m : copy)
			runThinker(m, moves, iterator);
		makeMove();
	}

	public void printTestHeader() {
		System.out.println("**********************************************");
		System.out.println("START:" + game.fen);
		initRun(game.fen);
	}

	public void setTimeout(int timeout_ms) {
		state = RunState.RUNNING;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				state = RunState.TIMEOUT;
			}
		}, timeout_ms);
	}

	public void processUntilTimeout(IIterator iterator) {
		for (Position m : moves.toArray(new Position[moves.size()])) {
			if (state != RunState.RUNNING)
				break;
			// System.out.println("Processing:"+FEN.notation(m));
			runThinker(m, moves, iterator);
		}
	}

	public void processSimple(IIterator iterator) {
		for (Position m : (IterateScores) moves.clone())
			runThinker(m, moves, iterator);
	}

}
