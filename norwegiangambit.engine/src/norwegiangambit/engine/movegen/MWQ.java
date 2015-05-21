package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MWQ extends MSlider{

	public static MWQ[] MOVES;
	public static void init() {
		MOVES=new MWQ[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWQ(from);
	}

	public MWQ(int from) {
		super(from);
		int[][] DIAG=new int[][]{slide(IConst.WQ, UP + LEFT),slide(IConst.WQ, UP + RIGHT),slide(IConst.WQ, DOWN + LEFT),slide(IConst.WQ, DOWN + RIGHT)};
		int[][] LINE=new int[][]{slide(IConst.WQ, UP),slide(IConst.WQ, DOWN), slide(IConst.WQ, LEFT),slide(IConst.WQ, RIGHT)};
		SLIDES=new int[][]{DIAG[0],DIAG[1],DIAG[2],DIAG[3],LINE[0],LINE[1],LINE[2],LINE[3]};
		register();
	}
}
