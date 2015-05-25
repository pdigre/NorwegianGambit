package norwegiangambit.engine.movegen;

public abstract class MRook extends MSlider {

	public MRook(int from) {
		super(from);
	}

	public static int[][] cline(int[][] l,long castling) {
		return new int[][]{checkRook(l[0],castling),checkRook(l[1],castling), checkRook(l[2],castling),checkRook(l[3],castling)};
	}

	public int[][] rookSlides(int from) {
		return new int[][]{rslide(UP,from),rslide(DOWN,from), rslide(LEFT,from),rslide(RIGHT,from)};
	}

	public abstract int[] rslide(int offset, int from);


}
