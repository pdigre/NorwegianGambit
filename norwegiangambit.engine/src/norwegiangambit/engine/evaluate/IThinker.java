package norwegiangambit.engine.evaluate;

import java.util.Map;

public interface IThinker {

	public class Eval{
		int count=0;
		int value=0;

		public Eval(int count, int value) {
			this.count = count;
			this.value = value;
		}
	}
	
	Map<String, Eval> evaluate(String fen, int levels);

}
