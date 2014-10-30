package norwegiangambit.engine

import norwegiangambit.engine.evaluate.AbstractTest
import norwegiangambit.engine.evaluate.QuiesceTester
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test

class Test_Quiesce1 extends AbstractTest {

	@BeforeClass
	def static void prepare() {
		setTesting(new QuiesceTester(true,true));
	}
	
	@Test
	def void m1_3() {
		testEval(testing, 3,4133,'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		a2a3 b8c6 b1c3=0
		a2a4 b8c6 b1c3=-5
		b1a3 b8c6 g1f3=10
		b1c3 b8c6 g1f3=50
		b2b3 b8c6 b1c3=-15
		b2b4 b8c6 b1c3=-10
		c2c3 b8c6 g1f3=-20
		c2c4 b8c6 b1c3=-10
		d2d3 b8c6 b1c3=20
		d2d4 b8c6 b1c3=40
		e2e3 b8c6 b1c3=20
		e2e4 b8c6 b1c3=40
		f2f3 b8c6 b1c3=-20
		f2f4 b8c6 b1c3=-10
		g1f3 b8c6 b1c3=50
		g1h3 b8c6 b1c3=10
		g2g3 b8c6 b1c3=-15
		g2g4 b8c6 b1c3=-10
		h2h3 b8c6 b1c3=0
		h2h4 b8c6 b1c3=-5
		''');
	}

	@Test
	def void m1_4() {
		testEval(testing, 4,18465,'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		a2a3 e7e5 b1c3 f8a3=-95
		a2a4 b8c6 b1c3 g8f6=-55
		b1a3 b8c6 g1f3 g8f6=-40
		b1c3 b8c6 g1f3 g8f6=0
		b2b3 b8c6 b1c3 g8f6=-65
		b2b4 b8c6 b4b5 g8f6=-105
		c2c3 b8c6 g1f3 g8f6=-70
		c2c4 b8c6 b1c3 g8f6=-60
		d2d3 b8c6 b1c3 g8f6=-30
		d2d4 b8c6 d4d5 g8f6=-55
		e2e3 b8c6 b1c3 g8f6=-30
		e2e4 g8f6 e4e5 b8c6=-55
		f2f3 b8c6 b1c3 g8f6=-70
		f2f4 b8c6 b1c3 g8f6=-60
		g1f3 b8c6 b1c3 g8f6=0
		g1h3 b8c6 b1c3 g8f6=-40
		g2g3 b8c6 b1c3 g8f6=-65
		g2g4 g8f6 b1c3 f6g4=-105
		h2h3 d7d5 b1c3 c8h3=-95
		h2h4 e7e5 h4h5 b8c6=-90
		''');
	}

