package norwegiangambit.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Base_Test_OCE {

    static Process process;

    static private OutputStream outputStream;
    static private BufferedWriter outputWriter;

    static private Thread inputThread;

    static Timer timer = new Timer();

    static Semaphore semaphore;

    static StringBuilder response = new StringBuilder();

    static volatile String reply;

    /**
     * Runs a command on the console returns a string
     *
     * @param command
     * @return
     * @throws Exception
     */
    protected synchronized String command(String command) {
    	long time1 = System.currentTimeMillis();
        try {
            sendLine(command);
            sendLine("join");
            semaphore.acquire(1);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        // Strip OK from Reply
        if (":".equals(reply)) {
            reply = "";
        } else if (reply.endsWith("\n:")) {
            reply = reply.substring(0, reply.length() - 2);
        }
        System.out.println((System.currentTimeMillis()-time1)+"ms =>" + command + "\n" + reply);
		return reply;
    }

    static int parseLine(String num) {
        if (num == null || num.isEmpty())
            return 0;
        return Integer.parseInt(num);
    }

    static void sendLine(String line) throws IOException {
//    	System.out.println("cmd:"+line);
        outputWriter.write(line);
        outputWriter.newLine();
        outputWriter.flush();
    }


    @BeforeClass
	public static void before() {
        final BufferedReader inputStream;
            try {
				ProcessBuilder pb = new ProcessBuilder();
				File gsnexe = new File("../oce/debug/oce.exe");
				String path = gsnexe.getCanonicalPath();
				pb.command(new String[] { path, "-p", "-d" });
				pb.directory(gsnexe.getParentFile());
				pb.redirectErrorStream(true);
				process = pb.start();
				outputStream = process.getOutputStream();
				outputWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
				inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()), 1);
	            semaphore = new Semaphore(1, true);
	            semaphore.acquire(1);
	            inputThread = new Thread("input") {

	                @Override
	                public void run() {
	                    try {
	                        String line = null;
	                        while ((line = inputStream.readLine()) != null) {
	                            if (":JOIN".equals(line)) {
	                                reply = response.toString();
	                                response = new StringBuilder();
	                                semaphore.release(1);
	                            } else{
	                            	if(line.startsWith(":"))
	                            		line=line.substring(1);
	                            	if(!line.isEmpty()){
//		                            	System.out.println("OCE:"+line);
		                                response.append(line);
		                                response.append("\n");
	                            	}
	                            }
	                        }
	                        reply="EXIT";
	                        semaphore.release();
	                        if (process != null)
	                            process.destroy();
	                        process = null;
	                        inputStream.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }

	            };
	            inputThread.start();
	            sendLine("join");
	            semaphore.acquire(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@AfterClass
	public static void after() {
        try {
        	sendLine("quit");
            outputWriter.close();
        } catch (Exception e) {
            // Exception OK here
        }
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (process != null) {
                    process.destroy();
                    process.exitValue();
                    try {
                        process.waitFor();
                        timer.cancel();
                    } catch (InterruptedException e) {
                    } catch (IllegalStateException e) {
                        // Ignore this
                    } finally {
                    }
                }
                process = null;
            }
        }, 2000);
	}
	
    @Test
    public void testOCE_1() {
    	command("setboard rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    	command("fen");
    	command("info");
    }
	
	
}
