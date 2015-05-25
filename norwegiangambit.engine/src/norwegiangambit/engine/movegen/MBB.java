package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBB extends MSlider implements IBlack{

	public static MBB[] MOVES;
	public static void init() {
		MOVES=new MBB[64];
		for (int from = 0; from < 64; from++) {
			MBB m = new MBB(from);
			m.registerSlider(MOVEDATA.MD_B, DIR_BISHOP, IConst.BB);
			MOVES[from] = m;
		}
	}

	public MBB(int from) {
		super(from);
	}
}
