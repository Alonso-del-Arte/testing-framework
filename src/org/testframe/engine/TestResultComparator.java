package org.testframe.engine;

import java.util.Comparator;

/**
 * Compares two test results according to their test result status. The 
 * motivation for this comparator is that it enables the test results reporter 
 * to group test results according to their test result status, so that, for 
 * example, the failing tests are grouped together and one doesn't have to 
 * scroll through a long list of results.
 * @since 1.0
 * @author Alonso del Arte
 */
public class TestResultComparator 
        implements Comparator<TestResult> {

    /**
     * Compares test result statuses according to the order in {@link 
     * TestResultStatus}.
     * @param resultA The first status to compare. For example, {@link 
     * TestResultStatus#FAILED}.
     * @param resultB The second status to compare. For example, {@link 
     * TestResultStatus#PASSED}.
     * @return 0 if the statuses are the same, a negative integer if 
     * <code>resultA</code>'s status is listed earlier than 
     * <code>resultB</code>'s status, or a positive integer if 
     * <code>resultA</code>'s status is listed later than <code>resultB</code>'s 
     * status. The range of this function is currently &minus;3 to +3, but this 
     * is not guaranteed to stay the same in later versions.
     */
    @Override
    public int compare(TestResult resultA, TestResult resultB) {
        return resultA.getStatus().ordinal() - resultB.getStatus().ordinal();
    }

}
