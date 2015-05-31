package norwegiangambit.engine;

import static org.junit.Assert.assertEquals;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.iterate.NodeGen;
import norwegiangambit.engine.iterate.NodeUtil;
import norwegiangambit.engine.movedata.MBase;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.PSQT_SEF;
import norwegiangambit.util.PieceType;
import norwegiangambit.util.polyglot.ZobristPolyglot;

import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("static-method")
public class Test_BasicMoves {

	static ZobristPolyglot z = new ZobristPolyglot();

	@BeforeClass
	public static void prepare() {
		MBase.psqt=new PSQT_SEF();
		MBase.zobrist=z;
	}

    public static String getLegalMovesFromPos(String from_txt, StartGame start) {
        int from = FEN.text2pos(from_txt);
        int type = start.getPiece(from);
        FEN.printPiece(type, from);
        StringBuffer sb = new StringBuffer();
        sb.append(PieceType.types[type].fen);
        for (long bitmap : NodeUtil.filterFrom(NodeGen.getLegalMoves(start), from))
            sb.append(" "+FEN.pos2string(BITS.getTo(bitmap)));
        return sb.toString();
    }

    @Test
    public void moves_check_0() {
        String fen = "rnbqk1nr/ppp2ppp/4p3/3p4/1b1P4/P4P2/1PP1P1PP/RNBQKBNR w KQkq - 1 4";
        StartGame move = new StartGame(fen);
        assertEquals("King", "K f2", getLegalMovesFromPos("e1", move));
        assertEquals("Queen", "Q d2", getLegalMovesFromPos("d1", move));
        assertEquals("Pawn1", "P b4", getLegalMovesFromPos("a3", move));
        assertEquals("Pawn2", "P c3", getLegalMovesFromPos("c2", move));
    }

    @Test
    public void moves_opening_0() {
        String fen = "rnbqkb1r/pppp1ppp/5n2/4p3/3PP3/8/PPP2PPP/RNBQKBNR w KQkq - 1 3";
        assertEquals("Queen", "Q e2 f3 g4 h5 d2 d3", getLegalMovesFromPos("d1", new StartGame(fen)));
    }

    @Test
    public void moves_whiteStart_0() {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        StartGame move = new StartGame(fen);
        assertEquals("Knight1", "N a3 c3", getLegalMovesFromPos("b1", move));
        assertEquals("Knight2", "N f3 h3", getLegalMovesFromPos("g1", move));
        assertEquals("Pawn1", "P a3 a4", getLegalMovesFromPos("a2", move));
    }

    @Test
    public void moves_promotions_0() {
        String fen = "n1n5/PPPk4/8/8/8/8/4Kppp/5N1N b - - 0 1";
        assertEquals("Pawn", "p g1 g1 g1 g1 f1 f1 f1 f1 h1 h1 h1 h1", getLegalMovesFromPos("g2", new StartGame(fen)));
    }

    @Test
    public void moves_enpassant_0() {
        String fen = "rnbqkbnr/ppp1pppp/8/8/1PpP4/8/P3PPPP/RNBQKBNR b KQkq b3 0 3";
        assertEquals("Pawn", "p b3 c3", getLegalMovesFromPos("c4", new StartGame(fen)));
    }


}
