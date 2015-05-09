package norwegiangambit.engine.movegen;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MBK extends MBase {

	final int[][] M;
	final static int CQ,CK,CQ2,CK2;
	final static int[][] X,XQ,XK;
	
	final static MBK[] MOVES;
	static {
		MOVES=new MBK[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MBK(from);
		int[][] M=MOVES[BK_STARTPOS].M;
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
}
