package norwegiangambit.engine

import norwegiangambit.engine.evaluate.AbstractTest
import norwegiangambit.engine.evaluate.AbstractTester
import norwegiangambit.engine.evaluate.EvalTester
import norwegiangambit.engine.evaluate.QuiesceTester
import norwegiangambit.engine.movegen.BASE
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import static norwegiangambit.engine.evaluate.AbstractTester.*
import static norwegiangambit.engine.evaluate.QuiesceTester.*

class Test_Eval2_kiwipete extends AbstractTest {

	@BeforeClass
	def static void prepare() {
		new BASE();
		val engine = new EvalTester();
		AbstractTester.useConcurrency=true;
		QuiesceTester.useTransposition=true;
		setTesting(engine);
	}
	
	@Test
	def void k1_3() {
		testEval(testing, 3,95,'''
		r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
		a1b1 b4c3 f3f6=-15
		a1c1 b4c3 f3f6=-15
		a1d1 b4c3 f3f6=-10
		a2a3 b4c3 f3f6=-15
		a2a4 b4c3 f3f6=-20
		b2b3 b4c3 f3f6=-30
		c3a4 a6e2 a4b6=-30
		c3b1 a6e2 e1e2=-58
		c3b5 a6b5 e2b5=-5
		c3d1 a6e2 e1e2=-48
		d2c1 b4c3 f3f6=-25
		d2e3 b4c3 f3f6=-5
		d2f4 b4c3 e2a6=-25
		d2g5 b4c3 g5f6=-10
		d2h6 b4c3 h6g7=-10
		d5d6 e7d6 f3f6=200
		d5e6 b4c3 f3f6=90
		e1c1 b4c3 f3f6=0
		e1d1 b4c3 f3f6=-15
		e1f1 b4c3 f3f6=-5
		e1g1 b4c3 f3f6=13
		e2a6 b4c3 f3f6=295
		e2b5 b4c3 f3f6=-10
		e2c4 b6c4 e5c4=-15
		e2d1 b4c3 f3f6=-25
		e2d3 a6d3 f3d3=-10
		e2f1 b4c3 f3f6=-25
		e5c4 b4c3 f3f6=-20
		e5c6 d7c6 f3f6=-20
		e5d3 b4c3 f3f6=-20
		e5d7 b4c3 d7f6=55
		e5f7 e8f7 f3f6=97
		e5g4 b4c3 g4f6=-25
		e5g6 f7g6 f3f6=100
		f3d3 a6d3 e2d3=-575
		f3e3 b4c3 e3b6=-30
		f3f4 b4c3 f4f6=-15
		f3f5 g6f5 e2a6=-600
		f3f6 g7f6 e2a6=-270
		f3g3 b4c3 e2a6=-40
		f3g4 f6g4 e2g4=-575
		f3h3 h8h3 g2h3=-300
		f3h5 g6h5 e2a6=-600
		g2g3 b4c3 f3f6=-30
		g2g4 b4c3 f3f6=-25
		g2h3 b4c3 f3f6=90
		h1f1 b4c3 f3f6=-15
		h1g1 b4c3 f3f6=-15
		''');
	}

	@Test
	def void k1_4() {
		testEval(testing, 4,-395,'''
		r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
		a1b1 b4c3 e2a6 c3d2=-395
		a1c1 b4c3 e2a6 c3d2=-395
		a1d1 b4c3 e2a6 c3d2=-390
		a2a3 b4c3 e2a6 c3d2=-395
		a2a4 b4c3 e2a6 c3d2=-400
		b2b3 b4c3 e2a6 c3d2=-410
		c3a4 h3g2 f3g2 a6e2=-385
		c3b1 h3g2 f3g2 a6e2=-395
		c3b5 e8g8 g2h3 a6b5=-263
		c3d1 h3g2 f3g2 a6e2=-385
		d2c1 h3g2 f3g2 b4c3=-360
		d2e3 h3g2 f3g2 b4c3=-340
		d2f4 h3g2 f3g2 b4c3=-340
		d2g5 h3g2 f3g2 b4c3=-345
		d2h6 h3g2 f3g2 b4c3=-360
		d5d6 h3g2 f3g2 b4c3=-345
		d5e6 b4c3 e6f7 e7f7=-260
		e1c1 b4c3 e2a6 c3d2=-380
		e1d1 b4c3 e2a6 c3d2=-395
		e1f1 b4c3 e2a6 c3d2=-385
		e1g1 b4c3 e2a6 c3d2=-367
		e2a6 b4c3 d2c3 h3g2=-55
		e2b5 b4c3 b5a6 c3d2=-395
		e2c4 b4c3 c4a6 c3d2=-395
		e2d1 b4c3 d2c3 h3g2=-375
		e2d3 b4c3 d3a6 c3d2=-395
		e2f1 b4c3 f1a6 c3d2=-395
		e5c4 b4c3 c4b6 c3d2=-405
		e5c6 d7c6 e2a6 b4c3=-385
		e5d3 b4c3 d2c3 a6d3=-575
		e5d7 b6d7 e2a6 b4c3=-295
		e5f7 e8f7 e2a6 b4c3=-268
		e5g4 b4c3 g4f6 g7f6=-360
		e5g6 h3g2 f3g2 b4c3=-275
		f3d3 h3g2 d3a6 g2h1q=-1075
		f3e3 h3g2 h1g1 b4c3=-495
		f3f4 h3g2 h1g1 b4c3=-495
		f3f5 h3g2 f5f6 g2h1q=-1050
		f3f6 g7f6 e2a6 b4c3=-615
		f3g3 b4c3 e2a6 c3d2=-400
		f3g4 f6g4 e2g4 b4c3=-920
		f3h3 h8h3 g2h3 b4c3=-645
		f3h5 g6h5 e2a6 b4c3=-945
		g2g3 b4c3 e2a6 c3d2=-410
		g2g4 b4c3 e2a6 c3d2=-405
		g2h3 b4c3 e2a6 c3d2=-290
		h1f1 b4c3 e2a6 c3d2=-395
		h1g1 b4c3 e2a6 c3d2=-395
		''');
	}

