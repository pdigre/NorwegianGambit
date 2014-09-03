package norwegiangambit.engine.fen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UCI {

	public enum UCI_to_GUI {
		ID, UCIOK, READYOK, BESTMOVE, COPYPROTECTION, REGISTRATION, INFO, OPTION
	}

	public enum UCI_from_GUI {
		UCI, DEBUG, ISREADY, SETOPTION, REGISTER, UCINEWGAME, POSITION, GO, STOP, PONDERHIT, QUIT
	}
	
	BufferedReader br;

	private Position pos;
	private static UCI instance;
	boolean isReady=true;

	public static UCI getInstance() {
		if (instance == null)
			instance = new UCI();
		return instance;
	}

	public UCI() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("pdigre Chess:");
		UCI inst = getInstance();
		do {
			String line = inst.getLine();
			inst.processUCI(line);
		} while (instance != null);
	}

	public String getLine() {
		try {
			String line;
			while (instance != null && (line = br.readLine()) != null) {
				return line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void send(UCI_to_GUI reply) {
		System.out.println(reply);
	}

	public void send(UCI_to_GUI reply, String params) {
		System.out.println(reply + " " + params);
	}

	public String processUCI(String line) {
		if (line == null)
			return null;
		String[] c = line.split(" ");
		String cmd = c[0];

		try {
			switch (UCI_from_GUI.valueOf(cmd.toUpperCase())) {
			case QUIT:
				instance = null;
				System.out.println("EXIT");
				return null;
			case UCI:
				return uci();
			case DEBUG:
				return debug_onoff("on".equalsIgnoreCase(c[1]));
			case GO:
				break;
			case ISREADY:
				send(UCI_to_GUI.READYOK);
				return null;
			case PONDERHIT:
				break;
			case POSITION:
				if("fen".equalsIgnoreCase(c[1])){
					pos=new StartGame(c[2]);
				} else if("startpos".equalsIgnoreCase(c[1])){
					pos=new StartGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
				}
				break;
			case REGISTER:
				break;
			case SETOPTION:
				break;
			case STOP:
				break;
			case UCINEWGAME:
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println("Unrecognized command '" + cmd + "'");
		}
		return "unknown";
	}

	private String debug_onoff(boolean on) {
		return null;
	}

	private String uci() {
		send(UCI_to_GUI.ID,"name OsloChess");
		send(UCI_to_GUI.ID,"author Per Digre");
		send(UCI_to_GUI.UCIOK);
		return null;
	}

}
