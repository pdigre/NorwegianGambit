package norwegiangambit.engine.iterate;

import norwegiangambit.engine.Movegen;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.IConst;

public class NodeGen implements IConst {

	public static final long[] getLegalMoves(Position pos) {
		Position[] list = getLegalMoves64(pos);
		long[] ret = new long[list.length];
		for (int i = 0; i < ret.length; i++)
			ret[i] = list[i].getBitmap();
		return ret;
	}

	public static final Position[] getLegalMoves64(Position pos) {
		Movegen movegen = new Movegen();
		movegen.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		return wrap(pos, movegen.legalmoves());
	}

	public static Position[] wrap(Position pos, int[] moves) {
		Position[] list=new Position[moves.length];
		for (int i = 0; i < moves.length; i++) {
			list[i]=new Position(pos);
			list[i].make(i,list[i].bitmap);
		}
		return list;
	}

	public static final Position[] getQuiescence64(Position pos) {
		Movegen movegen = new Movegen();
		movegen.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		return wrap(pos,movegen.legalmoves());
	}

	/**
	 * Tuning parameter: list size at or below which insertion sort will be used
	 * in preference to mergesort. To be removed in a future release.
	 */
	public static final int INSERTIONSORT_THRESHOLD = 7;

	/**
	 * Src is the source array that starts at index 0 Dest is the (possibly
	 * larger) array destination with a possible offset low is the index in dest
	 * to start sorting high is the end index in dest to end sorting off is the
	 * offset to generate corresponding low, high in src To be removed in a
	 * future release.
	 */
	public static void mergeSort(Position[] src, Position[] dest, int low, int high, int off) {
		int length = high - low;

		// Insertion sort on smallest arrays
		if (length < INSERTIONSORT_THRESHOLD) {
			for (int i = low; i < high; i++)
				for (int j = i; j > low && dest[j - 1].score > dest[j].score; j--) {
					int b = j - 1;
					Position t = dest[j];
					dest[j] = dest[b];
					dest[b] = t;
				}
			return;
		}

		// Recursively sort halves of dest into src
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high) >>> 1;
		mergeSort(dest, src, low, mid, -off);
		mergeSort(dest, src, mid, high, -off);

		// If list is already sorted, just copy from src to dest. This is an
		// optimization that results in faster sorts for nearly ordered lists.
		if (src[mid - 1].score <= src[mid].score) {
			System.arraycopy(src, low, dest, destLow, length);
			return;
		}

		// Merge sorted halves (now in src) into dest
		for (int i = destLow, p = low, q = mid; i < destHigh; i++) {
			if (q >= high || p < mid && src[p].score <= src[q].score)
				dest[i] = src[p++];
			else
				dest[i] = src[q++];
		}
	}

}
