package norwegiangambit.engine.base;

import static norwegiangambit.engine.base.BASE.DOWN;
import static norwegiangambit.engine.base.BASE.LEFT;
import static norwegiangambit.engine.base.BASE.RIGHT;
import static norwegiangambit.engine.base.BASE.UP;
import norwegiangambit.util.IConst;


public class MBB extends MSlider{

	final MOVEDATA[] UL,UR,DL,DR;
	final MOVEDATA[][] DIAG;

	final static MBB[] BB;
	static {
		BB=new MBB[64];
		for (int from = 0; from < 64; from++)
			BB[from] = new MBB(from);
	}

	public MBB(int from) {
		super(from);
		UL=slide(IConst.BB, UP + LEFT);
		UR=slide(IConst.BB, UP + RIGHT);
		DL=slide(IConst.BB, DOWN + LEFT);
		DR=slide(IConst.BB, DOWN + RIGHT);
		DIAG=new MOVEDATA[][]{UL,UR,DL,DR};
	}

	public void genLegal(Movegen gen){
		bslide(gen,DIAG);
	}
}
