package norwegiangambit.engine.movegen;

import java.util.Arrays;
import java.util.function.Consumer;

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
	
	static{
		int i=MOVEDATA.ALL.length;
		assert i>0;
	}

	public boolean wNext=false;
	public boolean ecq, eck, ocq, ock;
	public long bOccupied,aMinor,aMajor,aSlider;
	public long aOccupied,castling;
	public int wkingpos,bkingpos;
	public int epsq;
	public int erq,erk,orq,ork; // Enemy and own Rook Queen side or King side
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

	public void make(int md_num,boolean wLast, long castling, int wkingpos, int bkingpos, long b1, long b2, long b3, long bb) {
		MOVEDATA md 	= MOVEDATA.ALL[md_num];
		this.pv			= md_num;
		this.wNext		= !wLast;
		this.bOccupied 	= bb^md.bOccupied;
		this.aMinor 	= b1^md.aMinor;
		this.aMajor 	= b2^md.aMajor;
		this.aSlider 	= b3^md.aSlider;
		this.wkingpos   = wkingpos;
		this.bkingpos   = bkingpos;
		this.epsq 		= md instanceof ENPASSANT?((ENPASSANT)md).epsq:-1;
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
		erk        	= wNext?BR_KING_STARTPOS:WR_KING_STARTPOS;
		erq        	= wNext?BR_QUEEN_STARTPOS:WR_QUEEN_STARTPOS;
		ecq			= (castling & (wNext?CANCASTLE_BLACKQUEEN:CANCASTLE_WHITEQUEEN)) != 0;
		eck			= (castling & (wNext?CANCASTLE_BLACKKING:CANCASTLE_WHITEKING)) != 0;
		ork 		= wNext?WR_KING_STARTPOS:BR_KING_STARTPOS;
		orq 		= wNext?WR_QUEEN_STARTPOS:BR_QUEEN_STARTPOS;
		ocq 		= (castling & (wNext?CANCASTLE_WHITEQUEEN:CANCASTLE_BLACKQUEEN)) != 0;
		ock 		= (castling & (wNext?CANCASTLE_WHITEKING:CANCASTLE_BLACKKING)) != 0;
		
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

		boolean start=oKingpos==(wNext?WK_STARTPOS:BK_STARTPOS);
		if(!start && (ock || ocq))
			System.out.println("Wrong FEN");

	}

	public void undo(MOVEDATA md){
		
	}
	
	/**
	 * Add capture, check value of victim
	 * @param md
	 * @param type
	 * @param victim
	 * @param bto
	 */
	final public void capture(int md, int type, int victim, long bto) {
		if(type<victim || (bto&eAttacked)==0){ // Good
			add1(md);
		} else if(type==victim){ // Equal
			add2(md);
		} else { // Bad
			add3(md);
		}
	}

	/**
	 * Good move <br>
	 * Winning capture, promotion, checks
	 * @param md
	 */
	final void add1(int md) {
		moves[iAll++] = moves[lvl3];
		moves[lvl3++] = moves[lvl2];
		moves[lvl2++] = moves[lvl1];
		moves[lvl1++]=md;
	}
	
	/**
	 * Add promotion 
	 * @param promo
	 * @param ctype
	 */
	public void add1_promo(int md) {
		add1(md);
		add1(md+1);
		add1(md+2);
		add1(md+3);
	}
	
	/**
	 * Equal capture
	 * @param md
	 */
	final void add2(int md) {
		moves[iAll++] = moves[lvl3];
		moves[lvl3++] = moves[lvl2];
		moves[lvl2++]=md;
	}
	
	/**
	 * Loosing capture
	 * @param md
	 */
	final void add3(int md) {
		moves[iAll++] = moves[lvl3];
		moves[lvl3++]=md;
	}

	/**
	 * Silent move
	 * @param md
	 */
	final public void add4(int md) {
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
			if (diagatks != 0){
				int bits = Long.bitCount(diagatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(diagatks);
					long attacker = 1L << asq;
					diagatks ^= attacker;
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
								slide(wNext?MWB.MOVES[from].SLIDES:MBB.MOVES[from].SLIDES,wNext?MWQ.MOVES[from]:MBQ.MOVES[from],attacker,between, 2);
							}
						} else if((pinner&aPawns)!=0){  // PAWN CAPTURE
							int ctype = ctype(attacker);
							if(wNext){
								if(pinner<<7==attacker && (attacker&MaskHFile)==0){
									MPCapture l = MWP.L[from];
									if(from>47){
										add1_promo(l.P+ctype*4);
									} else
										capture(l.C[ctype], 0, ctype, attacker);
								}
								if(pinner<<9==attacker && (attacker&MaskAFile)==0){
									MPCapture r = MWP.R[from];
									if(from>47){
										add1_promo(r.P+ctype*4);
									} else
										capture(r.C[ctype], 0, ctype, attacker);
								}
							} else {
								if(pinner>>9==attacker && (attacker&MaskHFile)==0){
									MPCapture l = MBP.L[from];
									if(from<16){
										add1_promo(l.P+ctype*4);
									} else
										capture(l.C[ctype], 0, ctype, attacker);
								}
								if(pinner>>7==attacker && (attacker&MaskAFile)==0){
									MPCapture r = MBP.R[from];
									if(from<16){
										add1_promo(r.P+ctype*4);
									} else
										capture(r.C[ctype], 0, ctype, attacker);
								}
							}
						}
					}
				}
			}
			long lineatks = eOccupied & aLine & BitBoard.RMasks[oKingpos];
			if (lineatks != 0){
				int bits = Long.bitCount(lineatks);
				for (int j = 0; j < bits; j++) {
					int asq = Long.numberOfTrailingZeros(lineatks);
					long attacker = 1L << asq;
					lineatks ^= attacker;
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
								slide(wNext?MWR.MOVES[from].SLIDES:MBR.MOVES[from].SLIDES,wNext?MWR.MOVES[from]:MBR.MOVES[from],attacker,between, 3);
							}
						} else if((pinner&aPawns)!=0){  // PAWN FORWARD
							if(wNext){
								if(((pinner<<8)&between)!=0){
									add4(MWP.WP[from].M1);
									if(from<16 && ((pinner<<16)&between)!=0)
										add4(MWP.WP[from].M2);
								}
							} else {
								if(((pinner>>8)&between)!=0){
									add4(MBP.BP[from].M1);
									if(from>47 && ((pinner>>16)&between)!=0)
										add4(MBP.BP[from].M2);
								}
							}
						}
					}
				}
			}
		}
		
		// Calculate unsafe positions, those attacked by enemy
		long pcs=aOccupied&~(oOccupied &  aKings);
		eAttacked=wNext?wPawnAtkBy:bPawnAtkBy;
