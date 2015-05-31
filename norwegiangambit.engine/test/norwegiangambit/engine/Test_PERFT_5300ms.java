package norwegiangambit.engine;

import norwegiangambit.engine.evaluate.FastPerft;
import norwegiangambit.engine.movedata.MOVEDATA;
import norwegiangambit.util.EngineStockfish;
import norwegiangambit.util.PerftTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
public class Test_PERFT_5300ms extends PerftTest{

	@BeforeClass
	public static void prepare() {
		setTesting(new FastPerft(true,true));
		setValidator(new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH));
	}
	
	@Test
	public void m1_1_553() {
		testPerft(testing, 5,4865609,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	
	@Test
	@Ignore
	public void stock_1() {
		testPerft(validator, 5,193690690,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void ng_1() {
		testPerft(testing, 5,193690690,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void m1_FULL6() {
		testPerft(testing, 6,119060324,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void m1_FULL7() {
		testPerft(testing, 7,3195901860L,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void m1_FULL8() {
		testPerft(testing, 8,84998978956L,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void m1_FULL9() {
		testPerft(testing, 9,2439530234167L,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@Test
	public void m2_1_1083() {
		testPerft(testing, 5,3605103,"n1n5/PPPk4/8/8/8/8/4Kppp/5N1N b - - 0 1");
	}
	
	@Test
	public void m3_kiwipete_153() {
		testPerft(testing, 4,4085603,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	public void m3_kiwipete_fast_239() {
		testPerft(testing, 4,4085603, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	public void m4_626() {
		testPerft(testing, 5,674624,"8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1");
	}

	@Test
	public void m5a_835() {
		testPerft(testing, 5,15833292,"r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1");
	}

	@Test
	public void m5b_830() {
		testPerft(testing, 5,15833292,"r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1");
	}
	@Test
	@Ignore
	public void m5b2_830() {
		testPerft(testing, 2,15833292,"r2q1rk1/pP1p2pp/Q4n2/b1p1p3/Npb5/1B3NBn/pPPP1PPP/R3K2R w KQ - 1 2");
	}
	
	@Test
	public void m5c_830_fast() {
		testPerft(testing, 5,15833292,"r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1");
	}

	@Test
	public void e3_err_fast() {
		testPerft(testing, 6,179869,"8/8/8/8/8/8/6k1/4K2R b K - 0 1");
	}
	
	@Test
//	@Ignore
	public void e1_err_fast() {
		testPerft(testing, 6, 119060324, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void e2_err_fast() {
		testPerft(testing, 5, 193690690, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void e56_err_fast() {
		testPerft(testing, 6,525169084,"R6r/8/8/2K5/5k2/8/8/r6R w - - 0 1");
	}

	@Test
	@Ignore
	public void e58_err_fast() {
		testPerft(testing, 6,524966748,"R6r/8/8/2K5/5k2/8/8/r6R b - - 0 1");
	}

	@Test
	public void e121_err_fast() {
		testPerft(testing, 6,28859283,"8/PPPk4/8/8/8/8/4Kppp/8 w - - 0 1");
	}

	@Test
	@Ignore
	public void kp7_err_fast() {
		testPerft(testing, 3,28859283,"r3k2q/p1pp1pb1/bn2Pnp1/4N3/1p2P3/2N5/PPPBBPPP/R3K2R b KQkq - 0 1");
	}

	@Test
	public void e1_m6_fast() {
		testPerft(testing, 6,119060324,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	 
	
	
	@AfterClass
	public static void finish() {
		MOVEDATA.stats();
	}
	

}
