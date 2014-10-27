package norwegiangambit.engine.movegen;

import norwegiangambit.util.IConst;

public class MBP  extends MBase{

	final int[] CL,CR;	// Capture
	final int EL,ER;  // Enpassant
	final int M1,M2;   // Forward
	final int[] P1,PL,PR;   // Promotion
	static int[] PQ,PK;  // Promotion & Capture rook
	final static long[] REV=new long[64];
	final static MBP[] BP;
	static {
		BP=new MBP[64];
		for (int from = 0; from < 64; from++)
			BP[from] = new MBP(from);
	}

	public MBP(int from) {
		super(from);
		int[] CL=null,CR=null,P1=null,PL=null,PR=null;	
		int EL=0,ER=0,M1=0,M2=0;
		if(from>7 && from < 56){
			if (from > 15) {
				M1=move(from - 8);
				if(from > 47)
					M2=move2(from - 16,from - 8);
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

	private int move(int to) {
		return MOVEDATA.create(assemble(IConst.BP, from, to, CASTLING_STATE));
	}

	private int move2(int to,int enp) {
		return MOVEDATA2.create(assemble(IConst.BP, from, to, CASTLING_STATE),enp);
	}

	private int enpassant(int to) {
		long bitmap = assemble(IConst.BP, from, to, CASTLING_STATE | IConst.SPECIAL);
		return MOVEDATA.create(bitmap | (IConst.WP << IConst._CAPTURE));
	}

	private int[] captures(int to) {
		int[] captures=new int[5];
		for (int i = 0; i < 5; i++) {
			long bitmap = assemble(IConst.BP, from, to, CASTLING_STATE);
			captures[i]=MOVEDATA.capture(bitmap, BCAPTURES[i]);
		}
		return captures;
	}

	private int[] promotes(int to) {
		int[] promotes=new int[4];
		for (int p = 0; p < 4; p++)
			promotes[p]=MOVEDATA.create(assemblePromote(IConst.BP, BPROMOTES[p], from, to, CASTLING_STATE | SPECIAL));
		return promotes;
	}

	private int[] cpromotes(int to) {
		int[] promotes=new int[20];
		for (int p = 0; p < 4; p++)
			for (int i = 0; i < 5; i++)
				promotes[p*5+i]=MOVEDATA.cpromote(from,to, BPROMOTES[p], IConst.BP, BCAPTURES[i]);
		return promotes;
	}

	private int[] cpromotesx(int to) {
		int[] promotes=new int[4];
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
				gen.promote(mp[from].P1[0],1);
				gen.promote(mp[from].P1[1],2);
				gen.promote(mp[from].P1[2],3);
				gen.promote(mp[from].P1[3],4);
			} else {
				gen.move(mp[from].M1);
			}
		}

		long m2 = b&occ&0x00FF000000000000L&(occ<<8);
		pop = Long.bitCount(m2);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(m2);
			m2 ^= 1L << from;
			gen.move(mp[from].M2);
		}

		final int enp = gen.epsq;
		long e=gen.bb_white|(1L<<enp);
		long cl = (b & IConst.MaskBToHFiles) &(e<<9);
		pop = Long.bitCount(cl);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(cl);
			cl ^= 1L << from;
			int to=from-9;
			if (to == enp) {
				int md=mp[from].EL;
				if(gen.isSafeMove(md))
					gen.enpassant(md);
			} else {
				int ctype=gen.ctype(1L << to);
				if(from>15){
					gen.capture(mp[from].CL[ctype], 0, ctype);
				} else {
					if(from-9==WR_QUEEN_STARTPOS && (gen.castling & CANCASTLE_WHITEQUEEN)!=0){
						gen.capturePromote(MBP.PQ[0], 1, ctype);
						gen.capturePromote(MBP.PQ[1], 2, ctype);
						gen.capturePromote(MBP.PQ[2], 3, ctype);
						gen.capturePromote(MBP.PQ[3], 4, ctype);
					} else {
						gen.capturePromote(mp[from].PL, ctype);
					}
				}
			}
		}

		long cr = (b & IConst.MaskAToGFiles) &(e<<7);
		pop = Long.bitCount(cr);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(cr);
			cr ^= 1L << from;
			int to=from-7;
			if (to == enp) {
				int md=mp[from].ER;
				if(gen.isSafeMove(md))
					gen.enpassant(md);
			} else {
				int ctype=gen.ctype(1L << to);
				if(from>15){
					gen.capture(mp[from].CR[ctype], 0, ctype);
				} else {
					if(from-7==WR_KING_STARTPOS && (gen.castling & CANCASTLE_WHITEKING)!=0){
						gen.capturePromote(MBP.PK[0], 1, ctype);
						gen.capturePromote(MBP.PK[1], 2, ctype);
						gen.capturePromote(MBP.PK[2], 3, ctype);
						gen.capturePromote(MBP.PK[3], 4, ctype);
					} else {
						MBP mbp = mp[from];
						gen.capturePromote(mbp.PR, ctype);
					}
				}
			}
		}
	}
}
