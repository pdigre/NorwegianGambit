package norwegiangambit.engine.base;

import norwegiangambit.util.IConst;

public class BASE implements IConst{
	final public static int LEFT = -1;
	final public static int RIGHT = 1;
	final public static int UP = 8;
	final public static int DOWN = -8;
	final public static SQATK[] REV;
	final static MOVEDATA[] DATA=new MOVEDATA[40000];
	final static long[] BETWEEN;
	final static int[] DIAGONAL_MOVES = new int[]{UP+LEFT,UP+RIGHT,DOWN+LEFT,DOWN+RIGHT};
	final static int[] LINE_MOVES = new int[]{UP,DOWN,LEFT,RIGHT};
	final static int[] KNIGHT_MOVES = new int[]{UP + LEFT + LEFT,UP + UP + LEFT,UP + RIGHT + RIGHT,UP + UP + RIGHT,
		DOWN + LEFT + LEFT,DOWN + DOWN + LEFT,DOWN + RIGHT + RIGHT,DOWN + DOWN + RIGHT};

	static {
		REV = new SQATK[64];
		for (int from = 0; from < 64; from++) {
			long RB = slide(from, BASE.DIAGONAL_MOVES);
			long RR = slide(from, BASE.LINE_MOVES);
			long RQ = RB | RR;
			long RK = moves(from, BASE.LINE_MOVES) | moves(from, BASE.DIAGONAL_MOVES);
			long RN = moves(from, BASE.KNIGHT_MOVES);
			REV[from] = new SQATK(RN, RB, RR, RQ,RK);
		}

		BETWEEN=new long[4096];
		fillBetween(DIAGONAL_MOVES);
		fillBetween(LINE_MOVES);
	}

	private static void fillBetween(int[] offset) {
		for (int j = 0; j < offset.length; j++) {
			int off=offset[j];
			for (int from = 0; from < 64; from++){
				int to=from+off;
				long ray=0L;
				while(inside(to, to-off)){
					int num = from+to*64;
					BETWEEN[num]=ray;
					ray |= (1L<<to);
					to+=off;
				}
			}
		}
	}

	private static long moves(int from, int[] offset) {
		long ret = 0L;
		for (int i = 0; i < offset.length; i++) {
			int to = from + offset[i];
			if (BASE.inside(to, from))
				ret |= 1L << to;
		}
		return ret;
	}

	private static long slide(int from, int[] offset) {
		long ret = 0L;
		for (int i = 0; i < offset.length; i++) {
			int off = offset[i];
			int to = from + off;
			while (BASE.inside(to, to - off)) {
				ret |= (1L << to);
				to += off;
			}
		}
		return ret;
	}
	
	/**
	 * is it inside the board = legal move
	 * 
	 * @param to
	 * @param from
	 * @return
	 */
	static boolean inside(int to, int from) {
		if (to < 0 || to > 63)
			return false;
		int x1 = to % 8;
		int x2 = from % 8;
		if ((x1 < 3 && x2 > 4) || (x2 < 3 && x1 > 4))
			return false;
		return true;
	}

}