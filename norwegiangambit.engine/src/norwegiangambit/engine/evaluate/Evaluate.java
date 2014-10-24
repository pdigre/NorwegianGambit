package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.movegen.BASE;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.engine.movegen.MOVEDATA2;
import norwegiangambit.engine.movegen.Movegen;
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
	protected long pawnhash;
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
	
	public int alphabeta(int alpha, int beta){
		return score();
	}
	
	public void make(int md) {
		((Movegen)deeper).make(md,isWhite, castling, wking, bking, bb_bit1, bb_bit2, bb_bit3, bb_black);
	}

	public String getHistory1(){
		String text=getFen()+"\n";
		if(parent instanceof Evaluate)
			return parent.getHistory1()+text;
		return text;
	}
	
	public String getHistory2(){
		String id = curr_move>0?BASE.ALL[curr_move].id():"??";
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

	public void evaluate(int i) {
		curr_move=i;
		MOVEDATA md = BASE.ALL[i];
		midscore=parent.midScore()+md.mscore;
		endscore=parent.endScore()+md.escore;
		if(parent instanceof Evaluate){
			zobrist_fwd=((Evaluate) parent).zobrist_fwd^md.zobrist;
			zobrist=zobrist_fwd;
			if(md instanceof MOVEDATA2){
				final long ep=1L<<epsq;
				final long epb = isWhite
						?((ep&IConst.RIGHTMASK)>>7) | ((ep&IConst.LEFTMASK)>>9)
						:((ep&IConst.LEFTMASK)<<7) | ((ep&IConst.RIGHTMASK)<<9);
				final long cmask = isWhite ? ~bb_black:bb_black;
				long enemy = cmask & bb_bit1&~bb_bit2&~bb_bit3;
//				String b1=FEN.board2string(enemy);
//				String b2=FEN.board2string(epb);
//				if(epsq==40 && (epb & enemy) !=0L)
//					System.out.println("here");
				if((epb & enemy) !=0L)
					zobrist^=((MOVEDATA2)md).zobrist_ep;
			}
//			pawnhash^=md.pawnhash;
//			long z1=getZobrist();
//			long z2=ZobristKey.getKey(isWhite, castling, epsq, FEN.boardFrom64(bb_bit1,bb_bit2,bb_bit3, bb_black));
//			if(z1!=z2){
//				System.out.print("Zobrist:"+md.id()+" "+(zobrist!=zobrist_fwd?epsq:"") + getFen());
//				System.out.println("");
//			}
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
}
