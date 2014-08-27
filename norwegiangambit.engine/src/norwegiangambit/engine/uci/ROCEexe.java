package norwegiangambit.engine.uci;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import norwegiangambit.engine.IPerft;
import norwegiangambit.engine.fen.FEN;
import norwegiangambit.engine.fen.Position;

public class ROCEexe implements IPerft{
	private InputStream es;
	private InputStream is;
	private OutputStream os;
	private PrintWriter writer;

	private static ROCEexe instance;
	private BufferedReader br;
	
	public static IPerft getInstance(){
		if(instance==null){
			instance=new ROCEexe();
			instance.run();
		}
		return instance;
	}
	
	private ROCEexe() {
	}
	
	public void run(){
		ClassLoader cl = this.getClass().getClassLoader();
		URL resource = cl.getResource("");
		String dir = new File(resource.getPath()).getParent()+"/resources/";
		ProcessBuilder pb=new ProcessBuilder();
		pb.command(dir+"roce39.exe");
		pb.directory(new File(dir));
		try {
			Process exec = pb.start();
			is = exec.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			es = exec.getErrorStream();
			readStream(es);
			os = exec.getOutputStream();
			writer = new PrintWriter(os);
//			while(!getLine().startsWith("roce:"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void stop() {
		try {
			os.close();
			is.close();
			es.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void command(String cmd) {
		writer.print(cmd+"\n");
		writer.flush();
	}

	
	
	public static void main(String[] args) {
		ROCEexe run = (ROCEexe) getInstance();
		run.command("help");
		run.command("setboard r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1");
		run.command("perft 5");
		run.command("divide 4");
		run.stop();
	}

	public Map<String, Integer> divide(Position pos, int depth) {
		command("setboard "+FEN.getFen(pos));
		command("divide "+depth);
		TreeMap<String, Integer> moves = new TreeMap<String, Integer>();
		String line="";
		while(!line.startsWith("Moves:")){
			line=getLine();
			if(line.startsWith("roce: "))
				line=line.substring(6);
			String[] split = line.split(" ");
			if(split.length==2 && !split[0].contains(":"))
				moves.put(split[0], new Integer(split[1]));
		}
		return moves;
	}

	private void readStream(final InputStream is) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line;
					while ((line = br.readLine()) != null) {
					  System.out.println("ERROR:"+line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public String getLine() {
		try {
			String line;
			while ((line = br.readLine()) != null) {
//				System.out.println("LINE:"+line);
			  return line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
