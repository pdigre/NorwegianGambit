package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MWP extends MPawn{

	public static int PQ,PK;  // Promotion & Capture rook
	public static long[] REV=new long[64];
	public static MWP[] WP;
	public static MPCapture[] L,R;
	
	public static void init(){
		L=new MPCapture[64];
		R=new MPCapture[64];
		WP=new MWP[64];
		for (int from = 0; from < 64; from++)
			WP[from] = new MWP(from);
	}

	public MWP(int from) {
		super(from);
		int[] CL=null,CR=null;	
		int EL=0,ER=0,M1=0,P1=0,PL=0,PR=0;
		if(from>7 && from < 56){
			if (from < 48) {
				M1=move(from + 8);
				if(from < 16)
					move2(from + 16,from + 8);
			} else {
				P1=promotes(from + 8);
			}
			int x = from & 7;
			// LEFT
			if (x != 0){
				int to=from + 7;
				REV[to] |= (1L << from);
				if(from>47){
					PL=cpromotes(to);
					if(to==BR_QUEEN_STARTPOS)
						PQ=cpromotesx(to,MOVEDATA.MD_PQ);
				} else {
					CL=captures(to);
					if(from > 31 && from < 40)
						EL=enpassant(to,8);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from + 9;
				REV[to] |= (1L << from);
				if(from>47){
					PR=cpromotes(to);
					if(to==BR_KING_STARTPOS)
						PK=cpromotesx(to,MOVEDATA.MD_PK);
				} else {
				    CR=captures(to);
					if(from > 31 && from < 40)
						ER=enpassant(to,16);
				}
			}
		}
		MPCapture L=new MPCapture();
		MWP.L[from]=L;
		MPCapture R=new MPCapture();
		MWP.R[from]=R;
		L.C=CL;
		R.C=CR;
		L.E=EL;
		R.E=ER;
		this.M1=M1;
		this.P1=P1;
		L.P=PL;
		R.P=PR;
	}

	private int move(int to) {
		return MOVEDATA.create(assemble(IConst.WP, from, to, CASTLING_STATE));
	}

	private void move2(int to,int enp) {
		ENPASSANT.create(assemble(IConst.WP, from, to, CASTLING_STATE),enp);
	}

	private int enpassant(int to,int offset) {
		long bitmap = assemble(IConst.WP, from, to, CASTLING_STATE | SPECIAL);
		return MOVEDATA.create2(bitmap | (IConst.WP << _CAPTURE),offset+from%8);
	}

	private int[] captures(int to) {
		int[] captures=new int[5];
		for (int i = 0; i < 5; i++) {
			long bitmap = assemble(IConst.WP, from, to, CASTLING_STATE);
			captures[i]=MOVEDATA.capture(bitmap, WCAPTURES[i]);
		}
		return captures;
	}

	private int promotes(int to) {
		int[] promotes=new int[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(assemblePromote(IConst.WP, WPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
		return promotes[0];
	}

	private int cpromotes(int to) {
		int[] promotes=new int[20];
		for (int i = 0; i < 20; i++)
			promotes[i]=MOVEDATA.cpromote(from,to, WPROMOTES[i%4], IConst.WP, WCAPTURES[i/4]);
		return promotes[0];
	}

	private int cpromotesx(int to,int offset) {
		int[] promotes=new int[4];
		for (int p = 0; p < 4; p++){
			promotes[p]=MOVEDATAX.cpromote2(from,to, WPROMOTES[p], IConst.WP, IConst.BR,offset+p); 
		}
		return promotes[0];
	}
}
