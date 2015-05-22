package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MWK extends MSimple {
	public static int XB,XE,XQB,XQE,XKB,XKE; // Castling breakers

	public static MWK[] MOVES;
	
	public static void init() {
		MOVES=new MWK[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWK(from);
		MWK m = MOVES[WK_STARTPOS];
		
		XB=MOVEDATA.brk_cnt;
		castlingKing(m.B,m.E,CANCASTLE_WHITE);
		XE=MOVEDATA.brk_cnt;

		XQB=MOVEDATA.brk_cnt;
		castlingKing(m.B,m.E,CANCASTLE_WHITEQUEEN);
		XQE=MOVEDATA.brk_cnt;

		XKB=MOVEDATA.brk_cnt;
		castlingKing(m.B,m.E,CANCASTLE_WHITEKING);
		XKE=MOVEDATA.brk_cnt;
		
		long cq = assemble(IConst.WK, WK_STARTPOS, WK_STARTPOS - 2, CANCASTLE_BLACK | SPECIAL);
		MOVEDATAX.create2(cq,CANCASTLE_WHITE,MOVEDATA.MD_KCQ);
		MOVEDATAX.create2(cq,CANCASTLE_WHITEQUEEN,MOVEDATA.MD_KCQ2);
		long ck = assemble(IConst.WK, WK_STARTPOS, WK_STARTPOS + 2, CANCASTLE_BLACK | SPECIAL);
		MOVEDATAX.create2(ck,CANCASTLE_WHITE,MOVEDATA.MD_KCK);
		MOVEDATAX.create2(ck,CANCASTLE_WHITEKING,MOVEDATA.MD_KCK2);
	}

	public MWK(int from) {
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
			long bitmap = assemble(IConst.WK, from, to, CANCASTLE_BLACK | HALFMOVES);
			MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = WCAPTURES[i];
				MOVEDATA.capture(bitmap, c); 
				rookCapture2(to, bitmap, c);
			}
		}
	}
}
