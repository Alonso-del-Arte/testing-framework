package testframe.engine;

import java.lang.reflect.Method;

/**
 * Holds the results of a test. Includes a <code>Throwable</code> if it was 
 * provided to hold the stack trace.
 * @author Alonso del Arte
 */
public class TestResult {
    
    private final Method testProcedure;
    
    private final TestResultStatus testStatus;
    
    private final Throwable testStackTraceHolder;
    
    /**
     * Gets the test procedure the result is for.
     * @return The test procedure the result is for. Such as, for example, 
     * <code>testNoDivisionByZero()</code>.
     */
    public Method getProcedure() {
        return this.testProcedure;
    }
    
    /**
     * Gets the test result status.
     * @return The test result status, one of {@link TestResultStatus#PASSED}, 
     * {@link TestResultStatus#FAILED}, {@link TestResultStatus#ERROR} or {@link 
     * TestResultStatus#SKIPPED}.
     */
    public TestResultStatus getStatus() {
        return this.testStatus;
    }
    
    /**
     * Tells whether the result has a stack trace or not.
     * @return True if a <code>Throwable</code> object presumably holding a 
     * stack trace was provided to the constructor, false otherwise. 
     */
    public boolean hasStackTrace() {
        return this.testStackTraceHolder != null;
    }
    
    /**
     * Retrieves the <code>Throwable</code> that was provided to the 
     * constructor.
     * @return A <code>Throwable</code> object that presumably holds a stack 
     * trace.
     */
    public Throwable getInformation() {
        return this.testStackTraceHolder;
    }
    
    /**
     * Constructor.
     * @param procedure The test procedure the test result is for. Such as, for 
     * example, <code>testNoDivisionByZero()</code>.
     * @param status The test result status, one of {@link 
     * TestResultStatus#PASSED}, {@link TestResultStatus#FAILED}, {@link 
     * TestResultStatus#ERROR} or {@link TestResultStatus#SKIPPED}.
     * @param information An error or exception object presumably holding a 
     * stack trace pertinent to the test, or null if not applicable (such as 
     * ought to be the case for a passing or skipped test). For example, an 
     * <code>ArithmeticException</code> object with a stack trace that includes 
     * a line in <code>testNoDivisionByZero()</code>.
     */
    public TestResult(Method procedure, TestResultStatus status, 
            Throwable information) {
        this.testProcedure = procedure;
        this.testStatus = status;
        this.testStackTraceHolder = information;
    }

}
