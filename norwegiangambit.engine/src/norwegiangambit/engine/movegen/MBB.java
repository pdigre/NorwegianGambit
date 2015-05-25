package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBB extends MSlider implements IBlack{

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MBB(from).registerSlider(MOVEDATA.MD_B, DIR_BISHOP, IConst.BB);
	}

	public MBB(int from) {
		super(from);
	}
}
