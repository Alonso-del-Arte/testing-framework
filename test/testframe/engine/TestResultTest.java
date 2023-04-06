package testframe.engine;

import java.lang.reflect.Method;

public class TestResultTest {
    
    private static void checkGetProcedure() throws ClassNotFoundException {
        System.out.println("getProcedure");
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> type = loader.loadClass(TestRunnerTest.TEST_CLASS_NAME);
        Method[] tests = type.getMethods();
        for (Method expected : tests) {
            TestResult result = new TestResult(expected, 
                    TestResultStatus.PASSED, null);
            Method actual = result.getProcedure();
            String msg = "Expecting " + expected.getName() + ", got " 
                    + actual.getName();
            TestRunnerTest.check(expected.equals(actual), msg);
        }
    }
    
    private static void checkGetStatus() {
        System.out.println("getStatus");
        Method procedure = TestResultTest.class.getMethods()[0];
        TestResultStatus[] statuses = TestResultStatus.values();
        for (TestResultStatus expected : statuses) {
            TestResult result = new TestResult(procedure, expected, null);
            TestResultStatus actual = result.getStatus();
            String msg = "Expecting " + expected.toString() + ", got " 
                    + actual.toString();
            TestRunnerTest.check(expected.equals(actual), msg);
        }
    }
    
    public static void main(String[] args) {
        try {
            checkGetProcedure();
        } catch (ClassNotFoundException cnfe) {
            System.out.println("\"" + cnfe.getMessage() + "\"");
        }
        checkGetStatus();
        System.out.println("All tests have PASSED");
    }

}
