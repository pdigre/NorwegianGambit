package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.IConst;


public class MWR extends MRook{

	public static MRook QLINE,KLINE;
	public static MWR[] MOVES=new MWR[64];
	public static void init() {
		for (int from = 0; from < 64; from++) {
			MWR m = new MWR(from);
			m.SLIDES=m.rookSlides(from);
			m.register(MOVEDATA.MD_R);
			MOVES[from] = m;
		}

		MWR q = MOVES[WR_QUEEN_STARTPOS];

		QLINE=new MSliderSpecial();
		QLINE.SLIDES=cline(q.SLIDES,CANCASTLE_WHITEQUEEN);
		QLINE.register(MOVEDATA.MD_RQ);
		QLINE.Q=MOVEDATAX.create(MOVEDATA.ALL[q.Q].bitmap^CANCASTLE_WHITEKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);

		MWR k = MOVES[WR_KING_STARTPOS];

		KLINE=new MSliderSpecial();
		KLINE.SLIDES=cline(k.SLIDES,CANCASTLE_WHITEKING);
		KLINE.register(MOVEDATA.MD_RK);
		KLINE.K=MOVEDATAX.create(MOVEDATA.ALL[k.K].bitmap^CANCASTLE_WHITEQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
	}

	public MWR(int from) {
		super(from);
	}

	@Override
	public int[] rslide(int dir,int from) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to=from+dir;
		while(inside(to, to-dir)){
			long bitmap = assemble(IConst.WR, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = WCAPTURES[i];
				int md = MOVEDATA.capture(bitmap, c);
				list.add(md);
				rookCapture2(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to+=dir;
		}
		return makeArray(list);
	}
}
