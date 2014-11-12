package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.engine.movegen.MOVEDATA2;
import norwegiangambit.engine.movegen.Movegen;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;

public class FastEval extends Movegen {

	final public static int MATE = -32000; 
	final public static int STALE = 0; 
    static final int pV = MBase.psqt.pVal(WP);
    static final int nV = MBase.psqt.pVal(WN);
    static final int bV = MBase.psqt.pVal(WB);
    static final int rV = MBase.psqt.pVal(WR);
    static final int qV = MBase.psqt.pVal(WQ);
    static final int kV = MBase.psqt.pVal(WK); // Used by SEE algorithm, but not included in board material sums
	
	FastEval parent,deeper;

	protected int midscore;
	protected int endscore;
	protected int pawnscore;
	protected int score;
	protected long zobrist;
	protected long pawnhash;
	protected long kinghash;
	protected long zobrist_fwd;
	public int ply, depth;
	private int best_move;
	public int curr_move;
	
	public void notifyPV(FastEval next, int depth, boolean lowerBound, boolean upperBound, int score){
		parent.notifyPV(this, depth, lowerBound, upperBound, score);
	}
	
	public int midScore(){
		return midscore;
	}

	public int endScore(){
		return endscore;
	}

	public int score(){
		return wNext?score:-score;
	}

	public void fasteval(){
		int popcnt=Long.bitCount(aOccupied);
		score = ((popcnt)*midscore+(32-popcnt)*endscore)/32;
	}
	
	public int whiteScore() {
		return score;
	}

	public void setParent(FastEval parent){
		this.parent=parent;
	}
	
	public void setChild(FastEval child){
		this.deeper=child;
	}
	
	public int search(int alpha, int beta){
		return score();
	}
	
	public void make(int md) {
		((Movegen)deeper).make(md,wNext, castling, wkingpos, bkingpos, aMinor, aMajor, aSlider, bOccupied);
	}

	public void evaluate(int i) {
		curr_move=i;
		MOVEDATA md = MBase.ALL[i];
		midscore=parent.midScore()+md.mscore;
		endscore=parent.endScore()+md.escore;
		if(parent instanceof FastEval){
			zobrist_fwd=((FastEval) parent).zobrist_fwd^md.zobrist;
			zobrist=zobrist_fwd;
			if(md instanceof MOVEDATA2){
				final long ep=1L<<epsq;
				final long epb = wNext
						?((ep&IConst.MaskAToGFiles)>>7) | ((ep&IConst.MaskBToHFiles)>>9)
						:((ep&IConst.MaskBToHFiles)<<7) | ((ep&IConst.MaskAToGFiles)<<9);
				final long cmask = wNext ? ~bOccupied:bOccupied;
				long enemy = cmask & aMinor&~aMajor&~aSlider;
				if((epb & enemy) !=0L)
					zobrist^=((MOVEDATA2)md).zobrist_ep;
			}
		}
		fasteval();
	}

	public long getZobrist() {
		return zobrist;
	}

	public void evaluate() {
		int[] brd = FEN.boardFrom64(aMinor, aMajor, aSlider,bOccupied);
		zobrist_fwd=MBase.zobrist.getKey(wNext, castling, epsq, brd);
		zobrist=zobrist_fwd;
		int[] score = getScore(brd);
		midscore=score[0];
		endscore=score[1];
		fasteval();
	}
	
	public static int[] getScore(int[] brd) {
		int[] score=new int[]{0,0};
		for (int sq = 0; sq < 64; sq++) {
			int piece = brd[sq];
			if(piece!=0){
				int[] pv = MBase.psqt(sq, piece);
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
		if(parent instanceof FastEval)
			return parent.getHistory1()+text;
		return text;
	}
	
	public String getHistory2(){
		String id = curr_move>0?MBase.ALL[curr_move].id():"??";
		String text=FEN.board2string(this.aMinor, this.aMajor, this.aSlider, this.bOccupied) + "\n" 
				+(" << "+id+"              ").substring(0,10) + "\n";
		if(parent instanceof FastEval)
			return FEN.addHorizontal(text,parent.getHistory2());
		return text;
	}
	
	@Override
	public String toString() {
		return getHistory1()+getHistory2();
	}
	
}