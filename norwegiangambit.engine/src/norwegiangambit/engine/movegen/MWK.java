package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MWK extends MSimple {
	public static int XB,XQB,XKB; // Castling breakers

	public static MWK[] MOVES;
	
	public static void init() {
		MOVES=new MWK[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWK(from);
		int b=MOVEDATA.MD_K[WK_STARTPOS*2];
		int e=MOVEDATA.MD_K[WK_STARTPOS*2+1];

		XB=MOVEDATA.brk_cnt;
		castlingKing(b,e,CANCASTLE_WHITE);

		XQB=MOVEDATA.brk_cnt;
		castlingKing(b,e,CANCASTLE_WHITEQUEEN);

		XKB=MOVEDATA.brk_cnt;
		castlingKing(b,e,CANCASTLE_WHITEKING);

		long cq = assemble(IConst.WK, WK_STARTPOS, WK_STARTPOS - 2, CANCASTLE_BLACK | SPECIAL);
		MOVEDATAX.create2(cq,CANCASTLE_WHITE,MOVEDATA.MD_KCQ);
		MOVEDATAX.create2(cq,CANCASTLE_WHITEQUEEN,MOVEDATA.MD_KCQ2);
		long ck = assemble(IConst.WK, WK_STARTPOS, WK_STARTPOS + 2, CANCASTLE_BLACK | SPECIAL);
		MOVEDATAX.create2(ck,CANCASTLE_WHITE,MOVEDATA.MD_KCK);
		MOVEDATAX.create2(ck,CANCASTLE_WHITEKING,MOVEDATA.MD_KCK2);
	}

	public MWK(int from) {
		super(from);
		MOVEDATA.MD_K[from*2]=B;
		for (int i : new int[]{UP,DOWN,LEFT,RIGHT,UP + LEFT,UP + RIGHT,DOWN + LEFT,DOWN + RIGHT})
			add(i);
		MOVEDATA.MD_K[from*2+1]=n;
		addBreakers();
	}

	protected void add(int offset) {
		int to = from + offset;
		if (inside(to, from)){
			n+=6;
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
