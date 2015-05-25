package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.IConst;

public abstract class MRook extends MSlider {

	public MRook(int from) {
		super(from);
	}

	public void cline(int[][] l,long castling,int offset, int[] md) {
		SLIDES = new int[4][];
		for (int i = 0; i < 4; i++) {
			int[] M = l[i];
			int[] x=new int[M.length];
			for (int i1 = 0; i1 < M.length; i1++)
				x[i1]=MOVEDATAX.create(MOVEDATA.ALL[M[i1]].bitmap^castling,castling);
			SLIDES[i]=x;
		}
		B=new int[SLIDES.length];
		E=new int[SLIDES.length];
		int off = SLIDES.length*2*from;
		for (int i = 0; i < SLIDES.length; i++) {
			B[i]=n;
			md[off+i*2]=n-MOVEDATA.color_offset;
			n+=SLIDES[i].length;
			E[i]=n;
			md[off+i*2+1]=n-MOVEDATA.color_offset;
		}
		MOVEDATA.nrm_cnt+=2;
		Q=n;
		MOVEDATA.ALL[n] = q;
		K=n+1;
		MOVEDATA.ALL[n+1] = k;
	}

	public static int[][] cline2(MSlider m,long castling,int[] md,int offset,MRook rook) {
		int[][] a = new int[4][];
		int oi=0;
		rook.B=new int[4];
		rook.E=new int[4];
		for (int i = 0; i < a.length; i++) {
			int[] M = m.SLIDES[i];
			md[oi++]=offset;
			rook.B[i]=offset;
			int[] x=new int[M.length];
			for (int i1 = 0; i1 < M.length; i1++){
				x[i1]=MOVEDATAX.create2(MOVEDATA.ALL[M[i1]].bitmap^castling,castling,offset++);
			}
			a[i]=x;
			md[oi++]=offset;
			rook.E[i]=offset;
		}
		return a;
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


}
