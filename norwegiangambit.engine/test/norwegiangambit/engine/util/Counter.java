package norwegiangambit.engine.util;


public class Counter {

    public int moves;

    public int captures;

    public int checks;

    public int mates;

    public int castlings;

    public int enpassants;

    public int promotions;

	void add(Counter count) {
        captures += count.captures;
        castlings += count.castlings;
        checks += count.checks;
        enpassants += count.enpassants;
        mates += count.mates;
        moves += count.moves;
        promotions += count.promotions;
	}

    public static void total(Counter[] total, Counter[] add) {
        for (int i = 0; i < add.length; i++)
            total[i+1].add(add[i]);
    }

    public static void total(int[] total, int[] add) {
        for (int i = 0; i < add.length; i++)
            total[i+1]+=add[i];
    }

}