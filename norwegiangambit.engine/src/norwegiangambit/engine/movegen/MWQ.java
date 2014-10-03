package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;
import norwegiangambit.util.IConst;


public class MWQ extends MSlider{

	final int[][] DIAG,LINE;
	final int QD,KD;

	final static MWQ[] WQ;
	static {
		WQ=new MWQ[64];
		for (int from = 0; from < 64; from++)
			WQ[from] = new MWQ(from);
	}

	public MWQ(int from) {
		super(from);
//		if(from==45)
//			System.out.println("hi");
		DIAG=new int[][]{slide(IConst.WQ, UP + LEFT),slide(IConst.WQ, UP + RIGHT),slide(IConst.WQ, DOWN + LEFT),slide(IConst.WQ, DOWN + RIGHT)};
		QD=Q;
		KD=K;
		LINE=new int[][]{slide(IConst.WQ, UP),slide(IConst.WQ, DOWN), slide(IConst.WQ, LEFT),slide(IConst.WQ, RIGHT)};
	}

	public void genLegal(Movegen gen){
		wslide(gen,DIAG, 4,QD,KD);
		wslide(gen,LINE, 4,Q,K);
	}
	
}
