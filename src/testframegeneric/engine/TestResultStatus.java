package testframegeneric.engine;

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
	 * associated with the color red.
	 */
	ERROR

}
