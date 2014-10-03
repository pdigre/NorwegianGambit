package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import norwegiangambit.util.IConst;


public class MWB extends MSlider{

	final int[][] DIAG;

	final static MWB[] WB;
	static {
		WB=new MWB[64];
		for (int from = 0; from < 64; from++)
			WB[from] = new MWB(from);
	}

	public MWB(int from) {
		super(from);
		DIAG=new int[][]{slide(IConst.WB, UP + LEFT),slide(IConst.WB, UP + RIGHT),slide(IConst.WB, DOWN + LEFT),slide(IConst.WB, DOWN + RIGHT)};
	}

	public void genLegal(Movegen gen){
		wslide(gen,DIAG, 2,Q,K);
	}
	
}
