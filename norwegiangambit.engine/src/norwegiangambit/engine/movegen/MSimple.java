package norwegiangambit.engine.movegen;

public abstract class MSimple extends MBase {

	public int B,E;

	public MSimple(int from) {
		super(from);
		B=MOVEDATA.nrm_cnt;
		E=B;
	}

}
