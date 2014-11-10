package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.util.BitBoard;
import norwegiangambit.util.IConst;

public class LongEval extends FastEval {

	static final int[] RookMobScore = {-10,-7,-4,-1,2,5,7,9,11,12,13,14,14,14,14};
	static final int[] BishMobScore = {-15,-10,-6,-2,2,6,10,13,16,18,20,22,23,24};
    static final int[] QueenMobScore = {-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,9,10,10,10,10,10,10,10,10,10,10,10,10};
	
	int wMtrl,wMtrlPawns,bMtrl,bMtrlPawns;
	long wPawns,wKnights,wBishops,wRooks,wQueens,wKings,bPawns,bKnights,bBishops,bRooks,bQueens,bKings;

	public void initAdjustments() {
		wPawns   = wOccupied & aPawns;
        wKnights = wOccupied & aKnights;
        wBishops = wOccupied & aBishops;
        wRooks   = wOccupied & aRooks;
        wQueens  = wOccupied & aQueens;
        wKings   = wOccupied & aKings;
        bPawns   = bOccupied & aPawns;
        bKnights = bOccupied & aKnights;
        bBishops = bOccupied & aBishops;
        bRooks   = bOccupied & aRooks;
        bQueens  = bOccupied & aQueens;
        bKings   = bOccupied & aKings;
        wMtrlPawns=pV*Long.bitCount(wPawns);
        bMtrlPawns=pV*Long.bitCount(bPawns);
        wMtrl=nV*Long.bitCount(wKnights)+bV*Long.bitCount(wBishops)+rV*Long.bitCount(wRooks)+qV*Long.bitCount(wQueens);
        bMtrl=nV*Long.bitCount(bKnights)+bV*Long.bitCount(bBishops)+rV*Long.bitCount(bRooks)+qV*Long.bitCount(bQueens);
	}

	public void longeval() {
		eval(false);
	}

	public String eval(boolean print) {
        initAdjustments();
        int pawnBonus = pawnBonus();
        int tradeBonus = tradeBonus();
        int castleBonus = castleBonus();
        int rookBonus = rookBonus();
        int bishopBonus = bishopBonus();
        int threatBonus = threatBonus();
        int kingSafety = kingSafety();
        int endgame = endGameEval(score);
        int plus= pawnBonus+tradeBonus+ castleBonus+ rookBonus+bishopBonus+threatBonus+kingSafety+endgame;
        if(print){
        	StringBuilder sb = new StringBuilder();
        	sb.append(" _<"+f(score,4));
        	sb.append(" pb"+f(pawnBonus,3));
        	sb.append(" tb"+f(tradeBonus,3));
        	sb.append(" cb"+f(castleBonus,3));
        	sb.append(" rb"+f(rookBonus,3));
        	sb.append(" bb"+f(bishopBonus,3));
        	sb.append(" xb"+f(threatBonus,3));
        	sb.append(" ks"+f(kingSafety,3));
        	sb.append(" eg"+f(endgame,3));
        	sb.append(" _>"+f(plus,3));
        	sb.append(" _>"+f(score+plus,4));
   			return sb.toString();
        }
        score+=plus;
        return null;
	}

	public static String f(int in,int i){
		String all="           "+in;
		return all.substring(all.length()-3);
	}
	
	public String printEval() {
		return eval(true);
	}

    // King safety variables
    private long wKingZone, bKingZone;       // Squares close to king that are worth attacking
    private int wKingAttacks, bKingAttacks; // Number of attacks close to white/black king
    private long wAttacksBB, bAttacksBB;
    private long wPawnAttacks, bPawnAttacks; // Squares attacked by white/black pawns

