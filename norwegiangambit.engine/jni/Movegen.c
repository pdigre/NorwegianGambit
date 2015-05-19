#include <jni.h>
#include <stdio.h>
#include "Movegen.h"

JNIEXPORT void JNICALL Java_norwegiangambit_engine_Movegen_movegen(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}
typedef unsigned long long u64;
#define NUM_OFFSETS 64*4
#define NUM_MAGICS 90624+128+4800+128
#define MaskAFile 0x0101010101010101LL
#define MaskHFile 0x8080808080808080LL
#define MaskBToHFiles ~MaskAFile
#define MaskAToGFiles ~MaskHFile
int offsets[NUM_OFFSETS];
u64 magics[NUM_MAGICS];
u64 nmasks[64],kmasks[64];
u64 eOccupied,oOccupied,bOccupied,aOccupied,wOccupied,aPawns,wPawns,bPawns,aKnights,aKings,aBishops,aRooks,aQueens,eAttacked,aMinor,aMajor,aSlider,aDiag,aLine;
u64 wPawnAtks,wPawnAtkBy,bPawnAtks,bPawnAtkBy,castling;
int epsq,wKingpos,bKingpos,oKingpos,eKingpos;
unsigned char wNext;

jclass thisClass;
jfieldID ideAttacked,ideOccupied,idaKnights,idaKings,idaBishops,idaRooks,idaQueens;

JNIEXPORT void JNICALL Java_norwegiangambit_engine_Movegen_copyMaps(JNIEnv *env, jobject thisObj, jlongArray jnmasks, jlongArray jkmasks){
	jlong *tnmasks = (*env)->GetLongArrayElements(env, jnmasks, NULL);
	int i=0;
	for(i=0;i<64;i++){
		nmasks[i] = (u64) tnmasks[i];
	}
	jlong *tkmasks = (*env)->GetLongArrayElements(env, jkmasks, NULL);
	for(i=0;i<64;i++){
		kmasks[i] = (u64) tkmasks[i];
	}
	thisClass = (*env)->GetObjectClass(env, thisObj);
	ideAttacked=(*env)->GetFieldID(env, thisClass, "eAttacked", "J");
	ideOccupied = (*env)->GetFieldID(env, thisClass, "eOccupied", "J");
	idaKnights = (*env)->GetFieldID(env, thisClass, "aKnights", "J");
	idaKings = (*env)->GetFieldID(env, thisClass, "aKings", "J");
	idaBishops = (*env)->GetFieldID(env, thisClass, "aBishops", "J");
	idaRooks = (*env)->GetFieldID(env, thisClass, "aRooks", "J");
	idaQueens = (*env)->GetFieldID(env, thisClass, "aQueens", "J");
}

JNIEXPORT void JNICALL Java_norwegiangambit_engine_Movegen_copyMagic(JNIEnv *env, jobject thisObj, jintArray joffsets, jlongArray jmagics) {
	jint *toffsets = (*env)->GetIntArrayElements(env, joffsets, NULL);
	int i=0;
	for(i=0;i<NUM_OFFSETS;i++)
		offsets[i]=(int) toffsets[i];
	jlong *tmagics = (*env)->GetLongArrayElements(env, jmagics, NULL);
	for(i=0;i<NUM_MAGICS;i++)
		magics[i]=(u64) tmagics[i];
	return;
}

inline u64 magicAtks(u64 occupied,int i){
	int offset = offsets[i];
	return magics[offset + (int)(((occupied & magics[offset-1]) * magics[offset-2]) >> offsets[i+1])];
}

