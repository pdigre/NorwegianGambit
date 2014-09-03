package norwegiangambit.engine;

import norwegiangambit.engine.base.BASE;
import norwegiangambit.util.EngineStockfish;
import norwegiangambit.util.PerftTest;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 30sec Dell 3.sep.2014
 *
 */
public class Test_PERFT_114s extends PerftTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
//		setTesting(new RunPerftFast());
		setTesting(new EngineStockfish("C:/chess/stockfish.exe"));
		setValidator(new EngineStockfish("C:/chess/stockfish.exe"));
//		setValidator(new RunPerftFast());
//		setValidator(new EngineRoce("C:/git/chess/roce39/roce39.exe"));
	}

	@Test
	public void perft_114s() {
		testPerftSuite(getClass().getResourceAsStream("perftsuite.epd"));
	}

}
