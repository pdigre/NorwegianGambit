package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.IConst;


public class MBR extends MSlider{

	final static MSlider QLINE,KLINE;
//	final static int Q2, K2;

	final static MBR[] MOVES=new MBR[64];
	static {
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MBR(from);

		MBR q = MOVES[BR_QUEEN_STARTPOS];
		QLINE=new MSliderSpecial();
		QLINE.SLIDES=cline(q.SLIDES,CANCASTLE_BLACKQUEEN);
		q.Q=q.SLIDES[1][39];
		QLINE.Q=MOVEDATAX.create(ALL[q.Q].bitmap^CANCASTLE_BLACKKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);

		MBR k = MOVES[BR_KING_STARTPOS];
		KLINE=new MSliderSpecial();
		KLINE.SLIDES=cline(k.SLIDES,CANCASTLE_BLACKKING);
		k.K=k.SLIDES[1][39];
		KLINE.K=MOVEDATAX.create(ALL[k.K].bitmap^CANCASTLE_BLACKQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
	}

	public static int[][] cline(int[][] l,long castling) {
		return new int[][]{checkRook(l[0],castling),checkRook(l[1],castling), checkRook(l[2],castling),checkRook(l[3],castling)};
	}

	public MBR(int from) {
		super(from);
		SLIDES=new int[][]{slide(UP),slide(DOWN), slide(LEFT),slide(RIGHT)};
	}

	private int[] slide(int offset) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to=from+offset;
		while(inside(to, to-offset)){
			long bitmap = assemble(IConst.BR, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = BCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to+=offset;
		}
		return makeArray(list);
	}
}
