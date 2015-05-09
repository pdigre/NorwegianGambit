package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.IConst;

public abstract class MSlider extends MBase {

	int[][] SLIDES;

	public MSlider(int from) {
		super(from);
	}

	public int[] slide(int type, int offset) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to = from + offset;
		while (inside(to, to - offset)) {
			long bitmap = assemble(type, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = (type & 8) > 0?BCAPTURES[i]:WCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to += offset;
		}
		return makeArray(list);
	}
}