	@Test
	def void k1_5() {
		testEval(testing, 5,-111,'''
		r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
		a1b1 b4c3 d2c3 a6e2 e1e2=-221
		a1c1 b4c3 d2c3 a6e2 e1e2=-221
		a1d1 b4c3 d2c3 a6e2 e1e2=-216
		a2a3 b4c3 d2c3 a6e2 e1e2=-221
		a2a4 b4c3 d2c3 a6e2 e1e2=-226
		b2b3 b4c3 d2c3 a6e2 e1e2=-236
		c3a4 h3g2 f3g2 a6e2 a4b6=-35
		c3b1 h3g2 f3g2 a6e2 e1e2=-61
		c3b5 f6d5 e5f7 a6b5 f7h8=65
		c3d1 h3g2 f3g2 a6e2 e1e2=-51
		d2c1 h3g2 f3g2 b4c3 e2a6=-50
		d2e3 h3g2 f3g2 b4c3 e2a6=-30
		d2f4 b4c3 e2a6 c3b2 g2h3=-60
		d2g5 h3g2 f3g2 b4c3 g5f6=-15
		d2h6 h8h6 e2a6 b4c3 f3f6=-30
		d5d6 c7d6 e2a6 d6e5 f3f6=165
		d5e6 b4c3 e6f7 e8f8 f3f6=211
		e1c1 b4c3 d2c3 a6e2 f3f6=-211
		e1d1 b4c3 e2a6 c3d2 f3f6=-65
		e1f1 b4c3 e2a6 c3d2 f3f6=-56
		e1g1 b4c3 e2a6 c3d2 f3f6=-38
		e2a6 h3g2 h1g1 b4c3 f3f6=145
		e2b5 b4c3 d2c3 a6b5 f3f6=-225
		e2c4 b4c3 d2c3 b6c4 e5c4=-230
		e2d1 h3g2 h1g1 b4c3 f3f6=-175
		e2d3 b4c3 d2c3 a6d3 f3d3=-225
		e2f1 b4c3 d2c3 a6f1 f3f6=-215
		e5c4 b4c3 d2c3 b6c4 e2c4=-225
		e5c6 d7c6 e2a6 b4c3 f3f6=-55
		e5d3 b4c3 d2c3 a6d3 e2d3=-225
		e5d7 f6d7 d5d6 e7d6 e2a6=-70
		e5f7 b4c3 f7h8 c3d2 e1d2=-10
		e5g4 f6g4 e2a6 b4c3 f3g4=-50
		e5g6 f7g6 e2a6 b4c3 f3f6=65
		f3d3 a6d3 d5d6 e7d6 e2d3=-705
		f3e3 b4c3 d2c3 a6e2 e1e2=-221
		f3f4 b4c3 d2c3 a6e2 e1e2=-221
		f3f5 e6f5 e5g6 f7g6 e2a6=-825
		f3f6 g7f6 d5d6 e7d6 e2a6=-400
		f3g3 b4c3 d2c3 a6e2 e1e2=-226
		f3g4 f6g4 e5g4 b4c3 e2a6=-625
		f3h3 h8h3 g2h3 b4c3 e2a6=-335
		f3h5 g6h5 d5d6 e7d6 e2a6=-730
		g2g3 b4c3 d2c3 a6e2 e1e2=-236
		g2g4 b4c3 d2c3 a6e2 e1e2=-231
		g2h3 b4c3 d2c3 a6e2 e1e2=-115
		h1f1 b4c3 d2c3 a6e2 e1e2=-221
		h1g1 b4c3 d2c3 a6e2 e1e2=-221
		''');
	}

