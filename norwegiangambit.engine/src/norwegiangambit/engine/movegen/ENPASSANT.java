package norwegiangambit.engine.movegen;

import norwegiangambit.util.polyglot.IZobristKey;

/**
 * Extra movedata for when special zobrist enpassant file is required
 */
public class ENPASSANT extends MOVEDATA {

	final public int epsq;
	final public long zobrist_ep;
	
	protected ENPASSANT(long bits,int epsq) {
		super(bits);
		this.epsq=epsq;
		this.zobrist_ep=MBase.getZobrist(IZobristKey.ZOBRIST_ENP+(epsq&7));
	}

	public static int create(long bitmap,int epsq){
		return MOVEDATA.add(new ENPASSANT(bitmap,epsq));
	}

}
