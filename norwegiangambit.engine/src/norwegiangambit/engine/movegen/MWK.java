package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MWK extends MBase {
	final static int CQ,CK;
	final static int[][] X,XQ,XK;

	final int[][] M;

	final static MWK[] WK;
	static {
		WK=new MWK[64];
		for (int from = 0; from < 64; from++)
			WK[from] = new MWK(from);
		int[][] M=WK[IConst.WK_STARTPOS].M;
		X=castlingKing(M,IConst.CANCASTLE_WHITE);
		XQ=castlingKing(M,IConst.CANCASTLE_WHITEQUEEN);
		XK=castlingKing(M,IConst.CANCASTLE_WHITEKING);
		CK=MOVEDATAX.create(assemble(IConst.WK, IConst.WK_STARTPOS, IConst.WK_STARTPOS + 2, IConst.CANCASTLE_BLACK | IConst.SPECIAL));
		CQ=MOVEDATAX.create(assemble(IConst.WK, IConst.WK_STARTPOS, IConst.WK_STARTPOS - 2, IConst.CANCASTLE_BLACK | IConst.SPECIAL));
	}

	public MWK(int from) {
		super(from);			
		ArrayList<int[]> list=new ArrayList<int[]>();
		add(UP,list);
		add(DOWN,list);
		add(LEFT,list);
		add(RIGHT,list);
		add(UP + LEFT,list);
		add(UP + RIGHT,list);
		add(DOWN + LEFT,list);
		add(DOWN + RIGHT,list);
		M=list.toArray(new int[list.size()][]);
	}

	protected void add(int offset, List<int[]> list) {
		int to = from + offset;
		if (BASE.inside(to, from)){
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

	public void genLegal(Movegen gen) {
		kmoves(gen,from==IConst.WK_STARTPOS?getCastlingMoves(gen):M);
	}
	
	public void kmoves(Movegen gen, int[][] moves) {
		long enemy = gen.bb_black;
		long all = gen.bb_piece;
		for (int[] m : moves){
			long bto = BASE.getBTo(m[5]);
			if ((all & bto) == 0) {
				add(gen,m[5]);
			} else {
				if ((enemy & bto) != 0) {
					int c = gen.ctype(bto);
					if(c==3 && bto==1L<<IConst.BR_KING_STARTPOS)
						add(gen,K);
					else if(c==3 && bto==1L<<IConst.BR_QUEEN_STARTPOS)
						add(gen,Q);
					else 
						add(gen,m[c]);
				}
			}
		}
	}

	final static void add(Movegen gen,int md) {
		if(gen.isSafeKingMove(md))
			gen.move(md);
	}
	
	public int[][] getCastlingMoves(Movegen gen) {
		final boolean qc=(gen.castling & IConst.CANCASTLE_WHITEQUEEN) != 0;
		final boolean kc=(gen.castling & IConst.CANCASTLE_WHITEKING) != 0;
		return qc?(kc?X:XQ):(kc?XK:M);
	}

	public static void genCastling(Movegen gen) {
		long castling = gen.castling & IConst.CANCASTLE_WHITE;
		if ((IConst.CWQ & gen.bb_piece) == 0
				&& (castling & IConst.CANCASTLE_WHITEQUEEN) != 0
				&& gen.isSafePos(IConst.WK_STARTPOS - 1)
				&& gen.isSafePos(IConst.WK_STARTPOS - 2)) {
			if(gen.isSafeKingMove(CQ))
				gen.castling(CQ);
		}
		if ((IConst.CWK & gen.bb_piece) == 0
				&& (castling & IConst.CANCASTLE_WHITEKING) != 0
				&& gen.isSafePos(IConst.WK_STARTPOS + 1)
				&& gen.isSafePos(IConst.WK_STARTPOS + 2)) {
			if(gen.isSafeKingMove(CK))
				gen.castling(CK);
		}
	}
}
