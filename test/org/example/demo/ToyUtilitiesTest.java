package org.example.demo;

import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class ToyUtilitiesTest {
	
	private static final Random RANDOM = new Random();
	
	@Test
	public void testRepeater() {
		System.out.println("repeater");
		assert ToyUtilities.repeater(true) : "repeater should repeat true";
		assert !ToyUtilities.repeater(false) : "repeater should repeat false";
		int number = RANDOM.nextInt();
		assertEquals(number, ToyUtilities.repeater(number));
		double re = RANDOM.nextDouble();
		assertEquals(re, ToyUtilities.repeater(re));
	}

}