	@Test
	def void m1_5() {
		testEval(testing, 5,142159,'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		a2a3 b8c6 g1f3 g8f6 b1c3=0
		a2a4 b8c6 g1f3 g8f6 b1c3=-5
		b1a3 b8c6 a3b5 g8f6 b5c7=50
		b1c3 g8f6 c3b5 b8c6 b5c7=50
		b2b3 b8c6 c1b2 d7d5 b2g7=15
		b2b4 e7e6 c1b2 f8b4 b2g7=-25
		c2c3 g8f6 d1b3 b8c6 b3f7=-5
		c2c4 g8f6 d1b3 b8c6 b3b7=5
		d2d3 b8c6 c1f4 g8f6 f4c7=40
		d2d4 b8c6 c1f4 g8f6 f4c7=60
		e2e3 b8c6 d1f3 d7d5 f3d5=60
		e2e4 b8c6 f1b5 c6e5 b5d7=70
		f2f3 b8c6 e2e4 g8f6 b1c3=-30
		f2f4 b8c6 b1c3 g8f6 g1f3=-10
		g1f3 b8c6 f3g5 g8f6 g5f7=50
		g1h3 e7e5 d2d4 e5d4 d1d4=20
		g2g3 g8f6 f1g2 e7e5 g2b7=15
		g2g4 g8f6 g4g5 f6d5 b1c3=-15
		h2h3 b8c6 g1f3 g8f6 b1c3=0
		h2h4 b8c6 g1f3 g8f6 b1c3=-5
		''');
	}

	@Test
	def void m1_6() {
		testEval(testing, 6,801553,'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		a2a3 e7e5 b1c3 b8c6 g1f3 f8a3=-95
		a2a4 e7e5 b1c3 f8b4 c3e4 b4d2=-75
		b1a3 d7d5 g1f3 c8f5 d2d4 f5c2=-60
		b1c3 e7e5 e2e3 f8b4 c3e4 b4d2=-50
		b2b3 e7e5 b1c3 f8b4 c3e4 b4d2=-85
		b2b4 e7e5 b4b5 f8c5 b1c3 c5f2=-115
		c2c3 e7e6 d2d3 d8f6 e2e4 f6f2=-95
		c2c4 d7d5 c4d5 d8d5 d1a4 b8c6=-90
		d2d3 e7e5 e2e3 f8c5 b1c3 c5e3=-70
		d2d4 b8c6 d4d5 c6b4 b1c3 b4d5=-90
		e2e3 e7e5 b1c3 f8b4 c3e4 b4d2=-50
		e2e4 b8c6 b1c3 g8f6 g1f3 f6e4=-90
		f2f3 e7e5 b1c3 d8h4 g2g3 h4g3=-125
		f2f4 d7d5 b1c3 c8f5 g1f3 f5c2=-70
		g1f3 e7e6 d2d4 d8f6 f3d2 f6d4=-70
		g1h3 e7e5 b1c3 d8h4 h3g1 h4f2=-105
		g2g3 e7e5 b1c3 f8b4 c3e4 b4d2=-85
		g2g4 d7d5 e2e3 b8c6 g4g5 g8f6=-125
		h2h3 d7d5 b1c3 b8c6 h3h4 g8f6=-95
		h2h4 e7e5 b1c3 f8b4 c3e4 d8h4=-100
		''')
	}

