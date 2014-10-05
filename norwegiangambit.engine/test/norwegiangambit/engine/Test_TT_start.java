package norwegiangambit.engine;

import norwegiangambit.engine.evaluate.EvalTest;
import norwegiangambit.engine.evaluate.EvalTesterTT;
import norwegiangambit.engine.evaluate.Tester;
import norwegiangambit.engine.movegen.BASE;

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
public class Test_TT_start extends EvalTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		EvalTesterTT engine = new EvalTesterTT();
		Tester.useConcurrency=true;
		setTesting(engine);
	}
	
	@Test
	public void m1_3() {
		testEval(testing, 3,4127,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1","a2a3=0,a2a4=-5,b1a3=10,b1c3=50,b2b3=-15,b2b4=-10,c2c3=-20,c2c4=-10,d2d3=20,d2d4=40,e2e3=20,e2e4=40,f2f3=-20,f2f4=-10,g1f3=50,g1h3=10,g2g3=-15,g2g4=-10,h2h3=0,h2h4=-5");
	}

	@Test
	public void m1_4() {
		testEval(testing, 4,11513,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1","a2a3=-50,a2a4=-55,b1a3=-40,b1c3=0,b2b3=-65,b2b4=-60,c2c3=-70,c2c4=-60,d2d3=-30,d2d4=-10,e2e3=-30,e2e4=-10,f2f3=-70,f2f4=-60,g1f3=0,g1h3=-40,g2g3=-65,g2g4=-60,h2h3=-50,h2h4=-55");
	}

	@Test
	public void m1_5() {
		testEval(testing, 5,78731,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
				"a2a3=0,a2a4=-5,b1a3=50,b1c3=60,b2b3=15,b2b4=20,c2c3=-5,c2c4=5,d2d3=40,d2d4=60,e2e3=60,e2e4=70,f2f3=-30,f2f4=-10,g1f3=60,g1h3=50,g2g3=15,g2g4=20,h2h3=0,h2h4=-5");
	}

	@Test
	public void m1_6() {
		testEval(testing, 6,289914,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
				"a2a3=-40,a2a4=-45,b1a3=25,b1c3=50,b2b3=-35,b2b4=-30,c2c3=-5,c2c4=5,d2d3=0,d2d4=20,e2e3=65,e2e4=90,f2f3=-70,f2f4=-50,g1f3=10,g1h3=10,g2g3=-15,g2g4=-30,h2h3=-40,h2h4=-45");
	}

	@Test
	public void m1_7() {
		testEval(testing, 7,2258393,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
				"a2a3=50,a2a4=45,b1a3=370,b1c3=370,b2b3=95,b2b4=100,c2c3=370,c2c4=350,d2d3=145,d2d4=180,e2e3=418,e2e4=435,f2f3=0,f2f4=30,g1f3=379,g1h3=379,g2g3=95,g2g4=100,h2h3=50,h2h4=45");
	}

	@Test
	public void m1_8() {
		testEval(testing, 8,8423050,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
				"a2a3=49,a2a4=44,b1a3=320,b1c3=320,b2b3=65,b2b4=70,c2c3=415,c2c4=373,d2d3=95,d2d4=130,e2e3=493,e2e4=490,f2f3=-50,f2f4=10,g1f3=329,g1h3=329,g2g3=83,g2g4=70,h2h3=49,h2h4=44");
	}

	@Test
	public void m1_9() {
		testEval(testing, 9,146300044,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",
				"a2a3=385,a2a4=370,b1a3=415,b1c3=430,b2b3=385,b2b4=390,c2c3=760,c2c4=550,d2d3=395,d2d4=445,e2e3=1020,e2e4=1040,f2f3=45,f2f4=120,g1f3=430,g1h3=410,g2g3=370,g2g4=323,h2h3=375,h2h4=370");
	}

	@AfterClass
	public static void finish() {
	}
/*
 * Killer moves testing
 * 
LVL        MM         AB        PVS     K1-2/2      TT-64k      TT-1M
3        8902       4130       5763       4133        3141       3141     
4      197281      48848      49451      16783       10217      10090    
5    4.865609     474191     361336     139275       50324      46505    
6  119.060324   4.486486   2.353697     516162      314942     270166   
7 3195.901860  43.546456  14.992705   3.410173    2.717581   2.014566  
8             267.312462  74.805680  14.015194   11.190470   9.043398  
9                        611.483870 167.300175  141.494884 132.428331

 *
 *
 *         TOT 3141      > 0
 *         TOT 10090     > -127
 *         TOT 46505     > -3819
 *         TOT 270166    > -44776
 *         TOT 2014566   > -703015
 *         TOT 9043398   > -2147072
 *         TOT 132428331 > -9066553
 *
 *
 *
 *
 */
}
