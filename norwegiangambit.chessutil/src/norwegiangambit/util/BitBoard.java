package norwegiangambit.util;

import java.util.Arrays;






public class BitBoard implements IConst{

	private static final byte dirTable[] = {
        -9,  0,  0,  0,  0,  0,  0, -8,  0,  0,  0,  0,  0,  0, -7,
     0,  0, -9,  0,  0,  0,  0,  0, -8,  0,  0,  0,  0,  0, -7,  0,
     0,  0,  0, -9,  0,  0,  0,  0, -8,  0,  0,  0,  0, -7,  0,  0,
     0,  0,  0,  0, -9,  0,  0,  0, -8,  0,  0,  0, -7,  0,  0,  0,
     0,  0,  0,  0,  0, -9,  0,  0, -8,  0,  0, -7,  0,  0,  0,  0,
     0,  0,  0,  0,  0,  0, -9,-17, -8,-15, -7,  0,  0,  0,  0,  0,
     0,  0,  0,  0,  0,  0,-10, -9, -8, -7, -6,  0,  0,  0,  0,  0,
     0, -1, -1, -1, -1, -1, -1, -1,  0,  1,  1,  1,  1,  1,  1,  1,
     0,  0,  0,  0,  0,  0,  6,  7,  8,  9, 10,  0,  0,  0,  0,  0,
     0,  0,  0,  0,  0,  0,  7, 15,  8, 17,  9,  0,  0,  0,  0,  0,
     0,  0,  0,  0,  0,  7,  0,  0,  8,  0,  0,  9,  0,  0,  0,  0,
     0,  0,  0,  0,  7,  0,  0,  0,  8,  0,  0,  0,  9,  0,  0,  0,
     0,  0,  0,  7,  0,  0,  0,  0,  8,  0,  0,  0,  0,  9,  0,  0,
     0,  0,  7,  0,  0,  0,  0,  0,  8,  0,  0,  0,  0,  0,  9,  0,
     0,  7,  0,  0,  0,  0,  0,  0,  8,  0,  0,  0,  0,  0,  0,  9
	 };
	
	 static public final int getDirection(int from, int to) {
	     int offs = to + (to|7) - from - (from|7) + 0x77;
	     return dirTable[offs];
	 }
	
	 private static final byte distTable[] = {
	        7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
	     0, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7,
	     0, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 7,
	     0, 7, 6, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 3, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 3, 2, 2, 2, 2, 2, 3, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 3, 2, 1, 1, 1, 2, 3, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 3, 2, 2, 2, 2, 2, 3, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 3, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7,
	     0, 7, 6, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7,
	     0, 7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 7,
	     0, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7,
	     0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7
	 };
	
	 public static final int getDistance(int from, int to) {
	     int offs = to + (to|7) - from - (from|7) + 0x77;
	     return distTable[offs];
	 }
	
	 public static final long southFill(long mask) {
	     mask |= (mask >>> 8);
	     mask |= (mask >>> 16);
	     mask |= (mask >>> 32);
	     return mask;
	 }
	 
	 public static final long northFill(long mask) {
	     mask |= (mask << 8);
	     mask |= (mask << 16);
	     mask |= (mask << 32);
	     return mask;
	 }

	 public final static long[] BETWEEN;

	static {
		BETWEEN = new long[4096];
		fillBetween(DIAGONAL_MOVES);
		fillBetween(LINE_MOVES);
	}

	private static void fillBetween(int[] offset) {
		for (int j = 0; j < offset.length; j++) {
			int off = offset[j];
			for (int from = 0; from < 64; from++) {
				int to = from + off;
				long ray = 0L;
				while (inside(to, to - off)) {
					int num = from + to * 64;
					BETWEEN[num] = ray;
					ray |= (1L << to);
					to += off;
				}
			}
		}
	}

	


	private static long moves(int from, int[] offset) {
		long ret = 0L;
		for (int i = 0; i < offset.length; i++) {
			int to = from + offset[i];
			if (inside(to, from))
				ret |= 1L << to;
		}
		return ret;
	}

