package norwegiangambit.engine.base;

import norwegiangambit.util.IConst;

public class MWP extends MBase{

	final MOVEDATA[] CL,CR;	// Capture
	final MOVEDATA EL,ER;  // Enpassant
	final MOVEDATA M1,M2;   // Forward
	final MOVEDATA[] P1,PL,PR;   // Promotion
	static MOVEDATA[] PQ,PK;  // Promotion & Capture rook
	static long[] REV=new long[64];
	final static MWP[] WP;
	static {
		WP=new MWP[64];
		for (int from = 0; from < 64; from++)
			WP[from] = new MWP(from);
	}

	public MWP(int from) {
		super(from);
		MOVEDATA[] CL=null,CR=null,P1=null,PL=null,PR=null;	
		MOVEDATA EL=null,ER=null,M1=null,M2=null;
		if(from>7 && from < 56){
			if (from < 48) {
				M1=move(from + 8);
				if(from < 16)
					M2=move(from + 16);
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
						PQ=cpromotesx(to);
				} else {
					CL=captures(to);
					if(from > 31 && from < 40)
						EL=enpassant(to);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from + 9;
				REV[to] |= (1L << from);
				if(from>47){
					PR=cpromotes(to);
					if(to==BR_KING_STARTPOS)
						PK=cpromotesx(to);
				} else {
				    CR=captures(to);
					if(from > 31 && from < 40)
						ER=enpassant(to);
				}
			}
		}
		this.CL=CL;
		this.CR=CR;
		this.EL=EL;
		this.ER=ER;
		this.M1=M1;
		this.M2=M2;
		this.P1=P1;
		this.PL=PL;
		this.PR=PR;
	}

	private MOVEDATA move(int to) {
		return MOVEDATA.create(assemble(IConst.WP, from, to, CASTLING_STATE));
	}

	private MOVEDATA enpassant(int to) {
		long bitmap = assemble(IConst.WP, from, to, CASTLING_STATE | IConst.SPECIAL);
		return MOVEDATA.create(bitmap | (IConst.WP << IConst._CAPTURE));
	}

	private MOVEDATA[] captures(int to) {
		MOVEDATA[] captures=new MOVEDATA[5];
		for (int i = 0; i < 5; i++) {
			long bitmap = assemble(IConst.WP, from, to, CASTLING_STATE);
			captures[i]=MOVEDATA.capture(bitmap, WCAPTURES[i]);
		}
		return captures;
	}

	private MOVEDATA[] promotes(int to) {
		MOVEDATA[] promotes=new MOVEDATA[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(assemblePromote(IConst.WP, WPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
		return promotes;
	}

	private MOVEDATA[] cpromotes(int to) {
		MOVEDATA[] promotes=new MOVEDATA[20];
		for (int p = 0; p < 4; p++)
			for (int i = 0; i < 5; i++) {
				promotes[p*5+i]=MOVEDATA.cpromote(from,to, WPROMOTES[p], IConst.WP, WCAPTURES[i]);
			}
		return promotes;
	}

	private MOVEDATA[] cpromotesx(int to) {
		MOVEDATA[] promotes=new MOVEDATA[4];
		for (int p = 0; p < 4; p++){
			promotes[p]=MOVEDATAX.cpromote(from,to, WPROMOTES[p], IConst.WP, IConst.BR); 
		}
		return promotes;
	}

	public static <X extends MBase> void genLegal(final Movegen gen,long b, final X[] arr) {
		final MWP[] mp=(MWP[])arr;
		long occ=~(gen.bb_piece>>8);
		long m1=b&occ;
		new Adder(gen,m1) {

			@Override
			public void add(int from) {
				if(from>47){
					add(mp[from].P1[0]);
					add(mp[from].P1[1]);
					add(mp[from].P1[2]);
					add(mp[from].P1[3]);
				} else {
					add(mp[from].M1);
				}
			}
		};
		new Adder(gen,m1&0xFF00L&(occ>>8)) {

			@Override
			public void add(int from) {
				add(mp[from].M2);
			}
		};
		final int enp = gen.enpassant;
		long e=gen.bb_black|(1L<<enp);

		new Adder(gen,(b & IConst.LEFTMASK) &(e>>7)) {

			@Override
			public void add(int from) {
				int to=from+7;
				if (to == enp) {
					MOVEDATA md=mp[from].EL;
					if(gen.isSafe(md))
						capture(md);
				} else {
					int ctype=gen.ctype(1L << to);
					if(from<48){
						capture(mp[from].CL[ctype]);
					} else {
						if(from+7==BR_QUEEN_STARTPOS && (gen.castling & CANCASTLE_BLACKQUEEN)!=0){
							capture(MWP.PQ[0]);
							capture(MWP.PQ[1]);
							capture(MWP.PQ[2]);
							capture(MWP.PQ[3]);
						} else {
							capture(mp[from].PL[ctype]);
							capture(mp[from].PL[ctype+5]);
							capture(mp[from].PL[ctype+10]);
							capture(mp[from].PL[ctype+15]);
						}
					}
				}
			}
		};
		new Adder(gen,(b & IConst.RIGHTMASK) &(e>>9)) {

			@Override
			public void add(int from) {
				int to=from+9;
				if (to == enp) {
					MOVEDATA md=mp[from].ER;
					if(gen.isSafe(md))
						capture(md);
				} else {
					int ctype=gen.ctype(1L << to);
					if(from<48){
						capture(mp[from].CR[ctype]);
					} else {
						if(from+9==BR_KING_STARTPOS && (gen.castling & CANCASTLE_BLACKKING)!=0){
							capture(MWP.PK[0]);
							capture(MWP.PK[1]);
							capture(MWP.PK[2]);
							capture(MWP.PK[3]);
						} else {
							capture(mp[from].PR[ctype]);
							capture(mp[from].PR[ctype+5]);
							capture(mp[from].PR[ctype+10]);
							capture(mp[from].PR[ctype+15]);
						}
					}
				}
			}
		};
	}
}
