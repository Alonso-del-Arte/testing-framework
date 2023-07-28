package testframe.engine;

public class TestResultStatusCheck {
    
    private static void checkOrdinal() {
        System.out.println("ordinal");
        TestResultStatus[] statuses = {TestResultStatus.PASSED, 
                TestResultStatus.SKIPPED, TestResultStatus.ERROR, 
                TestResultStatus.FAILED};
        for (int expected = 0; expected < statuses.length; expected++) {
            TestResultStatus status = statuses[expected];
            int actual = status.ordinal();
            String msg = "Ordinal of " + status.name() + " expected to be " 
                    + expected + ", is " + actual;
            TestRunnerTest.check(expected == actual, msg);
        }
    }
    
    public static void main(String[] args) {
        checkOrdinal();
    }

}
