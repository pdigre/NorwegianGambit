package norwegiangambit.engine;

import norwegiangambit.engine.evaluate.EvalTest;
import norwegiangambit.engine.evaluate.EvalTester;
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
public class Test_Eval_start extends EvalTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		setTesting(new EvalTester());
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
                   "a2a3 e7e5 a3a4 b8c6=-95"
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
	              ,"g2g4 g8f6 g4g5 b8c6=-105"
	              ,"h2h3 d7d5 h3h4 b8c6=-95"
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
	              ,"f2f3 e7e5 b1c3 d8h4 g2g3 h4h2=-125"  
	              ,"f2f4 d7d5 b1c3 c8f5 g1f3 f5c2=-70"   
	              ,"g1f3 e7e6 d2d4 d8f6 f3d2 f6d4=-70"   
	              ,"g1h3 e7e5 b1c3 d8h4 h3g1 h4f2=-105"  
	              ,"g2g3 e7e5 b1c3 f8b4 c3e4 b4d2=-85"   
	              ,"g2g4 d7d5 e2e3 b8c6 g4g5 g8f6=-125"  
	              ,"h2h3 d7d5 b1c3 b8c6 h3h4 g8f6=-95"   
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
	              ,"c2c3 e7e6 d2d4 f8d6 d1b3 b8c6 b3b7=45"     
	              ,"c2c4 e7e6 e2e4 b8c6 d1a4 c6e5 a4a7=50"     
	              ,"d2d3 e7e6 b1c3 b8c6 c1f4 g8f6 f4c7=70"     
	              ,"d2d4 e7e6 b1c3 b8c6 c1f4 g8f6 f4c7=90"     
	              ,"e2e3 b8c6 b1c3 c6b4 f1c4 g8f6 c4f7=95"     
	              ,"e2e4 d7d5 b1c3 d5e4 f1b5 c7c6 c3e4=95"     
	              ,"f2f3 e7e6 d2d4 b8c6 e2e4 g8f6 b1c3=-10"    
	              ,"f2f4 b8c6 d2d4 e7e6 b1c3 d8h4 e1d2=10"     
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
	              ,"e2e3 e7e5 d2d4 e5e4 f1c4 b8c6 d4d5 g8f6=-60"
	              ,"e2e4 e7e5 d2d4 d8h4 d4e5 h4e4 e1d2 e4e5=-87"
	              ,"f2f3 e7e5 e2e3 b8c6 b1c3 d8h4 e1e2 h4h2=-139"
	              ,"f2f4 e7e5 e2e3 e5f4 e3f4 d8h4 e1e2 h4f4=-117"
	              ,"g1f3 e7e6 b1c3 d8f6 c3b5 b8a6 f3g1 f6b2=-100"
	              ,"g1h3 e7e5 b1c3 d8h4 c3d5 f8d6 h3g1 h4f2=-115"
	              ,"g2g3 d7d5 b1c3 d5d4 c3b5 d8d7 b5a3 b8c6=-105"
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
	              ,"f2f3 e7e5 e2e4 b8c6 b1c3 d8h4 g2g3 h4f6 g1e2=-5"    
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
LVL        MM         AB        PVS    K1-2/2     K1-2/0 
3        8902       4130       5763      4133       4133 
4      197281      48848      49451     18465      18465 
5    4.865609     474191     361336    142159     142093 
6  119.060324   4.486486   2.353697    801553     806438 
7 3195.901860  43.546456  14.992705  3.802460   3.851039 
8             267.312462  74.805680 20.795152  21.589496 
9                        611.483870 93.788106  98.518723






