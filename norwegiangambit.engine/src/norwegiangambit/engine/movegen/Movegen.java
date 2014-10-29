package norwegiangambit.engine.movegen;

import java.util.Arrays;

import norwegiangambit.util.BITS;
import norwegiangambit.util.BitBoard;
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
	protected long checkers, pinners, support, unsafe;
	int king,eking;
	public int pv;

	public final int[] moves = new int[99];
	public int iAll = 0, lvl1=0,lvl2=0;
	int iTested = 0, lvl3=0;

	final void clear(){
		iTested = 0;
		lvl1=0;
		lvl2=0;
		lvl3=0;
		iAll=0;
	}

	public String getFen() {
		return FEN.board2fen(FEN.boardFrom64(bb_bit1, bb_bit2, bb_bit3, bb_black)) + " " +(isWhite?"w":"b") + " " + FEN.getFenCastling(castling) + " "+ FEN.pos2string(epsq);
	}

	public void make(int md_num,boolean isWhite, long castling, int wking, int bking, long b1, long b2, long b3, long bb) {
		MOVEDATA md 	= MBase.ALL[md_num];
		this.pv			= md_num;
		this.isWhite	= !isWhite;
		this.bb_black 	= bb^md.b_black;
		this.bb_bit1 	= b1^md.b_bit1;
		this.bb_bit2 	= b2^md.b_bit2;
		this.bb_bit3 	= b3^md.b_bit3;
		this.wking      = wking;
		this.bking      = bking;
		this.epsq 		= md instanceof MOVEDATA2?((MOVEDATA2)md).epsq:-1;
		this.castling	= castling;
		int type  = BITS.getPiece(md.bitmap);
		if(type==IConst.WK)
			this.wking=BITS.getTo(md.bitmap);
		else if(type==IConst.BK)
			this.bking=BITS.getTo(md.bitmap);
		if(md instanceof MOVEDATAX)
			this.castling^=((MOVEDATAX) md).castling;
		init();
	}
	public void set(boolean isWhite, long bitmap, int wking, int bking, long bb, long b1, long b2, long b3) {
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
	 * @param bto TODO
	 */
	final public void capturePromote(int md, int type, int victim, long bto) {
		sort1(md);
	}

	final public void promote(int md,int type) {
		sort1(md);
	}
	
	final public void capture(int md, int type, int victim, long bto) {
		if(type<victim || (bto&unsafe)==0L){ // Good
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

	public void generate() {
		clear();
		// Calculate checkers and pinners
		pinners=0L;
		long en=enemy & ~bb_bit1 &  bb_bit2 & ~bb_bit3;
		long ep=enemy &  bb_bit1 & ~bb_bit2 & ~bb_bit3;
		long ek=enemy &  bb_bit1 &  bb_bit2 & ~bb_bit3;
		long eslide=enemy & bb_bit3;
		long ediag=eslide & bb_bit1;
		long eline=eslide & bb_bit2;
		long eb=ediag & ~bb_bit2;
		long er=eline & ~bb_bit1;
		long eq=ediag & eline;

		// Regular checks
		checkers=(en & BitBoard.NMasks[king]) | (ep & (isWhite?MBP.REV[king]:MWP.REV[king]));
		// Slider checks
		if((eslide & BitBoard.QMasks[king]) !=0L){
			long diagatks = ediag & BitBoard.BMasks[king];
			if (diagatks != 0L)
				diagPinners(diagatks);
			long lineatks = eline & BitBoard.RMasks[king];
			if (lineatks != 0L)
				linePinners(lineatks);
		}
		// Calculate unsafe positions, those attacked by enemy
		long pcs=bb_piece&~(own &  bb_bit1 &  bb_bit2 & ~bb_bit3);
		unsafe=isWhite?((ep&MaskAToGFiles)>>7) | ((ep&MaskBToHFiles)>>9):((ep&MaskAToGFiles)<<9) | ((ep&MaskBToHFiles)<<7);
		while(en!=0L){
			int sq = Long.numberOfTrailingZeros(en);
			unsafe|=BitBoard.NMasks[sq];
			en^=1L << sq;
		}
		while(ek!=0L){
			int sq = Long.numberOfTrailingZeros(ek);
			unsafe|=BitBoard.KMasks[sq];
			ek^=1L << sq;
		}
		while(eb!=0L){
			int sq = Long.numberOfTrailingZeros(eb);
			unsafe|=BitBoard.bishopAttacks(sq, pcs);
			eb^=1L << sq;
		}
		while(er!=0L){
			int sq = Long.numberOfTrailingZeros(er);
			unsafe|=BitBoard.rookAttacks(sq, pcs);
			er^=1L << sq;
		}
		while(eq!=0L){
			int sq = Long.numberOfTrailingZeros(eq);
			unsafe|=BitBoard.bishopAttacks(sq, pcs);
			unsafe|=BitBoard.rookAttacks(sq, pcs);
			eq^=1L << sq;
		}

		if(checkers==0L){
			nonevasive(own & ~pinners);
		} else {
			clear(); // not interested in pinned moves for evasive moves
			evasive(own & ~pinners);
		}
	}

	final private  void diagPinners(long atks) {
		int bits = Long.bitCount(atks);
		for (int j = 0; j < bits; j++) {
			int asq = Long.numberOfTrailingZeros(atks);
			long attacker = 1L << asq;
			atks ^= attacker;
			long between = BitBoard.BETWEEN[asq+64*king];
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
						if(pinner<<7==attacker && (attacker&IConst.MaskHFile)==0L){
							if(from>47){
								capturePromote(mwp.PL, ctype, attacker);
							} else
								capture(mwp.CL[ctype], 0, ctype, attacker);
						}
						if(pinner<<9==attacker && (attacker&IConst.MaskAFile)==0L){
							if(from>47){
								capturePromote(mwp.PR, ctype, attacker);
							} else
								capture(mwp.CR[ctype], 0, ctype, attacker);
						}
					} else {
						MBP mbp = MBP.BP[from];
						if(pinner>>9==attacker && (attacker&IConst.MaskHFile)==0L){
							if(from<16){
								capturePromote(mbp.PL, ctype, attacker);
							} else
								capture(mbp.CL[ctype], 0, ctype, attacker);
						}
						if(pinner>>7==attacker && (attacker&IConst.MaskAFile)==0L){
							if(from<16){
								capturePromote(mbp.PR, ctype, attacker);
							} else
								capture(mbp.CR[ctype], 0, ctype, attacker);
						}
					}
				}
			}
		}
	}

	public void capturePromote(int[] mvs, int ctype,long bto) {
		if(mvs==null || ctype==-1)
			return;
		capturePromote(mvs[ctype], 1, ctype, bto);
		capturePromote(mvs[ctype+5], 2, ctype, bto);
		capturePromote(mvs[ctype+10], 3, ctype, bto);
		capturePromote(mvs[ctype+15], 4, ctype, bto);
	}

	final private void linePinners(long atks) {
		int bits = Long.bitCount(atks);
		for (int j = 0; j < bits; j++) {
			int asq = Long.numberOfTrailingZeros(atks);
			long attacker = 1L << asq;
			atks ^= attacker;
			long between = BitBoard.BETWEEN[asq+64*king];
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
		int num = Long.bitCount(checkers);
		long _b = pieces &  bb_bit1 & ~bb_bit2 &  bb_bit3;
		long _n = pieces & ~bb_bit1 &  bb_bit2 & ~bb_bit3;
		long _r = pieces & ~bb_bit1 &  bb_bit2 &  bb_bit3;
		long _q = pieces &  bb_bit1 &  bb_bit2 &  bb_bit3;
		long _p = pieces & bb_pawns;
		if(num==1){
			int atk_sq = Long.numberOfTrailingZeros(checkers);
			long between=BitBoard.BETWEEN[atk_sq*64+king];
			long mask = between|checkers;
			if (isWhite) {
				loopMoves(_b, MWB.WB, mask);
				loopMoves(_r, MWR.WR, mask);
				loopMoves(_q, MWQ.WQ, mask);
				loopMoves(_n, MWN.WN, mask);
				MWP.genSingle(this,_p, ~between);
				MWP.genDouble(this,_p & (between>>16), bb_piece);
				MWP.genCaptures(this,_p, checkers);
			} else {
				loopMoves(_b, MBB.BB, mask);
				loopMoves(_r, MBR.BR, mask);
				loopMoves(_q, MBQ.BQ, mask);
				loopMoves(_n, MBN.BN, mask);
				MBP.genSingle(this,_p, ~between);
				MBP.genDouble(this,_p & (between<<16), bb_piece);
				MBP.genCaptures(this,_p, checkers);
			}
		}
		if (isWhite) {
			MWK.WK[king].genLegal(this,~unsafe);
		} else {
			MBK.BK[king].genLegal(this,~unsafe);
		}
	}

	protected void nonevasive(long pieces) {
		long _r = pieces & ~bb_bit1 &  bb_bit2 & bb_bit3;
		long _b = pieces &  bb_bit1 & ~bb_bit2 & bb_bit3;
		long _q = pieces &  bb_bit1 &  bb_bit2 & bb_bit3;
		long _p = pieces & bb_pawns;
		long _n = pieces & bb_knights;
		if (isWhite) {
			loopMoves(_b, MWB.WB, ~0L);
			loopMoves(_r, MWR.WR, ~0L);
			loopMoves(_q, MWQ.WQ, ~0L);
			loopMoves(_n, MWN.WN, ~0L);
			MWK.WK[king].genLegal(this,~unsafe);
			MWP.genSingle(this, _p, this.bb_piece);
			MWP.genDouble(this, _p, this.bb_piece);
			MWP.genCaptures(this, _p, this.bb_black);
			if(king==IConst.WK_STARTPOS)
				MWK.genCastling(this,unsafe);
		} else {
			loopMoves(_b, MBB.BB, ~0L);
			loopMoves(_r, MBR.BR, ~0L);
			loopMoves(_q, MBQ.BQ, ~0L);
			loopMoves(_n, MBN.BN, ~0L);
			MBK.BK[king].genLegal(this,~unsafe);
			MBP.genSingle(this, _p, this.bb_piece);
			MBP.genDouble(this, _p, this.bb_piece);
			MBP.genCaptures(this, _p, this.bb_white);
			if(king==IConst.BK_STARTPOS)
				MBK.genCastling(this,unsafe);
		}
	}

	public void loopMoves(long _b, MBase[] base, long mask) {
		while(_b!=0L){
			int from = Long.numberOfTrailingZeros(_b);
			_b ^= 1L << from;
			base[from].genLegal(this,mask);
		}
	}

	private void slide(int[][] mm,MBase b,long attacker,long between, int type) {
		for (int[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = MBase.getBTo(m[i + 5]);
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
								capture(b.K, type, c, bto);
							 else if(bto==1L<<IConst.BR_QUEEN_STARTPOS && (castling&CANCASTLE_BLACKQUEEN)!=0)
								capture(b.Q, type, c, bto);
							 else 
								capture(m[i + c], type, c, bto);
						} else {
							if(bto==1L<<IConst.WR_KING_STARTPOS && (castling&CANCASTLE_WHITEKING)!=0)
								capture(b.K, type, c, bto);
							 else if(bto==1L<<IConst.WR_QUEEN_STARTPOS && (castling&CANCASTLE_WHITEQUEEN)!=0)
								capture(b.Q, type, c, bto);
							 else 
								capture(m[i + c], type, c, bto);
						}
					} else {
						capture(m[i + c], type, c, bto);
					}
				}
				break;
			}
		}
	}


	@Override
	public String toString() {
		return FEN.board2string(this.bb_bit1, this.bb_bit2, this.bb_bit3, this.bb_black) + "\n";
	}

	public boolean isSafeMove(int md) {
		MOVEDATA m=MBase.ALL[md];
		return isSafe(isWhite,king,bb_black^m.b_black, bb_bit1^m.b_bit1, bb_bit2^m.b_bit2, bb_bit3^m.b_bit3);
	}

	public final static boolean isSafe(boolean white,int king,long bb_black, long bb_bit1, long bb_bit2, long bb_bit3) {
		long bb_piece = bb_bit1 | bb_bit2 | bb_bit3;
		long bb_knights = ~bb_bit1 & bb_bit2 & ~bb_bit3;
		long bb_kings = bb_bit1 & bb_bit2 & ~bb_bit3;
		long bb_pawns = bb_bit1 & ~bb_bit2 & ~bb_bit3;
		long enemy=white?bb_black:(bb_bit1 | bb_bit2 | bb_bit3)^bb_black;
		if(((enemy & bb_knights & BitBoard.NMasks[king]) != 0) || 
				((enemy & bb_kings & BitBoard.KMasks[king]) != 0) || 
				((enemy & bb_pawns & (white?MBP.REV[king]:MWP.REV[king])) != 0))
			return false;
		long slider=enemy & bb_bit3;
		if((slider & BitBoard.QMasks[king]) !=0){
			long diag = bb_bit1 & slider;
			if ((diag & BitBoard.BMasks[king]) != 0) {
				if((diag & BitBoard.bishopAttacks(king, bb_piece))!=0L)
					return false;
			}
			long line = bb_bit2 & slider;
			if ((line & BitBoard.RMasks[king]) != 0) {
				if((line & BitBoard.rookAttacks(king, bb_piece))!=0L)
					return false;
			}
		}
		return true;
	}
	
	public void sortHash(int md) {
		for (int i = 0; i < iAll; i++) {
			if(moves[i]==md){
				for (int j = i; j > 0; j--)
					moves[j]=moves[j-1];
				moves[0]=md;
				break;
			}
		}
	}
    
    public void sortKiller(int md){
    	if(md!=-1){
        	int start = lvl1;
        	int from  = lvl1;
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
	
	public void sortCheckers(){
		lvl2=lvl1;

    	// findDiscoveredCheckCandidates
		long sliders=own & bb_bit3 & BitBoard.QMasks[eking];
		long diagsqrs = bb_bit1 & BitBoard.BMasks[eking];
		long linesqrs = bb_bit2 & BitBoard.RMasks[eking];
		long hiders=0L;
		if(sliders==0L){
			long diagatks = sliders & diagsqrs;
			if (diagatks != 0L) {
				int bits = Long.bitCount(diagatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(diagatks);
					long attacker = 1L << asq;
					diagatks ^= attacker;
					long between = BitBoard.BETWEEN[asq+64*eking];
					if(Long.bitCount(between&bb_piece)==1)
						hiders|=between&own;
				}
			}
			long lineatks = sliders & linesqrs;
			if (lineatks != 0L) {
				int bits = Long.bitCount(lineatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(lineatks);
					long attacker = 1L << asq;
					lineatks ^= attacker;
					long between = BitBoard.BETWEEN[asq+64*eking];
					if(Long.bitCount(between&bb_piece)==1)
						hiders|=between&own;
				}
			}
		}
    	for (int i = lvl1; i < iAll; i++) {
    		MOVEDATA md = MBase.ALL[moves[i]];
    		// Hiders
    		int from = BITS.getFrom(md.bitmap);
    		long bfrom=1L<<from;
    		if((bfrom&hiders)!=0L){
				addChecker(i);
    			continue;
    		}
    		int piece = BITS.getPiece(md.bitmap);
    		long bto=md.bto;
    		if((piece&4)==4){
        		// Diag
        		if((piece&1)==1){  
        			if((bto & diagsqrs)!=0L){
        	    		if(isClear(md)){
        					addChecker(i);
        					continue;
        				}
        			}
        		}
        		// Line
        		if((piece&2)==2){  
        			if((bto & linesqrs)!=0L){
        				if(isClear(md)){
        					addChecker(i);
        					continue;
        				}
        			}
        		}
    		} else {
    			switch(piece){
    			case IConst.WN:
    			case IConst.BN:
    				if((bto & BitBoard.NMasks[eking])!=0L){
    					addChecker(i);
    					continue;
    				}
    				break;
				case IConst.WP:
					if((bto & MWP.REV[eking])!=0L){
						addChecker(i);
						continue;
					}
					break;
				case IConst.BP:
					if((bto & MBP.REV[eking])!=0L){
						addChecker(i);
						continue;
					}
					break;
				}
    		}
		}
    }

	public boolean isClear(MOVEDATA md) {
		int to = BITS.getTo(md.bitmap);
		long between = BitBoard.BETWEEN[to+64*eking];
		return Long.bitCount(between&bb_piece)==0;
	}

	public void addChecker(int i) {
		int sw=moves[lvl2];
		moves[lvl2++]=moves[i];
		moves[i]=sw;
	}

}
