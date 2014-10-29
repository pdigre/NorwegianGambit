package norwegiangambit.engine.movegen;

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
				int c = (type & 8) > 0?BCAPTURES[i]:WCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to += offset;
		}
		return makeArray(list);
	}

	public void bslide(Movegen gen, int[][] mm, int type,int Q, int K, long mask) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_white;
		for (int[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = getBTo(m[i + 5]);
				if ((occ & bto) != 0) {
					if ((enemy & bto & mask) != 0) {
						int c = gen.ctype(bto);
						if(c==3 && bto==1L<<IConst.WR_KING_STARTPOS && (gen.castling&CANCASTLE_WHITEKING)!=0){
							gen.capture(K,type,c, bto);
						}else if(c==3 && bto==1L<<IConst.WR_QUEEN_STARTPOS && (gen.castling&CANCASTLE_WHITEQUEEN)!=0){
							gen.capture(Q,type,c, bto);
						}else{
							gen.capture(m[i + c], type, c, bto);
						}
					}
					break;
				} else {
					if((bto & mask)!=0)
						gen.move(m[i + 5]);
					i += 6;
				}
			}
		}
	}

	public void wslide(Movegen gen, int[][] mm, int type,int Q, int K, long mask) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_black;
		for (int[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = getBTo(m[i + 5]);
				if ((occ & bto) != 0) {
					if ((enemy & bto & mask) != 0) {
						int c = gen.ctype(bto);
						if(c==3 && bto==1L<<IConst.BR_KING_STARTPOS  && (gen.castling&CANCASTLE_BLACKKING)!=0){
							gen.capture(K, type, c, bto);
						}else if(c==3 && bto==1L<<IConst.BR_QUEEN_STARTPOS && (gen.castling&CANCASTLE_BLACKQUEEN)!=0){
							gen.capture(Q, type, c, bto);
						}else{
							gen.capture(m[i + c], type, c, bto);
						}
					}
					break;
				} else {
					if((bto&mask)!=0)
						gen.move(m[i + 5]);
					i += 6;
				}
			}
		}
	}
}
