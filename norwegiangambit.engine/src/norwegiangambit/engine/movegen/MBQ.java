package norwegiangambit.engine.movegen;



public class MBQ extends MSlider implements IBlack{

	public static MBQ[] MOVES;
	public static void init() {
		MOVES=new MBQ[64];
		for (int from = 0; from < 64; from++) {
			MBQ m = new MBQ(from);
			m.registerSlider(MOVEDATA.MD_Q, DIR_QUEEN, BQ);
			MOVES[from] = m;
		}
	}

	public MBQ(int from) {
		super(from);
	}
}
