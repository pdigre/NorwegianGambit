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
		Tester.useConcurrency=false;
		setTesting(engine);
	}
	
	@Test
	public void m1_3() {
		testEval(testing, 3,4133,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
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
		testEval(testing, 4,18465,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 e7e5 b1c3 f8a3=-95"                   
	              ,"a2a4 b8c6 b1c3 g8f6=-55"                   
	              ,"b1a3 b8c6 g1f3 g8f6=-40"                   
	              ,"b1c3 b8c6 g1f3 g8f6=0"                     
	              ,"b2b3 b8c6 b1c3 g8f6=-65"                   
	              ,"b2b4 b8c6 b4b5 g8f6=-105"                  
	              ,"c2c3 b8c6 g1f3 g8f6=-70"                   
	              ,"c2c4 b8c6 b1c3 g8f6=-60"                   
	              ,"d2d3 b8c6 b1c3 g8f6=-30"                   
	              ,"d2d4 b8c6 d4d5 g8f6=-55"                   
	              ,"e2e3 b8c6 b1c3 g8f6=-30"                   
	              ,"e2e4 g8f6 e4e5 b8c6=-55"                   
	              ,"f2f3 b8c6 b1c3 g8f6=-70"                   
	              ,"f2f4 b8c6 b1c3 g8f6=-60"                   
	              ,"g1f3 b8c6 b1c3 g8f6=0"                     
	              ,"g1h3 b8c6 b1c3 g8f6=-40"                   
	              ,"g2g3 b8c6 b1c3 g8f6=-65"                   
	              ,"g2g4 g8f6 b1c3 f6g4=-105"                  
	              ,"h2h3 d7d5 b1c3 c8h3=-95"                   
	              ,"h2h4 e7e5 h4h5 b8c6=-90"                   
		});
	}

	@Test
	public void m1_5() {
		testEval(testing, 5,142159,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 b8c6 g1f3 g8f6 b1c3=0"        
	              ,"a2a4 b8c6 g1f3 g8f6 b1c3=-5"       
	              ,"b1a3 b8c6 a3b5 g8f6 b5c7=50"       
	              ,"b1c3 g8f6 c3b5 b8c6 b5c7=50"       
	              ,"b2b3 b8c6 c1b2 d7d5 b2g7=15"       
	              ,"b2b4 e7e6 c1b2 f8b4 b2g7=-25"      
	              ,"c2c3 g8f6 d1b3 b8c6 b3f7=-5"       
	              ,"c2c4 g8f6 d1b3 b8c6 b3b7=5"        
	              ,"d2d3 b8c6 c1f4 g8f6 f4c7=40"       
	              ,"d2d4 b8c6 c1f4 g8f6 f4c7=60"       
	              ,"e2e3 b8c6 d1f3 d7d5 f3d5=60"       
	              ,"e2e4 b8c6 f1b5 c6e5 b5d7=70"       
	              ,"f2f3 b8c6 e2e4 g8f6 b1c3=-30"      
	              ,"f2f4 b8c6 b1c3 g8f6 g1f3=-10"      
	              ,"g1f3 b8c6 f3g5 g8f6 g5f7=50"       
	              ,"g1h3 e7e5 d2d4 e5d4 d1d4=20"       
	              ,"g2g3 g8f6 f1g2 e7e5 g2b7=15"       
	              ,"g2g4 g8f6 g4g5 f6d5 b1c3=-15"      
	              ,"h2h3 b8c6 g1f3 g8f6 b1c3=0"        
	              ,"h2h4 b8c6 g1f3 g8f6 b1c3=-5"       
		});
	}

	@Test
	public void m1_6() {
		testEval(testing, 6,801553,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 e7e5 b1c3 b8c6 g1f3 f8a3=-95"   
	              ,"a2a4 e7e5 b1c3 f8b4 c3e4 b4d2=-75"   
	              ,"b1a3 d7d5 g1f3 c8f5 d2d4 f5c2=-60"   
	              ,"b1c3 e7e5 e2e3 f8b4 c3e4 b4d2=-50"   
	              ,"b2b3 e7e5 b1c3 f8b4 c3e4 b4d2=-85"   
	              ,"b2b4 e7e5 b4b5 f8c5 b1c3 c5f2=-115"  
	              ,"c2c3 e7e6 d2d3 d8f6 e2e4 f6f2=-95"   
	              ,"c2c4 d7d5 c4d5 d8d5 d1a4 b8c6=-90"   
	              ,"d2d3 e7e5 e2e3 f8c5 b1c3 c5e3=-70"   
	              ,"d2d4 b8c6 d4d5 c6b4 b1c3 b4d5=-90"   
	              ,"e2e3 e7e5 b1c3 f8b4 c3e4 b4d2=-50"   
	              ,"e2e4 b8c6 b1c3 g8f6 g1f3 f6e4=-90"   
	              ,"f2f3 e7e5 b1c3 d8h4 g2g3 h4g3=-125"  
	              ,"f2f4 d7d5 b1c3 c8f5 g1f3 f5c2=-70"   
	              ,"g1f3 e7e6 d2d4 d8f6 f3d2 f6d4=-70"   
	              ,"g1h3 e7e5 b1c3 d8h4 h3g1 h4f2=-105"  
	              ,"g2g3 e7e5 b1c3 f8b4 c3e4 b4d2=-85"   
	              ,"g2g4 d7d5 e2e3 b8c6 g4g5 g8f6=-125"  
	              ,"h2h3 d7d5 b1c3 b8c6 g1f3 c8h3=-95"   
	              ,"h2h4 e7e5 b1c3 f8b4 c3e4 d8h4=-100"  
		});
	}

	@Test
	public void m1_7() {
		testEval(testing, 7,3802460,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=50"     
	              ,"a2a4 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=45"     
	              ,"b1a3 e7e5 a3c4 e5e4 c4e5 g8f6 e5f7=55"     
	              ,"b1c3 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=100"    
	              ,"b2b3 b8c6 g1f3 g8f6 c1b2 e7e5 f3e5=40"     
	              ,"b2b4 b8c6 b4b5 c6d4 e2e3 d4f5 b1c3=10"     
	              ,"c2c3 e7e6 d2d4 f8d6 d1b3 g8f6 b3b7=45"     
	              ,"c2c4 e7e6 b1c3 g8f6 d1a4 e6e5 a4a7=50"     
	              ,"d2d3 e7e6 b1c3 f8b4 g1f3 b4c3 b2c3=70"     
	              ,"d2d4 e7e6 b1c3 b8c6 c1f4 g8f6 f4c7=90"     
	              ,"e2e3 b8c6 b1c3 c6b4 f1c4 g8f6 c4f7=95"     
	              ,"e2e4 d7d5 b1c3 d5e4 f1b5 c7c6 c3e4=95"     
	              ,"f2f3 e7e6 b1c3 b8c6 d2d4 g8f6 e2e4=-10"    
	              ,"f2f4 b8c6 g1f3 g8f6 b1c3 d7d6 d2d4=10"     
	              ,"g1f3 g8f6 e2e3 b8c6 f1c4 d7d6 c4f7=70"     
	              ,"g1h3 d7d5 d2d4 c8h3 g2h3 b8c6 b1c3=35"     
	              ,"g2g3 b8c6 b1c3 g8f6 f1g2 d7d5 c3d5=40"     
	              ,"g2g4 d7d6 e2e4 e7e5 f1c4 b8c6 c4f7=40"     
	              ,"h2h3 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=50"     
	              ,"h2h4 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=45"     
		});
	}

	@Test
	public void m1_8() {
		testEval(testing, 8,20795152,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 e7e5 b1c3 b8c6 c3d5 c6d4 e2e4 f8a3=-105"
	              ,"a2a4 b8c6 e2e3 e7e5 b1c3 f8b4 c3e4 b4d2=-105"
	              ,"b1a3 e7e5 a3b5 b8c6 e2e3 f8c5 g1f3 c5e3=-95"
	              ,"b1c3 e7e5 e2e3 d8f6 d1f3 b8c6 f3f6 g8f6=-70"
	              ,"b2b3 e7e5 e2e3 b8c6 b1c3 f8b4 c3e4 b4d2=-115"
	              ,"b2b4 e7e5 c1b2 d8h4 b2c3 b8c6 g1f3 h4f2=-145"
	              ,"c2c3 b8c6 e2e3 e7e5 f1b5 g8f6 g1f3 d7d5=-115"
	              ,"c2c4 e7e5 b1c3 b8c6 e2e3 f8b4 c3e4 b4d2=-110"
	              ,"d2d3 e7e5 d3d4 b8c6 d4e5 f8b4 c2c3 c6e5=-95"
	              ,"d2d4 e7e6 b1c3 d8f6 a2a3 b8c6 e2e4 c6d4=-80"
	              ,"e2e3 b8c6 g1f3 g8f6 b1c3 f6g4 e3e4 g4f2=-60"
	              ,"e2e4 e7e5 d2d4 d8h4 d4e5 h4e4 e1d2 e4e5=-87"
	              ,"f2f3 e7e5 e2e3 b8c6 b1c3 d8h4 e1e2 h4h2=-139"
	              ,"f2f4 e7e5 e2e3 e5f4 e3f4 d8h4 e1e2 h4f4=-117"
	              ,"g1f3 e7e6 b1c3 d8f6 c3b5 b8a6 f3g1 f6b2=-100"
	              ,"g1h3 e7e5 b1c3 d8h4 c3d5 f8d6 h3g1 h4f2=-115"
	              ,"g2g3 d7d5 e2e3 b8c6 b1c3 c8f5 g1f3 f5c2=-105"
	              ,"g2g4 d7d5 e2e3 e7e5 b1c3 b8c6 g1f3 c8g4=-135"
	              ,"h2h3 d7d5 e2e3 e7e5 g1f3 b8c6 b1c3 c8h3=-115"
	              ,"h2h4 b8c6 b1c3 e7e6 e2e3 f8b4 c3e4 d8h4=-110"
		});
	}

	@Test
	public void m1_9() {
		testEval(testing, 9,93788106L,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",new String[]{
                   "a2a3 e7e6 d2d4 b8c6 e2e3 g8e7 f1b5 c6a5 b5d7=80"    
	              ,"a2a4 e7e6 e2e4 d7d5 e4e5 f8c5 b1c3 d5d4 g1f3=55"    
	              ,"b1a3 e7e6 a3b5 b8c6 e2e4 d7d5 e4d5 e6d5 b5c7=60"    
	              ,"b1c3 e7e6 e2e3 b8c6 d1g4 g8e7 g1f3 d7d6 g4g7=105"   
	              ,"b2b3 e7e6 c1b2 b8c6 e2e4 d8h4 f1d3 g8e7 b2g7=55"    
	              ,"b2b4 e7e6 b4b5 d7d6 e2e4 b8d7 c1b2 g8e7 b2g7=35"    
	              ,"c2c3 d7d5 g1f3 b8d7 d1a4 c7c6 a4d4 e7e5 f3e5=70"    
	              ,"c2c4 e7e6 e2e4 b8c6 g1f3 g8f6 d1a4 c6e7 a4a7=70"    
	              ,"d2d3 e7e6 e2e3 b8c6 d1f3 c6b4 b1a3 g8e7 f3b7=60"    
	              ,"d2d4 e7e6 d1d3 f8b4 e1d1 g8e7 c1g5 f7f6 g5f6=100"   
	              ,"e2e3 e7e6 b1c3 b8c6 d1g4 g8e7 g1f3 d7d6 g4g7=105"   
	              ,"e2e4 d7d5 b1c3 d5d4 c3d5 c7c6 d5b4 c6c5 g1f3=100"   
	              ,"f2f3 e7e5 e2e4 b8c6 b1c3 d8h4 g2g3 h4f6 d2d4=-5"    
	              ,"f2f4 e7e6 e2e3 g8f6 g1f3 b8c6 f1c4 d7d6 c4e6=40"    
	              ,"g1f3 g8f6 d2d4 e7e6 a2a3 c7c5 c1g5 d8a5 b1c3=95"    
	              ,"g1h3 d7d5 d2d4 g8f6 h3f4 g7g5 f4d3 g5g4 b1c3=60"    
	              ,"g2g3 b8c6 e2e4 e7e6 d1f3 d8f6 b1c3 f6f3 g1f3=55"    
	              ,"g2g4 e7e6 f1g2 d7d5 g1f3 g8f6 f3e5 d5d4 g2b7=55"    
	              ,"h2h3 e7e6 b1c3 b8c6 g1f3 g8f6 f3g5 e6e5 g5f7=60"    
	              ,"h2h4 e7e6 e2e4 d7d5 e4e5 f8c5 b1c3 d5d4 g1f3=55"    
		});
	}

	@AfterClass
	public static void finish() {
	}

