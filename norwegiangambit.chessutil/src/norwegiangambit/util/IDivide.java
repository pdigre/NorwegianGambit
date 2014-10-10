package norwegiangambit.util;

import java.util.List;

public interface IDivide {

	public class Eval implements Comparable<Eval>{
		public String move;
		public long count=0;
		public long quiesce=0;
		public int value=0;

		public Eval(String move,int count, int value) {
			this.move=move;
			this.count = count;
			this.value = value;
		}

		@Override
		public int compareTo(Eval e2) {
			return move.compareTo(e2.move);
		}
		
	}

	List<Eval> divide(String fen, int depth);

	
}
