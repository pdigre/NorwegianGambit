package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MWP extends MPawn{

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
		int M1=0,P1=0;
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
					cpromotes(to,MOVEDATA.MD_PPL);
					if(to==BR_QUEEN_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PQ);
				} else {
					CL=captures(to);
					if(from > 31 && from < 40)
						enpassant(to,MOVEDATA.MD_PEL);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from + 9;
				REV[to] |= (1L << from);
				if(from>47){
					cpromotes(to,MOVEDATA.MD_PPR);
					if(to==BR_KING_STARTPOS)
						cpromotesx(to,MOVEDATA.MD_PK);
				} else {
				    CR=captures(to);
					if(from > 31 && from < 40)
						enpassant(to,MOVEDATA.MD_PER);
				}
			}
		}
		MPCapture L=new MPCapture();
		MWP.L[from]=L;
		MPCapture R=new MPCapture();
		MWP.R[from]=R;
		L.C=CL;
		R.C=CR;
		this.M1=M1;
		this.P1=P1;
	}

	private int move(int to) {
		return MOVEDATA.create(assemble(IConst.WP, from, to, CASTLING_STATE));
	}

	private void move2(int to,int enp) {
		ENPASSANT.create(assemble(IConst.WP, from, to, CASTLING_STATE),enp);
	}

	private void enpassant(int to,int offset) {
		MOVEDATA.create2(assemble(IConst.WP, from, to, CASTLING_STATE | SPECIAL) | (IConst.WP << _CAPTURE),offset+from%8);
	}

	private int[] captures(int to) {
		int[] captures=new int[5];
		for (int i = 0; i < 5; i++)
			captures[i]=MOVEDATA.capture(assemble(IConst.WP, from, to, CASTLING_STATE), WCAPTURES[i]);
		return captures;
	}

	private int promotes(int to) {
		int[] promotes=new int[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(assemblePromote(IConst.WP, WPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
		return promotes[0];
	}

	private void cpromotes(int to,int offset) {
		for (int i = 0; i < 20; i++)
			MOVEDATA.cpromote2(from,to, WPROMOTES[i%4], IConst.WP, WCAPTURES[i/4],offset+i+20*(from%8));
	}

	private void cpromotesx(int to,int offset) {
		for (int p = 0; p < 4; p++)
			MOVEDATAX.cpromote2(from,to, WPROMOTES[p], IConst.WP, IConst.BR,offset+p); 
	}
}
