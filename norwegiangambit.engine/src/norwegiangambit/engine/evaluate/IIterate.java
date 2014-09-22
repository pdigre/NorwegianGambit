package norwegiangambit.engine.evaluate;

public interface IIterate {

	public abstract void setChild(IIterate child);

	public abstract void setParent(IIterate parent);

	public abstract int score();

	public abstract int alphabeta(int alpha, int beta);

	public abstract void setPos(boolean isWhite, long bitmap, int wking, int bking, long bb_black, long bb_bit1, long bb_bit2, long bb_bit3);

}
