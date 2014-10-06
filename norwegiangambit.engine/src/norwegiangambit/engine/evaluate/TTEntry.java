package norwegiangambit.engine.evaluate;

/**
 * hash_64 - zobrist ^ bit1_64
 * data_64 
 * 5 - Depth
 * 1 - Slot
 * 2 - Type
 * 
 * (56 - count for PERFT)
 * 8 - Generation
 * 16 - Move
 * 16 - Score_1
 * 16 - Score_2
 *
 */
public class TTEntry{
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

	public long hash;
	public long data;
//	
//
//	public int generation;
	public int move;
	public int type;
	public int depth;
//	public int evalScore;  // Score from static evaluation 
	public int score;      // Score from search
//	public int depthSlot;  // Search depth (bit 0-14) and hash slot (bit 15).
	public long validate;
    
    public final int getType(){
    	return ((int)data)&T_TYPEMASK;
    }
    
    public final void setType(int type){
    	data=(data&~((long)T_TYPEMASK))^(long)type;
    }
    
	public boolean isExact() {
		return ((int)data&T_TYPEMASK)==T_EXACT;
	}

    public final int getScore(){
    	int s = ((int)((data&T_SCORE1MASK)>>32))-32000;
        if(s!=score)
        	System.out.println("score error");
		return s;
    }
    
    public final void setScore(int score){
    	long s=score+32000;
    	data=(data&~((long)T_SCORE1MASK))^(s<<32);
    }
    
    public final int getGeneration(){
    	return (((int)data)&T_GENMASK)>>8;
    }
    
    public final void setGeneration(int gen){
    	data=(data&~((long)T_GENMASK))^(gen<<8);
    }
    
    public final long getCount(){
    	return data>>8;
    }
    
    public final void setCount(long count){
    	data=(count<<8)^(data&~T_COUNTMASK);
    }
    
    public final int getMove(){
    	int m = (int) ((data&T_MOVEMASK)>>16);
        if(m!=move)
        	System.out.println("move error");
		return m;
    }
    
    public final void setMove(int move){
    	data=(data&~((long)T_MOVEMASK))^(long)(move<<16);
    }
    
    public final int getDepth() {
        int i = (int) (data&T_DEPTHMASK);
        if(i!=depth)
        	System.out.println("depth error");
		return i;
    }

    public final void setDepth(int depth) {
    	data=(data&~((long)T_DEPTHMASK))^(long)depth;
    }

	public void set(int depth, int type, int md, int score, long hash, long validate) {
		this.score=score;
		this.type=type;
		this.depth=depth;
		this.move=md;
		long s=((long)(score + 32000))<<32;
		long m=((long)md) << 16;
		data= ((long)(depth | type)) | m | s;
		this.hash=hash;
		this.validate=validate;
	}

    

}