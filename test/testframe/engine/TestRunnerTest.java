package testframe.engine;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Tests of the TestRunner class. Obviously we can't use TestRunner itself to 
 * run the tests for TestRunner. So we're going to make an ad hoc test runner 
 * for each test. It's going to be verbose and repetitive, and it'll sure make  
 * us appreciate TestRunner once we have it working properly. Also note that 
 * this is designed so that if any test before the last test fails, the 
 * remaining tests will not run.
 * @author Alonso del Arte
 */
public class TestRunnerTest {
	
	private static final String testClassName 
	        = "testframe.engine.ToyTests";

	public static void checkResultSetSizeIsPositive(List<TestResult> results) {
		int size = results.size();
		if (size == 0) {
			String msg = "TestRunner failed to pick up any tests";
			throw new AssertionError(msg);
		} else {
			System.out.println("TestRunner picked up " + size + " tests");
		}
	}
	
	public static void checkTestThatShouldFailDoesFail() {
		try {
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			Class<?> type = loader.loadClass(testClassName);
			Object testClassInstance = type.newInstance();
			Method test = type.getDeclaredMethod("testThatShouldFail");
			TestResult result = TestRunner.run(test, testClassInstance);
			TestResultStatus status = result.getStatus();
			if (status.equals(TestResultStatus.FAILED)) {
				System.out.println("Test that should fail indeed failed");
			} else {
				String msg = "Expected failing test to be reported as " 
			            + TestResultStatus.FAILED.toString() 
			            + " but it was reported as "
			            + status.toString() + " instead";
				throw new AssertionError(msg);
			}
		} catch (NoSuchMethodException nsme) {
			String msg = "Unable to find test procedure";
			throw new AssertionError(msg, nsme);
		} catch (ClassNotFoundException cnfe) {
			String msg = "Unable to find test class";
			throw new AssertionError(msg, cnfe);
		} catch (IllegalAccessException iae) {
			String msg = "Unable to access test class";
			throw new AssertionError(msg, iae);
		} catch (InstantiationException ie) {
			String msg = "Unable to instantiate " + testClassName;
			throw new AssertionError(msg, ie);
		} catch (SecurityException se) {
			String msg = "Encountered security problem";
			throw new AssertionError(msg, se);
		}
	}
	
	public static void checkTestThatShouldPassDoesPass() {
		try {
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			Class<?> type = loader.loadClass(testClassName);
			Object testClassInstance = type.newInstance();
			Method test = type.getDeclaredMethod("testThatShouldPass");
			TestResult result = TestRunner.run(test, testClassInstance);
			TestResultStatus status = result.getStatus();
			if (status.equals(TestResultStatus.PASSED)) {
				System.out.println("Test that should pass indeed passed");
			} else {
				String msg = "Expected passing test to be reported as " 
			            + TestResultStatus.PASSED.toString() 
			            + " but it was reported as "
			            + status.toString() + " instead";
				throw new AssertionError(msg);
			}
		} catch (NoSuchMethodException nsme) {
			String msg = "Unable to find test procedure";
			throw new AssertionError(msg, nsme);
		} catch (ClassNotFoundException cnfe) {
			String msg = "Unable to find test class";
			throw new AssertionError(msg, cnfe);
		} catch (IllegalAccessException iae) {
			String msg = "Unable to access test class";
			throw new AssertionError(msg, iae);
		} catch (InstantiationException ie) {
			String msg = "Unable to instantiate " + testClassName;
			throw new AssertionError(msg, ie);
		} catch (SecurityException se) {
			String msg = "Encountered security problem";
			throw new AssertionError(msg, se);
		}
	}
	
