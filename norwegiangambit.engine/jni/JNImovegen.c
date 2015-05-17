#include <jni.h>
#include <stdio.h>
#include "JNImovegen.h"

JNIEXPORT void JNICALL Java_JNImovegen_movegen(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}

jint *offsets;
jlong *magics;

JNIEXPORT void JNICALL Java_JNImovegen_copyMagic(JNIEnv *env, jobject thisObj, jintArray joffsets, jlongArray jmagics) {
	offsets = (*env)->GetIntArrayElements(env, joffsets, NULL);
	magics = (*env)->GetLongArrayElements(env, jmagics, NULL);
	return;
}

