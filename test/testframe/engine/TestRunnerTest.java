package testframe.engine;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;

/**
 * Tests of the TestRunner class. Obviously we can't use TestRunner itself to 
 * run the tests for TestRunner. So instead we have an ad hoc test runner for 
 * each test. It's going to be verbose and repetitive, and it'll sure make us  
 * appreciate TestRunner once we have it working properly. Also note that this 
 * is designed so that if any test before the last test fails, the remaining 
 * tests will not run.
 * @author Alonso del Arte
 */
public class TestRunnerTest {
    
    private static final String TEST_CLASS_NAME = "testframe.engine.ToyTests";

    private static final Logger INVOCATION_LOGGER 
            = Logger.getLogger(TEST_CLASS_NAME);
    
    private static final InvocationCounter INVOCATION_COUNTER 
            = new InvocationCounter();
    
    static {
        INVOCATION_LOGGER.setLevel(Level.ALL);
        INVOCATION_LOGGER.addHandler(INVOCATION_COUNTER);
    }

    // TODO: Update to 4 after adding testThatShouldBeSkipped()
    private static final int NUMBER_OF_TOY_TESTS = 3;
    
    /**
     * Checks that a condition is true, throws an AssertionError if it's not. 
     * This is necessary because assertions are almost certainly off and 
     * therefore can't be thrown with the usual assertion syntax.
     * @param condition The condition to check for.
     * @param msg The message for the AssertionError if it's thrown.
     */
    private static void check(boolean condition, String msg) {
        if (!condition) {
            throw new AssertionError(msg);
        }
    }
    
    /**
     * Checks that TestRunner correctly identified the three or four tests in 
     * the toy test class, and gives a list of three or four test result 
     * objects.
     * @param results The list of test results to check for size.
     */
    static void checkResultSetSizeIsPositive(List<TestResult> results) {
        int size = results.size();
        switch (size) {
            case 0:
                String errMsg = "TestRunner failed to pick up any tests";
                throw new AssertionError(errMsg);
            case NUMBER_OF_TOY_TESTS:
                System.out.println("TestRunner correctly picked up " 
                        + NUMBER_OF_TOY_TESTS + " tests");
                break;
            default:
                String errMsgPos = "TestRunner picked up " + size 
                        + " tests, not " + NUMBER_OF_TOY_TESTS + " as expected";
                throw new AssertionError(errMsgPos);
        }
    }
    
    /**
     * Checks that the correct number of invoked toy tests has been logged.
     */
    static void checkInvocationCount() {
        int actual = INVOCATION_COUNTER.testCount;
        String msg = "Expected " + NUMBER_OF_TOY_TESTS 
                + " toy tests to be invoked, but only " + actual 
                + " toy tests were actually invoked";
        check(actual == NUMBER_OF_TOY_TESTS, msg);
    }
    
    private static TestResult lookForResult(String testName, 
            List<TestResult> results) {
        TestResult result = null;
        int index = 0;
        boolean found = false;
        while (!found && index < results.size()) {
            TestResult curr = results.get(index);
            found = testName.equals(curr.getProcedure().getName());
            if (found) {
                result = curr;
            }
            index++;
        }
        return result;
    }
    
    /**
     * Checks that the test that should pass does pass and is reported as 
     * passing.
     */
    static void checkTestActuallyPassed(List<TestResult> results) {
        String testName = "testThatShouldPass";
        TestResult result = lookForResult(testName, results);
        if (result == null) {
            String errMsg = "Could not find result record for " + testName;
            throw new AssertionError(errMsg);
        }
        TestResultStatus expected = TestResultStatus.PASSED;
        TestResultStatus actual = result.getStatus();
        String msg = "Expected " + expected.toString() 
                + " for passing test but was " + actual.toString();
        check(expected.equals(actual), msg);
    }
    
    /**
     * Checks that the test that should fail does fail and is reported as 
     * failing. And also that the TestResult object carries an AssertionError 
     * object.
     */
    static void checkTestActuallyFailed(List<TestResult> results) {
        String testName = "testThatShouldFail";
        TestResult result = lookForResult(testName, results);
        if (result == null) {
            String errMsg = "Could not find result record for " + testName;
            throw new AssertionError(errMsg);
        }
        TestResultStatus expected = TestResultStatus.FAILED;
        TestResultStatus actual = result.getStatus();
        String msg = "Expected " + expected.toString() 
                + " for failing test but was " + actual.toString();
        check(expected.equals(actual), msg);
        Throwable info = result.getInformation();
        String errMsg = "Failing test result should carry AssertionError";
        check(info instanceof AssertionError, errMsg);
    }
    
