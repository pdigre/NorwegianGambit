package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import static norwegiangambit.engine.movegen.BASE.inside;

import java.util.ArrayList;

import norwegiangambit.util.IConst;


public class MWR extends MSlider{

	final static int[][] QLINE,KLINE;
	final int[][] LINE;
	final static int Q2, K2;

	final static MWR[] WR=new MWR[64];
	static {
		for (int from = 0; from < 64; from++)
			WR[from] = new MWR(from);
		
		MWR q = WR[WR_QUEEN_STARTPOS];
		QLINE=cline(q.LINE,CANCASTLE_WHITEQUEEN);
		q.Q=q.LINE[0][39];
		Q2=MOVEDATAX.create(BASE.ALL[q.Q].bitmap^CANCASTLE_WHITEKING,CANCASTLE_BLACKQUEEN|CANCASTLE_WHITEQUEEN);

		MWR k = WR[WR_KING_STARTPOS];
		KLINE=cline(k.LINE,CANCASTLE_WHITEKING);
		k.K=k.LINE[0][39];
		K2=MOVEDATAX.create(BASE.ALL[k.K].bitmap^CANCASTLE_WHITEQUEEN,CANCASTLE_BLACKKING|CANCASTLE_WHITEKING);
	}

	public static int[][] cline(int[][] l,long castling) {
		return new int[][]{checkRook(l[0],castling),checkRook(l[1],castling), checkRook(l[2],castling),checkRook(l[3],castling)};
	}

	public MWR(int from) {
		super(from);
		LINE=new int[][]{slide(UP),slide(DOWN), slide(LEFT),slide(RIGHT)};
	}

	private int[] slide(int offset) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to=from+offset;
		while(inside(to, to-offset)){
			long bitmap = assemble(IConst.WR, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = WCAPTURES[i];
				int md = MOVEDATA.capture(bitmap, c);
				list.add(md);
//				MOVEDATA m=BASE.ALL[md];
//				if(from==7 && to==63)
//					System.out.println("hi");
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to+=offset;
		}
		return makeArray(list);
	}

	public void genLegal(Movegen gen){
		if(from==IConst.WR_QUEEN_STARTPOS){
			if((gen.castling & IConst.CANCASTLE_WHITEQUEEN) != 0 ){
				wslide2(gen,QLINE);
				return;
			}
		} else if(from==IConst.WR_KING_STARTPOS){
			if((gen.castling & IConst.CANCASTLE_WHITEKING) != 0 ){
				wslide2(gen,KLINE);
				return;
			}
		}
		wslide(gen,LINE, 3,Q,K);
	}
	
	public void wslide2(Movegen gen, int[][] moves) {
		long occ = gen.bb_piece;
		long enemy = gen.bb_black;
		for (int[] m : moves) {
			int i = 0;
			while (i < m.length) {
				long bto = BASE.getBTo(m[i + 5]);
				if ((occ & bto) != 0) {
					if ((enemy & bto) != 0) {
						int c = gen.ctype(bto);
						if(c==3 && bto==1L<<IConst.BR_KING_STARTPOS  && (gen.castling&CANCASTLE_BLACKKING)!=0)
							gen.capture(K2, 3, c);
						else if(c==3 && bto==1L<<IConst.BR_QUEEN_STARTPOS && (gen.castling&CANCASTLE_BLACKQUEEN)!=0)
							gen.capture(Q2, 3, c);
						else {
//							MOVEDATA md = BASE.ALL[m[i + c]];
//							if(from==7 && BITS.getTo(md.bitmap)==63)
//								System.out.println("hi");
							gen.capture(m[i + c], 3, c);
						}
					}
					break;
				} else {
//					MOVEDATA md = BASE.ALL[m[i + 5]];
//					if(from==7 && BITS.getTo(md.bitmap)==63)
//						System.out.println("hi");
					gen.move(m[i + 5]);
					i += 6;
				}
			}
		}
	}
}
