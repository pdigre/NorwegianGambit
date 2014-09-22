package norwegiangambit.engine.base;

import java.util.Arrays;

import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;

/**
 * checkers - current pieces giving check to king
 * pinners  - pinned pieces blocking check
 * hiders   - Discovered Check Candidates
 *
 */
public class Movegen implements IConst{
	public boolean isWhite=false;
	public long bitmap,bb_black,bb_bit1,bb_bit2,bb_bit3;
	public int wking,bking,enpassant;
	long castling,bb_piece,bb_white, own, enemy,bb_knights,bb_kings,bb_pawns;
	protected long checkers;
	protected long pinners;
	long hiders;
	int king,eking;

	public final MOVEDATA[] moves = new MOVEDATA[99];
	public int iAll = 0;
	int iLegal = 0;
	int iTested = 0;
	int iCapture=0;

	final void clear(){
		iLegal = 0;
		iTested = 0;
		iAll=0;
		iCapture=0;
	}

	public void setPos(boolean isWhite, long bitmap, int wking, int bking, long bb, long b1, long b2, long b3) {
		this.bitmap 	= bitmap;
		this.isWhite	= isWhite;
		this.bb_black 	= bb;
		this.bb_bit1 	= b1;
		this.bb_bit2 	= b2;
		this.bb_bit3 	= b3;
		this.wking      = wking;
		this.bking      = bking;
		init();
	}

	public void init() {
		this.king 		= isWhite?wking:bking;
		this.eking 		= isWhite?bking:wking;
		this.bb_piece 	= bb_bit1 | bb_bit2 | bb_bit3;
		this.bb_white 	= bb_piece ^ bb_black;
		this.bb_knights = ~bb_bit1 & bb_bit2 & ~bb_bit3;
		this.bb_kings 	= bb_bit1 & bb_bit2 & ~bb_bit3;
		this.bb_pawns 	= bb_bit1 & ~bb_bit2 & ~bb_bit3;
		this.own 		= isWhite?bb_white:bb_black;
		this.enemy 		= isWhite?bb_black:bb_white;
		this.enpassant 	= BITS.getEnpassant(bitmap);
		this.castling 	= ~CASTLING_STATE | bitmap; // all other are set
	}

	public void make(MOVEDATA md){
		bitmap	  =md.bitmap&(~CASTLING_STATE | bitmap);
		bb_black ^=md.b_black;
		bb_bit1  ^=md.b_bit1;
		bb_bit2  ^=md.b_bit2;
		bb_bit3  ^=md.b_bit3;
		isWhite=!isWhite;
		init();
		int type  = BITS.getPiece(bitmap);
		if(type==IConst.WK)
			wking=BITS.getTo(bitmap);
		else if(type==IConst.BK)
			bking=BITS.getTo(bitmap);
	}
	
	public void undo(MOVEDATA md){
		
	}
	
	final void add(MOVEDATA md) {
		moves[iAll++] = md;
	}
	
	final void capture(MOVEDATA md) {
		moves[iAll++] = moves[iCapture];
		moves[iCapture++]=md;
	}
	
	final int ctype(long bit) {
		return ((bb_bit1 & bit) == 0 ? 0 : 1) + ((bb_bit2 & bit) == 0 ? 0 : 2) + ((bb_bit3 & bit) == 0 ? 0 : 2) - 1;
	}
	
	final public MOVEDATA[] legalmoves() {
		generate();
		return Arrays.copyOfRange(moves, 0, iAll);
	}
	public void generate() {
		clear();
		// Calculate checkers and pinners
		SQATK rev = BASE.REV[king];
		pinners=0L;
		checkers=enemy & ~bb_bit3 & ((~bb_bit1 & bb_bit2 & rev.RN) | (bb_bit1 & ~bb_bit2 & (isWhite?MBP.REV[king]:MWP.REV[king])));
		long sliders=enemy  & bb_bit3 & rev.RQ; // Sliders
		if(checkers==0L && sliders !=0L){
			long diagatks = bb_bit1 & sliders & rev.RB;
			if (diagatks != 0L)
				diagPinners(diagatks);
			long lineatks = bb_bit2 & sliders & rev.RR;
			if (checkers==0L && lineatks != 0L)
				linePinners(lineatks);
		}
		if(checkers==0L){
			nonevasive(own & ~pinners);
		} else {
			clear(); // not interested in pinned moves for evasive moves
			evasive(own);
		}
		order();
	}

