#include <jni.h>
#include <stdio.h>
#include "JNImovegen.h"

JNIEXPORT void JNICALL Java_JNImovegen_movegen(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}
