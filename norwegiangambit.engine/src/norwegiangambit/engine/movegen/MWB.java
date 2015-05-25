package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MWB extends MSlider{

	public static MWB[] MOVES;
	public static void init() {
		MOVES=new MWB[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWB(from);
	}

	public MWB(int from) {
		super(from);
		SLIDES=new int[][]{slide(IConst.WB, UP + LEFT),slide(IConst.WB, UP + RIGHT),slide(IConst.WB, DOWN + LEFT),slide(IConst.WB, DOWN + RIGHT)};
		register(MOVEDATA.MD_B);
	}
}
