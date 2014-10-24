package norwegiangambit.engine;

import norwegiangambit.engine.evaluate.AbstractTest;
import norwegiangambit.engine.evaluate.EloTester;
import norwegiangambit.engine.evaluate.IThinker;
import norwegiangambit.engine.movegen.BASE;

import org.junit.BeforeClass;
import org.junit.Test;


public class Test_Elo extends AbstractTest{

	@BeforeClass
	public static void prepare() {
		new BASE();
		setTesting(new EloTester());
	}
	
	@Test
	public void simpleElo_Nxg7() {
		testElo((IThinker) testing, "rq2r1k1/5pp1/p7/4bNP1/1p2P2P/5Q2/PP4K1/5R1R w - - 0 1",5000000);
	}

	@Test
	public void simpleElo_Nb3() {
		testElo((IThinker) testing, "r3kb1r/2p3pp/p1n1p3/pn1P3/8/2q5/P1BN1PPP/R1BQ1RK1 w - - 0 1",500000);
	}

	@Test
	public void mate2_Nb3() {
		testElo((IThinker) testing, "2bqkbn1/2pppp2/np2N3/r3P1p1/p2N2B1/5Q2/PPPPKPP1/RNB2r2 w KQkq - 0 1",500000);
	}

	
}
