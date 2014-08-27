package norwegiangambit.engine.base;

import static norwegiangambit.engine.base.BASE.DOWN;
import static norwegiangambit.engine.base.BASE.LEFT;
import static norwegiangambit.engine.base.BASE.RIGHT;
import static norwegiangambit.engine.base.BASE.UP;


public class MWB extends MSlider{

	final MOVEDATA[] UL,UR,DL,DR;
	final MOVEDATA[][] DIAG;

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
		DIAG=new MOVEDATA[][]{UL,UR,DL,DR};
	}

	public void genLegal(Movegen gen){
		wslide(gen,DIAG);
	}
	
}
