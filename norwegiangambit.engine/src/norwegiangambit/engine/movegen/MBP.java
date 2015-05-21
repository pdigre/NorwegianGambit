package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MBP  extends MPawn implements IBlack{

	public static int PQ,PK;  // Promotion & Capture rook
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
		int EL=0,ER=0,M1=0,P1=0,PL=0,PR=0;
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
					PL=cpromotes(to);
					if(to==WR_QUEEN_STARTPOS)
						PQ=cpromotesx(to,MOVEDATA.MD_PQ);
				} else {
					CL=captures(to);
					if(from > 23 && from < 32)
						EL=enpassant(to,8);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from - 7;
				REV[to] |= (1L << from);
				if(from<16){
					PR=cpromotes(to);
					if(to==WR_KING_STARTPOS)
						PK=cpromotesx(to,MOVEDATA.MD_PK);
				} else {
				    CR=captures(to);
					if(from > 23 && from < 32)
						ER=enpassant(to,16);
				}
			}
		}
		MPCapture L=new MPCapture();
		MBP.L[from]=L;
		MPCapture R=new MPCapture();
		MBP.R[from]=R;
		L.C=CL;
		R.C=CR;
		L.E=EL;
		R.E=ER;
		this.M1=M1;
//		this.M2=M2;
		this.P1=P1;
		L.P=PL;
		R.P=PR;
	}

	private int move(int to) {
		return MOVEDATA.create(assemble(IConst.BP, from, to, CASTLING_STATE));
	}

	private void move2(int to,int enp) {
		ENPASSANT.create(assemble(IConst.BP, from, to, CASTLING_STATE),enp);
	}

	private int enpassant(int to,int offset) {
		long bitmap = assemble(IConst.BP, from, to, CASTLING_STATE | IConst.SPECIAL);
		return MOVEDATA.create2(bitmap | (IConst.WP << IConst._CAPTURE),offset+from%8);
	}

	private int[] captures(int to) {
		int[] captures=new int[5];
		for (int i = 0; i < 5; i++) {
			long bitmap = assemble(IConst.BP, from, to, CASTLING_STATE);
			captures[i]=MOVEDATA.capture(bitmap, BCAPTURES[i]);
		}
		return captures;
	}

	private int promotes(int to) {
		int[] promotes=new int[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(assemblePromote(IConst.BP, BPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
		return promotes[0];
	}

	private int cpromotes(int to) {
		int[] promotes=new int[20];
		for (int i = 0; i < 20; i++)
			promotes[i]=MOVEDATA.cpromote(from,to, BPROMOTES[i%4], IConst.BP, BCAPTURES[i/4]);
		return promotes[0];
	}

	private int cpromotesx(int to,int offset) {
		int[] promotes=new int[4];
		for (int p = 0; p < 4; p++){
			promotes[p]=MOVEDATAX.cpromote2(from,to, BPROMOTES[p], IConst.BP, IConst.WR,offset+p);
		}
		return promotes[0];
	}
}
