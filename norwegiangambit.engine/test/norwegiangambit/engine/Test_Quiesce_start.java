package norwegiangambit.engine;

import norwegiangambit.engine.evaluate.EvalTest;
import norwegiangambit.engine.evaluate.QuiesceTester;
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
public class Test_Quiesce_start extends EvalTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		QuiesceTester engine = new QuiesceTester();
		Tester.useConcurrency=true;
		QuiesceTester.useTransposition=true;
		setTesting(engine);
	}
	
	@Test
	public void m1_3() {
		testQuiesce(testing, 3,4133,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 b8c6 b1c3=0"                 
	              ,"a2a4 b8c6 b1c3=-5"                
	              ,"b1a3 b8c6 g1f3=10"                
	              ,"b1c3 b8c6 g1f3=50"                
	              ,"b2b3 b8c6 b1c3=-15"               
	              ,"b2b4 b8c6 b1c3=-10"               
	              ,"c2c3 b8c6 g1f3=-20"               
	              ,"c2c4 b8c6 b1c3=-10"               
	              ,"d2d3 b8c6 b1c3=20"                
	              ,"d2d4 b8c6 b1c3=40"                
	              ,"e2e3 b8c6 b1c3=20"                
	              ,"e2e4 b8c6 b1c3=40"                
	              ,"f2f3 b8c6 b1c3=-20"               
	              ,"f2f4 b8c6 b1c3=-10"               
	              ,"g1f3 b8c6 b1c3=50"                
	              ,"g1h3 b8c6 b1c3=10"                
	              ,"g2g3 b8c6 b1c3=-15"               
	              ,"g2g4 b8c6 b1c3=-10"               
	              ,"h2h3 b8c6 b1c3=0"                 
	              ,"h2h4 b8c6 b1c3=-5"                
		});
	}

	@Test
	public void m1_4() {
		testQuiesce(testing, 4,18465,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 b8c6 b1c3 g8f6=-50"              
	              ,"a2a4 b8c6 b1c3 g8f6=-55"              
	              ,"b1a3 b8c6 g1f3 g8f6=-40"              
	              ,"b1c3 b8c6 g1f3 g8f6=0"            
	              ,"b2b3 b8c6 b1c3 g8f6=-65"              
	              ,"b2b4 e7e5 b4b5 g8f6=-95"              
	              ,"c2c3 b8c6 g1f3 g8f6=-70"              
	              ,"c2c4 b8c6 b1c3 g8f6=-60"              
	              ,"d2d3 b8c6 b1c3 g8f6=-30"              
	              ,"d2d4 b8c6 d4d5 c6e5=-15"              
	              ,"e2e3 b8c6 b1c3 g8f6=-30"              
	              ,"e2e4 g8f6 e4e5 f6d5=-15"              
	              ,"f2f3 b8c6 b1c3 g8f6=-70"              
	              ,"f2f4 b8c6 b1c3 g8f6=-60"              
	              ,"g1f3 b8c6 b1c3 g8f6=0"            
	              ,"g1h3 b8c6 b1c3 g8f6=-40"              
	              ,"g2g3 b8c6 b1c3 g8f6=-65"              
	              ,"g2g4 d7d5 g4g5 b8c6=-95"              
	              ,"h2h3 b8c6 b1c3 g8f6=-50"              
	              ,"h2h4 b8c6 b1c3 g8f6=-55"              
		});
	}

	@Test
	public void m1_5() {
		testQuiesce(testing, 5,142159,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 b8c6 g1f3 g8f6 b1c3=0"    
	              ,"a2a4 b8c6 g1f3 g8f6 b1c3=-5"    
	              ,"b1a3 b8c6 a3b5 g8f6 b5c7=50"    
	              ,"b1c3 b8c6 c3b5 g8f6 b5c7=50"    
	              ,"b2b3 b8c6 g1f3 g8f6 b1c3=-15"    
	              ,"b2b4 e7e6 b4b5 g8f6 b1c3=-25"    
	              ,"c2c3 b8c6 d2d4 g8f6 g1f3=-30"    
	              ,"c2c4 b8c6 b1c3 g8f6 g1f3=-10"    
	              ,"d2d3 b8c6 g1f3 g8f6 b1c3=20"    
	              ,"d2d4 b8c6 b1c3 g8f6 g1f3=40"    
	              ,"e2e3 b8c6 b1c3 g8f6 g1f3=20"    
	              ,"e2e4 b8c6 b1c3 g8f6 g1f3=40"    
	              ,"f2f3 b8c6 e2e4 g8f6 b1c3=-30"    
	              ,"f2f4 b8c6 g1f3 g8f6 b1c3=-10"    
	              ,"g1f3 b8c6 f3g5 g8f6 g5f7=50"    
	              ,"g1h3 e7e5 d2d4 e5d4 d1d4=20"    
	              ,"g2g3 b8c6 g1f3 g8f6 b1c3=-15"    
	              ,"g2g4 g8f6 g4g5 f6d5 g1f3=-15"    
	              ,"h2h3 b8c6 g1f3 g8f6 b1c3=0"    
	              ,"h2h4 b8c6 g1f3 g8f6 b1c3=-5"    
		});
	}

	@Test
	public void m1_6() {
		testQuiesce(testing, 6,801553,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 g8f6 g1f3 f6g4 b1c3 g4f2=-50"
	              ,"a2a4 g8f6 g1f3 f6g4 b1c3 g4f2=-55"
	              ,"b1a3 d7d5 a3b5 g8f6 g1f3 b8c6=-45"
	              ,"b1c3 b8c6 g1f3 g8f6 d2d3 d7d5=-20"
	              ,"b2b3 b8a6 b1c3 a6b4 g1f3 b4c2=-65"
	              ,"b2b4 b8c6 b4b5 c6b4 b1c3 b4c2=-105"
	              ,"c2c3 g8f6 d2d4 f6g4 g1f3 g4f2=-80"
	              ,"c2c4 g8f6 g1f3 f6e4 b1c3 e4f2=-60"
	              ,"d2d3 b8c6 b1c3 c6b4 g1f3 b4c2=-30"
	              ,"d2d4 d7d5 e2e3 b8c6 g1f3 g8f6=-30"
	              ,"e2e3 b8c6 b1c3 c6b4 g1f3 b4c2=-30"
	              ,"e2e4 b8c6 b1c3 g8f6 d2d3 d7d5=-30"
	              ,"f2f3 d7d5 e2e4 b8c6 e4d5 d8d5=-80"
	              ,"f2f4 b8a6 g1f3 a6b4 b1c3 b4c2=-60"
	              ,"g1f3 d7d5 b1c3 b8c6 d2d3 g8f6=-20"
	              ,"g1h3 d7d5 b1c3 e7e5 d2d3 b8c6=-50"
	              ,"g2g3 b8a6 b1c3 a6b4 g1f3 b4c2=-65"
	              ,"g2g4 d7d5 g4g5 c8f5 d2d3 b8c6=-95"
	              ,"h2h3 b8a6 b1c3 a6b4 g1f3 b4c2=-50"
	              ,"h2h4 b8a6 b1c3 a6b4 g1f3 b4c2=-55"
		});
	}

	@Test
	public void m1_7() {
		testQuiesce(testing, 7,3802460,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 b8c6 b1c3 g8f6 g1f3 e7e6 d2d4=20"  
	              ,"a2a4 b8c6 b1c3 g8f6 g1f3 e7e6 d2d4=15"  
	              ,"b1a3 e7e5 g1f3 f8a3 b2a3 e5e4 f3d4=50"  
	              ,"b1c3 b8c6 g1f3 g8f6 d2d4 d7d6 e2e4=60"  
	              ,"b2b3 b8c6 b1c3 g8f6 g1f3 d7d6 d2d4=5"  
	              ,"b2b4 e7e5 g1f3 e5e4 f3e5 f8b4 e5f7=-20"  
	              ,"c2c3 d7d5 d2d4 b8c6 c1f4 d8d6 g1f3=-10"  
	              ,"c2c4 b8c6 b1c3 g8f6 g1f3 e7e6 d2d4=10"  
	              ,"d2d3 b8c6 b1c3 g8f6 g1f3 e7e6 e2e4=40"  
	              ,"d2d4 b8c6 g1f3 g8f6 b1c3 d7d6 e2e4=60"  
	              ,"e2e3 b8c6 b1c3 g8f6 d2d4 d7d6 g1f3=40"  
	              ,"e2e4 b8c6 b1c3 g8f6 g1f3 e7e6 d2d4=60"  
	              ,"f2f3 e7e5 b1c3 b8c6 e2e4 g8f6 d2d4=-30"  
	              ,"f2f4 b8c6 g1f3 g8f6 b1c3 e7e6 d2d4=10"  
	              ,"g1f3 b8c6 b1c3 g8f6 d2d4 e7e6 e2e4=60"  
	              ,"g1h3 e7e5 b1c3 b8c6 c3b5 g8f6 b5c7=20"  
	              ,"g2g3 b8c6 b1c3 g8f6 g1f3 e7e6 d2d4=5"  
	              ,"g2g4 d7d5 e2e4 g8f6 b1c3 f6e4 c3d5=-10"  
	              ,"h2h3 b8c6 b1c3 g8f6 g1f3 e7e6 d2d4=20"  
	              ,"h2h4 b8c6 b1c3 g8f6 g1f3 e7e6 d2d4=15"  
		});
	}

	@Test
	public void m1_8() {
		testQuiesce(testing, 8,20795152,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 d7d5 b1c3 g8f6 g1f3 b8c6 d2d3 e7e5=-60"
	              ,"a2a4 d7d5 b1c3 g8f6 g1f3 b8c6 d2d3 e7e5=-65"
	              ,"b1a3 b8c6 g1f3 g8f6 d2d3 c6b4 a3c4 b4c2=-75"
	              ,"b1c3 b8c6 d2d4 d7d5 e2e3 e7e5 d4e5 c6e5=-30"
	              ,"b2b3 d7d5 b1c3 g8f6 g1f3 b8c6 d2d3 e7e5=-75"
	              ,"b2b4 d7d5 b1c3 b8c6 b4b5 c6d4 g1f3 d4c2=-95"
	              ,"c2c3 b8c6 d2d4 g8f6 d4d5 c6e5 f2f4 e5c4=-90"
	              ,"c2c4 b8c6 b1c3 g8f6 g1f3 f6g4 e2e4 g4f2=-70"
	              ,"d2d3 b8c6 e2e4 g8f6 b1c3 c6b4 g1f3 b4c2=-40"
	              ,"d2d4 d7d5 b1c3 g8f6 g1f3 f6g4 e2e3 g4f2=-30"
	              ,"e2e3 b8c6 d2d4 g8f6 g1f3 c6b4 b1c3 b4c2=-40"
	              ,"e2e4 e7e5 d2d3 b8c6 b1c3 f8c5 c1e3 g8f6=-30"
	              ,"f2f3 e7e5 e2e4 b8c6 g1e2 d7d5 e4d5 d8d5=-80"
	              ,"f2f4 b8c6 g1f3 g8f6 b1c3 c6b4 d2d4 b4c2=-70"
	              ,"g1f3 b8c6 b1c3 g8f6 d2d4 c6b4 e2e3 b4c2=-40"
	              ,"g1h3 d7d5 b1c3 e7e5 d2d3 g8f6 h3g5 b8c6=-65"
	              ,"g2g3 d7d5 b1c3 g8f6 g1f3 b8c6 d2d3 e7e5=-75"
	              ,"g2g4 d7d5 h2h3 g8f6 b1c3 d5d4 g4g5 b8c6=-100"
	              ,"h2h3 d7d5 b1c3 g8f6 g1f3 b8c6 d2d3 e7e5=-60"
	              ,"h2h4 d7d5 b1c3 g8f6 g1f3 b8c6 d2d3 e7e5=-65"
		});
	}

	@Test
	public void m1_9() {
		testQuiesce(testing, 9,93788106L,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 d7d5 d2d4 b8c6 g1f3 e7e6 f3g5 g8f6 g5f7=30"
	              ,"a2a4 d7d5 d2d4 b8c6 g1f3 g8f6 f3g5 e7e6 g5f7=25"
	              ,"b1a3 e7e5 g1f3 b8c6 a3c4 e5e4 f3e5 c6e5 c4e5=15"
	              ,"b1c3 e7e6 e2e4 g8f6 g1f3 d7d5 d2d3 d5e4 d3e4=70"
	              ,"b2b3 d7d5 d2d4 b8c6 g1f3 e7e6 c1f4 f8d6 b1c3=15"
	              ,"b2b4 e7e5 b4b5 d7d5 g1f3 e5e4 f3e5 f7f6 e5g4=-25"
	              ,"c2c3 e7e6 d2d4 b8c6 g1f3 g8f6 f3g5 d7d5 g5f7=10"
	              ,"c2c4 d7d6 b1c3 b8c6 g1f3 g8f6 d2d4 e7e6 e2e4=30"
	              ,"d2d3 e7e5 g1f3 b8c6 b1c3 f8b4 e2e4 b4c3 b2c3=40"
	              ,"d2d4 e7e6 e2e4 b8c6 g1f3 g8f6 b1c3 d7d6 c1e3=60"
	              ,"e2e3 d7d5 b1c3 b8c6 g1f3 c8g4 d2d4 e7e6 e3e4=55"
	              ,"e2e4 e7e6 b1c3 g8f6 g1f3 d7d5 d2d3 d5e4 d3e4=70"
	              ,"f2f3 d7d6 d2d4 e7e5 d4e5 d6e5 d1d8 e8d8 b1c3=-10"
	              ,"f2f4 e7e6 b1c3 d7d5 g1f3 b8c6 e2e4 d5e4 c3e4=30"
	              ,"g1f3 e7e6 b1c3 g8f6 e2e4 d7d5 d2d3 d5e4 d3e4=70"
	              ,"g1h3 e7e5 b1c3 b8c6 e2e4 d7d6 f1c4 c8e6 d2d4=30"
	              ,"g2g3 d7d5 d2d4 b8c6 b1c3 e7e6 e2e4 d5e4 c3e4=15"
	              ,"g2g4 d7d5 e2e3 g8f6 g4g5 f6e4 b1c3 e4c3 d2c3=-15"
	              ,"h2h3 d7d5 d2d4 b8c6 g1f3 e7e6 c1f4 f8d6 b1c3=30"
	              ,"h2h4 e7e5 g1f3 b8c6 b1c3 g8e7 f3g5 f7f5 g5h7=30"
		});
	}

	@AfterClass
	public static void finish() {
	}

/*
 * Horizon moves testing
                                                                Quiescence
LVL        MM         AB        PVS    K1-2/2    TT-16M            Winning
3        8902       4130       5763      4133      4133      4042       52
4      197281      48848      49451     18465     15380     16012      459
5    4.865609     474191     361336    142159     71144     56300     4415
6  119.060324   4.486486   2.353697    801553    359250    300886    27227
7 3195.901860  43.546456  14.992705  3.802460  1.300946    875986   103486
8             267.312462  74.805680 20.795152  7.218729  4.397331   578556
9                        611.483870 93.788106 27.271052 16.535587 2.698345

 
 
 
 
 
 
 
 
 
 
 
 

 *    
 *    
 *    
 *    
 *    
 *    
 *    
 *      
 *      
 *      
 */
}
