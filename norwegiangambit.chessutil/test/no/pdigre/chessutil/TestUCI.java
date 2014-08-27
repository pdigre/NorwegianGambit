package no.pdigre.chessutil;

import java.util.HashMap;

import org.junit.Test;

public class TestUCI {


	private static final String STARTPOS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

	@Test
	public void testStockFish() {
		WrapExe engine = new EngineStockfish();
//		engine.command("uci", "uciok");
		engine.command("isready", "readyok");
//		engine.command("copyprotection", "copyprotection ok");
		engine.command("position startpos");
		engine.command("perft 6");
		engine.command("isready", "readyok");
		engine.command("divide 6");
		engine.command("isready", "readyok");
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testStockFish2() {
		EngineStockfish engine = new EngineStockfish();
		int perft= engine.perft(STARTPOS,6);
		HashMap<String, Integer> divide= engine.divide(STARTPOS,6);
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testStockRoce2() {
		EngineRoce engine = new EngineRoce();
		int perft= engine.perft(STARTPOS,5);
		HashMap<String, Integer> divide= engine.divide(STARTPOS,5);
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testRoce() {
		WrapExe engine = new WrapExe("C:/git/TheChessProject/TheChessProject/resources/roce39.exe");
		engine.command("setboard "+STARTPOS);
		engine.command("perft 6");
		engine.command("divide 6");
		engine.command("uci", "uciok");
		engine.command("isready", "readyok");
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testQperft() {
		EngineQperft engine = new EngineQperft();
		int perft= engine.perft(STARTPOS,6);
		HashMap<String, Integer> divide= engine.divide(STARTPOS,6);
		System.out.println("hi");
	}

}
