package norwegiangambit.engine;

import norwegiangambit.engine.base.BASE;
import norwegiangambit.engine.base.MovegenCache;
import norwegiangambit.util.EngineQperft;
import norwegiangambit.util.EngineRoce;
import norwegiangambit.util.EngineStockfish;
import norwegiangambit.util.PerftTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * 
 * 4700ms with improvements in "kingsafe"
 * 4300ms with new MOVEDATA bits
 * 4400ms with pinned pieces - take 1 (3700 p� jobben)
 * 
 */
@SuppressWarnings("static-method")
public class Test_PERFT_5300ms extends PerftTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		setTesting(new RunPerftFast());
//		setValidator(new EngineQperft("C:/chess/perft.exe"));
//		setValidator(new EngineRoce("C:/chess/roce39.exe"));
		setValidator(new EngineStockfish("C:/chess/stockfish.exe"));
		
	}
	
	@Test
	public void m1_1_553() {
		testPerft(new RunPerftFast(), 5,4865609,"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
	}

	@Test
	public void m2_1_1083() {
		testPerft(new RunPerftFast(), 5,3605103,"n1n5/PPPk4/8/8/8/8/4Kppp/5N1N b - - 0 1");
	}
	
	@Test
	public void m2b_1_1083() {
		testPerft(new RunPerftFast(), 3,3605103,"n1R5/PP1k4/1n6/8/8/8/4Kppp/5N1N b - - 2 3");
	}
	
	@Test
	public void m3_kiwipete_153() {
		testPerft(new RunPerftFast(), 4,4085603,"r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	@Ignore
	public void m3b_kiwipete_153() {
		testPerft(new RunPerftFast(), 3,4085603,"r3k2r/p1ppq1b1/bn2pnp1/4N2Q/1p2P3/2N4p/PPPBBPPP/R3K2R b KQkq - 3 4");
	}
	
	@Test
	public void m3_kiwipete_fast_239() {
		testPerft(new RunPerftFast(), 4,4085603, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	public void m4_626() {
		testPerft(new RunPerftFast(), 5,674624,"8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1");
	}

	@Test
	public void m5a_835() {
		testPerft(new RunPerftFast(), 5,15833292,"r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1");
	}

	@Test
	public void m5b_830() {
		testPerft(new RunPerftFast(), 5,15833292,"r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1");
	}
	@Test
	@Ignore
	public void m5b2_830() {
		testPerft(new RunPerftFast(), 2,15833292,"r2q1rk1/pP1p2pp/Q4n2/b1p1p3/Npb5/1B3NBn/pPPP1PPP/R3K2R w KQ - 1 2");
	}
	
	@Test
	public void m5c_830_fast() {
		testPerft(new RunPerftFast(), 5,15833292,"r2q1rk1/pP1p2pp/Q4n2/bbp1p3/Np6/1B3NBn/pPPP1PPP/R3K2R b KQ - 0 1");
	}

	@Test
	public void e3_err_fast() {
		testPerft(new RunPerftFast(), 6,179869,"8/8/8/8/8/8/6k1/4K2R b K - 0 1");
	}

	@Test
	public void e2_err_fast() {
		testPerft(new RunPerftFast(), 5, 193690690, "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
	}

	@Test
	public void e56_err_fast() {
		testPerft(new RunPerftFast(), 6,525169084,"R6r/8/8/2K5/5k2/8/8/r6R w - - 0 1");
	}

	@Test
	public void e58_err_fast() {
		testPerft(new RunPerftFast(), 6,524966748,"R6r/8/8/2K5/5k2/8/8/r6R b - - 0 1");
	}

	@Test
	public void e121_err_fast() {
		testPerft(new RunPerftFast(), 6,28859283,"8/PPPk4/8/8/8/8/4Kppp/8 w - - 0 1");
	}

	@Test
	@Ignore
	public void e121b_err_fast() {
		testPerft(new RunPerftFast(), 3,28859283,"8/PP6/3k4/8/8/8/4Kppp/2Q5 b - - 3 4");
	}

	@AfterClass
	public static void finish() {
		System.out.println(MovegenCache.printStats());
	}
	

}
