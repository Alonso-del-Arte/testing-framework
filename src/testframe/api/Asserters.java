package testframe.api;

import java.time.Duration;

/**
 * Static class containing procedures to simplify making assertions in tests. It 
 * almost goes without saying that each of these throws an 
 * <code>AssertionError</code> if the specified assertion fails. That particular 
 * bit of information is not repeated in any procedure's Javadoc, but I do try 
 * to make a note of other throwables that might arise even if they seem obvious 
 * (e.g., <code>NullPointerException</code>). 
 * @author Alonso del Arte
 */
public class Asserters {
    
    /**
     * The default tolerance for comparing floating point values, roughly 
     * 2.225073858507202 &times; 10<sup>&minus;308</sup>. This might be too 
     * little for most purposes, but it's better than 0.0.
     */
    public static final double DEFAULT_TEST_DELTA 
            = Double.longBitsToDouble(4503599627370497L);

    // TODO: Write tests for this
    public static void assertMinimum(long minimum, long actual, String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertMinimum(double minimum, double actual, 
            String msg) {
        //
    }

    // TODO: Write tests for this
    public static void assertMinimum(long minimum, long actual) {
        String msg = "Value " + actual + " ought to be at least " + minimum;
    }
    
    // TODO: Write tests for this
    public static void assertMinimum(double minimum, double actual) {
        //
    }

    /**
     * Asserts that two integers are equal. If they are indeed equal and there 
     * are no other assertions in the test, the test should pass. But if they 
     * are not equal, the test should fail. The test failure explanation will 
     * state what the expected value was and what the actual value was.
     * @param expected The expected integer. For example, 73.
     * @param actual The actual integer. For example, &minus;410.
     */
    public static void assertEquals(int expected, int actual) {
         assertEquals(expected, actual, "");
    }
    
    /**
     * Asserts that two integers are equal. If they are indeed equal and there 
     * are no other assertions in the test, the test should pass. But if they 
     * are not equal, the test should fail.
     * @param expected The expected integer. For example, &minus;489.
     * @param actual The actual integer. For example, 22050.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. For example, "The two lists have the 
     * same elements and should therefore they should be the same size." The 
     * expected and actual values will be appended to the test failure 
     * explanation.
     */
    public static void assertEquals(int expected, int actual, String msg) {
        String message = msg + ". Expected = " + expected + ". Actual = " 
                + actual;
        if (message.startsWith(". ")) {
            message = message.substring(2);
        }
        assert expected == actual : message;
    }
    
    /**
     * Asserts that two integers are equal. If they are indeed equal and there 
     * are no other assertions in the test, the test should pass. But if they 
     * are not equal, the test should fail. The test failure explanation will 
     * state what the expected value was and what the actual value was.
     * @param expected The expected integer. For example, 4294967369.
     * @param actual The actual integer. For example, &minus;4294967779.
     */
    public static void assertEquals(long expected, long actual) {
         assertEquals(expected, actual, "");
    }
    
    /**
     * Asserts that two integers are equal. If they are indeed equal and there 
     * are no other assertions in the test, the test should pass. But if they 
     * are not equal, the test should fail.
     * @param expected The expected integer. For example, &minus;4294968268.
     * @param actual The actual integer. For example, 4294990318.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. For example, "The two lists have the 
     * same elements and should therefore they should be the same size." The 
     * expected and actual values will be appended to the test failure 
     * explanation.
     */
    public static void assertEquals(long expected, long actual, String msg) {
        String message = msg + ". Expected = " + expected + ". Actual = " 
                + actual;
        if (message.startsWith(". ")) {
            message = message.substring(2);
        }
        assert expected == actual : message;
    }
    
    // TODO: Write tests for this
    public static void assertEquals(double expected, double actual) {
         String msg = "Sorry, default message not implemented yet";
         assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
    }
    
    // TODO: Write tests for this
    public static void assertEquals(double expected, double actual, 
            double delta) {
         String msg = "Sorry, default message not implemented yet";
         assertEquals(expected, actual, delta, msg);
    }
    
    // TODO: Write tests for this
    public static void assertEquals(double expected, double actual, 
            String msg) {
         assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
    }
    
    /**
     * 
     * @param expected
     * @param actual
     * @param delta
     * @param msg
     */
    public static void assertEquals(double expected, double actual, 
            double delta, String msg) {
//        double difference = Math.abs(expected - actual);
        String message = msg + ". Expected " + expected 
                + " to not differ from " + actual + " by more than " + delta;
        assert expected == actual : message;
    }
    
    /**
     * Asserts that two objects are equal according to the pertinent 
     * <code>equals()</code> function. If they are indeed equal and there are no 
     * other assertions in the test, the test should pass. But if they are not  
     * equal, the test should fail. The test failure explanation will state what 
     * the expected value was and what the actual value was.
     * @param expected The expected object. For example, a 
     * <code>LocalDateTime</code> object for right now. Note that it is this  
     * parameter's <code>equals()</code> function that will be called.
     * @param actual The actual object. For example, a 
     * <code>LocalDateTime</code> object for this time of day tomorrow.
     * @throws NullPointerException If <code>expected</code> is null.
     */
    public static void assertEquals(Object expected, Object actual) {
         assertEquals(expected, actual, "");
    }
    
    /**
     * Asserts that two objects are equal according to the pertinent 
     * <code>equals()</code> function. If they are indeed equal and there are no 
     * other assertions in the test, the test should pass. But if they are not  
     * equal, the test should fail. The test failure explanation will state what 
     * the expected value was and what the actual value was.
     * @param expected The expected object. For example, a 
     * <code>LocalDateTime</code> object for right now. Note that it is this  
     * parameter's <code>equals()</code> function that will be called.
     * @param actual The actual object. For example, a 
     * <code>LocalDateTime</code> object for this time of day tomorrow.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. For example, "Transaction date of 
     * withdrawal from account A should match date of deposit to account B." The 
     * expected and actual values will be appended to the test failure 
     * explanation.
     * @throws NullPointerException If <code>expected</code> is null.
     */
    public static void assertEquals(Object expected, Object actual, 
            String msg) {
        String message = msg + ". Expected = " + expected.toString() 
                + ". Actual = " + actual;
        if (message.startsWith(". ")) {
            message = message.substring(2);
        }
        assert expected.equals(actual) : message;
    }
    
    // TODO: Write tests for this
    public static void assertEquals(int[] expected, int[] actual) {
         String msg = "Sorry, default message not implemented yet";
         assertEquals(expected, actual, msg);
    }
    
    public static void assertEquals(int[] expected, int[] actual, String msg) {
// if (expected.length != actual.length) {
            String errMsg = "Arrays differ in length: expected has " 
                    + expected.length + " elements but actual has " 
                    + actual.length + " elements";
            throw new AssertionError(errMsg);
// }
    }
    
    // TODO: Write tests for this
    public static void assertEquals(double[] expected, double[] actual) {
         String msg = "Sorry, default message not implemented yet";
         assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
    }
    
    // TODO: Write tests for this
    public static void assertEquals(double[] expected, double[] actual, 
            double delta) {
         String msg = "Sorry, default message not implemented yet";
         assertEquals(expected, actual, delta, msg);
    }
    
    // TODO: Write tests for this
    public static void assertEquals(double[] expected, double[] actual, 
            String msg) {
         assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
    }
    
    public static void assertEquals(double[] expected, double[] actual, 
            double delta, String msg) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public static void assertEquals(Object[] expected, Object[] actual) {
         String msg = "Sorry, default message not implemented yet";
         assertEquals(expected, actual, msg);
    }
    
    public static void assertEquals(Object[] expected, Object[] actual, 
            String msg) {
        // TODO: Write tests for this
    }
    
    // No assertArrayEquals will be provided. Use assertEquals.

    // No assertTrue will be provided. Use plain Java assert.
    
    // No assertFalse will be provided. Use plain Java assert.
    
    // No assertNull will provided. Use plain Java assert.
    
    // TODO: Write tests for this
    public static void assertMaximum(long maximum, long actual, String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertMaximum(double maximum, double actual, 
            String msg) {
        //
    }

    // TODO: Write tests for this
    public static void assertMaximum(long maximum, long actual) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertMaximum(double maximum, double actual) {
        //
    }

    // TODO: Write tests for this
    public static void assertDifferent(int some, int other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, msg);
    }
    
    public static void assertDifferent(int some, int other, String msg) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(double some, double other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(double some, double other, 
            double delta) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, delta, msg);
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(double some, double other, 
            String msg) {
         assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
    }
    
    public static void assertDifferent(double some, double other, 
            double delta, String msg) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(Object some, Object other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, msg);
    }
    
