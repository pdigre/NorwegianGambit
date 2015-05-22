package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MWP extends MPawn{

	public static MWP[] WP;
	
	public static void init(){
		WP=new MWP[64];
		for (int from = 0; from < 64; from++)
			WP[from] = new MWP(from);
	}

	public MWP(int from) {
		super(from);
		int lane = from%8;
		if(from>7 && from < 56){
			if (from < 48) {
				move(from + 8,MOVEDATA.MD_P1+from);
				if(from < 16)
					move2(from + 16,from + 8);
			} else {
				promotes(from + 8,MOVEDATA.MD_PP+4*lane);
			}
			int x = from & 7;
			// LEFT
			if (x != 0){
				int to=from + 7;
				MOVEDATA.REV_WP[to] |= (1L << from);
				if(from>47){
					cpromotes(to,MOVEDATA.MD_PPL+20*lane);
					if(to==BR_QUEEN_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PQ);
				} else {
					captures(to,MOVEDATA.MD_PCL+from*5);
					if(from > 31 && from < 40)
						enpassant(to,MOVEDATA.MD_PEL+lane);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from + 9;
				MOVEDATA.REV_WP[to] |= (1L << from);
				if(from>47){
					cpromotes(to,MOVEDATA.MD_PPR+20*lane);
					if(to==BR_KING_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PK);
				} else {
				    captures(to,MOVEDATA.MD_PCR+from*5);
					if(from > 31 && from < 40)
						enpassant(to,MOVEDATA.MD_PER+lane);
				}
			}
		}
	}

	private void move(int to,int offset) {
		MOVEDATA.create2(assemble(IConst.WP, from, to, CASTLING_STATE),offset);
	}

	private void move2(int to,int enp) {
		ENPASSANT.create(assemble(IConst.WP, from, to, CASTLING_STATE),enp);
	}

	private void enpassant(int to,int offset) {
		MOVEDATA.create2(assemble(IConst.WP, from, to, CASTLING_STATE | SPECIAL) | (IConst.WP << _CAPTURE),offset);
	}

	private void captures(int to,int offset) {
		for (int i = 0; i < 5; i++)
			MOVEDATA.capture2(assemble(IConst.WP, from, to, CASTLING_STATE), WCAPTURES[i],offset+i);
	}

	private void promotes(int to,int offset) {
		for (int p = 0; p < 4; p++)
			MOVEDATA.create2(assemblePromote(IConst.WP, WPROMOTES[p], from, to, CASTLING_STATE | SPECIAL),offset+p);
	}

	private void cpromotes(int to,int offset) {
		for (int i = 0; i < 20; i++)
			MOVEDATA.cpromote2(from,to, WPROMOTES[i%4], IConst.WP, WCAPTURES[i/4],offset+i);
	}

	private void cpromotesx(int to,int offset) {
		for (int p = 0; p < 4; p++)
			MOVEDATAX.cpromote2(from,to, WPROMOTES[p], IConst.WP, IConst.BR,offset+p); 
	}
}
