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
		registerSlider(MOVEDATA.MD_Q, DIR_QUEEN, IConst.WQ);
	}

}
