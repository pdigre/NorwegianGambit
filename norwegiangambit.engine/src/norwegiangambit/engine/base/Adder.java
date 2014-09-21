package norwegiangambit.engine.base;


public abstract class Adder {
	final Movegen gen;

	public Adder(Movegen gen,long b64) {
		this.gen = gen;
		int pop = Long.bitCount(b64);
		for (int j = 0; j < pop; j++) {
			int from = Long.numberOfTrailingZeros(b64);
			b64 ^= 1L << from;
			add(from);
		}
	}

	abstract void add(int from);

	void add(MOVEDATA move) {
		gen.add(move);
	}

	void capture(MOVEDATA move) {
		gen.capture(move);
	}
}
