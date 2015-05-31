package norwegiangambit.engine.movedata;

import norwegiangambit.util.IConst;

public class MWN extends MSimple{

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MWN(from);
	}
	
	public MWN(int from) {
		super(from);
		MOVEDATA.MD_N[from*2]=n;
		for (int i = 0; i < KNIGHT_MOVES.length; i++)
			add(KNIGHT_MOVES[i]);
		MOVEDATA.MD_N[from*2+1]=n;
		addBreakers();
	}

	private void add(int offset) {
		int to = from + offset;
		if (inside(to, from)){
			long bitmap = assemble(IConst.WN, from, to, CASTLING_STATE | HALFMOVES);
			MOVEDATA.create(bitmap);
			n+=6;
			for (int i = 0; i < 5; i++){
				int c = WCAPTURES[i];
				MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}
}