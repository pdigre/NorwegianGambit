package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MWR extends MRook{

	public static void init() {
		MWR[] MOVES=new MWR[64];
		MRook QLINE,KLINE;
		for (int from = 0; from < 64; from++) {
			MWR m = new MWR(from);
			m.SLIDES=m.rookSlides(from,IConst.WR,WCAPTURES);
			m.register(MOVEDATA.MD_R,m.SLIDES);
			MOVES[from] = m;
		}

		MWR q = MOVES[WR_QUEEN_STARTPOS];
		QLINE=new MSliderSpecial();
		QLINE.q=new MOVEDATAX(q.q.bitmap^CANCASTLE_WHITEKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);
		QLINE.cline(q.SLIDES,CANCASTLE_WHITEQUEEN,MOVEDATA.MD_RQ,MOVEDATA.MD_RQW);

		MWR k = MOVES[WR_KING_STARTPOS];
		KLINE=new MSliderSpecial();
		KLINE.k=new MOVEDATAX(k.k.bitmap^CANCASTLE_WHITEQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
		KLINE.cline(k.SLIDES,CANCASTLE_WHITEKING,MOVEDATA.MD_RK,MOVEDATA.MD_RKW);
	}

	public MWR(int from) {
		super(from);
	}
}
