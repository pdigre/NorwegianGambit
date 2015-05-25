package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MBN extends MSimple implements IBlack {

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MBN(from);
	}

	public MBN(int from) {
		super(from);
		for (int i = 0; i < KNIGHT_MOVES.length; i++)
			add(KNIGHT_MOVES[i]);
		addBreakers();
	}

	private void add(int offset) {
		int to = from + offset;
		if (inside(to, from)){
			long bitmap = assemble(IConst.BN, from, to, CASTLING_STATE | HALFMOVES);
			MOVEDATA.create(bitmap);
			n+=6;
			for (int i = 0; i < 5; i++){
				int c = BCAPTURES[i];
				MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}
}
