package norwegiangambit.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class WrapExe{
	
	
	String exepath;
	volatile private OutputStreamWriter writer;
	protected Process process;
	private Thread thread;

	volatile boolean exit=false;
	volatile ArrayList<String> response=new ArrayList<>();

	private static WrapExe instance;
	
	public static IDivide getInstance(){
		if(instance==null){
			instance=new WrapExe();
			instance.start();
		}
		return (IDivide) instance;
	}

	public WrapExe() {
		super();
	}

	public WrapExe(String exepath) {
		super();
		this.exepath = exepath;
		start();
	}

	private void start() {
		try {
			File file = new File(exepath);
			ProcessBuilder pb = new ProcessBuilder(exepath);
			pb.redirectErrorStream(true);
			pb.directory(file.getParentFile());
			process = pb.start();
			OutputStream outputstream = process.getOutputStream();
			writer = new OutputStreamWriter(outputstream);
			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						InputStream is = process.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(is));
						String line;
						while ((line = br.readLine()) != null) {
//							System.out.println(">"+line);
							response(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
//					System.out.println("Program terminated!");
				}
			});
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void response(String line){
		response.add(line);
	}

	/** 
	 * Send a command but not wait for response
	 * @param line
	 */
	public void command(String line) {
		try {
			while(writer==null)
				Thread.sleep(100);
//			System.out.println("<"+line);
			writer.write(line+"\n");
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			process.getInputStream().close();
			writer.close();
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Will wait for reponse that matches out
	 * @param in
	 * @param out
	 * @return
	 */
	public String command(String in, String out) {
		command(in);
		return waitFor(out);
	}

	public String waitFor(String match) {
		StringBuffer sb = new StringBuffer();
		try {
			while(true){
				if(!response.isEmpty()){
					String test = response.get(0);
					sb.append(test+"\n");
					response.remove(0);
					if(test.startsWith(match))
						return sb.toString();
				}
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void loop() {
		try {
			while(!exit)
				Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



}