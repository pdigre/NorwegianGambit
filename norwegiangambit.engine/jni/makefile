# Define a variable for classpath
CLASS_PATH = ../bin

# Define a virtual path for .class in the bin directory
vpath %.class $(CLASS_PATH)

all : movegen.dll

# $@ matches the target, $< matches the first dependancy
movegen.dll : JNImovegen.o
	gcc -Wl,--add-stdcall-alias -shared -o $@ $<

# $@ matches the target, $< matches the first dependancy
JNImovegen.o : JNImovegen.c JNImovegen.h
	gcc -I"C:\Program Files\Java\jdk1.8.0_05\include" -I"C:\Program Files\Java\jdk1.8.0_05\include\win32" -c $< -o $@

# $* matches the target filename without the extension
JNImovegen.h : JNImovegen.class
	javah -classpath $(CLASS_PATH) $*

clean :
	rm JNImovegen.h JNImovegen.o movegen.dll
