package norwegiangambit.engine.movegen;

import java.util.ArrayList;

import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public abstract class MBase implements IConst{
	
	final static int[] WPROMOTES=new int[]{IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	final static int[] BPROMOTES=new int[]{IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] WCAPTURES=new int[]{IConst.BP,IConst.BN,IConst.BB,IConst.BR,IConst.BQ};
	final static int[] BCAPTURES=new int[]{IConst.WP,IConst.WN,IConst.WB,IConst.WR,IConst.WQ};
	
	int Q, K; // Break castling
	
	final public int from;
	
	public MBase(int from) {
		this.from = from;
	}

	public abstract void genLegal(Movegen gen,long mask);

	public int[] makeArray(ArrayList<Integer> list) {
		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);
		return arr;
	}

	final static long purge(long bitmap, int subtract) {
		int score = BITS.score(bitmap) - subtract;
		return (((long) score) << 32) | ((int) bitmap);
	}

	public static int[] checkRook(int[] M,long castling) {
		int[] x=new int[M.length];
		for (int i = 0; i < M.length; i++)
			x[i]=MOVEDATAX.create(ALL[M[i]].bitmap^castling,castling);
		return x;
	}

	public static int[][] castlingKing(int[][] M,long castling) {
		int[][] x=new int[M.length][];
		for (int i = 0; i < M.length; i++)
			x[i]=castling(M[i],castling);
		return x;
	}
	
	private static int[] castling(int[] m,long mask) {
		int[] x=new int[m.length];
		for (int i = 0; i < m.length; i++)
			x[i]=MOVEDATAX.create((ALL[m[i]].bitmap),mask);
		return x;
	}

	public void rookCapture(int to, long bitmap, int captured) {
		if (captured == IConst.BR) {
			if (to == IConst.BR_KING_STARTPOS){
				K=MOVEDATAX.capture(bitmap, captured);
			} else if (to == IConst.BR_QUEEN_STARTPOS){
				Q=MOVEDATAX.capture(bitmap,captured);
			}
		}
		if (captured == IConst.WR) {
			if (to == IConst.WR_KING_STARTPOS){
				K=MOVEDATAX.capture(bitmap, captured);
			} else if (to == IConst.WR_QUEEN_STARTPOS){
				Q=MOVEDATAX.capture(bitmap, captured);
			}
		}
	}

	final public static long assemble(int piece, int from, int to, long extra) {
		return (piece << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
	}

	final public static long assemblePromote(int pawn, int promote, int from, int to, long extra) {
		return (promote << IConst._PIECE) | (from << IConst._FROM) | (to << IConst._TO) | extra;
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

	final public static MOVEDATA[] ALL = new MOVEDATA[50000];

	public static int allw = 1, allb = 1;

	public static int cw = 0, cb = 0, mw = 0, mb = 0, mhw = 0, mhb = 0, ew = 0, eb = 0, cpw = 0, cpb = 0, mpw = 0, mpb = 0,mcw=0,mcb=0;
	public static int cww = 0, cwb = 0,cew = 0, ceb = 0,clw = 0, clb = 0;
	public static int xmw = 0, xmb = 0,xcw = 0, xcb = 0;

	final public static long getBTo(int md){
		return ALL[md].bto;
	}
	
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

//		System.out.println("XMW:"+xmw+",XMB:"+xmb+" = 1/"+(mb/xmb));
//		System.out.println("XCW:"+xcw+",XCB:"+xcb+" = 1/"+(cb/xcb));
	}

	public static int add(MOVEDATA md) {
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
//			if(allw==20434)
//				System.out.println("hi");
			ALL[allw++] = md;
			return allw-1;
		} else {
			ALL[25000 + allb++] = md;
			return 25000 + allb-1;
		}
	}

}
