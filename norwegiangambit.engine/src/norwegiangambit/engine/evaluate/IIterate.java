package norwegiangambit.engine.evaluate;

public interface IIterate {

	void setChild(IIterate child);

	void setParent(IIterate parent);

	int score();

	int alphabeta(int alpha, int beta);

	void set(boolean isWhite, long bitmap, int wking, int bking, long bb_black, long bb_bit1, long bb_bit2, long bb_bit3);

	int midScore();

	int endScore();

}
