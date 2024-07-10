package org.testframe.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testframe.api.AfterAllTests;
import org.testframe.api.AfterEachTest;
import org.testframe.api.BeforeAllTests;
import org.testframe.api.BeforeEachTest;
import org.testframe.api.Skip;
import org.testframe.api.Test;

/**
 * Runs the tests in a test class. You can use the -enableassertions switch on 
 * the command line, but that's not necessary, because this test runner makes 
 * sure that assertions are turned on.
 * @since 1.0
 * @author Alonso del Arte
 */
public class TestRunner {
    
    private static List<Method> setUps = new ArrayList<Method>();

    private static List<Method> befores = new ArrayList<Method>();

    private static List<Method> tests = new ArrayList<Method>();

    private static List<Method> skips = new ArrayList<Method>();

    private static List<Method> afters = new ArrayList<Method>();
    
    private static List<Method> tearDowns = new ArrayList<Method>();
    
    private static List<TestResult> results = new ArrayList<TestResult>();

    private static List<Method> filter(Method[] procedures, 
            Class<? extends Annotation> annotation) {
        List<Method> tests = new ArrayList<>();
        for (Method procedure : procedures) {
            Annotation holder = procedure.getAnnotation(annotation);
            if (holder != null) {
                tests.add(procedure);
            }
        }
        return tests;
    }
    
    private static void filterOutSkips() {
        for (Method test : tests) {
            if (test.getAnnotation(Skip.class) != null) {
                skips.add(test);
            }
        }
        tests.removeAll(skips);
    }

    private static TestResult run(Method test, Object instance) {
        TestResultStatus status = TestResultStatus.PASSED;
        Throwable info = null;
        try {
            test.invoke(instance);
        } catch (InvocationTargetException ite) {
            Throwable cause = ite.getCause();
            info = cause;
            if (cause instanceof AssertionError) {
                status = TestResultStatus.FAILED;
            } else {
                status = TestResultStatus.ERROR;
            }
        } catch (IllegalAccessException iae) {
            String excMsg = "Unable to run test " + test.getName() 
                    + " due to illegal access";
            throw new RuntimeException(excMsg, iae);
        }
        return new TestResult(test, status, info);
    }
    
    private static List<TestResult> skip() {
        List<TestResult> results = new ArrayList<>();
        for (Method skip : skips) {
            results.add(new TestResult(skip, TestResultStatus.SKIPPED, null));
        }
        return results;
    }
    
    private static void runSetUps(Object instance) {
        try {
            for (Method setUp : setUps) {
                setUp.invoke(instance);
            }
        } catch (Exception e) {
            String msg = "Unable to run @BeforeAllTests because of " 
                    + e.getClass().getName();
            throw new RuntimeException(msg, e);
        }
    }
    
    private static void runBefores(Object instance) {
        try {
            for (Method before : befores) {
                before.invoke(instance);
            }
        } catch (Exception e) {
            String excMsg = "Unable to run @BeforeEach due to " 
                    + e.getClass().getName();
            throw new RuntimeException(excMsg, e);
        }
    }
    
    private static void runAfters(Object instance) {
        try {
            for (Method after : afters) {
                after.invoke(instance);
            }
        } catch (Exception e) {
            String excMsg = "Unable to run @AfterEach due to " 
                    + e.getClass().getName();
            throw new RuntimeException(excMsg, e);
        }
    }
    
    private static void run(Object instance) {
        for (Method test : tests) {
            runBefores(instance);
            results.add(run(test, instance));
            runAfters(instance);
        }
    }

    private static void runTearDowns(Object instance) {
        try {
            for (Method tearDown : tearDowns) {
                tearDown.invoke(instance);
            }
        } catch (Exception e) {
            String msg = "Unable to run @AfterAllTests because of " 
                    + e.getClass().getName();
            throw new RuntimeException(msg, e);
        }
    }
    
    /**
     * Runs the tests of a test class and reports the results.
     * @param testClassName The name of the test class. It needs to be fully 
     * qualified with all relevant package names. For example, 
     * <code>"org.example.demo.textops.PalindromeCheckerTest"</code>.
     * @return A list of <code>TestResult</code> objects, one for each properly 
     * annotated public test procedure.
     */
    public static List<TestResult> run(String testClassName) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        loader.setDefaultAssertionStatus(true);    
        try {
            Class<?> type = loader.loadClass(testClassName);
            Object testClassInstance = type.newInstance();
            Method[] procedures = type.getMethods();
            setUps = filter(procedures, BeforeAllTests.class);
            befores = filter(procedures, BeforeEachTest.class);
            tests = filter(procedures, Test.class);
            filterOutSkips();
            afters = filter(procedures, AfterEachTest.class);
            tearDowns = filter(procedures, AfterAllTests.class);
            runSetUps(testClassInstance);
            run(testClassInstance);
            runTearDowns(testClassInstance);
            results.addAll(skip());
        } catch (ClassNotFoundException cnfe) {
            System.err.println("No tests ran");
            System.err.println("Unable to find class " + testClassName);
            System.err.println("\"" + cnfe.getMessage() + "\"");
        } catch (IllegalAccessException | InstantiationException ie) {
            System.err.println("No tests ran because of " 
                    + ie.getClass().getName());
        }
        return results;
    }

    /**
     * Runs the tests of a test class specified on the command line and reports 
     * the results.
     * @param args First the fully qualified name of the test class, then the 
     * command line options. For example, 
     * "org.example.demo.textops.PalindromeCheckerTest". For now, only the 
     * command line option "-sort", which sorts the test results so that passing 
     * tests are reported first and failing tests last, is supported. This 
     * option must be placed after the test class name.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify class to test");
        } else {
            List<TestResult> results = run(args[0]);
            if (args.length > 1 && args[1].equals("-sort")) {
                Collections.sort(results, new TestResultComparator());
            }
            TestResultsReporter reporter 
                    = new TestResultsReporter(args[0], results);
            reporter.report();
        }
    }

}
