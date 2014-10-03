package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import static norwegiangambit.engine.movegen.BASE.inside;

import java.util.ArrayList;

import norwegiangambit.util.IConst;


public class MBR extends MSlider{

	final static int[][] QLINE,KLINE;
	final int[][] LINE;
	final static int Q2, K2;

	final static MBR[] BR;
	static {
		BR=new MBR[64];
		for (int from = 0; from < 64; from++)
			BR[from] = new MBR(from);

		MBR q = BR[BR_QUEEN_STARTPOS];
		QLINE=cline(q.LINE,CANCASTLE_BLACKQUEEN);
		q.Q=q.LINE[1][39];
		Q2=MOVEDATAX.create(BASE.ALL[q.Q].bitmap^CANCASTLE_BLACKKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);

		MBR k = BR[BR_KING_STARTPOS];
		KLINE=cline(k.LINE,CANCASTLE_BLACKKING);
		k.K=k.LINE[1][39];
		K2=MOVEDATAX.create(BASE.ALL[k.K].bitmap^CANCASTLE_BLACKQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
	}

	public static int[][] cline(int[][] l,long castling) {
		return new int[][]{checkRook(l[0],castling),checkRook(l[1],castling), checkRook(l[2],castling),checkRook(l[3],castling)};
	}

	public MBR(int from) {
		super(from);
		LINE=new int[][]{slide(UP),slide(DOWN), slide(LEFT),slide(RIGHT)};
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

	public void genLegal(Movegen gen){
		if(from==IConst.BR_QUEEN_STARTPOS){
			if((gen.castling & IConst.CANCASTLE_BLACKQUEEN) != 0 ){
				bslide2(gen,QLINE);
				return;
			}
		} else if(from==IConst.BR_KING_STARTPOS){
			if((gen.castling & IConst.CANCASTLE_BLACKKING) != 0 ){
				bslide2(gen,KLINE);
				return;
			}
		}
		bslide(gen,LINE, 3,Q,K);
	}

	public void bslide2(Movegen gen, int[][] moves) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_white;
		for (int[] m : moves) {
			int i = 0;
			while (i < m.length) {
				long bto = BASE.getBTo(m[i + 5]);
				if ((occ & bto) != 0) {
					if ((enemy & bto) != 0) {
						int c = gen.ctype(bto);
						if(c==3 && bto==1L<<IConst.WR_KING_STARTPOS)
							gen.capture(K2, 3, c);
						else if(c==3 && bto==1L<<IConst.WR_QUEEN_STARTPOS)
							gen.capture(Q2, 3, c);
						else
							gen.capture(m[i + c], 3, c);
					}
					break;
				} else {
					gen.move(m[i + 5]);
					i += 6;
				}
			}
		}
	}

}
