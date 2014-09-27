package norwegiangambit.engine.movegen;

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
					if(from > 23 && from < 32)
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
					if(from > 23 && from < 32)
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
		return MOVEDATA.create(assemble(IConst.BP, from, to, CASTLING_STATE));
	}

	private MOVEDATA enpassant(int to) {
		long bitmap = assemble(IConst.BP, from, to, CASTLING_STATE | IConst.SPECIAL);
		return MOVEDATA.create(bitmap | (IConst.WP << IConst._CAPTURE));
	}

	private MOVEDATA[] captures(int to) {
		MOVEDATA[] captures=new MOVEDATA[5];
		for (int i = 0; i < 5; i++) {
			long bitmap = assemble(IConst.BP, from, to, CASTLING_STATE);
			captures[i]=MOVEDATA.capture(bitmap, BCAPTURES[i]);
		}
		return captures;
	}

	private MOVEDATA[] promotes(int to) {
		MOVEDATA[] promotes=new MOVEDATA[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(assemblePromote(IConst.BP, BPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
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
		int pop = Long.bitCount(m1);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(m1);
			m1 ^= 1L << from;
			if(from<16){
				gen.add(mp[from].P1[0]);
				gen.add(mp[from].P1[1]);
				gen.add(mp[from].P1[2]);
				gen.add(mp[from].P1[3]);
			} else {
				gen.add(mp[from].M1);
			}
		}

		long m2 = b&occ&0x00FF000000000000L&(occ<<8);
		pop = Long.bitCount(m2);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(m2);
			m2 ^= 1L << from;
			gen.add(mp[from].M2);
		}

		final int enp = gen.enpassant;
		long e=gen.bb_white|(1L<<enp);
		long cl = (b & IConst.LEFTMASK) &(e<<9);
		pop = Long.bitCount(cl);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(cl);
			cl ^= 1L << from;
			int to=from-9;
			if (to == enp) {
				MOVEDATA md=mp[from].EL;
				if(gen.isSafe(md))
					gen.capture(md);
			} else {
				int ctype=gen.ctype(1L << to);
				if(from>15){
					gen.capture(mp[from].CL[ctype]);
				} else {
					if(from-9==WR_QUEEN_STARTPOS && (gen.castling & CANCASTLE_WHITEQUEEN)!=0){
						gen.capture(MBP.PQ[0]);
						gen.capture(MBP.PQ[1]);
						gen.capture(MBP.PQ[2]);
						gen.capture(MBP.PQ[3]);
					} else {
						gen.capture(mp[from].PL[ctype]);
						gen.capture(mp[from].PL[ctype+5]);
						gen.capture(mp[from].PL[ctype+10]);
						gen.capture(mp[from].PL[ctype+15]);
					}
				}
			}
		}

		long cr = (b & IConst.RIGHTMASK) &(e<<7);
		pop = Long.bitCount(cr);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(cr);
			cr ^= 1L << from;
			int to=from-7;
			if (to == enp) {
				MOVEDATA md=mp[from].ER;
				if(gen.isSafe(md))
					gen.capture(md);
			} else {
				int ctype=gen.ctype(1L << to);
				if(from>15){
					gen.capture(mp[from].CR[ctype]);
				} else {
					if(from-7==WR_KING_STARTPOS && (gen.castling & CANCASTLE_WHITEKING)!=0){
						gen.capture(MBP.PK[0]);
						gen.capture(MBP.PK[1]);
						gen.capture(MBP.PK[2]);
						gen.capture(MBP.PK[3]);
					} else {
						MBP mbp = mp[from];
						gen.capture(mbp.PR[ctype]);
						gen.capture(mbp.PR[ctype+5]);
						gen.capture(mbp.PR[ctype+10]);
						gen.capture(mbp.PR[ctype+15]);
					}
				}
			}
		}
	}
}
