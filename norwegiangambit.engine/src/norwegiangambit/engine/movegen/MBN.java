package norwegiangambit.engine.movegen;

import java.util.ArrayList;
import java.util.List;

import norwegiangambit.util.IConst;

public class MBN extends MBase {

	final static MBN[] BN;
	static {
		BN=new MBN[64];
		for (int from = 0; from < 64; from++)
			BN[from] = new MBN(from);
	}

	public MBN(int from) {
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
			long bitmap = assemble(IConst.BN, from, to, CASTLING_STATE | HALFMOVES);
			m[5]=MOVEDATA.create(bitmap);
			for (int i = 0; i < 5; i++){
				int c = BCAPTURES[i];
				m[i]=MOVEDATA.capture(bitmap, c); 
				rookCapture(to, bitmap, c);
			}
		}
	}
	
	final int[][] M;

	public void genLegal(Movegen gen,long mask) {
		long all = gen.bb_piece;
		long enemy = gen.bb_white;
		for (int[] m : M){
			long bto = getBTo(m[5]);
			if ((all & bto) == 0) {
				if((bto & mask)!=0L)
					gen.move(m[5]);
			} else {
				if ((enemy & bto & mask) != 0) {
					int c = gen.ctype(bto);
					if(c==3 && bto==1L<<IConst.WR_KING_STARTPOS && (gen.castling&CANCASTLE_WHITEKING)!=0)
						gen.capture(K, 1, c, bto);
					else if(c==3 && bto==1L<<IConst.WR_QUEEN_STARTPOS && (gen.castling&CANCASTLE_WHITEKING)!=0)
						gen.capture(Q, 1, c, bto);
					else
						gen.capture(m[c], 1, c, bto);
				}
			}
		}
	}

}
