package norwegiangambit.engine;

import norwegiangambit.engine.evaluate.FastPerft;
import norwegiangambit.util.EngineStockfish;
import norwegiangambit.util.PerftTest;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 30.0 sec Dell 03.09.2014
 *  9.0 sec Dell 27.10.2014
 *  4.8 sec Dell 28.10.2014 Introducing Magic numbers and "unsafe" map
 *  3.5 sec Dell_Work 29.10.2014
 *
 */
public class Test_PERFT_114s extends PerftTest{

	@BeforeClass
	public static void prepare() {
		setTesting(new FastPerft());
//		setTesting(new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH));
		setValidator(new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH));
//		setValidator(new RunPerftFast());
//		setValidator(new EngineRoce(EngineRoce.DEFAULT_EXEPATH));
	}

	@Test
	public void perft_114s() {
		testPerftSuite(getClass().getResourceAsStream("perftsuite.epd"));
	}

}
