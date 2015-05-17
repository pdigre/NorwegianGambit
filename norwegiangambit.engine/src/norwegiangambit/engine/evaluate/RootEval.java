package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.util.IDivide.Eval;

public class RootEval extends Eval {

	public RootEval(String move, int count, int value) {
		super(move, count, value);
	}
	public int[] path;

	@Override
	public String toString() {
		return pv2string(path);
	}

	public static String pv2string(int[] m) {
		if(m==null)
			return "null";
		StringBuilder sb=new StringBuilder();
		for (int i : m) {
			MOVEDATA md=MOVEDATA.ALL[i];
			if(sb.length()!=0)
				sb.append(" ");
			sb.append(md.id());
		}
		return sb.toString();
	}



	
	

}
