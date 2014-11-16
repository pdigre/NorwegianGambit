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
import norwegiangambit.util.EngineStockfish
import norwegiangambit.util.IEvalStat

class Test_Eval1 extends AbstractTest {
	
	static IEvalStat validator;

	@BeforeClass
	def static void prepare() {
		validator = new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH)
		MBase.zobrist=new ZobristPolyglot
		MBase.psqt=new PSQT_Cuckoo
	}
	
	
	@Test
	def void eval_kiwipete() {
		val fen = "rq2r1k1/5pp1/p7/4bNP1/1p2P2P/5Q2/PP4K1/5R1R w - - 0 1";
		testEval(new LongEval(),fen,validator.eval(fen))
	}
	
	@Test
	def void m1() {
		testEval(new LongEval(),'''
		rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		Move PSQT Pawn_W__B <Bishop <--Rook Cstle Trade <--Threat KingS  EG  ADJ  TOT
		a2a3    0   0  0  0  -2  -2 -17 -20  0  0  0  0    0    0  0  0   0    3    3
		a2a4    0   0  0  0  -2  -2 -14 -20  0  0  0  0    0    0  0  0   0    6    6
		b1a3   21   0  0  0  -2  -2 -17 -20  0  0  0  0    0    0  0  0   0    3   24
		b1c3   42   0  0  0  -2  -2 -17 -20  0  0  0  0    0    0  0  0   0    3   45
		b2b3    0   0  0  0   7  -2 -20 -20  0  0  0  0    0    0  0  0   0    9    9
		b2b4    0   0  0  0   7  -2 -20 -20  0  0  0  0    0    0  0  0   0    9    9
		c2c3   -2   0  0  0  -2  -2 -20 -20  0  0  0  0    0    0  0  0   0    0   -2
		c2c4    1   0  0  0  -2  -2 -20 -20  0  0  0  0    0    0  0  0   0    0    1
		d2d3   20   0  0  0  19  -2 -20 -20  0  0  0  0    0    0  0  0   0   21   41
		d2d4   31   0  0  0  19  -2 -20 -20  0  0  0  0    0    0  0  0   0   21   52
		e2e3   20   0  0  0  19  -2 -20 -20  0  0  0  0    0    0  0  0   0   21   41
		e2e4   31   0  0  0  19  -2 -20 -20  0  0  0  0    0    0  0  0   0   21   52
		f2f3   -2   0  0  0  -2  -2 -20 -20  0  0  0  0    0    0  0  0   0    0   -2
		f2f4    1   0  0  0  -2  -2 -20 -20  0  0  0  0    0    0  0  0   0    0    1
		g1f3   42   0  0  0  -2  -2 -17 -20  0  0  0  0    0    0  0  0   0    3   45
		g1h3   21   0  0  0  -2  -2 -17 -20  0  0  0  0    0    0  0  0   0    3   24
		g2g3    0   0  0  0   7  -2 -20 -20  0  0  0  0    0    0  0  0   0    9    9
		g2g4    0   0  0  0   7  -2 -20 -20  0  0  0  0    0    0  0  0   0    9    9
		h2h3    0   0  0  0  -2  -2 -17 -20  0  0  0  0    0    0  0  0   0    3    3
		h2h4    0   0  0  0  -2  -2 -14 -20  0  0  0  0    0    0  0  0   0    6    6
		''')
	}

	@Test
	def void m2_kiwipete() {
		testEval(new LongEval(),'''
		r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1
		Move PSQT Pawn_W__B <Bishop <--Rook Cstle Trade <--Threat KingS  EG  ADJ  TOT
		a1b1   71   0  0  0  44  32  -5   6  0  0  0  0 1425 1522  0  0   0 2948 3019
		a1c1   73   0  0  0  40  32  -5   6  0  0  0  0   52 1552  0  0   0 1601 1674
		a1d1   73   0  0  0  40  32  -5   6  0  0  0  0   52 5620  0  0   0 5669 5742
		a2a3   68   0  0  0  44  32  -2   6  0  0  0  0 1425  208  0  0   0 1637 1705
		a2a4   68   0  0  0  44  32   1   6  0  0  0  0 2817  169  0  0   0 2993 3061
		b2b3   68   0  0  0  44  32  -5   6  0  0  0  0 1425  169  0  0   0 1595 1663
		c3a4   50   0  0  0  51  32  -5   6  0  0  0  0 2817  222  0  0   0 3047 3097
		c3b1   26   0  0  0  51  32 -14   6  0  0  0  0 1425  216  0  0   0 1640 1666
		c3b5   68   0  0  0  43  20  -5   6  0  0  0  0 1439 2988  0  0   0 4439 4507
		c3d1   47   0  0  0  47  32  -8   6  0  0  0  0 1433  232  0  0   0 1666 1713
		d2c1   59   0  0  0  44  32 -11   6  0  0  0  0   74  210  0  0   0  279  338
		d2e3   70   0  0  0  54  32  -5   6  0  0  0  0 1425  183  0  0   0 1619 1689
		d2f4   70   0  0  0  48  32  -5   6  0  0  0  0 1425  155  0  0   0 1585 1655
		d2g5   68   0  0  0  51  32  -5   6  0  0  0  0 1425  183  0  0   0 1616 1684
		d2h6   66   0  0  0  48  32  -5   1  0  0  0  0 1453 1559  0  0   0 3022 3088
		d5d6   76   0  0  0  44  32  -5   6  0  0  0  0 1412 2955  0  0   0 4368 4444
		d5e6  170   0  0  0  44  35  -5   6  0  0  0  0 1398  247  0  0   0 1643 1813
		e1c1   82   0  0  0  40  32  -2   6  0  0  0  0   78 4303  0  0   0 4381 4463
		e1d1   68   0  0  0  44  32  -5   6  0  0  0  0 2819 4273  0  0   0 7093 7161
		e1f1   77   0  0  0  44  32  -5   6  0  0  0  0   78 4322  0-67   0 4334 4411
		e1g1   97   0  0  0  44  32   4   6  0  0  0  0   27  110  0  0   0  147  244
		e2a6  448   0  0  0  47  -6  -5   6  0  0  0 12 1425  169  0  0   0 1648 2096
		e2b5   68   0  0  0  50  20  -5   6  0  0  0  0 1439  208  0  0   0 1666 1734
		e2c4   70   0  0  0  44  24  -5   6  0  0  0  0 4218  169  0  0   0 4396 4466
		e2d1   61   0  0  0  24  35  -8   6  0  0  0  0   66  210  0  0   0  251  312
		e2d3   70   0  0  0  40  28  -5   6  0  0  0  0 1464  169  0  0   0 1634 1704
		e2f1   59   0  0  0  40  35  -8   6  0  0  0  0 1490  218  0  0   0 1699 1758
		e5c4   50   0  0  0  32  24  -5   6  0  0  0  0 4218  105  0  0   0 4320 4370
		e5c6   57   0  0  0  44  32  -5   6  0  0  0  0 1425 2912  0  0   0 4338 4395
		e5d3   47   0  0  0  28  28  -5   6  0  0  0  0 1464  130  0  0   0 1583 1630
		e5d7  122   0  0  0  44  35  -5   6  0  0  0  0 1582  118  0  0   0 1698 1820
		e5f7  130   0  0  0  44  35  -5   6  0  0  0  0 1504  129  0  0   0 1631 1761
		e5g4   47   0  0  0  44  32  -5   6  0  0  0  0 4214  105  0  0   0 4320 4367
		e5g6  145   0  0  0  44  35  -5   6  0  0  0  0 1425 1524  0  0   0 2947 3092
		f3d3   69   0  0  0  40  28  -5   6  0  0  0  0 1464  155  0  0   0 1620 1689
		f3e3   69   0  0  0  36  32  -5   6  0  0  0  0 1425  169  0  0   0 1587 1656
		f3f4   69   0  0  0  40  32  -5   6  0  0  0  0 1425  130  0  0   0 1552 1621
		f3f5   69   0  0  0  52  32  -5   6  0  0  0  0 1425  169  0  0   0 1603 1672
		f3f6  450   0  0  0  52  36  -5   6  0  0  0 12   41 2940  0  0   0 2998 3448
		f3g3   68   0  0  0  52  32  -5   6  0  0  0  0 1425  155  0  0   0 1589 1657
		f3g4   68   0  0  0  47  32  -5   6  0  0  0  0 4214  155  0  0   0 4373 4441
		f3h3  156   0  0  0  52  35  -5  20  0  0  0  0 1464  116  0  0   0 1572 1728
		f3h5   63   0  0  0  50  32  -5   4  0  0  0  0 1453  194  0  0   0 1656 1719
		g2g3   68   0  0  0  44  32  -5   6  0  0  0  0 1425   91  0  0   0 1517 1585
		g2g4   68   0  0  0  44  32  -5   6  0  0  0  0 4214  130  0  0   0 4345 4413
		g2h3  151   0  0  0  44  35  -5  20  0  0  0  0 1464   91  0  0   0 1539 1690
		h1f1   73   0  0  0  40  32  -5   6  0  0  0  0 1425  210  0  0   0 1632 1705
		h1g1   71   0  0  0  44  32  -5   6  0  0  0  0 1425  169  0  0   0 1595 1666
		''')
	}

	@Test
	def void bt2450_f5g7() {
		testEval(new LongEval(),'''
		rq2r1k1/5pp1/p7/4bNP1/1p2P2P/5Q2/PP4K1/5R1R w - - 0 1
		Move PSQT Pawn_W__B <Bishop <--Rook Cstle Trade <--Threat KingS  EG  ADJ  TOT
		a2a3  103   0  0  0   0  18  20  10  0  0  0  0    0   14  0  0   0    6  109
		a2a4  106   0  0  0   0  18  20  10  0  0  0  0    0   14  0  0   0    6  112
		b2b3  103   0  0  0   0  20  20  10  0  0  0  0    0   14  0  0   0    4  107
		f1a1   96   0  0  0   0  18  18  10  0  0  0  0   13    0  0  0   0    3   99
		f1b1   99   0  0  0   0  18  16  10  0  0  0  0    0   14  0  0   0    2  101
		f1c1  101   0  0  0   0  18  46  10  0  0  0  0    0   14  0  0   0   32  133
		f1d1  101   0  0  0   0  18  44  10  0  0  0  0   13    0  0  0   0   29  130
		f1e1  101   0  0  0   0  18  13  10  0  0  0  0    0   14  0  0   0   -1  100
		f1f2  101   0  0  0   0  18  26  10  0  0  0  0    0    0  0  0   0   -2   99
		f1g1   99   0  0  0   0  18   3  10  0  0  0  0    0    0  0  0   0  -25   74
		f3a3   96   0  0  0   0  18  23  10  0  0  0  0    0   28  0  0   0   23  119
		f3b3  101   0  0  0   0  18  23  10  0  0  0  0    0   29  0  0   0   24  125
		f3c3  101   0  0  0   0  16  23  10  0  0  0  0    0   28  0  0   0   25  126
		f3d1   96   0  0  0   0  18  16  10  0  0  0  0   38   14  0  0   0   40  136
		f3d3  102   0  0  0   0  18  23  10  0  0  0  0    0   28  0  0   0   23  125
		f3e2  101   0  0  0   0  18  23  10  0  0  0  0    0   28  0  0   0   23  124
		f3e3  102   0  0  0   0  18  23  10  0  0  0  0    0   14  0  0   0    9  111
		f3f2  101   0  0  0   0  18  18  10  0  0  0  0    0   14  0  0   0    4  105
		f3f4  102   0  0  0   0  13  22  10  0  0  0  0    0   28  0  0   0   27  129
		f3g3  101   0  0  0   0  16  23  10  0  0  0  0    0   28  0  0   0   25  126
		f3g4  101   0  0  0   0  18  23  10  0  0  0  0    0   14  0  0   0    9  110
		f3h3   96   0  0  0   0  18  20  10  0  0  0  0    0   14  0  0   0    6  102
		f3h5   96   0  0  0   0  18  23  10  0  0  0  0    0   29  0  0   0   24  120
		f5d4  113   0  0  0   0  13  20  10  0  0  0  0    0   14  0  0   0   11  124
		f5d6  108   0  0  0   0  16  20  10  0  0  0  0   27   43  0  0   0   64  172
		f5e3   91   0  0  0   0  18  20  10  0  0  0  0    0   14  0  0   0    6   97
		f5e7   91   0  0  0   0  18  20   7  0  0  0  0   14   14  0  0   0   23  114
		f5g3   81   0  0  0   0  16  20  10  0  0  0  0    0   14  0  0   0    8   89
		f5g7  153   0  0  0   0  20  20  10  0  0  0  0   14   28  0  0   0   32  185
		f5h6   70   0  0  0   0  18  20  10  0  0  0  0    0   29  0  0   0   21   91
		g2f2  101   0  0  0   0  18  18  10  0  0  0  0    0   14  0  0   0    4  105
		g2g1  111   0  0  0   0  18  15  10  0  0  0  0    0   14  0-42   0  -41   70
		g2h3   96   0  0  0   0  18  17  10  0  0  0  0    0   14  0  0   0    3   99
		g5g6  109   0  0  0   0  18  20  10  0  0  0  0    0   29  0  0   0   21  130
		h1g1  104   0  0  0   0  18  12  10  0  0  0  0    0   14  0  0   0   -2  102
		h1h2   98   0  0  0   0  18  19  10  0  0  0  0    0   14  0  0   0    5  103
		h1h3   99   0  0  0   0  18  22  10  0  0  0  0    0   14  0  0   0    8  107
		h4h5  108   0  0  0   0  18  23  10  0  0  0  0    0   14  0  0   0    9  117
		''')
	}

	@Test
	def void mate2_Nb3() {
		testEval(new LongEval(),'''
		2bqkbn1/2pppp2/np2N3/r3P1p1/p2N2B1/5Q2/PPPPKPP1/RNB2r2 w KQkq - 0 1
		Move PSQT Pawn_W__B <Bishop <--Rook Cstle Trade <--Threat KingS  EG  ADJ  TOT
		a2a3 -116   0  0  0  14  15  -7   9  0  0  0 -4   46   -9  0  0   0   16 -100
		b1a3  -96   0  0  0  14  15  -7   9  0  0  0 -4   39   -9  0  0   0    9  -87
		b1c3  -75   0  0  0  14  15  -7   9  0  0  0 -4   39   -9  0  0   0    9  -66
		b2b3 -116   0  0  0  23  15 -10   9  0  0  0 -4   46   -9  0  0   0   22  -94
		b2b4 -116   0  0  0  23  15 -10   9  0  0  0 -4   59  -14  0  0   0   30  -86
		c2c3 -118   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -105
		c2c4 -115   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -102
		d2d3  -98   0  0  0  31  15 -10   9  0  0  0 -4   46   -9  0  0   0   30  -68
		d4b3 -148   0  0  0  14  15 -10   9  0  0  0 -4   46  -14  0  0   0    8 -140
		d4b5 -138   0  0  0  14  15 -10   0  0  0  0 -4   36  -14  0  0   0    7 -131
		d4c6 -128   0  0  0  14  15 -10   9  0  0  0 -4   46  -19  0  0   0    3 -125
		d4f5 -128   0  0  0  10  15 -10   9  0  0  0 -4   46   -9  0  0   0    9 -119
		e2d3 -127   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -114
		e2e3 -127   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -114
		e2f1  497   0  0  0  14  15 -10   2  0  0  0 18   39 1548  0  0   0 1592 2089
		e6c5 -123   0  0  0  22  15 -10   3  0  0  0 -4   36  -14  0  0   0   12 -111
		e6c7  -48   0  0  0  22  18 -10   9  0  0  0 -4   31   -9  0  0   0    3  -45
		e6d8 1089   0  0  0  22  15 -10   9  0  0  0 98   41   -4  0  0   0  123 1212
		e6f4 -130   0  0  0  22  15 -10   9  0  0  0 -4   46   -4  0  0   0   26 -104
		e6f8  212   0  0  0  22 -10 -10   9  0  0  0  6   46   -9  0  0   0   56  268
		e6g5  -37   0  0  0  22  18 -10   9  0  0  0 -4   46   -4  0  0   0   23  -14
		e6g7 -165   0  0  0  22  11 -10   9  0  0  0 -4   46   -4  0  0   0   30 -135
		f3a3 -122   0  0  0  18  15 -10   9  0  0  0 -4   46   -9  0  0   0   17 -105
		f3a8 -132   0  0  0  18  15 -10   9  0  0  0 -4   46  -19  0  0   0    7 -125
		f3b3 -117   0  0  0  18  15 -10   9  0  0  0 -4   46  -14  0  0   0   12 -105
		f3b7 -122   0  0  0  18  15 -10   9  0  0  0 -4   41  -29  0  0   0   -8 -130
		f3c3 -117   0  0  0  18  15 -10   9  0  0  0 -4   46  -19  0  0   0    7 -110
		f3c6 -117   0  0  0  18  15 -10   9  0  0  0 -4   46  -24  0  0   0    2 -115
		f3d3 -116   0  0  0  18  15 -10   9  0  0  0 -4   46  -14  0  0   0   12 -104
		f3d5 -116   0  0  0  18  15 -10   6  0  0  0 -4   41  -19  0  0   0    5 -111
		f3e3 -116   0  0  0  18  15 -10   9  0  0  0 -4   46   -9  0  0   0   17  -99
		f3e4 -116   0  0  0  18  15 -10   9  0  0  0 -4   46   -9  0  0   0   17  -99
		f3f4 -116   0  0  0  18  15 -10   9  0  0  0 -4   46   -9  0  0   0   17  -99
		f3f5 -116   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -103
		f3f6 -117   0  0  0  18  15 -10   9  0  0  0 -4   46   -9  0  0   0   17 -100
		f3f7  -22   0  0  0  18  18 -10  21  0  0  0 -4   46   -9  0  0   0    2  -20
		f3g3 -117   0  0  0  18  15 -10   9  0  0  0 -4   46   -9  0  0   0   17 -100
		f3h3 -122   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -109
		g2g3 -116   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -103
		g4f5 -115   0  0  0  26  15 -10   9  0  0  0 -4   46   -9  0  0   0   25  -90
		g4h3 -119   0  0  0  10  15 -10   9  0  0  0 -4   46   -9  0  0   0    9 -110
		g4h5 -119   0  0  0  14  15 -10   9  0  0  0 -4   46   -9  0  0   0   13 -106
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