package norwegiangambit.engine.movegen;

import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public class BASE implements IConst {

	static class SQATK {
		// Reverse lookup for in-check
		public final long RN;
		public final long RB;
		public final long RR;
		public final long RQ;
		public final long RK;

		public SQATK(long rN, long rB, long rR, long rQ, long rK) {
			RN = rN;
			RB = rB;
			RR = rR;
			RQ = rQ;
			RK = rK;
		}
	}

	final public static MOVEDATA[] ALL = new MOVEDATA[50000];

	public static int allw = 0, allb = 0;

	public static int cw = 0, cb = 0, mw = 0, mb = 0, mhw = 0, mhb = 0, ew = 0, eb = 0, cpw = 0, cpb = 0, mpw = 0, mpb = 0,mcw=0,mcb=0;
	public static int cww = 0, cwb = 0,cew = 0, ceb = 0,clw = 0, clb = 0;
	public static int xmw = 0, xmb = 0,xcw = 0, xcb = 0;


	public static void stats() {
		System.out.println("White:"+allw+",Black:"+allb);

		System.out.println("CW:"+cw+",CB:"+cb);
		System.out.println("EW:"+ew+",EB:"+eb);
		System.out.println("CPW:"+cpw+",CPB:"+cpb);

		System.out.println("MW:"+mw+",MB:"+mb);

		System.out.println("MPW:"+mpw+",MPB:"+mpb);
		System.out.println("MCW:"+mcw+",MCB:"+mcb);

		System.out.println("CWW:"+cww+",CWB:"+cwb);
		System.out.println("CEW:"+cew+",CEB:"+ceb);
		System.out.println("CLW:"+clw+",CLB:"+clb);

		System.out.println("XMW:"+xmw+",XMB:"+xmb+" = 1/"+(mb/xmb));
		System.out.println("XCW:"+xcw+",XCB:"+xcb+" = 1/"+(cb/xcb));
	}

	@SuppressWarnings("unchecked")
	public static <X extends MOVEDATA>X add(MOVEDATA md) {
		long bitmap = md.bitmap;
		boolean isWhite = BITS.white(bitmap);
		boolean isPromo = BITS.isPromotion(bitmap);
		int type = BITS.getType(bitmap);
		if (BITS.isCapture(bitmap)) {
			boolean isEnpassant = BITS.isEnpassant(bitmap);
			int captured = BITS.getCapturedType(bitmap);
			
			if (isWhite) {
				cw++;
				if(md instanceof MOVEDATAX)
					xcw++;
				if (isEnpassant)
					ew++;
				if (isPromo)
					cpw++;
				if(captured>type)
					cww++;
				if(captured==type)
					cew++;
				if(captured<type)
					clw++;
			} else {
				cb++;
				if(md instanceof MOVEDATAX)
					xcb++;
				if (isEnpassant)
					eb++;
				if (isPromo)
					cpb++;
				if(captured>type)
					cwb++;
				if(captured==type)
					ceb++;
				if(captured<type)
					clb++;
			}
		} else {
			boolean isCastling = BITS.isCastling(bitmap);
			boolean isHalf=type!=WP&&!isPromo&&!isCastling;
			if (isWhite) {
				mw++;
				if(md instanceof MOVEDATAX)
					xmw++;
				if (isCastling)
					mcw++;
				if (isPromo)
					mpw++;
				if(isHalf)
					mhw++;
			} else {
				mb++;
				if(md instanceof MOVEDATAX)
					xmb++;
				if (isCastling)
					mcb++;
				if (isPromo)
					mpb++;
				if(isHalf)
					mhw++;
			}

		}
		if (isWhite) {
			ALL[allw++] = md;
		} else {
			ALL[25000 + allb++] = md;
		}
		return (X) md;
	}

	final public static int LEFT = -1;
	final public static int RIGHT = 1;
	final public static int UP = 8;
	final public static int DOWN = -8;
	final public static SQATK[] REV;
	final static MOVEDATA[] DATA = new MOVEDATA[40000];
	final static long[] BETWEEN;
	final static int[] DIAGONAL_MOVES = new int[] { UP + LEFT, UP + RIGHT, DOWN + LEFT, DOWN + RIGHT };
	final static int[] LINE_MOVES = new int[] { UP, DOWN, LEFT, RIGHT };
	final static int[] KNIGHT_MOVES = new int[] { UP + LEFT + LEFT, UP + UP + LEFT, UP + RIGHT + RIGHT, UP + UP + RIGHT, DOWN + LEFT + LEFT,
			DOWN + DOWN + LEFT, DOWN + RIGHT + RIGHT, DOWN + DOWN + RIGHT };

	static {
		REV = new SQATK[64];
		for (int from = 0; from < 64; from++) {
			long RB = slide(from, DIAGONAL_MOVES);
			long RR = slide(from, LINE_MOVES);
			long RQ = RB | RR;
			long RK = moves(from, LINE_MOVES) | moves(from, DIAGONAL_MOVES);
			long RN = moves(from, KNIGHT_MOVES);
			REV[from] = new SQATK(RN, RB, RR, RQ, RK);
		}

		BETWEEN = new long[4096];
		fillBetween(DIAGONAL_MOVES);
		fillBetween(LINE_MOVES);
	}

	private static void fillBetween(int[] offset) {
		for (int j = 0; j < offset.length; j++) {
			int off = offset[j];
			for (int from = 0; from < 64; from++) {
				int to = from + off;
				long ray = 0L;
				while (inside(to, to - off)) {
					int num = from + to * 64;
					BETWEEN[num] = ray;
					ray |= (1L << to);
					to += off;
				}
			}
		}
	}

	private static long moves(int from, int[] offset) {
		long ret = 0L;
		for (int i = 0; i < offset.length; i++) {
			int to = from + offset[i];
			if (inside(to, from))
				ret |= 1L << to;
		}
		return ret;
	}

	private static long slide(int from, int[] offset) {
		long ret = 0L;
		for (int i = 0; i < offset.length; i++) {
			int off = offset[i];
			int to = from + off;
			while (inside(to, to - off)) {
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