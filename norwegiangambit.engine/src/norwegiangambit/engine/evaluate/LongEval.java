package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.util.BitBoard;

public class LongEval extends FastEval {

	static final int[] RookMobScore = {-10,-7,-4,-1,2,5,7,9,11,12,13,14,14,14,14};
	static final int[] BishMobScore = {-15,-10,-6,-2,2,6,10,13,16,18,20,22,23,24};
    static final int[] QueenMobScore = {-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,9,10,10,10,10,10,10,10,10,10,10,10,10};

    static final int[] PawnThreat  = {0,80,80,117,127}; // pnbrq
    static final int[] MinorThreat = {7,24,24,41,41}; // pnbrq
    static final int[] MajorThreat = {15,15,15,15,24}; 
    static final int   HANGING     = 23;

    int wMtrl,wMtrlPawns,bMtrl,bMtrlPawns;
	long wKnights,wBishops,wRooks,wQueens,wKings,bKnights,bBishops,bRooks,bQueens,bKings;
	long wMinorAtks,wMajorAtks,bMinorAtks,bMajorAtks;

    // King safety variables
    long wKingZone, bKingZone;      // Squares close to king that are worth attacking
    int wKingAtks, bKingAtks; 		// Number of attacks close to white/black king

	StringBuilder sb;
	
	public void longeval() {
		eval();
	}

	public void eval() {
        if(sb!=null)
        	sb.append(f(score,5));
        int plus= initAdjustments() + pawnBonus1() + passedPawnAdditionalBonus()+mob_atks()+special()+
        		castleBonus()+ tradeBonus()+ threatBonus()+kingSafety()+endGameEval(score);
        score+=plus;
        if(sb!=null){
        	sb.append(f(plus,5));
        	sb.append(f(score,5));
        }
	}

	public static String f(int in,int i){
		String all="           "+in;
		return all.substring(all.length()-i);
	}
	
	public String printEval() {
		sb=new StringBuilder();
		eval();
		return sb.toString();
	}

	public int initAdjustments() {
		wKnights = wOccupied & aKnights;
        wBishops = wOccupied & aBishops;
        wRooks   = wOccupied & aRooks;
        wQueens  = wOccupied & aQueens;
        wKings   = wOccupied & aKings;
        bKnights = bOccupied & aKnights;
        bBishops = bOccupied & aBishops;
        bRooks   = bOccupied & aRooks;
        bQueens  = bOccupied & aQueens;
        bKings   = bOccupied & aKings;
        int wPawnNum = Long.bitCount(wPawns);
        int bPawnNum = Long.bitCount(bPawns);
        int wBishopNum = Long.bitCount(wBishops);
        int bBishopNum = Long.bitCount(bBishops);
		int wRookNum = Long.bitCount(wRooks);
		int bRookNum = Long.bitCount(bRooks);
		wMtrlPawns=pV*wPawnNum;
		bMtrlPawns=pV*bPawnNum;
		wMtrl=nV*Long.bitCount(wKnights)+bV*wBishopNum+rV*wRookNum+qV*Long.bitCount(wQueens);
		bMtrl=nV*Long.bitCount(bKnights)+bV*bBishopNum+rV*bRookNum+qV*Long.bitCount(bQueens);
		wKingZone=BitBoard.KMasks[wkingpos];
		bKingZone=BitBoard.KMasks[bkingpos];
		
		// Bishop Pair Bonus
		int bishopPair= bishopPairBonus(wBishopNum, wPawnNum) - bishopPairBonus(bBishopNum, bPawnNum);
		
		// Rook on enemy pawn row bonus
		int rookEnemyRow=(wRookNum==2 && (wRooks &  ~0xff000000000000L)==0L && (bKings & ~0xff00000000000000L) == 0L ? 30:0)
				    -(bRookNum==2 && (bRooks &  ~0x0000000000ff00L)==0L && (wKings & ~0x00000000000000ffL) == 0L ? 30:0);
		
		if(sb!=null){
        	sb.append(f(bishopPair,3));
        	sb.append(f(rookEnemyRow,3));
        }
		return bishopPair+rookEnemyRow;
	}

