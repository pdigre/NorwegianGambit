package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.movegen.BASE;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.util.IDivide.Eval;

public class RootEval extends Eval {

	public RootEval(String move, int count, int value) {
		super(move, count, value);
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (int i : bestPV) {
			MOVEDATA md=BASE.ALL[i];
			if(sb.length()!=0)
				sb.append(" ");
			sb.append(md.id());
		}
		return sb.toString();
	}
}
