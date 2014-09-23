package norwegiangambit.engine.evaluate;

import java.util.Map;
import java.util.Map.Entry;

import norwegiangambit.engine.evaluate.RunAlphaBeta.Eval;

public class EvalTest {

	protected static IThinker testing;

	public static void setTesting(IThinker engine) {
		testing = engine;
	}

	public static void testEval(IThinker inst, int depth, String fen) {
		Map<String, Eval> divide = inst.evaluate(fen, depth);
		for (Entry<String, Eval> div : divide.entrySet()){
			Eval eval = div.getValue();
			System.out.println(div.getKey()+" ("+eval.count+") = "+eval.value);
		}
	}

}
