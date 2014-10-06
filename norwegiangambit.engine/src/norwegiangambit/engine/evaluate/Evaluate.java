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

	Evaluate parent,deeper;

	protected int midscore;
	protected int endscore;
	protected int pawnscore;
	protected long zobrist;
	protected long zobrist_fwd;
	protected long pawnhash;
	public int ply, depth, best_move, curr_move;
	
	
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
		((Movegen)deeper).make(md,isWhite, castling, wking, bking, bb_black, bb_bit1, bb_bit2, bb_bit3);
	}

	@Override
	public String toString() {
		String string = super.toString();
		String string2 = parent==null?string:FEN.addHorizontal(string, parent.toString());
		String string3 = FEN.board2string(pinners);
		String string4 = FEN.board2string(checkers);
		String addHorizontal = FEN.addHorizontal(FEN.addHorizontal(string2, string3), string4);
		return addHorizontal+"\n"+ getFen() ;
	}

	public void evaluate(int i) {
		MOVEDATA md = BASE.ALL[i];
		midscore=parent.midScore()+md.mscore;
		endscore=parent.endScore()+md.escore;
		if(parent instanceof Evaluate){
			Evaluate eval=(Evaluate) parent;
			zobrist_fwd=eval.zobrist_fwd^md.zobrist;
			if(md instanceof MOVEDATA2){
				final long ep=1L<<epsq;
				final long epl = ep&IConst.LEFTMASK;
				final long epr = ep&IConst.RIGHTMASK;
				final long epb = isWhite?(epl>>7) | (epr>>9):(epr<<7) | (epl<<9);
				final long cmask = isWhite ? ~bb_black:bb_black;
				if((epb & cmask & bb_bit1&~bb_bit2&~bb_bit3) !=0L)
					zobrist=zobrist_fwd^((MOVEDATA2)md).zobrist_ep;
			} else 
				zobrist=zobrist_fwd;
//			pawnhash^=md.pawnhash;
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

}
