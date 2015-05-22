package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MWN extends MSimple{

	public static MSimple[] MOVES;

	public static void init() {
		MOVES=new MWN[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWN(from);
	}
	
	public MWN(int from) {
		super(from);
		for (int i = 0; i < KNIGHT_MOVES.length; i++)
			add(KNIGHT_MOVES[i]);
		if(q!=null)
			Q=MOVEDATA.add(q);
		if(k!=null)
			K=MOVEDATA.add(k);
	}

	private void add(int offset) {
		int to = from + offset;
		if (inside(to, from)){
			long bitmap = assemble(IConst.WN, from, to, CASTLING_STATE | HALFMOVES);
			MOVEDATA.create(bitmap);
			E+=6;
			for (int i = 0; i < 5; i++){
				int c = WCAPTURES[i];
				MOVEDATA.capture(bitmap, c); 
				rookCapture2(to, bitmap, c);
			}
		}
	}
}
