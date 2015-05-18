#include <jni.h>
#include <stdio.h>
#include "JNImovegen.h"

JNIEXPORT void JNICALL Java_jni_JNImovegen_movegen(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}
#define NUM_OFFSETS 64*4
#define NUM_MAGICS 90624+128+4800+128
int offsets[NUM_OFFSETS];
unsigned long long magics[NUM_MAGICS];

JNIEXPORT void JNICALL Java_jni_JNImovegen_copyMagic(JNIEnv *env, jobject thisObj, jintArray joffsets, jlongArray jmagics) {
	jint *toffsets = (*env)->GetIntArrayElements(env, joffsets, NULL);
	int i=0;
	for(i=0;i<NUM_OFFSETS;i++){
		offsets[i]=(int) toffsets[i];
	}
	jlong *tmagics = (*env)->GetLongArrayElements(env, jmagics, NULL);
	for(i=0;i<NUM_MAGICS;i++){
		magics[i]=(unsigned long long) tmagics[i];
	}
	return;
}

JNIEXPORT jlong JNICALL Java_jni_JNImovegen_magicAtks(JNIEnv *env, jobject thisObj, jlong occupied, jint i){
	int offset = offsets[i];
	int bits = offsets[i+1];
	unsigned long long magic = magics[offset-2];
	unsigned long long mask = magics[offset-1];
	return magics[offset + (int)(((occupied & mask) * magic) >> bits)];
}

