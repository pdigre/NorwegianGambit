package norwegiangambit.engine.evaluate;

import static norwegiangambit.util.ShortIntPairs.*;

import java.util.Arrays;

import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.util.BitBoard;

public class LongEval extends FastEval {
	
    static final int pV = M(MBase.psqt.pVal(WP));
    static final int nV = M(MBase.psqt.pVal(WN));
    static final int bV = M(MBase.psqt.pVal(WB));
    static final int rV = M(MBase.psqt.pVal(WR));
    static final int qV = M(MBase.psqt.pVal(WQ));
    static final int kV = M(MBase.psqt.pVal(WK)); // Used by SEE algorithm, but not included in board material sums
    static final int mgBalance = 15581;
    static final int egBalance = 3998;
    public static final int WM=0,BM=8;	// Used for Mobility bonus
    public static final int WX=4,BX=12;	// Used for Threat bonus
    public static final int WS=16,BS=17;	// Used for Space bonus

    final static int[] KnightMobScore = {S(-65,-50), S(-42,-30), S(-9,-10), S( 3,  0), S(15, 10), S(27, 20), // Knight
		  S( 37, 28), S( 42, 31), S(44, 33)};
	final static int[] BishMobScore = {S(-52,-47), S(-28,-23), S( 6,  1), S(20, 15), S(34, 29), S(48, 43), // Bishop
		  S( 60, 55), S( 68, 63), S(74, 68), S(77, 72), S(80, 75), S(82, 77), S( 84, 79), S( 86, 81)};
	final static int[] RookMobScore = {S(-47,-53), S(-31,-26), S(-5,  0), S( 1, 16), S( 7, 32), S(13, 48), // Rooks
	      S( 18, 64), S( 22, 80), S(26, 96), S(29,109), S(31,115), S(33,119),
	      S( 35,122), S( 36,123), S(37,124)};
    final static int[] QueenMobScore = {S(-42,-40), S(-28,-23), S(-5, -7), S( 0,  0), S( 6, 10), S(11, 19), // Queens
	      S( 13, 29), S( 18, 38), S(20, 40), S(21, 41), S(22, 41), S(22, 41),
	      S( 22, 41), S( 23, 41), S(24, 41), S(25, 41), S(25, 41), S(25, 41),
	      S( 25, 41), S( 25, 41), S(25, 41), S(25, 41), S(25, 41), S(25, 41),
	      S( 25, 41), S( 25, 41), S(25, 41), S(25, 41)};

    final static int[] PawnThreat  = {S(0, 0), S(87, 118), S(84, 122), S(114, 203), S(121, 217)}; // pnbrq

    final static int[] ProtectedMinorThreat = {S( 0, 0), S(19, 37), S(24, 37), S(44, 97), S(35,106)}; // pnbrq
    final static int[] ProtectedMajorThreat = {S( 0, 0), S( 9, 14), S( 9, 14), S( 7, 14), S(24, 48)}; 
    final static int[] WeakMinorThreat = {S( 0,32), S(33, 41), S(31, 50), S(41,100), S(35,104)}; // pnbrq
    final static int[] WeakMajorThreat = {S( 0,27), S(26, 57), S(26, 57), S(0 , 43), S(23, 51)}; 
    
    // Assorted Bonuses
    final static int KingOnOne        = SS(2 , 58);
    final static int KingOnMany       = SS(6 ,125);
    final static int RookOnPawn       = SS(7 , 27);
    final static int RookOpenFile     = SS(43, 21);
    final static int RookSemiOpenFile = SS(19, 10);
    final static int BishopPawns      = SS( 8, 12);
    final static int MinorBehindPawn  = SS(16,  0);
    final static int TrappedRook      = SS(92,  0);
    final static int Unstoppable      = SS( 0, 20);
    final static int Hanging     	  = SS(31, 26);

    int wMtrl,wMtrlPawns,bMtrl,bMtrlPawns;
	long wKnights,wBishops,wRooks,wQueens,wKings,bKnights,bBishops,bRooks,bQueens,bKings;
	long wMinorAtks,wMajorAtks,bMinorAtks,bMajorAtks;

