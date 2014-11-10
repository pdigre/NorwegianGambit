package norwegiangambit.engine

import norwegiangambit.engine.evaluate.AbstractTest
import norwegiangambit.engine.evaluate.LongEval
import norwegiangambit.engine.movegen.MBase
import norwegiangambit.util.PSQT_Cuckoo
import norwegiangambit.util.polyglot.ZobristPolyglot
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import static norwegiangambit.engine.movegen.MBase.*

class Test_Eval1 extends AbstractTest {

	@BeforeClass
	def static void prepare() {
		MBase.zobrist=new ZobristPolyglot
		MBase.psqt=new PSQT_Cuckoo
	}
	
	@Test
	def void m1() {
		testEval(new LongEval(),'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		b1a3= _< 21 pb  0 tb  0 cb  0 rb  3 bb  0 xb  0 ks  0 eg  0 _>  3 _> 24
		b1c3= _< 42 pb  0 tb  0 cb  0 rb  3 bb  0 xb  0 ks  0 eg  0 _>  3 _> 45
		g1f3= _< 42 pb  0 tb  0 cb  0 rb  3 bb  0 xb  0 ks  0 eg  0 _>  3 _> 45
		g1h3= _< 21 pb  0 tb  0 cb  0 rb  3 bb  0 xb  0 ks  0 eg  0 _>  3 _> 24
		a2a3= _<  0 pb  0 tb  0 cb  0 rb  3 bb  0 xb  0 ks  0 eg  0 _>  3 _>  3
		b2b3= _<  0 pb  0 tb  0 cb  0 rb  0 bb  9 xb  0 ks  0 eg  0 _>  9 _>  9
		c2c3= _< -2 pb  0 tb  0 cb  0 rb  0 bb  0 xb  0 ks  0 eg  0 _>  0 _> -2
		d2d3= _< 20 pb  0 tb  0 cb  0 rb  0 bb 21 xb  0 ks  0 eg  0 _> 21 _> 41
		e2e3= _< 20 pb  0 tb  0 cb  0 rb  0 bb 21 xb  0 ks  0 eg  0 _> 21 _> 41
		f2f3= _< -2 pb  0 tb  0 cb  0 rb  0 bb  0 xb  0 ks  0 eg  0 _>  0 _> -2
		g2g3= _<  0 pb  0 tb  0 cb  0 rb  0 bb  9 xb  0 ks  0 eg  0 _>  9 _>  9
		h2h3= _<  0 pb  0 tb  0 cb  0 rb  3 bb  0 xb  0 ks  0 eg  0 _>  3 _>  3
		a2a4= _<  0 pb  0 tb  0 cb  0 rb  6 bb  0 xb  0 ks  0 eg  0 _>  6 _>  6
		b2b4= _<  0 pb  0 tb  0 cb  0 rb  0 bb  9 xb  0 ks  0 eg  0 _>  9 _>  9
		c2c4= _<  1 pb  0 tb  0 cb  0 rb  0 bb  0 xb  0 ks  0 eg  0 _>  0 _>  1
		d2d4= _< 31 pb  0 tb  0 cb  0 rb  0 bb 21 xb  0 ks  0 eg  0 _> 21 _> 52
		e2e4= _< 31 pb  0 tb  0 cb  0 rb  0 bb 21 xb  0 ks  0 eg  0 _> 21 _> 52
		f2f4= _<  1 pb  0 tb  0 cb  0 rb  0 bb  0 xb  0 ks  0 eg  0 _>  0 _>  1
		g2g4= _<  0 pb  0 tb  0 cb  0 rb  0 bb  9 xb  0 ks  0 eg  0 _>  9 _>  9
		h2h4= _<  0 pb  0 tb  0 cb  0 rb  6 bb  0 xb  0 ks  0 eg  0 _>  6 _>  6
		''')
	}

	@Test
	def void m2_kiwipete() {
		testEval(new LongEval(),'''
		r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
		e2a6= _<448 pb  0 tb 12 cb  0 rb-11 bb 53 xb595 ks  0 eg  0 _>649 _>097
		g2h3= _<151 pb  0 tb  0 cb  0 rb-25 bb  9 xb556 ks  0 eg  0 _>540 _>691
		d5e6= _<170 pb  0 tb  0 cb  0 rb-11 bb  9 xb646 ks  0 eg  0 _>644 _>814
		e5d7= _<122 pb  0 tb  0 cb  0 rb-11 bb  9 xb700 ks  0 eg  0 _>698 _>820
		f3f6= _<450 pb  0 tb 12 cb  0 rb-11 bb 16 xb981 ks  0 eg  0 _>998 _>448
		f3h3= _<156 pb  0 tb  0 cb  0 rb-25 bb 17 xb581 ks  0 eg  0 _>573 _>729
		e5g6= _<145 pb  0 tb  0 cb  0 rb-11 bb  9 xb949 ks  0 eg  0 _>947 _>092
		e5f7= _<130 pb  0 tb  0 cb  0 rb-11 bb  9 xb633 ks  0 eg  0 _>631 _>761
		e5c4= _< 50 pb  0 tb  0 cb  0 rb-11 bb  8 xb323 ks  0 eg  0 _>320 _>370
		e5d3= _< 47 pb  0 tb  0 cb  0 rb-11 bb  0 xb595 ks  0 eg  0 _>584 _>631
		e5g4= _< 47 pb  0 tb  0 cb  0 rb-11 bb 12 xb319 ks  0 eg  0 _>320 _>367
		d2e3= _< 70 pb  0 tb  0 cb  0 rb-11 bb 22 xb609 ks  0 eg  0 _>620 _>690
		d2f4= _< 70 pb  0 tb  0 cb  0 rb-11 bb 16 xb581 ks  0 eg  0 _>586 _>656
		d2g5= _< 68 pb  0 tb  0 cb  0 rb-11 bb 19 xb609 ks  0 eg  0 _>617 _>685
		d2h6= _< 66 pb  0 tb  0 cb  0 rb -6 bb 16 xb012 ks  0 eg  0 _>022 _>088
		d2c1= _< 59 pb  0 tb  0 cb  0 rb-17 bb 12 xb285 ks  0 eg  0 _>280 _>339
		e2d3= _< 70 pb  0 tb  0 cb  0 rb-11 bb 12 xb634 ks  0 eg  0 _>635 _>705
		e2c4= _< 70 pb  0 tb  0 cb  0 rb-11 bb 20 xb388 ks  0 eg  0 _>397 _>467
		e2b5= _< 68 pb  0 tb  0 cb  0 rb-11 bb 30 xb648 ks  0 eg  0 _>667 _>735
		c3d1= _< 47 pb  0 tb  0 cb  0 rb-14 bb 15 xb666 ks  0 eg  0 _>667 _>714
		e2d1= _< 61 pb  0 tb  0 cb  0 rb-14 bb-11 xb277 ks  0 eg  0 _>252 _>313
		e2f1= _< 59 pb  0 tb  0 cb  0 rb-14 bb  5 xb709 ks  0 eg  0 _>700 _>759
		a1b1= _< 71 pb  0 tb  0 cb  0 rb-11 bb 12 xb948 ks  0 eg  0 _>949 _>020
		a1c1= _< 73 pb  0 tb  0 cb  0 rb-11 bb  8 xb605 ks  0 eg  0 _>602 _>675
		a1d1= _< 73 pb  0 tb  0 cb  0 rb-11 bb  8 xb673 ks  0 eg  0 _>670 _>743
		h1g1= _< 71 pb  0 tb  0 cb  0 rb-11 bb 12 xb595 ks  0 eg  0 _>596 _>667
		h1f1= _< 73 pb  0 tb  0 cb  0 rb-11 bb  8 xb636 ks  0 eg  0 _>633 _>706
		f3g4= _< 68 pb  0 tb  0 cb  0 rb-11 bb 15 xb370 ks  0 eg  0 _>374 _>442
		f3h5= _< 63 pb  0 tb  0 cb  0 rb -9 bb 18 xb647 ks  0 eg  0 _>656 _>719
		f3f4= _< 69 pb  0 tb  0 cb  0 rb-11 bb  8 xb556 ks  0 eg  0 _>553 _>622
		f3f5= _< 69 pb  0 tb  0 cb  0 rb-11 bb 20 xb595 ks  0 eg  0 _>604 _>673
		e5c6= _< 57 pb  0 tb  0 cb  0 rb-11 bb 12 xb338 ks  0 eg  0 _>339 _>396
		f3e3= _< 69 pb  0 tb  0 cb  0 rb-11 bb  4 xb595 ks  0 eg  0 _>588 _>657
		f3d3= _< 69 pb  0 tb  0 cb  0 rb-11 bb 12 xb620 ks  0 eg  0 _>621 _>690
		f3g3= _< 68 pb  0 tb  0 cb  0 rb-11 bb 20 xb581 ks  0 eg  0 _>590 _>658
		c3a4= _< 50 pb  0 tb  0 cb  0 rb-11 bb 19 xb040 ks  0 eg  0 _>048 _>098
		e1d1= _< 68 pb  0 tb  0 cb  0 rb-11 bb 12 xb093 ks  0 eg  0 _>094 _>162
		e1f1= _< 77 pb  0 tb  0 cb  0 rb-11 bb 12 xb401 ks-67 eg  0 _>335 _>412
		a2a3= _< 68 pb  0 tb  0 cb  0 rb -8 bb 12 xb634 ks  0 eg  0 _>638 _>706
		b2b3= _< 68 pb  0 tb  0 cb  0 rb-11 bb 12 xb595 ks  0 eg  0 _>596 _>664
		g2g3= _< 68 pb  0 tb  0 cb  0 rb-11 bb 12 xb517 ks  0 eg  0 _>518 _>586
		d5d6= _< 76 pb  0 tb  0 cb  0 rb-11 bb 12 xb368 ks  0 eg  0 _>369 _>445
		a2a4= _< 68 pb  0 tb  0 cb  0 rb -5 bb 12 xb987 ks  0 eg  0 _>994 _>062
		g2g4= _< 68 pb  0 tb  0 cb  0 rb-11 bb 12 xb344 ks  0 eg  0 _>345 _>413
		c3b5= _< 68 pb  0 tb  0 cb  0 rb-11 bb 23 xb428 ks  0 eg  0 _>440 _>508
		c3b1= _< 26 pb  0 tb  0 cb  0 rb-20 bb 19 xb642 ks  0 eg  0 _>641 _>667
		e1c1= _< 82 pb  0 tb  0 cb  0 rb -8 bb  8 xb381 ks  0 eg  0 _>381 _>463
		e1g1= _< 97 pb  0 tb  0 cb  0 rb -2 bb 12 xb138 ks  0 eg  0 _>148 _>245
		''')
	}

	@Test
	def void bt2450_f5g7() {
		testEval(new LongEval(),'''
		rq2r1k1/5pp1/p7/4bNP1/1p2P2P/5Q2/PP4K1/5R1R w - - 0 1
		f5g7= _<153 pb  0 tb  0 cb  0 rb 10 bb-20 xb 43 ks  0 eg  0 _> 33 _>186
		f5e7= _< 91 pb  0 tb  0 cb  0 rb 13 bb-18 xb 29 ks  0 eg  0 _> 24 _>115
		f5h6= _< 70 pb  0 tb  0 cb  0 rb 10 bb-18 xb 29 ks  0 eg  0 _> 21 _> 91
		f5d6= _<108 pb  0 tb  0 cb  0 rb 10 bb-16 xb 70 ks  0 eg  0 _> 64 _>172
		f5d4= _<113 pb  0 tb  0 cb  0 rb 10 bb-13 xb 14 ks  0 eg  0 _> 11 _>124
		f5e3= _< 91 pb  0 tb  0 cb  0 rb 10 bb-18 xb 14 ks  0 eg  0 _>  6 _> 97
		f5g3= _< 81 pb  0 tb  0 cb  0 rb 10 bb-16 xb 14 ks  0 eg  0 _>  8 _> 89
		f1f2= _<101 pb  0 tb  0 cb  0 rb 16 bb-18 xb  0 ks  0 eg  0 _> -2 _> 99
		f1e1= _<101 pb  0 tb  0 cb  0 rb  3 bb-18 xb 14 ks  0 eg  0 _> -1 _>100
		f1d1= _<101 pb  0 tb  0 cb  0 rb 34 bb-18 xb 13 ks  0 eg  0 _> 29 _>130
		f1c1= _<101 pb  0 tb  0 cb  0 rb 36 bb-18 xb 14 ks  0 eg  0 _> 32 _>133
		f1b1= _< 99 pb  0 tb  0 cb  0 rb  6 bb-18 xb 14 ks  0 eg  0 _>  2 _>101
		f1a1= _< 96 pb  0 tb  0 cb  0 rb  8 bb-18 xb 13 ks  0 eg  0 _>  3 _> 99
		f1g1= _< 99 pb  0 tb  0 cb  0 rb -7 bb-18 xb  0 ks  0 eg  0 _>-25 _> 74
		h1h2= _< 98 pb  0 tb  0 cb  0 rb  9 bb-18 xb 14 ks  0 eg  0 _>  5 _>103
		h1h3= _< 99 pb  0 tb  0 cb  0 rb 12 bb-18 xb 14 ks  0 eg  0 _>  8 _>107
		h1g1= _<104 pb  0 tb  0 cb  0 rb  2 bb-18 xb 14 ks  0 eg  0 _> -2 _>102
		f3g4= _<101 pb  0 tb  0 cb  0 rb 13 bb-18 xb 14 ks  0 eg  0 _>  9 _>110
		f3h5= _< 96 pb  0 tb  0 cb  0 rb 13 bb-18 xb 29 ks  0 eg  0 _> 24 _>120
		f3e2= _<101 pb  0 tb  0 cb  0 rb 13 bb-18 xb 28 ks  0 eg  0 _> 23 _>124
		f3d1= _< 96 pb  0 tb  0 cb  0 rb  6 bb-18 xb 53 ks  0 eg  0 _> 41 _>137
		f3f4= _<102 pb  0 tb  0 cb  0 rb 12 bb-13 xb 28 ks  0 eg  0 _> 27 _>129
		f3f2= _<101 pb  0 tb  0 cb  0 rb  8 bb-18 xb 14 ks  0 eg  0 _>  4 _>105
		f3e3= _<102 pb  0 tb  0 cb  0 rb 13 bb-18 xb 14 ks  0 eg  0 _>  9 _>111
		f3d3= _<102 pb  0 tb  0 cb  0 rb 13 bb-18 xb 28 ks  0 eg  0 _> 23 _>125
		f3c3= _<101 pb  0 tb  0 cb  0 rb 13 bb-16 xb 28 ks  0 eg  0 _> 25 _>126
		f3b3= _<101 pb  0 tb  0 cb  0 rb 13 bb-18 xb 29 ks  0 eg  0 _> 24 _>125
		f3a3= _< 96 pb  0 tb  0 cb  0 rb 13 bb-18 xb 28 ks  0 eg  0 _> 23 _>119
		f3g3= _<101 pb  0 tb  0 cb  0 rb 13 bb-16 xb 28 ks  0 eg  0 _> 25 _>126
		f3h3= _< 96 pb  0 tb  0 cb  0 rb 10 bb-18 xb 14 ks  0 eg  0 _>  6 _>102
		g2g1= _<111 pb  0 tb  0 cb  0 rb  5 bb-18 xb 14 ks-42 eg  0 _>-41 _> 70
		g2f2= _<101 pb  0 tb  0 cb  0 rb  8 bb-18 xb 14 ks  0 eg  0 _>  4 _>105
		g2h3= _< 96 pb  0 tb  0 cb  0 rb  7 bb-18 xb 14 ks  0 eg  0 _>  3 _> 99
		a2a3= _<103 pb  0 tb  0 cb  0 rb 10 bb-18 xb 14 ks  0 eg  0 _>  6 _>109
		b2b3= _<103 pb  0 tb  0 cb  0 rb 10 bb-20 xb 14 ks  0 eg  0 _>  4 _>107
		h4h5= _<108 pb  0 tb  0 cb  0 rb 13 bb-18 xb 14 ks  0 eg  0 _>  9 _>117
		g5g6= _<109 pb  0 tb  0 cb  0 rb 10 bb-18 xb 29 ks  0 eg  0 _> 21 _>130
		a2a4= _<106 pb  0 tb  0 cb  0 rb 10 bb-18 xb 14 ks  0 eg  0 _>  6 _>112
		''')
	}

	@Test
	def void mate2_Nb3() {
		testEval(new LongEval(),'''
		2bqkbn1/2pppp2/np2N3/r3P1p1/p2N2B1/5Q2/PPPPKPP1/RNB2r2 w KQkq - 0 1
		e6d8= _<089 pb  0 tb 98 cb  0 rb-19 bb  7 xb 36 ks  0 eg  0 _>122 _>211
		e6f8= _<212 pb  0 tb  6 cb  0 rb-19 bb 32 xb 36 ks  0 eg  0 _> 55 _>267
		e6g5= _<-37 pb  0 tb -4 cb  0 rb-19 bb  4 xb 41 ks  0 eg  0 _> 22 _>-15
		e6c7= _<-48 pb  0 tb -4 cb  0 rb-19 bb  4 xb 22 ks  0 eg  0 _>  3 _>-45
		f3f7= _<-22 pb  0 tb -4 cb  0 rb-31 bb  0 xb 36 ks  0 eg  0 _>  1 _>-21
		d4b3= _<148 pb  0 tb -4 cb  0 rb-19 bb -1 xb 31 ks  0 eg  0 _>  7 _>141
		b1a3= _<-96 pb  0 tb -4 cb  0 rb-16 bb -1 xb 29 ks  0 eg  0 _>  8 _>-88
		b1c3= _<-75 pb  0 tb -4 cb  0 rb-16 bb -1 xb 29 ks  0 eg  0 _>  8 _>-67
		e6g7= _<165 pb  0 tb -4 cb  0 rb-19 bb 11 xb 41 ks  0 eg  0 _> 29 _>136
		d4b5= _<138 pb  0 tb -4 cb  0 rb-10 bb -1 xb 22 ks  0 eg  0 _>  7 _>131
		e6c5= _<123 pb  0 tb -4 cb  0 rb-13 bb  7 xb 21 ks  0 eg  0 _> 11 _>112
		d4c6= _<128 pb  0 tb -4 cb  0 rb-19 bb -1 xb 27 ks  0 eg  0 _>  3 _>125
		e6f4= _<130 pb  0 tb -4 cb  0 rb-19 bb  7 xb 41 ks  0 eg  0 _> 25 _>105
		g4f5= _<115 pb  0 tb -4 cb  0 rb-19 bb 11 xb 36 ks  0 eg  0 _> 24 _>-91
		g4h5= _<119 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>107
		g4h3= _<119 pb  0 tb -4 cb  0 rb-19 bb -5 xb 36 ks  0 eg  0 _>  8 _>111
		f3e4= _<116 pb  0 tb -4 cb  0 rb-19 bb  3 xb 36 ks  0 eg  0 _> 16 _>100
		f3d5= _<116 pb  0 tb -4 cb  0 rb-16 bb  3 xb 21 ks  0 eg  0 _>  4 _>112
		f3c6= _<117 pb  0 tb -4 cb  0 rb-19 bb  3 xb 22 ks  0 eg  0 _>  2 _>115
		f3b7= _<122 pb  0 tb -4 cb  0 rb-19 bb  3 xb 12 ks  0 eg  0 _> -8 _>130
		f3a8= _<132 pb  0 tb -4 cb  0 rb-19 bb  3 xb 26 ks  0 eg  0 _>  6 _>126
		f3f4= _<116 pb  0 tb -4 cb  0 rb-19 bb  3 xb 36 ks  0 eg  0 _> 16 _>100
		f3f5= _<116 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>104
		f3f6= _<117 pb  0 tb -4 cb  0 rb-19 bb  3 xb 36 ks  0 eg  0 _> 16 _>101
		d4f5= _<128 pb  0 tb -4 cb  0 rb-19 bb -5 xb 36 ks  0 eg  0 _>  8 _>120
		f3e3= _<116 pb  0 tb -4 cb  0 rb-19 bb  3 xb 36 ks  0 eg  0 _> 16 _>100
		f3d3= _<116 pb  0 tb -4 cb  0 rb-19 bb  3 xb 31 ks  0 eg  0 _> 11 _>105
		f3c3= _<117 pb  0 tb -4 cb  0 rb-19 bb  3 xb 26 ks  0 eg  0 _>  6 _>111
		f3b3= _<117 pb  0 tb -4 cb  0 rb-19 bb  3 xb 31 ks  0 eg  0 _> 11 _>106
		f3a3= _<122 pb  0 tb -4 cb  0 rb-19 bb  3 xb 36 ks  0 eg  0 _> 16 _>106
		f3g3= _<117 pb  0 tb -4 cb  0 rb-19 bb  3 xb 36 ks  0 eg  0 _> 16 _>101
		f3h3= _<122 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>110
		e2e3= _<127 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>115
		e2d3= _<127 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>115
		e2f1= _<497 pb  0 tb 18 cb  0 rb-12 bb -1 xb587 ks  0 eg  0 _>592 _>089
		a2a3= _<116 pb  0 tb -4 cb  0 rb-16 bb -1 xb 36 ks  0 eg  0 _> 15 _>101
		b2b3= _<116 pb  0 tb -4 cb  0 rb-19 bb  8 xb 36 ks  0 eg  0 _> 21 _>-95
		c2c3= _<118 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>106
		d2d3= _<-98 pb  0 tb -4 cb  0 rb-19 bb 16 xb 36 ks  0 eg  0 _> 29 _>-69
		g2g3= _<116 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>104
		b2b4= _<116 pb  0 tb -4 cb  0 rb-19 bb  8 xb 45 ks  0 eg  0 _> 30 _>-86
		c2c4= _<115 pb  0 tb -4 cb  0 rb-19 bb -1 xb 36 ks  0 eg  0 _> 12 _>103
		''')
	}


	@AfterClass
	def static void finish() {
	}
	
/*
 * Eval testing
 * 
 * 
 */
}