	public static void checkTestThatShouldCauseErrorDoesCauseError() {
		try {
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			Class<?> type = loader.loadClass(testClassName);
			Object testClassInstance = type.newInstance();
			Method test = type.getDeclaredMethod("testThatShouldCauseError");
			TestResult result = TestRunner.run(test, testClassInstance);
			TestResultStatus status = result.getStatus();
			if (status.equals(TestResultStatus.ERROR)) {
				System.out.println("Error causing test indeed caused error");
			} else {
				String msg = "Expected test with error to be reported as " 
			            + TestResultStatus.ERROR.toString() 
			            + " but it was reported as "
			            + status.toString() + " instead";
				throw new AssertionError(msg);
			}
		} catch (NoSuchMethodException nsme) {
			String msg = "Unable to find test procedure";
			throw new AssertionError(msg, nsme);
		} catch (ClassNotFoundException cnfe) {
			String msg = "Unable to find test class";
			throw new AssertionError(msg, cnfe);
		} catch (IllegalAccessException iae) {
			String msg = "Unable to access test class";
			throw new AssertionError(msg, iae);
		} catch (InstantiationException ie) {
			String msg = "Unable to instantiate " + testClassName;
			throw new AssertionError(msg, ie);
		} catch (SecurityException se) {
			String msg = "Encountered security problem";
			throw new AssertionError(msg, se);
		}
	}
	
	public static void checkFailingTestResultHasStackTrace() {
		try {
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			Class<?> type = loader.loadClass(testClassName);
			Object testClassInstance = type.newInstance();
			Method test = type.getDeclaredMethod("testThatShouldFail");
			TestResult result = TestRunner.run(test, testClassInstance);
			Throwable info = result.getInformation();
			if (info instanceof AssertionError) {
				System.out.println("Failing test result carries stack trace");
			} else {
				String msg = "Expected AssertionError with stack trace but was " 
			            + info + " instead";
				throw new AssertionError(msg);
			}
		} catch (NoSuchMethodException nsme) {
			String msg = "Unable to find test procedure";
			throw new AssertionError(msg, nsme);
		} catch (ClassNotFoundException cnfe) {
			String msg = "Unable to find test class";
			throw new AssertionError(msg, cnfe);
		} catch (IllegalAccessException iae) {
			String msg = "Unable to access test class";
			throw new AssertionError(msg, iae);
		} catch (InstantiationException ie) {
			String msg = "Unable to instantiate " + testClassName;
			throw new AssertionError(msg, ie);
		} catch (SecurityException se) {
			String msg = "Encountered security problem";
			throw new AssertionError(msg, se);
		}
	}
	
	public static void checkErrorCausingTestResultHasStackTrace() {
		try {
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			Class<?> type = loader.loadClass(testClassName);
			Object testClassInstance = type.newInstance();
			Method test = type.getDeclaredMethod("testThatShouldCauseError");
			TestResult result = TestRunner.run(test, testClassInstance);
			Throwable info = result.getInformation();
			if (info instanceof Exception) {
				System.out.println("Test with error carries stack trace");
			} else {
				String msg = "Expected Exception with stack trace but was " 
			            + info + " instead";
				throw new AssertionError(msg);
			}
		} catch (NoSuchMethodException nsme) {
			String msg = "Unable to find test procedure";
			throw new AssertionError(msg, nsme);
		} catch (ClassNotFoundException cnfe) {
			String msg = "Unable to find test class";
			throw new AssertionError(msg, cnfe);
		} catch (IllegalAccessException iae) {
			String msg = "Unable to access test class";
			throw new AssertionError(msg, iae);
		} catch (InstantiationException ie) {
			String msg = "Unable to instantiate " + testClassName;
			throw new AssertionError(msg, ie);
		} catch (SecurityException se) {
			String msg = "Encountered security problem";
			throw new AssertionError(msg, se);
		}
	}

	public static void main(String[] args) {
		List<TestResult> results = TestRunner.run(testClassName);
		checkResultSetSizeIsPositive(results);
		checkTestThatShouldFailDoesFail();
		checkTestThatShouldPassDoesPass();
		checkTestThatShouldCauseErrorDoesCauseError();
		checkFailingTestResultHasStackTrace();
		checkErrorCausingTestResultHasStackTrace();
		System.out.println("All tests have PASSED");
	}
	
}
