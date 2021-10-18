package testframegeneric.engine;

import java.util.Set;

class TestResultsReporter {
	
	static void report(Set<TestResult> results) {
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
		System.out.println("Passed: " + passCount + " Failed: " + failCount 
				+ " Skipped: " + skipCount + " Caused an error: " + errorCount);
		System.out.println("Total: " + totalCount);
	}

}
