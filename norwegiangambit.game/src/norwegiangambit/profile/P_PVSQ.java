package norwegiangambit.profile;

import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.iterate.PVS;
import norwegiangambit.engine.iterate.Quiescence;

public class P_PVSQ extends TestPlayer {
	
    public P_PVSQ(int depth) {
		super("PVSQuiesce", depth, new Quiescence());
	}

    @Override
    public IIterator addIterator(IIterator iter) {
    	return new PVS(0,iter);
    }
}
