package norwegiangambit.engine.movegen;

public abstract class MSimple extends MBase {

	public int B,n;

	public MSimple(int from) {
		super(from);
		n=MOVEDATA.nrm_cnt;
		B=n;
	}

	protected void addBreakers() {
		MOVEDATA.nrm_cnt+=2;
		Q=n;
		MOVEDATA.ALL[n] = q;
		K=n+1;
		MOVEDATA.ALL[n+1] = k;
	}
}
