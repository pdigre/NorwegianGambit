package jni;
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
		instance.copyMagic(BitBoard.offsets,BitBoard.magics);
	}

	private native void copyMagic(int[] offsets, long[] magics);
	private native void copyMovedata(MOVEDATA[] all);
	private native void config(Movegen gen);
	private native void init(Movegen gen);
	private native void movegen();

	// Test Driver
	public static void main(String[] args) {
		new JNImovegen().movegen(); // invoke the native method
	}

    public static final long rookAttacks(int sq, long occupied) {
		return instance.magicAtks(occupied, sq*4);
    }

    public static final long bishopAttacks(int sq, long occupied) {
		return instance.magicAtks(occupied, sq*4+2);
    }

    private native long magicAtks(long occupied, int i);

}
