package org.testframe.engine;

import java.lang.reflect.Method;

/**
 * Tests of the TestResult class. In hindsight, I should have tested this class 
 * before TestRunner and Asserters. As a consequence of that, I wrote these 
 * tests with the assumption that assertions are not actually turned on.
 * @author Alonso del Arte
 */
public class TestResultCheck {
    
    private static void checkGetProcedure() throws ClassNotFoundException {
        System.out.println("getProcedure");
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> type = loader.loadClass(TestRunnerCheck.TEST_CLASS_NAME);
        Method[] tests = type.getMethods();
        for (Method expected : tests) {
            TestResult result = new TestResult(expected, 
                    TestResultStatus.PASSED, null);
            Method actual = result.getProcedure();
            String msg = "Expected " + expected.getName() + ", got " 
                    + actual.getName();
            TestRunnerCheck.check(expected.equals(actual), msg);
        }
    }
    
    private static void checkGetStatus() {
        System.out.println("getStatus");
        Method procedure = TestResultCheck.class.getMethods()[0];
        TestResultStatus[] statuses = TestResultStatus.values();
        for (TestResultStatus expected : statuses) {
            TestResult result = new TestResult(procedure, expected, null);
            TestResultStatus actual = result.getStatus();
            String msg = "Expected " + expected.toString() + ", got " 
                    + actual.toString();
            TestRunnerCheck.check(expected.equals(actual), msg);
        }
    }
    
    private static void checkDoesNotHaveStackTrace() {
        Method procedure = TestResultCheck.class.getMethods()[0];
        TestResult result = new TestResult(procedure, TestResultStatus.PASSED, 
                null);
        String msg = "Result should not have stack trace that was not given";
        TestRunnerCheck.check(!result.hasStackTrace(), msg);
    }
    
    private static void checkHasStackTrace() {
        System.out.println("hasStackTrace");
        Method procedure = TestResultCheck.class.getMethods()[0];
        Throwable traceHolder = new AssertionError("For testing purposes only");
        TestResult result = new TestResult(procedure, TestResultStatus.FAILED, 
                traceHolder);
        String msg = "Result should have stack trace that was given";
        TestRunnerCheck.check(result.hasStackTrace(), msg);
    }
    
    private static void checkGetInformation() {
        System.out.println("getInformation");
        Method procedure = TestResultCheck.class.getMethods()[0];
        Throwable expected = null;
        try {
            throw new RuntimeException();
        } catch (RuntimeException re) {
            expected = re;
            TestResult result = new TestResult(procedure, 
                    TestResultStatus.ERROR, expected);
            Throwable actual = result.getInformation();
            String msg = "Expected " + expected.toString() + ", got " + actual;
            TestRunnerCheck.check(expected.equals(actual), msg);
        }
    }
    
    public static void main(String[] args) {
        try {
            checkGetProcedure();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("\"" + cnfe.getMessage() + "\"");
        }
        checkGetStatus();
        checkDoesNotHaveStackTrace();
        checkHasStackTrace();
        checkGetInformation();
        System.out.println("All checks have PASSED");
    }

}
