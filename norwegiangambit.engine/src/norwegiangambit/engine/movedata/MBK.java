package norwegiangambit.engine.movedata;

import norwegiangambit.util.IConst;

public class MBK extends MSimple{

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MBK(from);
		int b=MOVEDATA.MD_K[BK_STARTPOS*2]+MOVEDATA.color_offset;
		int e=MOVEDATA.MD_K[BK_STARTPOS*2+1]+MOVEDATA.color_offset;
		castlingKing(b,e,CANCASTLE_BLACK,MOVEDATA.MD_KX);
		castlingKing(b,e,CANCASTLE_BLACKQUEEN,MOVEDATA.MD_KXQ);
		castlingKing(b,e,CANCASTLE_BLACKKING,MOVEDATA.MD_KXK);
		
		long cq = assemble(IConst.BK, BK_STARTPOS, BK_STARTPOS - 2, CANCASTLE_WHITE | SPECIAL);
		MOVEDATAX.create2(cq,CANCASTLE_BLACK,MOVEDATA.MD_KCQ);
		MOVEDATAX.create2(cq,CANCASTLE_BLACKQUEEN,MOVEDATA.MD_KCQ2);
		long ck = assemble(IConst.BK, BK_STARTPOS, BK_STARTPOS + 2, CANCASTLE_WHITE | SPECIAL);
		MOVEDATAX.create2(ck,CANCASTLE_BLACK,MOVEDATA.MD_KCK);
		MOVEDATAX.create2(ck,CANCASTLE_BLACKKING,MOVEDATA.MD_KCK2);
	}

	public MBK(int from) {
		super(from);
		for (int i : new int[]{UP,DOWN,LEFT,RIGHT,UP + LEFT,UP + RIGHT,DOWN + LEFT,DOWN + RIGHT})
			add(i);
		addBreakers();
	}

	protected void add(int offset) {
		int to = from + offset;
		if (inside(to, from)){
			n+=6;
			long bitmap = assemble(IConst.BK, from, to, CANCASTLE_WHITE | HALFMOVES);
			MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = BCAPTURES[i];
				MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}
}
