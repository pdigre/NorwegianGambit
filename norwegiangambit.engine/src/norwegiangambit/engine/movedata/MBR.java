package norwegiangambit.engine.movedata;

import norwegiangambit.util.IConst;


public class MBR extends MRook{

	public int[][] SLIDES;

	public static void init() {
		MBR[] MOVES=new MBR[64];
		for (int from = 0; from < 64; from++) {
			MBR m = new MBR(from);
			m.SLIDES=m.rookSlides(from,IConst.BR,BCAPTURES);
			m.register(MOVEDATA.MD_R,m.SLIDES);
			MOVES[from] = m;
		}

		MBR q = MOVES[BR_QUEEN_STARTPOS];
		MRook QLINE=new MRook(0);
		QLINE.q=new MOVEDATAX(q.q.bitmap^CANCASTLE_BLACKKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);
		QLINE.cline(q.SLIDES,CANCASTLE_BLACKQUEEN,MOVEDATA.MD_RQ,MOVEDATA.MD_RQB);

		MBR k = MOVES[BR_KING_STARTPOS];
		MRook KLINE=new MRook(0);
		KLINE.k=new MOVEDATAX(k.k.bitmap^CANCASTLE_BLACKQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
		KLINE.cline(k.SLIDES,CANCASTLE_BLACKKING,MOVEDATA.MD_RK,MOVEDATA.MD_RKB);
	}

	public MBR(int from) {
		super(from);
	}
}
