package norwegiangambit.engine.movegen;

import static norwegiangambit.engine.movegen.BASE.DOWN;
import static norwegiangambit.engine.movegen.BASE.LEFT;
import static norwegiangambit.engine.movegen.BASE.RIGHT;
import static norwegiangambit.engine.movegen.BASE.UP;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MBK extends MBase {

	final int[][] M;
	final static int CQ,CK;
	final static int[][] X,XQ,XK;
	
	final static MBK[] BK;
	static {
		BK=new MBK[64];
		for (int from = 0; from < 64; from++)
			BK[from] = new MBK(from);
		int[][] M=BK[IConst.BK_STARTPOS].M;
		X=castlingKing(M,IConst.CANCASTLE_BLACK);
		XQ=castlingKing(M,IConst.CANCASTLE_BLACKQUEEN);
		XK=castlingKing(M,IConst.CANCASTLE_BLACKKING);
		CQ=MOVEDATAX.create(assemble(IConst.BK, IConst.BK_STARTPOS, IConst.BK_STARTPOS - 2, IConst.CANCASTLE_WHITE | IConst.SPECIAL));
		CK=MOVEDATAX.create(assemble(IConst.BK, IConst.BK_STARTPOS, IConst.BK_STARTPOS + 2, IConst.CANCASTLE_WHITE | IConst.SPECIAL));
	}

	public MBK(int from) {
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
			long bitmap = assemble(IConst.BK, from, to, CANCASTLE_WHITE | HALFMOVES);
			m[5]=MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = BCAPTURES[i];
				m[i]=MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}

	public void genLegal(Movegen gen) {
		kmoves(gen,from == IConst.BK_STARTPOS?getCastlingMoves(gen):M);
	}
	
	public void kmoves(Movegen gen, int[][] moves) {
		long enemy = gen.bb_white;
		long all = gen.bb_piece;
		for (int[] m : moves){
			long bto = BASE.getBTo(m[5]);
			if ((all & bto) == 0) {
				add(gen,m[5]);
			} else {
				if ((enemy & bto) != 0L) {
					int c = gen.ctype(bto);
					if(c==3 && bto==1L<<IConst.WR_KING_STARTPOS)
						add(gen,K);
					else if(c==3 && bto==1L<<IConst.WR_QUEEN_STARTPOS)
						add(gen,Q);
					else 
						add(gen,m[c]);
				}
			}
		}
	}

	final static void add(Movegen gen,int md) {
		if(gen.isSafeKingMove(md))
			gen.add(md);
	}
	
	final static void addCapture(Movegen gen,int md) {
		if(gen.isSafeKingMove(md))
			gen.capture(md);
	}
	
	public int[][] getCastlingMoves(Movegen gen) {
		final boolean qc=(gen.castling & IConst.CANCASTLE_BLACKQUEEN) != 0;
		final boolean kc=(gen.castling & IConst.CANCASTLE_BLACKKING) != 0;
		return qc?(kc?X:XQ):(kc?XK:M);
	}

	public static void genCastling(Movegen gen) {
		long castling = gen.castling & IConst.CANCASTLE_BLACK;
		if ((IConst.CBQ & gen.bb_piece) == 0
				&& (castling & IConst.CANCASTLE_BLACKQUEEN) != 0
				&& gen.isSafePos(IConst.BK_STARTPOS - 1)
				&& gen.isSafePos(IConst.BK_STARTPOS - 2)) {
			add(gen,CQ);
		}
		if ((IConst.CBK & gen.bb_piece) == 0
				&& (castling & IConst.CANCASTLE_BLACKKING) != 0
				&& gen.isSafePos(IConst.BK_STARTPOS + 1)
				&& gen.isSafePos(IConst.BK_STARTPOS + 2)) {
			add(gen,CK);
		}
	}

}
