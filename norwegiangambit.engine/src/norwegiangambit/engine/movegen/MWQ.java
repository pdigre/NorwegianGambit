package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import norwegiangambit.util.IConst;


public class MWQ extends MSlider{

	final int[] U,D, L,R,UL,UR,DL,DR;
	final int[][] DIAG,LINE;

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
		DIAG=new int[][]{UL,UR,DL,DR};
		LINE=new int[][]{U,D, L,R};
	}

	public void genLegal(Movegen gen){
		wslide(gen,LINE);
		wslide(gen,DIAG);
	}
	
}
