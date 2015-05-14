


public class JNImovegen {
	   static {
		      System.loadLibrary("jni/movegen"); // hello.dll (Windows) or libhello.so (Unixes)
		   }
		 
		   // Declare native method
		   private native void movegen();
		 
		   // Test Driver
		   public static void main(String[] args) {
		      new JNImovegen().movegen();  // invoke the native method
		   }

}
