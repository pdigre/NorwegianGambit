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
		QLINE.SLIDES=cline(q.SLIDES,CANCASTLE_BLACKQUEEN);
		QLINE.register(MOVEDATA.MD_RQB);
		QLINE.Q=MOVEDATAX.create(MOVEDATA.ALL[q.Q].bitmap^CANCASTLE_BLACKKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);

		MBR k = MOVES[BR_KING_STARTPOS];

		KLINE=new MSliderSpecial();
		KLINE.SLIDES=cline(k.SLIDES,CANCASTLE_BLACKKING);
		KLINE.register(MOVEDATA.MD_RKB);
		KLINE.K=MOVEDATAX.create(MOVEDATA.ALL[k.K].bitmap^CANCASTLE_BLACKQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);

//		MBR q = MOVES[BR_QUEEN_STARTPOS];
//		QLINE=new MSliderSpecial();
//		QLINE.SLIDES=cline2(q,CANCASTLE_BLACKQUEEN,MOVEDATA.MD_RQB,MOVEDATA.MD_RQ,QLINE);
//		QLINE.Q=MOVEDATAX.create2(q.q.bitmap^CANCASTLE_BLACKKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN,MOVEDATA.MD_RQ+84);
//		QLINE.K=MOVEDATA.MD_RQ+85;
//
//		MBR k = MOVES[BR_KING_STARTPOS];
//		KLINE=new MSliderSpecial();
//		KLINE.SLIDES=cline2(k,CANCASTLE_BLACKKING,MOVEDATA.MD_RKB,MOVEDATA.MD_RK,KLINE);
//		KLINE.Q=MOVEDATA.MD_RK+84;
//		KLINE.K=MOVEDATAX.create2(k.k.bitmap^CANCASTLE_BLACKQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING,MOVEDATA.MD_RK+85);
	}

	public MBR(int from) {
		super(from);
	}
}
