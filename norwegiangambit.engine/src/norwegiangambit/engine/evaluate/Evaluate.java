package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.base.Movegen;
import norwegiangambit.util.FEN;

public class Evaluate extends Movegen implements IIterate {

	IIterate parent,child;

	private int score;
	
	@Override
	public int score(){
		return isWhite?score:-score;
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
	
	public void setPos(int i) {
		child.setPos(isWhite, bitmap, wking, bking, bb_black, bb_bit1, bb_bit2, bb_bit3);
		((Movegen)child).make(moves[i]);
	}

	@Override
	public String toString() {
		String string = super.toString();
		String string2 = parent==null?string:FEN.addHorizontal(string, parent.toString());
		String string3 = FEN.board2string(pinners);
		String string4 = FEN.board2string(checkers);
		return FEN.addHorizontal(FEN.addHorizontal(string2, string3), string4);
	}

}