	final private  void diagPinners(long atks) {
		int bits = Long.bitCount(atks);
		for (int j = 0; j < bits; j++) {
			int asq = Long.numberOfTrailingZeros(atks);
			long attacker = 1L << asq;
			atks ^= attacker;
			long between = BASE.BETWEEN[asq+64*king];
			long bpcs = between&bb_piece;
			if(bpcs==0L){
				checkers|=attacker;
			} else if(Long.bitCount(bpcs)==1){
				// check for slide moves
				long pinner = between&own;
				int from = Long.numberOfTrailingZeros(pinner);
				pinners|=pinner;
				if((pinner&bb_bit1&bb_bit3)!=0){	// BISHOP / QUEEN
					if((pinner&bb_bit2)!=0){  	// QUEEN
						slide(isWhite?MWQ.WQ[from].DIAG:MBQ.BQ[from].DIAG,isWhite?MWQ.WQ[from]:MBQ.BQ[from],attacker,between);
					} else {
						slide(isWhite?MWB.WB[from].DIAG:MBB.BB[from].DIAG,isWhite?MWQ.WQ[from]:MBQ.BQ[from],attacker,between);
					}
				} else if((pinner&bb_bit1&~bb_bit2&~bb_bit3)!=0){  // PAWN CAPTURE
					int ctype = ctype(attacker);
					if(isWhite){
						MWP mwp = MWP.WP[from];
						if(pinner<<7==attacker && (attacker&IConst.RIGHTLANE)==0L){
							if(from>47){
								capture(mwp.PL[ctype]);
								capture(mwp.PL[ctype+5]);
								capture(mwp.PL[ctype+10]);
								capture(mwp.PL[ctype+15]);
							} else
								capture(mwp.CL[ctype]);
						}
						if(pinner<<9==attacker && (attacker&IConst.LEFTLANE)==0L){
							if(from>47){
								capture(mwp.PR[ctype]);
								capture(mwp.PR[ctype+5]);
								capture(mwp.PR[ctype+10]);
								capture(mwp.PR[ctype+15]);
							} else
								capture(mwp.CR[ctype]);
						}
					} else {
						MBP mbp = MBP.BP[from];
						if(pinner>>9==attacker && (attacker&IConst.RIGHTLANE)==0L){
							if(from<16){
								capture(mbp.PL[ctype]);
								capture(mbp.PL[ctype+5]);
								capture(mbp.PL[ctype+10]);
								capture(mbp.PL[ctype+15]);
							} else
								capture(mbp.CL[ctype]);
						}
						if(pinner>>7==attacker && (attacker&IConst.LEFTLANE)==0L){
							if(from<16){
								capture(mbp.PR[ctype]);
								capture(mbp.PR[ctype+5]);
								capture(mbp.PR[ctype+10]);
								capture(mbp.PR[ctype+15]);
							} else
								capture(mbp.CR[ctype]);
						}
					}
				}
			}
		}
	}

	final private void linePinners(long atks) {
		int bits = Long.bitCount(atks);
		for (int j = 0; j < bits; j++) {
			int asq = Long.numberOfTrailingZeros(atks);
			long attacker = 1L << asq;
			atks ^= attacker;
			long between = BASE.BETWEEN[asq+64*king];
			long bpcs = between&bb_piece;
			if(bpcs==0L){
				checkers|=attacker;
			} else if(Long.bitCount(bpcs)==1){
				// check for slide moves
				long pinner = between&own;
				int from = Long.numberOfTrailingZeros(pinner);
				pinners|=pinner;
				if((pinner&bb_bit2&bb_bit3)!=0){		// ROOK / QUEEN
					if((pinner&bb_bit1)!=0){	// QUEEN
						slide(isWhite?MWQ.WQ[from].LINE:MBQ.BQ[from].LINE,isWhite?MWQ.WQ[from]:MBQ.BQ[from],attacker,between);
					} else {
						slide(isWhite?MWR.WR[from].LINE:MBR.BR[from].LINE,isWhite?MWR.WR[from]:MBR.BR[from],attacker,between);
					}
				} else if((pinner&bb_pawns)!=0){  // PAWN FORWARD
					if(isWhite){
						if(((pinner<<8)&between)!=0){
							add(MWP.WP[from].M1);
							if(from<16 && ((pinner<<16)&between)!=0)
								add(MWP.WP[from].M2);
						}
					} else {
						if(((pinner>>8)&between)!=0){
							add(MBP.BP[from].M1);
							if(from>47 && ((pinner>>16)&between)!=0)
								add(MBP.BP[from].M2);
						}
					}
				}
			}
		}
	}

