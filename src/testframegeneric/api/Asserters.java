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
		String message = msg + ". Expected = " + expected + ". Actual = " 
		        + actual;
		assert expected == actual : message;
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
		throw new AssertionError(msg);
	}
	
	public static <E extends Exception> E assertThrows(Procedure lambda, 
			Class<E> exceptionType, String msg) {
		try {
			lambda.execute();
			String errMsg = msg + ". Expected " + exceptionType.getName() 
			        + " but nothing was thrown";
			throw new AssertionError(errMsg);
		} catch (Exception e) {
			String errMsg = msg + ". Expected " + exceptionType.getName() 
			        + " but was " + e.getClass().getName();
			assert exceptionType.isAssignableFrom(e.getClass()) : errMsg;
			return (E) e;
		}
	}

	public static void assertDoesNotThrow(Procedure lambda, String msg) {
		try {
			lambda.execute();
		} catch (Exception e) {
			String errMsg = msg + ". No exception should have occurred but " 
		            + e.getClass().getName() + " did";
			throw new AssertionError(errMsg);
		}
	}
	
	public static void assertTimeout(Procedure lambda, Duration allottedTime, 
			String msg) {
		// TODO: Write tests for this
	}

}
