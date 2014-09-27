package norwegiangambit.util;

import java.util.ArrayList;
import java.util.List;

public class EngineRoce extends WrapExe implements IDivide{

	public static String DEFAULT_EXEPATH = "C:/chess/roce39.exe";

	public EngineRoce(String exepath) {
		super(exepath);
	}

	@Override
	public List<Eval> divide(String fen,int depth){
		ArrayList<Eval> map=new ArrayList<Eval>();
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
				map.add(new Eval(s[0], Integer.parseInt(s[1]),0));
			}
		}
		return map;
	}
	
}
