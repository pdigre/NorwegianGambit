package norwegiangambit.engine.base;

import norwegiangambit.engine.fen.FEN;

/**
 * Approx 50% calculate pinners
 * Approx 50% cache hits
 * 
 *
 */
public class MovegenCache extends Movegen{
	long lineEnms,diagEnms,lineDefs,diagDefs,lineAtks,diagAtks,lineKey,diagKey;
	int lineCnt,diagCnt;
	MovegenCache compare;
	public static long count=0;
	public static long hits=0;
	public static long misses=0;

	public void setCompare(MovegenCache gen) {
		compare=gen;
	}

	@Override
	public void generate() {
		clear();
		// Calculate checkers and pinners
		isWhite = pos.whiteNext();
		final long own = isWhite?bb_white:bb_black;
		final long enemy = isWhite?bb_black:bb_white;
		final int king=isWhite?wking:bking;
		SQATK rev = BASE.REV[king];
		pinned=0L;
		checkers=~bb_bit3 & enemy & ((~bb_bit1 & bb_bit2 & rev.RN) | (bb_bit1 & ~bb_bit2 & (isWhite?MBP.REV[king]:MWP.REV[king])));
		long eslider=bb_bit3 & enemy  & rev.RQ; // Sliders
		count++;
		if(checkers==0L && eslider !=0L){
			boolean hasCompare = isCompare && compare!=null && compare.checkers==0L;
			long atks = rev.RB;
			long diagatks = bb_bit1 & eslider & atks;
			if (diagatks != 0L){
				boolean isDiagReusable=false;
				if(isCompare){
					diagAtks=atks;
					diagDefs=own & atks;
					diagEnms=enemy & atks;
					diagKey = atks|(diagDefs)>>1|(diagEnms)>>2|(bb_bit2 & atks)>>3;
					if(hasCompare){
						isDiagReusable = diagEnms==compare.diagEnms && diagDefs==compare.diagDefs && diagAtks==compare.diagAtks && diagKey==compare.diagKey;
						if(isDiagReusable)
							hits++;
						else
							misses++;
					}
				}
				if(isDiagReusable){
					int diagCnt2=compare.diagCnt;
					for (int i = 0; i < diagCnt2; i++)
						moves[iAll++]=compare.moves[i];
					pinned=compare.pinned&atks;
				} else {
					diagPinners(own, king, diagatks);
				}
				diagCnt=iAll;
				if(isDiagReusable){
					int diagCnt2=compare.diagCnt;
					if(diagCnt!=diagCnt2){
						System.out.println("Diag wrong count "+diagCnt +"/"+diagCnt2);
						String t1 = FEN.addHorizontal(toString(),FEN.addHorizontal(FEN.board2string(diagEnms), FEN.board2string(diagDefs)));
						String t2 = FEN.addHorizontal(compare.toString(),FEN.addHorizontal(FEN.board2string(compare.diagEnms), FEN.board2string(compare.diagDefs)));
						System.out.println(FEN.addHorizontal(t1,t2));
						for (int i = 0; i < diagCnt; i++) {
							MOVEDATA md = moves[i];
							System.out.println("New "+md);
						}
						for (int i = 0; i < diagCnt2; i++) {
							MOVEDATA md = compare.moves[i];
							System.out.println("Old "+md);
						}
					} else {
						for (int i = 0; i < diagCnt2; i++) {
							MOVEDATA a = moves[i];
							MOVEDATA b = compare.moves[i];
							if(a!=b)
								System.out.println("Diag wrong move "+a);
						}
					}
				} 
				diagCnt=iAll;
			}
			long line_atks = rev.RR;
			long lineatks = bb_bit2 & eslider & line_atks;
			if (checkers==0L && lineatks != 0L) {
				boolean isLineReusable = false;
				if(isCompare){
					lineAtks=atks;
					lineEnms=enemy & line_atks;
					lineDefs=own & line_atks;
					lineKey = atks|(lineDefs)>>9|(lineEnms)>>18|(bb_bit1 & line_atks)>>27;
					if(hasCompare){
						isLineReusable = hasCompare && lineEnms==compare.lineEnms && lineDefs==compare.lineDefs && lineAtks==compare.lineAtks && lineKey==compare.lineKey;
						if(isLineReusable)
							hits++;
						else
							misses++;
					}
				}
				if(isLineReusable){
					int lineCnt2=compare.lineCnt;
					int diagCnt2 = compare.diagCnt;
					for (int i = 0; i < lineCnt2-diagCnt2; i++)
						moves[iAll++]=compare.moves[diagCnt2+i];
					pinned|=compare.pinned&line_atks;
				} else {
					linePinners(own, king, lineatks);
				}
				lineCnt=iAll;
				if(isLineReusable){
					int diagCnt2 = compare.diagCnt;
					long lineDefs2=compare.lineDefs;
					long lineAtks2 = compare.lineEnms;
					int lineCnt2=compare.lineCnt;
					if(lineCnt-diagCnt!=lineCnt2-diagCnt2){
						System.out.println("Line wrong count "+(lineCnt-diagCnt)+"/"+(lineCnt2-diagCnt2)+" "+this);
						for (int i = diagCnt; i < lineCnt; i++) {
							MOVEDATA md = moves[i];
							System.out.println("New "+md);
						}
						for (int i = diagCnt2; i < lineCnt2; i++) {
							MOVEDATA md = compare.moves[i];
							System.out.println("Old "+md);
						}
					} else {
						for (int i = 0; i < lineCnt2-diagCnt2; i++)
							if(moves[diagCnt+i]!=compare.moves[diagCnt2+i])
								System.out.println("Line wrong move "+moves[i]);
					}
					lineCnt=iAll;
				} 
			}
		}
		if(checkers==0L){
			nonevasive(isWhite, king,own & ~pinned);
		} else {
			clear(); // not interested in pinned moves for evasive moves
			evasive(isWhite,king,own);
		}
	}
	

	public static String printStats() {
		long tot = hits+misses+1;
		count++;
		return hits +"/" +tot + "="+100*hits/tot+"% count="+(50*tot/count)+"%";
	}
}