	private static long slide(int from, int[] offset) {
		long ret = 0L;
		for (int i = 0; i < offset.length; i++) {
			int off = offset[i];
			int to = from + off;
			while (inside(to, to - off)) {
				ret |= (1L << to);
				to += off;
			}
		}
		return ret;
	}

	
	/**
	 * is it inside the board = legal move
	 * 
	 * @param to
	 * @param from
	 * @return
	 */
	static boolean inside(int to, int from) {
		if (to < 0 || to > 63)
			return false;
		int x1 = to % 8;
		int x2 = from % 8;
		if ((x1 < 3 && x2 > 4) || (x2 < 3 && x1 > 4))
			return false;
		return true;
	}


	final public static long[]  // Outer Mask
			BMasks = new long[64],
			RMasks = new long[64],  
			QMasks = new long[64],
			KMasks = new long[64],
			NMasks = new long[64];

    static { 
		for (int from = 0; from < 64; from++) {
			BMasks[from] = slide(from, DIAGONAL_MOVES);
			RMasks[from] = slide(from, LINE_MOVES);
			QMasks[from] = BMasks[from] | RMasks[from];
			KMasks[from] = moves(from, LINE_MOVES) | moves(from, DIAGONAL_MOVES);
			NMasks[from] = moves(from, KNIGHT_MOVES);
		}

    }
    
    /** Return index in squares[] vector corresponding to (x,y). */
    public final static int xy2sq(int x, int y) {
        return y * 8 + x;
    }

    public final static int sq2x(int square) {
        return square & 7;
    }
    /** Return y position (rank) corresponding to a square. */
    public final static int sq2y(int square) {
        return square >> 3;
    }

    public static final long[] maskFile = {
        0x0101010101010101L,
        0x0202020202020202L,
        0x0404040404040404L,
        0x0808080808080808L,
        0x1010101010101010L,
        0x2020202020202020L,
        0x4040404040404040L,
        0x8080808080808080L
    };
    
    
    
    
/**********************************
 * Magic number implementation    
 **********************************/
    
    public final static long[] magics=new long[90624+128 + 4800+128];
    public final static int[] offsets=new int[64*4];
    public final static int[] rOffsets=new int[64];

    public final static int[] rBits = { 
    	12, 11, 11, 11, 11, 11, 11, 12,
        11, 10, 10, 11, 10, 10, 10, 11,
        11, 10, 10, 10, 10, 10, 10, 11,
        11, 10, 10, 10, 10, 10, 10, 11,
        11, 10, 10, 10, 10, 10, 10, 11,
        11, 10, 10, 11, 10, 10, 10, 11,
        10,  9,  9,  9,  9,  9, 10, 10,
        11, 10, 10, 10, 10, 11, 10, 11 };

    public final static long[] rMagics = {
        0x19a80065ff2bffffL, 0x3fd80075ffebffffL, 0x4010000df6f6fffeL, 0x0050001faffaffffL,
        0x0050028004ffffb0L, 0x7f600280089ffff1L, 0x7f5000b0029ffffcL, 0x5b58004848a7fffaL,
        0x002a90005547ffffL, 0x000050007f13ffffL, 0x007fa0006013ffffL, 0x006a9005656fffffL,
        0x007f600f600affffL, 0x007ec007e6bfffe2L, 0x007ec003eebffffbL, 0x0071d002382fffdaL,
        0x009f803000e7fffaL, 0x00680030008bffffL, 0x00606060004f3ffcL, 0x001a00600bff9ffdL,
        0x000d006005ff9fffL, 0x0001806003005fffL, 0x00000300040bfffaL, 0x000192500065ffeaL,
        0x00fff112d0006800L, 0x007ff037d000c004L, 0x003fd062001a3ff8L, 0x00087000600e1ffcL,
        0x000fff0100100804L, 0x0007ff0100080402L, 0x0003ffe0c0060003L, 0x0001ffd53000d300L,
        0x00fffd3000600061L, 0x007fff7f95900040L, 0x003fff8c00600060L, 0x001ffe2587a01860L,
        0x000fff3fbf40180cL, 0x0007ffc73f400c06L, 0x0003ff86d2c01405L, 0x0001fffeaa700100L,
        0x00fffdfdd8005000L, 0x007fff80ebffb000L, 0x003fffdf603f6000L, 0x001fffe050405000L,
        0x000fff400700c00cL, 0x0007ff6007bf600aL, 0x0003ffeebffec005L, 0x0001fffdf3feb001L,
        0x00ffff39ff484a00L, 0x007fff3fff486300L, 0x003fff99ffac2e00L, 0x001fff31ff2a6a00L,
        0x000fff19ff15b600L, 0x0007fff5fff28600L, 0x0003fffddffbfee0L, 0x0001fff5f63c96a0L,
        0x00ffff5dff65cfb6L, 0x007fffbaffd1c5aeL, 0x003fff71ff6cbceaL, 0x001fffd9ffd4756eL,
        0x000ffff5fff338e6L, 0x0007fffdfffe24f6L, 0x0003ffef27eebe74L, 0x0001ffff23ff605eL
    };

