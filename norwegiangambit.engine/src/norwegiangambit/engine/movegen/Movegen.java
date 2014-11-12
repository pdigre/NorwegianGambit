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
 * Prefixes to variables
 * a - All
 * w - White
 * b - Black
 * o - Own
 * e - Enemy
 *
 */
public class Movegen implements IConst{
	public boolean wNext=false;
	public long bOccupied,aMinor,aMajor,aSlider;
	public long aOccupied,castling;
	public int wkingpos,bkingpos,epsq;
	public long wOccupied, oOccupied, eOccupied,aPawns,aKnights,aBishops,aRooks,aQueens,aKings,aLine,aDiag;
	public long checkers, pinners, oAttacked, eAttacked;
	public long wPawns,bPawns,wPawnAtks,bPawnAtks,wPawnAtkBy,bPawnAtkBy;
	int oKingpos,eKingpos;
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
		return FEN.board2fen(FEN.boardFrom64(aMinor, aMajor, aSlider, bOccupied)) + " " +(wNext?"w":"b") + " " + FEN.getFenCastling(castling) + " "+ FEN.pos2string(epsq);
	}

	public void make(int md_num,boolean wNext, long castling, int wkingpos, int bkingpos, long b1, long b2, long b3, long bb) {
		MOVEDATA md 	= MBase.ALL[md_num];
		this.pv			= md_num;
		this.wNext		= !wNext;
		this.bOccupied 	= bb^md.bOccupied;
		this.aMinor 	= b1^md.aMinor;
		this.aMajor 	= b2^md.aMajor;
		this.aSlider 	= b3^md.aSlider;
		this.wkingpos   = wkingpos;
		this.bkingpos   = bkingpos;
		this.epsq 		= md instanceof MOVEDATA2?((MOVEDATA2)md).epsq:-1;
		this.castling	= castling;
		int type  = BITS.getPiece(md.bitmap);
		if(type==IConst.WK)
			this.wkingpos=BITS.getTo(md.bitmap);
		else if(type==IConst.BK)
			this.bkingpos=BITS.getTo(md.bitmap);
		if(md instanceof MOVEDATAX)
			this.castling^=((MOVEDATAX) md).castling;
		init();
	}
	public void set(boolean isWhite, long bitmap, int wkingpos, int bkingpos, long bOccupied, long aMinor, long aMajor, long aSlider) {
		this.wNext		= isWhite;
		this.bOccupied 	= bOccupied;
		this.aMinor 	= aMinor;
		this.aMajor 	= aMajor;
		this.aSlider 	= aSlider;
		this.wkingpos 	= wkingpos;
		this.bkingpos 	= bkingpos;
		this.epsq 		= BITS.getEnpassant(bitmap);
		this.castling 	= ~CASTLING_STATE | bitmap; // all other are set
		init();
	}

	public void init() {
		aOccupied 	= aMinor | aMajor | aSlider;
		wOccupied 	= aOccupied ^ bOccupied;
		aPawns 		=  aMinor & ~aMajor & ~aSlider;
		aKnights 	= ~aMinor &  aMajor & ~aSlider;
		aKings 		=  aMinor &  aMajor & ~aSlider;
		aDiag 		=  aMinor           &  aSlider;
		aLine	 	=            aMajor &  aSlider;
		aBishops 	=  aMinor & ~aMajor &  aSlider;
		aRooks 		= ~aMinor &  aMajor &  aSlider;
		aQueens 	=  aMinor &  aMajor &  aSlider;
		oKingpos 	= wNext?wkingpos:bkingpos;
		eKingpos 	= wNext?bkingpos:wkingpos;
		oOccupied	= wNext?wOccupied:bOccupied;
		eOccupied	= wNext?bOccupied:wOccupied;
		wPawns   	= wOccupied & aPawns;
        bPawns   	= bOccupied & aPawns;
		wPawnAtks 	= (((wPawns & MaskBToHFiles) << 7) | ((wPawns & MaskAToGFiles) << 9));  // Left - Right
		bPawnAtks 	= (((bPawns & MaskBToHFiles) >> 9) | ((bPawns & MaskAToGFiles) >> 7));  // Left - Right
		wPawnAtkBy 	= (((bPawns & MaskBToHFiles) >> 9) | ((bPawns & MaskAToGFiles) >> 7));  // Left - Right
		bPawnAtkBy 	= (((wPawns & MaskBToHFiles) << 7) | ((wPawns & MaskAToGFiles) << 9));  // Left - Right
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
		if(type<victim || (bto&eAttacked)==0){ // Good
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
	
	/**
	 * Capture type 
	 * <br>[pawn,knight,bishop,rook,queen]
	 * @param bit bit_position
	 * @return 0-4
	 */
	final public int ctype(long bit) {
		return ((aMinor & bit) == 0 ? 0 : 1) + ((aMajor & bit) == 0 ? 0 : 2) + ((aSlider & bit) == 0 ? 0 : 2) - 1;
	}
	
	/**
	 * Piece type according to bitboard 
	 * <br>[_pnk_brq_PNK_BRQ]
	 * @param bit bit_position
	 * @return 0-15
	 */
	final public int type(long bit) {
		return ((aMinor & bit) == 0 ? 0 : 1) + ((aMajor & bit) == 0 ? 0 : 2) + ((aSlider & bit) == 0 ? 0 : 4) + ((bOccupied & bit) == 0 ? 0 : 8);
	}
	
	final public int[] legalmoves() {
		generate();
		return Arrays.copyOfRange(moves, 0, iAll);
	}

	public void generate() {
		clear();

		// Calculate checkers and pinners
		pinners=0;

		// Regular checks
		checkers=(eOccupied & aKnights & BitBoard.NMasks[oKingpos]) | (eOccupied & aPawns & (wNext?MBP.REV[oKingpos]:MWP.REV[oKingpos]));

		// Slider checks
		if((eOccupied & aSlider & BitBoard.QMasks[oKingpos]) !=0){
			long diagatks = eOccupied & aDiag & BitBoard.BMasks[oKingpos];
			if (diagatks != 0)
				diagPinners(diagatks);
			long lineatks = eOccupied & aLine & BitBoard.RMasks[oKingpos];
			if (lineatks != 0)
				linePinners(lineatks);
		}
		// Calculate unsafe positions, those attacked by enemy
		long pcs=aOccupied&~(oOccupied &  aKings);
		eAttacked=wNext?wPawnAtkBy:bPawnAtkBy;
		{
			long m=eOccupied & aKnights;
			while(m!=0){
				int sq = Long.numberOfTrailingZeros(m);
				eAttacked|=BitBoard.NMasks[sq];
				m &= m-1;
			}
		}
		{
			long m=eOccupied & aKings;
			while(m!=0){
				int sq = Long.numberOfTrailingZeros(m);
				eAttacked|=BitBoard.KMasks[sq];
				m &= m-1;
			}
		}
		{
			long m=eOccupied & aBishops;
			while(m!=0){
				int sq = Long.numberOfTrailingZeros(m);
				eAttacked|=BitBoard.bishopAttacks(sq, pcs);
				m &= m-1;
			}
		}
		{
			long m=eOccupied & aRooks;
			while(m!=0){
				int sq = Long.numberOfTrailingZeros(m);
				eAttacked|=BitBoard.rookAttacks(sq, pcs);
				m &= m-1;
			}
		}
		{
			long m=eOccupied & aQueens;
			while(m!=0){
				int sq = Long.numberOfTrailingZeros(m);
				eAttacked|=BitBoard.bishopAttacks(sq, pcs);
				eAttacked|=BitBoard.rookAttacks(sq, pcs);
				m &= m-1;
			}
		}

		if(checkers==0){
			nonevasive();
		} else {
			clear(); // not interested in pinned moves for evasive moves
			evasive();
		}
	}

	final private  void diagPinners(long atks) {
		int bits = Long.bitCount(atks);
		for (int j = 0; j < bits; j++) {
			int asq = Long.numberOfTrailingZeros(atks);
			long attacker = 1L << asq;
			atks ^= attacker;
			long between = BitBoard.BETWEEN[asq+64*oKingpos];
			long bpcs = between&aOccupied;
			if(bpcs==0){
				checkers|=attacker;
			} else if(Long.bitCount(bpcs)==1){
				// check for slide moves
				long pinner = between&oOccupied;
				int from = Long.numberOfTrailingZeros(pinner);
				pinners|=pinner;
				if((pinner&aDiag)!=0){	// BISHOP / QUEEN
					if((pinner&aQueens)!=0){  	// QUEEN
						slide(wNext?MWQ.MOVES[from].DIAG:MBQ.MOVES[from].DIAG,wNext?MWQ.MOVES[from]:MBQ.MOVES[from],attacker,between, 4);
					} else {
						slide(wNext?MWB.MOVES[from].DIAG:MBB.MOVES[from].DIAG,wNext?MWQ.MOVES[from]:MBQ.MOVES[from],attacker,between, 2);
					}
				} else if((pinner&aPawns)!=0){  // PAWN CAPTURE
					int ctype = ctype(attacker);
					if(wNext){
						MWP mwp = MWP.WP[from];
						if(pinner<<7==attacker && (attacker&MaskHFile)==0){
							if(from>47){
								capturePromote(mwp.PL, ctype, attacker);
							} else
								capture(mwp.CL[ctype], 0, ctype, attacker);
						}
						if(pinner<<9==attacker && (attacker&MaskAFile)==0){
							if(from>47){
								capturePromote(mwp.PR, ctype, attacker);
							} else
								capture(mwp.CR[ctype], 0, ctype, attacker);
						}
					} else {
						MBP mbp = MBP.BP[from];
						if(pinner>>9==attacker && (attacker&MaskHFile)==0){
							if(from<16){
								capturePromote(mbp.PL, ctype, attacker);
							} else
								capture(mbp.CL[ctype], 0, ctype, attacker);
						}
						if(pinner>>7==attacker && (attacker&MaskAFile)==0){
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
			long between = BitBoard.BETWEEN[asq+64*oKingpos];
			long bpcs = between&aOccupied;
			if(bpcs==0){
				checkers|=attacker;
			} else if(Long.bitCount(bpcs)==1){
				// check for slide moves
				long pinner = between&oOccupied;
				int from = Long.numberOfTrailingZeros(pinner);
				pinners|=pinner;
				if((pinner&aLine)!=0){		// ROOK / QUEEN
					if((pinner&aQueens)!=0){	// QUEEN
						slide(wNext?MWQ.MOVES[from].LINE:MBQ.MOVES[from].LINE,wNext?MWQ.MOVES[from]:MBQ.MOVES[from],attacker,between, 4);
					} else {
						slide(wNext?MWR.MOVES[from].LINE:MBR.MOVES[from].LINE,wNext?MWR.MOVES[from]:MBR.MOVES[from],attacker,between, 3);
					}
				} else if((pinner&aPawns)!=0){  // PAWN FORWARD
					if(wNext){
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

	final public void evasive() {
		int num = Long.bitCount(checkers);
		if(num==1){
			int atk_sq = Long.numberOfTrailingZeros(checkers);
			long between=BitBoard.BETWEEN[atk_sq*64+oKingpos];
			long mask = between|checkers;
			long free = oOccupied & ~pinners;
			long pfree = free & aPawns;
			if (wNext) {
				loopMoves(free & aKnights, MWN.MOVES, mask);
				loopMoves(free & aBishops, MWB.MOVES, mask);
				loopMoves(free & aRooks,   MWR.MOVES, mask);
				loopMoves(free & aQueens,  MWQ.MOVES, mask);
				MWP.genSingle(this,pfree, ~between);
				MWP.genDouble(this,pfree & (between>>16), aOccupied);
				MWP.genCaptures(this,pfree, checkers);
			} else {
				loopMoves(free & aKnights, MBN.MOVES, mask);
				loopMoves(free & aBishops, MBB.MOVES, mask);
				loopMoves(free & aRooks,   MBR.MOVES, mask);
				loopMoves(free & aQueens,  MBQ.MOVES, mask);
				MBP.genSingle(this,pfree, ~between);
				MBP.genDouble(this,pfree & (between<<16), aOccupied);
				MBP.genCaptures(this,pfree, checkers);
			}
		}
		if (wNext) {
			MWK.MOVES[wkingpos].genLegal(this,~eAttacked);
		} else {
			MBK.MOVES[bkingpos].genLegal(this,~eAttacked);
		}
	}

	protected void nonevasive() {
		long mask = ~0L;
		if (wNext) {
			long free = wOccupied & ~pinners;
			loopMoves(free & aKnights, MWN.MOVES, mask);
			loopMoves(free & aBishops, MWB.MOVES, mask);
			loopMoves(free & aRooks,   MWR.MOVES, mask);
			loopMoves(free & aQueens,  MWQ.MOVES, mask);
			MWK.MOVES[wkingpos].genLegal(this,~eAttacked);
			long _p = free & aPawns;
			MWP.genSingle(this, _p, this.aOccupied);
			MWP.genDouble(this, _p, this.aOccupied);
			MWP.genCaptures(this, _p, this.bOccupied);
			if(wkingpos==WK_STARTPOS)
				MWK.genCastling(this,eAttacked);
		} else {
			long free = bOccupied & ~pinners;
			loopMoves(free & aKnights, MBN.MOVES, mask);
			loopMoves(free & aBishops, MBB.MOVES, mask);
			loopMoves(free & aRooks,   MBR.MOVES, mask);
			loopMoves(free & aQueens,  MBQ.MOVES, mask);
			MBK.MOVES[bkingpos].genLegal(this,~eAttacked);
			long _p = free & aPawns;
			MBP.genSingle(this, _p, this.aOccupied);
			MBP.genDouble(this, _p, this.aOccupied);
			MBP.genCaptures(this, _p, this.wOccupied);
			if(bkingpos==BK_STARTPOS)
				MBK.genCastling(this,eAttacked);
		}
	}

	public void loopMoves(long _b, MBase[] base, long mask) {
		while(_b!=0){
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
						if(wNext){
							if(bto==1L<<BR_KING_STARTPOS && (castling&CANCASTLE_BLACKKING)!=0)
								capture(b.K, type, c, bto);
							 else if(bto==1L<<BR_QUEEN_STARTPOS && (castling&CANCASTLE_BLACKQUEEN)!=0)
								capture(b.Q, type, c, bto);
							 else 
								capture(m[i + c], type, c, bto);
						} else {
							if(bto==1L<<WR_KING_STARTPOS && (castling&CANCASTLE_WHITEKING)!=0)
								capture(b.K, type, c, bto);
							 else if(bto==1L<<WR_QUEEN_STARTPOS && (castling&CANCASTLE_WHITEQUEEN)!=0)
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
		return FEN.board2string(this.aMinor, this.aMajor, this.aSlider, this.bOccupied) + "\n";
	}

	public boolean isSafeMove(int md) {
		MOVEDATA m=MBase.ALL[md];
		return isSafe(wNext,oKingpos,bOccupied^m.bOccupied, aMinor^m.aMinor, aMajor^m.aMajor, aSlider^m.aSlider);
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
				if((diag & BitBoard.bishopAttacks(king, bb_piece))!=0)
					return false;
			}
			long line = bb_bit2 & slider;
			if ((line & BitBoard.RMasks[king]) != 0) {
				if((line & BitBoard.rookAttacks(king, bb_piece))!=0)
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
		long sliders=oOccupied & aSlider & BitBoard.QMasks[eKingpos];
		long diagsqrs = aDiag & BitBoard.BMasks[eKingpos];
		long linesqrs = aLine & BitBoard.RMasks[eKingpos];
		long hiders=0;
		if(sliders==0){
			long diagatks = sliders & diagsqrs;
			if (diagatks != 0) {
				int bits = Long.bitCount(diagatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(diagatks);
					long attacker = 1L << asq;
					diagatks ^= attacker;
					long between = BitBoard.BETWEEN[asq+64*eKingpos];
					if(Long.bitCount(between&aOccupied)==1)
						hiders|=between&oOccupied;
				}
			}
			long lineatks = sliders & linesqrs;
			if (lineatks != 0) {
				int bits = Long.bitCount(lineatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(lineatks);
					long attacker = 1L << asq;
					lineatks ^= attacker;
					long between = BitBoard.BETWEEN[asq+64*eKingpos];
					if(Long.bitCount(between&aOccupied)==1)
						hiders|=between&oOccupied;
				}
			}
		}
    	for (int i = lvl1; i < iAll; i++) {
    		MOVEDATA md = MBase.ALL[moves[i]];
    		// Hiders
    		int from = BITS.getFrom(md.bitmap);
    		long bfrom=1L<<from;
    		if((bfrom&hiders)!=0){
				addChecker(i);
    			continue;
    		}
    		int piece = BITS.getPiece(md.bitmap);
    		long bto=md.bto;
    		if((piece&4)==4){
        		// Diag
        		if((piece&1)==1){  
        			if((bto & diagsqrs)!=0){
        	    		if(isClear(md)){
        					addChecker(i);
        					continue;
        				}
        			}
        		}
        		// Line
        		if((piece&2)==2){  
        			if((bto & linesqrs)!=0){
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
    				if((bto & BitBoard.NMasks[eKingpos])!=0){
    					addChecker(i);
    					continue;
    				}
    				break;
				case IConst.WP:
					if((bto & MWP.REV[eKingpos])!=0){
						addChecker(i);
						continue;
					}
					break;
				case IConst.BP:
					if((bto & MBP.REV[eKingpos])!=0){
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
		long between = BitBoard.BETWEEN[to+64*eKingpos];
		return Long.bitCount(between&aOccupied)==0;
	}

	public void addChecker(int i) {
		int sw=moves[lvl2];
		moves[lvl2++]=moves[i];
		moves[i]=sw;
	}

}
