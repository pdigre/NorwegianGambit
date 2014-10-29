package norwegiangambit.engine.movegen;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MWK extends MBase {
	final static int CQ,CK,CQ2,CK2;
	final static int[][] X,XQ,XK; // Castling breakers

	final int[][] M;

	final static MWK[] WK;
	static {
		WK=new MWK[64];
		for (int from = 0; from < 64; from++)
			WK[from] = new MWK(from);
		int[][] M=WK[WK_STARTPOS].M;
		X=castlingKing(M,CANCASTLE_WHITE);
		XQ=castlingKing(M,CANCASTLE_WHITEQUEEN);
		XK=castlingKing(M,CANCASTLE_WHITEKING);
		long cq = assemble(IConst.WK, WK_STARTPOS, WK_STARTPOS - 2, CANCASTLE_BLACK | SPECIAL);
		CQ=MOVEDATAX.create(cq,CANCASTLE_WHITE);
		CQ2=MOVEDATAX.create(cq,CANCASTLE_WHITEQUEEN);
		long ck = assemble(IConst.WK, WK_STARTPOS, WK_STARTPOS + 2, CANCASTLE_BLACK | SPECIAL);
		CK=MOVEDATAX.create(ck,CANCASTLE_WHITE);
		CK2=MOVEDATAX.create(ck,CANCASTLE_WHITEKING);
	}

	public MWK(int from) {
		super(from);			
		M=addMoves(new int[]{UP,DOWN,LEFT,RIGHT,UP + LEFT,UP + RIGHT,DOWN + LEFT,DOWN + RIGHT});
	}

	public int[][] addMoves(int[] mvs) {
		ArrayList<int[]> list=new ArrayList<int[]>();
		for (int i : mvs)
			add(i,list);
		return list.toArray(new int[list.size()][]);
	}

	protected void add(int offset, List<int[]> list) {
		int to = from + offset;
		if (inside(to, from)){
			int[] m=new int[6];
			list.add(m);
			long bitmap = assemble(IConst.WK, from, to, CANCASTLE_BLACK | HALFMOVES);
			m[5]=MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = WCAPTURES[i];
				m[i]=MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}

	public void genLegal(Movegen gen,long mask) {
		long enemy = gen.bb_black;
		long all = gen.bb_piece;
		int[][] mvs = from==WK_STARTPOS?getBreakerMoves(gen,mask):M;
		for (int[] m : mvs){
			long bto = getBTo(m[5]);
			if ((all & bto) == 0) {
				if((bto&mask)!=0L)
					gen.move(m[5]);
			} else {
				if ((enemy & bto & mask) != 0) {
					int c = gen.ctype(bto);
					if(c==3 && bto==1L<<BR_KING_STARTPOS && (gen.castling&CANCASTLE_BLACKKING)!=0)
						gen.move(K);
					else if(c==3 && bto==1L<<BR_QUEEN_STARTPOS && (gen.castling&CANCASTLE_BLACKQUEEN)!=0)
						gen.move(Q);
					else
						gen.move(m[c]);
				}
			}
		}
	}
	
	final private int[][] getBreakerMoves(Movegen gen,long mask) {
		final boolean qc=(gen.castling & CANCASTLE_WHITEQUEEN) != 0;
		final boolean kc=(gen.castling & CANCASTLE_WHITEKING) != 0;
		return qc?(kc?X:XQ):(kc?XK:M);
	}

	final private static long CQ_MASK=(1L<<WK_STARTPOS - 1) | (1L<<WK_STARTPOS - 2);
	final private static long CK_MASK=(1L<<WK_STARTPOS + 1) | (1L<<WK_STARTPOS + 2);
	
	public static void genCastling(Movegen gen,long unsafe) {
		long castling = gen.castling & CANCASTLE_WHITE;
		if ((CWQ & gen.bb_piece) == 0
				&& (castling & CANCASTLE_WHITEQUEEN) != 0
				&& (CQ_MASK&unsafe)==0) {
			gen.move((gen.castling & CANCASTLE_WHITEKING) != 0?CQ:CQ2);
		}
		if ((CWK & gen.bb_piece) == 0
				&& (castling & CANCASTLE_WHITEKING) != 0
				&& (CK_MASK&unsafe)==0) {
			gen.move((gen.castling & CANCASTLE_WHITEQUEEN) != 0?CK:CK2);
		}
	}
}
