package org.testframe.engine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestResultComparatorCheck {
    
    private static void checkCompare() {
        System.out.println("compare");
        Random random = new Random();
        final int maxPer = 5;
        TestResultStatus[] statuses = TestResultStatus.values();
        final int len = statuses.length;
        final int capacity = maxPer * statuses.length;
        List<TestResultStatus> expected = new ArrayList<>(capacity);
        for (int i = 0; i < len; i++) {
            int total = random.nextInt(maxPer) + 1;
            for (int j = 0; j < total; j++) {
                expected.add(statuses[i]);
            }
        }
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
            Class<?> type = loader.loadClass(TestRunnerCheck.TEST_CLASS_NAME);
            Method[] procedures = type.getMethods();
            int bound = procedures.length;
            List<TestResultStatus> list = new ArrayList<>(expected);
            Collections.shuffle(list);
            List<TestResult> results = new ArrayList<>(len);
            int size = expected.size();
            for (int j = 0; j < size; j++) {
                TestResult result 
                        = new TestResult(procedures[random.nextInt(bound)], 
                                list.get(j), null);
                results.add(result);
            }
            TestResultComparator comparator = new TestResultComparator();
            Collections.sort(results, comparator);
            List<TestResultStatus> actual = new ArrayList<>(len);
            for (int k = 0; k < size; k++) {
                actual.add(results.get(k).getStatus());
            }
            String msg = "Expected " + expected.toString() + ", was " 
                    + actual.toString();
            TestRunnerCheck.check(expected.equals(actual), msg);
        } catch (ClassNotFoundException cnfe) {
            String msg = "Unable to run check due to " 
                    + cnfe.getClass().getName();
            RuntimeException wrapper = new RuntimeException(msg, cnfe);
            throw wrapper;
        }
    }

    public static void main(String[] args) {
        checkCompare();
        System.out.println("All checks have PASSED");
    }

}
