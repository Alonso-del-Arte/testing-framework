package org.example.exercises.arithmetic;

import java.util.Random;

import testframegeneric.api.Test;
import static testframegeneric.api.Asserters.*;

public class FractionTest {
	
	private static final Random RANDOM = new Random();
	
	@Test
	public void testConstructorRejectsDenominatorZero() {
		int numer = RANDOM.nextInt(1024) + 16;
		String msg = "Denominator 0 for numerator " + numer 
				+ " should cause ArithmeticException";
		RuntimeException re = assertThrows(() -> {
			Fraction badFraction = new Fraction(numer, 0);
			System.out.println("Should not have been able to create " 
			        + badFraction.toString());
		}, ArithmeticException.class, msg);
		String excMsg = re.getMessage();
		assert excMsg != null : "Message should not be null";
	}

	@Test
	public void testConstructorRejectsDenominatorLongMin() {
		int numer = RANDOM.nextInt(1024) + 16;
		String msg = "Denominator " + Long.MIN_VALUE + " for numerator " + numer 
				+ " should cause ArithmeticException";
		RuntimeException re = assertThrows(() -> {
			Fraction badFraction = new Fraction(numer, Long.MIN_VALUE);
			System.out.println("Should not have been able to create " 
			        + badFraction.toString());
		}, ArithmeticException.class, msg);
		String excMsg = re.getMessage();
		assert excMsg != null : "Message should not be null";
	}

}
