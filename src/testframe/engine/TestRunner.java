package testframe.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import testframe.api.Test;

/**
 * Runs the tests in a test class. You can use the -enableassertions switch on 
 * the command line, but that's not necessary, because this test runner makes 
 * sure that assertions are turned on.
 * @author Alonso del Arte
 */
public class TestRunner {
	
	private static List<Method> filter(Method[] procedures) {
		List<Method> tests = new ArrayList<>();
		for (Method procedure : procedures) {
			Test testAnnotation = (Test) procedure.getAnnotation(Test.class);
			if (testAnnotation != null) {
				tests.add(procedure);
			}
		}
		return tests;
	}
	
	static TestResult run(Method test, Object instance) {
		TestResultStatus status;
		Throwable info = null;
		try {
			test.invoke(instance);
			status = TestResultStatus.PASSED;
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
	
	public static List<TestResult> run(String testClassName) {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		loader.setDefaultAssertionStatus(true);
	    List<TestResult> results = new ArrayList<TestResult>();
		try {
			Class<?> type = loader.loadClass(testClassName);
		    Object testClassInstance = type.newInstance();
		    Method[] procedures = type.getMethods();
		    List<Method> tests = filter(procedures);
		    for (Method test : tests) {
		    	results.add(run(test, testClassInstance));
		    }
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
	 * Runs the tests of a test class and reports the results.
	 * @param args First the fully qualified name of the test class, then the 
	 * command line options (currently no command line options are supported). 
	 * For example, "org.example.demo.textops.PalindromeCheckerTest".
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify class to test");
		} else {
			List<TestResult> results = run(args[0]);
			TestResultsReporter.report(results);
		}
	}

}