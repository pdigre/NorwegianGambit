package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.IConst;

public abstract class MRook extends MSlider {

	public int[][] SLIDES;

	public MRook(int from) {
		super(from);
	}

	public void cline(int[][] l,long castling,int offset, int[] arr) {
		int off = 8*from;
		for (int i = 0; i < 4; i++) {
			int[] M = l[i];
			int[] x=new int[M.length];
			arr[off+i*2]=n-MOVEDATA.color_offset;
			for (int i1 = 0; i1 < M.length; i1++) {
				MOVEDATAX md = new MOVEDATAX(MOVEDATA.ALL[M[i1]].bitmap^castling,castling);
				x[i1]=MOVEDATA.add(md);
				n++;
			}
			arr[off+i*2+1]=n-MOVEDATA.color_offset;
		}
		MOVEDATA.nrm_cnt+=2;
		MOVEDATA.ALL[n] = q;
		MOVEDATA.ALL[n+1] = k;
	}

	public int[][] rookSlides(int from,int type,int[] captures) {
		int[][] a = new int[DIR_ROOK.length][];
		for (int i = 0; i < DIR_ROOK.length; i++)
			a[i]=rslide(DIR_ROOK[i],from,type,captures);
		return a;
	}

	public int[] rslide(int dir,int from,int type,int[] captures) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int to=from+dir;
		while(inside(to, to-dir)){
			long bitmap = assemble(type, from, to, IConst.CASTLING_STATE | IConst.HALFMOVES);
			for (int i = 0; i < 5; i++) {
				int c = captures[i];
				int md = MOVEDATA.capture(bitmap, c);
				list.add(md);
				rookCapture(to, bitmap, c);
			}
			list.add(MOVEDATA.create(bitmap));
			to+=dir;
		}
		return makeArray(list);
	}

	public void register(int[] offsets,int[][] SLIDES) {
		int off = SLIDES.length*2*from;
		for (int i = 0; i < SLIDES.length; i++) {
			offsets[off+i*2]=n-MOVEDATA.color_offset;
			n+=SLIDES[i].length;
			offsets[off+i*2+1]=n-MOVEDATA.color_offset;
		}
		MOVEDATA.nrm_cnt+=2;
		MOVEDATA.ALL[n] = q;
		MOVEDATA.ALL[n+1] = k;
	}


}
