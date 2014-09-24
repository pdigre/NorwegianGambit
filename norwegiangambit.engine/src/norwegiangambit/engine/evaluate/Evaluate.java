package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.base.MOVEDATA;
import norwegiangambit.engine.base.Movegen;
import norwegiangambit.util.FEN;

public class Evaluate extends Movegen implements IIterate {

	IIterate parent,child;

	protected int midscore;
	protected int endscore;
	
	@Override
	public int midScore(){
		return midscore;
	}

	@Override
	public int endScore(){
		return endscore;
	}

	@Override
	public int score(){
		int score = whiteScore();
		return isWhite?score:-score;
	}

	public int whiteScore() {
		int popcnt=Long.bitCount(bb_piece);
		return ((popcnt)*midscore+(32-popcnt)*endscore)/32;
	}

	@Override
	public void setParent(IIterate parent){
		this.parent=parent;
	}
	
	@Override
	public void setChild(IIterate child){
		this.child=child;
	}
	
	public int alphabeta(int alpha, int beta){
		return score();
	}
	
	public void make(MOVEDATA md) {
		child.set(isWhite, bitmap, wking, bking, bb_black, bb_bit1, bb_bit2, bb_bit3);
		((Movegen)child).set(md);
	}

	@Override
	public String toString() {
		String string = super.toString();
		String string2 = parent==null?string:FEN.addHorizontal(string, parent.toString());
		String string3 = FEN.board2string(pinners);
		String string4 = FEN.board2string(checkers);
		return FEN.addHorizontal(FEN.addHorizontal(string2, string3), string4);
	}

	public void evaluate(MOVEDATA md) {
		midscore=parent.midScore()+md.mscore;
		endscore=parent.endScore()+md.escore;
	}

}
