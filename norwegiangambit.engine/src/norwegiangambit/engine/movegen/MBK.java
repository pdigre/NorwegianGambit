package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MBK extends MSimple implements IBlack {

	public static int XB,XE,XQB,XQE,XKB,XKE; // Castling breakers
	
	public static MBK[] MOVES;
	public static void init() {
		MOVES=new MBK[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MBK(from);
		MBK m = MOVES[BK_STARTPOS];
		
		XB=MOVEDATA.brk_cnt;
		castlingKing(m.B,m.E,CANCASTLE_BLACK);
		XE=MOVEDATA.brk_cnt;
		XQB=MOVEDATA.brk_cnt;
		castlingKing(m.B,m.E,CANCASTLE_BLACKQUEEN);
		XQE=MOVEDATA.brk_cnt;
		XKB=MOVEDATA.brk_cnt;
		castlingKing(m.B,m.E,CANCASTLE_BLACKKING);
		XKE=MOVEDATA.brk_cnt;
		
		long cq = assemble(IConst.BK, BK_STARTPOS, BK_STARTPOS - 2, CANCASTLE_WHITE | SPECIAL);
		MOVEDATAX.create2(cq,CANCASTLE_BLACK,MOVEDATA.MD_KCQ);
		MOVEDATAX.create2(cq,CANCASTLE_BLACKQUEEN,MOVEDATA.MD_KCQ2);
		long ck = assemble(IConst.BK, BK_STARTPOS, BK_STARTPOS + 2, CANCASTLE_WHITE | SPECIAL);
		MOVEDATAX.create2(ck,CANCASTLE_BLACK,MOVEDATA.MD_KCK);
		MOVEDATAX.create2(ck,CANCASTLE_BLACKKING,MOVEDATA.MD_KCK2);
	}

	public MBK(int from) {
		super(from);
		addMoves(new int[]{UP,DOWN,LEFT,RIGHT,UP + LEFT,UP + RIGHT,DOWN + LEFT,DOWN + RIGHT});
		if(q!=null)
			Q=MOVEDATA.add(q);
		if(k!=null)
			K=MOVEDATA.add(k);
	}

	public void addMoves(int[] mvs) {
		for (int i : mvs)
			add(i);
	}

	protected void add(int offset) {
		int to = from + offset;
		if (inside(to, from)){
			E+=6;
			long bitmap = assemble(IConst.BK, from, to, CANCASTLE_WHITE | HALFMOVES);
			MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = BCAPTURES[i];
				MOVEDATA.capture(bitmap, c); 
				rookCapture2(to, bitmap, c);
			}
		}
	}
}
