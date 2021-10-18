package testframegeneric.engine;

import java.lang.annotation.Annotation;
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
	
	public static TestResult run(Method test, Object instance) {
		TestResultStatus status;
		Throwable info = null;
		try {
			test.invoke(instance);
			status = TestResultStatus.PASSED;
		} catch (AssertionError ae) {
			status = TestResultStatus.FAILED;
		} catch (Exception e) {
			status = TestResultStatus.ERROR;
		}
		return new TestResult(test, status, info);
	}
	
	public static List<TestResult> run(Object testClass) {
		List<TestResult> results = new ArrayList<TestResult>();
		Method[] procedures = testClass.getClass().getMethods();
		List<Method> tests = filter(procedures);
		for (Method test : tests) {
			results.add(run(test, testClass));
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