	@Test
	def void k1_6() {
		testEval(testing, 6,-290,'''
		r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
		a1b1 b4c3 e2a6 h3g2 f3g2 c3d2=-400
		a1c1 b4c3 e2a6 h3g2 f3g2 c3d2=-400
		a1d1 b4c3 e2a6 h3g2 f3g2 c3d2=-395
		a2a3 b4c3 e2a6 h3g2 f3g2 c3d2=-400
		a2a4 b4c3 e2a6 h3g2 f3g2 c3d2=-405
		b2b3 b4c3 e2a6 h3g2 f3g2 c3d2=-415
		c3a4 a6e2 e1e2 b6a4 g2h3 e6d5=-345
		c3b1 a6e2 e1e2 e6d5 e5d3 h3g2=-356
		c3b5 f6d5 e4d5 g7e5 b5c7 e5c7=-345
		c3d1 a6e2 e1e2 e6d5 e5d3 h3g2=-346
		d2c1 b4c3 e2a6 e6d5 e5d3 h3g2=-345
		d2e3 b4c3 e2a6 c3b2 a1b1 h3g2=-315
		d2f4 b4c3 e2a6 e6d5 e5d3 h3g2=-325
		d2g5 b4c3 e2a6 c3b2 a1b1 h3g2=-320
		d2h6 b4c3 h6g7 c3b2 a1b1 a6e2=-490
		d5d6 c7d6 e2a6 b4c3 d2c3 d6e5=-380
		d5e6 e7e6 e2a6 b4c3 d2c3 e6e5=-275
		e1c1 b4c3 e2a6 h3g2 f3g2 c3d2=-386
		e1d1 b4c3 e2a6 h3g2 f3g2 c3d2=-400
		e1f1 b4c3 e2a6 c3d2 a1d1 h3g2=-531
		e1g1 b4c3 e2a6 c3d2 a1d1 h3g2=-513
		e2a6 b4c3 d2c3 e6d5 e5d3 h3g2=-205
		e2b5 b4c3 b5a6 h3g2 f3g2 c3d2=-400
		e2c4 b6c4 e5c4 b4c3 d2c3 a6c4=-580
		e2d1 b4c3 d2c3 e6d5 e4d5 e7e5=-590
		e2d3 b4c3 d3a6 h3g2 f3g2 c3d2=-400
		e2f1 b4c3 d2c3 e6d5 f1a6 e7e5=-395
		e5c4 b4c3 d2c3 b6c4 e2c4 a6c4=-580
		e5c6 d7c6 e2a6 b4c3 d2c3 c6d5=-410
		e5d3 b4c3 d2c3 h3g2 f3g2 a6d3=-580
		e5d7 b4c3 d7f6 g7f6 e2a6 c3d2=-330
		e5f7 b4c3 f7h8 c3d2 e1d2 a6e2=-349
		e5g4 b4c3 g4f6 g7f6 e2a6 c3d2=-410
		e5g6 a6e2 c3e2 f7g6 g2h3 e6d5=-290
		f3d3 a6d3 c3b5 d3e2 e1e2 h3g2=-1066
		f3e3 b4c3 e2a6 h3g2 h1g1 c3d2=-545
		f3f4 b4c3 e2a6 h3g2 h1g1 c3d2=-545
		f3f5 e6f5 e2a6 b4c3 d2c3 e7e5=-1155
		f3f6 g7f6 e2a6 b4c3 d2c3 f6e5=-825
		f3g3 b4c3 e2a6 f6e4 g3f4 c3d2=-525
		f3g4 f6g4 e5g4 h3g2 g4f6 g7f6=-1070
		f3h3 h8h3 g2h3 b4c3 e2a6 c3d2=-695
		f3h5 f6h5 e2a6 b4c3 d2c3 g7e5=-1115
		g2g3 b4c3 e2a6 e6d5 d2c3 e7e5=-410
		g2g4 b4c3 e2a6 e6d5 d2c3 e7e5=-405
		g2h3 b4c3 e2a6 e6d5 d2c3 e7e5=-290
		h1f1 b4c3 e2a6 h3g2 f3g2 c3d2=-400
		h1g1 b4c3 e2a6 e6d5 d2c3 e7e5=-395
		''')
	}

