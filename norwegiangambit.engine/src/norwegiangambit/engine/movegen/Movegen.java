package norwegiangambit.engine.movegen;

import java.util.Arrays;

import norwegiangambit.engine.movegen.BASE.SQATK;
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
	public long bb_black,bb_bit1,bb_bit2,bb_bit3;
	public long bitmap;
	public long bb_piece,castling;
	public int wking,bking,epsq;
	long bb_white, own, enemy,bb_knights,bb_kings,bb_pawns;
	protected long checkers, pinners, hiders;
	int king,eking;

	public final int[] moves = new int[99];
	public int iAll = 0, lvl1=0,lvl2=0;
	int iLegal = 0, iTested = 0, lvl3=0;

	final void clear(){
		iLegal = 0;
		iTested = 0;
		lvl1=0;
		lvl2=0;
		lvl3=0;
		iAll=0;
	}

	public String getFen() {
		return FEN.board2fen(FEN.boardFrom64(bb_bit1, bb_bit2, bb_bit3, bb_black)) + " " +(isWhite?"w":"b") + " " + FEN.getFenCastling(castling) + " "+ FEN.pos2string(epsq);
	}

	public void make(int md_num,boolean isWhite, long bitmap, int wking, int bking, long bb, long b1, long b2, long b3) {
		MOVEDATA md 	= BASE.ALL[md_num];
		this.isWhite	= !isWhite;
		this.bb_black 	= bb^md.b_black;
		this.bb_bit1 	= b1^md.b_bit1;
		this.bb_bit2 	= b2^md.b_bit2;
		this.bb_bit3 	= b3^md.b_bit3;
		this.wking      = wking;
		this.bking      = bking;
		this.epsq 		= md instanceof MOVEDATA2?((MOVEDATA2)md).epsq:-1;
		int type  = BITS.getPiece(md.bitmap);
		if(type==IConst.WK)
			this.wking=BITS.getTo(md.bitmap);
		else if(type==IConst.BK)
			this.bking=BITS.getTo(md.bitmap);

		this.castling 	= CASTLING_STATE & md.bitmap & bitmap; // all other are set
		long castling1	= CASTLING_STATE & bitmap;
		if(md instanceof MOVEDATAX){
			long castling2=((bitmap^((MOVEDATAX) md).castling)) & CASTLING_STATE;
			if(castling != castling2){
				System.out.println(md.id()+"\n"+FEN.addHorizontal(FEN.addHorizontal(FEN.board2string(bitmap),FEN.board2string(castling)),FEN.addHorizontal(FEN.board2string(castling2),FEN.board2string(((MOVEDATAX) md).castling))));
				System.out.println("castling");
			}
		} else {
			if(castling1!=castling){
				System.out.println(md.id()+"\n"+FEN.addHorizontal(FEN.board2string(castling),FEN.board2string(castling1)));
				System.out.println("non");
			}
		}

		this.bitmap	    = md.bitmap&(~CASTLING_STATE | bitmap);
		

		init();
	}
	public void set(boolean isWhite, long bitmap, int wking, int bking, long bb, long b1, long b2, long b3) {
		this.bitmap 	= bitmap;
		this.isWhite	= isWhite;
		this.bb_black 	= bb;
		this.bb_bit1 	= b1;
		this.bb_bit2 	= b2;
		this.bb_bit3 	= b3;
		this.wking      = wking;
		this.bking      = bking;
		this.epsq 		= BITS.getEnpassant(bitmap);
		this.castling 	= ~CASTLING_STATE | bitmap; // all other are set
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
	}

	public void undo(MOVEDATA md){
		
	}
	
	/**
	 * - 1 - 
	 * good
	 * promoteCapture
	 * promote
	 * 
	 * - 2 -
	 * equal
	 * enpassant
	 * 
	 * - 3 -
	 * bad
	 * castling
	 * quiet
	 * 
	 * - iAll -
	 * 
	 * 
	 * @param md
	 */
	final public void capturePromote(int md, int type, int victim) {
		sort1(md);
	}

	final public void promote(int md,int type) {
		sort1(md);
	}
	
	final public void capture(int md, int type, int victim) {
		if(type<victim){ // Good
			sort1(md);
		} else if(type==victim){ // Equal
			sort2(md);
		} else { // Bad
			sort3(md);
		}
	}

	final private void sort1(int md) {
		moves[iAll++] = moves[lvl3];
		moves[lvl3++] = moves[lvl2];
		moves[lvl2++] = moves[lvl1];
		moves[lvl1++]=md;
	}
	
	final private void sort2(int md) {
		moves[iAll++] = moves[lvl3];
		moves[lvl3++] = moves[lvl2];
		moves[lvl2++]=md;
	}
	
	final private void sort3(int md) {
		moves[iAll++] = moves[lvl3];
		moves[lvl3++]=md;
	}

	final public void enpassant(int md) {
		sort2(md);
	}
	
	final public void move(int md) {
		moves[iAll++] = md;
	}
	
	final public void castling(int md) {
		moves[iAll++] = md;
	}
	
	final public void quiet(int md) {
		moves[iAll++] = md;
	}
	
	final int ctype(long bit) {
		return ((bb_bit1 & bit) == 0 ? 0 : 1) + ((bb_bit2 & bit) == 0 ? 0 : 2) + ((bb_bit3 & bit) == 0 ? 0 : 2) - 1;
	}
	
	final public int[] legalmoves() {
		generate();
		return Arrays.copyOfRange(moves, 0, iAll);
	}

	public void pruneLegal() {
		while (iTested < iAll) {
			int md = moves[iTested++];
			if (isSafeMove(md)){
				moves[iLegal++]=md;
			} else 
				if(iTested<lvl3)
					lvl3--;
		}
		iAll=iLegal;
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
						slide(isWhite?MWQ.WQ[from].DIAG:MBQ.BQ[from].DIAG,isWhite?MWQ.WQ[from]:MBQ.BQ[from],attacker,between, 4);
					} else {
						slide(isWhite?MWB.WB[from].DIAG:MBB.BB[from].DIAG,isWhite?MWQ.WQ[from]:MBQ.BQ[from],attacker,between, 2);
					}
				} else if((pinner&bb_bit1&~bb_bit2&~bb_bit3)!=0){  // PAWN CAPTURE
					int ctype = ctype(attacker);
					if(isWhite){
						MWP mwp = MWP.WP[from];
						if(pinner<<7==attacker && (attacker&IConst.RIGHTLANE)==0L){
							if(from>47){
								capturePromote(mwp.PL, ctype);
							} else
								capture(mwp.CL[ctype], 0, ctype);
						}
						if(pinner<<9==attacker && (attacker&IConst.LEFTLANE)==0L){
							if(from>47){
								capturePromote(mwp.PR, ctype);
							} else
								capture(mwp.CR[ctype], 0, ctype);
						}
					} else {
						MBP mbp = MBP.BP[from];
						if(pinner>>9==attacker && (attacker&IConst.RIGHTLANE)==0L){
							if(from<16){
								capturePromote(mbp.PL, ctype);
							} else
								capture(mbp.CL[ctype], 0, ctype);
						}
						if(pinner>>7==attacker && (attacker&IConst.LEFTLANE)==0L){
							if(from<16){
								capturePromote(mbp.PR, ctype);
							} else
								capture(mbp.CR[ctype], 0, ctype);
						}
					}
				}
			}
		}
	}

	public void capturePromote(int[] mvs, int ctype) {
		if(mvs==null)
			return;
		capturePromote(mvs[ctype], 1, ctype);
		capturePromote(mvs[ctype+5], 2, ctype);
		capturePromote(mvs[ctype+10], 3, ctype);
		capturePromote(mvs[ctype+15], 4, ctype);
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
						slide(isWhite?MWQ.WQ[from].LINE:MBQ.BQ[from].LINE,isWhite?MWQ.WQ[from]:MBQ.BQ[from],attacker,between, 4);
					} else {
						slide(isWhite?MWR.WR[from].LINE:MBR.BR[from].LINE,isWhite?MWR.WR[from]:MBR.BR[from],attacker,between, 3);
					}
				} else if((pinner&bb_pawns)!=0){  // PAWN FORWARD
					if(isWhite){
						if(((pinner<<8)&between)!=0){
							move(MWP.WP[from].M1);
							if(from<16 && ((pinner<<16)&between)!=0)
								move(MWP.WP[from].M2);
						}
					} else {
						if(((pinner>>8)&between)!=0){
							move(MBP.BP[from].M1);
							if(from>47 && ((pinner>>16)&between)!=0)
								move(MBP.BP[from].M2);
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

	private void slide(int[][] mm,MBase b,long attacker,long between, int type) {
		for (int[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = BASE.getBTo(m[i + 5]);
				if((between&bto)!=0){
					move(m[i + 5]);
					i += 6;
					continue;
				}
				if ((attacker & bto) != 0){
					int c = ctype(bto);
					if(c==3){
						if(isWhite){
							if(bto==1L<<IConst.BR_KING_STARTPOS && (castling&CANCASTLE_BLACKKING)!=0)
								capture(b.K, type, c);
							 else if(bto==1L<<IConst.BR_QUEEN_STARTPOS && (castling&CANCASTLE_BLACKQUEEN)!=0)
								capture(b.Q, type, c);
							 else 
								capture(m[i + c], type, c);
						} else {
							if(bto==1L<<IConst.WR_KING_STARTPOS && (castling&CANCASTLE_WHITEKING)!=0)
								capture(b.K, type, c);
							 else if(bto==1L<<IConst.WR_QUEEN_STARTPOS && (castling&CANCASTLE_WHITEQUEEN)!=0)
								capture(b.Q, type, c);
							 else 
								capture(m[i + c], type, c);
						}
					} else {
						capture(m[i + c], type, c);
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

	public boolean isSafeMove(int md) {
		MOVEDATA m=BASE.ALL[md];
		return isSafe(isWhite,king,bb_black^m.b_black, bb_bit1^m.b_bit1, bb_bit2^m.b_bit2, bb_bit3^m.b_bit3);
	}

	final public boolean isSafeKingMove(int md) {
		return isSafePos(BITS.getTo(BASE.ALL[md].bitmap));
	}
	
	final public boolean isSafePos(int kingpos) {
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
				if(ray(diag, x.DIAG[0], a)||ray(diag, x.DIAG[1], a)||ray(diag, x.DIAG[2], a)||ray(diag, x.DIAG[3], a))
					return false;
			}
			if ((bb_bit2 & slider & rev.RR) != 0) {
				long line=slider & bb_bit2;
				if(ray(line, x.LINE[0], a)||ray(line, x.LINE[1], a)||ray(line, x.LINE[2], a)||ray(line, x.LINE[3], a))
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
				if(ray(diag, rq.DIAG[0], a)||ray(diag, rq.DIAG[1], a)||ray(diag, rq.DIAG[2], a)||ray(diag, rq.DIAG[3], a))
					return false;
			}
			if ((bb_bit2 & slider & rev.RR) != 0) {
				long line=slider & bb_bit2;
				if(ray(line, rq.LINE[0], a)||ray(line, rq.LINE[1], a)||ray(line, rq.LINE[2], a)||ray(line, rq.LINE[3], a))
					return false;
			}
		}
		return true;
	}

	final private static boolean ray(long enemy, int[] s, long a) {
		for (int m : s) {
			long bit = BASE.getBTo(m);
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
	
    
    public void sortKiller(int md){
    	if(md!=-1){
        	int start = lvl2;
        	int from  = lvl2;
			for (int i = start; i < iAll; i++) {
    			if(moves[i]==md){
    				for (int j = i; j > from; j--)
    					moves[j]=moves[j-1];
    				moves[from]=md;
    				break;
    			}
    		}
    	}
    }
	
}
