package norwegiangambit.engine.movegen;



public class MWQ extends MSlider{

	public static MWQ[] MOVES;
	public static void init() {
		MOVES=new MWQ[64];
		for (int from = 0; from < 64; from++) {
			MWQ m = new MWQ(from);
			m.registerSlider(MOVEDATA.MD_Q, DIR_QUEEN, WQ);
			MOVES[from] = m;
		}
	}

	public MWQ(int from) {
		super(from);
	}

}
