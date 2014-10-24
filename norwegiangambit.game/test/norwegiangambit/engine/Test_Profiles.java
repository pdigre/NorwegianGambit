package norwegiangambit.engine;

import norwegiangambit.profile.StockfishPlayer2;

import org.junit.Test;

@SuppressWarnings("static-method")
public class Test_Profiles {


	@Test
	public void w1_Qf7_hard_502() {
		RunProfiles.testMove(new StockfishPlayer2(), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}


}
