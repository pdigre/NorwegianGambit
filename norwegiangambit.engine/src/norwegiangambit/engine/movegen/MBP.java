package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MBP  extends MPawn implements IBlack{

	public static long[] REV=new long[64];
	public static MBP[] BP;
	public static MPCapture[] L,R;
	public static void init() {
		L=new MPCapture[64];
		R=new MPCapture[64];
		BP=new MBP[64];
		for (int from = 0; from < 64; from++)
			BP[from] = new MBP(from);
	}

	public MBP(int from) {
		super(from);
		int[] CL=null,CR=null;	
		int M1=0,P1=0;
		if(from>7 && from < 56){
			if (from > 15) {
				M1=move(from - 8);
				if(from > 47)
					move2(from - 16,from - 8);
			} else {
				P1=promotes(from - 8);
			}
			int x = from & 7;
			// LEFT
			if (x != 0){
				int to=from - 9;
				REV[to] |= (1L << from);
				if(from<16){
					cpromotes(to,MOVEDATA.MD_PPL);
					if(to==WR_QUEEN_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PQ);
				} else {
					CL=captures(to);
					if(from > 23 && from < 32)
						enpassant(to,MOVEDATA.MD_PEL);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from - 7;
				REV[to] |= (1L << from);
				if(from<16){
					cpromotes(to,MOVEDATA.MD_PPR);
					if(to==WR_KING_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PK);
				} else {
				    CR=captures(to);
					if(from > 23 && from < 32)
						enpassant(to,MOVEDATA.MD_PER);
				}
			}
		}
		MPCapture L=new MPCapture();
		MBP.L[from]=L;
		MPCapture R=new MPCapture();
		MBP.R[from]=R;
		L.C=CL;
		R.C=CR;
		this.M1=M1;
		this.P1=P1;
	}

	private int move(int to) {
		return MOVEDATA.create(assemble(IConst.BP, from, to, CASTLING_STATE));
	}

	private void move2(int to,int enp) {
		ENPASSANT.create(assemble(IConst.BP, from, to, CASTLING_STATE),enp);
	}

	private void enpassant(int to,int offset) {
		MOVEDATA.create2(assemble(IConst.BP, from, to, CASTLING_STATE | IConst.SPECIAL) | (IConst.WP << IConst._CAPTURE),offset+from%8);
	}

	private int[] captures(int to) {
		int[] captures=new int[5];
		for (int i = 0; i < 5; i++)
			captures[i]=MOVEDATA.capture(assemble(IConst.BP, from, to, CASTLING_STATE), BCAPTURES[i]);
		return captures;
	}

	private int promotes(int to) {
		int[] promotes=new int[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(assemblePromote(IConst.BP, BPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
		return promotes[0];
	}

	private void cpromotes(int to,int offset) {
		for (int i = 0; i < 20; i++)
			MOVEDATA.cpromote2(from,to, BPROMOTES[i%4], IConst.BP, BCAPTURES[i/4],offset+i+20*(from%8));
	}

	private void cpromotesx(int to,int offset) {
		for (int p = 0; p < 4; p++)
			MOVEDATAX.cpromote2(from,to, BPROMOTES[p], IConst.BP, IConst.WR,offset+p);
	}
}
