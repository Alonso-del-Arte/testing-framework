package org.testframe.engine;

import java.util.List;

/**
 * Reports the test results. Any frills in the output, such as text in different 
 * colors, is courtesy of the host environment, e.g., if you're running this in 
 * an integrated development environment (IDE) like Eclipse or NetBeans. An IDE 
 * might provide clickable line numbers (e.g., 
 * <code>AssertersTest.java:17</code>) that take you to the line that caused the 
 * test failure or error.
 * @since 1.0
 * @author Alonso del Arte
 */
public class TestResultsReporter {
    
    private final String testClassName;
    
    private final List<TestResult> resultsList;
    
    /**
     * Reports the test results to <code>System.out</code>. The output includes 
     * one or more stack traces if any tests failed or caused an error.
     */
    public void report() {
        int totalCount = this.resultsList.size();
        int passCount = 0;
        int failCount = 0;
        int skipCount = 0;
        int errorCount = 0;
        System.out.println();
        System.out.println("Test results for " + this.testClassName);
        for (TestResult result : this.resultsList) {
            System.out.print(result.getProcedure().getName() + " ");
            TestResultStatus status = result.getStatus();
            System.out.print(status.toString() + " ");
            switch (status) {
                case PASSED: 
                    passCount++;
                    System.out.println();
                    break;
                case FAILED:
                    failCount++;
                    Throwable failInfo = result.getInformation();
                    System.out.println(failInfo.getMessage());
                    failInfo.printStackTrace();
                    break;
                case SKIPPED:
                    skipCount++;
                    System.out.println();
                    break;
                case ERROR:
                    errorCount++;
                    Throwable errorInfo = result.getInformation();
                    System.out.println(errorInfo.getMessage());
                    errorInfo.printStackTrace();
                    break;
                default:
                    throw new RuntimeException("Unknown test result status");
            }
            System.out.println();
        }
        System.out.println("Passed: " + passCount + ". Failed: " + failCount 
                + ". Skipped: " + skipCount + ". Caused an error: " 
                + errorCount);
        System.out.println("Total: " + totalCount);
    }
    
    /**
     * Sole constructor.
     * @param name The name of the test class, fully qualified. For example, 
     * <code>org.example.HelloWorldTest</code>.
     * @param results The results of the tests. Such as, for example, that one 
     * test passed and another failed.
     */
    public TestResultsReporter(String name, List<TestResult> results) {
        this.testClassName = name;
        this.resultsList = results;
    }

}
