package norwegiangambit.engine.evaluate;

import static org.junit.Assert.*;

import java.util.List;
import java.util.TreeSet;

import norwegiangambit.util.IDivide;
import norwegiangambit.util.IDivide.Eval;

public class EvalTest {

	protected static IDivide testing;

	public static void setTesting(IDivide engine) {
		testing = engine;
	}

	public static void testEval(IDivide inst, int depth, long ecount,String fen,String expected) {
		List<Eval> divide = inst.divide(fen, depth);
		long acount=0;
		StringBuilder sb=new StringBuilder();
		for (Eval eval : new TreeSet<Eval>(divide)){
			acount+=eval.count;
			if(sb.length()>0)
				sb.append(",");
			sb.append(eval.toString()+"="+eval.bestV);
		}
		System.out.println("TOT "+acount+" > "+(acount-ecount));
		assertEquals("Wrong score", expected.replace(",", "\n"), sb.toString().replace(",", "\n"));
		assertTrue("Poor score",acount<=ecount);
	}

}
