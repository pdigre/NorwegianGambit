package norwegiangambit.engine;

import norwegiangambit.engine.base.BASE;
import norwegiangambit.engine.evaluate.PerftTesterSlow;
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
		setTesting(new PerftTesterSlow());
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
