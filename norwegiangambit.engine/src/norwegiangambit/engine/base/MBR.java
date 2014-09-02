package norwegiangambit.engine.base;

import static norwegiangambit.engine.base.BASE.DOWN;
import static norwegiangambit.engine.base.BASE.LEFT;
import static norwegiangambit.engine.base.BASE.RIGHT;
import static norwegiangambit.engine.base.BASE.UP;
import static norwegiangambit.engine.base.BASE.inside;
import java.util.ArrayList;

import norwegiangambit.util.IConst;


public class MBR extends MSlider{

	final static MOVEDATA[] XQU,XQD,XQL,XQR,XKU,XKD,XKL,XKR;
	final MOVEDATA[] U, D, L, R;
	final MOVEDATA[][] LINE;

	final static MBR[] BR;
	static {
		BR=new MBR[64];
		for (int from = 0; from < 64; from++)
			BR[from] = new MBR(from);
		MBR Q=BR[BR_QUEEN_STARTPOS];
		XQU=castlingRook(Q.U);
		XQD=castlingRook(Q.D);
		XQL=castlingRook(Q.L);
		XQR=castlingRook(Q.R);
		MBR K=BR[BR_KING_STARTPOS];
		XKU=castlingRook(K.U);
		XKD=castlingRook(K.D);
		XKL=castlingRook(K.L);
		XKR=castlingRook(K.R);
	}

	public MBR(int from) {
		super(from);
		U=slide(UP);
		D=slide(DOWN);
		L=slide(LEFT);
		R=slide(RIGHT);
		LINE=new MOVEDATA[][]{U,D, L,R};
	}

	private MOVEDATA[] slide(int offset) {
		ArrayList<MOVEDATA> list = new ArrayList<MOVEDATA>();
		int to=from+offset;
		while(inside(to, to-offset)){
			long bitmap = PSQT.assemble(IConst.BR, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			if(from==IConst.BR_QUEEN_STARTPOS)
				bitmap^= IConst.CANCASTLE_BLACKQUEEN;
			else if(from==IConst.BR_KING_STARTPOS)
				bitmap^= IConst.CANCASTLE_BLACKKING;
			for (int i = 0; i < 5; i++) {
				int c = BCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to+=offset;
		}
		return list.toArray(new MOVEDATA[list.size()]);
	}

	public void genLegal(Movegen gen){
		bslide(gen,LINE);
	}
	
}
