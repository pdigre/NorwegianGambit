package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MWB extends MSlider{

	final int[][] DIAG;

	final static MWB[] MOVES;
	static {
		MOVES=new MWB[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWB(from);
	}

	public MWB(int from) {
		super(from);
		DIAG=new int[][]{slide(IConst.WB, UP + LEFT),slide(IConst.WB, UP + RIGHT),slide(IConst.WB, DOWN + LEFT),slide(IConst.WB, DOWN + RIGHT)};
	}

	public void genLegal(Movegen gen,long mask){
		wslide(gen,DIAG, 2,Q,K,mask);
	}
	
}