	private int bishopBonus() {
        int score = 0;
        final long occupied = aOccupied;
        if ((wBishops | bBishops) == 0)
            return 0;
        long m = wBishops;
        while (m != 0) {
            int sq = Long.numberOfTrailingZeros(m);
            long atk = BitBoard.bishopAttacks(sq, occupied);
            wAttacksBB |= atk;
            score += BishMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAttacks))];
            if ((atk & bKingZone) != 0)
                bKingAttacks += Long.bitCount(atk & bKingZone);
            m &= m-1;
        }
        m = bBishops;
        while (m != 0) {
            int sq = Long.numberOfTrailingZeros(m);
            long atk = BitBoard.bishopAttacks(sq, occupied);
            bAttacksBB |= atk;
            score -= BishMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAttacks))];
            if ((atk & wKingZone) != 0)
                wKingAttacks += Long.bitCount(atk & wKingZone);
            m &= m-1;
        }

        boolean whiteDark  = (wBishops & MaskDarkSq ) != 0;
        boolean whiteLight = (wBishops & MaskLightSq) != 0;
        boolean blackDark  = (bBishops & MaskDarkSq ) != 0;
        boolean blackLight = (bBishops & MaskLightSq) != 0;
        
        // Bishop pair bonus
		if (whiteDark && whiteLight) {
			score += 28 + (8 - Long.bitCount(wPawns)) * 3;
        }
		if (blackDark && blackLight) {
			score -= 28 + (8 - Long.bitCount(bPawns)) * 3;
        }
    
        // Penalty for bishop trapped behind pawn at a2/h2/a7/h7
        if (((wBishops | bBishops) & 0x0081000000008100L) != 0) {
            if ((((1L<<48)& wBishops)!=0L) && // a7
                (((1L<<41)& bPawns)!=0L) &&
                (((1L<<50)& bPawns)!=0L))
                score -= 150;
            if ((((1L<<55)& wBishops)!=0L) && // h7
                (((1L<<46)& bPawns)!=0L) &&
                (((1L<<53)& bPawns)!=0L))
                score -= (wQueens != 0) ? pV : pV * 3 / 2;
            if ((((1L<<8)& bBishops)!=0L) &&  // a2
                (((1L<<17)& wPawns)!=0L) &&
                (((1L<<10)& wPawns)!=0L))
                score += 150;
            if ((((1L<<15)& bBishops)!=0L) && // h2
                (((1L<<22)& wPawns)!=0L) &&
                (((1L<<13)& wPawns)!=0L))
                score += (bQueens != 0) ? pV : pV * 3 / 2;
        }
        return score;
	}

	private int rookBonus() {
        int score = 0;
		long m = wRooks;
        while (m != 0) {
            int sq = Long.numberOfTrailingZeros(m);
            final int x = BitBoard.sq2x(sq);
            if ((wPawns & BitBoard.maskFile[x]) == 0) { // At least half-open file
                score += (bPawns & BitBoard.maskFile[x]) == 0 ? 25 : 12;
            }
            long atk = BitBoard.rookAttacks(sq, aOccupied);
            wAttacksBB |= atk;
            score += RookMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAttacks))];
            if ((atk & bKingZone) != 0)
                bKingAttacks += Long.bitCount(atk & bKingZone);
            m &= m-1;
        }
        long r7 = (wRooks >>> 48) & 0x00ffL;
        if (((r7 & (r7 - 1)) != 0) &&
            ((bKings & 0xff00000000000000L) != 0))
            score += 30; // Two rooks on 7:th row
        m = bRooks;
        while (m != 0) {
            int sq = Long.numberOfTrailingZeros(m);
            final int x = BitBoard.sq2x(sq);
            if ((bPawns & BitBoard.maskFile[x]) == 0) {
                score -= (wPawns & BitBoard.maskFile[x]) == 0 ? 25 : 12;
            }
            long atk = BitBoard.rookAttacks(sq, aOccupied);
            bAttacksBB |= atk;
            score -= RookMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAttacks))];
            if ((atk & wKingZone) != 0)
                wKingAttacks += Long.bitCount(atk & wKingZone);
            m &= m-1;
        }
        r7 = bRooks & 0xff00L;
        if (((r7 & (r7 - 1)) != 0) &&
            ((wKings & 0xffL) != 0))
          score -= 30; // Two rooks on 2:nd row
        return score;
	}

	private int threatBonus() {
        long kMask = ~(aMinor& aMajor&~aSlider); 
        int score1 = threatBonus(bOccupied, wOccupied & kMask);
		int score2 = threatBonus(wOccupied, bOccupied & kMask);
		return (score1 + score2) / 64;
	}

	private int threatBonus(long own, long enemy) {
		int score = 0;
        long op = own&aPawns;
        long on = own&aKnights;
        long ob = own&aBishops;
        long or = own&aRooks;
        long oq = own&aQueens;
		long atks=wNext?((op&MaskAToGFiles)>>7) | ((op&MaskBToHFiles)>>9):((op&MaskAToGFiles)<<9) | ((op&MaskBToHFiles)<<7);
		score+=threatOffset(atks&enemy);
            
        while(on!=0L){
			int sq = Long.numberOfTrailingZeros(on);
			score+=threatOffset(BitBoard.NMasks[sq]&enemy);
			on^=1L << sq;
		}
		while(ob!=0L){
			int sq = Long.numberOfTrailingZeros(ob);
			score+=threatOffset(BitBoard.bishopAttacks(sq, aOccupied)&enemy);
			ob^=1L << sq;
		}
		while(or!=0L){
			int sq = Long.numberOfTrailingZeros(or);
			score+=threatOffset(BitBoard.rookAttacks(sq, aOccupied)&enemy);
			or^=1L << sq;
		}
		while(oq!=0L){
			int sq = Long.numberOfTrailingZeros(oq);
			score+=threatOffset(BitBoard.bishopAttacks(sq, aOccupied)&enemy);
			score+=threatOffset(BitBoard.rookAttacks(sq, aOccupied)&enemy);
			oq^=1L << sq;
		}
		return score;
	}

	private int threatOffset(long atk) {
		int score=0;
		while(atk!=0L){
			int sq = Long.numberOfTrailingZeros(atk);
			int type = type(sq);
			if(type!=0){
				int val = MBase.psqt(sq, type)[1];
				score+=val+val*val/qV;
			}
			atk^=1L << sq;
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
        final int deltaScore = wMtrl - bMtrl;
        int pBonus1 = interpolate((deltaScore > 0) ? wMtrlPawns : bMtrlPawns, 0, -30 * deltaScore / 100, 6 * pV, 0);
        int pBonus2 = interpolate((deltaScore > 0) ? bMtrl : wMtrl, 0, 30 * deltaScore / 100, qV + 2 * rV + 2 * bV + 2 * nV, 0);
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
    	return 0;
    }
	private int kingSafety() {
        final int minM = rV + bV;
        final int m = (wMtrl - wMtrlPawns + bMtrl - bMtrlPawns) / 2;
        if (m <= minM)
            return 0;
        final int maxM = qV + 2 * rV + 2 * bV + 2 * nV;
        int score = kingSafetyKPPart();
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
        score += (bKingAttacks - wKingAttacks) * 4;
        final int kSafety = interpolate(m, minM, 0, maxM, score);
        return kSafety;
	}

    private final int kingSafetyKPPart() {
        // FIXME!!! Try non-linear king safety
        final long key = pawnhash ^ kinghash;
        int i=KingHashTable.get(key);
        if(i>=0)
        	return KingHashTable.getScore(KingHashTable.data[i]);
        int score = 0;
        {
            int safety = 0;
            int halfOpenFiles = 0;
            if (BitBoard.sq2y(wkingpos) < 2) {
                long shelter = 1L << BitBoard.sq2x(wkingpos);
                shelter |= ((shelter & IConst.MaskBToHFiles) >>> 1) |
                           ((shelter & IConst.MaskAToGFiles) << 1);
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
            score += kSafety;
        }
        {
            int safety = 0;
            int halfOpenFiles = 0;
            if (BitBoard.sq2y(bkingpos) >= 6) {
                long shelter = 1L << (56 + BitBoard.sq2x(bkingpos));
                shelter |= ((shelter & IConst.MaskBToHFiles) >>> 1) |
                           ((shelter & IConst.MaskAToGFiles) << 1);
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
            final int kSafety = (safety - 9) * 15 - halfOpenFiles;
            score -= kSafety;
        }
        KingHashTable.set(key,score);
        return score;
    }

    int passedBonusW,passedBonusB;
    long passedPawnsW,passedPawnsB;
    
    private int pawnBonus() {
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
        int score = PawnHashTable.getScore(data);

        final int hiMtrl = qV + rV;
        score += interpolate(bMtrl - bMtrlPawns, 0, 2 * passedBonusW, hiMtrl, passedBonusW);
        score -= interpolate(wMtrl - wMtrlPawns, 0, 2 * passedBonusB, hiMtrl, passedBonusB);

        // Passed pawns are more dangerous if enemy king is far away
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
                    score += interpolate(mtrlNoPawns, 0, kScore, hiMtrl, 0);
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
                    score -= interpolate(mtrlNoPawns, 0, kScore, hiMtrl, 0);
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
                    score += 500;
                } else if (wPly == bPly - 1) {
                    if (BitBoard.getDirection(bestWPromSq, bkingpos) != 0)
                        score += 500;
                } else if (wPly == bPly + 1) {
                    if (BitBoard.getDirection(bestBPromSq, bkingpos) != 0)
                        score -= 500;
                } else {
                    score -= 500;
                }
            } else
                score += 500;
        } else if (bestBPromSq >= 0)
            score -= 500;

        return score;
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
        long wPawnAttacks = (((wPawns & IConst.MaskBToHFiles) << 7) |
                             ((wPawns & IConst.MaskAToGFiles) << 9));
        long bPawnAttacks = (((bPawns & IConst.MaskBToHFiles) >>> 9) |
                             ((bPawns & IConst.MaskAToGFiles) >>> 7));
        long wBackward = wPawns & ~((wPawns | bPawns) >>> 8) & (bPawnAttacks >>> 8) &
                         ~BitBoard.northFill(wPawnAttacks);
        wBackward &= (((wPawns & IConst.MaskBToHFiles) >>> 9) |
                      ((wPawns & IConst.MaskAToGFiles) >>> 7));
        wBackward &= ~BitBoard.northFill(bPawnFiles);
        long bBackward = bPawns & ~((wPawns | bPawns) << 8) & (wPawnAttacks << 8) &
                         ~BitBoard.southFill(bPawnAttacks);
        bBackward &= (((bPawns & IConst.MaskBToHFiles) << 7) |
                      ((bPawns & IConst.MaskAToGFiles) << 9));
        bBackward &= ~BitBoard.northFill(wPawnFiles);
        score -= (Long.bitCount(wBackward) - Long.bitCount(bBackward)) * 15;

        // Evaluate passed pawn bonus, white
        passedPawnsW = wPawns & ~BitBoard.southFill(bPawns | bPawnAttacks | (wPawns >>> 8));
        final int[] ppBonus = {-1,24,26,30,36,55,100,-1};
        passedBonusW = 0;
        if (passedPawnsW != 0) {
            long guardedPassedW = passedPawnsW & (((wPawns & IConst.MaskBToHFiles) << 7) |
                                                  ((wPawns & IConst.MaskAToGFiles) << 9));
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
        passedPawnsB = bPawns & ~BitBoard.northFill(wPawns | wPawnAttacks | (bPawns << 8));
        passedBonusB = 0;
        if (passedPawnsB != 0) {
            long guardedPassedB = passedPawnsB & (((bPawns & IConst.MaskBToHFiles) >>> 9) |
                                                  ((bPawns & IConst.MaskAToGFiles) >>> 7));
            passedBonusB += 15 * Long.bitCount(guardedPassedB);
            long m = passedPawnsB;
            while (m != 0) {
                int sq = Long .numberOfTrailingZeros(m);
                int y = BitBoard.sq2y(sq);
                passedBonusB += ppBonus[7-y];
                m &= m-1;
            }
        }
        return PawnHashTable.set(score,pawnhash,passedBonusW,passedBonusB,passedPawnsW,passedPawnsB);
	}

	private int endGameEval(int score) {
		return 0;
	}

}
