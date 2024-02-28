package org.testframe.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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
     * 1.52587890625 &times; 10<sup>&minus;5</sup>. This might be too little for  
     * most purposes, but it's better than 0.0 or any subnormal value.
     */
    public static final double DEFAULT_TEST_DELTA = -(0.5 / Short.MIN_VALUE);
    
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
     * <p>Note however that the test will fail if both of the floating point 
     * values are NaN, regardless of their bit patterns.</p>
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
     * <p>Note however that the test will fail if both of the floating point 
     * values are NaN, regardless of their bit patterns.</p>
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
     * <p>Note however that the test will fail if both of the floating point 
     * values are NaN, regardless of their bit patterns.</p>
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
     * <p>Note however that the test will fail if both of the floating point 
     * values are NaN, regardless of their bit patterns.</p>
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
    
    /**
     * Asserts that two arrays of 64-bit floating point values have the same 
     * numbers in the same order within {@link #DEFAULT_TEST_DELTA}. This 
     * procedure stops at the first evidence of failure. First, it checks that 
     * the arrays are of the same length. If they are, it proceeds to compare 
     * the numbers index by index, stopping on finding a difference in excess of 
     * the variance even if there are more numbers to compare. The test failure 
     * explanation will depend on how far along the process came along.
     * <p>Note however that the test will fail if both of the floating point 
     * values at a given index are NaN, regardless of their bit patterns.</p>
     * @param expected The array of expected values. For example, an array 
     * containing 4.0, 3.0, 3.25, 3.16 in that order.
     * @param actual The array of actual values. For example, an array  
     * containing 4.0, 3.0, 3.2507, 3.15999 in that order.
     */
    public static void assertEquals(double[] expected, double[] actual) {
        assertEquals(expected, actual, DEFAULT_TEST_DELTA, "");
    }
    
    /**
     * Asserts that two arrays of 64-bit floating point values have the same 
     * numbers in the same order within a specified variance. This procedure 
     * stops at the first evidence of failure. First, it checks that the arrays 
     * are of the same length. If they are, it proceeds to compare the numbers 
     * index by index, stopping on finding a difference in excess of the 
     * variance even if there are more numbers to compare. The test failure 
     * explanation will depend on how far along the process came along.
     * <p>Note however that the test will fail if both of the floating point 
     * values at a given index are NaN, regardless of their bit patterns.</p>
     * @param expected The array of expected values. For example, an array 
     * containing 4.0, 3.0, 3.25, 3.16 in that order.
     * @param actual The array of actual values. For example, an array  
     * containing 4.0, 3.0, 3.2507, 3.15999 in that order.
     * @param delta The maximum allowed variance for the numbers in 
     * <code>expected</code> and <code>actual</code> to differ and still be 
     * considered close enough to be equal. For example, 0.00001. Ought to be at 
     * least 0.0 but is preferably positive and not subnormal, though less than 
     * 1.0. Negative variances are not recommended, and the behavior is not at 
     * all guaranteed to remain consistent from one version of this framework to 
     * the next.
     */
    public static void assertEquals(double[] expected, double[] actual, 
            double delta) {
        assertEquals(expected, actual, delta, "");
    }
    
    /**
     * Asserts that two arrays of 64-bit floating point values have the same 
     * numbers in the same order within {@link #DEFAULT_TEST_DELTA}. This 
     * procedure stops at the first evidence of failure. First, it checks that 
     * the arrays are of the same length. If they are, it proceeds to compare 
     * the numbers index by index, stopping on finding a difference in excess of 
     * the variance even if there are more numbers to compare. 
     * <p>Note however that the test will fail if both of the floating point 
     * values at a given index are NaN, regardless of their bit patterns.</p>
     * @param expected The array of expected values. For example, an array 
     * containing 4.0, 3.0, 3.25, 3.16 in that order.
     * @param actual The array of actual values. For example, an array  
     * containing 4.0, 3.0, 3.2507, 3.15999 in that order.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails. For example, "Numbers should converge to &pi;."
     */
    public static void assertEquals(double[] expected, double[] actual, 
            String msg) {
        assertEquals(expected, actual, DEFAULT_TEST_DELTA, msg);
    }
    
    /**
     * Asserts that two arrays of 64-bit floating point values have the same 
     * numbers in the same order within a specified variance. This procedure 
     * stops at the first evidence of failure. First, it checks that the arrays 
     * are of the same length. If they are, it proceeds to compare the numbers 
     * index by index, stopping on finding a difference in excess of the 
     * variance even if there are more numbers to compare. 
     * <p>Note however that the test will fail if both of the floating point 
     * values at a given index are NaN, regardless of their bit patterns.</p>
     * @param expected The array of expected values. For example, an array 
     * containing 4.0, 3.0, 3.25, 3.16 in that order.
     * @param actual The array of actual values. For example, an array  
     * containing 4.0, 3.0, 3.2507, 3.15999 in that order.
     * @param delta The maximum allowed variance for the numbers in 
     * <code>expected</code> and <code>actual</code> to differ and still be 
     * considered close enough to be equal. For example, 0.00001. Ought to be at 
     * least 0.0 but is preferably positive and not subnormal, though less than 
     * 1.0. Negative variances are not recommended, and the behavior is not at 
     * all guaranteed to remain consistent from one version of this framework to 
     * the next.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails. For example, "Numbers should converge to &pi;."
     */
    public static void assertEquals(double[] expected, double[] actual, 
            double delta, String msg) {
        int expLen = expected.length;
        int actLen = actual.length;
        String lenMsg = msg + ". Arrays differ in length: expected has " 
                + expLen + " elements but actual has " + actLen + " elements";
        String intermediateLenMsg = prepMsg(lenMsg);
        assert expLen == actLen : intermediateLenMsg;
        for (int i = 0; i < expLen; i++) {
            double difference = Math.abs(expected[i] - actual[i]);
            String intermediate = msg + ". Arrays first differ at index " + i 
                    + ", expected at least " + (expected[i] - delta) 
                    + " or at most " + (expected[i] + delta) + " but was " 
                    + actual[i];
            String errMsg = prepMsg(intermediate);
            assert delta >= difference : errMsg;
        }
    }
    
    /**
     * Asserts that two arrays of <code>Object</code> instances are equal. The 
     * elements are the same and in the same order. A test failure explanation 
     * will be given if the test fails for the arrays being of different lengths 
     * or for having a different element.
     * @param expected The expected array. For example, the colors cyan, 
     * magenta, yellow and black.
     * @param actual The actual array. For example, the colors cyan, black, 
     * yellow and magenta.
     */
    public static void assertEquals(Object[] expected, Object[] actual) {
        assertEquals(expected, actual, "");
    }
    
    /**
     * Asserts that two arrays of <code>Object</code> instances are equal. The 
     * elements are the same and in the same order. 
     * @param expected The expected array. For example, the colors cyan, 
     * magenta, yellow and black.
     * @param actual The actual array. For example, the colors cyan, black, 
     * yellow and magenta.
     * @param msg A message to include in the test failure explanation if the 
     * test fails.
     */
    public static void assertEquals(Object[] expected, Object[] actual, 
            String msg) {
        int expLen = expected.length;
        int actLen = actual.length;
        String diffLenMsgIntermediate = msg 
                + ". Arrays differ in length: expected has " + expLen 
                + " elements but actual has " + actLen + " elements";
        String diffLenMsg = prepMsg(diffLenMsgIntermediate);
        assert expLen == actLen : diffLenMsg;
        int index = 0;
        boolean equalSoFar = true;
        while (index < expLen && equalSoFar) {
            equalSoFar = expected[index].equals(actual[index]);
            index++;
        }
        String intermediate = msg + ". Expected " + Arrays.toString(expected) 
                + " but was " + Arrays.toString(actual);
        String errMsg = prepMsg(intermediate);
        assert equalSoFar : errMsg;
    }
    
    // No assertArrayEquals will be provided. Use assertEquals.

    // No assertTrue will be provided. Use plain Java assert.
    
    // No assertFalse will be provided. Use plain Java assert.
    
    /**
     * Asserts that an object is null. If the object is not null, the test 
     * failure explanation will include the object's <code>toString()</code>.
     * <p>To assert that an object is <em>not</em> null, use a plain Java assert 
     * with an appropriate message.</p>
     * @param object The object to assert is null. For example, a field that is 
     * not supposed to be initialized at construction time.
     */
    public static void assertNull(Object object) {
        assertNull(object, "");
    }
    
    /**
     * Asserts that an object is null. To assert that an object is <em>not</em> 
     * null, use a plain Java assert with an appropriate message.
     * @param object The object to assert is null. For example, a field that is 
     * not supposed to be initialized at construction time.
     * @param msg A message for the test failure explanation, to which will be 
     * appended a message saying a null object was expected but instead a 
     * non-null object was found, if the test fails.
     */
    public static void assertNull(Object object, String msg) {
        if (object != null) {
            String intermediate = msg + ". Expected null object but found " 
                    + object.toString();
            String errMsg = prepMsg(intermediate);
            throw new AssertionError(errMsg);
        }
    }
    
    // No assertNotNull will provided. Use plain Java assert.
    
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

    /**
     * Asserts an integer is not greater than a specified maximum. The test 
     * failure explanation will include the expected maximum and the actual 
     * value if the assertion fails.
     * @param actual The number to test for not going over the maximum. For 
     * example, 1000.
     * @param maximum The maximum that <code>actual</code> can be without 
     * failing the assertion. For example, 973.
     */
    public static void assertMaximum(long actual, long maximum) {
        assertMaximum(actual, maximum, "");
    }
    
    /**
     * Asserts an integer is not greater than a specified maximum.
     * @param actual The number to test for not going over the maximum. For 
     * example, 1000.
     * @param maximum The maximum that <code>actual</code> can be without 
     * failing the assertion. For example, 973.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     */
    public static void assertMaximum(long actual, long maximum, String msg) {
        String intermediate = msg + ". Value " + actual 
                + " expected to be at most " + maximum;
        String errMsg = prepMsg(intermediate);
        assert maximum >= actual : errMsg;
    }
    
    /**
     * Asserts a floating point number is less than or equal to a specified 
     * maximum. The maximum and the actual number will be included in the test 
     * failure explanation if the assertion fails.
     * @param actual The number to assert is less than a given maximum. For 
     * example, 99.890773.
     * @param maximum The number <code>actual</code> is expected to be less than 
     * or equal to. For example, 100.0.
     */
    public static void assertMaximum(double actual, double maximum) {
        assertMaximum(actual, maximum, "");
    }

    /**
     * Asserts a floating point number is less than or equal to a specified 
     * maximum.
     * @param actual The number to assert is less than a given maximum. For 
     * example, 99.890773.
     * @param maximum The number <code>actual</code> is expected to be less than 
     * or equal to. For example, 100.0.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     */
    public static void assertMaximum(double actual, double maximum,  
            String msg) {
        String intermediate = msg + ". Value " + actual 
                + " expected to be at most " + maximum;
        String errMsg = prepMsg(intermediate);
        assert maximum >= actual : errMsg;
    }

    /**
     * Asserts that the value held by a <code>Comparable</code> object is 
     * less than or equal to a specified maximum. The test failure explanation 
     * will include the actual value and the maximum if the assertion fails.
     * @param <T> The type of the <code>maximum</code> and <code>actual</code> 
     * parameters. Must implement <code>Comparable&lt;T&gt;</code>. For example, 
     * <code>Fraction implements Comparable&lt;Fraction&gt;</code>.
     * @param minimum The maximum permissible value. For example, 
     * <sup>3</sup>&frasl;<sub>2</sub>.
     * @param actual The value to compare against the specified maximum. For 
     * example, <sup>25</sup>&frasl;<sub>16</sub>.
     */
    public static <T extends Comparable<T>> void assertMaximum(T actual, 
            T maximum) {
        assertMaximum(actual, maximum, "");
    }

    /**
     * Asserts that the value held by a <code>Comparable</code> object is 
     * less than or equal to a specified maximum.
     * @param <T> The type of the <code>maximum</code> and <code>actual</code> 
     * parameters. Must implement <code>Comparable&lt;T&gt;</code>. For example, 
     * <code>Fraction implements Comparable&lt;Fraction&gt;</code>.
     * @param minimum The maximum permissible value. For example, 
     * <sup>3</sup>&frasl;<sub>2</sub>.
     * @param actual The value to compare against the specified maximum. For 
     * example, <sup>25</sup>&frasl;<sub>16</sub>.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion. The maximum and actual values will 
     * be appended to the test failure explanation.
     */
    public static <T extends Comparable<T>> void assertMaximum(T actual, 
            T maximum, String msg) {
        String intermediate = msg + ". Value " + actual.toString() 
                + " expected to be at most " + maximum.toString();
        String errMsg = prepMsg(intermediate);
        assert maximum.compareTo(actual) >= 0 : errMsg;
    }

    /**
     * Asserts that a 64-bit floating point number is NaN (not a number). Don't 
     * use {@link #assertEquals(double, double) assertEquals()} because NaN is 
     * never equal to itself. The test failure explanation will include NaN and 
     * the actual number if the test fails.
     * @param actual The number to assert is NaN. For example, negative 
     * infinity.
     */
    public static void assertNaN(double actual) {
        assertNaN(actual, "");
    }
    
    /**
     * Asserts that a 64-bit floating point number is NaN (not a number). Note 
     * that if the number to be checked is indeed NaN, it will almost certainly 
     * be the "canonical" NaN. Don't use {@link #assertEquals(double, double, 
     * String) assertEquals()} because no NaN is ever equal to itself, not even 
     * canonical NaN.
     * @param actual The number to assert is NaN. For example, positive 
     * infinity.
     * @param msg The message to put into the test failure explanation if the 
     * test fails because of the assertion.
     */
    public static void assertNaN(double actual, String msg) {
        String intermediate = msg + ". Number " + actual 
                + " expected to be NaN";
        String errMsg = prepMsg(intermediate);
        assert Double.isNaN(actual) : errMsg;
    }
    
    /**
     * Asserts that a 64-bit floating point number is not NaN (not a number). 
     * The test failure explanation will state that the number is expected to 
     * not be NaN.
     * @param actual The number to assert is not NaN. For example, 
     * <code>Math.PI</code>.
     */
    public static void assertNotNaN(double actual) {
        assertNotNaN(actual, "");
    }
    
    /**
     * Asserts that a 64-bit floating point number is not NaN (not a number).
     * @param actual The number to assert is not NaN. For example, 
     * <code>Math.PI</code>.
     * @param msg The message to be included in the test failure explanation if 
     * the number is indeed NaN.
     */
    public static void assertNotNaN(double actual, String msg) {
        String intermediate = msg + ". Number " + actual 
                + " expected to not be NaN";
        String errMsg = prepMsg(intermediate);
        assert !Double.isNaN(actual) : errMsg;
    }
    
    /**
     * Asserts that an integer is in a given range. Using this assertion is 
     * similar to combining {@link #assertMinimum(long, long)} and {@link 
     * #assertMaximum(long, long)} in a single test, but with this assertion, if 
     * the assertion fails, the test failure explanation will include both the 
     * minimum and the maximum regardless of whether or not the failure was for 
     * the actual number being too low or too high.
     * @param minimum The lowest number that <code>actual</code> can be without 
     * failing the test. For example, &minus;163. If this parameter is 0 or 1 
     * and <code>maximum</code> is <code>Long.MAX_VALUE</code>, then it might be 
     * better to use {@link #assertNotNegative(long)} or {@link 
     * #assertPositive(long)} instead. This parameter may be equal to 
     * <code>maximum</code>, but must not be greater.
     * @param actual The number said to be between <code>minimum</code> and 
     * <code>maximum</code>. For example, &minus;1. This number may be equal to 
     * either <code>minimum</code> or <code>maximum</code> and still not cause 
     * the assertion to fail.
     * @param maximum The highest number that <code>actual</code> can be without 
     * failing the test. For example, 73. This parameter is allowed to be equal 
     * to <code>minimum</code>, but generally it makes more sense to use {@link 
     * #assertEquals(long, long)} in that case. This parameter must not be less 
     * than <code>minimum</code>.
     * @throws IllegalArgumentException If <code>minimum</code> is greater than 
     * <code>maximum</code>, without regard for what <code>actual</code> is. The 
     * exception message will include <code>minimum</code> and 
     * <code>maximum</code> but not <code>actual</code>.
     */
    public static void assertInRange(long minimum, long actual, long maximum) {
        assertInRange(minimum, actual, maximum, "");
    }
    
    /**
     * Asserts that an integer is in a given range. Using this assertion is 
     * similar to combining {@link #assertMinimum(long, long, String)} and 
     * {@link #assertMaximum(long, long, String)} in a single test, but with  
     * this assertion, if the assertion fails, the test failure explanation will 
     * include both the minimum and the maximum regardless of whether or not the 
     * failure was for the actual number being too low or too high.
     * @param minimum The lowest number that <code>actual</code> can be without 
     * failing the test. For example, &minus;163. If this parameter is 0 or 1 
     * and <code>maximum</code> is <code>Long.MAX_VALUE</code>, then it might be 
     * better to use {@link #assertNotNegative(long, String)} or {@link 
     * #assertPositive(long, String)} instead. This parameter may be equal to 
     * <code>maximum</code>, but must not be greater.
     * @param actual The number said to be between <code>minimum</code> and 
     * <code>maximum</code>. For example, &minus;1. This number may be equal to 
     * either <code>minimum</code> or <code>maximum</code> and still not cause 
     * the assertion to fail.
     * @param maximum The highest number that <code>actual</code> can be without 
     * failing the test. For example, 73. This parameter is allowed to be equal 
     * to <code>minimum</code>, but generally it makes more sense to use {@link 
     * #assertEquals(long, long, String)} in that case. This parameter must not 
     * be less than <code>minimum</code>.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     * @throws IllegalArgumentException If <code>minimum</code> is greater than 
     * <code>maximum</code>, without regard for what <code>actual</code> is. The 
     * exception message will include <code>minimum</code> and 
     * <code>maximum</code> but not <code>actual</code>.
     */
    public static void assertInRange(long minimum, long actual, long maximum, 
            String msg) {
        if (minimum > maximum) {
            String excMsg = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            throw new IllegalArgumentException(excMsg);
        }
        String intermediate = msg + ". Expected " + actual 
                + " to be in range from " + minimum + " to " + maximum;
        String errMsg = prepMsg(intermediate);
        boolean inRange = minimum <= actual && actual <= maximum;
        assert inRange : errMsg;
    }
    
    /**
     * Asserts that a floating point number is in a given range. In most cases, 
     * the test failure explanation will include the minimum and the maximum.
     * @param minimum The lowest number that <code>actual</code> can be without 
     * failing the test. For example, &minus;0.5. Negative infinity may be used, 
     * but in that case it might make more sense to use {@link 
     * #assertMaximum(double, double, String) assertMaximum()} instead.
     * @param actual The actual number. For example, 0.0.
     * @param maximum The highest number that <code>actual</code> can be without 
     * failing the test. For example, 0.5. Positive infinity may be used, but in 
     * that case it might make more sense to use {@link #assertMinimum(double, 
     * double, String) assertMinimum()} instead.
     * @throws IllegalArgumentException If either <code>minimum</code> or 
     * <code>maximum</code> is NaN, or if <code>minimum</code> is greater than 
     * <code>maximum</code>, without regard for the variance.
     */
    public static void assertInRange(double minimum, double actual, 
            double maximum) {
        assertInRange(minimum, actual, maximum, DEFAULT_TEST_DELTA, "");
    }
    
    /**
     * Asserts that a floating point number is in a given range. In most cases, 
     * the test failure explanation will include the minimum and the maximum.
     * @param minimum The lowest number that <code>actual</code> can be without 
     * failing the test. For example, &minus;0.5. Negative infinity may be used, 
     * but in that case it might make more sense to use {@link 
     * #assertMaximum(double, double, String) assertMaximum()} instead.
     * @param actual The actual number. For example, 0.0.
     * @param maximum The highest number that <code>actual</code> can be without 
     * failing the test. For example, 0.5. Positive infinity may be used, but in 
     * that case it might make more sense to use {@link #assertMinimum(double, 
     * double, String) assertMinimum()} instead.
     * @param delta How much variance is allowed for <code>actual</code> to be 
     * below <code>minimum</code> or above <code>maximum</code> and still pass 
     * the test. For example, 0.1.
     * @throws IllegalArgumentException If either <code>minimum</code> or 
     * <code>maximum</code> is NaN, or if <code>minimum</code> is greater than 
     * <code>maximum</code>, without regard for the variance.
     */
    public static void assertInRange(double minimum, double actual, 
            double maximum, double delta) {
        assertInRange(minimum, actual, maximum, delta, "");
    }
    
    /**
     * Asserts that a floating point number is in a given range. In most cases, 
     * the test failure explanation will include the minimum and the maximum. 
     * The default variance {@link #DEFAULT_TEST_DELTA} will be used.
     * @param minimum The lowest number that <code>actual</code> can be without 
     * failing the test. For example, &minus;0.5. Negative infinity may be used, 
     * but in that case it might make more sense to use {@link 
     * #assertMaximum(double, double, String) assertMaximum()} instead.
     * @param actual The actual number. For example, 0.0.
     * @param maximum The highest number that <code>actual</code> can be without 
     * failing the test. For example, 0.5. Positive infinity may be used, but in 
     * that case it might make more sense to use {@link #assertMinimum(double, 
     * double, String) assertMinimum()} instead.
     * @param msg A message to include in the test failure explanation.
     * @throws IllegalArgumentException If either <code>minimum</code> or 
     * <code>maximum</code> is NaN, or if <code>minimum</code> is greater than 
     * <code>maximum</code>, without regard for the variance.
     */
    public static void assertInRange(double minimum, double actual, 
            double maximum, String msg) {
        assertInRange(minimum, actual, maximum, DEFAULT_TEST_DELTA, msg);
    }
    
    /**
     * Asserts that a floating point number is in a given range. In most cases, 
     * the test failure explanation will include the minimum and the maximum.
     * @param minimum The lowest number that <code>actual</code> can be without 
     * failing the test. For example, &minus;0.5. Negative infinity may be used, 
     * but in that case it might make more sense to use {@link 
     * #assertMaximum(double, double, String) assertMaximum()} instead.
     * @param actual The actual number. For example, 0.0.
     * @param maximum The highest number that <code>actual</code> can be without 
     * failing the test. For example, 0.5. Positive infinity may be used, but in 
     * that case it might make more sense to use {@link #assertMinimum(double, 
     * double, String) assertMinimum()} instead.
     * @param delta How much variance is allowed for <code>actual</code> to be 
     * below <code>minimum</code> or above <code>maximum</code> and still pass 
     * the test. For example, 0.1.
     * @param msg A message to include in the test failure explanation.
     * @throws IllegalArgumentException If either <code>minimum</code> or 
     * <code>maximum</code> is NaN, or if <code>minimum</code> is greater than 
     * <code>maximum</code>, without regard for the variance.
     */
    public static void assertInRange(double minimum, double actual, 
            double maximum, double delta, String msg) {
        if (Double.isNaN(minimum) || Double.isNaN(maximum) 
                || Double.isNaN(delta)) {
            String excMsg = "Minimum, maximum, delta should not be NaN";
            throw new IllegalArgumentException(excMsg);
        }
        if (minimum > maximum) {
            String excMsg = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            throw new IllegalArgumentException(excMsg);
        }
        double adjustedMininum = minimum - delta;
        double adjustedMaxinum = maximum + delta;
        if (actual < adjustedMininum || adjustedMaxinum < actual) {
            String intermediate = "Expected " + actual + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " + delta;
            String errMsg = prepMsg(intermediate);
            throw new AssertionError(errMsg);
        }
    }
    
    /**
     * Asserts that an object is in a given range. Using this assertion is 
     * similar to combining {@link #assertMinimum(Comparable, Comparable)} and 
     * {@link #assertMaximum(Comparable, Comparable)} in a single test. But if 
     * this assertion fails, the test failure explanation will include both the 
     * minimum and the maximum, regardless of whether it's because the actual 
     * value was too low or too high. The actual value will be included as well.
     * @param <T> The type for <code>minimum</code>, <code>actual</code> and 
     * <code>maximum</code>. Must implement <code>Comparable&lt;T&gt;</code>. 
     * For example, <code>DayOfWeek</code>.    
     * @param minimum The lowest value <code>actual</code> can be and still pass 
     * the assertion. For example, Monday.
     * @param actual The actual value. For example, Tuesday.
     * @param maximum The highest value <code>actual</code> can be and still 
     * pass the assertion. For example, Friday.
     * @throws IllegalArgumentException If <code>minimum</code> is greater than 
     * <code>maximum</code>.
     */
    public static <T extends Comparable<T>> void assertInRange(T minimum, 
            T actual, T maximum) {
        assertInRange(minimum, actual, maximum, "");
    }
    
    /**
     * Asserts that an object is in a given range. Using this assertion is 
     * similar to combining {@link #assertMinimum(Comparable, Comparable, 
     * String)} and {@link #assertMaximum(Comparable, Comparable, String)} in a 
     * single test. But if this assertion fails, the test failure explanation 
     * will include both the minimum and the maximum, regardless of whether it's 
     * because the actual value was too low or too high. The actual value will 
     * be included as well.
     * @param <T> The type for <code>minimum</code>, <code>actual</code> and 
     * <code>maximum</code>. Must implement <code>Comparable&lt;T&gt;</code>. 
     * For example, <code>DayOfWeek</code>.    
     * @param minimum The lowest value <code>actual</code> can be and still pass 
     * the assertion. For example, Monday.
     * @param actual The actual value. For example, Tuesday.
     * @param maximum The highest value <code>actual</code> can be and still 
     * pass the assertion. For example, Friday.  
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails. For example, "Assigned day expected to be a weekday."
     * @throws IllegalArgumentException If <code>minimum</code> is greater than 
     * <code>maximum</code>.
     */
    public static <T extends Comparable<T>> void assertInRange(T minimum, 
            T actual, T maximum, String msg) {
        if (minimum.compareTo(maximum) > 0) {
            String excMsg = "Combination of minimum " + minimum.toString() 
                    + " and maximum " + maximum.toString() + " is invalid";
            throw new IllegalArgumentException(excMsg);
        }
        String intermediate = msg + ". Expected " + actual.toString() 
                + " to be in range from " + minimum.toString() + " to " 
                + maximum.toString();
        String errMsg = prepMsg(intermediate);
        assert actual.compareTo(minimum) >= 0 : errMsg;
        assert actual.compareTo(maximum) <= 0 : errMsg;
    }
    
    /**
     * Asserts that an integer value is different from some other integer value. 
     * The test failure explanation will include the number that was supposed to 
     * be different if the assertion fails.
     * @param some The integer the other integer should not be. For example, 
     * 5777096943112551843.
     * @param other The other integer. For example, &minus;6892550187484447930.
     */
    public static void assertDifferent(long some, long other) {
        assertDifferent(some, other, "");
    }
    
    /**
     * Asserts that an integer value is different from some other integer value.
     * @param some The integer the other integer should not be. For example, 
     * &minus;1674271114479202609.
     * @param other The other integer. For example, &minus;3348542228958405218.
     * @param msg A message for the test failure explanation. If the assertion 
     * fails, the explanation will include the number that was expected to be 
     * different.
     */
    public static void assertDifferent(long some, long other, String msg) {
        String intermediate = msg + ". Expected " + some 
                + " to be different from " + other;
        String errMsg = prepMsg(intermediate);
        assert some != other : errMsg;
    }
    
    /**
     * Asserts that a floating point value is different from some floating point 
     * value by more than {@link #DEFAULT_TEST_DELTA}. The actual and expected  
     * differences will be included in the test explanation failure if the test 
     * fails because of the assertion.
     * @param some A floating point value. For example, 3.141592653589793.
     * @param other A floating point value said to be different from 
     * <code>some</code> by more than the default variance. For example, 
     * 3.142857142857143.
     */
    public static void assertDifferent(double some, double other) {
         assertDifferent(some, other, DEFAULT_TEST_DELTA, "");
    }
    
    /**
     * Asserts that a floating point value is different from some floating point 
     * value by more than a specified variance. The actual and expected 
     * differences will be included in the test explanation failure if the test 
     * fails because of the assertion.
     * @param some A floating point value. For example, 3.141592653589793.
     * @param other A floating point value said to be different from 
     * <code>some</code> by more than a specified variance. For example, 
     * 3.142857142857143.
     * @param delta The maximum allowable variance. For example, 0.00125. Should 
     * almost never be a subnormal value (remember that 0.0 is subnormal). We 
     * make no promises whatsoever regarding the accuracy of assertions 
     * involving subnormal values.
     */
    public static void assertDifferent(double some, double other, 
            double delta) {
         assertDifferent(some, other, delta, "");
    }
    
    /**
     * Asserts that a floating point value is different from some floating point 
     * value by more than {@link #DEFAULT_TEST_DELTA}. The actual and expected  
     * differences will be included in the test explanation failure if the test 
     * fails because of the assertion.
     * @param some A floating point value. For example, 3.141592653589793.
     * @param other A floating point value said to be different from 
     * <code>some</code> by more than the default variance. For example, 
     * 3.142857142857143.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     */
    public static void assertDifferent(double some, double other, 
            String msg) {
         assertDifferent(some, other, DEFAULT_TEST_DELTA, msg);
    }
    
    /**
     * Asserts that a floating point value is different from some floating point 
     * value by more than a specified variance. The actual and expected 
     * differences will be included in the test explanation failure if the test 
     * fails because of the assertion.
     * @param some A floating point value. For example, 3.141592653589793.
     * @param other A floating point value said to be different from 
     * <code>some</code> by more than a specified variance. For example, 
     * 3.142857142857143.
     * @param delta The maximum allowable variance. For example, 0.00125. Should 
     * almost never be a subnormal value (remember that 0.0 is subnormal). We 
     * make no promises whatsoever regarding the accuracy of assertions 
     * involving subnormal values.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     */
    public static void assertDifferent(double some, double other, 
            double delta, String msg) {
        double difference = Math.abs(some - other);
        String intermediate = msg + ". Expected " + some + " to differ from " 
                + other + " by at least " + delta + ", values differ by " 
                + difference;
        String errMsg = prepMsg(intermediate);
        assert difference >= delta : errMsg;
    }
    
    /**
     * Asserts that two objects are different. Difference is determined by 
     * <code>equals()</code> returning false. The test failure explanation will 
     * include the object's <code>toString()</code> if the assertion fails.
     * @param some An object to test for difference to another object. For 
     * example, a <code>LocalDate</code> object for today's date. It is this 
     * parameter's <code>equals()</code> function that will be called, but we 
     * reserve the right to change this in a later version.
     * @param other An object to assert is not the same as <code>some</code>. 
     * Preferably of the same runtime type as <code>some</code>. For example, a 
     * <code>LocalDate</code> object for tomorrow's date.
     * @throws NullPointerException If either <code>some</code> or 
     * <code>other</code> is null.
     */
    public static void assertDifferent(Object some, Object other) {
        assertDifferent(some, other, "");
    }
    
    /**
     * Asserts that two objects are different. Difference is determined by 
     * <code>equals()</code> returning false.
     * @param some An object to test for difference to another object. For 
     * example, a <code>LocalDate</code> object for today's date. It is this 
     * parameter's <code>equals()</code> function that will be called, but we 
     * reserve the right to change this in a later version.
     * @param other An object to assert is not the same as <code>some</code>. 
     * Preferably of the same runtime type as <code>some</code>. For example, a 
     * <code>LocalDate</code> object for tomorrow's date.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     * @throws NullPointerException If either <code>some</code> or 
     * <code>other</code> is null.
     */
    public static void assertDifferent(Object some, Object other, 
            String msg) {
        String intermediate = msg + ". Expected " + some.toString() 
                + " to be different from " + other.toString();
        String errMsg = prepMsg(intermediate);
        assert !some.equals(other) : errMsg;
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(int[] some, int[] other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, msg);
    }
    
    // TODO: Write tests for this
    public static void assertDifferent(int[] some, int[] other, String msg) {
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
    
    // TODO: Write tests for this
    public static void assertDifferent(double[] some, double[] other, 
            double delta, String msg) {
    }
    
    // TODO: Write tests for this
    public static <E> void assertDifferent(E[] some, E[] other) {
         String msg = "Sorry, default message not implemented yet";
         assertDifferent(some, other, msg);
    }
    
    /**
     * Asserts that two arrays of objects are different in some way.
     * @param <E> The type of the two arrays. For example, <code>Pattern</code>. 
     * Remember that this is only checked at compile time.
     * @param some An array. For example, an array with a regular expression for 
     * e-mail addresses, a regular expression for Roman numerals, a regular 
     * expression for floating point numbers, and a null.
     * @param other
     * @param msg
     */
    public static <E> void assertDifferent(E[] some, E[] other, String msg) {
        if (some.length == other.length) {
            String errMsg = msg + ". Arrays " + Arrays.toString(some) + " and " 
                    + Arrays.toString(other) + " are not different as asserted";
            throw new AssertionError(errMsg);
        }
    }
    
    /**
     * Asserts the array contains the specified element. The test failure 
     * explanation will include the expected element and the elements contained 
     * in the array if the test fails because of the assertion.
     * @param <E> The type of elements in the array. For example, 
     * <code>DayOfWeek</code>.
     * @param expected The element expected to be in the array of type 
     * <code>E</code>. For example, Wednesday.
     * @param actual The array of type <code>E</code>. For example, the days 
     * Monday, Tuesday, Wednesday, Thursday and Friday.
     * @throws NullPointerException If <code>expected</code> is null and 
     * <code>actual</code> is not an empty array.
     */
    public static <E> void assertContains(E expected, E[] actual) {
        assertContains(expected, actual, "");
    }
    
    /**
     * Asserts that an array contains a specified element. The test failure 
     * explanation will include the expected element and the elements contained 
     * in the array if the test fails because of the assertion, along with a 
     * customized message.
     * @param <E> The type of elements in the array. For example, 
     * <code>DayOfWeek</code>.
     * @param expected The element expected to be in the array of type 
     * <code>E</code>. For example, Wednesday.
     * @param actual The array of type <code>E</code>. For example, the days 
     * Monday, Tuesday, Wednesday, Thursday and Friday.
     * @param msg A message to append to the test failure explanation if the 
     * test fails because of the assertion.
     * @throws NullPointerException If <code>expected</code> is null and 
     * <code>actual</code> is not an empty array.
     */
    public static <E> void assertContains(E expected, E[] actual, String msg) {
        boolean found = false;
        int index = 0;
        int len = actual.length;
        while (!found && index < len) {
            found = expected.equals(actual[index]);
            index++;
        }
        String intermediate = msg + ". Expected element " + expected.toString() 
                + " to be in " + Arrays.toString(actual);
        String errMsg = prepMsg(intermediate); 
        assert found : errMsg;
    }
    
    /**
     * Asserts that a list contains a specified element. The test failure 
     * explanation will include the expected element and the elements contained 
     * in the list if the test fails because of the assertion.
     * @param <E> The type of elements in the list. For example, 
     * <code>DayOfWeek</code>.
     * @param expected The element expected to be in the list of type 
     * <code>E</code>. For example, Wednesday.
     * @param actual The list of type <code>E</code>. For example, the days 
     * Monday, Tuesday, Wednesday, Thursday and Friday.
     * @throws NullPointerException If <code>expected</code> is null, regardless 
     * of whether or not <code>actual</code> is an empty list. Depending on user 
     * feedback, this behavior might be changed so that a null 
     * <code>expected</code> does not cause this exception.
     */
    public static <E> void assertContains(E expected, List<E> actual) {
        assertContains(expected, actual, "");
    }
    
    /**
     * Asserts that a list contains a specified element. The test failure 
     * explanation will include the expected element and the elements contained 
     * in the list if the test fails because of the assertion, along with a 
     * customized message.
     * @param <E> The type of elements in the list. For example, 
     * <code>DayOfWeek</code>.
     * @param expected The element expected to be in the list of type 
     * <code>E</code>. For example, Wednesday.
     * @param actual The list of type <code>E</code>. For example, the days 
     * Monday, Tuesday, Wednesday, Thursday and Friday.
     * @param msg A message to append to the test failure explanation if the 
     * test fails because of the assertion.
     * @throws NullPointerException If <code>expected</code> is null, regardless 
     * of whether or not <code>actual</code> is an empty list. Depending on user 
     * feedback, this behavior might be changed so that a null 
     * <code>expected</code> does not cause this exception.
     */
    public static <E> void assertContains(E expected, List<E> actual, 
            String msg) {
        String intermediate = msg + ". Expected element " + expected.toString() 
                + " to be in " + actual.toString();
        String errMsg = prepMsg(intermediate);
        assert actual.contains(expected) : errMsg;
    }
    
    /**
     * Asserts that a set contains a specified element. The test failure 
     * explanation will include the expected element and the elements contained 
     * in the set if the test fails because of the assertion.
     * @param <E> The type of elements in the set. For example, 
     * <code>Month</code>.
     * @param expected The element expected to be in the set of type 
     * <code>E</code>. For example, November.
     * @param actual The set of type <code>E</code>. For example, the months 
     * February, April, June, September and November.
     * @throws NullPointerException If <code>expected</code> is null, regardless 
     * of whether or not <code>actual</code> is an empty set. Depending on user 
     * feedback, this behavior might be changed so that a null 
     * <code>expected</code> does not cause this exception.
     */
    public static <E> void assertContains(E expected, Set<E> actual) {
        assertContains(expected, actual, "");
    }
    
    /**
     * Asserts that a set contains a specified element. The test failure 
     * explanation will include the expected element and the elements contained 
     * in the set if the test fails because of the assertion, along with a 
     * customized message.
     * @param <E> The type of elements in the set. For example, 
     * <code>Month</code>.
     * @param expected The element expected to be in the set of type 
     * <code>E</code>. For example, November.
     * @param actual The set of type <code>E</code>. For example, the months 
     * January, March, May, July, August, October and December.
     * @param msg A message to append to the test failure explanation if the 
     * test fails because of the assertion.
     * @throws NullPointerException If <code>expected</code> is null, regardless 
     * of whether or not <code>actual</code> is an empty set. Depending on user 
     * feedback, this behavior might be changed so that a null 
     * <code>expected</code> does not cause this exception.
     */
    public static <E> void assertContains(E expected, Set<E> actual, 
            String msg) {
        String intermediate = msg + ". Expected element " + expected.toString() 
                + " to be in " + actual.toString();
        String errMsg = prepMsg(intermediate);
        assert actual.contains(expected) : errMsg;
    }
    
    /**
     * Asserts that two arrays contain the same elements, though not necessarily 
     * in the same order or the same quantities. One array may have fewer 
     * elements than the other, but the assertion won't fail as long as every 
     * element that appears in one array appears at least once in the other 
     * array.
     * @param <E> The type of the elements in the two arrays. Remember that this 
     * is not checked at runtime. For example, <code>java.time.Month</code>.
     * @param expected The array with the elements the other array is supposed  
     * to contain. For example, <code>MARCH</code>, <code>APRIL</code>, 
     * <code>MAY</code> and <code>JUNE</code>.
     * @param actual The array to check against <code>expected</code>. For 
     * example, <code>APRIL</code>, <code>APRIL</code>, <code>JUNE</code>, 
     * <code>MARCH</code>, <code>MAY</code> and <code>MAY</code>.
     */
    public static <E> void assertContainsSame(E[] expected, E[] actual) {
        assertContainsSame(expected, actual, "");
    }
    
    /**
     * Asserts that two arrays contain the same elements, though not necessarily 
     * in the same order or the same quantities. One array may have fewer 
     * elements than the other, but the assertion won't fail as long as every 
     * element that appears in one array appears at least once in the other 
     * array.
     * @param <E> The type of the elements in the two arrays. Remember that this 
     * is not checked at runtime. For example, <code>java.time.Month</code>.
     * @param expected The array with the elements the other array is supposed  
     * to contain. For example, <code>MARCH</code>, <code>APRIL</code>, 
     * <code>MAY</code> and <code>JUNE</code>.
     * @param actual The array to check against <code>expected</code>. For 
     * example, <code>APRIL</code>, <code>APRIL</code>, <code>JUNE</code>, 
     * <code>MARCH</code>, <code>MAY</code> and <code>MAY</code>.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     */
    public static <E> void assertContainsSame(E[] expected, E[] actual, 
            String msg) {
        Set<E> expSet = new HashSet<>(expected.length);
        for (E expElem : expected) expSet.add(expElem);
        Set<E> actSet = new HashSet<>(actual.length);
        for (E actElem : actual) actSet.add(actElem);
        String intermediate = msg + ". Expected array to contain " 
                + Arrays.toString(expected) + " but actually contained "
                + Arrays.toString(actual);
        String errMsg = prepMsg(intermediate);
        assert expSet.equals(actSet) : errMsg;
    }
    
    /**
     * Asserts that two lists contain the same elements, though not necessarily 
     * in the same order or the same quantities. The two lists may be different 
     * implementations of <code>java.util.List</code>. One list may have fewer 
     * elements than the other, but the assertion won't fail as long as every 
     * element that appears in one list appears at least once in the other list.
     * @param <E> The type of the elements in the two lists. Remember that this 
     * is not checked at runtime. For example, <code>java.time.Month</code>.
     * @param expected The list with the elements the other list is supposed to 
     * contain. For example, <code>MARCH</code>, <code>APRIL</code>, 
     * <code>MAY</code> and <code>JUNE</code>.
     * @param actual The list to check against <code>expected</code>. For 
     * example, <code>APRIL</code>, <code>APRIL</code>, <code>JUNE</code>, 
     * <code>MARCH</code>, <code>MAY</code> and <code>MAY</code>.
     */
    public static <E> void assertContainsSame(List<E> expected, 
            List<E> actual) {
        assertContainsSame(expected, actual, "");
    }
    
    /**
     * Asserts that two lists contain the same elements, though not necessarily 
     * in the same order or the same quantities. The two lists may be different 
     * implementations of <code>java.util.List</code>. One list may have fewer 
     * elements than the other, but the assertion won't fail as long as every 
     * element that appears in one list appears at least once in the other list.
     * @param <E> The type of the elements in the two lists. Remember that this 
     * is not checked at runtime. For example, <code>java.time.Month</code>.
     * @param expected The list with the elements the other list is supposed to 
     * contain. For example, <code>MARCH</code>, <code>APRIL</code>, 
     * <code>MAY</code> and <code>JUNE</code>.
     * @param actual The list to check against <code>expected</code>. For 
     * example, <code>APRIL</code>, <code>APRIL</code>, <code>JUNE</code>, 
     * <code>MARCH</code>, <code>MAY</code> and <code>MAY</code>.
     * @param msg A message to include in the test failure explanation if the 
     * assertion fails.
     */
    public static <E> void assertContainsSame(List<E> expected, 
            List<E> actual, String msg) {
        Set<E> expSet = new HashSet<>(expected);
        Set<E> actSet = new HashSet<>(actual);
        String intermediate = msg + ". Expected list to contain " 
                + expected.toString() + " but actually contained " 
                + actual.toString();
        String errMsg = prepMsg(intermediate);
        assert expSet.equals(actSet) : errMsg;
    }
    
//     TODO: Write tests for this
    public static String assertPrintOut(Predicate<String> predicate, 
            Procedure lambda, String msg) {
        return "SORRY NOT IMPLEMENTED YET";
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
        // TODO: Write tests for assertTimeout()
    }

}
