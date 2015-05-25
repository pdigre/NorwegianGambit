package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBR extends MRook implements IBlack{

	public static MRook QLINE,KLINE;
	public static MBR[] MOVES=new MBR[64];
	public static void init() {
		for (int from = 0; from < 64; from++) {
			MBR m = new MBR(from);
			m.SLIDES=m.rookSlides(from,IConst.BR,BCAPTURES);
			m.register(MOVEDATA.MD_R);
			MOVES[from] = m;
		}

		MBR q = MOVES[BR_QUEEN_STARTPOS];
		QLINE=new MSliderSpecial();
		QLINE.q=new MOVEDATAX(q.q.bitmap^CANCASTLE_BLACKKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);
		QLINE.cline(q.SLIDES,CANCASTLE_BLACKQUEEN,MOVEDATA.MD_RQ,MOVEDATA.MD_RQB);

		MBR k = MOVES[BR_KING_STARTPOS];
		KLINE=new MSliderSpecial();
		KLINE.k=new MOVEDATAX(k.k.bitmap^CANCASTLE_BLACKQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
		KLINE.cline(k.SLIDES,CANCASTLE_BLACKKING,MOVEDATA.MD_RK,MOVEDATA.MD_RKB);
	}

	public MBR(int from) {
		super(from);
	}
}
