package norwegiangambit.engine;

import java.util.Map;

public interface IPerft {

	Map<String, Integer> divide(String fen, int depth);

}
