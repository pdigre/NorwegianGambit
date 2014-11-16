package norwegiangambit.engine;

import static org.junit.Assert.assertEquals;
import norwegiangambit.util.BITS;
import norwegiangambit.util.EngineStockfish;

import org.junit.Test;

public class TestStockFish {

	@Test
	public void testEval() {
		String fen = "rq2r1k1/5pp1/p7/4bNP1/1p2P2P/5Q2/PP4K1/5R1R w - - 0 1";
		System.out.println(new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH).eval(fen));
	}

}
