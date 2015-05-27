package norwegiangambit.engine;


public interface IMovedata {
	// Pawn moves
	int MD_P2=0; 		// 8 lanes Opening for enpassant
	int MD_PEL=8;   	// 8 lanes enpassant left
	int MD_PER=16;  	// 8 lanes enpassant right
	int MD_PP=24;   	// 32 - 8 lanes * 4 types
	int MD_PPL=56;  	// 160 - 8 lanes * 20 types - promotion left
	int MD_PPR=216;  	// 160- 8 lanes * 20 types - promotion right
	int MD_P1=376;  	// 64 move 1 step
	int MD_PCL=440;		// 320 - 64*5 types - left
	int MD_PCR=760;		// 320 - 64*5 types - right
	int MD_PQ=1080;		// 4 - promotion 4 types
	int MD_PK=1084; 	// 4 - promotion 4 types

		// Castling
	int MD_KCQ=1088;
	int MD_KCK=1089;
	int MD_KCQ2=1090;
	int MD_KCK2=1091;
	int	MD_KX=1092;		// 32 King moves - could castle both sides
	int MD_KXQ=1122;	// 32 King moves - could castle queen sides
	int MD_KXK=1152;	// 32 King moves - could castle king sides
	int MD_RQ=1182;	// 86 - Rook move - could castle	
	int MD_RK=1268;	// 86 - Rook move - could castle	
	

	// Simple - King & kNight
	// B->E (Q,K follows)
	
	// Sliders
	// 4/8* (B->E) (Q,K follows)

	int[] MD_K=new int[128];	// 64 - Simple set (B[64] E[64])
	int[] MD_N=new int[128];	// 64 - Simple set (B[64] E[64])

	int[] MD_Q=new int[1024];	// 64 - 8Slider set (B[64*8] E[64*8])
	int[] MD_B=new int[512];	// 64 - 4Slider set (B[64*4] E[64*4])
	int[] MD_R=new int[512];	// 64 - 4Slider set (B[64*4] E[64*4])

	int[] MD_RQW=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	int[] MD_RKW=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	int[] MD_RQB=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	int[] MD_RKB=new int[8];  	// 1 - 4Slider set  (B[4] E[4])
	
	//     1 0         NULL-MOVE
	//     8 1-8       ENPASSANT
	//   366 9-374     BREAKERS
	// 22724 375-23099 NORMAL
	
	int ENP_END=2000;
	int BRK_END=3000;
	int BLACK_OFFSET=32*1024; 
	int SIZE=BLACK_OFFSET*2; 
	
	final public static int MASK_32K=0x00007FFF;
	final public static int MOVEDATASIZE=64*1024; // 64k
	final public static long[] BITMAP=new long[MOVEDATASIZE];
	final public static long[] BOCCUPIED=new long[MOVEDATASIZE];
	final public static long[] AMINOR=new long[MOVEDATASIZE];
	final public static long[] AMAJOR=new long[MOVEDATASIZE];
	final public static long[] ASLIDER=new long[MOVEDATASIZE];
	final public static long[] BTO=new long[MOVEDATASIZE];
	final public static int[] MESCORE=new int[MOVEDATASIZE];
	final public static long[] ZOBRIST=new long[MOVEDATASIZE];
	final public static long[] PAWNHASH=new long[MOVEDATASIZE];

}
