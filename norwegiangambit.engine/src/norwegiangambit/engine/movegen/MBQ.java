package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;


public class MBQ extends MSlider implements IBlack{

	public static MBQ[] MOVES;
	public static void init() {
		MOVES=new MBQ[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MBQ(from);
	}

	public MBQ(int from) {
		super(from);
		int[][] DIAG=new int[][]{slide(IConst.BQ, UP + LEFT),slide(IConst.BQ, UP + RIGHT),slide(IConst.BQ, DOWN + LEFT),slide(IConst.BQ, DOWN + RIGHT)};
		int[][] LINE=new int[][]{slide(IConst.BQ, UP),slide(IConst.BQ, DOWN), slide(IConst.BQ, LEFT),slide(IConst.BQ, RIGHT)};
		SLIDES=new int[][]{DIAG[0],DIAG[1],DIAG[2],DIAG[3],LINE[0],LINE[1],LINE[2],LINE[3]};
		register(MOVEDATA.MD_Q);
	}
}
