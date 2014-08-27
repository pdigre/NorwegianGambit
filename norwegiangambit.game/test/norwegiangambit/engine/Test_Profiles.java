package norwegiangambit.engine;

import norwegiangambit.profile.Hard;
import norwegiangambit.profile.P_AlphaBeta;
import norwegiangambit.profile.P_AlphaBetaQ;
import norwegiangambit.profile.P_AlphaBetaTT;
import norwegiangambit.profile.P_MiniMax;
import norwegiangambit.profile.P_PVSQ;

import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("static-method")
public class Test_Profiles {

	@Test
	public void w1_g4_ab4_130() {
		RunProfiles.testMove(new P_AlphaBetaTT(4), "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "g4");
	}

	@Test
	public void w2_g4_ab4_230() {
		RunProfiles.testMove(new P_AlphaBetaTT(4), "rnbqkbnr/pppppppp/8/8/3P4/8/PPP1PPPP/RNBQKBNR b KQkq - 1 2", "g4");
	}

	@Test
	public void w1_g4_ab4_021() {
		RunProfiles.testMove(new P_AlphaBetaTT(4), "8/4p3/8/3P3p/P2pK3/6P1/7b/3k4 w - - 0 1", "g4");
	}

	@Test
	public void b_stupidity_ab4_1335() {
		RunProfiles.testMove(new P_AlphaBetaTT(4), "r1bqk2r/pppp1ppp/2B5/2b1P3/4n3/5N2/PPP2PPP/RNBQK2R b KQkq - 0 6", "f2");
	}

	@Test
	public void w2_Ka6_ab4_17() {
		RunProfiles.testMove(new P_AlphaBetaTT(4), "3B4/8/2B5/1K6/8/8/3p4/3k4 w - - 0 1", "a6");
	}

	@Test
	public void b1_e3_ab4_107() {
		RunProfiles.testMove(new P_AlphaBetaTT(4), "1k1r4/1pp4p/2n5/P6R/2R1p1r1/2P2p2/1PP2B1P/4K3 b - - 0 1", "e3");
	}

	@Test
	public void w1_Qf7_hard_502() {
		RunProfiles.testMove(new Hard(), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

	@Test
	public void w1_Qf7_mm4_7930() {
		RunProfiles.testMove(new P_MiniMax(4), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

	@Test
	public void w1_Qf7_abq4_1538() {
		RunProfiles.testMove(new P_AlphaBetaQ(4), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

	@Test
	public void w1_Qf7_ab4_1969() {
		RunProfiles.testMove(new P_AlphaBeta(4), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

	@Test
	public void w1_Qf7_ab6_1969() {
		RunProfiles.testMove(new P_AlphaBeta(6), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

	@Test
	public void w1_Qf7_abt4_13867() {
		RunProfiles.testMove(new P_AlphaBetaTT(4), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

	@Test @Ignore
	public void w1_Qf7_abt6_1969() {
		RunProfiles.testMove(new P_AlphaBetaTT(6), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

	@Test
	public void w1_Qf7_pv6_9230() {
		RunProfiles.testMove(new P_PVSQ(4), "5r1k/1P4pp/3P1p2/4p3/1P5P/3q2P1/Q2b2K1/B3R3 w - - 0 1", "f7");
	}

}
