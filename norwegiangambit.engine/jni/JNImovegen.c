#include <jni.h>
#include <stdio.h>
#include "JNImovegen.h"

JNIEXPORT void JNICALL Java_JNImovegen_movegen(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}

jlong *bMasks, *bMagics;
jint *bBits;
jint *bBits;

JNIEXPORT void JNICALL Java_JNImovegen_copyMagic(JNIEnv *env, jobject thisObj,
		jlongArray jbMasks, jintArray jbBits, jobjectArray jbTables, jlongArray jbMagics,
		jlongArray jrMasks, jintArray jrBits, jobjectArray jrTables, jlongArray jrMagics) {
	*bMasks = (*env)->GetLongArrayElements(env, jbMasks, NULL);
	*bMasks = (*env)->GetIntArrayElements(env, jbMasks, NULL);
	*bMasks = (*env)->GetIntArrayElements(env, jbMasks, NULL);
	*bMasks = (*env)->GetIntArrayElements(env, jbMasks, NULL);

	printf("Hello World!\n");
	return;
}

