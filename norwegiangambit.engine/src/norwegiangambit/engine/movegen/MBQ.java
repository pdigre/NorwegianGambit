package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBQ extends MSlider{

	final int[][] DIAG,LINE;
	final int QD,KD;

	final static MBQ[] BQ;
	static {
		BQ=new MBQ[64];
		for (int from = 0; from < 64; from++)
			BQ[from] = new MBQ(from);
	}

	public MBQ(int from) {
		super(from);
		DIAG=new int[][]{slide(IConst.BQ, UP + LEFT),slide(IConst.BQ, UP + RIGHT),slide(IConst.BQ, DOWN + LEFT),slide(IConst.BQ, DOWN + RIGHT)};
		QD=Q;
		KD=K;
		LINE=new int[][]{slide(IConst.BQ, UP),slide(IConst.BQ, DOWN), slide(IConst.BQ, LEFT),slide(IConst.BQ, RIGHT)};
	}

	public void genLegal(Movegen gen){
		bslide(gen,DIAG, 4,QD,KD);
		bslide(gen,LINE, 4,Q,K);
	}
	
}
