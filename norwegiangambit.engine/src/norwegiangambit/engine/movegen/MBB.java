package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBB extends MSlider{

	final int[][] DIAG;

	final static MBB[] MOVES;
	static {
		MOVES=new MBB[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MBB(from);
	}

	public MBB(int from) {
		super(from);
		DIAG=new int[][]{slide(IConst.BB, UP + LEFT),slide(IConst.BB, UP + RIGHT),slide(IConst.BB, DOWN + LEFT),slide(IConst.BB, DOWN + RIGHT)};
	}

	public void genLegal(Movegen gen,long mask){
		bslide(gen,DIAG, 2,Q,K,mask);
	}
}
