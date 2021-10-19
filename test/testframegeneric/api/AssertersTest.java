package testframegeneric.api;

public class AssertersTest {
	
	@Test
	public void testFail() {
		System.out.println("fail");
		String msg = "This failure message should be retrievable";
		boolean failOccurred = false;
		try {
			Asserters.fail(msg);
		} catch (AssertionError ae) {
			failOccurred = true;
		}
		assert failOccurred : "fail() should have occurred when called";
	}

}
