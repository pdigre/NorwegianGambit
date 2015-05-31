package norwegiangambit.engine.evaluate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movedata.MOVEDATA;
import norwegiangambit.util.IDivide;
import norwegiangambit.util.IDivide.Eval;

public abstract class AbstractTest {

	protected static IDivide testing;

	public static void setTesting(IDivide engine) {
		testing = engine;
	}

	public static String movepath2string(int[] m) {
		if(m==null)
			return "null";
		StringBuilder sb=new StringBuilder();
		for (int i : m) {
			if(i>0){
				MOVEDATA md=MOVEDATA.ALL[i];
				if(sb.length()!=0)
					sb.append(" ");
				sb.append(md.id());
			}
		}
		return sb.toString();
	}

	public static void testSearch(IDivide inst, int depth, long ecount,String fen,String[] expected) {
		List<Eval> divide = inst.divide(fen, depth);
		long acount=0;
		StringBuilder sb=new StringBuilder();
		for (Eval eval : new TreeSet<Eval>(divide)){
			acount+=eval.count;
			if(sb.length()>0)
				sb.append(",");
			sb.append(movepath2string(((RootEval)eval).path)+"="+eval.value);
		}
		System.out.println("TOT "+acount+" > "+(acount-ecount));
		String expected2 = String.join("\n", expected);
		String actual = sb.toString();
		String actual2 = actual.replace(",", "\n");
		assertEquals("Wrong score", expected2, actual2);
	}

	public static void testSearch(IDivide inst, int depth, long ecount,String input) {
		String[] in=input.replace("\r","").split("\n");
		testSearch(inst, depth, ecount, in[0], Arrays.copyOfRange(in, 1, in.length));
	}

	public static void testEval(LongEval leaf,String fen,String[] expected) {
		StartGame pos = new StartGame(fen);
		FastEval root = new FastEval();
		root.setChild(leaf);
		leaf.setParent(root);
		root.set(pos.whiteNext(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3(), pos.getBitmap());
		root.evaluate();
		root.generate();
		StringBuilder sb=new StringBuilder("Move PSQT Pawn_W__B <Bishop <--Rook Cstle Trade <--Threat KingS  EG  ADJ  TOT");
		TreeSet<String> lines = new TreeSet<String>();
		for (int i1 = 0; i1 < root.iAll; i1++) {
			int md = root.moves[i1];
			root.make(md);
			leaf.evaluate(md);
			lines.add(","+MOVEDATA.ALL[md].id()+leaf.printEval());
		}
		for (String line : lines)
			sb.append(line);
		String expected2 = String.join("\n", expected);
		String actual = sb.toString();
		String actual2 = actual.replace(",", "\n");
		assertEquals("Wrong score", expected2, actual2);
	}

	public static void testEval(LongEval leaf,String fen,String expected) {
		StartGame pos = new StartGame(fen);
		LongEval root = new LongEval();
		root.setChild(leaf);
		leaf.setParent(root);
		root.set(pos.whiteNext(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3(), pos.getBitmap());
		root.evaluate();
		assertEquals("Wrong score", expected, root.printEval());
	}

	public static void testEval(LongEval inst,String input) {
		String[] in=input.replace("\r","").split("\n");
		testEval(inst, in[0], Arrays.copyOfRange(in, 1, in.length));
	}

	public static void testFen(LongEval inst,String input) {
		String[] in=input.replace("\r","").split("\n");
		testEval(inst, in[0], Arrays.copyOfRange(in, 1, in.length));
	}

	public static void testQuiesce(IDivide inst, int depth, long ecount,String fen,String[] expected) {
		List<Eval> divide = inst.divide(fen, depth);
		long acount=0,quiesce=0;
		StringBuilder sb=new StringBuilder();
		for (Eval eval : new TreeSet<Eval>(divide)){
			acount+=eval.count;
			quiesce+=eval.quiesce;
			if(sb.length()>0)
				sb.append(",");
			sb.append(movepath2string(((RootEval)eval).path)+"="+eval.value);
		}
		System.out.println("TOT "+acount+" > "+(acount-ecount)+" "+quiesce);
		String expected2 = String.join("\n", expected);
		String actual = sb.toString();
		String actual2 = actual.replace(",", "\n");
		assertEquals("Wrong score", expected2, actual2);
		for (String res : expected) {
			String[] split = res.split("=");
			int score=Integer.parseInt(split[1]);
			String[] moves=split[0].split(" ");
			assertScore(fen,moves,score);
		}
		assertTrue("Poor score "+acount,acount<=ecount);
	}

	private static void assertScore(String fen, String[] moves, int expected) {
		FastEval gen = new FastEval();
		gen.setChild(gen);
		StartGame pos = new StartGame(fen);
		gen.set(pos.whiteNext(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3(), pos.getBitmap());
		try {
			for (String move : moves) {
				int mv=0;
				gen.generate();
				for (int i = 0; i < gen.iAll; i++) {
					int m = gen.moves[i];
					MOVEDATA md2 = MOVEDATA.ALL[m];
					if(md2.id().equals(move))
						mv=m;
				}
				gen.make(mv);
			}
			gen.evaluate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Wrong score "+String.join(" ",moves),expected, gen.whiteScore());
	}

	public static void testElo(final ISearch inst, String fen,long time) {
		final long time0 = System.currentTimeMillis();
		Timer timer=new Timer("Test");
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				inst.stop();
			}
		}, time);
		inst.foundBetter(new Runnable() {
			
			@Override
			public void run() {
				long time1 = System.currentTimeMillis();
				System.out.println((time1-time0)+"ms "+inst.bestPath());
			}
		});
		inst.start(fen);
	}


}
