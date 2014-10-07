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
 * 4400ms with pinned pieces - take 1 (3700 p� jobben)
 * 
 */
@SuppressWarnings("static-method")
public class Test_Eval_kiwipete extends EvalTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		setTesting(new EvalTester());
	}
	
	@Test
	public void k1_3() {
		testEval(testing, 3,30978,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
				"a1b1=300,a1c1=300,a1d1=305,a2a3=300,a2a4=295,b2b3=285,c3a4=280,c3b1=250,c3b5=150,c3d1=260,d2c1=290,d2e3=310,d2f4=100,d2g5=305,d2h6=305,d5d6=340,d5e6=395,e1c1=315,e1d1=300,e1f1=278,e1g1=330,e2a6=465,e2b5=305,e2c4=310,e2d1=145,e2d3=310,e2f1=290,e5c4=295,e5c6=315,e5d3=150,e5d7=380,e5f7=391,e5g4=290,e5g6=520,f3d3=90,f3e3=285,f3f4=300,f3f5=300,f3f6=655,f3g3=85,f3g4=85,f3h3=376,f3h5=270,g2g3=285,g2g4=290,g2h3=406,h1f1=300,h1g1=300".split(","));
	}

	@Test
	public void k1_4() {
		testEval(testing, 4,142641,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
				"a1b1=226,a1c1=226,a1d1=231,a2a3=226,a2a4=221,b2b3=211,c3a4=186,c3b1=176,c3b5=150,c3d1=186,d2c1=216,d2e3=236,d2f4=70,d2g5=231,d2h6=216,d5d6=295,d5e6=335,e1c1=241,e1d1=226,e1f1=250,e1g1=255,e2a6=465,e2b5=231,e2c4=236,e2d1=145,e2d3=236,e2f1=236,e5c4=221,e5c6=250,e5d3=150,e5d7=365,e5f7=317,e5g4=285,e5g6=345,f3d3=60,f3e3=80,f3f4=80,f3f5=80,f3f6=1190,f3g3=55,f3g4=55,f3h3=385,f3h5=280,g2g3=265,g2g4=270,g2h3=386,h1f1=226,h1g1=280".split(","));
	}

	@Test
	public void k1_5() {
		testEval(testing, 5,1551510,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
				"a1b1=570,a1c1=570,a1d1=575,a2a3=570,a2a4=565,b2b3=555,c3a4=570,c3b1=520,c3b5=621,c3d1=530,d2c1=560,d2e3=580,d2f4=405,d2g5=595,d2h6=595,d5d6=660,d5e6=675,e1c1=609,e1d1=570,e1f1=580,e1g1=655,e2a6=675,e2b5=570,e2c4=570,e2d1=355,e2d3=570,e2f1=585,e5c4=560,e5c6=616,e5d3=360,e5d7=700,e5f7=717,e5g4=612,e5g6=830,f3d3=260,f3e3=396,f3f4=411,f3f5=411,f3f6=1525,f3g3=345,f3g4=345,f3h3=878,f3h5=465,g2g3=611,g2g4=616,g2h3=732,h1f1=595,h1g1=626".split(","));
	}

	@Test
	public void k1_6() {
		testEval(testing, 6,6483651,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
				"a1b1=517,a1c1=517,a1d1=522,a2a3=517,a2a4=512,b2b3=502,c3a4=517,c3b1=467,c3b5=325,c3d1=477,d2c1=507,d2e3=527,d2f4=360,d2g5=557,d2h6=550,d5d6=1120,d5e6=606,e1c1=731,e1d1=507,e1f1=560,e1g1=824,e2a6=615,e2b5=517,e2c4=517,e2d1=295,e2d3=517,e2f1=717,e5c4=507,e5c6=1095,e5d3=297,e5d7=626,e5f7=755,e5g4=545,e5g6=1120,f3d3=216,f3e3=376,f3f4=391,f3f5=391,f3f6=1507,f3g3=307,f3g4=307,f3h3=885,f3h5=380,g2g3=781,g2g4=786,g2h3=901,h1f1=717,h1g1=796".split(","));
	}

	@Test
