package norwegiangambit.util;

import java.util.HashMap;

import org.junit.Test;

public class TestUCI {


	private static final String STARTPOS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

	@Test
	public void testStockFish() {
		WrapExe engine = new EngineStockfish("C:/fishtest/worker/testing/stockfish.exe");
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
		EngineStockfish engine = new EngineStockfish("C:/fishtest/worker/testing/stockfish.exe");
		int perft= perft(engine.divide(STARTPOS,6));
		HashMap<String, Integer> divide= engine.divide(STARTPOS,6);
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testStockRoce2() {
		EngineRoce engine = new EngineRoce("C:/git/chess/roce39/roce39.exe");
		int perft= perft(engine.divide(STARTPOS,5));
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
		EngineQperft engine = new EngineQperft("C:/git/TheChessProject/TheChessProject/resources/perft.exe");
//		int perft= perft(engine.divide(STARTPOS,6));
//		HashMap<String, Integer> divide= engine.divide(STARTPOS,6);
		System.out.println("hi");
	}

	private int perft(HashMap<String, Integer> divide) {
		int total=0;
		for (Integer count : divide.values())
			total+=count;
		
		return total;
	}

	
}
