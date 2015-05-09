package norwegiangambit.engine.movegen;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MWN extends MSimple{

	final static MSimple[] MOVES;

	static {
		MOVES=new MWN[64];
		for (int from = 0; from < 64; from++)
			MOVES[from] = new MWN(from);
	}
	
	public MWN(int from) {
		super(from);
		ArrayList<int[]> list=new ArrayList<int[]>();
		for (int i = 0; i < KNIGHT_MOVES.length; i++)
			add(KNIGHT_MOVES[i], list);
		M=list.toArray(new int[list.size()][]);
	}

	private void add(int offset, List<int[]> list) {
		int to = from + offset;
		if (inside(to, from)){
			int[] m=new int[6];
			list.add(m);
			long bitmap = assemble(IConst.WN, from, to, CASTLING_STATE | HALFMOVES);
			m[5]=MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = WCAPTURES[i];
				m[i]=MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}
}
