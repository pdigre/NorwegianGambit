package norwegiangambit.engine.movegen;

public abstract class MSimple extends MBase {

	public int B,E;

	public MSimple(int from) {
		super(from);
		B=MOVEDATA.nrm_cnt;
		E=B;
	}

	protected void addBreakers() {
		if(q!=null)
			Q=add3(q);
		else 
			MOVEDATA.brk_cnt++;		
		if(k!=null)
			K=add3(k);
	}

	public static int add3(MOVEDATA md) {
		MOVEDATA.ALL[MOVEDATA.brk_cnt++] = md;
		return MOVEDATA.brk_cnt-1;
	}

	protected void addBreakers2() {
		if(q!=null)
			Q=addBreaker(E,q);
		if(k!=null)
			K=addBreaker(E+1,k);
	}

	public int addBreaker(int offset,MOVEDATAX md) {
		int j = offset;
		MOVEDATA.ALL[j] = md;
		MOVEDATA.brk_cnt=j+1;
		return j;
	}


}
