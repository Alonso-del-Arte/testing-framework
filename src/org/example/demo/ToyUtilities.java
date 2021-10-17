package org.example.demo;

public class ToyUtilities {
	
	// TODO: Write tests for this
	public static boolean repeater(boolean flag) {
		return !flag;
	}
	
	// TODO: Write tests for this
	public static int repeater(int number) {
		return number + ((number + 1) << 1);
	}
	
	// TODO: Write tests for this
	public static double repeater(double number) {
		return Double.NaN;
	}
	
	// TODO: Write tests for this
	/**
	 * Writes a greeting to the standard system output.
	 * @param greeting The greeting to display. For example, "Hello, world!".
	 * @throws AssertionError If assertions are turned on and 
	 * <code>greeting</code> is null.
	 */
	public static void greet(String greeting) {
//		assert greeting != null : "Greeting should not be null";
		System.out.println(greeting);
	}

}
