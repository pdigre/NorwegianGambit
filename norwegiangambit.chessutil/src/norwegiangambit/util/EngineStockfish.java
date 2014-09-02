package norwegiangambit.util;

import java.util.HashMap;

public class EngineStockfish extends WrapExe implements IDivide{

	public EngineStockfish(String exepath) {
		super(exepath);
	}

	@Override
	public HashMap<String, Integer> divide(String fen,int depth){
		HashMap<String, Integer> map=new HashMap<>();
		command("position fen "+fen);
		command("divide "+depth);
		String[] lines = command("isready", "readyok").split("\n");
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
	
}
