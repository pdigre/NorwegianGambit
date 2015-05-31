package norwegiangambit.engine.movedata;

public abstract class MSimple extends MBase {

	public int n;

	public MSimple(int from) {
		super(from);
		n=MOVEDATA.nrm_cnt;
	}

	protected void addBreakers() {
		MOVEDATA.nrm_cnt+=2;
		MOVEDATA.ALL[n] = q;
		MOVEDATA.ALL[n+1] = k;
	}
}
