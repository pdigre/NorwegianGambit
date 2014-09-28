package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import static norwegiangambit.engine.movegen.BASE.inside;

import java.util.ArrayList;

import norwegiangambit.util.IConst;


public class MWR extends MSlider{

	final static int[] XQU,XQD,XQL,XQR,XKU,XKD,XKL,XKR;
	final int[] U, D, L, R;
	final int[][] LINE;

	final static MWR[] WR;
	static {
		WR=new MWR[64];
		for (int from = 0; from < 64; from++)
			WR[from] = new MWR(from);
		MWR Q=WR[WR_QUEEN_STARTPOS];
		XQU=castlingRook(Q.U);
		XQD=castlingRook(Q.D);
		XQL=castlingRook(Q.L);
		XQR=castlingRook(Q.R);
		MWR K=WR[WR_KING_STARTPOS];
		XKU=castlingRook(K.U);
		XKD=castlingRook(K.D);
		XKL=castlingRook(K.L);
		XKR=castlingRook(K.R);
	}

	public MWR(int from) {
		super(from);
		U=slide(UP);
		D=slide(DOWN);
		L=slide(LEFT);
		R=slide(RIGHT);
		LINE=new int[][]{U,D, L,R};
	}

	private int[] slide(int offset) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to=from+offset;
		while(inside(to, to-offset)){
			long bitmap = assemble(IConst.WR, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			if(from==IConst.WR_QUEEN_STARTPOS)
				bitmap^= IConst.CANCASTLE_WHITEQUEEN;
			else if(from==IConst.WR_KING_STARTPOS)
				bitmap^= IConst.CANCASTLE_WHITEKING;
			for (int i = 0; i < 5; i++) {
				int c = WCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to+=offset;
		}
		return makeArray(list);
	}

	public void genLegal(Movegen gen){
		wslide(gen,LINE, 3);
	}
	
}
