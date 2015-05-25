package norwegiangambit.engine.movegen;

public abstract class MRook extends MSlider {

	public MRook(int from) {
		super(from);
	}

	public static int[][] cline(int[][] l,long castling) {
		int[][] a = new int[4][];
		for (int i = 0; i < a.length; i++) {
			int[] M = l[i];
			int[] x=new int[M.length];
			for (int i1 = 0; i1 < M.length; i1++)
				x[i1]=MOVEDATAX.create(MOVEDATA.ALL[M[i1]].bitmap^castling,castling);
			a[i]=x;
		}
		return a;
	}

	public int[][] rookSlides(int from) {
		int[][] a = new int[DIR_ROOK.length][];
		for (int i = 0; i < DIR_ROOK.length; i++)
			a[i]=rslide(DIR_ROOK[i],from);
		return a;
	}

	public abstract int[] rslide(int offset, int from);


}
