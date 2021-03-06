package norwegiangambit.engine.base;

import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.IConst;

public class MBP  extends MBase{

	final MOVEDATA[] CL,CR;	// Capture
	final MOVEDATA EL,ER;  // Enpassant
	final MOVEDATA M1,M2;   // Forward
	final MOVEDATA[] P1,PL,PR;   // Promotion
	static MOVEDATA[] PQ,PK;  // Promotion & Capture rook
	final static long[] REV=new long[64];
	final static MBP[] BP;
	static {
		BP=new MBP[64];
		for (int from = 0; from < 64; from++)
			BP[from] = new MBP(from);
	}

	public MBP(int from) {
		super(from);
		MOVEDATA[] CL=null,CR=null,P1=null,PL=null,PR=null;	
		MOVEDATA EL=null,ER=null,M1=null,M2=null;
		if(from>7 && from < 56){
			if (from > 15) {
				M1=move(from - 8);
				if(from > 47)
					M2=move(from - 16);
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
						PQ=cpromotesx(to);
				} else {
					CL=captures(to);
					if(from > 15 && from < 32)
						EL=enpassant(to);
				}
			}
			// RIGHT
			if (x != 7) {
				int to = from - 7;
				REV[to] |= (1L << from);
				if(from<16){
					PR=cpromotes(to);
					if(to==WR_KING_STARTPOS)
						PK=cpromotesx(to);
				} else {
				    CR=captures(to);
					if(from > 15 && from < 32)
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
		return MOVEDATA.create(PSQT.assemble(IConst.BP, from, to, CASTLING_STATE));
	}

	private MOVEDATA enpassant(int to) {
		long bitmap = PSQT.assemble(IConst.BP, from, to, CASTLING_STATE | IConst.SPECIAL);
		return MOVEDATA.create(purge(bitmap,PSQT.pVal(to - 8, IConst.WP)) | (IConst.WP << IConst._CAPTURE));
	}

	private MOVEDATA[] captures(int to) {
		MOVEDATA[] captures=new MOVEDATA[5];
		for (int i = 0; i < 5; i++) {
			long bitmap = PSQT.assemble(IConst.BP, from, to, CASTLING_STATE);
			captures[i]=MOVEDATA.capture(bitmap, BCAPTURES[i]);
		}
		return captures;
	}

	private MOVEDATA[] promotes(int to) {
		MOVEDATA[] promotes=new MOVEDATA[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(PSQT.assemblePromote(IConst.BP, BPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
		return promotes;
	}

	private MOVEDATA[] cpromotes(int to) {
		MOVEDATA[] promotes=new MOVEDATA[20];
		for (int p = 0; p < 4; p++)
			for (int i = 0; i < 5; i++)
				promotes[p*5+i]=MOVEDATA.cpromote(from,to, BPROMOTES[p], IConst.BP, BCAPTURES[i]);
		return promotes;
	}

	private MOVEDATA[] cpromotesx(int to) {
		MOVEDATA[] promotes=new MOVEDATA[4];
		for (int p = 0; p < 4; p++){
			promotes[p]=MOVEDATAX.cpromote(from,to, BPROMOTES[p], IConst.BP, IConst.WR);
		}
		return promotes;
	}

	public static <X extends MBase> void genLegal(Movegen gen,long b, X[] arr) {
		final MBP[] mp=(MBP[])arr;
		long occ=~(gen.bb_piece<<8);
		long m1=b&occ;
		new Adder(gen,m1) {

			@Override
			public void add(int from) {
				MBP mbp = mp[from];
				if(from<16){
					if(mbp.P1==null)
						System.out.println(gen.pos.toString());
					add(mbp.P1[0]);
					add(mbp.P1[1]);
					add(mbp.P1[2]);
					add(mbp.P1[3]);
				} else {
					add(mbp.M1);
				}
			}
		};
		new Adder(gen,m1&0x00FF000000000000L&(occ<<8)) {

			@Override
			public void add(int from) {
				add(mp[from].M2);
			}
		};
		
		final int enp = gen.enpassant;
		long e=gen.bb_white|(1L<<enp);

		new Adder(gen,(b & IConst.LEFTMASK) &(e<<9)) {

			@Override
			public void add(int from) {
				int to=from-9;
				if (to == enp) {
					MOVEDATA md=mp[from].EL;
					if(KingSafe.pos(gen.pos, md).isSafeBlack())
						add(md);
				} else {
					int ctype=gen.ctype(1L << to);
					if(from>15){
						add(mp[from].CL[ctype]);
					} else {
						if(from-9==WR_QUEEN_STARTPOS && (gen.castling & CANCASTLE_WHITEQUEEN)!=0){
							Position next = gen.pos.move(MBP.PQ[0]);
							add(MBP.PQ[0]);
							add(MBP.PQ[1]);
							add(MBP.PQ[2]);
							add(MBP.PQ[3]);
						} else {
							add(mp[from].PL[ctype]);
							add(mp[from].PL[ctype+5]);
							add(mp[from].PL[ctype+10]);
							add(mp[from].PL[ctype+15]);
						}
					}
				}
			}
		};
		new Adder(gen,(b & IConst.RIGHTMASK) &(e<<7)) {

			@Override
			public void add(int from) {
				int to=from-7;
				if (to == enp) {
					MOVEDATA md=mp[from].ER;
					if(KingSafe.pos(gen.pos, md).isSafeBlack())
						add(md);
				} else {
					int ctype=gen.ctype(1L << to);
					if(from>15){
						add(mp[from].CR[ctype]);
					} else {
						if(from-7==WR_KING_STARTPOS && (gen.castling & CANCASTLE_WHITEKING)!=0){
							MOVEDATA t = MBP.PK[0];
							Position next = gen.pos.move(t);
							add(t);
							add(MBP.PK[1]);
							add(MBP.PK[2]);
							add(MBP.PK[3]);
						} else {
							MBP mbp = mp[from];
							add(mbp.PR[ctype]);
							add(mbp.PR[ctype+5]);
							add(mbp.PR[ctype+10]);
							add(mbp.PR[ctype+15]);
						}
					}
				}
			}
		};
	}
}
