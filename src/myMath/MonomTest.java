 package myMath;
import java.util.ArrayList;

import Ex1.Monom;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
//		test1();
//		test2();		
//		test3();
		test4();
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString(); // 0
			Monom m1 = new Monom(s); // 0
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}
	
	public static void test3()
	{
		Monom m2 = new Monom("-0");
		System.out.println(m2);
		
		Monom m3 = new Monom("0");
		System.out.println(m3);
		
		Monom m1 = new Monom("1");
		//add test
		
		System.out.println(m1);
		m1.add(m2);
		System.out.println(m1);
		m1.substract(m3);
		System.out.println(m1);
		
		
		Monom m4 = new Monom("3x");
		System.out.println(m4);
		m4.substract(m1);
		System.out.println(m4);

		
		Monom m5 = new Monom("4x");
		System.out.println(m5);

		m5.add(m4);
		System.out.println(m5);

		m5.substract(m4);
		System.out.println(m5);

		
	}
	
	public static void test4()
	{
		Monom m1 = new Monom(3,3);
		Monom m2 = new Monom("-0");
		Monom m3 = new Monom(2,3);
		Monom m4 = new Monom("x^2");
		Monom m5 = new Monom(2,1);
		
		//test f
		double n = 15;
		m1.f(n);
		System.out.println(m1);
		// test reverse
		System.out.println("Before being reveresed = " + m4);
		System.out.println("Before being reveresed = " + m2);
		m4.reverse();
		m2.reverse();		
		System.out.println("After being reveresed = " + m4);
		System.out.println("After being reveresed = " + m2);
		
		// test toString
		
		// test derivative
		Monom der = m3.derivative();
		System.out.println("After the derivetive function = " + der);		
		
		
	}
}