	@Test
	def void m1_7() {
		testEval(testing, 7,3802460,'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		a2a3 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=50
		a2a4 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=45
		b1a3 e7e5 a3c4 e5e4 c4e5 g8f6 e5f7=55
		b1c3 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=100
		b2b3 b8c6 g1f3 g8f6 c1b2 e7e5 f3e5=40
		b2b4 b8c6 b4b5 c6d4 e2e3 d4f5 b1c3=10
		c2c3 e7e6 d2d4 f8d6 d1b3 g8f6 b3b7=45
		c2c4 e7e6 b1c3 g8f6 d1a4 e6e5 a4a7=50
		d2d3 e7e6 b1c3 f8b4 g1f3 b4c3 b2c3=70
		d2d4 e7e6 b1c3 b8c6 c1f4 g8f6 f4c7=90
		e2e3 b8c6 b1c3 c6b4 f1c4 g8f6 c4f7=95
		e2e4 d7d5 b1c3 d5e4 f1b5 c7c6 c3e4=95
		f2f3 e7e6 b1c3 b8c6 d2d4 g8f6 e2e4=-10
		f2f4 b8c6 g1f3 g8f6 b1c3 d7d6 d2d4=10
		g1f3 g8f6 e2e3 e7e6 f1c4 b8c6 c4e6=70
		g1h3 d7d5 d2d4 c8h3 g2h3 b8c6 b1c3=35
		g2g3 b8c6 b1c3 g8f6 f1g2 d7d5 c3d5=40
		g2g4 d7d6 e2e4 e7e5 f1c4 b8c6 c4f7=40
		h2h3 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=50
		h2h4 e7e6 e2e4 b8c6 f1b5 c6e5 b5d7=45
		''')
	}

	@Test
	def void m1_8() {
		testEval(testing, 8,20795152,'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		a2a3 e7e5 b1c3 b8c6 c3d5 c6d4 e2e4 f8a3=-105
		a2a4 b8c6 e2e3 e7e5 b1c3 f8b4 c3e4 b4d2=-105
		b1a3 e7e5 a3b5 b8c6 e2e3 f8c5 g1f3 c5e3=-95
		b1c3 e7e5 e2e3 d8f6 d1f3 b8c6 f3f6 g8f6=-70
		b2b3 e7e5 e2e3 b8c6 b1c3 f8b4 c3e4 b4d2=-115
		b2b4 e7e5 c1b2 d8h4 b2c3 b8c6 g1f3 h4f2=-145
		c2c3 b8c6 e2e3 g8f6 f1b5 e7e5 g1f3 d7d5=-115
		c2c4 e7e5 b1c3 b8c6 e2e3 f8b4 c3e4 b4d2=-110
		d2d3 e7e5 d3d4 b8c6 d4e5 f8b4 c2c3 c6e5=-95
		d2d4 e7e6 b1c3 d8f6 a2a3 b8c6 e2e4 c6d4=-80
		e2e3 b8c6 g1f3 g8f6 b1c3 f6g4 e3e4 g4f2=-60
		e2e4 e7e5 d2d4 d8h4 d4e5 h4e4 e1d2 e4e5=-87
		f2f3 e7e5 e2e3 b8c6 b1c3 d8h4 e1e2 h4h2=-139
		f2f4 e7e5 e2e3 e5f4 e3f4 d8h4 e1e2 h4f4=-117
		g1f3 e7e6 b1c3 d8f6 c3b5 b8a6 f3g1 f6b2=-100
		g1h3 e7e5 b1c3 d8h4 c3d5 f8d6 h3g1 h4f2=-115
		g2g3 d7d5 e2e3 b8c6 b1c3 c8f5 g1f3 f5c2=-105
		g2g4 d7d5 e2e3 e7e5 b1c3 b8c6 g1f3 c8g4=-135
		h2h3 d7d5 e2e3 e7e5 b1c3 b8c6 g1f3 c8h3=-115
		h2h4 b8c6 b1c3 e7e6 e2e3 f8b4 c3e4 d8h4=-110
		''')
	}

