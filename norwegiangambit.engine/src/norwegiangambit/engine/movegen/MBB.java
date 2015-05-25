package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBB extends MSlider implements IBlack{

	public static MBB[] MOVES;
	public static void init() {
		MOVES=new MBB[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MBB(from);
	}

	public MBB(int from) {
		super(from);
		SLIDES=new int[][]{slide(IConst.BB, UP + LEFT),slide(IConst.BB, UP + RIGHT),slide(IConst.BB, DOWN + LEFT),slide(IConst.BB, DOWN + RIGHT)};
		register(MOVEDATA.MD_B);
	}
}
