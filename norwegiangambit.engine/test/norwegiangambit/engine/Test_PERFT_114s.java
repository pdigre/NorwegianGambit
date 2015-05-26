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
 *
 *  4.8 sec Dell 10.05.2015
 *  5.7 sec Dell 10.05.2015 using Lambda bitscan
 *  4.7 sec Dell 11.05.2015
 *  
 */
public class Test_PERFT_114s extends PerftTest{

	@BeforeClass
	public static void prepare() {
		setTesting(new FastPerft(true,true));
		setValidator(new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH));
	}

	@Test
	public void perft_114s() {
		testPerftSuite(getClass().getResourceAsStream("perftsuite.epd"));
		System.out.println("t1="+Movegen.tot1/1000000+"ms");
		System.out.println("t2="+Movegen.tot2/1000000+"ms");
	}
	
}
