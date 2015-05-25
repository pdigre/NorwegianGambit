package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.IConst;

public abstract class MSlider extends MBase {

	public int[][] SLIDES;
	public int[] B;
	public int[] E;
	int n;

	public static int[] DIR_QUEEN=new int[]{UP + LEFT,UP + RIGHT,DOWN + LEFT,DOWN + RIGHT,UP,DOWN,LEFT,RIGHT};
	public static int[] DIR_ROOK=new int[]{UP,DOWN,LEFT,RIGHT};
	public static int[] DIR_BISHOP=new int[]{UP + LEFT,UP + RIGHT,DOWN + LEFT,DOWN + RIGHT};
	
	public void registerSlider(int[] offset, int[] dir, int type) {
		int[][] SLIDES=new int[dir.length][];
		for (int i = 0; i < dir.length; i++) 
			SLIDES[i]=slide(type,dir[i]);
		register(offset,SLIDES);
	}

	public MSlider(int from) {
		super(from);
		n=this instanceof MSliderSpecial?MOVEDATA.brk_cnt:MOVEDATA.nrm_cnt;
	}

	public int[] slide(int type, int offset) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to = from + offset;
		while (inside(to, to - offset)) {
			long bitmap = assemble(type, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = (type & 8) > 0?BCAPTURES[i]:WCAPTURES[i];
				list.add(MOVEDATA.capture(bitmap, c));
				rookCapture2(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to += offset;
		}
		return makeArray(list);
	}

	public void register(int[] offsets) {
		B=new int[SLIDES.length];
		E=new int[SLIDES.length];
		int off = SLIDES.length*2*from;
		for (int i = 0; i < SLIDES.length; i++) {
			B[i]=n;
			offsets[off+i*2]=n-MOVEDATA.color_offset;
			n+=SLIDES[i].length;
			E[i]=n;
			offsets[off+i*2+1]=n-MOVEDATA.color_offset;
		}
		MOVEDATA.nrm_cnt+=2;
		Q=n;
		MOVEDATA.ALL[n] = q;
		K=n+1;
		MOVEDATA.ALL[n+1] = k;
	}

	public void register(int[] offsets,int[][] SLIDES) {
		B=new int[SLIDES.length];
		E=new int[SLIDES.length];
		int off = SLIDES.length*2*from;
		for (int i = 0; i < SLIDES.length; i++) {
			B[i]=n;
			offsets[off+i*2]=n-MOVEDATA.color_offset;
			n+=SLIDES[i].length;
			E[i]=n;
			offsets[off+i*2+1]=n-MOVEDATA.color_offset;
		}
		MOVEDATA.nrm_cnt+=2;
		Q=n;
		MOVEDATA.ALL[n] = q;
		K=n+1;
		MOVEDATA.ALL[n+1] = k;
	}
}
