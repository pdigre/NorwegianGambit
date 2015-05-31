package norwegiangambit.engine.movedata;

import norwegiangambit.util.IConst;


public class MWB extends MSlider{

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MWB(from).registerSlider(MOVEDATA.MD_B, DIR_BISHOP, IConst.WB);
	}

	public MWB(int from) {
		super(from);
	}
}
