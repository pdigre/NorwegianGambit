package no.pdigre.chessutil;

import java.util.HashMap;

public class EngineRoce extends WrapExe implements IPerft{

	public EngineRoce() {
		super("C:/git/TheChessProject/TheChessProject/resources/roce39.exe");
	}

	@Override
	public int perft(String fen,int depth){
		command("setboard "+fen);
		String[] lines = command("perft "+depth, "Perft").split("\n");
		String prefix = "Perft ";
		for (String line : lines) {
			if(line.startsWith(prefix)){
				String count = line.split(":")[1].split(",")[0];
				return Integer.parseInt(count.trim());
			}
		}
		return 0;
	}
	
	@Override
	public HashMap<String, Integer> divide(String fen,int depth){
		HashMap<String, Integer> map=new HashMap<>();
		command("setboard "+fen);
		String prefix = "Moves:";
		String[] lines = command("divide "+depth, prefix).split("\n");
		boolean started=false;
		for (String line : lines) {
			if(line.matches("[a-h][1-8][a-h][1-8] ([0-9]+)"))
				started=true;
			if(started){
				if(line.trim().isEmpty() || line.startsWith(prefix))
					break;
				String[] s = line.split(" ");
				map.put(s[0], Integer.parseInt(s[1]));
			}
		}
		return map;
	}
	
}
