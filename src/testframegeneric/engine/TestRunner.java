package testframegeneric.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import testframegeneric.api.Test;

/**
 * 
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
			if (cause instanceof AssertionError) {
				status = TestResultStatus.FAILED;
				info = cause;
			} else {
				status = TestResultStatus.ERROR;
				info = cause;
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// c.newInstance(); ???
	}

}
