package norwegiangambit.engine;

import java.util.Map;

import norwegiangambit.engine.fen.Position;

public interface IPerft {

	Map<String, Integer> divide(String fen, int depth);

}
