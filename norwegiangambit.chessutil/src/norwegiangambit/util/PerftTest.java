package norwegiangambit.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import norwegiangambit.util.IDivide.Eval;

public class PerftTest {
	
	protected static IDivide testing;
	protected static IDivide validator;
	
	public static void setTesting(IDivide engine){
		testing=engine;
	}

	public static void setValidator(IDivide engine){
		validator=engine;
	}

	public static void testPerft(IDivide inst, int depth, long expected, String fen) {
		Collection<Eval> divide = inst.divide(fen, depth);
		long actual=0;
		for (Eval eval : divide)
			actual+=eval.count;
		assertPERFT(expected,fen,depth,actual);
	}

	public static void assertPERFT(long cnt, String fen, int levels,long run) {
		if(cnt==run)
			return;
		findError(fen, levels,FEN.board2string(fen));
		assertTrue("Wrong "+run+"/"+cnt,false);
	}

	
	
	public static void findError(String fen, int levels,String append) {
		try {
			Map<String, Integer> actual = getMoves(testing.divide(fen,levels));
			Map<String, Integer> expected = getMoves(validator.divide(fen, levels));
			Set<String> kactual = new HashSet<String>(actual.keySet());
			Set<String> kexpected = new HashSet<String>(expected.keySet());
			if(!kactual.equals(kexpected)){
				Set<String> kactual2=new HashSet<String>(kactual);
				kactual.removeAll(kexpected);
				kexpected.removeAll(kactual2);
				System.out.println(append);
				System.out.println("ILLEGAL= "+String.join(" ", kactual)+", MISSING= "+String.join(" ", kexpected));
				System.out.println(fen);
				return;
			} else {
				String[] keys = kexpected.toArray(new String[expected.size()]);
				for (int i = 0; i < keys.length; i++) {
					String move=keys[i];
					int e = expected.get(move);
					int a = actual.get(move);
					if(a!=e){
						String fen2=FEN.make(fen,move);
						System.out.println(move+" > "+fen2);
						String text = FEN.addHorizontal(FEN.board2string(fen2)+"\n"+("  << "+move+"      ").substring(0, 10), append);
						findError(fen2, levels-1,text);
						return; 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(append);
			System.out.println(fen);
		}
	}

	private static Map<String,Integer> getMoves(Collection<Eval> actual) {
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		for (Eval eval : actual)
			map.put(eval.move,eval.count);
		return map;
	}

	public static class Score {

		public final String fen;

		public final int[] count;

		public Score(String fen, int[] count) {
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

	public static boolean failed=false;

	public static void testPerftSuite(InputStream input) {
		ArrayList<Score> list = new ArrayList<Score>();
		for (String line : FileUtils.stream2lines(input)) {
			String[] split = line.split("\\;");
			int[] count = new int[split.length - 1];
			for (int i = 0; i < count.length; i++)
				count[i] = Integer.parseInt(split[i + 1].split(" ")[1]);
			list.add(new Score(split[0], count));
		}
		int line = 1;
		for (Score score : list) {
			System.out.print((line++) + ". " + score);
			long time = System.currentTimeMillis();
			int levels = score.count.length;
			long actual=0;
			try {
				Map<String, Integer> divide = getMoves(testing.divide(score.fen, levels));
				for (Integer div : divide.values())
					actual+=div;
			} catch (Exception e) {
				e.printStackTrace();
			}
			time = System.currentTimeMillis() - time;
			int expected = score.count[levels-1];
			if (actual != expected) {
				failed=true;
				System.out.println(" NOT " + time + "ms = > "+actual);
				PerftTest.findError(score.fen, levels,FEN.board2string(score.fen));
			} else {
				System.out.println(" OK " + time + "ms");
			}
		}
		assertFalse("All test run",failed);
	}

}
