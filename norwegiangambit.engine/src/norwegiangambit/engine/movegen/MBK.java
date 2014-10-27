package norwegiangambit.engine.movegen;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MBK extends MBase {

	final int[][] M;
	final static int CQ,CK,CQ2,CK2;
	final static int[][] X,XQ,XK;
	
	final static MBK[] BK;
	static {
		BK=new MBK[64];
		for (int from = 0; from < 64; from++)
			BK[from] = new MBK(from);
		int[][] M=BK[BK_STARTPOS].M;
		X=castlingKing(M,CANCASTLE_BLACK);
		XQ=castlingKing(M,CANCASTLE_BLACKQUEEN);
		XK=castlingKing(M,CANCASTLE_BLACKKING);
		long cq = assemble(IConst.BK, BK_STARTPOS, BK_STARTPOS - 2, CANCASTLE_WHITE | SPECIAL);
		CQ=MOVEDATAX.create(cq,CANCASTLE_BLACK);
		CQ2=MOVEDATAX.create(cq,CANCASTLE_BLACKQUEEN);
		long ck = assemble(IConst.BK, BK_STARTPOS, BK_STARTPOS + 2, CANCASTLE_WHITE | SPECIAL);
		CK=MOVEDATAX.create(ck,CANCASTLE_BLACK);
		CK2=MOVEDATAX.create(ck,CANCASTLE_BLACKKING);
	}

	public MBK(int from) {
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
		kmoves(gen,from == BK_STARTPOS?getCastlingMoves(gen):M);
	}
	
	public void kmoves(Movegen gen, int[][] moves) {
		long enemy = gen.bb_white;
		long all = gen.bb_piece;
		for (int[] m : moves){
			long bto = getBTo(m[5]);
			if ((all & bto) == 0) {
				add(gen,m[5]);
			} else {
				if ((enemy & bto) != 0L) {
					int c = gen.ctype(bto);
					if(c==3 && bto==1L<<WR_KING_STARTPOS && (gen.castling&CANCASTLE_WHITEKING)!=0)
						add(gen,K);
					else if(c==3 && bto==1L<<WR_QUEEN_STARTPOS && (gen.castling&CANCASTLE_WHITEQUEEN)!=0)
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
		final boolean qc=(gen.castling & CANCASTLE_BLACKQUEEN) != 0;
		final boolean kc=(gen.castling & CANCASTLE_BLACKKING) != 0;
		return qc?(kc?X:XQ):(kc?XK:M);
	}

	public static void genCastling(Movegen gen) {
		long castling = gen.castling & CANCASTLE_BLACK;
		if ((CBQ & gen.bb_piece) == 0
				&& (castling & CANCASTLE_BLACKQUEEN) != 0
				&& gen.isSafePos(BK_STARTPOS - 1)
				&& gen.isSafePos(BK_STARTPOS - 2)) {
			add(gen,(gen.castling & CANCASTLE_BLACKKING) != 0?CQ:CQ2);
		}
		if ((CBK & gen.bb_piece) == 0
				&& (castling & CANCASTLE_BLACKKING) != 0
				&& gen.isSafePos(BK_STARTPOS + 1)
				&& gen.isSafePos(BK_STARTPOS + 2)) {
			add(gen,(gen.castling & CANCASTLE_BLACKQUEEN) != 0?CK:CK2);
		}
	}

}
