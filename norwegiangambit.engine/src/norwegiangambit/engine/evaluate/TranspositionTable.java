package norwegiangambit.engine.evaluate;


public class TranspositionTable {

	/*
DATA-BITS 64
============
2   Type    
1   Slot 1 or 0
5   Depth <32
8   generation <256
16  MoveData

16  evalscore 64k
16  score 64k

KEY 64
======
Zobrist ^ bit1

	 */
	
	final public static int TTLog2SIZE = 24;   // 10= 1024, 20= 1 million,
	final public static int TTSIZE=1<<TTLog2SIZE;
	final public static int TTMASK=TTSIZE-1;
	final public static int currGen = 0; // Generation number

	final public static int T_EMPTY=0;
	final public static int T_GE=1<<6;
	final public static int T_LE=2<<6;
	final public static int T_EXACT=3<<6;
	final public static int T_TYPEMASK=3<<6;
	final public static int T_GENMASK=0xFF00;
	final public static long T_DEPTHMASK=31L;
	final public static long T_MOVEMASK=0xFFFF0000L;
	final public static long T_SCORE1MASK=0x0000FFFF00000000L;
	final public static long T_SCORE2MASK=0xFFFF000000000000L;
	final public static long T_COUNTMASK =0xFFFFFFFFFFFFFF00L;

	final public static long[] hash=new long[TTSIZE];
	final public static long[] data=new long[TTSIZE];
	final public static long[] validate=new long[TTSIZE];

    public static final int getType(long data){
    	return ((int)data)&T_TYPEMASK;
    }
    
    public static final long setType(long data,int type){
    	return (data&~((long)T_TYPEMASK))^(long)type;
    }
    
	public static boolean isExact(long data) {
		return ((int)data&T_TYPEMASK)==T_EXACT;
	}

    public static final int getScore(long data){
    	return ((int)((data&T_SCORE1MASK)>>32))-32000;
    }
    
    public static final long setScore(long data,int score){
    	long s=score+32000;
    	return (data&~((long)T_SCORE1MASK))^(s<<32);
    }
    
    public static final int getGeneration(long data){
    	return (((int)data)&T_GENMASK)>>8;
    }
    
    public static final long setGeneration(long data,int gen){
    	return (data&~((long)T_GENMASK))^(gen<<8);
    }
    
    public static final long getCount(long data){
    	return data>>8;
    }
    
    public static final long setCount(long data,long count){
    	return (count<<8)^(data&~T_COUNTMASK);
    }
    
    public static final int getMove(long data){
    	return (int) ((data&T_MOVEMASK)>>16);
    }
    
    final public static long setMove(long data,int move){
    	return (data&~((long)T_MOVEMASK))^(long)(move<<16);
    }
    
    final public static int getDepth(long data) {
        return (int) (data&T_DEPTHMASK);
    }

    final public static long setDepth(long data,int depth) {
    	return (data&~((long)T_DEPTHMASK))^(long)depth;
    }

	final public static int get(long zobrist, long key2) {
		int i1=(int)zobrist&TTMASK;
		if(hash[i1]==(zobrist^key2)){
			if(validate[i1]!=key2){
//				System.out.println("Key collision:");
				return -1;
			}
			return i1;
		}
		i1=(int)((zobrist>>TTLog2SIZE)&TTMASK);
		if(hash[i1]==(zobrist^key2)){
			if(validate[i1]!=key2){
//				System.out.println("Key collision:");
				return -1;
			}
			return i1;
		}
		return -1;
	}

	final public static int set(long zobrist,long key2, int depth, int type, int md, int score) {
		int i1=(int)(zobrist&TTMASK);
		long s=((long)(score + 32000))<<32;
		long m=((long)md) << 16;
		long val = ((long)(depth | type)) | m | s;
		long hash0 = zobrist^key2;
		int i2=(int)((zobrist>>TTLog2SIZE)&TTMASK);
		boolean better = isBetter(data[i1],type,depth);
		if(better){
			// Add to best slot
			if(hash[i1]!=hash0){
				// Push 1 down
				long data1=data[i1];
				if(isBetter(data[i2],getType(data1),getDepth(data1))){
					validate[i2]=validate[i1];
					hash[i2]=hash[i1];
					data[i2]=data1;
				}
			}
			validate[i1]=key2;
			hash[i1]=hash0;
			data[i1]=val;
			return i1;
		} else {
			if(hash[i1]==hash0)
				return i1;
			if(isBetter(data[i2],type,depth)){
				validate[i2]=key2;
				hash[i2]=hash0;
				data[i2]=val;
			}
			return i2;
		}
	}
	
    private static boolean isBetter(long other,int type,int depth) {
    	if(getGeneration(other)!=currGen)
    		return true;
    	int type2 = getType(other);
    	if((type==T_EXACT) !=(type2==T_EXACT))
    		return type==T_EXACT;
		return depth>=getDepth(other);
	}

	final public static int set(long zobrist,long key2, int depth, long count) {
		int i=(int)(zobrist&TTMASK);
		hash[i]=zobrist^key2;
		validate[i]=key2;
		long s=count<<8;
		data[i]=((long)depth) | s;
		return i;
	}
	
}
