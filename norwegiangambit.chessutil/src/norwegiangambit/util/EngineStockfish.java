package norwegiangambit.util;

import java.util.ArrayList;
import java.util.List;

public class EngineStockfish extends WrapUCI implements IDivide, IEvalStat{

	public static String DEFAULT_EXEPATH = "C:/chess/stockfish.exe";

	public EngineStockfish(String exepath) {
		super(exepath);
	}

	@Override
	public List<Eval> divide(String fen,int depth){
		ArrayList<Eval> map=new ArrayList<Eval>();
		command("position fen "+fen);
		command("divide "+depth);
		String[] lines = waitFor("isready", "readyok").split("\n");
		String prefix = "Position:";
		boolean started=false;
		for (String line : lines) {
			if(started){
				if(line.trim().isEmpty())
					break;
				String[] s = line.split(": ");
				map.add(new Eval(s[0], Integer.parseInt(s[1]),0));
			}
			if(line.startsWith(prefix))
				started=true;
		}
		return map;
	}

	public String play(String fen,int depth) {
		command("position fen "+fen);
		String[] lines = waitFor("go depth "+depth, "bestmove").split("\n");
		return lines[lines.length-1].split(" ")[1];
	}
	
	@Override
	public String eval(String fen){
		waitFor("isready", "readyok");
		command("position fen "+fen);
		String res = waitFor("eval", "Total Evaluation:");
		return res;
	}
}
