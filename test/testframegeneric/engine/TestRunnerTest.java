package testframegeneric.engine;

import java.util.Set;

import testframegeneric.api.Test;
import static testframegeneric.api.Asserters.*;

public class TestRunnerTest {
	
	public static void checkResultSetSizeIsPositive(Set<TestResult> results) {
		int size = results.size();
		if (size == 0) {
			String msg = "TestRunner failed to pick up any tests";
			throw new AssertionError(msg);
		} else {
			System.out.println("TestRunner picked up " + size + " tests");
		}
	}
	
	public static void main(String[] args) {
		Set<TestResult> results = TestRunner.run(new TestRunnerTest.ToyTests());
		checkResultSetSizeIsPositive(results);
	}
	
	public static class ToyTests {
		
		private static boolean relay(boolean flag) {
			return flag;
		}
		
		@Test
		public void testThatShouldPass() {
			String msg = "This test should be reported as passing";
			System.out.println(msg);
			assert relay(true) : msg;
		}
		
		@Test
		public void testThatShouldFail() {
			String msg = "This test should be reported as failing";
			System.out.println(msg);
			assert relay(false) : msg;
		}
		
		// TODO: Write test that should be skipped
		
		@Test
		public void testThatShouldCauseError() {
			String msg 
			        = "This test should be reported as having caused an error";
			System.out.println(msg);
			throw new RuntimeException(msg);
		}
		
	}

}