	@Test
	def void m1_9() {
		testEval(testing, 9,93788106L,'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		a2a3 e7e6 b1c3 b8c6 e2e4 c6d4 g1e2 d4e2 f1e2=80
		a2a4 e7e6 b1c3 b8c6 g1f3 g8f6 f3g5 e6e5 g5f7=55
		b1a3 e7e6 a3b5 b8c6 e2e4 d7d5 e4d5 e6d5 b5c7=60
		b1c3 e7e6 e2e3 b8c6 d1g4 g8e7 g1f3 d7d6 g4g7=105
		b2b3 e7e6 c1b2 b8c6 e2e4 d8h4 f1d3 g8e7 b2g7=55
		b2b4 e7e6 b4b5 d7d6 e2e4 d6d5 b1c3 d5e4 c3e4=35
		c2c3 d7d5 g1f3 b8d7 d1a4 c7c6 a4d4 e7e5 f3e5=70
		c2c4 e7e6 b1c3 g8f6 d1a4 f8d6 g1f3 e8g8 a4a7=70
		d2d3 e7e6 g1f3 b8c6 c1d2 d7d6 d2c3 g8e7 c3g7=60
		d2d4 e7e6 d1d3 f8b4 e1d1 g8e7 c1g5 f7f6 g5f6=100
		e2e3 b8c6 b1c3 e7e6 d1g4 g8e7 g1f3 d7d6 g4g7=105
		e2e4 d7d5 b1c3 d5d4 c3d5 c7c6 d5b4 c6c5 g1f3=100
		f2f3 e7e5 b1c3 b8c6 e2e4 d8h4 g2g3 h4f6 d2d4=-5
		f2f4 e7e6 g1f3 g8f6 e2e3 b8c6 f1c4 d7d6 c4e6=40
		g1f3 g8f6 d2d4 e7e6 a2a3 c7c5 c1g5 d8a5 b1c3=95
		g1h3 d7d5 d2d4 g8f6 h3f4 g7g5 f4d3 g5g4 b1c3=60
		g2g3 b8c6 e2e4 e7e6 d1f3 d8f6 b1c3 f6f3 g1f3=55
		g2g4 e7e6 f1g2 d7d5 g1f3 g8f6 f3e5 d5d4 g2b7=55
		h2h3 e7e6 b1c3 b8c6 g1f3 g8f6 f3g5 e6e5 g5f7=60
		h2h4 e7e6 b1c3 b8c6 g1f3 g8f6 f3g5 e6e5 g5f7=55
		h2h4 e7e6 b1c3 g8f6 g1f3 b8c6 f3g5 e6e5 g5f7=55
		''')
	}

	@Ignore
	@Test
	def void bt2450_g5g7() {
		testEval(testing, 7,190,'''
		rq2r1k1/5pp1/p7/4bNP1/1p2P2P/5Q2/PP4K1/5R1R w - - 0 1
		a2a3 b4a3 b2a3 b8b7 f1f2 f7f6 g5g6 a8d8 f5g7=80
		a2a4 b4a3 b2a3 b8b7 f1f2 f7f6 g5g6 a8d8 f5g7=80
		b2b3 b8c7 f5e3 e5d4 e3c4 f7f5 g5f6 g7f6 f3f6=85
		f1a1 e5b2 a1f1 b8c7 f3b3 b2e5 g5g6 a8d8 g6f7=25
		f1b1 b8b5 b1f1 b5c4 b2b3 c4c2 f1f2 c2c5 f5g7=45
		f1c1 e5b2 c1f1 b8c7 f3b3 b2e5 g5g6 a8d8 g6f7=25
		f1d1 b8b5 d1d2 b5a4 h1a1 a4c6 f5e3 b4b3 f3f7=85
		f1e1 b8b6 f3b3 a8d8 e1d1 d8d1 h1d1 b6c6 b3f7=95
		f1f2 b8b7 f5e3 b4b3 a2b3 b7b3 f3f7 b3f7 f2f7=105
		f1g1 e5b2 g1f1 b8c7 f3b3 b2e5 g5g6 a8d8 g6f7=25
		f3a3 b4a3 b2a3 b8b2 f1f2 b2a3 h4h5 a3c5 f5g7=-845
		f3b3 b8b7 g5g6 b7e4 g2h3 e8d8 g6f7 g8f8 b3b4=115
		f3c3 b4c3 b2c3 b8b2 f1f2 b2c3 h4h5 a8d8 f5g7=-850
		f3d1 b8b7 g2f3 a8d8 d1b3 b7b6 f1e1 d8d2 b3f7=84
		f3d3 b8b5 d3c2 b5b6 f1f2 a8b8 c2b3 b8d8 b3f7=85
		f3e2 b8b5 e2c2 b5b6 f1f2 a8b8 c2b3 b8d8 b3f7=85
		f3e3 b8b5 b2b3 a8d8 f1f2 f7f6 g5g6 b5c6 f5g7=65
		f3f2 b8b7 f2f3 a8d8 f1f2 b7c6 f5e3 b4b3 f3f7=80
		f3f4 e5f4 f1e1 b4b3 a2b3 b8b3 e1e2 g7g6 h4h5=-890
		f3g3 e5g3 f5g3 b8c8 f1f2 c8g4 h1e1 g4h4 f2f7=-560
		f3g4 e5b2 g4f3 b8c7 f3b3 b2e5 g5g6 a8d8 g6f7=25
		f3h3 b8b5 h3f3 b5c4 b2b3 c4c2 f1f2 c2c5 f5g7=45
		f3h5 b8c7 f1c1 c7d7 c1c2 d7a4 b2b3 a4b5 h5f7=80
		f5d4 b8c7 d4c6 e8f8 c6e5 c7e5 f3e2 a8d8 f1f7=105
		f5d6 e8e7 d6f7 e5b2 f3h5 g7g6 h5g6 g8f8 g6a6=209
		f5e3 b8c7 e3d5 c7b7 f3h5 e5b2 f1f7 b7c6 f7g7=110
		f5e7 e8e7 g5g6 b8b6 g6f7 g8f8 f3b3 b6g6 g2f2=-170
		f5g3 b8c7 g3e2 c7d7 b2b3 e8e7 h4h5 d7e6 f3f7=70
		f5g7 e8f8 g7f5 e5b2 f3b3 b2h8 g5g6 h8f6 g6f7=160
		f5h6 g7h6 f3f7 g8h8 g5g6 e5g7 f1e1 e8d8 f7g7=140
		g2f2 e5b2 f2g2 b8c7 f3b3 b2e5 g5g6 a8d8 g6f7=25
		g2g1 e5b2 f3f2 b2c3 f5d6 e8e7 f2c5 c3b2 f1f7=30
		g2h3 b8b7 f3b3 g8h8 f1e1 a8d8 h4h5 h8g8 b3f7=83
		g5g6 f7g6 f5g7 e8f8 f3f8 b8f8 f1f8 a8f8 g7e6=10
		h1g1 b8c7 f3b3 c7b7 g2f3 e5h2 g1g4 h2e5 b3f7=94
		h1h2 e5h2 f5g7 e8d8 f3f7 g8h8 g7f5 h2e5 h4h5=-265
		h1h3 b8b7 f1f2 e5b8 h4h5 b8e5 f5e3 b4b3 f3f7=90
		h4h5 g8h8 f3b3 b8d8 g2h3 a8a7 f1g1 h8g8 b3f7=93
		''')
	}

	@Test
	def void mate2_Nb3() {
		testEval(testing, 6,0,'''
		2bqkbn1/2pppp2/np2N3/r3P1p1/p2N2B1/5Q2/PPPPKPP1/RNB2r2 w KQkq - 0 1
		a2a3=0
		b1a3=0
		b1c3=0
		b2b3=0
		b2b4=0
		c2c3 d7e6 e2f1 d8d7 d4c2 a5e5=-26
		c2c4 d7e6 d4c6 c8b7 e2f1 b7c6=-221
		d2d3 d7e6 e2f1 d8d4 f3c6 c8d7=-191
		d4b3=0
		d4b5 d7e6 e2f1 a5b5 f3c6 c8d7=-206
		d4c6 d7e6 e2f1 c8b7 c6d8 b7f3=72
		d4f5=0
		e2d3 d7e6 g4e6 a6c5 d3e2 c8e6=-650
		e2e3 d7e6 e3e2 f1c1 f3c6 c8d7=-685
		e2f1 d7e6 d4c6 c8b7 c6d8 b7f3=72
		e6c5=0
		e6c7 d8c7 e2f1 c7e5 g4d7 c8d7=-120
		e6d8 g8h6 e2f1 e8d8 g4h5 a5e5=912
		e6f4=0
		e6f8=0
		e6g5 a5e5 e2f1 e5g5 g4h3 g5g2=6
		e6g7 f8g7 e2f1 g7e5 d4f5 e5b2=-82
		f3a3 d7e6 d4c6 d8d7 e2f1 d7c6=-216
		f3a8=0
		f3b3=0
		f3b7=0
		f3c3=0
		f3c6=0
		f3d3=0
		f3d5=0
		f3e3=0
		f3e4=0
		f3f4=0
		f3f5 d7e6 f5g5 f1c1 g5g8 d8d4=-660
		f3f6 d7e6 f6g5 f1c1 g5g8 d8d4=-660
		f3f7 e6d8 f7e8 e2f1 e8d8=32000
		f3g3 d7e6 d4c6 d8d7 e2f1 d7c6=-206
		f3h3 d7e6 d4c6 d8d7 e2f1 d7c6=-216
		g2g3=0
		g4f5=0
		g4h3=0
		g4h5 f1e1 e2e1 a5e5 e1f1 d7e6=8
		''')
	}


	@AfterClass
	def static void finish() {
	}
	
/*
 * Horizon moves testing
LVL        MM         AB        PVS    K1-2/2    TT-16M    Quiesce  Winning   Quiesce +qchecks
3        8902       4130       5763      4133      4133       4042       52      4042       76
4      197281      48848      49451     18465     15380      16012      459     16215      528
5    4.865609     474191     361336    142159     71144      56300     4415     60925     4656
6  119.060324   4.486486   2.353697    801553    359250     300886    27227    286225    29563
7 3195.901860  43.546456  14.992705  3.802460  1.300946     875986   103486    865389   119570
8             267.312462  74.805680 20.795152  7.218729   4.397331   578556  4.002961   649541
9                        611.483870 93.788106 27.271052  16.535587 2.698345 15.013641 3.259118

 Movepaths (including quiet checks eval)      plain  qu +qc        
 a2a3 e7e5 b1c3 b8c6 e2e4 g8f6 g1f3 d7d6 d2d4    80  30  20        
 a2a4 e7e5 e2e4 b8c6 b1c3 d7d6 g1f3 g8f6 d2d4    55  25  15        
 b1a3 e7e5 g1f3 b8c6 a3c4 e5e4 f3e5 c6e5 c4e5    60  15  15        
 b1c3 e7e5 g1f3 b8c6 d2d4 e5d4 f3d4 g8f6 e2e4   105  70  50        
 b2b3 e7e5 b1c3 b8c6 e2e4 g8f6 g1f3 d7d6 d2d4    55  15   5        
 b2b4 e7e5 b4b5 d7d5 g1f3 e5e4 f3e5 f7f6 e5g4    35 -25 -25        
 c2c3 e7e6 d2d4 b8c6 g1f3 d7d5 f3g5 g8f6 g5f7    70  10  10        
 c2c4 e7e5 e2e4 b8c6 b1c3 d7d6 g1f3 g8f6 d2d4    70  30  10        
 d2d3 e7e5 g1f3 b8c6 b1c3 f8b4 e2e4 b4c3 b2c3    60  40  40        
 d2d4 e7e6 b1c3 g8f6 g1f3 f8b4 c1f4 b4c3 b2c3   100  60  60        
 e2e3 d7d6 d2d4 b8c6 b1c3 g8f6 g1f3 e7e6 e3e4   105  55  40        
 e2e4 e7e5 g1f3 b8c6 d2d4 e5d4 f3d4 g8f6 b1c3   100  70  50        
 f2f3 e7e5 b1c3 b8c6 e2e4 g8f6 g1e2 c6d4 d2d3    -5 -10 -20        
 f2f4 e7e6 g1f3 b8c6 e2e4 d7d5 b1c3 d5e4 c3e4    40  30  30        
 g1f3 e7e6 b1c3 b8c6 d2d4 f8b4 e2e4 g8f6 c1e3    95  70  65        
 g1h3 e7e5 d2d3 b8c6 h3g5 h7h6 g5f3 d7d6 b1c3    60  30  10        
 g2g3 e7e5 b1c3 b8c6 e2e4 g8f6 g1f3 d7d6 d2d4    55  15   5        
 g2g4 d7d5 e2e3 g8f6 g4g5 f6e4 b1c3 e4c3 d2c3    55 -15 -15        
 h2h3 e7e5 b1c3 b8c6 e2e4 g8f6 g1f3 d7d6 d2d4    60  30  20        
 h2h4 e7e5 g1f3 b8c6 b1c3 g8e7 f3g5 f7f5 g5h7    55  30  30        
 */
}