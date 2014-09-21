package norwegiangambit.engine.base;

import static norwegiangambit.engine.base.BASE.KNIGHT_MOVES;
import static norwegiangambit.engine.base.BASE.inside;
import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MWN extends MBase{

	final static MWN[] WN;
	;

	static {
		WN=new MWN[64];
		for (int from = 0; from < 64; from++)
			WN[from] = new MWN(from);
	}
	
	public MWN(int from) {
		super(from);
		ArrayList<MOVEDATA[]> list=new ArrayList<MOVEDATA[]>();
		for (int i = 0; i < KNIGHT_MOVES.length; i++)
			add(KNIGHT_MOVES[i], list);
		M=list.toArray(new MOVEDATA[list.size()][]);
	}

	private void add(int offset, List<MOVEDATA[]> list) {
		int to = from + offset;
		if (inside(to, from)){
			MOVEDATA[] m=new MOVEDATA[6];
			list.add(m);
			long bitmap = PSQT.assemble(IConst.WN, from, to, CASTLING_STATE | HALFMOVES);
			m[5]=MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = WCAPTURES[i];
				m[i]=MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}
	
	final MOVEDATA[][] M;

	public void genLegal(Movegen gen) {
		long all = gen.bb_piece;
		long enemy = gen.bb_black;
		for (MOVEDATA[] m : M){
			long bto = m[5].bto;
			if ((all & bto) == 0) {
				gen.add(m[5]);
			} else {
				if ((enemy & bto) != 0) {
					int c = gen.ctype(bto);
					if(c==3 && bto==1L<<IConst.BR_KING_STARTPOS)
						gen.capture(K);
					else if(c==3 && bto==1L<<IConst.BR_QUEEN_STARTPOS)
						gen.capture(Q);
					else 
						gen.capture(m[c]);
				}
			}
		}
	}
}
