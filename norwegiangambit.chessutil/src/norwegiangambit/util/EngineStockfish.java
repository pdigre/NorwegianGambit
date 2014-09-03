package norwegiangambit.util;

import java.util.HashMap;

public class EngineStockfish extends WrapUCI implements IDivide{

	public EngineStockfish(String exepath) {
		super(exepath);
	}

	@Override
	public HashMap<String, Integer> divide(String fen,int depth){
		HashMap<String, Integer> map=new HashMap<>();
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
				map.put(s[0], Integer.parseInt(s[1]));
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
	
}
