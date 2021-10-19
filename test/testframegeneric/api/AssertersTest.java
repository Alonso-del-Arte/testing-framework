package testframegeneric.api;

public class AssertersTest {
	
	@Test
	public void testFail() {
		System.out.println("fail");
		String expected = "This failure message should be retrievable";
		boolean failOccurred = false;
		try {
			Asserters.fail(expected);
		} catch (AssertionError ae) {
			failOccurred = true;
			String actual = ae.getMessage();
			String msg = "Expected \"" + expected + "\" but was \"" + actual 
					+ "\"";
			assert expected.equals(actual) : msg;
		}
		assert failOccurred : "fail() should have occurred when called";
	}

}
