package norwegiangambit.engine;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import norwegiangambit.engine.fen.FEN;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.util.FileUtils;
import norwegiangambit.engine.util.PerftResults;

import org.junit.Test;

public class Test_PERFT_114s {

	public boolean failed=false;
	
	public class Perft {

		public final String fen;

		public final int[] count;

		public Perft(String fen, int[] count) {
			this.fen = fen;
			this.count = count;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < count.length; i++)
				sb.append(count[i] + " ");
			sb.append(fen);
			return sb.toString();
		}
	}

	@Test
	public void perft_114s() {
		ArrayList<Perft> list = new ArrayList<Test_PERFT_114s.Perft>();
		for (String line : FileUtils.stream2lines(getClass().getResourceAsStream("perftsuite.epd"))) {
			String[] split = line.split("\\;");
			int[] count = new int[split.length - 1];
			for (int i = 0; i < count.length; i++)
				count[i] = Integer.parseInt(split[i + 1].split(" ")[1]);
			list.add(new Perft(split[0], count));
		}
		int line = 1;
		for (Perft perft : list) {
			System.out.print((line++) + ". " + perft);
			long time = System.currentTimeMillis();
			int levels = perft.count.length;
			long actual=0;
			try {
				actual=RunPerftFast.perft(levels, perft.fen);
			} catch (Exception e) {
				e.printStackTrace();
			}
			time = System.currentTimeMillis() - time;
			int expected = perft.count[levels-1];
			if (actual != expected) {
				failed=true;
				System.out.println(" NOT " + time + "ms = > "+actual);
				Position pos=new StartGame(perft.fen);
				PerftResults.findError(pos, levels,FEN.board2string(pos));
			} else {
				System.out.println(" OK " + time + "ms");
			}
		}
		assertFalse("All test run",failed);
	}
}
