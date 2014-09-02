package norwegiangambit.engine.base;

import static norwegiangambit.engine.base.BASE.DOWN;
import static norwegiangambit.engine.base.BASE.LEFT;
import static norwegiangambit.engine.base.BASE.RIGHT;
import static norwegiangambit.engine.base.BASE.UP;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public class MWK extends MBase {
	final static MOVEDATA CQ,CK;
	final static MOVEDATA[][] X,XQ,XK;

	final MOVEDATA[][] M;

	final static MWK[] WK;
	static {
		WK=new MWK[64];
		for (int from = 0; from < 64; from++)
			WK[from] = new MWK(from);
		MOVEDATA[][] M=WK[IConst.WK_STARTPOS].M;
		X=castlingKing(M,IConst.CANCASTLE_WHITE);
		XQ=castlingKing(M,IConst.CANCASTLE_WHITEQUEEN);
		XK=castlingKing(M,IConst.CANCASTLE_WHITEKING);
		CK=MOVEDATAX.create(PSQT.assemble(IConst.WK, IConst.WK_STARTPOS, IConst.WK_STARTPOS + 2, IConst.CANCASTLE_BLACK | IConst.SPECIAL));
		CQ=MOVEDATAX.create(PSQT.assemble(IConst.WK, IConst.WK_STARTPOS, IConst.WK_STARTPOS - 2, IConst.CANCASTLE_BLACK | IConst.SPECIAL));
	}

	public MWK(int from) {
		super(from);			
		ArrayList<MOVEDATA[]> list=new ArrayList<MOVEDATA[]>();
		add(UP,list);
		add(DOWN,list);
		add(LEFT,list);
		add(RIGHT,list);
		add(UP + LEFT,list);
		add(UP + RIGHT,list);
		add(DOWN + LEFT,list);
		add(DOWN + RIGHT,list);
		M=list.toArray(new MOVEDATA[list.size()][]);
	}

	protected void add(int offset, List<MOVEDATA[]> list) {
		int to = from + offset;
		if (BASE.inside(to, from)){
			MOVEDATA[] m=new MOVEDATA[6];
			list.add(m);
			long bitmap = PSQT.assemble(IConst.WK, from, to, CANCASTLE_BLACK | HALFMOVES);
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
	
	public void kmoves(Movegen gen, MOVEDATA[][] moves) {
		long enemy = gen.bb_black;
		long all = gen.bb_piece;
		for (MOVEDATA[] m : moves){
			long bto = m[5].bto;
			if ((all & bto) == 0) {
				add(gen,m[5]);
			} else {
				if ((enemy & bto) != 0) {
					int c = gen.ctype(bto);
//					if(c==3 && bto==1L<<IConst.BR_KING_STARTPOS)
//						gen.add(K);
//					 else if(c==3 && bto==1L<<IConst.BR_QUEEN_STARTPOS)
//						gen.add(Q);
//					 else 
						add(gen,m[c]);
				}
			}
		}
	}

	final static void add(Movegen gen,MOVEDATA md) {
		KingSafe p = KingSafe.pos(gen.pos,md);
		int to = BITS.getTo(md.bitmap);
		if(p.isSafeWhite(to))
			gen.add(md);
	}
	
	public MOVEDATA[][] getCastlingMoves(Movegen gen) {
		final boolean qc=(gen.castling & IConst.CANCASTLE_WHITEQUEEN) != 0;
		final boolean kc=(gen.castling & IConst.CANCASTLE_WHITEKING) != 0;
		return qc?(kc?X:XQ):(kc?XK:M);
	}

	public static void genCastling(Movegen gen) {
		long castling = gen.castling & IConst.CANCASTLE_WHITE;
		if ((IConst.CWQ & gen.bb_piece) == 0
				&& (castling & IConst.CANCASTLE_WHITEQUEEN) != 0
				&& KingSafe.pos(gen.pos).isSafeWhite(IConst.WK_STARTPOS - 1)) {
			add(gen,CQ);
		}
		if ((IConst.CWK & gen.bb_piece) == 0
				&& (castling & IConst.CANCASTLE_WHITEKING) != 0
				&& KingSafe.pos(gen.pos).isSafeWhite(IConst.WK_STARTPOS + 1)) {
			add(gen,CK);
		}
	}
}
