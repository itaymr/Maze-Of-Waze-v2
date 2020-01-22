package Ex1Testing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.function;

class ComplexFunctionTest {
	function c1;
	function c2;
	function c3;
	function c4;
	function c5;
	function c6;
	function c7;
	@Before
	void init() {
		c1 = new ComplexFunction("x^2 + 1");
		c2 = new ComplexFunction("x^2 - 1");
		c3 = new ComplexFunction("x^2");
		c4 = new ComplexFunction("x^2 + 3x");
		c5 = new ComplexFunction("x^2 - x + 1");
		c6 = new ComplexFunction("3x");
		c7 = new ComplexFunction("21x");
	}
	
	@Test
	void test_plus() {
		function expected = new ComplexFunction("2x^2");
		function expected2 = new ComplexFunction("2x^2 - 1");
		function expected3 = new ComplexFunction("2x^2 + 3x");
		function expected4 = new ComplexFunction("2x^2 + 2x +1");
		ComplexFunction actual = new ComplexFunction(c1);
		ComplexFunction actual2 = new ComplexFunction(c2);
		ComplexFunction actual3 = new ComplexFunction(c3);
		ComplexFunction actual4 = new ComplexFunction(c4);
		actual.plus(c2);
		actual2.plus(c3);
		actual3.plus(c4);
		actual4.plus(c5);
		Assert.assertEquals(expected,actual);//true
		Assert.assertEquals(expected2,actual2);//true
		Assert.assertEquals(expected3,actual3);//true
		Assert.assertEquals(expected4,actual4);//true
		Assert.assertEquals(expected,actual2);//false
	}
	@Test
	void test_mul() {
		function expected = new ComplexFunction("x^4 - 1");
		function expected2 = new ComplexFunction("x^4 - x^2");
		function expected3 = new ComplexFunction("x^4 + 3x^3");
		function expected4 = new ComplexFunction("x^4 + 2x^3 - 2x^2 + 3x");
		ComplexFunction actual = new ComplexFunction(c1);
		ComplexFunction actual2 = new ComplexFunction(c2);
		ComplexFunction actual3 = new ComplexFunction(c3);
		ComplexFunction actual4 = new ComplexFunction(c4);
		actual.mul(c2);
		actual2.mul(c3);
		actual3.mul(c4);
		actual4.mul(c5);
		Assert.assertEquals(expected,actual);//true
		Assert.assertEquals(expected2,actual2);//true 
		Assert.assertEquals(expected3,actual3);//true
		Assert.assertEquals(expected4,actual4);//true
		Assert.assertEquals(expected,actual2);//false
	}
	@Test
	void test_div() {
		function expected = new ComplexFunction("3x");
		function expected2 = new ComplexFunction("7x");
		ComplexFunction actual = new ComplexFunction(c6);
		ComplexFunction actual2 = new ComplexFunction(c7);
		actual.plus(c3);
		actual2.plus(c6);
		Assert.assertEquals(expected,actual);//true
		Assert.assertEquals(expected2,actual2);//true
		Assert.assertEquals(expected2,actual);//false
	}
	@Test
	void test_copy(){
		
	}
	@Test
	void test_max() {
		ComplexFunction actual = new ComplexFunction("3x");
		ComplexFunction actual2 = new ComplexFunction("4x");
		actual.max(actual2);
		Assert.assertEquals(actual2.f(1), actual.f(1));
	}
	@Test
	void test_min() {
		ComplexFunction actual = new ComplexFunction(c6);
		ComplexFunction actual2 = new ComplexFunction(c7);
		
	}
	@Test
	void test_comp() {
		
		ComplexFunction cf1 = new ComplexFunction("x + 2");
		ComplexFunction cf2 = new ComplexFunction("x + 2");
		ComplexFunction cf3 = new ComplexFunction("x + 2");


		cf1.comp(cf2);
		System.out.println("Expected: 5: " + cf1.f(1));
	}
	@Test
	void test_equals() {
		
	}

}