	final public void evasive(long pieces) {
		if (isWhite) {
			MWP.genLegal(this,pieces & bb_pawns, MWP.WP);
			MWN.genLegal(this,pieces & bb_knights, MWN.WN);
			MWB.genLegal(this,pieces & (bb_bit1) & (~bb_bit2) & (bb_bit3), MWB.WB);
			MWR.genLegal(this,pieces & (~bb_bit1) & (bb_bit2) & (bb_bit3), MWR.WR);
			MWQ.genLegal(this,pieces & (bb_bit1) & (bb_bit2) & (bb_bit3), MWQ.WQ);
			pruneLegal();
			MWK.WK[king].genLegal(this);
		} else {
			MBP.genLegal(this,pieces & bb_pawns, MBP.BP);
			MBN.genLegal(this,pieces & bb_knights, MBN.BN);
			MBB.genLegal(this,pieces & (bb_bit1) & (~bb_bit2) & (bb_bit3), MBB.BB);
			MBR.genLegal(this,pieces & (~bb_bit1) & (bb_bit2) & (bb_bit3), MBR.BR);
			MBQ.genLegal(this,pieces & (bb_bit1) & (bb_bit2) & (bb_bit3), MBQ.BQ);
			pruneLegal();
			MBK.BK[king].genLegal(this);
		}
	}

	public void pruneLegal() {
		while (iTested < iAll) {
			MOVEDATA md = moves[iTested++];
			if (isSafe(md))
				moves[iLegal++]=md;
			else 
				if(iTested<iCapture)
					iCapture--;
		}
		iAll=iLegal;
	}

	protected void nonevasive(long pieces) {
		if (isWhite) {
			MWP.genLegal(this,pieces & bb_pawns, MWP.WP);
			MWN.genLegal(this,pieces & bb_knights, MWN.WN);
			MWB.genLegal(this,pieces & (bb_bit1)  & (~bb_bit2) & (bb_bit3), MWB.WB);
			MWR.genLegal(this,pieces & (~bb_bit1) & (bb_bit2)  & (bb_bit3), MWR.WR);
			MWQ.genLegal(this,pieces & (bb_bit1)  & (bb_bit2)  & (bb_bit3), MWQ.WQ);
			MWK.WK[king].genLegal(this);
			if(king==IConst.WK_STARTPOS)
				MWK.genCastling(this);
		} else {
			MBP.genLegal(this,pieces & bb_pawns, MBP.BP);
			MBN.genLegal(this,pieces & bb_knights, MBN.BN);
			MBB.genLegal(this,pieces & (bb_bit1)  & (~bb_bit2) & (bb_bit3), MBB.BB);
			MBR.genLegal(this,pieces & (~bb_bit1) & (bb_bit2)  & (bb_bit3), MBR.BR);
			MBQ.genLegal(this,pieces & (bb_bit1)  & (bb_bit2)  & (bb_bit3), MBQ.BQ);
			MBK.BK[king].genLegal(this);
			if(king==IConst.BK_STARTPOS)
				MBK.genCastling(this);
		}
	}

	private void slide(MOVEDATA[][] mm,MBase b,long attacker,long between) {
		for (MOVEDATA[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = m[i + 5].bto;
				if((between&bto)!=0){
					add(m[i + 5]);
					i += 6;
					continue;
				}
				if ((attacker & bto) != 0){
					int c = ctype(bto);
					if(c==3){
						if(isWhite){
							if(bto==1L<<IConst.BR_KING_STARTPOS)
								capture(b.K);
							 else if(bto==1L<<IConst.BR_QUEEN_STARTPOS)
								capture(b.Q);
							 else 
								capture(m[i + c]);
						} else {
							if(bto==1L<<IConst.WR_KING_STARTPOS)
								capture(b.K);
							 else if(bto==1L<<IConst.WR_QUEEN_STARTPOS)
								capture(b.Q);
							 else 
								capture(m[i + c]);
						}
					} else {
						capture(m[i + c]);
					}
				}
				break;
			}
		}
	}


	@Override
	public String toString() {
		String string = FEN.board2string(this.bb_bit1, this.bb_bit2, this.bb_bit3, this.bb_black) + "\n " 
				+(" << "+FEN.move2literal(bitmap)+"              ").substring(0,10) + "\n";
		return string;
	}

	public boolean isSafe(MOVEDATA md) {
		return isSafe(isWhite,king,bb_black^md.b_black, bb_bit1^md.b_bit1, bb_bit2^md.b_bit2, bb_bit3^md.b_bit3);
	}