	private final static int bishopPairBonus(int wBishopNum, int wPawnNum) {
		return wBishopNum==2?28 + (8 - wPawnNum) * 3:0;
	}


	private int mob_atks() {
        int wscore = 0, bscore = 0;
        {
	        long m = wBishops;
	        while (m != 0L) {
	            int sq = Long.numberOfTrailingZeros(m);
	            long atk = BitBoard.bishopAttacks(sq, aOccupied);
	            wMinorAtks |= atk;
	            wscore += BishMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAtks))];
	            if ((atk & bKingZone) != 0)
	                bKingAtks += Long.bitCount(atk & bKingZone);
	            m &= m-1;
	        }
        }
        {
			long m = wRooks;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
				wscore+=rookOpenFileBonus(sq,wPawns,bPawns);
	            long atk = BitBoard.rookAttacks(sq, aOccupied);
	            wMajorAtks |= atk;
	            wscore += RookMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAtks))];
	            if ((atk & bKingZone) != 0)
	                bKingAtks += Long.bitCount(atk & bKingZone);
	            m &= m-1;
	        }
		}
        {
			long m = wQueens;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
				wscore+=rookOpenFileBonus(sq,wPawns,bPawns);
	            long atk = BitBoard.rookAttacks(sq, aOccupied) | BitBoard.bishopAttacks(sq, aOccupied);
	            wMajorAtks |= atk;
	            wscore += QueenMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAtks))];
	            if ((atk & bKingZone) != 0)
	                bKingAtks += Long.bitCount(atk & bKingZone);
	            m &= m-1;
	        }
		}
        {
	        long m = bBishops;
	        while (m != 0L) {
	            int sq = Long.numberOfTrailingZeros(m);
	            long atk = BitBoard.bishopAttacks(sq, aOccupied);
	            bMinorAtks |= atk;
	            bscore += BishMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAtks))];
	            if ((atk & wKingZone) != 0)
	                wKingAtks += Long.bitCount(atk & wKingZone);
	            m &= m-1;
	        }
        }
        {
	        long m = bRooks;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
				bscore+=rookOpenFileBonus(sq,bPawns,wPawns);
	            long atk = BitBoard.rookAttacks(sq, aOccupied);
	            bMajorAtks |= atk;
	            bscore += RookMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAtks))];
	            if ((atk & wKingZone) != 0)
	                wKingAtks += Long.bitCount(atk & wKingZone);
	            m &= m-1;
	        }
        }
        {
	        long m = bQueens;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
				bscore+=rookOpenFileBonus(sq,bPawns,wPawns);
	            long atk = BitBoard.rookAttacks(sq, aOccupied) | BitBoard.bishopAttacks(sq, aOccupied);
	            bMajorAtks |= atk;
	            bscore += QueenMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAtks))];
	            if ((atk & wKingZone) != 0)
	                wKingAtks += Long.bitCount(atk & wKingZone);
	            m &= m-1;
	        }
        }

        if(sb!=null){
        	sb.append(f(wscore,4));
        	sb.append(f(bscore,4));
        }
        return wscore-bscore;
	}

	private int special() {
        int wscore = 0,bscore=0;

        // Penalty for bishop trapped behind pawn at a2/h2/a7/h7
        if ((aBishops & 0x0081000000008100L) != 0) {
            if ((((1L<<48)& wBishops)!=0L) && // a7
                (((1L<<41)& bPawns)!=0L) &&
                (((1L<<50)& bPawns)!=0L))
                bscore += 150;
            if ((((1L<<55)& wBishops)!=0L) && // h7
                (((1L<<46)& bPawns)!=0L) &&
                (((1L<<53)& bPawns)!=0L))
                bscore += (wQueens != 0) ? pV : pV * 3 / 2;
            if ((((1L<<8)& bBishops)!=0L) &&  // a2
                (((1L<<17)& wPawns)!=0L) &&
                (((1L<<10)& wPawns)!=0L))
                wscore += 150;
            if ((((1L<<15)& bBishops)!=0L) && // h2
                (((1L<<22)& wPawns)!=0L) &&
                (((1L<<13)& wPawns)!=0L))
                wscore += (bQueens != 0) ? pV : pV * 3 / 2;
        }
        if(sb!=null){
        	sb.append(f(wscore,4));
        	sb.append(f(bscore,4));
        }
        return wscore-bscore;
	}

	private int rookOpenFileBonus(int sq,long own, long other) {
        long mask = BitBoard.maskFile[BitBoard.sq2x(sq)];
        return (own & mask) == 0L?(other & mask) == 0L ? 25 : 12:0;
	}

	private int threatBonus() {
        return threatBonus(bOccupied, wOccupied & ~aKings) + threatBonus(wOccupied, bOccupied & ~aKings);
	}

	private int threatBonus(long own, long enemy) {
		int s=threatOffset((wNext?wPawnAtks:bPawnAtks)&enemy);
		{
	        long m = own&aKnights;
	        while(m!=0L){
				int sq = Long.numberOfTrailingZeros(m);
				s+=threatOffset(BitBoard.NMasks[sq]&enemy);
	            m &= m-1;
			}
		}
        {
            long m = own&aBishops;
			while(m!=0L){
				int sq = Long.numberOfTrailingZeros(m);
				s+=threatOffset(BitBoard.bishopAttacks(sq, aOccupied)&enemy);
	            m &= m-1;
			}
		}
        {
            long m = own&aRooks;
			while(m!=0L){
				int sq = Long.numberOfTrailingZeros(m);
				s+=threatOffset(BitBoard.rookAttacks(sq, aOccupied)&enemy);
	            m &= m-1;
			}
		}
        {
            long m = own&aQueens;
			while(m!=0L){
				int sq = Long.numberOfTrailingZeros(m);
				s+=threatOffset(BitBoard.bishopAttacks(sq, aOccupied)&enemy);
				s+=threatOffset(BitBoard.rookAttacks(sq, aOccupied)&enemy);
	            m &= m-1;
			}
		}
		s /=64;
		
        if(sb!=null){
        	sb.append(f(s,5));
        }
		return s;
	}

	private int threatOffset(long m) {
		int score=0;
		while(m!=0L){
			int sq = Long.numberOfTrailingZeros(m);
			int type = type(sq);
			if(type!=0){
				int val = MBase.psqt(sq, type)[1];
				score+=val+val*val/qV;
			}
            m &= m-1;
		}
		return score;
	}

    /**
     * Interpolate between (x1,y1) and (x2,y2).
     * If x < x1, return y1, if x > x2 return y2. Otherwise, use linear interpolation.
     */
    static final int interpolate(int x, int x1, int y1, int x2, int y2) {
        if (x > x2) {
            return y2;
        } else if (x < x1) {
            return y1;
        } else {
            return (x - x1) * (y2 - y1) / (x2 - x1) + y1;
        }
    }

    /** Implement the "when ahead trade pieces, when behind trade pawns" rule. */
	private int tradeBonus() {
        final int deltaScore = (wMtrl - bMtrl) * 30 / 100;
        int pBonus1 = interpolate((deltaScore > 0) ? wMtrlPawns : bMtrlPawns, 0, -deltaScore, 6 * pV, 0);
        int pBonus2 = interpolate((deltaScore > 0) ? bMtrl : wMtrl, 0, deltaScore, qV + 2 * rV + 2 * bV + 2 * nV, 0);
        if(sb!=null){
        	sb.append(f(pBonus1,3));
        	sb.append(f(pBonus2,3));
        }
		return pBonus1+pBonus2;
	}

    private static final int[] castleFactor;
    static {
        castleFactor = new int[256];
        for (int i = 0; i < 256; i++) {
            int h1Dist = 100;
            boolean h1Castle = (i & (1<<7)) != 0;
            if (h1Castle)
                h1Dist = 2 + Long.bitCount(i & 0x0000000000000060L); // f1,g1
            int a1Dist = 100;
            boolean a1Castle = (i & 1) != 0;
            if (a1Castle)
                a1Dist = 2 + Long.bitCount(i & 0x000000000000000EL); // b1,c1,d1
            castleFactor[i] = 1024 / Math.min(a1Dist, h1Dist);
        }
    }

    /** Score castling ability. */
    private final int castleBonus() {
//        if (pos.getCastleMask() == 0) return 0;
//
//        final int k1 = kt1b[7*8+6] - kt1b[7*8+4];
//        final int k2 = kt2b[7*8+6] - kt2b[7*8+4];
//        final int t1 = qV + 2 * rV + 2 * bV;
//        final int t2 = rV;
//        final int t = pos.bMtrl - pos.bMtrlPawns;
//        final int ks = interpolate(t, t2, k2, t1, k1);
//
//        final int castleValue = ks + rt1b[7*8+5] - rt1b[7*8+7];
//        if (castleValue <= 0)
//            return 0;
//
//        long occupied = pos.whiteBB | pos.blackBB;
//        int tmp = (int) (occupied & 0x6E);
//        if (pos.a1Castle()) tmp |= 1;
//        if (pos.h1Castle()) tmp |= (1 << 7);
//        final int wBonus = (castleValue * castleFactor[tmp]) >> 10;
//
//        tmp = (int) ((occupied >>> 56) & 0x6E);
//        if (pos.a8Castle()) tmp |= 1;
//        if (pos.h8Castle()) tmp |= (1 << 7);
//        final int bBonus = (castleValue * castleFactor[tmp]) >> 10;
//
//        return wBonus - bBonus;
        if(sb!=null){
        	sb.append(f(0,3));
        	sb.append(f(0,3));
        }
    	return 0;
    }
	private int kingSafety() {
        final int minM = rV + bV;
        final int m = (wMtrl - wMtrlPawns + bMtrl - bMtrlPawns) / 2;
        if (m <= minM)
            return 0;
        final int maxM = qV + 2 * rV + 2 * bV + 2 * nV;
        int score = kingSafetyKPPart();
        if(sb!=null){
        	sb.append(f(score,3));
        }
        if (BitBoard.sq2y(wkingpos) == 0) {
            if (((wKings & 0x60L) != 0) && // King on f1 or g1
                ((wRooks & 0xC0L) != 0) && // Rook on g1 or h1
                ((wPawns & BitBoard.maskFile[6]) != 0) &&
                ((wPawns & BitBoard.maskFile[7]) != 0)) {
                score -= 6 * 15;
            } else
            if (((wKings & 0x6L) != 0) && // King on b1 or c1
                ((wRooks & 0x3L) != 0) && // Rook on a1 or b1
                ((wPawns & BitBoard.maskFile[0]) != 0) &&
                ((wPawns & BitBoard.maskFile[1]) != 0)) {
                score -= 6 * 15;
            }
        }
        if (BitBoard.sq2y(bkingpos) == 7) {
            if (((bKings & 0x6000000000000000L) != 0) && // King on f8 or g8
                ((bRooks & 0xC000000000000000L) != 0) && // Rook on g8 or h8
                ((bPawns & BitBoard.maskFile[6]) != 0) &&
                ((bPawns & BitBoard.maskFile[7]) != 0)) {
                score += 6 * 15;
            } else
            if (((bKings & 0x600000000000000L) != 0) && // King on b8 or c8
                ((bRooks & 0x300000000000000L) != 0) && // Rook on a8 or b8
                ((bPawns & BitBoard.maskFile[0]) != 0) &&
                ((bPawns & BitBoard.maskFile[1]) != 0)) {
                score += 6 * 15;
            }
        }
        score += (bKingAtks - wKingAtks) * 4;
        final int kSafety = interpolate(m, minM, 0, maxM, score);
        if(sb!=null){
        	sb.append(f(kSafety,3));
        }
        return kSafety;
	}

    private final int kingSafetyKPPart() {
        // FIXME!!! Try non-linear king safety
        final long key = pawnhash ^ kinghash;
        int i=KingHashTable.get(key);
        if(i>=0)
        	return KingHashTable.getScore(KingHashTable.data[i]);
        int score = wKingSafety()-bKingSafety();
        KingHashTable.set(key,score);
        return score;
    }

	public int bKingSafety() {
		int safety = 0;
		int halfOpenFiles = 0;
		if (BitBoard.sq2y(bkingpos) >= 6) {
		    long shelter = 1L << (56 + BitBoard.sq2x(bkingpos));
		    shelter |= ((shelter & MaskBToHFiles) >>> 1) | ((shelter & MaskAToGFiles) << 1);
		    shelter >>>= 8;
		    safety += 3 * Long.bitCount(bPawns & shelter);
		    safety -= 2 * Long.bitCount(wPawns & (shelter | (shelter >>> 8)));
		    shelter >>>= 8;
		    safety += 2 * Long.bitCount(bPawns & shelter);
		    shelter >>>= 8;
		    safety -= Long.bitCount(wPawns & shelter);
		    long wOpen = BitBoard.southFill(shelter) & (~BitBoard.southFill(wPawns)) & 0xff;
		    if (wOpen != 0) {
		        halfOpenFiles += 25 * Long.bitCount(wOpen & 0xe7);
		        halfOpenFiles += 10 * Long.bitCount(wOpen & 0x18);
		    }
		    long bOpen = BitBoard.southFill(shelter) & (~BitBoard.southFill(bPawns)) & 0xff;
		    if (bOpen != 0) {
		        halfOpenFiles += 25 * Long.bitCount(bOpen & 0xe7);
		        halfOpenFiles += 10 * Long.bitCount(bOpen & 0x18);
		    }
		    safety = Math.min(safety, 8);
		}
		return (safety - 9) * 15 - halfOpenFiles;
	}

	public int wKingSafety() {
		int safety = 0;
		int halfOpenFiles = 0;
		if (BitBoard.sq2y(wkingpos) < 2) {
		    long shelter = 1L << BitBoard.sq2x(wkingpos);
		    shelter |= ((shelter & MaskBToHFiles) >>> 1) | ((shelter & MaskAToGFiles) << 1);
		    shelter <<= 8;
		    safety += 3 * Long.bitCount(wPawns & shelter);
		    safety -= 2 * Long.bitCount(bPawns & (shelter | (shelter << 8)));
		    shelter <<= 8;
		    safety += 2 * Long.bitCount(wPawns & shelter);
		    shelter <<= 8;
		    safety -= Long.bitCount(bPawns & shelter);
		    long wOpen = BitBoard.southFill(shelter) & (~BitBoard.southFill(wPawns)) & 0xff;
		    if (wOpen != 0) {
		        halfOpenFiles += 25 * Long.bitCount(wOpen & 0xe7);
		        halfOpenFiles += 10 * Long.bitCount(wOpen & 0x18);
		    }
		    long bOpen = BitBoard.southFill(shelter) & (~BitBoard.southFill(bPawns)) & 0xff;
		    if (bOpen != 0) {
		        halfOpenFiles += 25 * Long.bitCount(bOpen & 0xe7);
		        halfOpenFiles += 10 * Long.bitCount(bOpen & 0x18);
		    }
		    safety = Math.min(safety, 8);
		}
		final int kSafety = (safety - 9) * 15 - halfOpenFiles;
		return kSafety;
	}

    int passedBonusW,passedBonusB;
    long passedPawnsW,passedPawnsB;
    
    private int pawnBonus1() {
        long key = pawnhash;
        int i = PawnHashTable.get(key);
        long data=0L;
        if(i==-1){
            i=computePawnHashData(key);
            data=PawnHashTable.data[i];
            passedBonusW=PawnHashTable.getPassedBonusW(data);
            passedBonusB=PawnHashTable.getPassedBonusB(data);
            passedPawnsW=PawnHashTable.passedW[i];
            passedPawnsB=PawnHashTable.passedB[i];
        } else {
            data=PawnHashTable.data[i];
        }
        int s = PawnHashTable.getScore(data);
        if(sb!=null)
        	sb.append(f(s,4));
		return s;
    }
    private int passedPawnAdditionalBonus() {
        final int hiMtrl = qV + rV;
        // Passed pawns are more dangerous if enemy king is far away
        int wscore = interpolate(bMtrl - bMtrlPawns, 0, 2 * passedBonusW, hiMtrl, passedBonusW);
        int bestWPawnDist = 8;
        int bestWPromSq = -1;
        long m = passedPawnsW;
        if (m != 0) {
            int mtrlNoPawns = bMtrl - bMtrlPawns;
            if (mtrlNoPawns < hiMtrl) {
                int kingPos = wkingpos;
                while (m != 0) {
                    int sq = Long.numberOfTrailingZeros(m);
                    int x = BitBoard.sq2x(sq);
                    int y = BitBoard.sq2y(sq);
                    int pawnDist = Math.min(5, 7 - y);
                    int kingDist = BitBoard.getDistance(kingPos, BitBoard.xy2sq(x, 7));
                    int kScore = kingDist * 4;
                    if (kingDist > pawnDist) kScore += (kingDist - pawnDist) * (kingDist - pawnDist);
                    wscore += interpolate(mtrlNoPawns, 0, kScore, hiMtrl, 0);
                    if (!wNext)
                        kingDist--;
                    if ((pawnDist < kingDist) && (mtrlNoPawns == 0)) {
                        if ((BitBoard.northFill(1L<<sq) & (1L << wkingpos)) != 0)
                            pawnDist++; // Own king blocking pawn
                        if (pawnDist < bestWPawnDist) {
                            bestWPawnDist = pawnDist;
                            bestWPromSq = BitBoard.xy2sq(x, 7);
                        }
                    }
                    m &= m-1;
                }
            }
        }
        
        int bscore = interpolate(wMtrl - wMtrlPawns, 0, 2 * passedBonusB, hiMtrl, passedBonusB);
        int bestBPawnDist = 8;
        int bestBPromSq = -1;
        m = passedPawnsB;
        if (m != 0) {
            int mtrlNoPawns = wMtrl - wMtrlPawns;
            if (mtrlNoPawns < hiMtrl) {
                int kingPos = wkingpos;
                while (m != 0) {
                    int sq = Long.numberOfTrailingZeros(m);
                    int x = BitBoard.sq2x(sq);
                    int y = BitBoard.sq2y(sq);
                    int pawnDist = Math.min(5, y);
                    int kingDist = BitBoard.getDistance(kingPos, BitBoard.xy2sq(x, 0));
                    int kScore = kingDist * 4;
                    if (kingDist > pawnDist) kScore += (kingDist - pawnDist) * (kingDist - pawnDist);
                    bscore += interpolate(mtrlNoPawns, 0, kScore, hiMtrl, 0);
                    if (wNext)
                        kingDist--;
                    if ((pawnDist < kingDist) && (mtrlNoPawns == 0)) {
                        if ((BitBoard.southFill(1L<<sq) & (1L << bkingpos)) != 0)
                            pawnDist++; // Own king blocking pawn
                        if (pawnDist < bestBPawnDist) {
                            bestBPawnDist = pawnDist;
                            bestBPromSq = BitBoard.xy2sq(x, 0);
                        }
                    }
                    m &= m-1;
                }
            }
        }

        // Evaluate pawn races in pawn end games
        if (bestWPromSq >= 0) {
            if (bestBPromSq >= 0) {
                int wPly = bestWPawnDist * 2; if (wNext) wPly--;
                int bPly = bestBPawnDist * 2; if (!wNext) bPly--;
                if (wPly < bPly - 1) {
                    wscore += 500;
                } else if (wPly == bPly - 1) {
                    if (BitBoard.getDirection(bestWPromSq, bkingpos) != 0)
                        wscore += 500;
                } else if (wPly == bPly + 1) {
                    if (BitBoard.getDirection(bestBPromSq, bkingpos) != 0)
                        bscore += 500;
                } else {
                    bscore += 500;
                }
            } else
                wscore += 500;
        } else if (bestBPromSq >= 0)
            bscore += 500;
        if(sb!=null){
        	sb.append(f(wscore,3));
        	sb.append(f(bscore,3));
        }
        return wscore-bscore;
	}
	
	private int computePawnHashData(long key) {
        int score = 0;

        // Evaluate double pawns and pawn islands
        long wPawnFiles = BitBoard.southFill(wPawns) & 0xff;
        int wDouble = Long.bitCount(wPawns) - Long.bitCount(wPawnFiles);
        int wIslands = Long.bitCount(((~wPawnFiles) >>> 1) & wPawnFiles);
        int wIsolated = Long.bitCount(~(wPawnFiles<<1) & wPawnFiles & ~(wPawnFiles>>>1));

        
        long bPawnFiles = BitBoard.southFill(bPawns) & 0xff;
        int bDouble = Long.bitCount(bPawns) - Long.bitCount(bPawnFiles);
        int bIslands = Long.bitCount(((~bPawnFiles) >>> 1) & bPawnFiles);
        int bIsolated = Long.bitCount(~(bPawnFiles<<1) & bPawnFiles & ~(bPawnFiles>>>1));

        score -= (wDouble - bDouble) * 25;
        score -= (wIslands - bIslands) * 15;
        score -= (wIsolated - bIsolated) * 15;

        // Evaluate backward pawns, defined as a pawn that guards a friendly pawn,
        // can't be guarded by friendly pawns, can advance, but can't advance without 
        // being captured by an enemy pawn.
        long wBackward = wPawns & ~(aPawns >> 8) & (bPawnAtks >> 8) & ~BitBoard.northFill(wPawnAtks);
        wBackward &= bPawnAtks;
        wBackward &= ~BitBoard.northFill(bPawnFiles);
        long bBackward = bPawns & ~(aPawns << 8) & (wPawnAtks << 8) & ~BitBoard.southFill(bPawnAtks);
        bBackward &= wPawnAtks;
        bBackward &= ~BitBoard.northFill(wPawnFiles);
        score -= (Long.bitCount(wBackward) - Long.bitCount(bBackward)) * 15;

        // Evaluate passed pawn bonus, white
        passedPawnsW = wPawns & ~BitBoard.southFill(bPawns | bPawnAtks | (wPawns >>> 8));
        final int[] ppBonus = {-1,24,26,30,36,55,100,-1};
        passedBonusW = 0;
        if (passedPawnsW != 0) {
            long guardedPassedW = passedPawnsW & wPawnAtks;
            passedBonusW += 15 * Long.bitCount(guardedPassedW);
            long m = passedPawnsW;
            while (m != 0) {
                int sq = Long .numberOfTrailingZeros(m);
                int y = BitBoard.sq2y(sq);
                passedBonusW += ppBonus[y];
                m &= m-1;
            }
        }

        // Evaluate passed pawn bonus, black
        passedPawnsB = bPawns & ~BitBoard.northFill(wPawns | wPawnAtks | (bPawns << 8));
        passedBonusB = 0;
        if (passedPawnsB != 0) {
            long guardedPassedB = passedPawnsB & bPawnAtks;
            passedBonusB += 15 * Long.bitCount(guardedPassedB);
            long m = passedPawnsB;
            while (m != 0) {
                int sq = Long.numberOfTrailingZeros(m);
                int y = BitBoard.sq2y(sq);
                passedBonusB += ppBonus[7-y];
                m &= m-1;
            }
        }
        return PawnHashTable.set(score,pawnhash,passedBonusW,passedBonusB,passedPawnsW,passedPawnsB);
	}

	private int endGameEval(int score) {
        if(sb!=null){
        	sb.append(f(0,4));
        }
		return 0;
	}

}