    // King safety variables
    long wKingZone, bKingZone;      // Squares close to king that are worth attacking
    int wKingAtks, bKingAtks; 		// Number of attacks close to white/black king
    
    int[] bonus=new int[18];
	int materialBonus,imbalanceBonus, pawnBonus;

	StringBuilder sb;
	
	public final static String f(int in,int i){
		return f(String.valueOf(in),i);
	}
	
	public final static String f(String in,int i){
		String all="                             "+in;
		return all.substring(all.length()-i);
	}
	
	public final static String d(int i){
		String in=String.valueOf(i);
		int d=in.replace("-","").trim().length();
		if(d<3)
			in=in.substring(0,in.length()-d)+"000".substring(d)+in.substring(in.length()-d);
		String all="           "+in;
		all=all.substring(0,all.length()-2)+"."+all.substring(all.length()-2);
		return all.substring(all.length()-6);
	}
	
	public final static String line1(String lbl,int score) {
		return f(lbl,20)+" |   ---   --- |   ---   --- |"+dpair(score)+" \n";
	}

	public final static String line2(String lbl,int wnb, int bnb) {
		return f(lbl,20)+" |"+dpair(wnb)+" |"+dpair(bnb)+" |"+dpair(wnb-SS(bnb))+" \n";
	}

	public final static String dpair(int pair) {
		return d(M(pair)*10000/M(pV))+d(E(pair)*10000/E(pV));
	}

	/**
	 * Prints positional eval in Stockfish format 
	 * 
	 * 
	 * @return
	 */
	public String printEval() {
		int materialBonus=ZERO,imbalanceBonus=ZERO, pawnBonus=ZERO;
		sb=new StringBuilder();
		sb.append("           Eval term |    White    |    Black    |    Total    \n");
		sb.append("                     |   MG    EG  |   MG    EG  |   MG    EG  \n");
		sb.append("---------------------+-------------+-------------+-------------\n");
		int tot = longEval();
		sb.append(line1("Material, PST, Tempo",materialBonus));
		sb.append(line1("Material imbalance",imbalanceBonus));
		sb.append(line1("Pawns",pawnBonus));
		sb.append(line2("Knights",bonus[WN], bonus[BN]));
		sb.append(line2("Bishops",bonus[WN], bonus[BN]));
		sb.append(line2("Rooks",bonus[WN], bonus[BN]));
		sb.append(line2("Queens",bonus[WN], bonus[BN]));
    	sb.append(line2("Mobility",bonus[WM],bonus[BM]));
    	sb.append(line2("King safety",bonus[WK],bonus[BK]));
    	sb.append(line2("Threats",bonus[WX],bonus[BX]));
    	sb.append(line2("Passed pawns",bonus[WP],bonus[BP]));
    	sb.append(line2("Space",bonus[WS],bonus[BS]));
		sb.append("---------------------+-------------+-------------+-------------\n");
		sb.append(line1("Total",tot));
		sb.append("\nTotal Evaluation: "+d(score)+" (white side)\n");
		return sb.toString();
	}

	public int longEval() {
        initAdjustments();
        pawnBonus1();
        passedPawnAdditionalBonus();
        mob_atks();
        special();
        castleBonus();
        tradeBonus();
        threatBonus();
        kingSafety();
        threatBonus();
		int tot=ZERO;
        tot+=SS(bonus[WN])-SS(bonus[BN]);
        tot+=SS(bonus[WB])-SS(bonus[BB]);
        tot+=SS(bonus[WR])-SS(bonus[BR]);
        tot+=SS(bonus[WQ])-SS(bonus[BQ]);
        tot+=SS(bonus[WM])-SS(bonus[BM]);
        tot+=SS(bonus[WK])-SS(bonus[BK]);
        tot+=SS(bonus[WX])-SS(bonus[BX]);
        tot+=SS(bonus[WP])-SS(bonus[BP]);	
        tot+=SS(bonus[WS])-SS(bonus[BS]);
        endGameEval(tot);
        // Weight adjusted to game phase
        int popcnt=Long.bitCount(aOccupied);
		score = interpolate(popcnt, 2, E(tot),32, M(tot));
		return tot;
	}

