package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBQ extends MSlider implements IBlack{

	public static MBQ[] MOVES;
	public static void init() {
		MOVES=new MBQ[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MBQ(from);
	}

	public MBQ(int from) {
		super(from);
		registerSlider(MOVEDATA.MD_Q, DIR_QUEEN, IConst.BQ);
	}
}
