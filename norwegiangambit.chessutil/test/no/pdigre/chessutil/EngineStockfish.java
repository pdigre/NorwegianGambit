package no.pdigre.chessutil;

import java.util.HashMap;

public class EngineStockfish extends WrapExe implements IPerft{

	public EngineStockfish() {
		super("C:/fishtest/worker/testing/stockfish.exe");
	}

	@Override
	public int perft(String fen,int depth){
		command("position "+fen);
		command("perft "+depth);
		String[] lines = command("isready", "readyok").split("\n");
		String prefix = "Nodes searched  : ";
		for (String line : lines) {
			if(line.startsWith(prefix))
				return Integer.parseInt(line.substring(prefix.length()).trim());
		}
		return 0;
	}
	
	@Override
	public HashMap<String, Integer> divide(String fen,int depth){
		HashMap<String, Integer> map=new HashMap<>();
		command("position "+fen);
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
