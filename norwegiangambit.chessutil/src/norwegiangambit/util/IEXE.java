package norwegiangambit.util;

public interface IEXE {

	void command(String line);

	void response(String line);

	String waitFor(String in, String out);

}
