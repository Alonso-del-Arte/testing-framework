package org.testframe.engine;

/**
 * Enumerates the four possible outcomes of running a test: it passed, it 
 * failed, it was skipped or it caused an error. Tests are annotated with the 
 * annotation {@link org.testframe.api.Test}.
 * @since 1.0
 * @author Alonso del Arte
 */
public enum TestResultStatus {
    
    /**
     * Indicates the test passed. Generally associated with the color green.
     */
    PASSED, 
    
    /**
     * Indicates the test was skipped. Generally associated with gray. A skipped 
     * test must be annotated with both {@link testframe.api.Skip} <em>and</em> 
     * {@link testframe.api.Test} or else it will be ignored by the test runner. 
     */
    SKIPPED, 
    
    /**
     * Indicates the test caused an unexpected error or exception. Generally 
     * associated with the color red. There is some difference of opinion as to 
     * whether or not a test that caused an unforeseen error or exception failed 
     * or not. In my opinion, if a test caused an unforeseen error or exception, 
     * it's neither a pass nor a failure, but an interruption of the test-driven 
     * development process that needs to be addressed before getting back to the 
     * normal test-driven development (TDD) cycle.
     */
    ERROR,

    /**
     * Indicates the test failed. Generally associated with the color yellow. 
     * The developer should check that the test failed for the expected reason.
     */
    FAILED
    
}
