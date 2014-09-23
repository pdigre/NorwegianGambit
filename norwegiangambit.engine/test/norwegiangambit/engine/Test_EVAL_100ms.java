package norwegiangambit.engine;

import norwegiangambit.engine.base.BASE;
import norwegiangambit.engine.evaluate.EvalTest;
import norwegiangambit.engine.evaluate.RunAlphaBeta;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 3700ms
 * 
 * 
 * 4700ms with improvements in "kingsafe"
 * 4300ms with new MOVEDATA bits
 * 4400ms with pinned pieces - take 1 (3700 på jobben)
 * 
 */
@SuppressWarnings("static-method")
public class Test_EVAL_100ms extends EvalTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		setTesting(new RunAlphaBeta());
	}
	
	@Test
	public void m1_1_553() {
		testEval(testing, 1,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@AfterClass
	public static void finish() {
	}
}
