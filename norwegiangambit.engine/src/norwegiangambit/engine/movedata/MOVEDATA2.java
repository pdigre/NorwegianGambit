package norwegiangambit.engine.movedata;

import norwegiangambit.util.polyglot.IZobristKey;

/**
 * Extra movedata for when special zobrist enpassant file is required
 */
public class MOVEDATA2 extends MOVEDATA {

	final public long zobrist_ep;
	
	protected MOVEDATA2(long bits,int epsq) {
		super(bits);
		this.zobrist_ep=MBase.getZobrist(IZobristKey.ZOBRIST_ENP+(epsq&7));
	}

	public static int create(long bitmap,int epsq){
		return MOVEDATA.add2(new MOVEDATA2(bitmap,epsq),epsq%8);
	}
}