	public void initAdjustments() {
		Arrays.fill(bonus, ZERO);
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
		wKingZone=BitBoard.KMasks[wKingpos];
		bKingZone=BitBoard.KMasks[bKingpos];
		
		// Bishop Pair Bonus
		bonus[WB]=bishopPairBonus(wBishopNum, wPawnNum);
		bonus[BB]=bishopPairBonus(bBishopNum, bPawnNum);
		
		// Rook on enemy pawn row bonus
		bonus[WR]=(wRookNum==2 && (wRooks &  ~0xff000000000000L)==0L && (bKings & ~0xff00000000000000L) == 0L ? 30:0);
		bonus[BR]=(bRookNum==2 && (bRooks &  ~0x0000000000ff00L)==0L && (wKings & ~0x00000000000000ffL) == 0L ? 30:0);
	}

	private final static int bishopPairBonus(int wBishopNum, int wPawnNum) {
		int v = wBishopNum==2?28 + (8 - wPawnNum) * 3:0;
		return S(v,v);
	}


	private void mob_atks() {
        {
	        long m = wBishops;
	        while (m != 0L) {
	            int sq = Long.numberOfTrailingZeros(m);
	            long atk = BitBoard.bishopAttacks(sq, aOccupied);
	            wMinorAtks |= atk;
	            bonus[WM] += SS(BishMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAtks))]);
	            if ((atk & bKingZone) != 0)
	                bKingAtks += Long.bitCount(atk & bKingZone);
	            m &= m-1;
	        }
        }
        {
			long m = wRooks;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
				bonus[WR]+=rookOpenFileBonus(sq,wPawns,bPawns);
	            long atk = BitBoard.rookAttacks(sq, aOccupied);
	            wMajorAtks |= atk;
	            bonus[WM] += SS(RookMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAtks))]);
	            if ((atk & bKingZone) != 0)
	                bKingAtks += Long.bitCount(atk & bKingZone);
	            m &= m-1;
	        }
		}
        {
			long m = wQueens;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
	            long atk = BitBoard.rookAttacks(sq, aOccupied) | BitBoard.bishopAttacks(sq, aOccupied);
	            wMajorAtks |= atk;
	            bonus[WM] += SS(QueenMobScore[Long.bitCount(atk & ~(wOccupied | bPawnAtks))]);
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
	            bonus[BM] += SS(BishMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAtks))]);
	            if ((atk & wKingZone) != 0)
	                wKingAtks += Long.bitCount(atk & wKingZone);
	            m &= m-1;
	        }
        }
        {
	        long m = bRooks;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
	            bonus[BR]+=rookOpenFileBonus(sq,bPawns,wPawns);
	            long atk = BitBoard.rookAttacks(sq, aOccupied);
	            bMajorAtks |= atk;
	            bonus[BM] += SS(RookMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAtks))]);
	            if ((atk & wKingZone) != 0)
	                wKingAtks += Long.bitCount(atk & wKingZone);
	            m &= m-1;
	        }
        }
        {
	        long m = bQueens;
	        while (m != 0) {
	            int sq = Long.numberOfTrailingZeros(m);
	            long atk = BitBoard.rookAttacks(sq, aOccupied) | BitBoard.bishopAttacks(sq, aOccupied);
	            bMajorAtks |= atk;
	            bonus[BM] += SS(QueenMobScore[Long.bitCount(atk & ~(bOccupied | wPawnAtks))]);
	            if ((atk & wKingZone) != 0)
	                wKingAtks += Long.bitCount(atk & wKingZone);
	            m &= m-1;
	        }
        }
	}

	private void special() {
        // Penalty for bishop trapped behind pawn at a2/h2/a7/h7
        if ((aBishops & 0x0081000000008100L) != 0) {
            if ((((1L<<48)& wBishops)!=0L) && // a7
                (((1L<<41)& bPawns)!=0L) &&
                (((1L<<50)& bPawns)!=0L))
                bonus[BB] += 150;
            if ((((1L<<55)& wBishops)!=0L) && // h7
                (((1L<<46)& bPawns)!=0L) &&
                (((1L<<53)& bPawns)!=0L))
            	bonus[BB] += (wQueens != 0) ? pV : pV * 3 / 2;
            if ((((1L<<8)& bBishops)!=0L) &&  // a2
                (((1L<<17)& wPawns)!=0L) &&
                (((1L<<10)& wPawns)!=0L))
            	bonus[WB] += 150;
            if ((((1L<<15)& bBishops)!=0L) && // h2
                (((1L<<22)& wPawns)!=0L) &&
                (((1L<<13)& wPawns)!=0L))
            	bonus[WB] += (bQueens != 0) ? pV : pV * 3 / 2;
        }
	}

	private int rookOpenFileBonus(int sq,long own, long other) {
        long mask = BitBoard.maskFile[BitBoard.sq2x(sq)];
        return (own & mask) == 0L?(other & mask) == 0L ? RookOpenFile : RookSemiOpenFile:0;
	}

	private void threatBonus() {
        bonus[WX] = threatBonus(bOccupied, wOccupied & ~aKings);
        bonus[BX] = threatBonus(wOccupied, bOccupied & ~aKings);
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
		return s;
	}

	private int threatOffset(long m) {
		int score=0;
		while(m!=0L){
			int sq = Long.numberOfTrailingZeros(m);
			int type = type(sq);
			if(type!=0){
				int val = M(MBase.psqt(sq, type));
				score+=val+val*val/qV;
			}
            m &= m-1;
		}
		return score;
	}


    /** Implement the "when ahead trade pieces, when behind trade pawns" rule. */
	private int tradeBonus() {
        final int deltaScore = (wMtrl - bMtrl) * 30 / 100;
        int pBonus1 = interpolate((deltaScore > 0) ? wMtrlPawns : bMtrlPawns, 0, -deltaScore, 6 * pV, 0);
        int pBonus2 = interpolate((deltaScore > 0) ? bMtrl : wMtrl, 0, deltaScore, qV + 2 * rV + 2 * bV + 2 * nV, 0);
		return pBonus1+pBonus2;
	}

    private final static int[] castleFactor;
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
	private void kingSafety() {
        final int minM = rV + bV;
        final int m = (wMtrl - wMtrlPawns + bMtrl - bMtrlPawns) / 2;
        if (m <= minM)
            return;
        final int maxM = qV + 2 * rV + 2 * bV + 2 * nV;
        int score = kingSafetyKPPart();
        
        if (BitBoard.sq2y(wKingpos) == 0) {
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
        
        if (BitBoard.sq2y(bKingpos) == 7) {
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
		if (BitBoard.sq2y(bKingpos) >= 6) {
		    long shelter = 1L << (56 + BitBoard.sq2x(bKingpos));
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
		if (BitBoard.sq2y(wKingpos) < 2) {
		    long shelter = 1L << BitBoard.sq2x(wKingpos);
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
    
    private void pawnBonus1() {
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
        pawnBonus=S(s,s);
    }
    
    private void passedPawnAdditionalBonus() {
        final int hiMtrl = qV + rV;
        // Passed pawns are more dangerous if enemy king is far away
        int wscore = interpolate(bMtrl - bMtrlPawns, 0, 2 * passedBonusW, hiMtrl, passedBonusW);
        int bestWPawnDist = 8;
        int bestWPromSq = -1;
        long m = passedPawnsW;
        if (m != 0) {
            int mtrlNoPawns = bMtrl - bMtrlPawns;
            if (mtrlNoPawns < hiMtrl) {
                int kingPos = wKingpos;
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
                        if ((BitBoard.northFill(1L<<sq) & (1L << wKingpos)) != 0)
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
                int kingPos = wKingpos;
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
                        if ((BitBoard.southFill(1L<<sq) & (1L << bKingpos)) != 0)
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
                    if (BitBoard.getDirection(bestWPromSq, bKingpos) != 0)
                        wscore += 500;
                } else if (wPly == bPly + 1) {
                    if (BitBoard.getDirection(bestBPromSq, bKingpos) != 0)
                        bscore += 500;
                } else {
                    bscore += 500;
                }
            } else
                wscore += 500;
        } else if (bestBPromSq >= 0)
            bscore += 500;
        bonus[WP]=S(wscore,wscore);
        bonus[BP]=S(bscore,bscore);
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
		return 0;
	}

}