/*
 * Killer moves testing
 * 
LVL        MM         AB        PVS    K1-2/2    TT-64k   TT2-64k     TT-1M    TT2-1M    TT-16M   TT2-16M    TT-64M   TT2-64M
3        8902       4130       5763      4133      4133      4133      4133      4133      4133      4133      4133      4133   
4      197281      48848      49451     18465     15416     15617     15403     15611     15399     15611     15399     15611  
5    4.865609     474191     361336    142159     78306     78099     71592     77903     71103     77903     71077     77903  
6  119.060324   4.486486   2.353697    801553    462018    373703    362291    359617    352015    359297    351908    359279 
7 3195.901860  43.546456  14.992705  3.802460  2.130623  1.500440  1.571397  1.306997  1.293210  1.306756  1.281445  1.306886
8             267.312462  74.805680 20.795152 11.981878  9.155194  8.949086  6.880547  7.008378  6.800005  6.818825  6.796110
9                        611.483870 93.788106 58.294155 43.036188 38.515040 25.653991 26.569120 22.793728 24.305811 22.749159

LVL  TT-64k     TT-1M    TT-16M    TT-64M
3      4133      4133      4133      4133
4     15617     15611     15611     15611
5     78099     77903     77903     77903
6    373703    359617    359297    359279
7  1.500440  1.306997  1.306756  1.306886
8  9.155194  6.880547  6.800005  6.796110
9 43.036188 25.653991 22.793728 22.749159
 * 
 *    
 *      
 *      
 *      
 */
}
