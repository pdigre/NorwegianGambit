package norwegiangambit.engine.movegen;


public class MOVEDATA2 extends MOVEDATA {

	final public int epsq;
	
	protected MOVEDATA2(long bits,int epsq) {
		super(bits);
		this.epsq=epsq;
	}

	public static int create(long bitmap,int epsq){
		return BASE.add(new MOVEDATA2(bitmap,epsq));
	}

}
