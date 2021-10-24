package testframe.engine;

/**
 * Enumerates the four possible outcomes of running a test: it passed, it 
 * failed, it was skipped or it caused an error.
 * @author Alonso del Arte
 */
public enum TestResultStatus {
	
	/**
	 * Indicates the test passed. Generally associated with the color green.
	 */
	PASSED, 
	
	/**
	 * Indicates the test failed. Generally associated with the color yellow.
	 */
	FAILED, 
	
	/**
	 * Indicates the test was skipped. Generally associated with gray.
	 */
	SKIPPED, 
	
	/**
	 * Indicates the test caused an unexpected error or exception. Generally 
	 * associated with the color red. There is some difference of opinion as to 
	 * whether or not a test that caused an unforeseen error or exception failed 
	 * or not. In my opinion, if a test caused an unforeseen error or exception, 
	 * it's neither a pass nor a failure, but an interruption of the test-driven 
	 * development process that needs to be addressed before getting back to the 
	 * normal cycle.
	 */
	ERROR

}
