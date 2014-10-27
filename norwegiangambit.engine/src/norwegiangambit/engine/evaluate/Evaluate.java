package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.engine.movegen.MOVEDATA2;
import norwegiangambit.engine.movegen.Movegen;
import norwegiangambit.util.BitBoard;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.PSQT_SEF;
import norwegiangambit.util.polyglot.ZobristKey;

public class Evaluate extends Movegen {

	final public static int MATE = -32000; 
	final public static int STALE = 0; 
	
	Evaluate parent,deeper;

	protected int midscore;
	protected int endscore;
	protected int pawnscore;
	protected int score;
	protected long zobrist;
	protected long zobrist_fwd;
	public int ply, depth;
	private int best_move;
	public int curr_move;
	
	public void notifyPV(Evaluate next, int depth, boolean lowerBound, boolean upperBound, int score){
		parent.notifyPV(this, depth, lowerBound, upperBound, score);
	}
	
	public int midScore(){
		return midscore;
	}

	public int endScore(){
		return endscore;
	}

	public int score(){
		int score = whiteScore();
		return isWhite?score:-score;
	}

	public int whiteScore() {
		int popcnt=Long.bitCount(bb_piece);
		return ((popcnt)*midscore+(32-popcnt)*endscore)/32;
	}

	public void setParent(Evaluate parent){
		this.parent=parent;
	}
	
	public void setChild(Evaluate child){
		this.deeper=child;
	}
	
	public int search(int alpha, int beta){
		return score();
	}
	
	public void make(int md) {
		((Movegen)deeper).make(md,isWhite, castling, wking, bking, bb_bit1, bb_bit2, bb_bit3, bb_black);
	}

	public void evaluate(int i) {
		curr_move=i;
		MOVEDATA md = MBase.ALL[i];
		midscore=parent.midScore()+md.mscore;
		endscore=parent.endScore()+md.escore;
		if(parent instanceof Evaluate){
			zobrist_fwd=((Evaluate) parent).zobrist_fwd^md.zobrist;
			zobrist=zobrist_fwd;
			if(md instanceof MOVEDATA2){
				final long ep=1L<<epsq;
				final long epb = isWhite
						?((ep&IConst.MaskAToGFiles)>>7) | ((ep&IConst.MaskBToHFiles)>>9)
						:((ep&IConst.MaskBToHFiles)<<7) | ((ep&IConst.MaskAToGFiles)<<9);
				final long cmask = isWhite ? ~bb_black:bb_black;
				long enemy = cmask & bb_bit1&~bb_bit2&~bb_bit3;
				if((epb & enemy) !=0L)
					zobrist^=((MOVEDATA2)md).zobrist_ep;
			}
		}
	}

	public long getZobrist() {
		return zobrist;
	}

	public void evaluate() {
		int[] brd = FEN.boardFrom64(bb_bit1, bb_bit2, bb_bit3,bb_black);
		zobrist_fwd=ZobristKey.getKey(isWhite, castling, epsq, brd);
		zobrist=zobrist_fwd;
		int[] score = getScore(brd);
		midscore=score[0];
		endscore=score[1];
	}
	
	public static int[] getScore(int[] brd) {
		int[] score=new int[]{0,0};
		for (int sq = 0; sq < 64; sq++) {
			int piece = brd[sq];
			if(piece!=0){
				int[] pv = PSQT_SEF.psqt(sq, piece);
				score[0]+=pv[0];
				score[1]+=pv[1];
			}
		}
		return score;
	}

	public void setPath(int[] mm) {
		if(deeper!=null){
			mm[ply+1]=best_move;
			deeper.setPath(mm);
		}
	}

	public int getBest() {
		return best_move;
	}

	public void setBest(int best_move, int score) {
		this.best_move = best_move;
		this.score=score;
	}

	public String getHistory1(){
		String text=getFen()+"\n";
		if(parent instanceof Evaluate)
			return parent.getHistory1()+text;
		return text;
	}
	
	public String getHistory2(){
		String id = curr_move>0?MBase.ALL[curr_move].id():"??";
		String text=FEN.board2string(this.bb_bit1, this.bb_bit2, this.bb_bit3, this.bb_black) + "\n" 
				+(" << "+id+"              ").substring(0,10) + "\n";
		if(parent instanceof Evaluate)
			return FEN.addHorizontal(text,parent.getHistory2());
		return text;
	}
	
	@Override
	public String toString() {
		return getHistory1()+getHistory2();
	}
	
	
	
	/*
	 * Adjustments
	 * 
	 * 
	 */
    static final int[] RookMobScore = {-10,-7,-4,-1,2,5,7,9,11,12,13,14,14,14,14};
	static final int[] BishMobScore = {-15,-10,-6,-2,2,6,10,13,16,18,20,22,23,24};
    static final int[] QueenMobScore = {-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,9,10,10,10,10,10,10,10,10,10,10,10,10};
	
	
	
	public int calulateAdjustments(){
		int score=0;
        score += pawnBonus();
        score += tradeBonus();
//        score += castleBonus();
        score += rookBonus();
        score += bishopBonus();
        score += threatBonus();
        score += kingSafety();
        score = endGameEval(score);
		return score;
	}

	private int endGameEval(int score2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int kingSafety() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int threatBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

    // King safety variables
    private long wKingZone, bKingZone;       // Squares close to king that are worth attacking
    private int wKingAttacks, bKingAttacks; // Number of attacks close to white/black king
    private long wAttacksBB, bAttacksBB;
    private long wPawnAttacks, bPawnAttacks; // Squares attacked by white/black pawns

	private int bishopBonus() {
        int score = 0;
        final long occupied = bb_bit1|bb_bit2|bb_bit3;
        final long bb_white = occupied&~bb_black;
        long wBishops = bb_bit1&~bb_bit2&bb_bit3&~bb_black;
        long bBishops = bb_bit1&~bb_bit2&bb_bit3&bb_black;
        if ((wBishops | bBishops) == 0)
            return 0;
        long wPawns = bb_bit1&~bb_bit2&~bb_bit3&~bb_black;
        long bPawns = bb_bit1&~bb_bit2&~bb_bit3&bb_black;
        long wQueens = bb_bit1&bb_bit2&bb_bit3&~bb_black;
        long bQueens = bb_bit1&bb_bit2&bb_bit3&bb_black;
        long m = wBishops;
        while (m != 0) {
            int sq = Long.numberOfTrailingZeros(m);
            long atk = BitBoard.bishopAttacks(sq, occupied);
            wAttacksBB |= atk;
            score += BishMobScore[Long.bitCount(atk & ~(bb_white | bPawnAttacks))];
            if ((atk & bKingZone) != 0)
                bKingAttacks += Long.bitCount(atk & bKingZone);
            m &= m-1;
        }
        m = bBishops;
        while (m != 0) {
            int sq = Long.numberOfTrailingZeros(m);
            long atk = BitBoard.bishopAttacks(sq, occupied);
            bAttacksBB |= atk;
            score -= BishMobScore[Long.bitCount(atk & ~(bb_black | wPawnAttacks))];
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
                score -= (wQueens != 0) ? 100 : 150;
            if ((((1L<<8)& bBishops)!=0L) &&  // a2
                (((1L<<17)& wPawns)!=0L) &&
                (((1L<<10)& wPawns)!=0L))
                score += 150;
            if ((((1L<<15)& bBishops)!=0L) && // h2
                (((1L<<22)& wPawns)!=0L) &&
                (((1L<<13)& wPawns)!=0L))
                score += (bQueens != 0) ? 100 : 150;
        }
        return score;
	}

	private int rookBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int tradeBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int pawnBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
