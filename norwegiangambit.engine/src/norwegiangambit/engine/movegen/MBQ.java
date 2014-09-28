package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import norwegiangambit.util.IConst;


public class MBQ extends MSlider{

	final int[] U,D, L,R,UL,UR,DL,DR;
	final int[][] DIAG,LINE;

	final static MBQ[] BQ;
	static {
		BQ=new MBQ[64];
		for (int from = 0; from < 64; from++)
			BQ[from] = new MBQ(from);
	}

	public MBQ(int from) {
		super(from);
		U=slide(IConst.BQ, UP);
		D=slide(IConst.BQ, DOWN);
		L=slide(IConst.BQ, LEFT);
		R=slide(IConst.BQ, RIGHT);
		UL=slide(IConst.BQ, UP + LEFT);
		UR=slide(IConst.BQ, UP + RIGHT);
		DL=slide(IConst.BQ, DOWN + LEFT);
		DR=slide(IConst.BQ, DOWN + RIGHT);
		DIAG=new int[][]{UL,UR,DL,DR};
		LINE=new int[][]{U,D, L,R};
	}

	public void genLegal(Movegen gen){
		bslide(gen,LINE, 4);
		bslide(gen,DIAG, 4);
	}
	
}
