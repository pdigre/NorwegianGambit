package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MBP  extends MPawn implements IBlack{

	public static void init() {
		for (int from = 0; from < 64; from++)
			new MBP(from);
	}

	public MBP(int from) {
		super(from);
		int lane = from%8;
		if(from>7 && from < 56){
			if (from > 15) {
				move(from - 8,MOVEDATA.MD_P1+from);
				if(from > 47)
					move2(from - 16,from - 8);
			} else {
				promotes(from - 8,MOVEDATA.MD_PP+4*lane);
			}
			int x = from & 7;
			// LEFT
			if (x != 0){
				int to=from - 9;
				MOVEDATA.REV_BP[to] |= (1L << from);
				if(from<16){
					cpromotes(to,MOVEDATA.MD_PPL+20*lane);
					if(to==WR_QUEEN_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PQ);
				} else {
					captures(to,MOVEDATA.MD_PCL+from*5);
					if(from > 23 && from < 32)
						enpassant(to,MOVEDATA.MD_PEL+lane);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from - 7;
				MOVEDATA.REV_BP[to] |= (1L << from);
				if(from<16){
					cpromotes(to,MOVEDATA.MD_PPR+20*lane);
					if(to==WR_KING_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PK);
				} else {
				    captures(to,MOVEDATA.MD_PCR+from*5);
					if(from > 23 && from < 32)
						enpassant(to,MOVEDATA.MD_PER+lane);
				}
			}
		}
	}

	private void move(int to,int offset) {
		MOVEDATA.create2(assemble(IConst.BP, from, to, CASTLING_STATE),offset);
	}

	private void move2(int to,int enp) {
		ENPASSANT.create(assemble(IConst.BP, from, to, CASTLING_STATE),enp);
	}

	private void enpassant(int to,int offset) {
		MOVEDATA.create2(assemble(IConst.BP, from, to, CASTLING_STATE | IConst.SPECIAL) | (IConst.WP << IConst._CAPTURE),offset);
	}

	private void captures(int to,int offset) {
		for (int i = 0; i < 5; i++)
			MOVEDATA.capture2(assemble(IConst.BP, from, to, CASTLING_STATE), BCAPTURES[i],offset+i);
	}

	private void promotes(int to,int offset) {
		for (int p = 0; p < 4; p++)
			MOVEDATA.create2(assemblePromote(IConst.BP, BPROMOTES[p], from, to, CASTLING_STATE | SPECIAL),offset+p);
	}

	private void cpromotes(int to,int offset) {
		for (int i = 0; i < 20; i++)
			MOVEDATA.cpromote2(from,to, BPROMOTES[i%4], IConst.BP, BCAPTURES[i/4],offset+i);
	}

	private void cpromotesx(int to,int offset) {
		for (int p = 0; p < 4; p++)
			MOVEDATAX.capturePromote(from,to, BPROMOTES[p], IConst.BP, IConst.WR,offset+p);
	}
}
