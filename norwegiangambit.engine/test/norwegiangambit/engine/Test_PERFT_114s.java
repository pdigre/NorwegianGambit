package norwegiangambit.engine;

import norwegiangambit.engine.base.BASE;
import norwegiangambit.util.EngineRoce;
import norwegiangambit.util.PerftTest;

import org.junit.BeforeClass;
import org.junit.Test;

public class Test_PERFT_114s extends PerftTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		setTesting(new RunPerftFast());
		setValidator(new EngineRoce("C:/git/chess/roce39/roce39.exe"));
	}

	@Test
	public void perft_114s() {
		testPerftSuite(getClass().getResourceAsStream("perftsuite.epd"));
	}

}
