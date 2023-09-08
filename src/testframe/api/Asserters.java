package testframe.api;

import java.time.Duration;
import java.util.Collection;
import java.util.List;

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
    
    private static String prepMsg(String intermediate) {
        if (intermediate.startsWith(". ")) {
            return intermediate.substring(2);
        } else {
            return intermediate;
        }
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
        String intermediate = msg + ". Expected = " + expected + ". Actual = " 
                + actual;
        String errMsg = prepMsg(intermediate);
        assert expected == actual : errMsg;
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
        String intermediate = msg + ". Expected = " + expected + ". Actual = " 
                + actual;
        String errMsg = prepMsg(intermediate);
        assert expected == actual : errMsg;
    }
    
    /**
     * Asserts that two floating point numbers are equal, or very close to 
     * equal, according to the default variance, {@link #DEFAULT_TEST_DELTA}. A 
     * default message specifying the expected, actual and variance values will 
     * be given if the expected and actual values diverge by more than the 
     * default variance.
     * @param expected The expected value. For example, 3.14159.
     * @param actual The actual value. For example, 3.14161.
     */
    public static void assertEquals(double expected, double actual) {
         assertEquals(expected, actual, DEFAULT_TEST_DELTA, "");
    }
    
    /**
     * Asserts that two floating point numbers are equal, or very close to 
     * equal, according to a specified variance. A default message specifying 
     * the expected, actual and variance values will be given if the expected 
     * and actual values diverge by more than the allowed variance.
     * @param expected The expected value. For example, 3.14159.
     * @param actual The actual value. For example, 3.14161.
     * @param delta The maximum allowed variance for <code>expected</code> and 
     * <code>actual</code> to differ and still be considered close enough to be 
     * equal. For example, 0.00001. Ought to be at least 0.0 but is preferably 
     * positive, though less than 1.0. Negative variances are not recommended, 
     * and the behavior is not at all guaranteed to remain consistent from one 
     * version of this framework to the next.
     */
    public static void assertEquals(double expected, double actual, 
            double delta) {
         assertEquals(expected, actual, delta, "");
    }
    
    /**
     * Asserts that two floating point numbers are equal, or very close to 
     * equal, according to the default variance, {@link #DEFAULT_TEST_DELTA}.
     * @param expected The expected value. For example, 3.14159.
     * @param actual The actual value. For example, 3.14161.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. For example, "The number should 
     * converge to &pi;." The expected, actual and default delta values will be 
     * appended to the test failure explanation.
     */
    public static void assertEquals(double expected, double actual, 
            String msg) {
         assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
    }
    
    /**
     * Asserts that two floating point numbers are equal, or very close to 
     * equal, according to a specified variance.
     * @param expected The expected value. For example, 3.14159.
     * @param actual The actual value. For example, 3.14161.
     * @param delta The maximum allowed variance for <code>expected</code> and 
     * <code>actual</code> to differ and still be considered close enough to be 
     * equal. For example, 0.00001. Ought to be at least 0.0 but is preferably 
     * positive, though less than 1.0. Negative variances are not recommended, 
     * and the behavior is not at all guaranteed to remain consistent from one 
     * version of this framework to the next.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. For example, "The number should 
     * converge to &pi;." The expected, actual and delta values will be appended 
     * to the test failure explanation.
     */
    public static void assertEquals(double expected, double actual, 
            double delta, String msg) {
        double difference = Math.abs(expected - actual);
        String message = prepMsg(msg + ". Expected " + expected 
                + " to not differ from " + actual + " by more than " + delta);
        assert delta >= difference : message;
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
        String intermediate = msg + ". Expected = " + expected.toString() 
                + ". Actual = " + actual;
        String errMsg = prepMsg(intermediate);
        assert expected.equals(actual) : errMsg;
    }
    
    /**
     * Asserts the two arrays of integers are of the same length and contain the 
     * same numbers. The contents of the arrays will be compared only if the 
     * arrays match in length. The test failure explanation will either state 
     * that the arrays differ in length or at what index the first difference 
     * was encountered if they do match in length.
     * @param expected The expected array. For example, {1, 3, 4, 7, 11, 18, 29, 
     * 47, 76}.
     * @param actual The actual array. Examples: {2, 1, 3, 4, 7, 11, 18, 29, 47, 
     * 76}, {1, 3, 4, 7, 11, 12, 18, 21, 28}.
     */
    public static void assertEquals(int[] expected, int[] actual) {
         assertEquals(expected, actual, "");
    }
    
    /**
     * Asserts the two arrays of integers are of the same length and contain the 
     * same numbers. The contents of the arrays will be compared only if the 
     * arrays match in length.
     * @param expected The expected array. For example, {1, 3, 4, 7, 11, 18, 29, 
     * 47, 76}.
     * @param actual The actual array. Examples: {2, 1, 3, 4, 7, 11, 18, 29, 47, 
     * 76}, {1, 3, 4, 7, 11, 12, 18, 21, 28}.
     * @param msg A message for the test failure explanation.
     */
    public static void assertEquals(int[] expected, int[] actual, String msg) {
        if (expected.length != actual.length) {
            String intermediate = msg 
                    + ". Arrays differ in length: expected has " 
                    + expected.length + " elements but actual has " 
                    + actual.length + " elements";
            String errMsg = prepMsg(intermediate);
            throw new AssertionError(errMsg);
        }
        for (int i = 0; i < expected.length; i++) {
            String intermediate = msg + ". Arrays first differ at index " + i 
                    + ", expected " + expected[i] + " but was " + actual[i];
            String errMsg = prepMsg(intermediate);
            assert expected[i] == actual[i] : errMsg;
        }
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
    
    /**
     * Asserts that an integer is greater than or equal to a specified minimum. 
     * The test failure explanation will include both the expected minimum and 
     * the actual number if it's below the minimum.
     * @param minimum The minimum permissible value. For example, 100.
     * @param actual The value to compare against the specified minimum. For 
     * example, 95.
     */
    public static void assertMinimum(long minimum, long actual) {
        assertMinimum(minimum, actual, "");
    }
    
    /**
     * Asserts that an integer is greater than or equal to a specified minimum. 
     * The test failure explanation will include both the expected minimum and 
     * the actual number if it's below the minimum.
     * @param minimum The minimum permissible value. For example, 100.
     * @param actual The value to compare against the specified minimum. For 
     * example, 95.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. For example, "List should have at 
     * least as many elements as the set." The minimum and actual values will be 
     * appended to the test failure explanation.
     */
    public static void assertMinimum(long minimum, long actual, String msg) {
        String intermediate = msg + ". Number " + actual 
                + " expected to be at least " + minimum;
        String errMsg = prepMsg(intermediate);
        assert actual >= minimum : errMsg;
    }
    
    /**
     * Asserts that a floating point number is equal to or greater than a 
     * specified minimum. Due to the vagaries of floating point, numbers 
     * slightly below the minimum might register as equal to the minimum. The 
     * test failure explanation will include both the expected minimum and the 
     * actual number if it's below the minimum.
     * @param minimum The minimum possible number. For example, &minus;0.5.
     * @param actual The number to compare against the specified minimum. For 
     * example, &minus;1.83.
     */
    public static void assertMinimum(double minimum, double actual) {
        assertMinimum(minimum, actual, "");
    }

    /**
     * Asserts that a floating point number is equal to or greater than a 
     * specified minimum. Due to the vagaries of floating point, numbers 
     * slightly below the minimum might register as equal to the minimum. The 
     * test failure explanation will include both the expected minimum and the 
     * actual number if it's below the minimum.
     * @param minimum The minimum possible number. For example, &minus;0.5.
     * @param actual The number to compare against the specified minimum. For 
     * example, &minus;1.83.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The minimum and actual values will 
     * be appended to the test failure explanation.
     */
    public static void assertMinimum(double minimum, double actual, 
            String msg) {
        String intermediate = msg + ". Value " + actual 
                + " expected to be at least " + minimum;
        String errMsg = prepMsg(intermediate);
        assert actual >= minimum : errMsg;
    }

    /**
     * Asserts that the value held by a <code>Comparable</code> object is 
     * greater than or equal to a specified minimum.
     * @param <T> The type of the <code>minimum</code> and <code>actual</code> 
     * parameters. Must implement <code>Comparable&lt;T&gt;</code>. For example, 
     * <code>Fraction implements Comparable&lt;Fraction&gt;</code>.
     * @param minimum The minimum permissible value. For example, 
     * <sup>3</sup>&frasl;<sub>2</sub>.
     * @param actual The value to compare against the specified minimum. For 
     * example, <sup>21</sup>&frasl;<sub>16</sub>.
     */
    public static <T extends Comparable<T>> void assertMinimum(T minimum, 
            T actual) {
        assertMinimum(minimum, actual, "");
    }

    /**
     * Asserts that the value held by a <code>Comparable</code> object is 
     * greater than or equal to a specified minimum.
     * @param <T> The type of the <code>minimum</code> and <code>actual</code> 
     * parameters. Must implement <code>Comparable&lt;T&gt;</code>. For example, 
     * <code>Fraction implements Comparable&lt;Fraction&gt;</code>.
     * @param minimum The minimum permissible value. For example, 
     * <sup>3</sup>&frasl;<sub>2</sub>.
     * @param actual The value to compare against the specified minimum. For 
     * example, <sup>21</sup>&frasl;<sub>16</sub>.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The minimum and actual values will 
     * be appended to the test failure explanation.
     */
    public static <T extends Comparable<T>> void assertMinimum(T minimum, 
            T actual, String msg) {
        String intermediate = msg + ". Value " + actual.toString() 
                + " expected to be at least " + minimum.toString();
        String errMsg = prepMsg(intermediate);
        int comparison = minimum.compareTo(actual);
        assert comparison < 1 : errMsg;
    }

    /**
     * Asserts that an integer is negative. The actual number and the maximum 
     * &minus;1 will be included in the test failure explanation if the 
     * assertion fails.
     * @param actual The number to check. For example, &minus;14370899.
     */
    public static void assertNegative(long actual) {
        assertNegative(actual, "");
    }

    /**
     * Asserts that an integer is negative.
     * @param actual The number to check. For example, &minus;14370899.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The number <code>actual</code> and 
     * the maximum &minus;1 will be appended to the test failure explanation.
     */
    public static void assertNegative(long actual, String msg) {
        String intermediate = msg + ". Number " + actual 
                + " expected to be at most -1";
        String errMsg = prepMsg(intermediate);
        assert actual < 0 : errMsg;
        // TODO: Refactor when assertMaximum() is available
    }

    /**
     * Asserts that a floating point number is negative. However, due to the 
     * vagaries of floating point, negative subnormal numbers might be 
     * erroneously regarded as not negative.
     * <p>Other special cases to be aware of:</p>
     * <ul>
     * <li>Negative infinity should not fail the assertion, same as finite 
     * negative numbers.</li>
     * <li>Negative zero, an oddity of the floating point specification, should 
     * nevertheless be considered not negative.</li>
     * <li>Positive zero should of course fail the assertion, the same as 
     * positive numbers.</li>
     * <li>Positive infinity should fail the assertion, same as finite positive 
     * numbers.</li>
     * <li>NaN should fail the assertion even if the bit pattern is negative. 
     * And in any case, it's difficult to access NaN values other than the 
     * "canonical" NaN through the Java Virtual Machine.</li>
     * </ul>
     * <p>If the test fails, the test failure explanation will include the 
     * number <code>actual</code> and the threshold 0.0, except in the case of 
     * asserting on NaN, in which case the test failure explanation will state 
     * that NaN is not considered negative, zero or positive.</p>
     * @param actual The number to check. For example, &minus;2.6065827580858707 
     * &times; 10<sup>8</sup>.
     */
    public static void assertNegative(double actual) {
        assertNegative(actual, "");
    }

    /**
     * Asserts that a floating point number is negative. However, due to the 
     * vagaries of floating point, negative subnormal numbers might be 
     * erroneously regarded as not negative.
     * <p>Other special cases to be aware of:</p>
     * <ul>
     * <li>Negative infinity should not fail the assertion, same as finite 
     * negative numbers.</li>
     * <li>Negative zero, an oddity of the floating point specification, should 
     * nevertheless be considered not negative.</li>
     * <li>Positive zero should of course fail the assertion, the same as 
     * positive numbers.</li>
     * <li>Positive infinity should fail the assertion, same as finite positive 
     * numbers.</li>
     * <li>NaN should fail the assertion even if the bit pattern is negative. 
     * And in any case, it's difficult to access NaN values other than the 
     * "canonical" NaN through the Java Virtual Machine.</li>
     * </ul>
     * @param actual The number to check. For example, &minus;2.6065827580858707 
     * &times; 10<sup>8</sup>.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The number <code>actual</code> and 
     * the threshold 0.0 will be appended to the test failure explanation, or, 
     * in the case of NaN, the appended explanation will say that NaN "is not 
     * considered negative, zero or positive."
     */
    public static void assertNegative(double actual, String msg) {
        if (Double.isNaN(actual)) {
            String intermediate = msg + ". Number " + actual 
                    + " is not considered negative, zero or positive";
            String errMsg = prepMsg(intermediate);
            throw new AssertionError(errMsg);
        }
        String intermediate = msg + ". Number " + actual 
                + " expected to be less than 0.0";
        String errMsg = prepMsg(intermediate);
        assert actual < 0.0 : errMsg;
    }

    /**
     * Asserts that an integer is not positive. Remember that 0 is not positive. 
     * If the test fails because of the assertion, the number 
     * <code>actual</code> and the threshold 1 will be appended to the test 
     * failure explanation.
     * @param actual The number to assert is not positive. For example, 
     * &minus;47.
     */
    public static void assertNotPositive(long actual) {
        assertNotPositive(actual, "");
    }

    /**
     * Asserts that an integer is not positive. Remember that 0 is not positive.
     * @param actual The number to assert is not positive. For example, 
     * &minus;47.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The number <code>actual</code> and 
     * the threshold 1 will be appended to the test failure explanation.
     */
    public static void assertNotPositive(long actual, String msg) {
        String intermediate = msg + ". Number " + actual 
                + " expected to be less than 1";
        String errMsg = prepMsg(intermediate);
        assert actual < 1 : errMsg;
    }

    /**
     * Asserts a given number is not positive. The number may be &minus;0.0 or 
     * 0.0 without failing the assertion. It may also be NaN and not fail the 
     * assertion (remember that NaN is neither positive nor negative even though 
     * the bit pattern may be negative, zero or positive), and obviously 
     * &minus;&infin; won't either. However, +&infin; will fail the assertion.
     * @param actual The number to be checked. For example, 10.843979291045144. 
     * This number will be included in the test failure explanation if the test 
     * fails because of this assertion.
     */
    public static void assertNotPositive(double actual) {
        assertNotPositive(actual, "");
    }

    /**
     * Asserts a given number is not positive. The number may be &minus;0.0 or 
     * 0.0 without failing the assertion. It may also be NaN and not fail the 
     * assertion (remember that NaN is neither positive nor negative even though 
     * the bit pattern may be negative, zero or positive), and obviously 
     * &minus;&infin; won't either. However, +&infin; will fail the assertion.
     * @param actual The number to be checked. For example, 10.843979291045144.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The actual value will be included in 
     * the test failure explanation.
     */
    public static void assertNotPositive(double actual, String msg) {
        if (Double.isNaN(actual)) {
            return;
        }
        String intermediate = msg + ". Number " + actual 
                + " expected to not be positive";
        String errMsg = prepMsg(intermediate);
        assert actual <= 0.0 : errMsg;
    }

    /**
     * Asserts that an integer is equal to zero. This has the same effect as an 
     * {@link #assertEquals(long, long) assertEquals()} with 0 as the 
     * <code>expected</code> value, though the test failure messages might 
     * differ in their wording. The number actual number and the expected value 
     * of 0 will be appended to the test failure explanation.
     * @param actual The number to assert is equal to 0. For example, 133.
     */
    public static void assertZero(long actual) {
        assertZero(actual, "");
    }

    /**
     * Asserts that an integer is equal to zero. This has the same effect as an 
     * {@link #assertEquals(long, long, String) assertEquals()} with 0 as the 
     * <code>expected</code> value, though the test failure messages might 
     * differ in their wording.
     * @param actual The number to assert is equal to 0. For example, 133.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The number <code>actual</code> and 
     * the expected value of 0 will be appended to the test failure explanation.
     */
    public static void assertZero(long actual, String msg) {
        String intermediate = msg + ". Number " + actual + " expected to be 0";
        String errMsg = prepMsg(intermediate);
        assert actual == 0 : errMsg;
    }

    /**
     * Asserts that a 64-bit floating point number is equal to 0.0 (or to 
     * &minus;0.0). However, if a nonzero subnormal number is acceptable to pass 
     * the test, then use {@link #assertEquals(double, double, double)} with a 
     * small delta or {@link #assertEquals(double, double)} which uses {@link 
     * #DEFAULT_TEST_DELTA} as the delta instead. The default test failure 
     * explanation will include the numbers <code>actual</code> and 0.0.
     * @param actual The floating point number to assert is equal to &pm;0.0. 
     * For example, &minus;0.0078125.
     */
    public static void assertZero(double actual) {
        assertZero(actual, "");
    }

    /**
     * Asserts that a 64-bit floating point number is equal to 0.0 (or to 
     * &minus;0.0). However, if a nonzero subnormal number is acceptable to pass 
     * the test, then use {@link #assertEquals(double, double, double, String)} 
     * with a small delta or {@link #assertEquals(double, double, String)} which 
     * uses {@link #DEFAULT_TEST_DELTA} as the delta instead.
     * @param actual The floating point number to assert is equal to &pm;0.0. 
     * For example, &minus;0.0078125.
     * @param msg The message for the test failure explanation. The numbers 
     * <code>actual</code> and 0.0 will be appended to this message.
     */
    public static void assertZero(double actual, String msg) {
        String intermediate = msg + ". Number " + actual 
                + " expected to be 0.0";
        String errMsg = prepMsg(intermediate);
        assert actual == 0.0 : errMsg;
    }

    /**
     * Asserts a given number is not negative. The number may be 0 without 
     * failing the assertion. The number will be included in the test failure 
     * explanation if the test fails because of this assertion.
     * @param actual The number to be checked. For example, &minus;103.
     */
    public static void assertNotNegative(long actual) {
        assertMinimum(0, actual);
    }

    /**
     * Asserts a given number is not negative. The number may be 0 without 
     * failing the assertion.
     * @param actual The number to be checked. For example, &minus;103.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The actual value will be appended to 
     * the test failure explanation.
     */
    public static void assertNotNegative(long actual, String msg) {
        assertMinimum(0, actual, msg);
    }

    /**
     * Asserts a given number is not negative. The number may be -0.0 or 0.0 
     * without failing the assertion.
     * @param actual The number to be checked. For example, 10.843979291045144.
     */
    public static void assertNotNegative(double actual) {
        assertNotNegative(actual, "");
    }

    /**
     * Asserts a given number is not negative. The number may be &minus;0.0 or 
     * 0.0 without failing the assertion.
     * @param actual The number to be checked. For example, 10.843979291045144.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The actual value will be included in 
     * the test failure explanation.
     */
    public static void assertNotNegative(double actual, String msg) {
        if (Double.isNaN(actual)) {
            return;
        }
        String intermediate = msg + ". Number " + actual 
                + " expected to be at least 0.0";
        String errMsg = prepMsg(intermediate);
        assert actual >= 0.0 : errMsg;
    }

    /**
     * Asserts that an integer is positive. The number must be 1 or greater to 
     * pass the assertion.
     * @param actual The number to assert is positive. For example, &minus;347.
     */
    public static void assertPositive(long actual) {
        assertPositive(actual, "");
    }

    /**
     * Asserts that an integer is positive. The number must be 1 or greater to 
     * pass the assertion.
     * @param actual The number to assert is positive. For example, &minus;347.
     * @param msg The message for the test failure explanation.
     */
    public static void assertPositive(long actual, String msg) {
        String intermediate = msg + ". Number " + actual 
                + " expected to be greater than 0";
        String errMsg = prepMsg(intermediate);
        assert actual > 0 : errMsg;
    }

    /**
     * Asserts that a floating point number is positive. If the test fails, the 
     * actual number and the threshold 0.0 will be included in the test failure 
     * explanation, or, in the case of NaN, the explanation will say that NaN 
     * "is not considered negative, zero or positive."
     * <p>Due to the vagaries of floating point, positive subnormal numbers 
     * might be erroneously regarded as not positive.</p>
     * <p>Other special cases to be aware of:</p>
     * <ul>
     * <li>Positive infinity should not fail the assertion, same as most finite 
     * positive numbers.</li>
     * <li>Negative zero, an oddity of the floating point specification, should 
     * nevertheless be considered not positive.</li>
     * <li>Positive zero should of course fail the assertion.</li>
     * <li>Negative infinity should fail the assertion, same as finite negative 
     * numbers.</li>
     * </ul>
     * @param actual The number to check. For example, &minus;2.6065827580858707 
     * &times; 10<sup>8</sup>.
     */
    public static void assertPositive(double actual) {
        assertPositive(actual, "");
    }

    /**
     * Asserts that a floating point number is positive. However, due to the 
     * vagaries of floating point, positive subnormal numbers might be 
     * erroneously regarded as not positive.
     * <p>Other special cases to be aware of:</p>
     * <ul>
     * <li>Positive infinity should not fail the assertion, same as most finite 
     * positive numbers.</li>
     * <li>Negative zero, an oddity of the floating point specification, should 
     * nevertheless be considered not positive.</li>
     * <li>Positive zero should of course fail the assertion.</li>
     * <li>Negative infinity should fail the assertion, same as finite negative 
     * numbers.</li>
     * <li>NaN should fail the assertion even if the bit pattern is positive. 
     * And in any case, it's difficult to access NaN values other than the 
     * "canonical" NaN through the Java Virtual Machine.</li>
     * </ul>
     * @param actual The number to check. For example, &minus;2.6065827580858707 
     * &times; 10<sup>8</sup>.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The number <code>actual</code> and 
     * the threshold 0.0 will be appended to the test failure explanation, or, 
     * in the case of NaN, the appended explanation will say that NaN "is not 
     * considered negative, zero or positive."
     */
    public static void assertPositive(double actual, String msg) {
        if (Double.isNaN(actual)) {
            String intermediate = msg + ". Number " + actual 
                    + " is not considered negative, zero or positive";
            String errMsg = prepMsg(intermediate);
            throw new AssertionError(errMsg);
        }
        String intermediate = msg + ". Number " + actual 
                + " expected to be greater than 0.0";
        String errMsg = prepMsg(intermediate);
        assert actual > 0.0 : errMsg;
    }

    // TODO: Write tests for this
    public static void assertMaximum(long actual, long maximum) {
        String msg = "Sorry, default message not implemented yet";
        assertMaximum(actual, maximum, msg);
    }
    
    // TODO: Write tests for this
    public static void assertMaximum(long actual, long maximum, String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertMaximum(double actual, double maximum) {
        String msg = "Sorry, default message not implemented yet";
        assertMaximum(actual, maximum, msg);
    }

    // TODO: Write tests for this
    public static void assertMaximum(double actual, double maximum,  
            String msg) {
        //
    }

    // TODO: Write tests for this
    public static <T extends Comparable<T>> void assertMaximum(T actual, 
            T maximum) {
        String msg = "Sorry, default message not implemented yet";
        assertMaximum(actual, maximum, msg);
    }

    // TODO: Write tests for this
    public static <T extends Comparable<T>> void assertMaximum(T actual, 
            T maximum, String msg) {
        //
    }

    // TODO: Write tests for this
    public static void assertNaN(double actual) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertNaN(double actual, String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertNotNaN(double actual) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertNotNaN(double actual, String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertInRange(long minimum, long actual, long maximum) {
        String msg = "Sorry, default message not implemented yet";
        assertInRange(minimum, actual, maximum, msg);
    }
    
    // TODO: Write tests for this
    public static void assertInRange(long minimum, long actual, long maximum, 
            String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static void assertInRange(double minimum, double actual, 
            double maximum) {
        String msg = "Sorry, default message not implemented yet";
        assertInRange(minimum, actual, maximum, msg);
    }
    
    // TODO: Write tests for this
    public static void assertInRange(double minimum, double actual, 
            double maximum, String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static <T extends Comparable<T>> void assertInRange(T minimum, 
            T actual, T maximum) {
        String msg = "Sorry, default message not implemented yet";
        assertInRange(minimum, actual, maximum, msg);
    }
    
    // TODO: Write tests for this
    public static <T extends Comparable<T>> void assertInRange(T minimum, 
            T actual, T maximum, String msg) {
        //
    }
    
    // TODO: Write tests for this
    public static <E> void assertSameElements(Collection<E> expected, 
            Collection<E> actual) {
        assertSameElements(expected, actual, 
                "Sorry, default message not implemented yet");
    }
    
    public static <E> void assertSameElements(Collection<E> expected, 
            Collection<E> actual, String msg) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public static <E> void assertSameOrder(List<E> expected, List<E> actual) {
        assertSameElements(expected, actual, 
                "Sorry, default message not implemented yet");
    }
    
    public static <E> void assertSameOrder(List<E> expected, List<E> actual, 
            String msg) {
        // TODO: Write tests for this
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
     * Asserts that a given lambda caused an exception of a particular type. If 
     * the test fails because no exception was thrown or an exception of the 
     * wrong type was thrown, a default message to that effect will be provided.
     * @param <E> The type of exception that should be thrown. For example, 
     * <code>ArithmeticException</code>.
     * @param lambda The anonymous procedure that should throw the exception of 
     * the specified type. For example, <code>() -> { Fraction badResult = 
     * oneHalf.divides(zero); }</code>. It would be very similar in Scala.
     * @param exceptionType A <code>Class</code> object for the expected 
     * exception type. For example, <code>ArithmeticException.class</code>. Note 
     * that in Scala this would be <code>classOf[ArithmeticException]</code>.
     * @return An exception of the specified type. However, this is not 
     * guaranteed if assertions are not enabled. Then again, this whole class is 
     * useless if assertions are not enabled. 
     * @throws NullPointerException If <code>lambda</code>, 
     * <code>exceptionType</code> or <code>msg</code> is null.
     */
    public static <E extends Exception> E assertThrows(Procedure lambda, 
            Class<E> exceptionType) {
        return assertThrows(lambda, exceptionType, "");
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
            String intermediate = msg + ". Expected " + exceptionType.getName() 
                    + " but nothing was thrown";
            String errMsg = prepMsg(intermediate);
            throw new AssertionError(errMsg);
        } catch (Exception e) {
            String intermediate = msg + ". Expected " + exceptionType.getName() 
                    + " but was " + e.getClass().getName(); 
            String errMsg = prepMsg(intermediate);
            if (!exceptionType.isAssignableFrom(e.getClass())) {
                throw new AssertionError(errMsg, e);
            };
            return (E) e;
        }
    }

    /**
     * Asserts that a given lambda does not throw exceptions of any type. A test 
     * failure explanation will be provided if any exception arises.
     * @param lambda The anonymous procedure that should not throw any 
     * exceptions. For example, <code>() -> { arrayBackedList.expandCapacity; 
     * }</code>.
     * @throws NullPointerException If <code>lambda</code> or <code>msg</code> 
     * is null.
     */
    public static void assertDoesNotThrow(Procedure lambda) {
        assertDoesNotThrow(lambda, "");
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
            String intermediate = msg 
                    + ". No exception should have occurred but " 
                    + e.getClass().getName() + " did";
            String errMsg = prepMsg(intermediate);
            throw new AssertionError(errMsg, e);
        }
    }
    
    public static void assertTimeout(Procedure lambda, Duration allottedTime, 
            String msg) {
        // TODO: Write tests for this
    }

}
