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
		testEval(testing, 8,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@AfterClass
	public static void finish() {
	}
/*
ALPHABETA 2       MINIMAX 4             ALPHABETA 4          ALPHABETA 6          ALPHABETA 8
a2a3 (20) = -50   a2a3 (8457)  = -50   a2a3 (2566) = -50  a2a3 (184474) = -40   a2a3 (11179114) = 49
b2b3 (20) = -65   b2b3 (9345)  = -65   b2b3 (2489) = -65  b2b3 (230269) = -35   b2b3 (11920974) = 65
c2c3 (20) = -70   c2c3 (9272)  = -70   c2c3 (2438) = -70  c2c3 (268661) = -5    c2c3 (13046913) = 415
d2d3 (20) = -30   d2d3 (11959) = -30   d2d3 (2511) = -30  d2d3 (232751) = 0     d2d3 (16812840) = 95
e2e3 (20) = -30   e2e3 (13134) = -30   e2e3 (2418) = -30  e2e3 (336249) = 65    e2e3 (17028959) = 493
f2f3 (20) = -70   f2f3 (8457)  = -70   f2f3 (2604) = -70  f2f3 (161662) = -70   f2f3 (11073434) = -50
g2g3 (20) = -65   g2g3 (9345)  = -65   g2g3 (2684) = -65  g2g3 (287167) = -15   g2g3 (15108519) = 83
h2h3 (20) = -50   h2h3 (8457)  = -50   h2h3 (2580) = -50  h2h3 (198260) = -40   h2h3 (13254462) = 49
a2a4 (20) = -55   a2a4 (9329)  = -55   a2a4 (2510) = -55  a2a4 (176793) = -45   a2a4 (10718853) = 44
b2b4 (20) = -60   b2b4 (9332)  = -60   b2b4 (2344) = -60  b2b4 (196495) = -30   b2b4 (10205195) = 70
c2c4 (20) = -60   c2c4 (9744)  = -60   c2c4 (2270) = -60  c2c4 (282648) = 5     c2c4 (16750777) = 373
d2d4 (20) = -10   d2d4 (12435) = -10   d2d4 (2199) = -10  d2d4 (177996) = 20    d2d4 (11317247) = 130
e2e4 (20) = -10   e2e4 (13160) = -10   e2e4 (2167) = -10  e2e4 (254333) = 90    e2e4 (14703072) = 490
f2f4 (20) = -60   f2f4 (8929)  = -60   f2f4 (2463) = -60  f2f4 (199529) = -50   f2f4 (11293514) = 10
g2g4 (20) = -60   g2g4 (9328)  = -60   g2g4 (2662) = -60  g2g4 (256150) = -30   g2g4 (13839579) = 70
h2h4 (20) = -55   h2h4 (9329)  = -55   h2h4 (2591) = -55  h2h4 (197590) = -45   h2h4 (13203884) = 44
b1a3 (20) = -40   b1a3 (8885)  = -40   b1a3 (2452) = -40  b1a3 (253551) = 25    b1a3 (14499522) = 320
b1c3 (20) = 0     b1c3 (9755)  = 0     b1c3 (2091) = 0    b1c3 (175052) = 50    b1c3 (9832630)  = 320
g1f3 (20) = 0     g1f3 (9748)  = 0     g1f3 (2303) = 0    g1f3 (159955) = 10    g1f3 (16449729) = 329
g1h3 (20) = -40   g1h3 (8881)  = -40   g1h3 (2506) = -40  g1h3 (256901) = 10    g1h3 (15073245) = 329

 */
}