    public final static int[] bOffsets=new int[64];
    public final static int[] bBits = { 
    	5, 4, 5, 5, 5, 5, 4, 5,
        4, 4, 5, 5, 5, 5, 4, 4,
        4, 4, 7, 7, 7, 7, 4, 4,
        5, 5, 7, 9, 9, 7, 5, 5,
        5, 5, 7, 9, 9, 7, 5, 5,
        4, 4, 7, 7, 7, 7, 4, 4,
        4, 4, 5, 5, 5, 5, 4, 4,
        5, 4, 5, 5, 5, 5, 4, 5 };
    
    public final static long[] bMagics = {
        0x0006eff5367ff600L, 0x00345835ba77ff2bL, 0x00145f68a3f5dab6L, 0x003a1863fb56f21dL,
        0x0012eb6bfe9d93cdL, 0x000d82827f3420d6L, 0x00074bcd9c7fec97L, 0x000034fe99f9ffffL,
        0x0000746f8d6717f6L, 0x00003acb32e1a3f7L, 0x0000185daf1ffb8aL, 0x00003a1867f17067L,
        0x0000038ee0ccf92eL, 0x000002a2b7ff926eL, 0x000006c9aa93ff14L, 0x00000399b5e5bf87L,
        0x00400f342c951ffcL, 0x0020230579ed8ff0L, 0x007b008a0077dbfdL, 0x001d00010c13fd46L,
        0x00040022031c1ffbL, 0x000fa00fd1cbff79L, 0x000400a4bc9affdfL, 0x000200085e9cffdaL,
        0x002a14560a3dbfbdL, 0x000a0a157b9eafd1L, 0x00060600fd002ffaL, 0x004006000c009010L,
        0x001a002042008040L, 0x001a00600fd1ffc0L, 0x000d0ace50bf3f8dL, 0x000183a48434efd1L,
        0x001fbd7670982a0dL, 0x000fe24301d81a0fL, 0x0007fbf82f040041L, 0x000040c800008200L,
        0x007fe17018086006L, 0x003b7ddf0ffe1effL, 0x001f92f861df4a0aL, 0x000fd713ad98a289L,
        0x000fd6aa751e400cL, 0x0007f2a63ae9600cL, 0x0003ff7dfe0e3f00L, 0x000003fd2704ce04L,
        0x00007fc421601d40L, 0x007fff5f70900120L, 0x003fa66283556403L, 0x001fe31969aec201L,
        0x0007fdfc18ac14bbL, 0x0003fb96fb568a47L, 0x000003f72ea4954dL, 0x00000003f8dc0383L,
        0x0000007f3a814490L, 0x00007dc5c9cf62a6L, 0x007f23d3342897acL, 0x003fee36eee1565cL,
        0x0003ff3e99fcccc7L, 0x000003ecfcfac5feL, 0x00000003f97f7453L, 0x0000000003f8dc03L,
        0x000000007efa8146L, 0x0000007ed3e2ef60L, 0x00007f47243adcd6L, 0x007fb65afabfb3b5L
    };
    
