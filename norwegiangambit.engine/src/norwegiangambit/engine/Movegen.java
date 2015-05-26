package norwegiangambit.engine;

import java.util.Arrays;

import norwegiangambit.engine.movegen.ENPASSANT;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.engine.movegen.MOVEDATAX;
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
	
	final public static int MOVEDATASIZE=64*1024; 
	final public static long[] BITMAP=new long[MOVEDATASIZE];
	final public static long[] BOCCUPIED=new long[MOVEDATASIZE];
	final public static long[] AMINOR=new long[MOVEDATASIZE];
	final public static long[] AMAJOR=new long[MOVEDATASIZE];
	final public static long[] ASLIDER=new long[MOVEDATASIZE];
	final public static long[] BTO=new long[MOVEDATASIZE];
	final public static int[] MESCORE=new int[MOVEDATASIZE];
	final public static long[] ZOBRIST=new long[MOVEDATASIZE];
	final public static long[] PAWNHASH=new long[MOVEDATASIZE];
	
	static{
		MOVEDATA.initialize(BITMAP,BOCCUPIED,AMINOR,AMAJOR,ASLIDER,BTO,MESCORE,ZOBRIST,PAWNHASH);
	}

	
	/**
	 * @param occupied bitmask
	 * @param i =sq*4 (+2 for Bishop)
	 * @return
	 */
	private native long magicAtks(long occupied, int i);

	private native void init();
	private native long enemyAttacks(boolean wNext, long aMinor, long aMajor, long aSlider, long bOccupied);
	private native int tzcnt2(long b);
	
	// initialization variables
	public boolean wNext;
	public long bOccupied,aMinor,aMajor,aSlider,castling;
	public int wKingpos,bKingpos,epsq;

	// temp variables
	public long aOccupied, wOccupied, oOccupied, eOccupied,aPawns,aKnights,aBishops,aRooks,aQueens,aKings;
	public long checkers, pinners, oAttacked, eAttacked;
	public long wPawns,bPawns,wPawnAtks,bPawnAtks,wPawnAtkBy,bPawnAtkBy;
	public long aLine,aDiag;
	int oKingpos,eKingpos;
	int mdoffset;
	
	// movegen shortcuts
	public boolean ecq, eck, ocq, ock;
	public int erq,erk,orq,ork; // Enemy and own Rook Queen side or King side

	// movegen state
	public final int[] moves = new int[99];
	public int iAll, lvl1,lvl2;
	int iTested, lvl3;

	final void clearMoves(){
		iTested = 0;
		lvl1=0;
		lvl2=0;
		lvl3=0;
		iAll=0;
	}

	public String getFen() {
		return FEN.board2fen(FEN.boardFrom64(aMinor, aMajor, aSlider, bOccupied)) + " " +(wNext?"w":"b") + " " + FEN.getFenCastling(castling) + " "+ FEN.pos2string(epsq);
	}

	public void make(boolean wNext,long aMinor, long aMajor, long aSlider, long bOccupied, long castling, int md_num) {
		MOVEDATA md = MOVEDATA.ALL[md_num];
		int epsq2 = md instanceof ENPASSANT?((ENPASSANT)md).epsq:-1;
		long castling2 = md instanceof MOVEDATAX?castling^=((MOVEDATAX) md).castling:castling;
		initVariables(wNext,bOccupied^BOCCUPIED[md_num],aMinor^AMINOR[md_num],aMajor^AMAJOR[md_num],aSlider^ASLIDER[md_num],epsq2,castling2);
	}
	public void set(boolean wNext, long bOccupied, long aMinor, long aMajor, long aSlider, long bitmap) {
		initVariables(wNext,bOccupied,aMinor,aMajor,aSlider,BITS.getEnpassant(bitmap),~CASTLING_STATE | bitmap);
	}

	private final void initVariables(boolean wNext, long bOccupied, long aMinor, long aMajor, long aSlider, int epsq, long castling) {
		this.wNext		= wNext;
		this.bOccupied 	= bOccupied;
		this.aMinor 	= aMinor;
		this.aMajor 	= aMajor;
		this.aSlider 	= aSlider;
		this.epsq 		= epsq;
		this.castling 	= castling; 
		
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
		wKingpos 	= Long.numberOfTrailingZeros(wOccupied & aKings);
		bKingpos 	= Long.numberOfTrailingZeros(bOccupied & aKings);

		wPawns   	= wOccupied & aPawns;
        bPawns   	= bOccupied & aPawns;
		wPawnAtks 	= (((wPawns & MaskBToHFiles) << 7) | ((wPawns & MaskAToGFiles) << 9));  // Left - Right
		bPawnAtks 	= (((bPawns & MaskBToHFiles) >> 9) | ((bPawns & MaskAToGFiles) >> 7));  // Left - Right
		wPawnAtkBy 	= (((bPawns & MaskBToHFiles) >> 9) | ((bPawns & MaskAToGFiles) >> 7));  // Left - Right
		bPawnAtkBy 	= (((wPawns & MaskBToHFiles) << 7) | ((wPawns & MaskAToGFiles) << 9));  // Left - Right

		mdoffset    = wNext?0:MOVEDATA.BLACK_OFFSET;
		oOccupied	= wNext?wOccupied:bOccupied;
		eOccupied	= wNext?bOccupied:wOccupied;
		oKingpos 	= wNext?wKingpos:bKingpos;
		eKingpos 	= wNext?bKingpos:oKingpos;
		erk        	= wNext?BR_KING_STARTPOS:WR_KING_STARTPOS;
		erq        	= wNext?BR_QUEEN_STARTPOS:WR_QUEEN_STARTPOS;
		ecq			= (castling & (wNext?CANCASTLE_BLACKQUEEN:CANCASTLE_WHITEQUEEN)) != 0;
		eck			= (castling & (wNext?CANCASTLE_BLACKKING:CANCASTLE_WHITEKING)) != 0;
		ork 		= wNext?WR_KING_STARTPOS:BR_KING_STARTPOS;
		orq 		= wNext?WR_QUEEN_STARTPOS:BR_QUEEN_STARTPOS;
		ocq 		= (castling & (wNext?CANCASTLE_WHITEQUEEN:CANCASTLE_BLACKQUEEN)) != 0;
		ock 		= (castling & (wNext?CANCASTLE_WHITEKING:CANCASTLE_BLACKKING)) != 0;

	}


	public void undo(int md_num){
		MOVEDATA md = MOVEDATA.ALL[md_num];
		initVariables(!wNext,aSlider^md.bOccupied,bOccupied^md.aMinor,aMinor^md.aMajor,aMajor^md.aSlider
			,md instanceof ENPASSANT?((ENPASSANT)md).epsq:-1
			,md instanceof MOVEDATAX?castling^=((MOVEDATAX) md).castling:castling);
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
		moves[lvl1++]=md|mdoffset;
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
		moves[lvl2++]=md|mdoffset;
	}
	
	/**
	 * Loosing capture
	 * @param md
	 */
	final void add3(int md) {
		moves[iAll++] = moves[lvl3];
		moves[lvl3++]=md|mdoffset;
	}

	/**
	 * Silent move
	 * @param md
	 */
	final public void add4(int md) {
		moves[iAll++] = md|mdoffset;
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

	public static volatile long tot1;
	public static volatile long tot2;
	
	public void generate() {
		clearMoves();
		calculatePinnersAndCheckers();  // 1.000 of 19 secs 
		
		calculateEnemyAttacks1();	// 1.200 of 19 secs  (double with JNI)
		if(checkers==0){
			calculateNonEvasiveMoves();	// 10.0 of 16 secs
		} else {
			clearMoves(); // dont want pinned moves for evasive moves calculation
			calculateEvasiveMoves();   // 1.000 of 16 secs
		}
	}

	private void calculateEvasiveMoves() {
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

	private void calculateNonEvasiveMoves() {
		long mask = ~0L;
		long free = oOccupied & ~pinners;
		genKnight(free & aKnights, aOccupied,eOccupied,mask);  	// 1.495 of 18 secs   Knight
		genBishop(free & aBishops, aOccupied,eOccupied,mask);  	// 1.050 of 18 secs   Bishop
		genRook(free & aRooks, aOccupied,eOccupied,mask);		// 5.700 of 18 secs   Rook
		genQueen(free & aQueens, aOccupied,eOccupied,mask);		// 0.960 of 18 secs   Queen
		long t1=System.nanoTime();
		genKing();												// 1.750 of 18 secs   King
		tot1+=System.nanoTime()-t1;
		long t2=System.nanoTime();
		long pfree = free & aPawns;
		if (wNext) {											// 1.250 of 18 secs   Pawn
			long open1 = pfree&~(aOccupied>>8);
			long open2 = open1&0xFF00L&(~(aOccupied>>8)>>8);
			genPawn(pfree,open1,open2,eOccupied);

			// Castling
			if (ocq && ((CWQ_FREE & aOccupied) | (CWQ_MASK&eAttacked))==0) {
				add4(ock?MOVEDATA.MD_KCQ:MOVEDATA.MD_KCQ2);
			}
			if (ock && ((CWK_FREE & aOccupied) | (CWK_MASK&eAttacked))==0) {
				add4(ocq?MOVEDATA.MD_KCK:MOVEDATA.MD_KCK2);
			}

		} else {
			long open1 = pfree&~(aOccupied<<8);
			long open2 = open1&0x00FF000000000000L&(~(aOccupied<<8)<<8);
			genPawn(pfree,open1,open2,eOccupied);

			// Castling
			if (ocq && ((CBQ_FREE & aOccupied) | (CBQ_MASK&eAttacked))==0) {
				add4(ock?MOVEDATA.MD_KCQ:MOVEDATA.MD_KCQ2);
			}
			if (ock && ((CBK_FREE & aOccupied) | (CBK_MASK&eAttacked))==0) {
				add4(ocq?MOVEDATA.MD_KCK:MOVEDATA.MD_KCK2);
			}
		}
		tot2+=System.nanoTime()-t2;
	}

	private void calculatePinnersAndCheckers() {
		// Calculate checkers and pinners
		pinners=0L;

		// Regular checks
		checkers=(eOccupied & aKnights & BitBoard.NMasks[oKingpos]) | (eOccupied & aPawns & (wNext?MOVEDATA.REV_BP[oKingpos]:MOVEDATA.REV_WP[oKingpos]));

		// Slider checks
		if((eOccupied & aSlider & BitBoard.QMasks[oKingpos]) !=0){
			long diagatks = eOccupied & aDiag & BitBoard.BMasks[oKingpos];
			while(diagatks!=0){
				int asq = Long.numberOfTrailingZeros(diagatks);
				diagatks &= diagatks-1;
				long attacker = 1L << asq;
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
							slide2(attacker,between, 4,0,4,MOVEDATA.MD_Q,from,8);
						} else {
							slide2(attacker,between, 4,0,4,MOVEDATA.MD_B,from,4);
						}
					} else if((pinner&aPawns)!=0){  // PAWN CAPTURE
						int ctype = ctype(attacker);
						if(wNext){
							if(pinner<<7==attacker && (attacker&MaskHFile)==0){
								if(from>47){
									add1_promo(MOVEDATA.MD_PPL+(from%8)*20+ctype*4);
								} else
									capture(MOVEDATA.MD_PCL+(from*5)+ctype, 0, ctype, attacker);
							}
							if(pinner<<9==attacker && (attacker&MaskAFile)==0){
								if(from>47){
									add1_promo(MOVEDATA.MD_PPR+(from%8)*20+ctype*4);
								} else
									capture(MOVEDATA.MD_PCR+(from*5)+ctype, 0, ctype, attacker);
							}
						} else {
							if(pinner>>9==attacker && (attacker&MaskHFile)==0){
								if(from<16){
									add1_promo(MOVEDATA.MD_PPL+(from%8)*20+ctype*4);
								} else
									capture(MOVEDATA.MD_PCL+(from*5)+ctype, 0, ctype, attacker);
							}
							if(pinner>>7==attacker && (attacker&MaskAFile)==0){
								if(from<16){
									add1_promo(MOVEDATA.MD_PPR+(from%8)*20+ctype*4);
								} else
									capture(MOVEDATA.MD_PCR+(from*5)+ctype, 0, ctype, attacker);
							}
						}
					}
				}
			}
			long lineatks = eOccupied & aLine & BitBoard.RMasks[oKingpos];
			while (lineatks != 0){
				int asq = Long.numberOfTrailingZeros(lineatks);
				lineatks &= lineatks-1;
				long attacker = 1L << asq;
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
							slide2(attacker,between, 4,4,8,MOVEDATA.MD_Q,from,8);
						} else {
							slide2(attacker,between, 3,0,4,MOVEDATA.MD_R,from,4);
						}
					} else if((pinner&aPawns)!=0){  // PAWN FORWARD
						if(wNext){
							if(((pinner<<8)&between)!=0){
								add4(MOVEDATA.MD_P1+from);
								if(from<16 && ((pinner<<16)&between)!=0)
									add4(MOVEDATA.MD_P2+from%8);
							}
						} else {
							if(((pinner>>8)&between)!=0){
								add4(MOVEDATA.MD_P1+from);
								if(from>47 && ((pinner>>16)&between)!=0)
									add4(MOVEDATA.MD_P2+from%8);
							}
						}
					}
				}
			}
		}
	}


	private void calculateEnemyAttacks2() {
		eAttacked=enemyAttacks(wNext,aMinor,aMajor,aSlider,bOccupied);
	}

	// Calculate unsafe positions, those attacked by enemy
	private void calculateEnemyAttacks1() {
		long pcs=aOccupied&~(oOccupied &  aKings);
		eAttacked=wNext?wPawnAtkBy:bPawnAtkBy;
		long m;
		m=eOccupied & aKnights;
		while(m!=0){
			int sq = Long.numberOfTrailingZeros(m);
			eAttacked|=BitBoard.NMasks[sq];
			m &= m-1;
		}
		m=eOccupied & aKings;
		while(m!=0){
			int sq = Long.numberOfTrailingZeros(m);
			eAttacked|=BitBoard.KMasks[sq];
			m &= m-1;
		}
		m=eOccupied & aDiag;
		while(m!=0){
			int sq = Long.numberOfTrailingZeros(m);
			eAttacked|=BitBoard.bishopAttacks(sq, pcs);
			m &= m-1;
		}
		m=eOccupied & aLine;
		while(m!=0){
			int sq = Long.numberOfTrailingZeros(m);
			eAttacked|=BitBoard.rookAttacks(sq, pcs);
			m &= m-1;
		}
	}

	private void genKing() {
		if(oKingpos!= (wNext?WK_STARTPOS:BK_STARTPOS) || !(ock || ocq)) {
			genKingMoves(MOVEDATA.MD_K[oKingpos*2],MOVEDATA.MD_K[oKingpos*2+1]);
		} else {
			int i=(ocq?(ock?MOVEDATA.MD_KX:MOVEDATA.MD_KXQ):(ock?MOVEDATA.MD_KXK:0));
			genKingMoves(i,i+30);
		}
	}

	private void genKingMoves(int b, int e) {
		for (int s=b;s<e;s+=6){
			long bto = BTO[s|mdoffset];
			if ((aOccupied & bto) == 0) {
				if((bto&~eAttacked)!=0L)
					add4(s);
			} else {
				if ((eOccupied & bto & ~eAttacked) != 0) {
					int c = ctype(bto);
					if(c==3 && bto==1L<<erk && eck)
						add4(e+1); // Enemy Rook -> no castling king side
					else if(c==3 && bto==1L<<erq && ecq)
						add4(e); // Enemy Rook -> no castling queen side
					else
						add4(s+1+c);
				}
			}
		}
	}

	private void genQueen(long from, long occupied,long capture,long mask) {
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			genSlides(occupied, capture, mask, 4,MOVEDATA.MD_Q,8,sq);
		}
	}

	private void genBishop(long from, long occupied,long capture,long mask) {
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			genSlides(occupied, capture, mask, 2,MOVEDATA.MD_B,4,sq);
		}
	}

	private void genRook(long from, long occupied,long capture,long mask) {
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			// If castling opportunities will be broken then special Zobrist
			if(sq==orq){
				if(ocq ){
					genSlides(occupied, capture, mask, 3, wNext?MOVEDATA.MD_RQW:MOVEDATA.MD_RQB,4,0);
					continue;
				}
			} else if(sq==ork){
				if(ock ){
					genSlides(occupied, capture, mask, 3, wNext?MOVEDATA.MD_RKW:MOVEDATA.MD_RKB,4,0);
					continue;
				}
			}
			genSlides(occupied, capture, mask, 3,MOVEDATA.MD_R,4,sq);
		}
	}

	private void genSlides(long occupied, long capture, long mask, int val, int[] slider, int n, int sq) {
		int offset = sq*n*2;
		int q = slider[offset+2*n-1];
		int k = q+1;
		for (int j = 0; j < n; j++) {
			int b = slider[offset+j*2];
			int e = slider[offset+1+j*2];
			while (b < e) {
				long bto = BTO[b+5+mdoffset];
				if ((occupied & bto) != 0) {
					if ((capture & bto & mask) != 0) {
						int c = ctype(bto);
						if(c==3 && bto==1L<<erk  && eck){ // Enemy Rook -> no castling king side
							capture(k, val, c, bto);
						}else if(c==3 && bto==1L<<erq && ecq){ // Enemy Rook -> no castling queen side
							capture(q, val, c, bto);
						}else{
							capture(b + c, val, c, bto);
						}
					}
					break;
				} else {
					if((bto&mask)!=0){
						add4(b+5);
					}
					b += 6;
				}
			}
		}
	}

	private void genKnight(long from, long occupied,long capture,long mask) {
		while(from!=0){
			int sq = Long.numberOfTrailingZeros(from);
			from ^= 1L << sq;
			int b=MOVEDATA.MD_N[sq*2]+mdoffset;
			int e=MOVEDATA.MD_N[sq*2+1]+mdoffset;
			for (int s=b;s<e;s+=6){
				long bto = BTO[s];
				if ((occupied & bto) == 0L) {
					if((bto & mask)!=0L)
						add4(s);
				} else {
					if ((capture & bto & mask) != 0) {
						int c = ctype(bto);
						if(c==3 && bto==1L<<erk && eck) // Enemy Rook -> no castling king side
							capture(e+1, 1, c, bto);
						else {
							if(c==3 && bto==1L<<erq && ecq) // Enemy Rook -> no castling queen side
								capture(e, 1, c, bto);
							else
								capture(s+1+c, 1, c, bto);
						}
					}
				}
			}
		}
	}

	private void genPawn(long from, long open1, long open2, long captures) {
		while(open1!=0){
			int sq = Long.numberOfTrailingZeros(open1);
			open1 &= open1-1;
			if(wNext?sq>47:sq<16){
				add1_promo(MOVEDATA.MD_PP+4*(sq%8));
			} else {
				add4(MOVEDATA.MD_P1+sq);
			}
		}
		while(open2!=0){
			int sq = Long.numberOfTrailingZeros(open2);
			open2 &= open2-1;
			add4(MOVEDATA.MD_P2+sq%8);
		}
		long e=captures|(1L<<epsq);
		if(wNext){
			pwnCaptures(MOVEDATA.MD_PQ, (from & MaskBToHFiles) &(e>>7), 7, erq, ecq,true);
			pwnCaptures(MOVEDATA.MD_PK, (from & MaskAToGFiles) &(e>>9), 9, erk, eck,false);
		} else {
			pwnCaptures(MOVEDATA.MD_PQ, (from & MaskBToHFiles) &(e<<9), -9, erq, ecq,true);
			pwnCaptures(MOVEDATA.MD_PK, (from & MaskAToGFiles) &(e<<7), -7, erk, eck,false);
		}
	}

	private void pwnCaptures(int pc, long m, int step, int cs, boolean cc, boolean isLeft) {
		while(m!=0){
			int sq = Long.numberOfTrailingZeros(m);
			m &= m-1;
			int to=sq+step;
			if (to == epsq) {
				int md=(isLeft?MOVEDATA.MD_PEL:MOVEDATA.MD_PER)+sq%8;
				if(isSafeMove(md|mdoffset))	// Check for safety since there may be a covered check wit en-passant
					add2(md);
			} else {
				long bto = 1L << to;
				int ctype=ctype(bto);
				if((bto & MaskGoal)==0L){
					capture((isLeft?MOVEDATA.MD_PCL:MOVEDATA.MD_PCR)+(sq*5)+ctype, 0, ctype, bto);
				} else {
					if(cc && sq+step==cs){
						add1_promo(pc);
					} else {
						int p = (isLeft?MOVEDATA.MD_PPL:MOVEDATA.MD_PPR)+(sq%8)*20;
						add1_promo(p+ctype*4);
					}
				}
			}
		}
	}

	private void slide2(long attacker,long between, int type,int from,int to,int[] slider,int sq,int n) {
		int offset = sq*n*2;
		int q = slider[offset+2*n-1];
		int k = q+1;
		for (int j = from; j < to; j++) {
			int b = slider[offset+j*2];
			int e = slider[offset+1+j*2];
			while (b < e) {
				long bto = BTO[b + 5];
				if((between&bto)!=0){
					add4(b + 5);
					b += 6;
					continue;
				}
				if ((attacker & bto) != 0){
					int c = ctype(bto);
					if(c==3){
						if(bto==1L<<erk && eck)
							capture(k, type, c, bto);
						 else if(bto==1L<<erq && ecq)
							capture(q, type, c, bto);
						 else 
							capture(b+c, type, c, bto);
					} else {
						capture(b + c, type, c, bto);
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

	final private boolean isSafeMove(int md) {
		return isSafe(wNext,oKingpos,bOccupied^BOCCUPIED[md], aMinor^AMINOR[md], aMajor^AMAJOR[md], aSlider^ASLIDER[md]);
	}

	public final static boolean isSafe(boolean white,int king,long bb_black, long bb_bit1, long bb_bit2, long bb_bit3) {
		long bb_piece = bb_bit1 | bb_bit2 | bb_bit3;
		long bb_knights = ~bb_bit1 & bb_bit2 & ~bb_bit3;
		long bb_kings = bb_bit1 & bb_bit2 & ~bb_bit3;
		long bb_pawns = bb_bit1 & ~bb_bit2 & ~bb_bit3;
		long enemy=white?bb_black:(bb_bit1 | bb_bit2 | bb_bit3)^bb_black;
		if(((enemy & bb_knights & BitBoard.NMasks[king]) != 0) || 
				((enemy & bb_kings & BitBoard.KMasks[king]) != 0) || 
				((enemy & bb_pawns & (white?MOVEDATA.REV_BP[king]:MOVEDATA.REV_WP[king])) != 0))
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
    		int md = moves[i];
			
    		// Hiders
    		int from = BITS.getFrom(BITMAP[md]);
    		long bfrom=1L<<from;
    		if((bfrom&hiders)!=0){
				addChecker(i);
    			continue;
    		}
    		int piece = BITS.getPiece(BITMAP[md]);
    		long bto=BTO[md];
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
					if((bto & MOVEDATA.REV_WP[eKingpos])!=0){
						addChecker(i);
						continue;
					}
					break;
				case IConst.BP:
					if((bto & MOVEDATA.REV_BP[eKingpos])!=0){
						addChecker(i);
						continue;
					}
					break;
				}
    		}
		}
    }

	public boolean isClear(int md) {
		int to = BITS.getTo(BITMAP[md]);
		return Long.bitCount(BitBoard.BETWEEN[to+64*eKingpos]&aOccupied)==0;
	}

	private void addChecker(int i) {
		int sw=moves[lvl2];
		moves[lvl2++]=moves[i];
		moves[i]=sw;
	}

}
