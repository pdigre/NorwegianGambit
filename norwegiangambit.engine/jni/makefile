# Define a variable for classpath
CLASS_PATH = ../bin;../../norwegiangambit.chessutil/bin

# Define a virtual path for .class in the bin directory
vpath %.class $(CLASS_PATH)

all : movegen.dll

# $@ matches the target, $< matches the first dependancy
movegen.dll : Movegen.o
	gcc -Wl,--add-stdcall-alias -shared -o $@ $<

# $@ matches the target, $< matches the first dependancy
Movegen.o : Movegen.c Movegen.h
	gcc -I"C:\Program Files\Java\jdk1.8.0_05\include" -I"C:\Program Files\Java\jdk1.8.0_05\include\win32" -c $< -o $@

# $* matches the target filename without the extension
Movegen.h : ../bin/norwegiangambit/engine/Movegen.class
	javah -classpath "$(CLASS_PATH)" -o Movegen.h norwegiangambit.engine.Movegen

clean :
	rm Movegen.h Movegen.o movegen.dll
