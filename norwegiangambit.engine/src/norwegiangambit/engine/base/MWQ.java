package norwegiangambit.engine.base;

import static norwegiangambit.engine.base.BASE.DOWN;
import static norwegiangambit.engine.base.BASE.LEFT;
import static norwegiangambit.engine.base.BASE.RIGHT;
import static norwegiangambit.engine.base.BASE.UP;


public class MWQ extends MSlider{

	final MOVEDATA[] U,D, L,R,UL,UR,DL,DR;
	final MOVEDATA[][] DIAG,LINE;

	final static MWQ[] WQ;
	static {
		WQ=new MWQ[64];
		for (int from = 0; from < 64; from++)
			WQ[from] = new MWQ(from);
	}

	public MWQ(int from) {
		super(from);
		U=slide(IConst.WQ, UP);
		D=slide(IConst.WQ, DOWN);
		L=slide(IConst.WQ, LEFT);
		R=slide(IConst.WQ, RIGHT);
		UL=slide(IConst.WQ, UP + LEFT);
		UR=slide(IConst.WQ, UP + RIGHT);
		DL=slide(IConst.WQ, DOWN + LEFT);
		DR=slide(IConst.WQ, DOWN + RIGHT);
		DIAG=new MOVEDATA[][]{UL,UR,DL,DR};
		LINE=new MOVEDATA[][]{U,D, L,R};
	}

	public void genLegal(Movegen gen){
		wslide(gen,LINE);
		wslide(gen,DIAG);
	}
	
}
