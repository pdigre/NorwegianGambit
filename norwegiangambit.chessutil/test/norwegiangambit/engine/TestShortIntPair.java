package norwegiangambit.engine;

import static org.junit.Assert.assertEquals;
import norwegiangambit.util.BITS;

import org.junit.Test;

public class TestShortIntPair extends BITS{


	public class TestS{

		int b;
		TestS(int low,int high){
			b=S(low, high);
		}
		public void plus(TestS s){
			b+=SS(s.b);
		}
		public void minus(TestS s){
			b-=SS(s.b);
		}
		public int high(){
			return E(b);
		}
		public int low(){
			return M(b);
		}

		@Override
		public String toString() {
			return "S("+low()+":"+high()+")";
		}
	}
	
	@Test
	public void test() {
		// Test ADD
		TestS s1 = new TestS(150,-150);
		TestS s2 = new TestS(-160,160);
		TestS s3 = new TestS(30000,-30000);
		assertEquals(s1.toString(),"S(150:-150)");
		s1.plus(s2);
		assertEquals(s1.toString(),"S(-10:10)");
		s1.minus(s2);
		assertEquals(s1.toString(),"S(150:-150)");
		s1.minus(s2);
		assertEquals(s1.toString(),"S(310:-310)");
		s1.plus(s3);
		assertEquals(s1.toString(),"S(30310:-30310)");
		s1.minus(s3);
		assertEquals(s1.toString(),"S(310:-310)");
		s1.minus(s3);
		assertEquals(s1.toString(),"S(-29690:29690)");
		s1.plus(s3);
		assertEquals(s1.toString(),"S(310:-310)");
	}

}
