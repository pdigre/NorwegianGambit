package norwegiangambit.engine.movegen;

import norwegiangambit.util.polyglot.ZobristKey;


public class MOVEDATA2 extends MOVEDATA {

	final public int epsq;
	final public long zobrist_ep;
	
	protected MOVEDATA2(long bits,int epsq) {
		super(bits);
		this.epsq=epsq;
		this.zobrist_ep=(epsq&7)==0?0L:ZobristKey.ZOBRIST_ENP[epsq&7];
	}

	public static int create(long bitmap,int epsq){
		return BASE.add(new MOVEDATA2(bitmap,epsq));
	}

}