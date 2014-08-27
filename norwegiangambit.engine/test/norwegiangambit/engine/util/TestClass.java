package norwegiangambit.engine.util;

import org.junit.Test;

public class TestClass {

	static long k0 = 0b00000000_00000000_00000000_00001010_00010001_00000000_00010001_00001010L;
	static long k1 = 0b00000000_00000000_00000000_00001010_00000000_00000000_00000000_00000000L;
	static long k2 = 0b00000000_00000000_00000000_00000000_00010000_00000000_00000000_00000000L;
	static long p1 = 0b01010101_01010101_01010101_01010101_01010101_01010101_01010101_01010101L;
	static long p2 = 0b00000000_11111111_00000000_11111111_00000000_11111111_00000000_11111111L;
	static long p3 = 0b11001100_11001100_00110011_00110011_11001100_11001100_00110011_00110011L;
	static long p4 = 0b00001111_00001111_00001111_00001111_11110000_11110000_11110000_11110000L;
	static int[] knight = new int[] { 1, 3, 8, 12, 24, 28, 33, 35 };
	

	@Test
	public void test_knight() {
//		long k=k1;
//		boolean b1=(k&p1)!=0;
//		boolean b2=(k&~p1)!=0;
//		boolean b3=(k&p2)!=0;
//		boolean b4=(k&~p2)!=0;
//		boolean b5=(k&p3)!=0;
//		boolean b6=(k&~p3)!=0;
//		boolean c1=b1&b3&b5;
//		boolean c2=b2&b3&b5;
//		boolean c3=b1&b4&b5;
//		boolean c4=b2&b4&b5;
//		boolean c5=b1&b3&b6;
//		boolean c6=b2&b3&b6;
//		boolean c7=b1&b4&b6;
//		boolean c8=b2&b4&b6;
		
//		String txt = Long.lowestOneBit(i)toBinaryString(t);
//		System.out.println(s+" "+ ".0000000.0000000.0000000.0000000".substring(0, 16-txt.length())+txt);
		System.out.println("hi");
	}
}
