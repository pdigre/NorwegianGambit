import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.engine.movegen.Movegen;
import norwegiangambit.util.BitBoard;


public class JNImovegen {
	private final static JNImovegen instance;
	
	static {
		System.loadLibrary("jni/movegen");
		int i=MOVEDATA.ALL.length;
		assert i>0;
		instance=new JNImovegen();
		instance.copyMagic(
				BitBoard.bMasks,BitBoard.bBits,BitBoard.bTables,BitBoard.bMagics,
				BitBoard.rMasks,BitBoard.rBits,BitBoard.rTables,BitBoard.rMagics);
	}

	private native void copyMagic(long[] bmasks, int[] bbits, long[][] btables, long[] bmagics, long[] rmasks, int[] rbits, long[][] rtables, long[] rmagics);
	private native void copyMovedata(MOVEDATA[] all);
	private native void config(Movegen gen);
	private native void init(Movegen gen);
	private native void movegen();

	// Test Driver
	public static void main(String[] args) {
		new JNImovegen().movegen(); // invoke the native method
	}

}
