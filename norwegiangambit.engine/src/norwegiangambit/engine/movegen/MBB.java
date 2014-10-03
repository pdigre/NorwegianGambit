package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import norwegiangambit.util.IConst;


public class MBB extends MSlider{

	final int[][] DIAG;

	final static MBB[] BB;
	static {
		BB=new MBB[64];
		for (int from = 0; from < 64; from++)
			BB[from] = new MBB(from);
	}

	public MBB(int from) {
		super(from);
		DIAG=new int[][]{slide(IConst.BB, UP + LEFT),slide(IConst.BB, UP + RIGHT),slide(IConst.BB, DOWN + LEFT),slide(IConst.BB, DOWN + RIGHT)};
	}

	public void genLegal(Movegen gen){
		bslide(gen,DIAG, 2,Q,K);
	}
}
