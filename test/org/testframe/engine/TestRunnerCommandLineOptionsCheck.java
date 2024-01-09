package org.testframe.engine;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Checks of the command line options for TestRunner. The occurrences of 
 * RuntimeException are acceptable as long as main() reports 
 * "All checks have PASSED" at the end.
 * @author Alonso del Arte
 */
public class TestRunnerCommandLineOptionsCheck {

    /**
     * Checks that the command line option "-sort" causes the test results to be 
     * reported with passes first, then skips, then errors and lastly fails. 
     * This check depends on YetMoreToyTests having three of each of those. 
     */
    private static void checkMainSortCommandLineOption() {
        String extraTestClassName =  "org.testframe.engine.YetMoreToyTests";
        String args[] = {extraTestClassName, "-sort"};
        OutputStream interceptor = new ByteArrayOutputStream();
        PrintStream usualOut = System.out;
        PrintStream tempOut = new PrintStream(interceptor);
        System.setOut(tempOut);
        TestRunner.main(args);
        System.setOut(usualOut);
        String resultsStr = interceptor.toString();
        int index = 0;
        TestResultStatus[] statuses = TestResultStatus.values();
        int len = statuses.length;
        for (int i = 0; i < len; i++) {
            String statusStr = statuses[i].toString();
            for (char letter = 'A'; letter < 'D'; letter++) {
                index = resultsStr.indexOf(statusStr, index);
                String msg = "Expecting index " + index + " for " + statusStr 
                        + " (" + letter + ") to be at least 0";
                TestRunnerCheck.check(index > -1, msg);
            }
        }
    }
    
    /**
     * Runs the checks.
     * @param args The command line arguments. These are completely ignored.
     */
    public static void main(String[] args) {
        checkMainSortCommandLineOption();
        System.out.println("All checks have PASSED");
    }
    
}
