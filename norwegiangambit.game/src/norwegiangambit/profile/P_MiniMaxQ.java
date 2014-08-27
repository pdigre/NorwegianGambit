package norwegiangambit.profile;

import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.iterate.MiniMax;
import norwegiangambit.engine.iterate.Quiescence;

public class P_MiniMaxQ extends TestPlayer {

    public P_MiniMaxQ(int depth) {
		super("MiniMaxQuiesce", depth,new Quiescence());
	}

    @Override
    public IIterator addIterator(IIterator iter) {
    	return new MiniMax(iter);
    }

}