    public static void assertDifferent(Object some, Object other, 
            String msg) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(int[] some, int[] other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, msg);
    }
    
    public static void assertDifferent(int[] some, int[] other, String msg) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(double[] some, double[] other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(double[] some, double[] other, 
            double delta) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, delta, msg);
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(double[] some, double[] other, 
            String msg) {
         assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
    }
    
    public static void assertDifferent(double[] some, double[] other, 
            double delta, String msg) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(Object[] some, Object[] other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, msg);
    }
    
    public static void assertDifferent(Object[] some, Object[] other, 
            String msg) {
        // TODO: Write tests for this
    }
    
    /**
     * Fails a test regardless of anything else. Hence it generally makes sense 
     * to use this in a branch of a flow control statement. However, if you need 
     * it in a Try block or a Catch block, consider instead using {@link 
     * #assertThrows} or {@link #assertDoesNotThrow}.
     * @param msg The message for the test failure explanation. Nothing will be 
     * appended to this message.
     */
    public static void fail(String msg) {
        throw new AssertionError(msg);
    }
    
    /**
     * Asserts that a given lambda caused an exception of a particular type.
     * @param <E> The type of exception that should be thrown. For example, 
     * <code>ArithmeticException</code>.
     * @param lambda The anonymous procedure that should throw the exception of 
     * the specified type. For example, <code>() -> { Fraction badResult = 
     * oneHalf.divides(zero); }</code>. It would be very similar in Scala.
     * @param exceptionType A <code>Class</code> object for the expected 
     * exception type. For example, <code>ArithmeticException.class</code>. Note 
     * that in Scala this would be <code>classOf[ArithmeticException]</code>.
     * @param msg A message for the test failure explanation. For example, 
     * "Division by zero should have caused an exception." If the assertion 
     * fails, either because an exception of the wrong type was thrown or 
     * because no exception was thrown, that information will be appended to the 
     * test failure explanation.
     * @return An exception of the specified type. However, this is not 
     * guaranteed if assertions are not enabled. Then again, this whole class is 
     * useless if assertions are not enabled. 
     * @throws NullPointerException If <code>lambda</code>, 
     * <code>exceptionType</code> or <code>msg</code> is null.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Exception> E assertThrows(Procedure lambda, 
            Class<E> exceptionType, String msg) {
        try {
            lambda.execute();
            String errMsg = msg + ". Expected " + exceptionType.getName() 
                    + " but nothing was thrown";
            throw new AssertionError(errMsg);
        } catch (Exception e) {
            String errMsg = msg + ". Expected " + exceptionType.getName() 
                    + " but was " + e.getClass().getName();
            if (!exceptionType.isAssignableFrom(e.getClass())) {
                throw new AssertionError(errMsg, e);
            };
            return (E) e;
        }
    }

    /**
     * Asserts that a given lambda does not throw exceptions of any type.
     * @param lambda The anonymous procedure that should not throw any 
     * exceptions. For example, <code>() -> { arrayBackedList.expandCapacity; 
     * }</code>.
     * @param msg A message for the test failure explanation. For example, 
     * "Expanding list capacity should not cause any exceptions." If an 
     * exception does occur, that will be appended to the test failure 
     * explanation. For example, "No exceptions should have occurred but 
     * ArrayIndexOutOfBoundsException did."
     * @throws NullPointerException If <code>lambda</code> or <code>msg</code> 
     * is null.
     */
    public static void assertDoesNotThrow(Procedure lambda, String msg) {
        try {
            lambda.execute();
        } catch (Exception e) {
            String errMsg = msg + ". No exception should have occurred but " 
                    + e.getClass().getName() + " did";
            throw new AssertionError(errMsg, e);
        }
    }
    
    public static void assertTimeout(Procedure lambda, Duration allottedTime, 
            String msg) {
        // TODO: Write tests for this
    }

}
