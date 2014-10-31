package norwegiangambit.util.polyglot;

import norwegiangambit.util.IConst;

/**
 * Public domain code for JLKISS64 RNG - long period KISS RNG producing 64-bit
 * results. <br>
 * by David Jones: www.cs.ucl.ac.uk/staff/d.jones/GoodPracticeRNG.pdf
 */
public class ZobristJLKISS64 implements IZobristKey,IConst {

	
	public final static long[] keys = new long[781];
	
	static{
		for (int i = 0; i < 781; i++)
			keys[i]=rand64();
	}

	static long x = 123456789123L, y = 987654321987L; 
   	static long z1 = 43219876, c1 = 6543217, z2 = 21987643, c2 = 1732654; 

   	public static long rand64(){ 
	   // seed variables 
		x = 1490024343005336237L * x + 123456789; 
	   	y ^= y << 21; y ^= y >> 17; y ^= y << 30;   // do not set y=0 

	   	long t; 
	   	t = 4294584393L * z1 + c1; 
	   	c1 = t >> 32; 
	   	z1 = t; 
	   	t = 4246477509L * z2 + c2; 
	   	c2 = t >> 32; 
		z2 = t; 
	   return x + y + z1 + ((long)z2 << 32);   // return 64-bit result 
	} 
	public final static int[] INDEX=new int[16];
	
	static{
		int[] pt=new int[]{BP,WP,BN,WN,BB,WB,BR,WR,BQ,WQ,BK,WK};
		for (int i = 0; i < pt.length; i++)
			INDEX[pt[i]]=i*64;
	}

	@Override
	public long getKey(boolean isWhite, long castling, int enpassant, int[] brd) {
		// Castling
		long key = keyCastling(castling);

		for (int i = 0; i < 64; i++) {
			int piece = brd[i];
			if(piece!=0)
				key ^= get(piece,i);
		}

		// passant flags only when pawn can capture
		if (enpassant > 0) {
			int file = enpassant & 7;
			if (isWhite) {
				if (file != 0 && brd[enpassant - 9] == WP) {
					key ^= keys[ZOBRIST_ENP+file];
				} else if (file != 7 && brd[enpassant - 7] == WP) {
					key ^= keys[ZOBRIST_ENP+file];
				}
			} else {
				if (file != 0 && brd[enpassant + 7] == BP) {
					key ^= keys[ZOBRIST_ENP+file];
				} else if (file != 7 && brd[enpassant + 9] == BP) {
					key ^= keys[ZOBRIST_ENP+file];
				}
			}
		}
		if (isWhite)
			key ^= keys[ZOBRIST_NXT];
		return key;
	}

	@Override
	public long keyCastling(long castling) {
		long key = 0;
		if ((castling & CANCASTLE_WHITEKING) != 0)
			key ^= keys[ZOBRIST_CWK];
		if ((castling & CANCASTLE_WHITEQUEEN) != 0)
			key ^= keys[ZOBRIST_CWQ];
		if ((castling & CANCASTLE_BLACKKING) != 0)
			key ^= keys[ZOBRIST_CBK];
		if ((castling & CANCASTLE_BLACKQUEEN) != 0)
			key ^= keys[ZOBRIST_CBQ];
		return key;
	}

	@Override
	final public long get(int piece, int sq) {
		return keys[INDEX[piece]+sq];
	}

	@Override
	public long get(int i) {
		return keys[i];
	}
}
