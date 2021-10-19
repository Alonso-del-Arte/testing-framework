package testframegeneric.engine;

import testframegeneric.api.Test;

public class ToyTests {

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

