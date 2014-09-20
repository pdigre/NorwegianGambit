package norwegiangambit.engine.base;

import java.util.Arrays;

import norwegiangambit.engine.fen.Position;
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
	protected Position pos;
	Movegen parent;
	long halfmoves;
	long castling;
	long bb_piece;
	long bb_white;
	long bb_black,bb_bit1,bb_bit2,bb_bit3;
	long bb_knights,bb_kings,bb_pawns;
	protected long checkers=0L,pinners=0L,hiders=0L;
	protected boolean isWhite=false;
	public boolean isCompare=false;
	int king,eking;
	public int enpassant;


	public final MOVEDATA[] moves = new MOVEDATA[99];
	public int iAll = 0;
	int iLegal = 0;
	int iTested = 0;

	final void clear(){
		iLegal = 0;
		iTested = 0;
		iAll=0;
	}

	public Movegen(Position pos) {
		setPos(pos);
	}

	public Movegen() {
	}

	public void setPos(Position pos) {
		this.pos = pos;
		long inherit = pos.getBitmap();
		this.isWhite=pos.whiteNext();
		this.bb_black = pos.get64black();
		this.bb_bit1 = pos.get64bit1();
		this.bb_bit2 = pos.get64bit2();
		this.bb_bit3 = pos.get64bit3();
		this.king =isWhite?pos.getWKpos():pos.getBKpos();
		this.eking =isWhite?pos.getBKpos():pos.getWKpos();
		this.bb_piece = bb_bit1 | bb_bit2 | bb_bit3;
		this.bb_white = bb_piece ^ bb_black;
		this.bb_knights = ~bb_bit1 & bb_bit2 & ~bb_bit3;
		this.bb_kings = bb_bit1 & bb_bit2 & ~bb_bit3;
		this.bb_pawns = bb_bit1 & ~bb_bit2 & ~bb_bit3;
		this.enpassant = BITS.getEnpassant(inherit);
		halfmoves = (BITS.halfMoves(inherit) + 1) << _HALFMOVES;
		castling = ~CASTLING_STATE | inherit; // all other are set
	}

	public void make(MOVEDATA md){
		setPos(pos.move(md));
	}
	
	public void undo(MOVEDATA md){
		
	}
	
	final void add(MOVEDATA md) {
		if(md==null)
			System.out.println("err");
		moves[iAll++] = md;
	}
	
	final int type(long bit) {
		return ((bb_bit1 & bit) == 0 ? 0 : 1) | ((bb_bit2 & bit) == 0 ? 0 : 2) | ((bb_bit3 & bit) == 0 ? 0 : 4);
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
		isWhite = pos.whiteNext();
		final long own = isWhite?bb_white:bb_black;
		final long enemy = isWhite?bb_black:bb_white;
		SQATK rev = BASE.REV[king];
		pinners=0L;
		checkers=~bb_bit3 & enemy & ((~bb_bit1 & bb_bit2 & rev.RN) | (bb_bit1 & ~bb_bit2 & (isWhite?MBP.REV[king]:MWP.REV[king])));
		long eslider=bb_bit3 & enemy  & rev.RQ; // Sliders
		if(checkers==0L && eslider !=0L){
			long diagatks = bb_bit1 & eslider & rev.RB;
			if (diagatks != 0L)
				diagPinners(own, king, diagatks);
			long lineatks = bb_bit2 & eslider & rev.RR;
			if (checkers==0L && lineatks != 0L)
				linePinners(own, king, lineatks);
		}
		if(checkers==0L){
			nonevasive(king,own & ~pinners);
		} else {
			clear(); // not interested in pinned moves for evasive moves
			evasive(king,own);
		}
	}

	final private  void diagPinners(final long own, final int king, long atks) {
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
								moves[iAll++] = mwp.PL[ctype];
								moves[iAll++] = mwp.PL[ctype+5];
								moves[iAll++] = mwp.PL[ctype+10];
								moves[iAll++] = mwp.PL[ctype+15];
							} else
								moves[iAll++] = mwp.CL[ctype];
						}
						if(pinner<<9==attacker && (attacker&IConst.LEFTLANE)==0L){
							if(from>47){
								moves[iAll++] = mwp.PR[ctype];
								moves[iAll++] = mwp.PR[ctype+5];
								moves[iAll++] = mwp.PR[ctype+10];
								moves[iAll++] = mwp.PR[ctype+15];
							} else
								moves[iAll++] = mwp.CR[ctype];
						}
					} else {
						MBP mbp = MBP.BP[from];
						if(pinner>>9==attacker && (attacker&IConst.RIGHTLANE)==0L){
							if(from<16){
								moves[iAll++] = mbp.PL[ctype];
								moves[iAll++] = mbp.PL[ctype+5];
								moves[iAll++] = mbp.PL[ctype+10];
								moves[iAll++] = mbp.PL[ctype+15];
							} else
								moves[iAll++] = mbp.CL[ctype];
						}
						if(pinner>>7==attacker && (attacker&IConst.LEFTLANE)==0L){
							if(from<16){
								moves[iAll++] = mbp.PR[ctype];
								moves[iAll++] = mbp.PR[ctype+5];
								moves[iAll++] = mbp.PR[ctype+10];
								moves[iAll++] = mbp.PR[ctype+15];
							} else
								moves[iAll++] = mbp.CR[ctype];
						}
					}
				}
			}
		}
	}

	final private void linePinners(final long own, final int king, long atks) {
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
							moves[iAll++] = MWP.WP[from].M1;
							if(from<16 && ((pinner<<16)&between)!=0)
								moves[iAll++] = MWP.WP[from].M2;
						}
					} else {
						if(((pinner>>8)&between)!=0){
							moves[iAll++] = MBP.BP[from].M1;
							if(from>47 && ((pinner>>16)&between)!=0)
								moves[iAll++] = MBP.BP[from].M2;
						}
					}
				}
			}
		}
	}

	final public void evasive(int king, long t) {
		if (isWhite) {
			MWP.genLegal(this,t & bb_pawns, MWP.WP);
			MWN.genLegal(this,t & bb_knights, MWN.WN);
			MWB.genLegal(this,t & (bb_bit1) & (~bb_bit2) & (bb_bit3), MWB.WB);
			MWR.genLegal(this,t & (~bb_bit1) & (bb_bit2) & (bb_bit3), MWR.WR);
			MWQ.genLegal(this,t & (bb_bit1) & (bb_bit2) & (bb_bit3), MWQ.WQ);
			while (iTested < iAll) {
				MOVEDATA md = moves[iTested++];
				if (isSafe(md))
					moves[iLegal++]=md;
			}
			iAll=iLegal;
			MWK.WK[king].genLegal(this);
		} else {
			MBP.genLegal(this,t & bb_pawns, MBP.BP);
			MBN.genLegal(this,t & bb_knights, MBN.BN);
			MBB.genLegal(this,t & (bb_bit1) & (~bb_bit2) & (bb_bit3), MBB.BB);
			MBR.genLegal(this,t & (~bb_bit1) & (bb_bit2) & (bb_bit3), MBR.BR);
			MBQ.genLegal(this,t & (bb_bit1) & (bb_bit2) & (bb_bit3), MBQ.BQ);
			while (iTested < iAll) {
				MOVEDATA md = moves[iTested++];
				if (isSafe(md))
					moves[iLegal++]=md;
			}
			iAll=iLegal;
			MBK.BK[king].genLegal(this);
		}
	}

	protected void nonevasive(int king, long t) {
		if (isWhite) {
			MWP.genLegal(this,t & bb_pawns, MWP.WP);
			MWN.genLegal(this,t & bb_knights, MWN.WN);
			MWB.genLegal(this,t & (bb_bit1)  & (~bb_bit2) & (bb_bit3), MWB.WB);
			MWR.genLegal(this,t & (~bb_bit1) & (bb_bit2)  & (bb_bit3), MWR.WR);
			MWQ.genLegal(this,t & (bb_bit1)  & (bb_bit2)  & (bb_bit3), MWQ.WQ);
			MWK.WK[king].genLegal(this);
			if(king==IConst.WK_STARTPOS)
				MWK.genCastling(this);
		} else {
			MBP.genLegal(this,t & bb_pawns, MBP.BP);
			MBN.genLegal(this,t & bb_knights, MBN.BN);
			MBB.genLegal(this,t & (bb_bit1)  & (~bb_bit2) & (bb_bit3), MBB.BB);
			MBR.genLegal(this,t & (~bb_bit1) & (bb_bit2)  & (bb_bit3), MBR.BR);
			MBQ.genLegal(this,t & (bb_bit1)  & (bb_bit2)  & (bb_bit3), MBQ.BQ);
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
					moves[iAll++] = m[i + 5];
					i += 6;
					continue;
				}
				if ((attacker & bto) != 0){
					int c = ctype(bto);
					if(c==3){
						if(isWhite){
							if(bto==1L<<IConst.BR_KING_STARTPOS)
								add(b.K);
							 else if(bto==1L<<IConst.BR_QUEEN_STARTPOS)
								add(b.Q);
							 else 
								moves[iAll++] = m[i + c];
						} else {
							if(bto==1L<<IConst.WR_KING_STARTPOS)
								add(b.K);
							 else if(bto==1L<<IConst.WR_QUEEN_STARTPOS)
								add(b.Q);
							 else 
								moves[iAll++] = m[i + c];
						}
					} else {
						moves[iAll++] = m[i + c];
					}
				}
				break;
			}
		}
	}


	@Override
	public String toString() {
		return FEN.addHorizontal(FEN.addHorizontal(pos.toString(), FEN.board2string(pinners)), FEN.board2string(checkers));
	}

	public boolean isSafe(MOVEDATA md) {
		long bb = bb_black^md.b_black;
		long b1 = bb_bit1^md.b_bit1;
		long b2 = bb_bit2^md.b_bit2;
		long b3 = bb_bit3^md.b_bit3;
		return isSafe(isWhite,king,bb, b1, b2, b3);
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
		long e=white?bb_black:(bb_bit1 | bb_bit2 | bb_bit3)^bb_black;
		long rp=white?MBP.REV[king]:MWP.REV[king];
		MWQ rq=MWQ.WQ[king];
		SQATK rev=BASE.REV[king];
		if(((e & bb_knights & rev.RN) != 0) || ((e & bb_kings & rev.RK) != 0) || ((e & bb_pawns & rp) != 0))
			return false;
		long slider=e & bb_bit3;
		if((slider & rev.RQ) !=0){
			long a = bb_piece  & ~(bb_kings & ~e);
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
}
