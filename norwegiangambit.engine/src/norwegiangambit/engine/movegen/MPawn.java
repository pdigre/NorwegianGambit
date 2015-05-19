package norwegiangambit.engine.movegen;

public abstract class MPawn extends MBase {

	public int M1;   // Forward
	public int M2;
	public int P1;   // Promotion

	public MPawn(int from) {
		super(from);
	}

}
