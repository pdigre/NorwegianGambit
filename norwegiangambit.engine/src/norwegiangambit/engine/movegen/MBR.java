package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.IConst;


public class MBR extends MRook implements IBlack{

	public static MRook QLINE,KLINE;
	public static MBR[] MOVES=new MBR[64];
	public static void init() {
		for (int from = 0; from < 64; from++) {
			MBR m = new MBR(from);
			m.SLIDES=m.rookSlides(from);
			m.register(MOVEDATA.MD_R);
			MOVES[from] = m;
		}

		MBR q = MOVES[BR_QUEEN_STARTPOS];

		QLINE=new MSliderSpecial();
		QLINE.SLIDES=cline(q.SLIDES,CANCASTLE_BLACKQUEEN);
		QLINE.register(MOVEDATA.MD_RQ);
		QLINE.Q=MOVEDATAX.create(MOVEDATA.ALL[q.Q].bitmap^CANCASTLE_BLACKKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);

		MBR k = MOVES[BR_KING_STARTPOS];

		KLINE=new MSliderSpecial();
		KLINE.SLIDES=cline(k.SLIDES,CANCASTLE_BLACKKING);
		KLINE.register(MOVEDATA.MD_RK);
		KLINE.K=MOVEDATAX.create(MOVEDATA.ALL[k.K].bitmap^CANCASTLE_BLACKQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
	}

	public MBR(int from) {
		super(from);
	}

	@Override
	public int[] rslide(int offset,int from) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to=from+offset;
		while(inside(to, to-offset)){
			long bitmap = assemble(IConst.BR, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = BCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture2(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to+=offset;
		}
		return makeArray(list);
	}
}
