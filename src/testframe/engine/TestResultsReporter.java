package testframe.engine;

import java.util.List;

/**
 * Reports the test results to <code>System.out</code>. Any frills in the 
 * output, such as text in different colors, is courtesy of the host 
 * environment, e.g., if you're running this in an integrated development 
 * environment (IDE) like Eclipse or NetBeans. An IDE might provide clickable 
 * line numbers (e.g., <code>AssertersTest.java:17</code>) that take you to the 
 * line that caused the test failure or error.
 * @author Alonso del Arte
 */
class TestResultsReporter {
	
	/**
	 * Reports the test results to <code>System.out</code>. The output includes 
	 * one or more stack traces if any tests failed or caused an error.
	 * @param results The list of test results to report. Preferably not an 
	 * empty list.
	 */
	static void report(List<TestResult> results) {
		int totalCount = results.size();
		int passCount = 0;
		int failCount = 0;
		int skipCount = 0;
		int errorCount = 0;
		System.out.println();
		for (TestResult result : results) {
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

}
