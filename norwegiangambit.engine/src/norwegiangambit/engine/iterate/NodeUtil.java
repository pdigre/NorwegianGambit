package norwegiangambit.engine.iterate;

import norwegiangambit.util.BITS;



public class NodeUtil {

    public static long[] filterFrom(long[] moves, int from) {
        int length = 0;
        long[] array = new long[moves.length];
        for (int i = 0; i < moves.length; i++) {
        	long bitmap = moves[i];
            if (BITS.getFrom(bitmap) == from) {
                array[length] = bitmap;
                length++;
            }
        }
        long[] ret = new long[length];
        System.arraycopy(array, 0, ret, 0, length);
        return ret;
    }

    public static long[] filterTo(long[] moves, int to) {
        int length = 0;
        long[] array = new long[moves.length];
        for (int i = 0; i < moves.length; i++) {
        	long bitmap = moves[i];
            if (BITS.getTo(bitmap) == to) {
                array[length] = bitmap;
                length++;
            }
        }
        long[] ret = new long[length];
        System.arraycopy(array, 0, ret, 0, length);
        return ret;
    }

}