    private static final long createPattern(int i, long mask) {
        long ret = 0L;
        for (int j = 0; ; j++) {
            long nextMask = mask & (mask - 1);
            long bit = mask ^ nextMask;
            if ((i & (1L << j)) != 0)
                ret |= bit;
            mask = nextMask;
            if (mask == 0)
                break;
        }
        return ret;
    }
    
    private static final long addRookRays(int x, int y, long occupied, boolean inner) {
        long mask = 0;
        mask = addRay(mask, x, y,  1,  0, occupied, inner);
        mask = addRay(mask, x, y, -1,  0, occupied, inner);
        mask = addRay(mask, x, y,  0,  1, occupied, inner);
        mask = addRay(mask, x, y,  0, -1, occupied, inner);
        return mask;
    }
    
    private static final long addBishopRays(int x, int y, long occupied, boolean inner) {
        long mask = 0;
        mask = addRay(mask, x, y,  1,  1, occupied, inner);
        mask = addRay(mask, x, y, -1, -1, occupied, inner);
        mask = addRay(mask, x, y,  1, -1, occupied, inner);
        mask = addRay(mask, x, y, -1,  1, occupied, inner);
        return mask;
    }

    private static final long addRay(long mask, int x, int y, int dx, int dy, 
                                     long occupied, boolean inner) {
        int lo = inner ? 1 : 0;
        int hi = inner ? 6 : 7;
        while (true) {
            if (dx != 0) {
                x += dx; if ((x < lo) || (x > hi)) break;
            }
            if (dy != 0) {
                y += dy; if ((y < lo) || (y > hi)) break;
            }
            int sq = xy2sq(x, y);
            mask |= 1L << sq;
            if ((occupied & (1L << sq)) != 0)
                break;
        }
        return mask;
    }

    static{
    	int tot=0;
    	int off=0;
    	Arrays.fill(magics, -1);
	    for (int sq = 0; sq < 64; sq++) {
	        int x = sq % 8;
	        int y = sq / 8;
            {
    		    // Rook magics
                int bits = rBits[sq];
				long mask = addRookRays(x, y, 0L, true);
		        long magix = rMagics[sq];
				magics[tot++]=magix;
		        magics[tot++]=mask;
		        rOffsets[sq]=tot;
		        offsets[off++]=tot;
		        offsets[off++]=64 - bits;
		        int nPatterns = 1 << Long.bitCount(mask);
		        for (int i = 0; i < nPatterns; i++) {
		            long p = createPattern(i, mask);
					magics[tot+(int)((p * magix) >>> 64 - bits)] = addRookRays(x, y, p, false);
		        }
		        tot+=1 << bits;
            }
            {
    		    // Bishop magics
                int bits = bBits[sq];
				long mask = addBishopRays(x, y, 0L, true);
		        long magix = bMagics[sq];
				magics[tot++]=magix;
		        magics[tot++]=mask;
		        bOffsets[sq]=tot;
		        offsets[off++]=tot;
		        offsets[off++]=64 - bits;
		        int nPatterns = 1 << Long.bitCount(mask);
		        for (int i = 0; i < nPatterns; i++) {
		            long p = createPattern(i, mask);
					magics[tot+(int)((p * magix) >>> 64 - bits)] = addBishopRays(x, y, p, false);
		        }
		        tot+=1 << bits;
            }
	    }
    }
    public static final long rookAttacks(int sq, long occupied) {		return magicAtks(occupied, sq*4);    }

    public static final long bishopAttacks(int sq, long occupied) {
		return magicAtks(occupied, sq*4+2);
    }

	private final static long magicAtks(long occupied, int i) {
		int offset = offsets[i];
        return magics[offset+(int)(((occupied & magics[offset-1]) * magics[offset-2]) >>> offsets[i+1])];
	}
}