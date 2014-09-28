package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.inside;

import java.util.ArrayList;

import norwegiangambit.util.IConst;

public abstract class MSlider extends MBase {

	public MSlider(int from) {
		super(from);
	}

	public int[] slide(int type, int offset) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to = from + offset;
		while (inside(to, to - offset)) {
			long bitmap = assemble(type, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = (type & 8) > 0?WCAPTURES[i]:BCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to += offset;
		}
		return makeArray(list);
	}

	public void bslide(Movegen gen, int[][] mm) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_white;
		for (int[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = BASE.getBTo(m[i + 5]);
				if ((occ & bto) != 0) {
					if ((enemy & bto) != 0) {
						int c = gen.ctype(bto);
//						if(c==3 && bto==1L<<IConst.WR_KING_STARTPOS)
//							gen.add(K);
//						else if(c==3 && bto==1L<<IConst.WR_QUEEN_STARTPOS)
//							gen.add(Q);
//						else
							gen.capture(m[i + c]);
					}
					break;
				} else {
					gen.add(m[i + 5]);
					i += 6;
				}
			}
		}
	}

	public void wslide(Movegen gen, int[][] mm) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_black;
		for (int[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = BASE.getBTo(m[i + 5]);
				if ((occ & bto) != 0) {
					if ((enemy & bto) != 0) {
						int c = gen.ctype(bto);
//						if(c==3 && bto==1L<<IConst.BR_KING_STARTPOS)
//							gen.add(K);
//						else if(c==3 && bto==1L<<IConst.BR_QUEEN_STARTPOS)
//							gen.add(Q);
//						else
							gen.capture(m[i + c]);
					}
					break;
				} else {
					gen.add(m[i + 5]);
					i += 6;
				}
			}
		}
	}
}
