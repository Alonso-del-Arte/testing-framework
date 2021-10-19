package testframegeneric.api;

import java.time.Duration;

public class Asserters {
	
	/**
	 * The default tolerance for comparing floating point values, roughly 
	 * 2.225073858507202 &times; 10<sup>&minus;308</sup>. This might be too 
	 * little for most purposes, but it's better than 0.0.
	 */
	public static final double DEFAULT_TEST_DELTA 
	        = Double.longBitsToDouble(4503599627370497L);

	// TODO: Write tests for this
	public static void assertEquals(int expected, int actual) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, msg);
	}
	
	public static void assertEquals(int expected, int actual, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertEquals(double expected, double actual) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
	}
	
	// TODO: Write tests for this
	public static void assertEquals(double expected, double actual, 
			double delta) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, delta, msg);
	}
	
	// TODO: Write tests for this
	public static void assertEquals(double expected, double actual, 
			String msg) {
		 assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
	}
	
	public static void assertEquals(double expected, double actual, 
			double delta, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertEquals(Object expected, Object actual) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, msg);
	}
	
	public static void assertEquals(Object expected, Object actual, 
			String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertEquals(int[] expected, int[] actual) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, msg);
	}
	
	public static void assertEquals(int[] expected, int[] actual, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertEquals(double[] expected, double[] actual) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
	}
	
	// TODO: Write tests for this
	public static void assertEquals(double[] expected, double[] actual, 
			double delta) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, delta, msg);
	}
	
	// TODO: Write tests for this
	public static void assertEquals(double[] expected, double[] actual, 
			String msg) {
		 assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
	}
	
	public static void assertEquals(double[] expected, double[] actual, 
			double delta, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertEquals(Object[] expected, Object[] actual) {
		 String msg = "Sorry, default message not implemented yet";
		 assertEquals(expected, actual, msg);
	}
	
	public static void assertEquals(Object[] expected, Object[] actual, 
			String msg) {
		// TODO: Write tests for this
	}
	
	// No assertArrayEquals will be provided. Use assertEquals.

	// No assertTrue will be provided. Use plain Java assert.
	
	// No assertFalse will be provided. Use plain Java assert.
	
	// TODO: Write tests for this
	public static void assertDifferent(int some, int other) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, msg);
	}
	
	public static void assertDifferent(int some, int other, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(double some, double other) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(double some, double other, 
			double delta) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, delta, msg);
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(double some, double other, 
			String msg) {
		 assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
	}
	
	public static void assertDifferent(double some, double other, 
			double delta, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(Object some, Object other) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, msg);
	}
	
	public static void assertDifferent(Object some, Object other, 
			String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(int[] some, int[] other) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, msg);
	}
	
	public static void assertDifferent(int[] some, int[] other, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(double[] some, double[] other) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(double[] some, double[] other, 
			double delta) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, delta, msg);
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(double[] some, double[] other, 
			String msg) {
		 assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
	}
	
	public static void assertDifferent(double[] some, double[] other, 
			double delta, String msg) {
		// TODO: Write tests for this
	}
	
	// TODO: Write tests for this
	public static void assertDifferent(Object[] some, Object[] other) {
		 String msg = "Sorry, default message not implemented yet";
		 assertDifferent(some, other, msg);
	}
	
	public static void assertDifferent(Object[] some, Object[] other, 
			String msg) {
		// TODO: Write tests for this
	}
	
	public static void fail(String msg) {
		throw new AssertionError("Sorry, not the message you were expecting");
	}
	
	// TODO: Write tests for this
	public static <E extends Exception> E assertThrows(Procedure lambda, 
			Class<E> exceptionType, String msg) {
		return null;
	}

	public static void assertDoesNotThrow(Procedure lambda, String msg) {
		// TODO: Write tests for this
	}
	
	public static void assertTimeout(Procedure lambda, Duration allottedTime, 
			String msg) {
		// TODO: Write tests for this
	}

}
