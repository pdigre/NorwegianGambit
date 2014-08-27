package norwegiangambit.profile;

import norwegiangambit.engine.iterate.AlphaBeta;
import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.iterate.IterateEnd;


public class Easy extends Player {

    @Override
    public String getDescription() {
        return "Easy 3 iterations MiniMax with Tactical evaluation";
    }
    
    @Override
    public void run() {
        IIterator iter0 = new IterateEnd();
        IIterator iter1 = new AlphaBeta(iter0);
        IIterator iter2 = new AlphaBeta(iter1);
        IIterator iter3 = new AlphaBeta(iter2);
        processAndMove(iter3);
    }

}