JNIEXPORT void JNICALL Java_norwegiangambit_engine_Movegen_initVariables
  (JNIEnv *env, jobject thisObj, jboolean jwNext, jlong jaMinor, jlong jaMajor, jlong jaSlider, jlong jbOccupied, jint jepsq, jlong jcastling){
	wNext       = (unsigned char) jwNext;
	aMinor      = (u64) jaMinor;
	aMajor      = (u64) jaMajor;
	aSlider     = (u64) jaSlider;
	bOccupied   = (u64) jbOccupied;
	epsq        = (int) jepsq;
	castling    = (u64) jcastling;

	aOccupied 	= aMinor | aMajor | aSlider;
	wOccupied 	= aOccupied ^ bOccupied;
	aPawns 		=  aMinor & ~aMajor & ~aSlider;
	aKnights 	= ~aMinor &  aMajor & ~aSlider;
	aKings 		=  aMinor &  aMajor & ~aSlider;
	aDiag 		=  aMinor           &  aSlider;
	aLine	 	=            aMajor &  aSlider;
	aBishops 	=  aMinor & ~aMajor &  aSlider;
	aRooks 		= ~aMinor &  aMajor &  aSlider;
	aQueens 	=  aMinor &  aMajor &  aSlider;
	bKingpos    = tzcnt(aKings & bOccupied);
	wKingpos    = tzcnt(aKings & wOccupied);

	oKingpos 	= wNext?wKingpos:bKingpos;
	eKingpos 	= wNext?bKingpos:wKingpos;
	oOccupied	= wNext?wOccupied:bOccupied;
	eOccupied	= wNext?bOccupied:wOccupied;
	wPawns   	= wOccupied & aPawns;
    bPawns   	= bOccupied & aPawns;
	wPawnAtks 	= (((wPawns & MaskBToHFiles) << 7) | ((wPawns & MaskAToGFiles) << 9));  // Left - Right
	bPawnAtks 	= (((bPawns & MaskBToHFiles) >> 9) | ((bPawns & MaskAToGFiles) >> 7));  // Left - Right
	wPawnAtkBy 	= (((bPawns & MaskBToHFiles) >> 9) | ((bPawns & MaskAToGFiles) >> 7));  // Left - Right
	bPawnAtkBy 	= (((wPawns & MaskBToHFiles) << 7) | ((wPawns & MaskAToGFiles) << 9));  // Left - Right
}


JNIEXPORT jlong JNICALL Java_norwegiangambit_engine_Movegen_magicAtks(JNIEnv *env, jobject thisObj, jlong occupied, jint i){
	return (jlong) magicAtks((u64) occupied, (int)i);
}

JNIEXPORT jlong JNICALL Java_norwegiangambit_engine_Movegen_enemyAttacks(JNIEnv *env, jobject this, jlong jeAttacked, jlong jpcs){
//	u64 eOccupied,aKnights,aKings,aBishops,aRooks,aQueens;
	u64 pcs=(u64)jpcs;
	u64 eAttacked=(u64)jeAttacked;
	u64 eOccupied = (u64)(*env)->GetLongField(env, this,ideOccupied);
	u64 aKnights = (u64)(*env)->GetLongField(env, this,idaKnights);
	u64 aKings = (u64)(*env)->GetLongField(env, this,idaKings);
	u64 aBishops = (u64)(*env)->GetLongField(env, this,idaBishops);
	u64 aRooks = (u64)(*env)->GetLongField(env, this,idaRooks);
	u64 aQueens = (u64)(*env)->GetLongField(env, this,idaQueens);

//	u64 pcs=aOccupied&~(oOccupied &  aKings);
//	u64 eAttacked=wNext?wPawnAtkBy:bPawnAtkBy;

	{
		u64 m=eOccupied & aKnights;
		while(m!=0){
			int sq = tzcnt(m);
			eAttacked|=nmasks[sq];
			m &= m-1;
		}
	}
	{
		u64 m=eOccupied & aKings;
		while(m!=0){
			int sq = tzcnt(m);
			eAttacked|=kmasks[sq];
			m &= m-1;
		}
	}
	{
		u64 m=eOccupied & aBishops;
		while(m!=0){
			int sq = tzcnt(m);
			eAttacked|=magicAtks(pcs,sq*4+2);
			m &= m-1;
		}
	}
	{
		u64 m=eOccupied & aRooks;
		while(m!=0){
			int sq = tzcnt(m);
			eAttacked|=magicAtks(pcs,sq*4);
			m &= m-1;
		}
	}
	{
		u64 m=eOccupied & aQueens;
		while(m!=0){
			int sq = tzcnt(m);
			eAttacked|=magicAtks(pcs,sq*4+2);
			eAttacked|=magicAtks(pcs,sq*4);
			m &= m-1;
		}
	}
	return (jlong) eAttacked;
}

JNIEXPORT jint JNICALL Java_norwegiangambit_engine_Movegen_tzcnt2(JNIEnv *env, jobject this, jlong jb){
	return (jint) tzcnt((u64)jb);
}

inline int tzcnt(u64 b) {
    u64 sq;
    asm (
    	"tzcnt %0, %1;"
        : "=a" (sq)
        : "a" (b));
	return (int) sq;
}

/*
int lsb(u64 b) {
	int temp;
	__asm__(
	      ".intel_syntax;"
	      "mov eax, %1;"
	      "mov %0, eax;"
	      ".att_syntax;"
	      : "=r"(temp)
	      : "r"(1)
	      : "eax");

	printf("%d\n", temp);

	__asm__
	        (
	           ".intel_syntax;"
	           "tzcnt eax;"
	    );
	 return (int) b;
}
*/

