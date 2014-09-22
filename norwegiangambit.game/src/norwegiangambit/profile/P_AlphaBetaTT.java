package norwegiangambit.profile;

import norwegiangambit.engine.iterate.AlphaBetaTTOld;
import norwegiangambit.engine.iterate.IIterator;
import norwegiangambit.engine.iterate.QuiescenceTT;
import norwegiangambit.engine.iterate.Transposition;

public class P_AlphaBetaTT extends TestPlayer {

	public P_AlphaBetaTT(int depth){
		super("AlphaBetaTT",depth,new QuiescenceTT());
	}

	@Override
	public IIterator addIterator(IIterator iter) {
		return new AlphaBetaTTOld(iter);
	}
	
	@Override
	public void run() {
		super.run();
		printResults();
	}

	public void printResults() {
		System.out.println("*****************************");
		System.out.println("Size:"+Transposition.TTSIZE);
		Transposition.total =Transposition.hits+Transposition.miss;
		stat("Hits", Transposition.hits);
		stat("Miss", Transposition.miss);
		stat("Coll", Transposition.coll);
		stat("Err", Transposition.err);
		stat("Qual", Transposition.qual);
		System.out.println("Hitrate:" + (1.0*Transposition.hits/(Transposition.miss))+"%");
	}

	public void stat(String name, long i) {
		System.out.println(name+":"+i + " = " + (100.0*i/Transposition.total)+"%");
	}
}
