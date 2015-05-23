package norwegiangambit.engine.movegen;

public abstract class MSimple extends MBase {

	public int B,E;

	public MSimple(int from) {
		super(from);
		B=MOVEDATA.nrm_cnt;
		E=B;
	}

	protected void addBreakers() {
		MOVEDATA.nrm_cnt+=2;
		Q=E;
		MOVEDATA.ALL[E] = q;
		K=E+1;
		MOVEDATA.ALL[E+1] = k;
	}
}
