package norwegiangambit.engine.evaluate;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.TreeSet;

import norwegiangambit.util.IDivide;
import norwegiangambit.util.IDivide.Eval;

public class EvalTest {

	protected static IDivide testing;

	public static void setTesting(IDivide engine) {
		testing = engine;
	}

	public static void testEval(IDivide inst, int depth, String fen,String expected) {
		List<Eval> divide = inst.divide(fen, depth);
		long count=0;
		StringBuilder sb=new StringBuilder();
		for (Eval eval : new TreeSet<Eval>(divide)){
			System.out.println(eval.move+" "+eval.count+" = "+eval.value);
			count+=eval.count;
			if(sb.length()>0)
				sb.append(",");
			sb.append(eval.move+"="+eval.value);
		}
		assertEquals("Wrong score", expected, sb.toString());
		System.out.println("TOT "+count);
	}

}
