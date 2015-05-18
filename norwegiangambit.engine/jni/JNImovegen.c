#include <jni.h>
#include <stdio.h>
#include "JNImovegen.h"

JNIEXPORT void JNICALL Java_jni_JNImovegen_movegen(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}

jint *offsets;
jlong *magics;

JNIEXPORT void JNICALL Java_jni_JNImovegen_copyMagic(JNIEnv *env, jobject thisObj, jintArray joffsets, jlongArray jmagics) {
	offsets = (*env)->GetIntArrayElements(env, joffsets, NULL);
	magics = (*env)->GetLongArrayElements(env, jmagics, NULL);
	return;
}

JNIEXPORT jlong JNICALL Java_jni_JNImovegen_magicAtks(JNIEnv *env, jobject thisObj, jlong occupied, jint i){
	int offset = (int) offsets[i];
	int bits = (int) offsets[i+1];
	unsigned long long magic = (unsigned long long) magics[offset-2];
	unsigned long long mask = (unsigned long long) magics[offset-1];
	return magics[offset + (int)(((occupied & mask) * magic) >> bits)];
}

