package org.example.exercises.arithmetic;

import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

/**
 * Tests of the IntegerMath class.
 * @author Alonso del Arte, etc.
 */
public class IntegerMathTest {
	
	private static final Random RANDOM = new Random();
	
	@Test
	public void testGCDSamePositiveNumber() {
		int a = RANDOM.nextInt(Short.MAX_VALUE) + 1;
		long expected = a;
		long actual = IntegerMath.euclideanGCD(a, a);
		System.out.println("gcd(" + a + ", " + a + ") is said to be " + actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGCDSameNegativeNumber() {
		int a = -RANDOM.nextInt(Short.MAX_VALUE) - 1;
		long expected = -a;
		long actual = IntegerMath.euclideanGCD(a, a);
		System.out.println("gcd(" + a + ", " + a + ") is said to be " + actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGCDZeroA() {
		long expected = RANDOM.nextInt(Short.MAX_VALUE) + 1;
		long b = -expected;
		long actual = IntegerMath.euclideanGCD(0, b);
		System.out.println("gcd(0, " + b + ") is said to be " + actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGCDZeroB() {
		long expected = RANDOM.nextInt(Short.MAX_VALUE) + 1;
		long a = -expected;
		long actual = IntegerMath.euclideanGCD(a, 0);
		System.out.println("gcd(" + a + ", 0) is said to be " + actual);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEuclideanGCD() {
		System.out.println("euclideanGCD");
		long expected = RANDOM.nextInt(110);
		long a = 113L * expected;
		long b = 127L * expected;
		long actual = IntegerMath.euclideanGCD(a, b);
		System.out.println("gcd(" + a + ", " + b + ") is said to be " + actual);
		assertEquals(expected, actual);
	}
	
}
