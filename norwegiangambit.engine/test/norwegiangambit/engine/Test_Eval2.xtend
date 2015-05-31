package norwegiangambit.engine

import norwegiangambit.engine.evaluate.AbstractTest
import norwegiangambit.engine.evaluate.LongEval
import norwegiangambit.engine.movegen.MBase
import norwegiangambit.util.EngineStockfish
import norwegiangambit.util.IEvalStat
import norwegiangambit.util.PSQT_Cuckoo
import norwegiangambit.util.polyglot.ZobristPolyglot
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import static norwegiangambit.engine.movegen.MBase.*
import norwegiangambit.util.PSQT_Stockfish

class Test_Eval2 extends AbstractTest {
	
	static IEvalStat validator;

	@BeforeClass
	def static void prepare() {
		validator = new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH)
		MBase.zobrist=new ZobristPolyglot
		MBase.psqt=new PSQT_Stockfish
	}
	
	
	@Test
	def void eval_kiwipete() {
		val fen = "rq2r1k1/5pp1/p7/4bNP1/1p2P2P/5Q2/PP4K1/5R1R w - - 0 1";
		testEval(new LongEval(),fen,validator.eval(fen))
	}

	@Test
	def void eval_start1() {
		val fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		testEval(new LongEval(),fen,validator.eval(fen))
	}

	@Test
	def void eval_start2() {
		val fen = "rnbqkbnr/p1pppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		testEval(new LongEval(),fen,validator.eval(fen))
	}

	@Test
	def void eval_start3() {
		val fen = "rnbqkbn1/ppp1pppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQq - 0 1";
		testEval(new LongEval(),fen,validator.eval(fen))
	}

	@Test
	def void eval_total1() {
		val fen = "r3k3/8/8/8/8/8/PPPPPPPP/RNBQKBNR w KQ - 0 1";
		testEval(new LongEval(),fen,validator.eval(fen))
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