    /**
     * Checks that the test that should cause an error (such as by triggering a 
     * null pointer exception) does cause an error and is reported as having 
     * caused an error. And also that the TestResult object carries an Exception 
     * object.
     */
    static void checkTestActuallyCausedError(List<TestResult> results) {
        String testName = "testThatShouldCauseError";
        TestResult result = lookForResult(testName, results);
        if (result == null) {
            String errMsg = "Could not find result record for " + testName;
            throw new AssertionError(errMsg);
        }
        TestResultStatus expected = TestResultStatus.ERROR;
        TestResultStatus actual = result.getStatus();
        String msg = "Expected " + expected.toString() 
                + " for test that caused an error but was " 
                + actual.toString();
        check(expected.equals(actual), msg);
        Throwable info = result.getInformation();
        String errMsg = "Failing test result should carry Exception";
        check(info instanceof Exception, errMsg);
    }
    
    static void checkPreAndPostWereExecuted() {
        int expected = 1;
        int actualPre = INVOCATION_COUNTER.beforeAllCount;
        String msg = "@BeforeAll was invoked " + actualPre 
                + " times (should've been invoked only once)";
        check(expected == actualPre, msg);
        int actualPost = INVOCATION_COUNTER.afterAllCount;
        msg = "@AfterAll was invoked " + actualPost 
                + " times (should've been invoked only once)";
        check(expected == actualPost, msg);
        msg = "@BeforeEach should've been invoked " + NUMBER_OF_TOY_TESTS 
                + " times, once for each test";
        check(NUMBER_OF_TOY_TESTS == INVOCATION_COUNTER.beforeEachCount, msg);
        msg = msg.replace("Before", "After");
        check(NUMBER_OF_TOY_TESTS == INVOCATION_COUNTER.afterEachCount, msg);
    }
    
    static void checkPreAndPostWereExecutedInRightOrder() {
        // TODO: Figure out how to actually check this
    }
    
    /**
     * Runs the tests.
     * @param args The command line arguments. These are completely ignored.
     */
    public static void main(String[] args) {
        List<TestResult> results = TestRunner.run(TEST_CLASS_NAME);
        INVOCATION_COUNTER.close();
        checkResultSetSizeIsPositive(results);
        checkInvocationCount();
        checkTestActuallyPassed(results);
        checkTestActuallyFailed(results);
        checkTestActuallyCausedError(results);
        checkPreAndPostWereExecuted();
//        checkPreAndPostWereExecutedInRightOrder();
        System.out.println("All tests have PASSED");
    }
    
    private static class InvocationCounter extends Handler {
        
        private boolean open = true;
        
        int beforeAllCount = 0;
        int beforeEachCount = 0;
        int testCount = 0;
        int afterEachCount = 0;
        int afterAllCount = 0;
        
        int beforeAllAfterAnything = 0;
        int beforeEachAfterTooMany = 0;
        int afterEachBeforeTooMany = 0;
        int afterAllBeforeAnything = 0;
        
        @Override
        public void close() {
            this.open = false;
        }
        
        @Override
        public void flush() {
            //
        }
        
        @Override
        public void publish(LogRecord record) {
            String name = record.getSourceMethodName();
            if (this.open) {
                switch (name) {
                    case "@BeforeAllTests":
                        this.beforeAllCount++;
                        this.beforeAllAfterAnything = this.beforeEachCount 
                                + this.testCount + this.afterEachCount 
                                + this.afterAllCount;
                        break;
                    case "@BeforeEachTest":
                        this.beforeEachCount++;
                        this.beforeEachAfterTooMany = this.afterAllCount 
                                + this.afterEachCount - this.testCount - 1; 
                        break;
                    case "@Test":
                        this.testCount++;
                        break;
                    case "@AfterEachTest":
                        this.afterEachCount++;
                        this.afterEachBeforeTooMany = this.afterAllCount 
                                + (this.beforeEachCount - this.afterEachCount);
                        break;
                    case "@AfterAllTests":
                        this.afterAllCount++;
                        this.afterAllBeforeAnything = this.beforeAllCount 
                                + this.beforeEachCount + this.testCount 
                                + this.afterEachCount;
                        break;
                    default:
                        System.err.println("Not sure what to do with log");
                        System.err.println("\"" + name + "\"");
                }
            } else {
                String excMsg = "This handler has already been closed";
                throw new IllegalStateException(excMsg);
            }
        }
        
    }
    
}
