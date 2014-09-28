package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import norwegiangambit.util.IConst;


public class MWB extends MSlider{

	final int[] UL,UR,DL,DR;
	final int[][] DIAG;

	final static MWB[] WB;
	static {
		WB=new MWB[64];
		for (int from = 0; from < 64; from++)
			WB[from] = new MWB(from);
	}

	public MWB(int from) {
		super(from);
		UL=slide(IConst.WB, UP + LEFT);
		UR=slide(IConst.WB, UP + RIGHT);
		DL=slide(IConst.WB, DOWN + LEFT);
		DR=slide(IConst.WB, DOWN + RIGHT);
		DIAG=new int[][]{UL,UR,DL,DR};
	}

	public void genLegal(Movegen gen){
		wslide(gen,DIAG);
	}
	
}