	@Test
	def void k1_7() {
		testEval(testing, 7,-114,'''
		r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
		a1b1 b4c3 d2c3 h3g2 f3g2 a6e2 e1e2=-224
		a1c1 b4c3 d2c3 h3g2 f3g2 a6e2 e1e2=-224
		a1d1 b4c3 d2c3 h3g2 f3g2 a6e2 e1e2=-219
		a2a3 b4c3 d2c3 h3g2 f3g2 a6e2 e1e2=-224
		a2a4 b4c3 d2c3 h3g2 f3g2 a6e2 e1e2=-229
		b2b3 b4c3 d2c3 h3g2 f3g2 a6e2 e1e2=-239
		c3a4 a6e2 e1e2 h3g2 h1e1 b6a4 f3f6=-120
		c3b1 h3g2 h1g1 e6d5 e2a6 e7e5 f3f6=-50
		c3b5 a6b5 e2b5 e6d5 f3f4 f6h5 e4d5=15
		c3d1 h3g2 h1g1 e6d5 e2a6 e7e5 f3f6=-40
		d2c1 b4c3 e2a6 e6d5 f3f4 f6h5 e4d5=-25
		d2e3 b4c3 d5e6 e7e6 e2a6 e6e5 f3f6=-65
		d2f4 b4c3 d5e6 a6e2 e6f7 e8f8 e1e2=-113
		d2g5 b4c3 d5e6 a6e2 e1e2 h3g2 g5f6=-59
		d2h6 a6e2 e1e2 h3g2 h1e1 b4c3 h6g7=-160
		d5d6 c7d6 e2a6 b4c3 d2c3 d6e5 f3f6=-50
		d5e6 e7e6 e2a6 e6e5 c3e2 f6h7 g2h3=85
		e1c1 b4c3 e2a6 h3g2 f3g2 c3d2 c1d2=-239
		e1d1 b4c3 e2a6 h3g2 h1e1 c3d2 f3f6=-210
		e1f1 b4c3 e2a6 c3b2 a1d1 h3g2 f1g2=-153
		e1g1 b4c3 d5e6 c3d2 e2a6 d2d1r f1d1=-109
		e2a6 b4c3 d2c3 h3g2 h1g1 e6d5 f3f6=130
		e2b5 b4c3 b5a6 h3g2 f3g2 c3d2 e1d2=-244
		e2c4 h3g2 f3g2 b6c4 e5c4 a6c4 d5e6=-265
		e2d1 b4c3 d2c3 h3g2 h1g1 e6d5 f3f6=-190
		e2d3 b4c3 d2c3 h3g2 f3g2 a6d3 e5d3=-235
		e2f1 a6f1 d5e6 b4c3 e6f7 e8f8 f3f6=-118
		e5c4 b6c4 e2c4 b4c3 d2c3 a6c4 f3f6=-250
		e5c6 d7c6 d5d6 e7d6 d2f4 e6e5 e2a6=-180
		e5d3 b4c3 d2c3 h3g2 f3g2 a6d3 e2d3=-230
		e5d7 b4c3 d7f6 g7f6 d2c3 a6e2 f3f6=-150
		e5f7 b4c3 f7h8 c3d2 e1d2 a6e2 f3f6=-18
		e5g4 b4c3 d2g5 a6e2 e1e2 h3g2 g4f6=-175
		e5g6 f7g6 d5d6 c7d6 e2a6 b4c3 f3f6=-50
		f3d3 a6d3 d5e6 e7e6 e5d3 b4c3 d2c3=-835
		f3e3 b4c3 d2c3 h3g2 h1g1 a6e2 e1e2=-370
		f3f4 b4c3 d2c3 h3g2 h1g1 a6e2 e1e2=-370
		f3f5 e6f5 e2a6 h3g2 h1g1 b4c3 g1g2=-940
		f3f6 g7f6 d5e6 f6e5 e6f7 e8f7 e2a6=-531
		f3g3 b4c3 d2c3 f6e4 e5g6 g7c3 g3c3=-275
		f3g4 f6g4 e5g4 b4c3 e2a6 c3d2 e1d2=-829
		f3h3 h8h3 d5e6 b4c3 e6f7 e8f8 g2h3=-418
		f3h5 g6h5 d5e6 e7e6 e2a6 e6e5 g2h3=-865
		g2g3 b4c3 d2c3 a6e2 e1e2 f6h7 d5e6=-80
		g2g4 b4c3 d2c3 a6e2 e1e2 f6h7 d5e6=-75
		g2h3 b4c3 d2c3 a6e2 e1e2 f6h7 d5e6=40
		h1f1 b4c3 d2c3 h3g2 f3g2 a6e2 e1e2=-224
		h1g1 b4c3 d2c3 a6e2 e1e2 f6h7 g2h3=-65
		''')
	}

	@AfterClass
	def static void finish() {
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