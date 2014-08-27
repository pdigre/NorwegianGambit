package no.pdigre.chessutil;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Very fast PERFT that utilizes hashing
 * Available from http://home.hccnet.nl/h.g.muller/dwnldpage.html
 *
 */
public class EngineQperft implements IPerft {
	String exepath = "C:/git/TheChessProject/TheChessProject/resources/perft.exe";

	@Override
	public int perft(String fen, int depth) {
		ArrayList<String> cmd=new ArrayList<String>();
		cmd.add(exepath);
		cmd.add(String.valueOf(depth));
		cmd.add("\""+fen+"\"");
		int perft=0;
		ArrayList<String> lines = runExec(cmd);
		for (String line : lines) {
			if(line.matches("perft\\(.*\\)")) {
				String trim = line.split("=")[1].split("\\(")[0].trim();
				perft=Integer.parseInt(trim);
			}
		}
		return perft;
	}

	@Override
	public HashMap<String, Integer> divide(String fen, int depth) {
		ArrayList<String> cmd=new ArrayList<String>();
		cmd.add(exepath);
		cmd.add(String.valueOf(depth));
		cmd.add("-2");
		cmd.add("\""+fen+"\"");
		ArrayList<String> lines = runExec(cmd);

		HashMap<String, Integer> map = new HashMap<>();
		for (String line : lines) {
			if (line.startsWith("2. ")){
				String[] split = line.split("=");
				String s = split[1].split("\\(")[0].split(",")[0].trim();
				map.put(line.split(" ")[1], Integer.parseInt(s));
			}
		}
		return map;
	}

	private ArrayList<String> runExec(List<String> cmd) {
		ArrayList<String> lines=new ArrayList<String>();
		try {
			File file = new File(exepath);
			assertTrue(file.exists());
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.redirectErrorStream(true);
			pb.directory(file.getParentFile());
			Process process = pb.start();
			try {
				InputStream is = process.getInputStream();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(">" + line);
					lines.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Program terminated!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
