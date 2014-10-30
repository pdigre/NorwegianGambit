package norwegiangambit.engine.movegen;

import norwegiangambit.util.polyglot.ZobristKey;


/**
 * Extra movedata for when special zobrist enpassant lane is required
 *
 */
public class MOVEDATA2 extends MOVEDATA {

	final public int epsq;
	final public long zobrist_ep;
	
	protected MOVEDATA2(long bits,int epsq) {
		super(bits);
		this.epsq=epsq;
		this.zobrist_ep=ZobristKey.ZOBRIST_ENP[epsq&7];
	}

	public static int create(long bitmap,int epsq){
		return MBase.add(new MOVEDATA2(bitmap,epsq));
	}

}
