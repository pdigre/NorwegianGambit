package norwegiangambit.util;

import java.util.HashMap;

public class EngineRoce extends WrapExe implements IDivide{

	public static String DEFAULT_EXEPATH = "C:/chess/roce39.exe";

	public EngineRoce(String exepath) {
		super(exepath);
	}

	@Override
	public HashMap<String, Integer> divide(String fen,int depth){
		HashMap<String, Integer> map=new HashMap<>();
		if(process==null)
			return map;
		command("setboard "+fen);
		String prefix = "Moves:";
		String[] lines = waitFor("divide "+depth, prefix).split("\n");
		boolean started=false;
		for (String line : lines) {
			if(line.matches("[a-h][1-8][a-h][1-8] ([0-9]+)"))
				started=true;
			if(started){
				if(line.trim().isEmpty() || line.startsWith(prefix) || line.contains(":"))
					break;
				String[] s = line.split(" ");
				map.put(s[0], Integer.parseInt(s[1]));
			}
		}
		return map;
	}
	
}
