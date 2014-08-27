package norwegiangambit.engine;

import static org.junit.Assert.assertEquals;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.util.CountForkJoinPool2TT;
import norwegiangambit.engine.util.CountForkJoinPoolTT;
import norwegiangambit.engine.util.CountTT;
import norwegiangambit.engine.util.Counter;
import norwegiangambit.engine.util.PerftResults;

import org.junit.BeforeClass;
import org.junit.Test;

public class Test_TT {

	@BeforeClass
	public static void prepare() {
		PerftResults.readAll();
	}
	
	long total=0L;
	
	@Test
	public void opening_1_1673() {
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		printResults(fen, new CountTT(new StartGame(fen), 6));
	}

	@Test
	public void opening_2_511() {
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		printResults(fen, new CountForkJoinPoolTT(new StartGame(fen), 6));
	}

	@Test
	public void opening_3_384() {
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
		printResults(fen, new CountForkJoinPool2TT(new StartGame(fen), 6));
	}

	public void printResults(String fen, CountTT countTT) {
		Counter[] counters=countTT.compute();
		String actual = "FEN=" + fen + "\r\n" + PerftResults.printCounter(counters);
		String expected = PerftResults.allexpected.get(fen).substring(0, actual.length());
		assertEquals(expected, actual);
		System.out.println(actual);
		System.out.println("*****************************");
		System.out.println("Size:"+CountTT.TTSIZE);
		total =CountTT.hits+CountTT.miss+CountTT.coll;
		stat("Hits", CountTT.hits);
		stat("Miss", CountTT.miss);
		stat("Coll", CountTT.coll);
		stat("Err", CountTT.err);
		System.out.println("Hitrate:" + (1.0*CountTT.hits/(CountTT.miss))+"%");
	}

	public void stat(String name, long i) {
		System.out.println(name+":"+i + " = " + (100.0*i/total)+"%");
	}
}
