package norwegiangambit.engine.movegen;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MWK extends MBase {
	final static int CQ,CK,CQ2,CK2;
	final static int[][] X,XQ,XK; // Castling breakers

	final int[][] M;

	final static MWK[] MOVES;
	static {
		MOVES=new MWK[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWK(from);
		int[][] M=MOVES[WK_STARTPOS].M;
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
}
