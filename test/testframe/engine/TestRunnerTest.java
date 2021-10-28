package testframe.engine;

import java.lang.reflect.Method;
import java.util.List;

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

    // TODO: Update to 4 after adding testThatShouldBeSkipped()
    private static final int NUMBER_OF_TOY_TESTS = 3;
    
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
     * Checks that the test that should fail does fail and is reported as 
     * failing.
     */
    static void checkTestThatShouldFailDoesFail() {
        String testName = "testThatShouldFail";
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class<?> type = loader.loadClass(TEST_CLASS_NAME);
            Object testClassInstance = type.newInstance();
            Method test = type.getDeclaredMethod(testName);
            TestResult result = TestRunner.run(test, testClassInstance);
            TestResultStatus status = result.getStatus();
            if (status.equals(TestResultStatus.FAILED)) {
                System.out.println("Test that should fail indeed failed");
            } else {
                String msg = "Expected failing test to be reported as " 
                        + TestResultStatus.FAILED.toString() 
                        + " but it was reported as "
                        + status.toString() + " instead";
                throw new AssertionError(msg);
            }
        } catch (NoSuchMethodException nsme) {
            String msg = "Unable to find test procedure " + testName;
            throw new AssertionError(msg, nsme);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Unable to find test class " + TEST_CLASS_NAME;
            throw new AssertionError(msg, cnfe);
        } catch (IllegalAccessException iae) {
            String msg = "Unable to access test class";
            throw new AssertionError(msg, iae);
        } catch (InstantiationException ie) {
            String msg = "Unable to instantiate " + TEST_CLASS_NAME;
            throw new AssertionError(msg, ie);
        } catch (SecurityException se) {
            String msg = "Encountered security problem";
            throw new AssertionError(msg, se);
        }
    }
    
    /**
     * Checks that the test that should pass does pass and is reported as 
     * passing.
     */
    static void checkTestThatShouldPassDoesPass() {
        String testName = "testThatShouldPass";
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class<?> type = loader.loadClass(TEST_CLASS_NAME);
            Object testClassInstance = type.newInstance();
            Method test = type.getDeclaredMethod(testName);
            TestResult result = TestRunner.run(test, testClassInstance);
            TestResultStatus status = result.getStatus();
            if (status.equals(TestResultStatus.PASSED)) {
                System.out.println("Test that should pass indeed passed");
            } else {
                String msg = "Expected passing test to be reported as " 
                        + TestResultStatus.PASSED.toString() 
                        + " but it was reported as "
                        + status.toString() + " instead";
                throw new AssertionError(msg);
            }
        } catch (NoSuchMethodException nsme) {
            String msg = "Unable to find test procedure " + testName;
            throw new AssertionError(msg, nsme);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Unable to find test class " + TEST_CLASS_NAME;
            throw new AssertionError(msg, cnfe);
        } catch (IllegalAccessException iae) {
            String msg = "Unable to access test class";
            throw new AssertionError(msg, iae);
        } catch (InstantiationException ie) {
            String msg = "Unable to instantiate " + TEST_CLASS_NAME;
            throw new AssertionError(msg, ie);
        } catch (SecurityException se) {
            String msg = "Encountered security problem";
            throw new AssertionError(msg, se);
        }
    }
    
    /**
     * Checks that the test that should cause an error (such as by triggering a 
     * null pointer exception) does cause an error and is reported as having 
     * caused an error.
     */
    static void checkTestThatShouldCauseErrorDoesCauseError() {
        String testName = "testThatShouldCauseError";
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class<?> type = loader.loadClass(TEST_CLASS_NAME);
            Object testClassInstance = type.newInstance();
            Method test = type.getDeclaredMethod(testName);
            TestResult result = TestRunner.run(test, testClassInstance);
            TestResultStatus status = result.getStatus();
            if (status.equals(TestResultStatus.ERROR)) {
                System.out.println("Error causing test indeed caused error");
            } else {
                String msg = "Expected test with error to be reported as " 
                        + TestResultStatus.ERROR.toString() 
                        + " but it was reported as "
                        + status.toString() + " instead";
                throw new AssertionError(msg);
            }
        } catch (NoSuchMethodException nsme) {
            String msg = "Unable to find test procedure " + testName;
            throw new AssertionError(msg, nsme);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Unable to find test class " + TEST_CLASS_NAME;
            throw new AssertionError(msg, cnfe);
        } catch (IllegalAccessException iae) {
            String msg = "Unable to access test class";
            throw new AssertionError(msg, iae);
        } catch (InstantiationException ie) {
            String msg = "Unable to instantiate " + TEST_CLASS_NAME;
            throw new AssertionError(msg, ie);
        } catch (SecurityException se) {
            String msg = "Encountered security problem";
            throw new AssertionError(msg, se);
        }
    }
    
    /**
     * Check that a test result object for a test that failed includes a 
     * Throwable object with the pertinent stack trace.
     */
    static void checkFailingTestResultHasStackTrace() {
        String testName = "testThatShouldFail";
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class<?> type = loader.loadClass(TEST_CLASS_NAME);
            Object testClassInstance = type.newInstance();
            Method test = type.getDeclaredMethod(testName);
            TestResult result = TestRunner.run(test, testClassInstance);
            Throwable info = result.getInformation();
            if (info instanceof AssertionError) {
                System.out.println("Failing test result carries stack trace");
            } else {
                String msg = "Expected AssertionError with stack trace but was " 
                        + info + " instead";
                throw new AssertionError(msg);
            }
        } catch (NoSuchMethodException nsme) {
            String msg = "Unable to find test procedure " + testName;
            throw new AssertionError(msg, nsme);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Unable to find test class " + TEST_CLASS_NAME;
            throw new AssertionError(msg, cnfe);
        } catch (IllegalAccessException iae) {
            String msg = "Unable to access test class";
            throw new AssertionError(msg, iae);
        } catch (InstantiationException ie) {
            String msg = "Unable to instantiate " + TEST_CLASS_NAME;
            throw new AssertionError(msg, ie);
        } catch (SecurityException se) {
            String msg = "Encountered security problem";
            throw new AssertionError(msg, se);
        }
    }
    
    /**
     * Check that a test result object for a test that caused an error includes 
     * a Throwable object with the pertinent stack trace.
     */
    static void checkErrorCausingTestResultHasStackTrace() {
        String testName = "testThatShouldCauseError";
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class<?> type = loader.loadClass(TEST_CLASS_NAME);
            Object testClassInstance = type.newInstance();
            Method test = type.getDeclaredMethod(testName);
            TestResult result = TestRunner.run(test, testClassInstance);
            Throwable info = result.getInformation();
            if (info instanceof Exception) {
                System.out.println("Test with error carries stack trace");
            } else {
                String msg = "Expected Exception with stack trace but was " 
                        + info + " instead";
                throw new AssertionError(msg);
            }
        } catch (NoSuchMethodException nsme) {
            String msg = "Unable to find test procedure " + testName;
            throw new AssertionError(msg, nsme);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Unable to find test class " + TEST_CLASS_NAME;
            throw new AssertionError(msg, cnfe);
        } catch (IllegalAccessException iae) {
            String msg = "Unable to access test class";
            throw new AssertionError(msg, iae);
        } catch (InstantiationException ie) {
            String msg = "Unable to instantiate " + TEST_CLASS_NAME;
            throw new AssertionError(msg, ie);
        } catch (SecurityException se) {
            String msg = "Encountered security problem";
            throw new AssertionError(msg, se);
        }
    }

    /**
     * Runs the tests.
     * @param args The command line arguments. These are completely ignored.
     */
    public static void main(String[] args) {
        List<TestResult> results = TestRunner.run(TEST_CLASS_NAME);
        checkResultSetSizeIsPositive(results);
        checkTestThatShouldFailDoesFail();
        checkTestThatShouldPassDoesPass();
        checkTestThatShouldCauseErrorDoesCauseError();
        checkFailingTestResultHasStackTrace();
        checkErrorCausingTestResultHasStackTrace();
        System.out.println("All tests have PASSED");
    }
    
}