Move   3   4   5   6   7   8    MM3   MM 4  MM 5     MM 6     MM 7         AB3  AB 4  AB 5   AB 6    AB 7     AB 8       PVS3  PVS4  PVS 5  PVS 6    PVS 7   PVS 8          
a2a3   0 -50   0 -40  50  49    380   8457  181046   4463267  106743106    195  2566  18752  184474  1231058  11179114    264  2423  14315  103638   682650  4922955        
b2b3 -15 -65  15 -35  95  65    420   9345  215255   5310358  133233975    202  2489  25647  230269  2031761  11920974    277  2515  17248  117509   662647  2435539           
c2c3 -20 -70  -5 - 5 370 415    420   9272  222861   5417640  144074944    202  2438  24180  268661  2477972  13046913    277  2429  19057  154038   570275  3202024            
d2d3  20 -30  40   0 145  95    539  11959  328511   8073082  227598692    226  2511  28429  232751  2881114  16812840    319  2530  23619  152928   816015  4516249           
e2e3  20 -30  60  65 418 493    599  13134  402988   9726018  306138410    263  2418  37600  336249  4190630  17028959    395  2505  25146  135717   946492  2628146           
f2f3 -20 -70 -30 -70   0 -50    380   8457  178889   4404141  102021008    205  2604  15586  161662  1156266  11073434    274  2492  14583  108238   743325  6125597           
g2g3 -15 -65  15 -15  95  83    420   9345  217210   5346260  135987651    213  2684  30515  287167  2407795  15108519    288  2624  18742  116681   634264  3585586           
h2h3   0 -50   0 -40  50  49    380   8457  181044   4463070  106678423    205  2580  20601  198260  1409758  13254462    274  2552  16244  116776   919668  5536288        
a2a4  -5 -55  -5 -45  45  44    420   9329  217832   5363555  137077337    203  2510  18938  176793  1198722  10718853    278  2426  14578  100036   795742  4232083          
b2b4 -10 -60  20 -30 100  70    421   9332  216145   5293555  134087476    190  2344  22031  196495  1543859  10205195    265  2440  15891  112439   614127  2988397            
c2c4 -10 -60   5   5 350 373    441   9744  240082   5866666  157756443    188  2270  23584  282648  2727621  16750777    266  2454  18195  125294   760184  1552948           
d2d4  40 -10  60  20 180 130    560  12435  361790   8879566  269605599    209  2199  22991  177996  2036400  11317247    304  2394  24774  167896   535138  3295306           
e2e4  40 -10  70  90 435 490    600  13160  405385   9771632  309478263    246  2167  28692  254333  3578229  14703072    377  2401  20065  118018   919745  3345981           
f2f4 -10 -60 -10 -50  30  10    401   8929  198473   4890429  119614841    193  2463  20490  199529  1327763  11293514    265  2527  16551  124293   923017  4496843            
g2g4 -10 -60  20 -30 100  70    421   9328  214048   5239875  130293018    210  2662  26914  256150  2030022  13839579    285  2661  17591  126489   684061  3217020            
h2h4  -5 -55  -5 -45  45  44    420   9329  218829   5385554  138495290    213  2591  21078  197590  1484564  13203884    288  2565  16661  115257  1001584  5735681          
b1a3  10 -40  50  25 370 320    400   8885  198572   4856835  120142144    190  2452  26097  253551  2507181  14499522    262  2397  17467   77469   523447  2267818           
b1c3  50   0  60  50 370 320    440   9755  234656   5708064  148527161    180  2091  16122  175052  2036004   9832630    258  2238  17071   73165   539457  2114120           
g1f3  50   0  60  10 379 329    440   9748  233491   5723523  147678554    195  2303  18184  159955  2673280  16449729    273  2343  14839  102311   921651  5373964           
g1h3  10 -40  50  10 379 329    400   8881  198502   4877234  120669525    202  2506  27760  256901  2616457  15073245    274  2535  18699  105505   799216  3233135           
                               8902 197281 4865609 119060324 3195901860   4130 48848 474191 4486486 43546456 267312462   5763 49451 361336 2353697 14992705 74805680

		 Depth 7  Depth 8  Depth 9
PVS      14992705 74805680 611483870
PVS+AB1  14992658 
PVS+AB2  14773675 74558317 610046230
PVS+AB3  15118699 







 */
}
