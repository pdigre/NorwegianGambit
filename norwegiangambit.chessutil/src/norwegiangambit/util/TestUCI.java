package norwegiangambit.util;

import java.util.HashMap;

import org.junit.Test;

public class TestUCI {


	private static final String STARTPOS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

	@Test
	public void testStockFish() {
		WrapExe engine = new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH);
//		engine.command("uci", "uciok");
		engine.waitFor("isready", "readyok");
//		engine.command("copyprotection", "copyprotection ok");
		engine.command("position startpos");
		engine.command("perft 6");
		engine.waitFor("isready", "readyok");
		engine.command("divide 6");
		engine.waitFor("isready", "readyok");
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testStockFish2() {
		EngineStockfish engine = new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH);
		int perft= perft(engine.divide(STARTPOS,6));
		HashMap<String, Integer> divide= engine.divide(STARTPOS,6);
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testStockRoce2() {
		EngineRoce engine = new EngineRoce(EngineRoce.DEFAULT_EXEPATH);
		int perft= perft(engine.divide(STARTPOS,5));
		HashMap<String, Integer> divide= engine.divide(STARTPOS,5);
		engine.command("quit");
		engine.loop();
	}

	@Test
	public void testQperft() {
		EngineQperft engine = new EngineQperft(EngineQperft.DEFAULT_EXEPATH);
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
