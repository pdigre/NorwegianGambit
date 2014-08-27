package norwegiangambit.profile;

import norwegiangambit.engine.iterate.IterateEnd;

public class P_AlphaBeta extends TestPlayer {

	public P_AlphaBeta(int depth){
		super("AlphaBeta",depth,new IterateEnd());
	}

}
