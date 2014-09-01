package norwegiangambit.engine.base;

import java.util.Arrays;

import norwegiangambit.util.FEN;
import norwegiangambit.engine.fen.Position;

public class Movegen implements IConst{
	protected Position pos;
	Movegen parent;
	long halfmoves;
	long castling;
	long bb_piece;
	long bb_white;
	long bb_black,bb_bit1,bb_bit2,bb_bit3;
	protected long pinned=0L,checkers=0L;
	protected boolean isWhite=false;
	public boolean isCompare=false;
	int wking,bking;
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
		this.bb_black = pos.get64black();
		this.bb_bit1 = pos.get64bit1();
		this.bb_bit2 = pos.get64bit2();
		this.bb_bit3 = pos.get64bit3();
		this.bb_piece = bb_bit1 | bb_bit2 | bb_bit3;
		this.bb_white = bb_piece ^ bb_black;
		this.enpassant = BITS.getEnpassant(inherit);
		this.wking = pos.getWKpos();
		this.bking = pos.getBKpos();
		halfmoves = (BITS.halfMoves(inherit) + 1) << _HALFMOVES;
		castling = ~CASTLING_STATE | inherit; // all other are set
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
//		if(Long.bitCount(bb_piece)==2){
//			return;
//		}
		final long own = isWhite?bb_white:bb_black;
		final long enemy = isWhite?bb_black:bb_white;
		final int king=isWhite?wking:bking;
		SQATK rev = BASE.REV[king];
		pinned=0L;
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
			nonevasive(isWhite, king,own & ~pinned);
		} else {
			clear(); // not interested in pinned moves for evasive moves
			evasive(isWhite,king,own);
		}
	}

	public void diagPinners(final long own, final int king, long atks) {
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
				pinned|=pinner;
				if((pinner&bb_bit1&bb_bit3)!=0){	// BISHOP / QUEEN
					if((pinner&bb_bit2)!=0){  	// QUEEN
						MOVEDATA[][] mm = isWhite?MWQ.WQ[from].DIAG:MBQ.BQ[from].DIAG;
						MBase b=isWhite?MWQ.WQ[from]:MBQ.BQ[from];
						slide(mm,b,attacker,between);
					} else {
						MOVEDATA[][] mm = isWhite?MWB.WB[from].DIAG:MBB.BB[from].DIAG;
						MBase b=isWhite?MWQ.WQ[from]:MBQ.BQ[from];
						slide(mm,b,attacker,between);
					}
				} else if((pinner&bb_bit1&~bb_bit2&~bb_bit3)!=0){  // PAWN CAPTURE
					if(isWhite){
						if(pinner<<7==attacker && (attacker&IConst.RIGHTLANE)==0)
							moves[iAll++] = MWP.WP[from].CL[ctype(attacker)];
						if(pinner<<9==attacker && (attacker&IConst.LEFTLANE)==0)
							moves[iAll++] = MWP.WP[from].CR[ctype(attacker)];
					} else {
						if(pinner>>9==attacker && (attacker&IConst.RIGHTLANE)==0)
							moves[iAll++] = MBP.BP[from].CL[ctype(attacker)];
						if(pinner>>7==attacker && (attacker&IConst.LEFTLANE)==0)
							moves[iAll++] = MBP.BP[from].CR[ctype(attacker)];
					}
				}
			}
		}
	}

	public void linePinners(final long own, final int king, long atks) {
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
				pinned|=pinner;
				if((pinner&bb_bit2&bb_bit3)!=0){		// ROOK / QUEEN
					if((pinner&bb_bit1)!=0){	// QUEEN
						MOVEDATA[][] mm = isWhite?MWQ.WQ[from].LINE:MBQ.BQ[from].LINE;
						MBase b=isWhite?MWQ.WQ[from]:MBQ.BQ[from];
						slide(mm,b,attacker,between);
					} else {
						MOVEDATA[][] mm = isWhite?MWR.WR[from].LINE:MBR.BR[from].LINE;
						MBase b= isWhite?MWR.WR[from]:MBR.BR[from];
						slide(mm,b,attacker,between);
					}
				} else if((pinner&bb_bit1&~bb_bit2&~bb_bit3)!=0){  // PAWN FORWARD
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

	final public void evasive(boolean isWhite,int king, long t) {
		if (isWhite) {
			MWP.genLegal(this,t & (bb_bit1) & (~bb_bit2) & (~bb_bit3), MWP.WP);
			MWN.genLegal(this,t & (~bb_bit1) & (bb_bit2) & (~bb_bit3), MWN.WN);
			MWB.genLegal(this,t & (bb_bit1) & (~bb_bit2) & (bb_bit3), MWB.WB);
			MWR.genLegal(this,t & (~bb_bit1) & (bb_bit2) & (bb_bit3), MWR.WR);
			MWQ.genLegal(this,t & (bb_bit1) & (bb_bit2) & (bb_bit3), MWQ.WQ);
			while (iTested < iAll) {
				MOVEDATA md = moves[iTested++];
				if (KingSafe.pos(pos,md).isSafeWhite()){
					moves[iLegal++]=md;
				}
			}
			iAll=iLegal;
			MWK.WK[king].genLegal(this);
		} else {
			MBP.genLegal(this,t & (bb_bit1) & (~bb_bit2) & (~bb_bit3), MBP.BP);
			MBN.genLegal(this,t & (~bb_bit1) & (bb_bit2) & (~bb_bit3), MBN.BN);
			MBB.genLegal(this,t & (bb_bit1) & (~bb_bit2) & (bb_bit3), MBB.BB);
			MBR.genLegal(this,t & (~bb_bit1) & (bb_bit2) & (bb_bit3), MBR.BR);
			MBQ.genLegal(this,t & (bb_bit1) & (bb_bit2) & (bb_bit3), MBQ.BQ);
			while (iTested < iAll) {
				MOVEDATA md = moves[iTested++];
				if (KingSafe.pos(pos,md).isSafeBlack())
					moves[iLegal++]=md;
			}
			iAll=iLegal;
			MBK.BK[king].genLegal(this);
		}
	}

	protected void nonevasive(boolean isWhite,int king, long t) {
		if (isWhite) {
			MWP.genLegal(this,t & (bb_bit1)  & (~bb_bit2) & (~bb_bit3), MWP.WP);
			MWN.genLegal(this,t & (~bb_bit1) & (bb_bit2)  & (~bb_bit3), MWN.WN);
			MWB.genLegal(this,t & (bb_bit1)  & (~bb_bit2) & (bb_bit3), MWB.WB);
			MWR.genLegal(this,t & (~bb_bit1) & (bb_bit2)  & (bb_bit3), MWR.WR);
			MWQ.genLegal(this,t & (bb_bit1)  & (bb_bit2)  & (bb_bit3), MWQ.WQ);
			MWK.WK[king].genLegal(this);
			if(king==IConst.WK_STARTPOS)
				MWK.genCastling(this);
		} else {
			MBP.genLegal(this,t & (bb_bit1)  & (~bb_bit2) & (~bb_bit3), MBP.BP);
			MBN.genLegal(this,t & (~bb_bit1) & (bb_bit2)  & (~bb_bit3), MBN.BN);
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
	
	final public MOVEDATA[] quiesce() {
		clear();
		if (pos.whiteNext()) {
			MWP.genLegal(this,bb_white & (bb_bit1) & (~bb_bit2) & (~bb_bit3), MWP.WP);
			MWN.genLegal(this,bb_white & (~bb_bit1) & (bb_bit2) & (~bb_bit3), MWN.WN);
			MWB.genLegal(this,bb_white & (bb_bit1) & (~bb_bit2) & (bb_bit3), MWB.WB);
			MWR.genLegal(this,bb_white & (~bb_bit1) & (bb_bit2) & (bb_bit3), MWR.WR);
			MWQ.genLegal(this,bb_white & (bb_bit1) & (bb_bit2) & (bb_bit3), MWQ.WQ);
			MWK.WK[wking].genLegal(this);
		} else {
			MBP.genLegal(this,bb_black & (bb_bit1) & (~bb_bit2) & (~bb_bit3), MBP.BP);
			MBN.genLegal(this,bb_black & (~bb_bit1) & (bb_bit2) & (~bb_bit3), MBN.BN);
			MBB.genLegal(this,bb_black & (bb_bit1) & (~bb_bit2) & (bb_bit3), MBB.BB);
			MBR.genLegal(this,bb_black & (~bb_bit1) & (bb_bit2) & (bb_bit3), MBR.BR);
			MBQ.genLegal(this,bb_black & (bb_bit1) & (bb_bit2) & (bb_bit3), MBQ.BQ);
			MBK.BK[bking].genLegal(this);
		}
		return Arrays.copyOfRange(moves, 0, iLegal);
	}

	@Override
	public String toString() {
		return FEN.addHorizontal(FEN.addHorizontal(pos.toString(), FEN.board2string(pinned)), FEN.board2string(checkers));
	}

}
