package norwegiangambit.engine.evaluate;

import java.util.Map;

import norwegiangambit.engine.evaluate.RunAlphaBeta.Eval;

public interface IThinker {

	Map<String, Eval> evaluate(String fen, int levels);

}
