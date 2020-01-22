package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		System.out.println("Test 1 complete");
		test2();
		System.out.println("Test 2 complete");
		test3();
		System.out.println("Test 3 complete");
		test4();
		
	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);

		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		
		
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
//		Polynom_able pp1 = Polynom.parse(s1);
//		System.out.println("from string: "+pp1);
	}
	
	public static void test3()
	{
		
		//Substract testing
		Monom m5 = new Monom("x^5");
		System.out.println(m5);
		Polynom p1 = new Polynom("3x");
		p1.substract(Monom.ZERO);
		
		System.out.println("p1: " + p1);
		Polynom p2 = new Polynom("-3x + 1");
		
		p1.substract(p2);
		System.out.println("p1: " + p1);	
		p1.add(p2);
		System.out.println("p1: " + p1);
		
		Polynom p10 = new Polynom("x^5");
		System.out.println(p10);
		Monom m2 = new Monom("x^5");
		p10.add(m2);
		System.out.println("after adding x^5: " + p10);
		p10.substract(m2);
		System.out.println("substracting: " + p10);
		p10.substract(m2);
		System.out.println("substracting: " + p10);
		p10.substract(m2);
		System.out.println("substracting: " + p10);
		p10.add(m2);
		System.out.println("adding: " + p10);
		
		
		Polynom zero = new Polynom();
		
		
		if(!zero.equals(p10))
		{
			System.out.println("This shouldn't print");
		}

		Polynom p20 = new Polynom("3x+ 2");
		System.out.println("p20: " + p20);

		p20.substract(Monom.ZERO);
		System.out.println("p20: " + p20);

		p20.add(Monom.ZERO);
		System.out.println("p20: " + p20);

		p20.multiply(Monom.ZERO);
		System.out.println("p20: " + p20);

		Polynom p30 = new Polynom("3x - 0");
		System.out.println("p30: " + p30);
		Polynom p31 = new Polynom("-0");
		System.out.println("p31: " + p31);


	}
	
	public static void test4()
	{
		//Testing f function
		Polynom p1 = new Polynom("4x^2 + 5");
		System.out.println("f(0) of 4x^2 + 5 = " + p1.f(0));
		System.out.println("f(1) of 4x^2 + 5 = " + p1.f(1));
		System.out.println("f(-1) of 4x^2 + 5 = " + p1.f(-1));
		System.out.println(p1);
		
		Polynom p2 = new Polynom("5");
		
		
		//test area
		//System.out.println(p2.area(-2, 3, Monom.EPSILON)+"\n");
		
		//size test
		System.out.println("Test size---start");
		Polynom p0 = new Polynom(" ");
		System.out.println("The size of p0 = " + p0.size());
		System.out.println("The size of p1 = " + p1.size());
		System.out.println("Test size---end \n");
		
		//reverse test
		System.out.println("Test reverse---start");
		Polynom p01 = new Polynom("12 + x^3 + 4x^6");
		System.out.println("before being reveresed " + p01);
		p01.reverse();
		System.out.println("reversed p01 " + p01);				
		System.out.println("Test reverse---end\n");
		
		//equals test
		System.out.println("Test equals---start");
		Polynom p11 = new Polynom("3x + 4");
		Polynom p12 = new Polynom("3x + 4");
		System.out.println("Test equals---start");
		System.out.println("Does p0 is equal to p2 ?\n " + p0.equals(p2));
		System.out.println("Does p11 is equal to p12 ?\n " + p11.equals(p12));
		System.out.println("Test equals---end\n");
		
		//isZero test
		System.out.println("Test isZero---start");
		System.out.println(p0.isZero());
		Polynom p00 = new Polynom("0");
		System.out.println(p2.isZero());
		System.out.println(p00.isZero());		
		System.out.println("Test isZero---end\n");
		//root test
		
		// copy test
		System.out.println("Test copy---start");
		Polynom_able p1c = p1.copy();
		System.out.println(p1c.equals(p1));		
		System.out.println("Test copy---end\n");
		
		// derivative test
		System.out.println(p01);
		p01.derivative();
		p0.derivative();
		p2.derivative();
		System.out.println("after the derivative function on p01 = " + p01);
		System.out.println("after the derivative function on p0 = " + p0);
		System.out.println("after the derivative function on p2 = " + p2);
		//test 
		
		//tostring test
		
	}
}
