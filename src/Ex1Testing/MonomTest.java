package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Ex1.Monom;

class MonomTest {

	@Test
	void testZero() {
		Monom m1 = new Monom("0");
		Assertions.assertEquals(m1.isZero(), true);
	}
	void testMult()
	{
		Monom m1 = new Monom("3x");
		Monom m2 = new Monom("3x");
		Monom m3 = new Monom("9x^2");
		m1.multiply(m2);
		Assertions.assertEquals(m1.equals(m3), true);
		
	}
	void testAdd()
	{
		Monom m1 = new Monom("3x");
		Monom m2 = new Monom("3x");
		m1.add(m2);
		
		Monom m3 = new Monom("6x");
		
		Assertions.assertEquals(m3.equals(m1), true);
		
	}
	void testDerivative()
	{
		Monom m1 = new Monom("3x^2");
		Monom m1der = new Monom("6x");
		m1 = m1.derivative();
		Assertions.assertEquals(m1.equals(m1der), true);
	}
	
	void testSubstract()
	{
		Monom m2 = new Monom ("x^2");
		Monom m1 = new Monom("x^2");
		
		m1.substract(m2);
		Assertions.assertEquals(m1.isZero(), true);
	}

}
