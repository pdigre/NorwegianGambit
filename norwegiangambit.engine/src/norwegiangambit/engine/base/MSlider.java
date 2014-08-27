package norwegiangambit.engine.base;

import static norwegiangambit.engine.base.BASE.inside;

import java.util.ArrayList;

public abstract class MSlider extends MBase {

	public MSlider(int from) {
		super(from);
	}

	public MOVEDATA[] slide(int type, int offset) {
		ArrayList<MOVEDATA> list = new ArrayList<MOVEDATA>();
		int to = from + offset;
		while (inside(to, to - offset)) {
			long bitmap = BITS.assemble(type, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = (type & 8) > 0?WCAPTURES[i]:BCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to += offset;
		}
		return list.toArray(new MOVEDATA[list.size()]);
	}

	public void bslide(Movegen gen, MOVEDATA[][] mm) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_white;
		for (MOVEDATA[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = m[i + 5].bto;
				if ((occ & bto) != 0) {
					if ((enemy & bto) != 0) {
						int c = gen.ctype(bto);
//						if(c==3 && bto==1L<<IConst.WR_KING_STARTPOS)
//							gen.add(K);
//						else if(c==3 && bto==1L<<IConst.WR_QUEEN_STARTPOS)
//							gen.add(Q);
//						else
							gen.add(m[i + c]);
					}
					break;
				} else {
					gen.add(m[i + 5]);
					i += 6;
				}
			}
		}
	}

	public void wslide(Movegen gen, MOVEDATA[][] mm) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_black;
		for (MOVEDATA[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = m[i + 5].bto;
				if ((occ & bto) != 0) {
					if ((enemy & bto) != 0) {
						int c = gen.ctype(bto);
//						if(c==3 && bto==1L<<IConst.BR_KING_STARTPOS)
//							gen.add(K);
//						else if(c==3 && bto==1L<<IConst.BR_QUEEN_STARTPOS)
//							gen.add(Q);
//						else
							gen.add(m[i + c]);
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
