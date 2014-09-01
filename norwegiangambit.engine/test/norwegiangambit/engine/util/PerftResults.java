package norwegiangambit.engine.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import norwegiangambit.engine.Test_PERFT_5300ms;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.FileUtils;

public class PerftResults {

	public static HashMap<String, String> allexpected;
	
	final Position[] rootmoves;
	final public int[] rootcount;
	public Counter[] counters;

	public PerftResults(Position[] rootmoves) {
		this.rootmoves = rootmoves;
		rootcount = new int[rootmoves.length];
	}

	public static void total(Counter[] total, Counter[] add) {
		for (int i = 0; i < add.length; i++)
			total[i + 1].add(add[i]);
	}

	public static String printCounter(Counter[] counters) {
		StringBuffer sb = new StringBuffer();
		String x = "Depth,Moves,Captures,Enpassant,Castling,Promotion,Check,Mate";
		sb.append(format10(x) + "\r\n");
		for (int i = 0; i < counters.length; i++) {
			Counter cnt = counters[i];
			sb.append(format10(String.format("%d,%d,%d,%d,%d,%d,%d,%d", i + 1, cnt.moves, cnt.captures, cnt.enpassants, cnt.castlings,
					cnt.promotions, cnt.checks, cnt.mates)) + "\r\n");
		}
		return sb.toString();
	}

	private static String format10(String delimited) {
		StringBuilder sb = new StringBuilder();
		for (String string : delimited.split(","))
			sb.append("          ".substring(string.length()) + string);
		return sb.toString();
	}

	public static void assertPERFT(String fen, Counter[] counters) {
		String actual = "FEN=" + fen + "\r\n" + PerftResults.printCounter(counters);
		String expected = allexpected.get(fen).substring(0, actual.length());
		assertEquals(expected, actual);
	}

	public static void readAll() {
		allexpected=new HashMap<String, String>();
		URL resource = new Test_PERFT_5300ms().getClass().getResource("perft.txt");
		try {
			String txt = FileUtils.stream2string((InputStream) resource.getContent());
			int i = 0;
			String[] split = txt.split("\\\r\\\n");
			loop:
			while(i<split.length){
				while (!split[++i].startsWith("FEN=")){
					if(i+2>split.length) break loop;
				}
				String fen=split[i].substring(4);
				StringBuffer sb = new StringBuffer();
				for (; i<split.length && !split[i].equals(""); i++)
					sb.append(split[i] + "\r\n");
				allexpected.put(fen,sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}