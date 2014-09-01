package norwegiangambit.engine.fen;


public class PositionWithLog extends Position {

    final private Position parent;

    public PositionWithLog(final Position parent, final long bitmap) {
    	super();
        this.parent = parent;
    }

    public PositionWithLog() {
    	super();
    	parent=null;
	}

	@Override
    public int totalMoves() {
        int i = parent.totalMoves();
        if ((getBitmap() & BLACK) != 0)
            i++;
        return i;
    }

    @Override
    public String toString() {
        return FEN_POS.printMove(this)+"\n"+norwegiangambit.util.FEN.board2string(this.bb_bit1, this.bb_bit2, this.bb_bit3, this.bb_black);
    }
}