//	@Ignore
	public void k1_7() {
		testEval(testing, 7,14963127L,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1",
				"a2a3=50,a2a4=45,b1a3=370,b1c3=370,b2b3=95,b2b4=100,c2c3=370,c2c4=350,d2d3=145,d2d4=180,e2e3=418,e2e4=435,f2f3=0,f2f4=30,g1f3=379,g1h3=379,g2g3=95,g2g4=100,h2h3=50,h2h4=45".split(","));
	}

	@AfterClass
	public static void finish() {
	}
/*
 * Killer moves
 * l1 1/1               l2 1/1              l2 1/0
TOT 5787 > 24           5777 > 14           5777 > 14
TOT 49560 > 109         49515 > 64          49515 > 64
TOT 380693 > 19373      372544 > 11224      368676 > 7356
TOT 2420867 > 67937     2378140 > 25210     2360749 > 7819
TOT 16098274 > 1135147  15546123 > 582996   15106385 > 143258
TOT 73107695 > -1622161 72403707 > -2326149 72428394 > -2301462
           


 * 
 * 
 * 
 * 
 * 
Move    3    4    5    6   7   8                PVS3  PVS4   PVS 5   PVS 6    PVS 7   PVS 8                                       
a1b1  300  226  570  517                        576   2846   29612  131441
a1c1  300  226  570  517                        575   2844   29383  128270
a1d1  305  231  575  522                        558   2833   28469  126797
a2a3  300  226  570  517                        634   3165   42641  151141
a2a4  295  221  565  512                        900   2958   31916  133454
b2b3  285  211  555  502                        937   2644   27181  116484
c3a4  280  186  570  517                        693   2016    9276   78448
c3b1  250  176  520  467                        601   1995   19148  105379
c3b5  150  150  621  325                        648   3222   45959   68059
c3d1  260  186  530  477                        601   1995   19097  105790
d2c1  290  216  560  507                        573   2829   30702  150931
d2e3  310  236  580  527                        612   2933   27489  137620
d2f4  100   70  405  360                        492   3118   51182  274283
d2g5  305  231  595  557                        988   2757   25652  128301
d2h6  305  216  595  550                        585   2664   23923  128423
d5d6  340  295  660 1120                        477   1674   31415   61966
d5e6  395  335  675  606                        701   2651   32123  114784
e1c1  315  241  609  731                        556   2836   27657  100431
e1d1  300  226  570  507                        567   2843   30147  169774
e1f1  278  250  580  560                        198   2081   15571  103016
e1g1  330  255  655  824                        593   3075   58107  181801
e2a6  465  465  675  615                        638   3342   23136  104327
e2b5  305  231  570  517                        894   2622   31930  118054
e2c4  310  236  570  517                        744   2514   24603  129611
e2d1  145  145  355  295                        545   4043   20014  147240
e2d3  310  236  570  517                        635   2796   30205  124234
e2f1  290  236  585  717                        553   2801   32005  101211
e5c4  295  221  560  507                        531   2941   18157  102772
e5c6  315  250  616 1095                        297   2103   20996   94507
e5d3  150  150  360  297                        440   3589   18358  136655
e5d7  380  365  700  626                        648   3986   49599   92446
e5f7  391  317  717  755                        769   2146   27523   85872
e5g4  290  285  612  545                        499   2126   41753   49718
e5g6  520  345  830 1120                        367   2066   18420   87511
f3d3  90    60  260  216                        626   3498   16621  179452
f3e3  285   80  396  376                        582   3691   20545  176066
f3f4  300   80  411  391                        609   3594   22820  143583
f3f5  300   80  411  391                        957   4369   23572  205985
f3f6  655 1190 1525 1507                       872   1989   41274    76500
f3g3  85    55  345  307                        539   3296   22959  179084
f3g4  85    55  345  307                        625   3222   15825  151532
f3h3  376  385  878  885                       1244   4121   72822  227741
f3h5  270  280  465  380                       1217   4844   82146  427589
g2g3  285  265  611  781                        517   3115   39131  137765
g2g4  290  270  616  786                        694   3496   55428  115085
g2h3  406  386  732  901                        720   3579   59364  134670
h1f1  300  226  595  717                        566   2842   30237  100434
h1g1  300  280  626  796                        585   3931   55417  157414
                                              30978 142641 1551510 6483651

		 Depth 7  Depth 8  Depth 9
PVS      14992705 74805680 611483870
PVS+AB1  14992658 
PVS+AB2  14773675 74558317 610046230
PVS+AB3  15118699 







 */
}