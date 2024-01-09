package org.testframe.engine;

import java.util.Comparator;

public class TestResultComparator 
        implements Comparator<TestResult> {

    @Override
    public int compare(TestResult resultA, TestResult resultB) {
        return resultA.getStatus().ordinal() - resultB.getStatus().ordinal();
    }

}
