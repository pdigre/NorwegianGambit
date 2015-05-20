package norwegiangambit.engine;

import static org.junit.Assert.assertEquals;
import norwegiangambit.engine.evaluate.FastEval;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.util.FEN;
import norwegiangambit.util.PSQT_SEF;
import norwegiangambit.util.polyglot.Polyglot;
import norwegiangambit.util.polyglot.ZobristPolyglot;

import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("static-method")
public class Test_Zobrist_Polyglot {

	static ZobristPolyglot z = new ZobristPolyglot();
	

	@BeforeClass
	public static void prepare() {
		MBase.psqt=new PSQT_SEF();
		MBase.zobrist=z;
	}
	
    @Test
    public void zobringKeyStraight_0() {
        _assertZobrist("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "463b96181691fc9c");
        // e2e4
        _assertZobrist("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", "823c9b50fd114196");
        // e2e4 d75
        _assertZobrist("rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2", "756b94461c50fb0");
        // e2e4 d7d5 e4e5
        _assertZobrist("rnbqkbnr/ppp1pppp/8/3pP3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2", "662fafb965db29d4");
        // e2e4 d7d5 e4e5 f7f5
        _assertZobrist("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3", "22a48b5a8e47ff78");
        // e2e4 d7d5 e4e5 f7f5 e1e2
        _assertZobrist("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR b kq - 0 3", "652a607ca3f242c1");
        // e2e4 d7d5 e4e5 f7f5 e1e2 e8f7
        _assertZobrist("rnbq1bnr/ppp1pkpp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR w - - 0 4", "fdd303c946bdd9");
        // a2a4 b7b5 h2h4 b5b4 c2c4
        _assertZobrist("rnbqkbnr/p1pppppp/8/8/PpP4P/8/1P1PPPP1/RNBQKBNR b KQkq c3 0 3", "3c8123ea7b067637");
        // a2a4 b7b5 h2h4 b5b4 c2c4 b4c3 a1a3
        _assertZobrist("rnbqkbnr/p1pppppp/8/8/P6P/R1p5/1P1PPPP1/1NBQKBNR b Kkq - 0 4", "5c3f9b829b279560");
    }

    @Test
    public void zobringKeyMove_0() {
        // e2e4 d7d5 e4e5 f7f5 e1e2 e8f7
        FastEval e1 = start("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		FastEval e2 = next(e1, "e2e4");
		assertEquals("823c9b50fd114196", Long.toHexString(e2.getZobrist()));
		FastEval e3 = next(e2, "d7d5");
		assertEquals("756b94461c50fb0", Long.toHexString(e3.getZobrist()));
		FastEval e4 = next(e3, "e4e5");
		assertEquals("662fafb965db29d4", Long.toHexString(e4.getZobrist()));
		FastEval e5 = next(e4, "f7f5");
		assertEquals("22a48b5a8e47ff78", Long.toHexString(e5.getZobrist()));
		FastEval e6 = next(e5, "e1e2");
		assertEquals("652a607ca3f242c1", Long.toHexString(e6.getZobrist()));
		FastEval e7 = next(e6, "e8f7");
		assertEquals("fdd303c946bdd9", Long.toHexString(e7.getZobrist()));
    }

    @Test
    public void zobringKeyMove_1() {
        FastEval e1 = start("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        // a2a4 b7b5 h2h4 b5b4 c2c4
		FastEval e6 = next(next(next(next(next(e1, "a2a4"), "b7b5"), "h2h4"), "b5b4"), "c2c4");
		assertEquals("3c8123ea7b067637", Long.toHexString(e6.getZobrist()));
        // a2a4 b7b5 h2h4 b5b4 c2c4 b4c3 a1a3
		FastEval e8 = next(next(e6, "b4c3"), "a1a3");
		assertEquals("5c3f9b829b279560", Long.toHexString(e8.getZobrist()));
    }


	public long getKey(FastEval eval) {
		return z.getKey(eval.wNext, eval.castling, eval.epsq, FEN.boardFrom64(eval.aMinor, eval.aMajor, eval.aSlider, eval.bOccupied));
	}


	public FastEval start(String fen) {
		StartGame pos = new StartGame(fen);
		FastEval e1 = new FastEval();
		e1.set(pos.whiteNext(), pos.getBitmap(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		e1.evaluate();
		return e1;
	}


	public FastEval next(FastEval e1, String mm) {
		int md = getMove(mm, e1);
		FastEval e2 = new FastEval();
		e1.setChild(e2);
		e2.setParent(e1);
		e1.make(md);
		e2.evaluate(md);
		return e2;
	}

	public int getMove(String id, FastEval eval) {
		eval.generate();
		for (int m : eval.moves) {
			MOVEDATA md=MOVEDATA.ALL[m];
			String id2 = md.id();
			if(id.equals(id2)){
				return m;
			}
		}
		return 0;
	}

    @Test
    public void zobringKeyEnpassant_0() {
        _assertZobrist("rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2", "756b94461c50fb0");
        _assertZobrist("rnbqkbnr/p1pppppp/8/8/PpP4P/8/1P1PPPP1/RNBQKBNR b KQkq c3 0 3", "3c8123ea7b067637");
        _assertZobrist("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3", "22a48b5a8e47ff78");
        _assertZobrist("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", "823c9b50fd114196");
    }

    @Test
    public void zobringKeyCastling_0() {
        _assertZobrist("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR b kq - 0 3", "652a607ca3f242c1");
        _assertZobrist("rnbqkbnr/p1pppppp/8/8/P6P/R1p5/1P1PPPP1/1NBQKBNR b Kkq - 0 4", "5c3f9b829b279560");
        _assertZobrist("rnbq1bnr/ppp1pkpp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR w - - 0 4", "fdd303c946bdd9");
    }

    @Test
    public void polyglot_77() {
        _assertMoves("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "11712(d2d4) 11476(e2e4) 2824(g1f3) 2071(c2c4) 83(g2g3) 52(b2b3) 20(f2f4) 4(b1c3) 3(b2b4) 3(c2c3) 3(d2d3)");
        _assertMoves("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", "4157(c7c5) 2504(e7e5) 1102(e7e6) 782(c7c6) 201(d7d6) 93(g8f6) 88(g7g6) 56(d7d5) 19(b8c6) 2(b7b6)");
        _assertMoves("rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2", "103(e4d5)");
    }

    public static void _assertZobrist(String fen, String key) {
        assertEquals(key, Long.toHexString(new StartGame(fen).getZobristKey()));
    }

    public static void _assertMoves(String fen, String moves_expected) {
		assertEquals(moves_expected,Polyglot.printMoves(Polyglot.get(new StartGame(fen).getZobristKey())));
    }


}