//		bitscan(eOccupied & aKnights, sq -> eAttacked|=BitBoard.NMasks[sq]);
//		bitscan(eOccupied & aKings, sq -> eAttacked|=BitBoard.KMasks[sq]);
//		bitscan(eOccupied & aBishops, sq -> eAttacked|=BitBoard.bishopAttacks(sq, pcs));
//		bitscan(eOccupied & aRooks, sq -> eAttacked|=BitBoard.rookAttacks(sq, pcs));
//		bitscan(eOccupied & aQueens, sq -> eAttacked|=BitBoard.bishopAttacks(sq, pcs)|BitBoard.rookAttacks(sq, pcs));

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
			// Non-Evasive moves
			long mask = ~0L;
			long free = oOccupied & ~pinners;
			long pfree = free & aPawns;
			genKnight(free & aKnights, aOccupied,eOccupied,mask);
			genBishop(free & aBishops, aOccupied,eOccupied,mask);
			genRook(free & aRooks, aOccupied,eOccupied,mask);
			genQueen(free & aQueens, aOccupied,eOccupied,mask);
			genKing();
			if (wNext) {
				long open1 = pfree&~(aOccupied>>8);
				long open2 = open1&0xFF00L&(~(aOccupied>>8)>>8);
				genPawn(pfree,open1,open2,eOccupied);

				// Castling
				if (ocq && ((CWQ_FREE & aOccupied) | (CWQ_MASK&eAttacked))==0) {
					add4(ock?MWK.CQ:MWK.CQ2);
				}
				if (ock && ((CWK_FREE & aOccupied) | (CWK_MASK&eAttacked))==0) {
					add4(ocq?MWK.CK:MWK.CK2);
				}

			} else {
				long open1 = pfree&~(aOccupied<<8);
				long open2 = open1&0x00FF000000000000L&(~(aOccupied<<8)<<8);
				genPawn(pfree,open1,open2,eOccupied);

				// Castling
				if (ocq && ((CBQ_FREE & aOccupied) | (CBQ_MASK&eAttacked))==0) {
					add4(ock?MBK.CQ:MBK.CQ2);
				}
				if (ock && ((CBK_FREE & aOccupied) | (CBK_MASK&eAttacked))==0) {
					add4(ocq?MBK.CK:MBK.CK2);
				}
			}
		} else {
			// Evasive moves
			clear(); // dont need pinned moves for evasive moves calculation
			int num = Long.bitCount(checkers);
			if(num==1){
				int atk_sq = Long.numberOfTrailingZeros(checkers);
				long between=BitBoard.BETWEEN[atk_sq*64+oKingpos];
				long mask = between|checkers;
				long free = oOccupied & ~pinners;
				genKnight(free & aKnights, aOccupied,eOccupied,mask);
				genBishop(free & aBishops, aOccupied,eOccupied,mask);
				genRook(free & aRooks, aOccupied,eOccupied,mask);
				genQueen(free & aQueens, aOccupied,eOccupied,mask);
				
				long pfree = free & aPawns;
				if (wNext) {
					long open1 = pfree&~(~between>>8);
					long open2 = pfree & (between>>16)&~(aOccupied>>8)&MaskRow2&(~(aOccupied>>8)>>8);
					genPawn(pfree,open1,open2,checkers);
				} else {
					long open1 = pfree&~(~between<<8);
					long open2 = pfree&(between<<16)&~(aOccupied<<8)&MaskRow7&(~(aOccupied<<8)<<8);
					genPawn(pfree,open1,open2,checkers);
				}
			}
			genKing();
		}
	}


	public void genKing() {
		MSimple mvs=wNext?MWK.MOVES[oKingpos]:MBK.MOVES[oKingpos]; 
		if(oKingpos!= (wNext?WK_STARTPOS:BK_STARTPOS) || !(ock || ocq))
			genKingMoves(mvs.B,mvs.E, mvs.K, mvs.Q);
		else {
			int i=(ocq?(ock?MWK.XB:MWK.XQB):(ock?MWK.XKB:0))+(wNext?0:MOVEDATA.BLACK_OFFSET);
			genKingMoves(i,i+30, mvs.K, mvs.Q);
		}
	}

	private void genKingMoves(int B, int E, int k, int q) {
		for (int s=B;s<E;s+=6){
			long bto = MOVEDATA.getBTo(s);
			if ((aOccupied & bto) == 0) {
				if((bto&~eAttacked)!=0L)
					add4(s);
			} else {
				if ((eOccupied & bto & ~eAttacked) != 0) {
					int c = ctype(bto);
					if(c==3 && bto==1L<<erk && eck)
						add4(k); // Enemy Rook -> no castling king side
					else if(c==3 && bto==1L<<erq && ecq)
						add4(q); // Enemy Rook -> no castling queen side
					else
						add4(s+1+c);
				}
			}
		}
	}

	private void genQueen(long from, long occupied,long capture,long mask) {
		MSlider[] mvs = wNext?MWQ.MOVES:MBQ.MOVES;
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			genSlides(occupied, capture, mask, mvs[sq],4);
		}
	}

	private void genBishop(long from, long occupied,long capture,long mask) {
		MSlider[] mvs = wNext?MWB.MOVES:MBB.MOVES;
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			genSlides(occupied, capture, mask, mvs[sq],2);
		}
	}

	private void genRook(long from, long occupied,long capture,long mask) {
		MSlider[] mvs = wNext?MWR.MOVES:MBR.MOVES;
		MSlider kline = wNext?MWR.KLINE:MBR.KLINE;
		MSlider qline = wNext?MWR.QLINE:MBR.QLINE;
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			// If castling opportunities will be broken then special Zobrist
			if(sq==orq){
				if(ocq ){
					genSlides(occupied, capture, mask, qline,3);
					continue;
				}
			} else if(sq==ork){
				if(ock ){
					genSlides(occupied, capture, mask, kline,3);
					continue;
				}
			}
			genSlides(occupied, capture, mask, mvs[sq],3);
		}
	}
	
	public void genSlides(long occupied, long capture, long mask, MSlider mv, int val) {
		for (int[] m : mv.SLIDES) {
			int i = 0;
			while (i < m.length) {
				long bto = MOVEDATA.getBTo(m[i + 5]);
				if ((occupied & bto) != 0) {
					if ((capture & bto & mask) != 0) {
						int c = ctype(bto);
						if(c==3 && bto==1L<<erk  && eck){ // Enemy Rook -> no castling king side
							capture(mv.K, val, c, bto);
						}else if(c==3 && bto==1L<<erq && ecq){ // Enemy Rook -> no castling queen side
							capture(mv.Q, val, c, bto);
						}else{
							capture(m[i + c], val, c, bto);
						}
					}
					break;
				} else {
					if((bto&mask)!=0)
						add4(m[i + 5]);
					i += 6;
				}
			}
		}
	}

	private void genKnight(long from, long occupied,long capture,long mask) {
		MSimple[] mvs = wNext?MWN.MOVES:MBN.MOVES;
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			MSimple mv = mvs[sq];
			for (int s=mv.B;s<mv.E;s+=6){
				long bto = MOVEDATA.getBTo(s);
				if ((occupied & bto) == 0L) {
					if((bto & mask)!=0L)
						add4(s);
				} else {
					if ((capture & bto & mask) != 0) {
						int c = ctype(bto);
						if(c==3 && bto==1L<<erk && eck) // Enemy Rook -> no castling king side
							capture(mv.K, 1, c, bto);
						else {
							if(c==3 && bto==1L<<erq && ecq) // Enemy Rook -> no castling queen side
								capture(mv.Q, 1, c, bto);
							else
								capture(s+1+c, 1, c, bto);
						}
					}
				}
			}
		}
	}

	private void genPawn(long from, long open1, long open2, long captures) {
		MPawn[] mvs = wNext?MWP.WP:MBP.BP;
		while(open1!=0){
			int sq = Long.numberOfTrailingZeros(open1);
			if(wNext?sq>47:sq<16){
				add1_promo(mvs[sq].P1);
			} else {
				add4(mvs[sq].M1);
			}
			open1 &= open1-1;
		}
		while(open2!=0){
			int sq = Long.numberOfTrailingZeros(open2);
			add4(mvs[sq].M2);
			open2 &= open2-1;
		}
		long e=captures|(1L<<epsq);
		if(wNext){
			pwnCaptures(MWP.L, MWP.PQ, (from & MaskBToHFiles) &(e>>7), 7, erq, ecq);
			pwnCaptures(MWP.R, MWP.PK, (from & MaskAToGFiles) &(e>>9), 9, erk, eck);
		} else {
			pwnCaptures(MBP.L, MBP.PQ, (from & MaskBToHFiles) &(e<<9), -9, erq, ecq);
			pwnCaptures(MBP.R, MBP.PK, (from & MaskAToGFiles) &(e<<7), -7, erk, eck);
		}
	}

	public void pwnCaptures(MPCapture[] mvs, int pc, long m, int step, int cs, boolean cc) {
		while(m!=0){
			int sq = Long.numberOfTrailingZeros(m);
			m &= m-1;
			int to=sq+step;
			MPCapture mv = mvs[sq];
			if (to == epsq) {
				int md=mv.E;
				if(isSafeMove(md))	// Check for safety since there may be a covered check wit en-passant
					add2(md);
			} else {
				long bto = 1L << to;
				int ctype=ctype(bto);
				if((bto & MaskGoal)==0L){
					capture(mv.C[ctype], 0, ctype, bto);
				} else {
					if(cc && sq+step==cs){
						add1_promo(pc);
					} else {
						int p = mv.P;
						add1_promo(p+ctype*4);
					}
				}
			}
		}
	}

	final static void bitscan(long m, Consumer<Integer> c){
		while(m!=0){
			int sq = Long.numberOfTrailingZeros(m);
			c.accept(sq);
			m &= m-1;
		}
	}
	
	private void slide(int[][] mm,MBase b,long attacker,long between, int type) {
		for (int[] m : mm) {
			int i = 0;
			while (i < m.length) {
				long bto = MOVEDATA.getBTo(m[i + 5]);
				if((between&bto)!=0){
					add4(m[i + 5]);
					i += 6;
					continue;
				}
				if ((attacker & bto) != 0){
					int c = ctype(bto);
					if(c==3){
						if(wNext){
							if(bto==1L<<erk && eck)
								capture(b.K, type, c, bto);
							 else if(bto==1L<<erq && ecq)
								capture(b.Q, type, c, bto);
							 else 
								capture(m[i + c], type, c, bto);
						} else {
							if(bto==1L<<erk && eck)
								capture(b.K, type, c, bto);
							 else if(bto==1L<<erq && ecq)
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
		return FEN.board2string(aMinor, aMajor, aSlider, bOccupied) + "\n";
	}

	public boolean isSafeMove(int md) {
		MOVEDATA m=MOVEDATA.ALL[md];
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
    		MOVEDATA md = MOVEDATA.ALL[moves[i]];
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
