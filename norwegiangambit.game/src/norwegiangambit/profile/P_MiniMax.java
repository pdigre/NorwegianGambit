package norwegiangambit.profile;

import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.iterate.IterateEnd;
import norwegiangambit.engine.iterate.MiniMax;

public class P_MiniMax extends TestPlayer {

    public P_MiniMax(int depth) {
		super("MiniMax", depth,new IterateEnd());
	}

    @Override
    public IIterator addIterator(IIterator iter) {
    	return new MiniMax(iter);
    }

}
