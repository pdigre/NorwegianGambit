package norwegiangambit.engine.movedata;



public class MBQ extends MSlider{

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MBQ(from).registerSlider(MOVEDATA.MD_Q, DIR_QUEEN, BQ);
	}

	public MBQ(int from) {
		super(from);
	}
}
