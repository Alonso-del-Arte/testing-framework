package testframe.api;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class AssertersTest {
	
	private static final Random RANDOM = new Random();
	
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
	
	@Test
	public void testAssertNotEqualsInt() {
		int someNumber = RANDOM.nextInt();
		int otherNumber = 2 * someNumber + 1;
		String msgCustomPart = "Customizable part of assertion message";
		String msgStandardPart = "Expected = " + someNumber + ". Actual = " 
		        + otherNumber;
		String expected = msgCustomPart + ". " + msgStandardPart;
		boolean failOccurred = false;
		try {
			Asserters.assertEquals(someNumber, otherNumber, msgCustomPart);
		} catch (AssertionError ae) {
			failOccurred = true;
			String actual = ae.getMessage();
			String msg = "Expected \"" + expected + "\" but was \"" + actual 
					+ "\"";
			assert expected.equals(actual) : msg;
		}
		String msg = "Asserting " + someNumber + " is equal to " + otherNumber 
				+ " should have failed the test";
		assert failOccurred : msg;
	}
	
	@Test
	public void testAssertEquals() {
		System.out.println("assertEquals");
		int someNumber = RANDOM.nextInt();
		int sameNumber = someNumber;
		String msg = "Customizable part of assertion message";
		boolean failOccurred = false;
		try {
			Asserters.assertEquals(someNumber, sameNumber, msg);
		} catch (AssertionError ae) {
			failOccurred = true;
			System.out.println("\"" + ae.getMessage() + "\"");
		}
		String message = "Asserting " + someNumber + " is equal to " 
		        + sameNumber + " should not have failed the test";
		assert !failOccurred : message;
	}
	
	@Test
	public void testAssertNotEqualsIntDefaultMessage() {
		int someNumber = RANDOM.nextInt();
		int otherNumber = 2 * someNumber + 1;
		String expected = "Expected = " + someNumber + ". Actual = " 
		        + otherNumber;
		boolean failOccurred = false;
		try {
			Asserters.assertEquals(someNumber, otherNumber);
		} catch (AssertionError ae) {
			failOccurred = true;
			String actual = ae.getMessage();
			String msg = "Expected \"" + expected + "\" but was \"" + actual 
					+ "\"";
			assert expected.equals(actual) : msg;
		}
		String msg = "Asserting " + someNumber + " is equal to " + otherNumber 
				+ " should have failed the test";
		assert failOccurred : msg;
	}
	
	@Test
	public void testAssertNotEqualsObject() {
		BigInteger someNumber = new BigInteger(72, RANDOM);
		BigInteger otherNumber = someNumber.add(someNumber).add(BigInteger.ONE);
		String msgCustomPart = "Customizable part of assertion message";
		String msgStandardPart = "Expected = " + someNumber.toString() 
		        + ". Actual = " + otherNumber.toString();
		String expected = msgCustomPart + ". " + msgStandardPart;
		boolean failOccurred = false;
		try {
			Asserters.assertEquals(someNumber, otherNumber, msgCustomPart);
		} catch (AssertionError ae) {
			failOccurred = true;
			String actual = ae.getMessage();
			String msg = "Expected \"" + expected + "\" but was \"" + actual 
					+ "\"";
			assert expected.equals(actual) : msg;
		}
		String msg = "Asserting " + someNumber.toString() + " is equal to " 
		        + otherNumber.toString() + " should have failed the test";
		assert failOccurred : msg;
	}
	
	@Test
	public void testAssertEqualsObject() {
		BigInteger someNumber = new BigInteger(64, RANDOM);
		BigInteger sameNumber = someNumber.add(someNumber);
		sameNumber = sameNumber.subtract(someNumber);
		String msg = "Customizable part of assertion message";
		boolean failOccurred = false;
		try {
			Asserters.assertEquals(someNumber, sameNumber, msg);
		} catch (AssertionError ae) {
			failOccurred = true;
			System.out.println("\"" + ae.getMessage() + "\"");
		}
		String message = "Asserting " + someNumber.toString() + " is equal to " 
		        + sameNumber.toString() + " should not have failed the test";
		assert !failOccurred : message;
	}
	
	@Test
	public void testAssertNotEqualsObjectDefaultMessage() {
		BigInteger someNumber = new BigInteger(72, RANDOM);
		BigInteger otherNumber = someNumber.add(someNumber).add(BigInteger.ONE);
		String expected = "Expected = " + someNumber.toString() + ". Actual = " 
		        + otherNumber.toString();
		boolean failOccurred = false;
		try {
			Asserters.assertEquals(someNumber, otherNumber);
		} catch (AssertionError ae) {
			failOccurred = true;
			String actual = ae.getMessage();
			String msg = "Expected \"" + expected + "\" but was \"" + actual 
					+ "\"";
			assert expected.equals(actual) : msg;
		}
		String msg = "Asserting " + someNumber.toString() + " is equal to " 
		        + otherNumber.toString() + " should have failed the test";
		assert failOccurred : msg;
	}
	
	@Test
	public void testAssertNotEqualsArrayIntDiffLengths() {
		int[] someNumbers = {1, 2, 3, 4, 5};
		int[] moreNumbers = {1, 2, 3, 4, 5, 6, 7};
		boolean failOccurred = false;
		try {
			Asserters.assertEquals(someNumbers, moreNumbers);
		} catch (AssertionError ae) {
			failOccurred = true;
			String expected = "Arrays differ in length: expected has " 
			        + someNumbers.length + " elements but actual has " 
					+ moreNumbers.length + " elements";
			String actual = ae.getMessage();
			String msg = "Expected \"" + expected + "\" but was \"" + actual 
					+ "\"";
			assert expected.equals(actual) : msg;
		}
		String msg = "Asserting " + Arrays.toString(someNumbers) 
		        + " is equal to " + Arrays.toString(moreNumbers) 
		        + " should have failed the test";
		assert failOccurred : msg;
	}
	
	@Test
	public void testAssertThrows() {
		System.out.println("assertThrows");
		String msg = "Division by zero should cause ArithmeticException";
		ArithmeticException ae = Asserters.assertThrows(() -> {
			System.out.println("3 divided by 0 equals " + (3 / 0));
		}, ArithmeticException.class, msg);
		assert ae instanceof ArithmeticException : msg;
	}

	@Test
	public void testAssertThrowsButDoesNotThrowAny() {
		boolean failOccurred = false;
		String msg = "Assertion should fail if no exception throws";
		try {
			Asserters.assertThrows(() -> {
				System.out.println("16 divided by 2 equals " + (16 / 2));
			}, ArithmeticException.class, msg);
		} catch (AssertionError ae) {
			failOccurred = true;
			System.out.println("\"" + ae.getMessage() + "\"");
		}
		assert failOccurred : msg;
	}

	@Test
	public void testAssertThrowsButThrowsWrongType() {
		boolean failOccurred = false;
		String msg = "Assertion should fail if wrong exception type thrown";
		try {
			RuntimeException re = Asserters.assertThrows(() -> {
				throw new RuntimeException("Sorry, wrong type exception");
			}, ArithmeticException.class, msg);
			System.out.println("Thrown exception is of type " 
			        + re.getClass().getName());
		} catch (AssertionError ae) {
			failOccurred = true;
			String errMsg = ae.getMessage();
			System.out.println("\"" + errMsg + "\"");
			String checkMsg 
			        = "Failure should report expected, unexpected exceptions";
			assert errMsg.contains("ArithmeticException") : checkMsg;
			assert errMsg.contains("RuntimeException") : checkMsg;
		}
		assert failOccurred : msg;
	}
	
	@Test
	public void testAssertDoesNotThrowButDoes() {
		boolean failOccurred = false;
		String msg = "Assertion should fail if exception thrown";
		try {
			Asserters.assertDoesNotThrow(() -> {
				throw new RuntimeException("Sorry");
			}, msg);
		} catch (AssertionError ae) {
			failOccurred = true;
			String errMsg = ae.getMessage();
			System.out.println("\"" + errMsg + "\"");
			String checkMsg = "Failure should report unexpected exception";
			assert errMsg.contains("RuntimeException") : checkMsg;
		}
		assert failOccurred : msg;
	}

	@Test
	public void testAssertDoesNotThrow() {
		System.out.println("assertDoesNotThrow");
		boolean failOccurred = false;
		String msg = "Operation should not cause any exception";
		try {
			Asserters.assertDoesNotThrow(() -> {
				System.out.println("This should not cause any exception");
			}, msg);
		} catch (AssertionError ae) {
			failOccurred = true;
		}
		assert !failOccurred : msg;
	}

}
