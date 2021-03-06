package norwegiangambit.engine;

import static org.junit.Assert.assertEquals;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.util.polyglot.Polyglot;
import org.junit.Test;

public class Test_Zobrist_Polyglot {

//	@BeforeClass
//	public static void prepare() {
//		EBase ebase=EBase.WHITE_BISHOP;
//	}

    @SuppressWarnings("static-method")
    @Test
    public void zobringKeyStraight_0() {
        _assertZobrist("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "463b96181691fc9c");
        _assertZobrist("rnbqkbnr/ppp1pppp/8/3pP3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2", "662fafb965db29d4");
    }

    @SuppressWarnings("static-method")
    @Test
    public void zobringKeyEnpassant_0() {
        _assertZobrist("rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2", "756b94461c50fb0");
        _assertZobrist("rnbqkbnr/p1pppppp/8/8/PpP4P/8/1P1PPPP1/RNBQKBNR b KQkq c3 0 3", "3c8123ea7b067637");
        _assertZobrist("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3", "22a48b5a8e47ff78");
        _assertZobrist("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1", "823c9b50fd114196");
    }

    @SuppressWarnings("static-method")
    @Test
    public void zobringKeyCastling_0() {
        _assertZobrist("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR b kq - 0 3", "652a607ca3f242c1");
        _assertZobrist("rnbqkbnr/p1pppppp/8/8/P6P/R1p5/1P1PPPP1/1NBQKBNR b Kkq - 0 4", "5c3f9b829b279560");
        _assertZobrist("rnbq1bnr/ppp1pkpp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR w - - 0 4", "fdd303c946bdd9");
    }

    @SuppressWarnings("static-method")
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
