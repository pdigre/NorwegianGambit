package norwegiangambit.engine.movedata;



public class MWQ extends MSlider{

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MWQ(from).registerSlider(MOVEDATA.MD_Q, DIR_QUEEN, WQ);
	}

	public MWQ(int from) {
		super(from);
	}

}