	final public boolean isSafe(int kingpos) {
		long e=isWhite?bb_black:bb_white;
		long rp=isWhite?MBP.REV[kingpos]:MWP.REV[kingpos];
		MWQ rq=MWQ.WQ[kingpos];
		SQATK rev=BASE.REV[kingpos];
		return safeSimple(e,rev,rp) && safeSliders(e, rev, rq);
	}

	final private boolean safeSimple(long e,SQATK rev,long revp) {
		return ((e & bb_knights & rev.RN) == 0) && ((e & bb_kings & rev.RK) == 0) && ((e & bb_pawns & revp) == 0);
	}

	final private  boolean safeSliders(long enemy, SQATK rev, MWQ x) {
		long slider=enemy & bb_bit3;
		if((slider & rev.RQ) !=0){
			long a = bb_piece  & ~(bb_kings & ~enemy);
			if ((bb_bit1 & slider & rev.RB) != 0) {
				long diag=slider & bb_bit1;
				if(ray(diag, x.UL, a)||ray(diag, x.UR, a)||ray(diag, x.DL, a)||ray(diag, x.DR, a))
					return false;
			}
			if ((bb_bit2 & slider & rev.RR) != 0) {
				long line=slider & bb_bit2;
				if(ray(line, x.U, a)||ray(line, x.D, a)||ray(line, x.L, a)||ray(line, x.R, a))
					return false;
			}
		}
		return true;
	}

	public final static boolean isSafe(boolean white,int king,long bb_black, long bb_bit1, long bb_bit2, long bb_bit3) {
		long bb_piece = bb_bit1 | bb_bit2 | bb_bit3;
		long bb_knights = ~bb_bit1 & bb_bit2 & ~bb_bit3;
		long bb_kings = bb_bit1 & bb_bit2 & ~bb_bit3;
		long bb_pawns = bb_bit1 & ~bb_bit2 & ~bb_bit3;
		long enemy=white?bb_black:(bb_bit1 | bb_bit2 | bb_bit3)^bb_black;
		long rp=white?MBP.REV[king]:MWP.REV[king];
		MWQ rq=MWQ.WQ[king];
		SQATK rev=BASE.REV[king];
		if(((enemy & bb_knights & rev.RN) != 0) || ((enemy & bb_kings & rev.RK) != 0) || ((enemy & bb_pawns & rp) != 0))
			return false;
		long slider=enemy & bb_bit3;
		if((slider & rev.RQ) !=0){
			long a = bb_piece  & ~(bb_kings & ~enemy);
			if ((bb_bit1 & slider & rev.RB) != 0) {
				long diag=slider & bb_bit1;
				if(ray(diag, rq.UL, a)||ray(diag, rq.UR, a)||ray(diag, rq.DL, a)||ray(diag, rq.DR, a))
					return false;
			}
			if ((bb_bit2 & slider & rev.RR) != 0) {
				long line=slider & bb_bit2;
				if(ray(line, rq.U, a)||ray(line, rq.D, a)||ray(line, rq.L, a)||ray(line, rq.R, a))
					return false;
			}
		}
		return true;
	}

	final private static boolean ray(long enemy, MOVEDATA[] s, long a) {
		for (MOVEDATA m : s) {
			long bit = m.bto;
			if ((a & bit) != 0)
				return (enemy & bit) != 0;
		}
		return false;
	}
	
	void order(){
		findDiscoveredCheckCandidates();
	}

	private void findDiscoveredCheckCandidates() {
		SQATK rev = BASE.REV[eking];
		hiders=0L;
		long sliders=own & bb_bit3 & rev.RQ;
		if(sliders !=0L){
			long diagatks = sliders & bb_bit1 & rev.RB;
			if (diagatks != 0L) {
				int bits = Long.bitCount(diagatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(diagatks);
					long attacker = 1L << asq;
					diagatks ^= attacker;
					long between = BASE.BETWEEN[asq+64*eking];
					if(Long.bitCount(between&bb_piece)==1)
						hiders|=between&own;
				}
			}
			long lineatks = sliders & bb_bit2 & rev.RR;
			if (lineatks != 0L) {
				int bits = Long.bitCount(lineatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(lineatks);
					long attacker = 1L << asq;
					lineatks ^= attacker;
					long between = BASE.BETWEEN[asq+64*eking];
					if(Long.bitCount(between&bb_piece)==1)
						hiders|=between&own;
				}
			}
		}
	}
}
