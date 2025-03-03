package org.testframe.api;

import java.awt.Color;
import java.awt.font.NumericShaper;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Tests of the Asserters class. These are more elegant than the tests of 
 * testframe.engine.TestRunner, but still more clunky than the tests that can be
 * written once Asserters is fully tested and proven.
 * <p>Because of the tests for assertTimeout(), this test class could take as 
 * much as ten seconds to run.</p>
 * @author Alonso del Arte
 */
public class AssertersTest {
    
    /**
     * How many extra milliseconds to allow for tests of assertTimeout().
     */
    private static final int TIMEOUT_GRACE_PERIOD_MILLISECONDS = 1024;
    
    /**
     * Bit mask for a 64-bit "negative" NaN in which the sign bit, all the 
     * exponent bits and the highest mantissa bit are all on but all other bits 
     * are off.
     */
    private static final long NaN_MASK = -2251799813685248L;
    
    private static final double LOCAL_DELTA = 1.0 / 32;
    
    private static final double HALF_LOCAL_DELTA = LOCAL_DELTA / 2;
    
    private static final double TWICE_LOCAL_DELTA = LOCAL_DELTA * 2;
    
    private static final double HALF_DEFAULT_DELTA 
            = Asserters.DEFAULT_TEST_DELTA / 2;
    
    private static final double THOUSAND_TIMES_DEFAULT_DELTA 
            = Asserters.DEFAULT_TEST_DELTA * 1024;
    
    private static final Random RANDOM = new Random();
    
    private static final String EXAMPLE_ASSERTION_MESSAGE_PART 
            = "Customizable part of assertion message";
    
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
        assert failOccurred : "fail() should have caused test to fail";
    }
    
    @Test
    public void testAssertNotEqualsInt() {
        int someNumber = RANDOM.nextInt();
        int otherNumber = 2 * someNumber + 1;
        String msgStandardPart = "Expected = " + someNumber + ". Actual = " 
                + otherNumber;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
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
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
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
    public void testAssertNotEqualsLong() {
        long someNumber = RANDOM.nextLong();
        long otherNumber = 2 * someNumber + 1;
        String msgStandardPart = "Expected = " + someNumber + ". Actual = " 
                + otherNumber;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
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
    public void testAssertEqualsLong() {
        long someNumber = RANDOM.nextLong();
        long sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber + " is equal to " 
                + sameNumber + " should not have failed the test";
        assert !failOccurred : message;
    }
    
    @Test
    public void testAssertNotEqualsLongDefaultMessage() {
        long someNumber = RANDOM.nextLong();
        long otherNumber = 2 * someNumber + 1;
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
    public void testAssertNotEqualsDouble() {
        double someNumber = RANDOM.nextDouble();
        double otherNumber = 2.0 * someNumber + 1.0;
        String msgStandardPart = "Expected " + someNumber 
                + " to not differ from " + otherNumber + " by more than " 
                + LOCAL_DELTA;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + LOCAL_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDouble() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleCloseEnough() {
        double someNumber = RANDOM.nextDouble();
        double nearNumber = someNumber + HALF_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, nearNumber, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber + " is equal to " 
                + nearNumber + " within variance " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : message;
    }
    
    @Test
    public void testAssertNotEqualsDoubleDefaultMessage() {
        double someNumber = RANDOM.nextDouble();
        double otherNumber = 2.0 * someNumber + 1.0;
        String expected = "Expected " + someNumber + " to not differ from " 
                + otherNumber + " by more than " + LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + LOCAL_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleDefaultMessage() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleByMoreThanDefaultVariance() {
        double someNumber = RANDOM.nextDouble() + 0.5;
        double otherNumber = 1.0625 * someNumber;
        String msgStandardPart = "Expected " + someNumber 
                + " to not differ from " + otherNumber + " by more than " 
                + Asserters.DEFAULT_TEST_DELTA;
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + Asserters.DEFAULT_TEST_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleDefaultVariance() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleDefaultMessageDefaultVariance() {
        double someNumber = RANDOM.nextDouble() + 0.5;
        double otherNumber = 1.0625 * someNumber;
        String expected = "Expected " + someNumber + " to not differ from " 
                + otherNumber + " by more than " + Asserters.DEFAULT_TEST_DELTA;
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
        String msg = "Asserting " + someNumber + " does not differ from " 
                + otherNumber + " by more than " + Asserters.DEFAULT_TEST_DELTA  
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsDoubleDefaultVarianceDefaultMessage() {
        double someNumber = RANDOM.nextDouble();
        double sameNumber = someNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + someNumber + " is equal to " + sameNumber 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsObject() {
        BigInteger someNumber = new BigInteger(72, RANDOM);
        BigInteger otherNumber = someNumber.add(someNumber).add(BigInteger.ONE);
        String msgStandardPart = "Expected = " + someNumber.toString() 
                + ". Actual = " + otherNumber.toString();
        String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                + msgStandardPart;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, otherNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
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
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, sameNumber, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
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
    public void testAssertEqualsNoExceptionForNullActual() {
        LocalDateTime expected = LocalDateTime.now();
        LocalDateTime actual = null;
        try {
            Asserters.assertEquals(expected, actual);
            String errMsg = "Comparing " + expected.toString() 
                    + " to null should've failed the assertion";
            throw new AssertionError(errMsg);
        } catch (NullPointerException npe) {
            String errMsg = "Comparing " + expected.toString() 
                    + " to null wrongly caused NPE";
            throw new AssertionError(errMsg, npe);
        } catch (RuntimeException re) {
            String errMsg = re.getClass().getName() 
                    + " should not have occurred";
            throw new AssertionError(errMsg, re);
        } catch (AssertionError ae) {
            System.out.println("Comparing " + expected.toString() 
                    + " to null correctly failed the assertion without NPE");
        }
    }
    
    @Test
    public void testAssertNotEqualsArrayIntDiffLengths() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[lengthA];
        int[] numbersB = new int[lengthB];
        for (int i = 0; i < lengthA; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = j;
        }
        String msgCustomPart = "Arrays should be the same";
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, msgCustomPart);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = msgCustomPart 
                    + ". Arrays differ in length: expected has " 
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayInt() {
        int length = RANDOM.nextInt(8) + 2;
        int[] someNumbers = new int[length];
        int[] sameNumbers = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            someNumbers[i] = number;
            sameNumbers[i] = number;
        }
        String msgCustomPart = "Arrays should be the same";
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers, msgCustomPart);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsIntArraySameLengthDiffNums() {
        int length = RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[length];
        int[] numbersB = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        int origNum = numbersA[changeIndex];
        int diffNum = 2 * origNum + 1025;
        numbersB[changeIndex] = diffNum;
        String msgCustomPart = "Arrays should be the same";
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, msgCustomPart);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = msgCustomPart 
                    + ". Arrays first differ at index " + changeIndex 
                    + ", expected " + origNum + " but was " + diffNum; 
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsArrayIntDiffLengthsDefaultMsg() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[lengthA];
        int[] numbersB = new int[lengthB];
        for (int i = 0; i < lengthA; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = j;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays differ in length: expected has "  
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsIntArraySameLengthDiffNumsDefaultMsg() {
        int length = RANDOM.nextInt(8) + 2;
        int[] numbersA = new int[length];
        int[] numbersB = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        int origNum = numbersA[changeIndex];
        int diffNum = 2 * origNum + 1025;
        numbersB[changeIndex] = diffNum;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays first differ at index " + changeIndex 
                    + ", expected " + origNum + " but was " + diffNum; 
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayIntDefaultMsg() {
        int length = RANDOM.nextInt(8) + 2;
        int[] someNumbers = new int[length];
        int[] sameNumbers = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(256) - 128;
            someNumbers[i] = number;
            sameNumbers[i] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }

    @Test
    public void testAssertEqualsButIsNotArrayDoubleDiffLengths() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        double[] numbersA = new double[lengthA];
        double[] numbersB = new double[lengthB];
        for (int i = 0; i < lengthA; i++) {
            double number = RANDOM.nextDouble() + i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = RANDOM.nextDouble() + j;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, LOCAL_DELTA,
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Arrays differ in length: expected has " 
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayDouble() {
        int length = RANDOM.nextInt(8) + 2;
        double[] someNumbers = new double[length];
        double[] sameNumbers = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            someNumbers[i] = number;
            sameNumbers[i] = number + HALF_LOCAL_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers, LOCAL_DELTA,
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " within variance " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleArraySameLengthDiffNums() {
        int length = RANDOM.nextInt(3) + 2;
        double[] numbersA = new double[length];
        double[] numbersB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() - i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        double origNum = numbersA[changeIndex];
        double diffNum = origNum + TWICE_LOCAL_DELTA;
        numbersB[changeIndex] = diffNum;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            double minusDelta = origNum - LOCAL_DELTA;
            double plusDelta = origNum + LOCAL_DELTA;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Arrays first differ at index " + changeIndex 
                    + ", expected at least " + minusDelta + " or at most " 
                    + plusDelta + " but was " + diffNum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " within variance " + LOCAL_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsButIsNotArrayDoubleDiffLengthsDefVar() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        double[] numbersA = new double[lengthA];
        double[] numbersB = new double[lengthB];
        for (int i = 0; i < lengthA; i++) {
            double number = RANDOM.nextDouble() + i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = RANDOM.nextDouble() + j;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Arrays differ in length: expected has " 
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayDoubleDefaultVariance() {
        int length = RANDOM.nextInt(8) + 2;
        double[] someNumbers = new double[length];
        double[] sameNumbers = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            someNumbers[i] = number;
            sameNumbers[i] = number + HALF_DEFAULT_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " within variance " + Asserters.DEFAULT_TEST_DELTA
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleArraySameLengthDiffNumsDefVar() {
        int length = RANDOM.nextInt(3) + 2;
        double[] numbersA = new double[length];
        double[] numbersB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() - i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        double origNum = numbersA[changeIndex];
        double diffNum = origNum + THOUSAND_TIMES_DEFAULT_DELTA;
        numbersB[changeIndex] = diffNum;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            double minusDelta = origNum - Asserters.DEFAULT_TEST_DELTA;
            double plusDelta = origNum + Asserters.DEFAULT_TEST_DELTA;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Arrays first differ at index " + changeIndex 
                    + ", expected at least " + minusDelta + " or at most " 
                    + plusDelta + " but was " + diffNum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " within variance " + Asserters.DEFAULT_TEST_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsButIsNotArrayDoubleDiffLengthsDefaultMessage() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        double[] numbersA = new double[lengthA];
        double[] numbersB = new double[lengthB];
        for (int i = 0; i < lengthA; i++) {
            double number = RANDOM.nextDouble() + i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = RANDOM.nextDouble() + j;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays differ in length: expected has "  
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsButIsNotArrayDoubleDiffLengthsDefVarDefMsg() {
        int lengthA = RANDOM.nextInt(8) + 2;
        int lengthB = lengthA + RANDOM.nextInt(8) + 2;
        double[] numbersA = new double[lengthA];
        double[] numbersB = new double[lengthB];
        for (int i = 0; i < lengthA; i++) {
            double number = RANDOM.nextDouble() + i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        for (int j = lengthA; j < lengthB; j++) {
            numbersB[j] = RANDOM.nextDouble() + j;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays differ in length: expected has " 
                    + numbersA.length + " elements but actual has " 
                    + numbersB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayDoubleDefaultMessage() {
        int length = RANDOM.nextInt(8) + 2;
        double[] someNumbers = new double[length];
        double[] sameNumbers = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            someNumbers[i] = number;
            sameNumbers[i] = number + HALF_LOCAL_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " within variance " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleArraySameLengthDiffNumsDefMsg() {
        int length = RANDOM.nextInt(3) + 2;
        double[] numbersA = new double[length];
        double[] numbersB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() - i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        double origNum = numbersA[changeIndex];
        double diffNum = origNum + TWICE_LOCAL_DELTA;
        numbersB[changeIndex] = diffNum;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            double minusDelta = origNum - LOCAL_DELTA;
            double plusDelta = origNum + LOCAL_DELTA;
            String expected = "Arrays first differ at index " + changeIndex 
                    + ", expected at least " + minusDelta + " or at most " 
                    + plusDelta + " but was " + diffNum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " within variance " + LOCAL_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsArrayDoubleDefaultVarianceDefaultMessage() {
        int length = RANDOM.nextInt(8) + 2;
        double[] someNumbers = new double[length];
        double[] sameNumbers = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            someNumbers[i] = number;
            sameNumbers[i] = number + HALF_DEFAULT_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumbers, sameNumbers);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting " + Arrays.toString(someNumbers) 
                + " is equal to " + Arrays.toString(sameNumbers) 
                + " within variance " + Asserters.DEFAULT_TEST_DELTA
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotEqualsDoubleArraySameLengthDiffNumsDefVarDefMsg() {
        int length = RANDOM.nextInt(3) + 2;
        double[] numbersA = new double[length];
        double[] numbersB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() - i;
            numbersA[i] = number;
            numbersB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        double origNum = numbersA[changeIndex];
        double diffNum = origNum + THOUSAND_TIMES_DEFAULT_DELTA;
        numbersB[changeIndex] = diffNum;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(numbersA, numbersB);
        } catch (AssertionError ae) {
            failOccurred = true;
            double minusDelta = origNum - Asserters.DEFAULT_TEST_DELTA;
            double plusDelta = origNum + Asserters.DEFAULT_TEST_DELTA;
            String expected = "Arrays first differ at index " + changeIndex 
                    + ", expected at least " + minusDelta + " or at most " 
                    + plusDelta + " but was " + diffNum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(numbersA) 
                + " is equal to " + Arrays.toString(numbersB) 
                + " within variance " + Asserters.DEFAULT_TEST_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsObjectArrayButLengthsDiffer() {
        int lengthA = RANDOM.nextInt(16) + 4;
        int lengthB = lengthA + 1;
        LocalDate[] datesA = new LocalDate[lengthA];
        LocalDate[] datesB = new LocalDate[lengthB];
        LocalDate today = LocalDate.now();
        for (int i = 0; i < lengthA; i++) {
            LocalDate date = today.plusDays(i);
            datesA[i] = date;
            datesB[i] = date;
        }
        datesB[lengthA] = today.plusDays(lengthA);
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(datesA, datesB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Arrays differ in length: expected has " + datesA.length 
                    + " elements but actual has " + datesB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(datesA) + " is equal to " 
                + Arrays.toString(datesB) + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsObjectArray() {
        int len = RANDOM.nextInt(8) + 2;
        BigInteger[] someArray = new BigInteger[len];
        BigInteger[] sameArray = new BigInteger[len];
        for (int i = 0; i < len; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            someArray[i] = number;
            sameArray[i] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someArray, sameArray, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + Arrays.toString(someArray) 
                + " is equal to " + Arrays.toString(sameArray) 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsObjectArraysSameLengthDiffOrder() {
        int len = RANDOM.nextInt(8) + 2;
        Color[] arrayA = new Color[len];
        Color[] arrayB = new Color[len];
        for (int i = 0; i < len; i++) {
            Color color = new Color(RANDOM.nextInt());
            arrayA[i] = color;
            arrayB[len - i - 1] = color;
        }
        String arrayAStr = Arrays.toString(arrayA);
        String arrayBStr = Arrays.toString(arrayB);
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + arrayAStr + " but was " + arrayBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + arrayAStr + " is equal to " + arrayBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsObjectArrayButLengthsDifferDefaultMessage() {
        int lengthA = RANDOM.nextInt(16) + 4;
        int lengthB = lengthA + 1;
        LocalDate[] datesA = new LocalDate[lengthA];
        LocalDate[] datesB = new LocalDate[lengthB];
        LocalDate today = LocalDate.now();
        for (int i = 0; i < lengthA; i++) {
            LocalDate date = today.plusDays(i);
            datesA[i] = date;
            datesB[i] = date;
        }
        datesB[lengthA] = today.plusDays(lengthA);
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(datesA, datesB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays differ in length: expected has " 
                    + datesA.length + " elements but actual has " 
                    + datesB.length + " elements";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(datesA) + " is equal to " 
                + Arrays.toString(datesB) + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsObjectArrayDefaultMessage() {
        int len = RANDOM.nextInt(8) + 2;
        BigInteger[] someArray = new BigInteger[len];
        BigInteger[] sameArray = new BigInteger[len];
        for (int i = 0; i < len; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            someArray[i] = number;
            sameArray[i] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someArray, sameArray);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + Arrays.toString(someArray) 
                + " is equal to " + Arrays.toString(sameArray) 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertEqualsObjectArraysSameLenDiffOrderDefaultMessage() {
        int len = RANDOM.nextInt(8) + 2;
        Color[] arrayA = new Color[len];
        Color[] arrayB = new Color[len];
        for (int i = 0; i < len; i++) {
            Color color = new Color(RANDOM.nextInt());
            arrayA[i] = color;
            arrayB[len - i - 1] = color;
        }
        String arrayAStr = Arrays.toString(arrayA);
        String arrayBStr = Arrays.toString(arrayB);
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(arrayA, arrayB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " 
                    + arrayAStr + " but was " + arrayBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + arrayAStr + " is equal to " + arrayBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNullButItIsNot() {
        Object object = LocalDateTime.now();
        boolean failOccurred = false;
        try {
            Asserters.assertNull(object, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Expected null object but found " 
                    + object.toString();
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + object.toString() 
                + " is null should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNull() {
        System.out.println("assertNull");
        boolean failOccurred = false;
        try {
            Asserters.assertNull(null, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting null is null should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNullButItIsNotDefaultMessage() {
        Object object = new BigInteger(96, RANDOM);
        boolean failOccurred = false;
        try {
            Asserters.assertNull(object);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected null object but found " 
                    + object.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + object.toString() 
                + " is null should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNullDefaultMessage() {
        boolean failOccurred = false;
        try {
            Asserters.assertNull(null);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting null is null should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumLong() {
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long belowMinimum = minimum - RANDOM.nextInt(128) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + belowMinimum 
                    + " expected to be at least " + minimum;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimum() {
        System.out.println("assertMinimum");
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long atOrAboveMinimum = minimum + RANDOM.nextInt(128);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumLongDefaultMessage() {
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long belowMinimum = minimum - RANDOM.nextInt(128) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + belowMinimum 
                    + " expected to be at least " + minimum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumLongDefaultMessage() {
        int halfIntMax = -(Integer.MIN_VALUE / 2);
        long minimum = ((long) Integer.MAX_VALUE) + RANDOM.nextInt(halfIntMax);
        long atOrAboveMinimum = minimum + RANDOM.nextInt(128);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumDouble() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double belowMinimum = minimum - unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Value " + belowMinimum 
                    + " expected to be at least " + minimum;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumDouble() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double atOrAboveMinimum = minimum + unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumDoubleDefaultMessage() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double belowMinimum = minimum - unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Value " + belowMinimum 
                    + " expected to be at least " + minimum;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum 
                + " is equal to or greater than " + minimum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumDoubleDefaultMessage() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double minimum = multiplier * unitIntervalNumber;
        double atOrAboveMinimum = minimum + unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum 
                + " is equal to or greater than " + minimum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumComparable() {
        BigInteger minimum = new BigInteger(72, RANDOM);
        BigInteger belowMinimum = minimum.subtract(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Value " + belowMinimum.toString() 
                    + " expected to be at least " + minimum.toString();
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumComparable() {
        BigInteger minimum = new BigInteger(84, RANDOM);
        BigInteger atOrAboveMinimum 
                = minimum.add(BigInteger.valueOf(RANDOM.nextInt(128)));
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertBelowMinimumComparableDefaultMessage() {
        BigInteger minimum = new BigInteger(72, RANDOM);
        BigInteger belowMinimum = minimum.subtract(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, belowMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Value " + belowMinimum.toString() 
                    + " expected to be at least " + minimum.toString();
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMinimumComparableDefaultMessage() {
        BigInteger minimum = new BigInteger(84, RANDOM);
        BigInteger atOrAboveMinimum 
                = minimum.add(BigInteger.valueOf(RANDOM.nextInt(128)));
        boolean failOccurred = false;
        try {
            Asserters.assertMinimum(minimum, atOrAboveMinimum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrAboveMinimum.toString() 
                + " is equal to or greater than " + minimum.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenItIs() {
        int number = -RANDOM.nextInt(65536) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at least 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegative() {
        System.out.println("assertNotNegative");
        int number = RANDOM.nextInt(65536);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenItIsDefaultMessage() {
        int number = -RANDOM.nextInt(65536) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be at least 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeDefaultMessage() {
        int number = RANDOM.nextInt(65536);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenDoubleIs() {
        double number = -RANDOM.nextDouble() - Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotActuallyNegative() {
        double number = -0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativePositiveZero() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeDouble() {
        double number = RANDOM.nextDouble() + Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNaNIsIndeedNotNegative() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNegativeInfinityIs() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButPositiveInfinityIsIndeedNotNegative() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeWhenDoubleIsDefaultMessage() {
        double number = -RANDOM.nextDouble() - Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotActuallyNegativeDefaultMessage() {
        double number = -0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativePositiveZeroDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeDoubleDefaultMessage() {
        double number = RANDOM.nextDouble() + Double.MIN_NORMAL;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNegativeInfinityIsDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be at least 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButInfinityIsNotNegativeDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNegativeButNaNIsIndeedNotNegativeDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButItIsNot() {
        int number = RANDOM.nextInt() & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be at most -1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegative() {
        System.out.println("assertNegative");
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButItIsNotDefaultMessage() {
        int number = RANDOM.nextInt() & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be at most -1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDefaultMessage() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDoubleButItIsNot() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDouble() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNegative() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveInfinityIsNot() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNaNIsNot() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number + " from bit pattern " 
                + bitPattern + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNegativeZeroIsNot() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveZeroIsNot() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDoubleButItIsNotDefaultMessage() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeDoubleDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNegativeDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is negative should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveInfinityIsNotDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNaNIsNotDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number + " from bit pattern " 
                + bitPattern + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButNegativeZeroIsNotDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeButPositiveZeroIsNotDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNegative(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is negative should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroButItIsNegative() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroButItIsPositive() {
        int number = (RANDOM.nextInt() | 2) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZero() {
        System.out.println("assertZero");
        boolean failOccurred = false;
        try {
            Asserters.assertZero(0L, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that 0 is 0 should not have failed the test";
        assert !failOccurred : msg;
    }
  
    @Test
    public void testAssertZeroButItIsNegativeDefaultMessage() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDefaultMessage() {
        boolean failOccurred = false;
        try {
            Asserters.assertZero(0L);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that 0 is 0 should not have failed the test";
        assert !failOccurred : msg;
    }
  
    @Test
    public void testAssertZeroButItIsPositiveDefaultMessage() {
        int number = (RANDOM.nextInt() | 4) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegative() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroPositiveZero() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositive() {
        int floor = (RANDOM.nextInt() | 32) & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroNegativeZero() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegativeInfinity() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositiveInfinity() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNaN() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNaNDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegativeDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroPositiveZeroDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositiveDefaultMessage() {
        int floor = (RANDOM.nextInt() | 64) & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroNegativeZeroDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsNegativeInfinityDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroDoubleButItIsPositiveInfinityDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertZero(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is 0.0 should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveButItIs() {
        int number = (RANDOM.nextInt() | 8) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be less than 1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }

    @Test
    public void testAssertNotPositive() {
        System.out.println("assertNotPositive");
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroIsNotPositive() {
        int number = 0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }

    @Test
    public void testAssertNotPositiveButItIsDefaultMessage() {
        int number = (RANDOM.nextInt() | 16) & Integer.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be less than 1";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }

    @Test
    public void testAssertNotPositiveDefaultMessage() {
        int number = RANDOM.nextInt() | Integer.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertZeroIsNotPositiveDefaultMessage() {
        int number = 0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDoubleButItIs() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDouble() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveZeroIsNotPositive() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotPositive() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsIndeedNotPositive() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsNotPositiveButItIs() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveButNaNIsIndeedNotPositive() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDoubleButItIsDefaultMessage() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveDoubleDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveZeroIsNotPositiveDefaultMessage() {
        double number = 0.0;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeZeroIsNotPositiveDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsIndeedNotPositiveDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsNotPositiveButItIsDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to not be positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is not positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotPositiveButNaNIsIndeedNotPositiveDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNotPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is not positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegative() {
        long number = RANDOM.nextLong() | Long.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositive() {
        System.out.println("assertPositive");
        long number = RANDOM.nextLong() & Long.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }

    @Test
    public void testAssertPositiveButItIsZero() {
        long number = 0L;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegativeDefaultMessage() {
        long number = RANDOM.nextLong() | Long.MIN_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDefaultMessage() {
        long number = RANDOM.nextLong() & Long.MAX_VALUE;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }

    @Test
    public void testAssertPositiveButItIsZeroDefaultMessage() {
        long number = 0L;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number 
                    + " expected to be greater than 0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDoubleButItIsNot() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDouble() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsPositive() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNotPositive() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNaN() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegativeZero() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsPositiveZero() {
        double number = Double.longBitsToDouble(0L);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDoubleButItIsNotDefaultMessage() {
        int floor = RANDOM.nextInt() | Integer.MIN_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " 
                    + number + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveDoubleDefaultMessage() {
        int floor = RANDOM.nextInt() & Integer.MAX_VALUE;
        double number = floor + RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveInfinityIsPositiveDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNegativeInfinityIsNotPositiveDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNaNDefaultMessage() {
        long bitPattern = (((long) (RANDOM.nextInt()) << 32) + RANDOM.nextInt()) 
                | NaN_MASK; 
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " is not considered negative, zero or positive";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsNegativeZeroDefaultMessage() {
        double number = Double.longBitsToDouble(Long.MIN_VALUE);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPositiveButItIsPositiveZeroDefaultMessage() {
        double number = Double.longBitsToDouble(0L);
        boolean failOccurred = false;
        try {
            Asserters.assertPositive(number);
        } catch (AssertionError ae) {
            failOccurred = true; 
            String expected = "Number " + number 
                    + " expected to be greater than 0.0";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
            System.out.println("\"" + actual + "\"");
        }
        String msg = "Asserting that number " + number 
                + " is positive should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumButItGoesOver() {
        long maximum = RANDOM.nextInt();
        long aboveMaximum = maximum + 1;
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(aboveMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + aboveMaximum +  " expected to be at most " + maximum;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveMaximum 
                + " is equal to or less than " + maximum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximum() {
        System.out.println("assertMaximum");
        long maximum = RANDOM.nextInt();
        long atOrBelowMaximum = maximum - RANDOM.nextInt(128);
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(atOrBelowMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrBelowMaximum 
                + " is equal to or less than " + maximum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumButItGoesOverDefaultMessage() {
        long maximum = RANDOM.nextInt();
        long aboveMaximum = maximum + 1;
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(aboveMaximum, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + aboveMaximum 
                    + " expected to be at most " + maximum;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveMaximum 
                + " is equal to or less than " + maximum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumDefaultMessage() {
        long maximum = RANDOM.nextInt();
        long atOrBelowMaximum = maximum - RANDOM.nextInt(128);
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(atOrBelowMaximum, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrBelowMaximum 
                + " is equal to or less than " + maximum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertAboveMaximumDouble() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double maximum = multiplier * unitIntervalNumber;
        double aboveMaximum = maximum + unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(aboveMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + aboveMaximum 
                    + " expected to be at most " + maximum;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveMaximum 
                + " is equal to or less than " + maximum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumDouble() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double maximum = multiplier * unitIntervalNumber;
        double atOrBelowMaximum = maximum - unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(atOrBelowMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrBelowMaximum 
                + " is equal to or less than " + maximum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertAboveMaximumDoubleDefaultMessage() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double maximum = multiplier * unitIntervalNumber;
        double aboveMaximum = maximum + unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(aboveMaximum, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + aboveMaximum 
                    + " expected to be at most " + maximum;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveMaximum 
                + " is equal to or less than " + maximum 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumDoubleDefaultMessage() {
        double multiplier = RANDOM.nextInt(256) - 128;
        double unitIntervalNumber = RANDOM.nextDouble();
        double maximum = multiplier * unitIntervalNumber;
        double atOrBelowMaximum = maximum - unitIntervalNumber;
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(atOrBelowMaximum, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrBelowMaximum 
                + " is equal to or less than " + maximum 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumComparableButItIsMore() {
        BigInteger maximum = new BigInteger(84, RANDOM);
        BigInteger aboveMaximum = maximum.add(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(aboveMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Value " + aboveMaximum.toString() 
                    + " expected to be at most " + maximum.toString();
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveMaximum.toString() 
                + " is equal to or less than " + maximum.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumComparable() {
        BigInteger maximum = new BigInteger(84, RANDOM);
        BigInteger atOrBelowMaximum 
                = maximum.subtract(BigInteger.valueOf(RANDOM.nextInt(128)));
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(atOrBelowMaximum, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrBelowMaximum.toString() 
                + " is equal to or less than " + maximum.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumComparableButItIsMoreDefaultMessage() {
        BigInteger maximum = new BigInteger(84, RANDOM);
        BigInteger aboveMaximum = maximum.add(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(aboveMaximum, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Value " + aboveMaximum.toString() 
                    + " expected to be at most " + maximum.toString();
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveMaximum.toString() 
                + " is equal to or less than " + maximum.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertMaximumComparableDefaultMessage() {
        BigInteger maximum = new BigInteger(84, RANDOM);
        BigInteger atOrBelowMaximum 
                = maximum.subtract(BigInteger.valueOf(RANDOM.nextInt(128)));
        boolean failOccurred = false;
        try {
            Asserters.assertMaximum(atOrBelowMaximum, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Asserting that " + atOrBelowMaximum.toString() 
                + " is equal to or less than " + maximum.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
        
    @Test
    public void testAssertNaNButItIsNot() {
        double number = Math.random();
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + number + " expected to be NaN";
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaN() {
        System.out.println("assertNaN");
        long bitPattern = NaN_MASK | RANDOM.nextLong();
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsNegativeInfinity() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + number + " expected to be NaN";
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsPositiveInfinity() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String msgStandardPart = "Number " + number + " expected to be NaN";
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". " 
                    + msgStandardPart;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsNotDefaultMessage() {
        double number = Math.random();
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNDefaultMessage() {
        long bitPattern = NaN_MASK | RANDOM.nextLong();
        double number = Double.longBitsToDouble(bitPattern);
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsNegativeInfinityDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNaNButItIsPositiveInfinityDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNButItIs() {
        double number = Double.NaN;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Number " 
                    + number + " expected to not be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNNegativeInfinity() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNPositiveInfinity() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaN() {
        System.out.println("assertNotNaN");
        double number = RANDOM.nextDouble() + RANDOM.nextInt();
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNButItIsDefaultMessage() {
        double number = Double.NaN;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Number " + number + " expected to not be NaN";
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNNegativeInfinityDefaultMessage() {
        double number = Double.NEGATIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNPositiveInfinityDefaultMessage() {
        double number = Double.POSITIVE_INFINITY;
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertNotNaNDefaultMessage() {
        double number = RANDOM.nextDouble() + RANDOM.nextInt();
        boolean failOccurred = false;
        try {
            Asserters.assertNotNaN(number);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + number 
                + " is not NaN should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeButIsBelow() {
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum + RANDOM.nextInt(Short.MAX_VALUE) + 1;
        int belowRange = minimum - RANDOM.nextInt(Short.MAX_VALUE) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + belowRange + " to be in range from " + minimum + " to " 
                    + maximum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowRange + " is in the range from " 
                + minimum + " to " + maximum + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRange() {
        System.out.println("assertInRange");
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum + RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        for (int actual = minimum; actual <= maximum; actual++) {
            boolean failOccurred = false;
            try {
                Asserters.assertInRange(minimum, actual, maximum, 
                        EXAMPLE_ASSERTION_MESSAGE_PART);
            } catch (AssertionError ae) {
                failOccurred = true;
                System.out.println("\"" + ae.getMessage() + "\"");
            }
            String msg = "Asserting that " + actual + " is in the range from " 
                    + minimum + " to " + maximum 
                    + " should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    @Test
    public void testAssertInRangeButIsAbove() {
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum + RANDOM.nextInt(Short.MAX_VALUE) + 1;
        int aboveRange = maximum + RANDOM.nextInt(Short.MAX_VALUE) + 1;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + aboveRange + " to be in range from " + minimum + " to " 
                    + maximum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveRange + " is in the range from " 
                + minimum + " to " + maximum + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeRejectsBadMinMaxCombination() {
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum - 1;
        int number = RANDOM.nextInt();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum + " and maximum " + maximum 
                + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeButIsBelowDefaultMessage() {
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum + RANDOM.nextInt(Short.MAX_VALUE) + 1;
        int belowRange = minimum - RANDOM.nextInt(Short.MAX_VALUE) - 1;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + belowRange + " to be in range from " 
                    + minimum + " to " + maximum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowRange + " is in the range from " 
                + minimum + " to " + maximum + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDefaultMessage() {
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum + RANDOM.nextInt(Byte.MAX_VALUE) + 1;
        for (int actual = minimum; actual <= maximum; actual++) {
            boolean failOccurred = false;
            try {
                Asserters.assertInRange(minimum, actual, maximum);
            } catch (AssertionError ae) {
                failOccurred = true;
                System.out.println("\"" + ae.getMessage() + "\"");
            }
            String msg = "Asserting that " + actual + " is in the range from " 
                    + minimum + " to " + maximum 
                    + " should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    @Test
    public void testAssertInRangeButIsAboveDefaultMessage() {
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum + RANDOM.nextInt(Short.MAX_VALUE) + 1;
        int aboveRange = maximum + RANDOM.nextInt(Short.MAX_VALUE) + 1;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + aboveRange + " to be in range from " 
                    + minimum + " to " + maximum;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveRange + " is in the range from " 
                + minimum + " to " + maximum + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeRejectsBadMinMaxCombinationDefaultMessage() {
        int minimum = RANDOM.nextInt(Short.MAX_VALUE);
        int maximum = minimum - 1;
        int number = RANDOM.nextInt();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum + " and maximum " + maximum 
                + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMinimum() {
        double minimum = Double.NaN;
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for minimum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMaximum() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = Double.NaN;
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for maximum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeDelta() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 - RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, Double.NaN, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for delta should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsBelow() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double belowRange = -2.0 - RANDOM.nextDouble() - LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + belowRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + LOCAL_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + belowRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDouble() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + number + " is in range " + minimum + " to " 
                + maximum + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsAbove() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double aboveRange = 2.0 + RANDOM.nextDouble() + LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + aboveRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + LOCAL_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + aboveRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testDoubleBelowButInRangeDueToVariance() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyBelowRange = minimum - HALF_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyBelowRange, maximum, 
                    LOCAL_DELTA, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyBelowRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + LOCAL_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testDoubleAboveButInRangeDueToVariance() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyAboveRange = maximum + HALF_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyAboveRange, maximum, 
                    LOCAL_DELTA, EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyAboveRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + LOCAL_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleRejectsBadMinMaxCombination() {
        double minimum = 1.0 + RANDOM.nextDouble();
        double maximum = 0.0 - RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum + " and maximum " + maximum 
                + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMinimumDefaultDelta() {
        double minimum = Double.NaN;
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for minimum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMaximumDefaultDelta() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = Double.NaN;
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for maximum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsBelowDefaultDelta() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double belowRange = -2.0 - RANDOM.nextDouble() 
                - Asserters.DEFAULT_TEST_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + belowRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + Asserters.DEFAULT_TEST_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + belowRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleDefaultDelta() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + number + " is in range " + minimum + " to " 
                + maximum + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsAboveDefaultDelta() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double aboveRange = 2.0 + RANDOM.nextDouble() 
                + Asserters.DEFAULT_TEST_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + aboveRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + Asserters.DEFAULT_TEST_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + aboveRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testDoubleBelowButInRangeDueToVarianceDefaultDelta() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyBelowRange = minimum - HALF_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyBelowRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyBelowRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + Asserters.DEFAULT_TEST_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testDoubleAboveButInRangeDueToVarianceDefaultVariance() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyAboveRange = maximum + HALF_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyAboveRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyAboveRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + Asserters.DEFAULT_TEST_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleRejectsBadMinMaxCombinationDefVar() {
        double minimum = 1.0 + RANDOM.nextDouble();
        double maximum = 0.0 - RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum + " and maximum " + maximum 
                + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMinimumDefaultMessage() {
        double minimum = Double.NaN;
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for minimum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMaximumDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = Double.NaN;
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for maximum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeDeltaDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 - RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, Double.NaN);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for delta should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsBelowDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double belowRange = -2.0 - RANDOM.nextDouble() - LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + belowRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + LOCAL_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + belowRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + number + " is in range " + minimum + " to " 
                + maximum + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsAboveDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double aboveRange = 2.0 + RANDOM.nextDouble() + LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + aboveRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + LOCAL_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + aboveRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testDoubleBelowButInRangeDueToVarianceDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyBelowRange = minimum - HALF_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyBelowRange, maximum, 
                    LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyBelowRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + LOCAL_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testDoubleAboveButInRangeDueToVarianceDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyAboveRange = maximum + HALF_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyAboveRange, maximum, 
                    LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyAboveRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + LOCAL_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleRejectsBadMinMaxCombinationDefMsg() {
        double minimum = 1.0 + RANDOM.nextDouble();
        double maximum = 0.0 - RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, LOCAL_DELTA);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum + " and maximum " + maximum 
                + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMinimumDefVarDefMsg() {
        double minimum = Double.NaN;
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for minimum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testNaNNotAllowedForAssertDoubleInRangeMaximumDefVarDefMsg() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = Double.NaN;
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Minimum, maximum, delta should not be NaN";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using NaN for maximum should have caused exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsBelowDefaultDeltaDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double belowRange = -2.0 - RANDOM.nextDouble() 
                - Asserters.DEFAULT_TEST_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + belowRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + Asserters.DEFAULT_TEST_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + belowRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleDefaultDeltaDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + number + " is in range " + minimum + " to " 
                + maximum + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleButIsAboveDefaultDeltaDefaultMessage() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double aboveRange = 2.0 + RANDOM.nextDouble() 
                + Asserters.DEFAULT_TEST_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + aboveRange + " to be in range from " 
                    + minimum + " to " + maximum + " with variance " 
                    + Asserters.DEFAULT_TEST_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "The number " + aboveRange 
                + " should not be said to be in range " + minimum + " to " 
                + maximum;
        assert failOccurred : msg;
    }
    
    @Test
    public void testDoubleBelowButInRangeDueToVarianceDefVarDefMsg() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyBelowRange = minimum - HALF_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyBelowRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyBelowRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + Asserters.DEFAULT_TEST_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testDoubleAboveButInRangeDueToVarianceDefVarDefMsg() {
        double minimum = -1.0 + RANDOM.nextDouble();
        double maximum = 1.0 + RANDOM.nextDouble();
        double slightlyAboveRange = maximum + HALF_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, slightlyAboveRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "The number " + slightlyAboveRange 
                + " should be said to be in range " + minimum + " to " + maximum 
                + " due to variance " + Asserters.DEFAULT_TEST_DELTA;
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeDoubleRejectsBadMinMaxComboDefVarDefMsg() {
        double minimum = 1.0 + RANDOM.nextDouble();
        double maximum = 0.0 - RANDOM.nextDouble();
        double number = RANDOM.nextDouble();
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum 
                    + " and maximum " + maximum + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum + " and maximum " + maximum 
                + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeComparableButIsBelow() {
        LocalDate today = LocalDate.now();
        int numberOfYears = 10;
        LocalDate minimum = today.minusYears(numberOfYears);
        LocalDate maximum = today.plusYears(numberOfYears);
        LocalDate belowRange = minimum.minusYears(numberOfYears 
                * numberOfYears);
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + belowRange.toString() + " to be in range from " 
                    + minimum.toString() + " to " + maximum.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowRange.toString() 
                + " is in the range from " + minimum.toString() + " to " 
                + maximum.toString() + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeComparable() {
        int radix = 36;
        int size = RANDOM.nextInt(32) + 8;
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            int num = RANDOM.nextInt() + i;
            strings[i] = Integer.toString(num, radix);
        }
        Arrays.sort(strings);
        String minimum = strings[0];
        String maximum = strings[size - 1];
        for (String actual : strings) {
            boolean failOccurred = false;
            try {
                Asserters.assertInRange(minimum, actual, maximum, 
                        EXAMPLE_ASSERTION_MESSAGE_PART);
            } catch (AssertionError ae) {
                failOccurred = true;
            }
            String msg = "Asserting that \"" + actual 
                    + "\" is in the range from \"" + minimum + "\" to \"" 
                    + maximum + "\" should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    @Test
    public void testAssertInRangeComparableButIsAbove() {
        BigInteger minimum = new BigInteger(84, RANDOM);
        BigInteger maximum = minimum.multiply(BigInteger.TEN)
                .add(BigInteger.ONE);
        BigInteger aboveRange = maximum.add(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + aboveRange.toString() + " to be in range from " 
                    + minimum.toString() + " to " + maximum.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveRange.toString() 
                + " is in the range from " + minimum.toString() + " to " 
                + maximum.toString() + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeComparableRejectsBadMinMaxCombination() {
        int hours = RANDOM.nextInt(Short.MAX_VALUE) + 1;
        Duration minimum = Duration.ofHours(hours); 
        Duration maximum = minimum.minusHours(1);
        Duration number = Duration.ofSeconds(3600 * hours 
                + RANDOM.nextInt(Short.MAX_VALUE));
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum.toString() 
                    + " and maximum " + maximum.toString() + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum.toString() + " and maximum " 
                + maximum.toString() + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeComparableButIsBelowDefaultMessage() {
        LocalDate today = LocalDate.now();
        int numberOfYears = 10;
        LocalDate minimum = today.minusYears(numberOfYears);
        LocalDate maximum = today.plusYears(numberOfYears);
        LocalDate belowRange = minimum.minusYears(numberOfYears 
                * numberOfYears);
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, belowRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + belowRange.toString() 
                    + " to be in range from " + minimum.toString() + " to " 
                    + maximum.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + belowRange.toString() 
                + " is in the range from " + minimum.toString() + " to " 
                + maximum.toString() + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeComparableDefaultMessage() {
        int radix = 36;
        int size = RANDOM.nextInt(32) + 8;
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            int num = RANDOM.nextInt() + i;
            strings[i] = Integer.toString(num, radix);
        }
        Arrays.sort(strings);
        String minimum = strings[0];
        String maximum = strings[size - 1];
        for (String actual : strings) {
            boolean failOccurred = false;
            try {
                Asserters.assertInRange(minimum, actual, maximum);
            } catch (AssertionError ae) {
                failOccurred = true;
            }
            String msg = "Asserting that \"" + actual 
                    + "\" is in the range from \"" + minimum + "\" to \"" 
                    + maximum + "\" should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    @Test
    public void testAssertInRangeComparableButIsAboveDefaultMessage() {
        BigInteger minimum = new BigInteger(84, RANDOM);
        BigInteger maximum = minimum.multiply(BigInteger.TEN)
                .add(BigInteger.ONE);
        BigInteger aboveRange = maximum.add(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertInRange(minimum, aboveRange, maximum);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + aboveRange.toString() 
                    + " to be in range from " + minimum.toString() + " to " 
                    + maximum.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + aboveRange.toString() 
                + " is in the range from " + minimum.toString() + " to " 
                + maximum.toString() + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertInRangeComparableRejectsBadMinMaxComboDefMsg() {
        int hours = RANDOM.nextInt(Short.MAX_VALUE) + 1;
        Duration minimum = Duration.ofHours(hours); 
        Duration maximum = minimum.minusHours(1);
        Duration number = Duration.ofSeconds(3600 * hours 
                + RANDOM.nextInt(Short.MAX_VALUE));
        boolean exceptionOccurred = false;
        try {
            Asserters.assertInRange(minimum, number, maximum);
        } catch (IllegalArgumentException iae) {
            exceptionOccurred = true;
            String expected = "Combination of minimum " + minimum.toString() 
                    + " and maximum " + maximum.toString() + " is invalid";
            String actual = iae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        } catch (AssertionError ae) {
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String msg = "Using minimum " + minimum.toString() + " and maximum " 
                + maximum.toString() + " should have caused an exception";
        assert exceptionOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentButIsSame() {
        int number = RANDOM.nextInt();
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(number, number, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + number + " to be different from " + number;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number + " is different from " + number 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferent() {
        System.out.println("assertDifferent");
        long some = RANDOM.nextLong() | 1L;
        long other = some << 1;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some + " is different from " + other 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentButIsSameDefaultMessage() {
        int number = RANDOM.nextInt();
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(number, number);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + number + " to be different from " 
                    + number;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number + " is different from " + number 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDefaultMessage() {
        long some = RANDOM.nextLong() | 1L;
        long other = some << 1;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some + " is different from " + other 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleButIsWithinVariance() {
        double some = RANDOM.nextDouble();
        double other = some + HALF_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + some + " to differ from " + other + " by at least " 
                    + LOCAL_DELTA + ", values differ by ";
            String actual = ae.getMessage();
            String msg = "Expected message to start with \"" + expected 
                    + "\", was \"" + actual + "\"";
            assert actual.startsWith(expected) : msg;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + LOCAL_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDouble() {
        double some = RANDOM.nextDouble();
        double other = some + TWICE_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleButIsWithinDefaultVariance() {
        double some = RANDOM.nextDouble();
        double other = some + HALF_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + some + " to differ from " + other + " by at least " 
                    + Asserters.DEFAULT_TEST_DELTA + ", values differ by " 
                    + HALF_DEFAULT_DELTA;
            String actual = ae.getMessage();
            String msg = "Expected message to start with \"" + expected 
                    + "\", was \"" + actual + "\"";
            assert actual.startsWith(expected) : msg;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + Asserters.DEFAULT_TEST_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentByMoreThanDefaultVarianceDouble() {
        double some = RANDOM.nextDouble();
        double other = some + THOUSAND_TIMES_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + Asserters.DEFAULT_TEST_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleButIsWithinVarianceDefaultMessage() {
        double some = RANDOM.nextDouble();
        double other = some + HALF_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + some + " to differ from " + other 
                    + " by at least " + LOCAL_DELTA + ", values differ by ";
            String actual = ae.getMessage();
            String msg = "Expected message to start with \"" + expected 
                    + "\", was \"" + actual + "\"";
            assert actual.startsWith(expected) : msg;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + LOCAL_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleDefaultMessage() {
        double some = RANDOM.nextDouble();
        double other = some + TWICE_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleButIsWithinDefVarDefMsg() {
        double some = RANDOM.nextDouble();
        double other = some + HALF_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected " + some + " to differ from " + other 
                    + " by at least " + Asserters.DEFAULT_TEST_DELTA 
                    + ", values differ by ";
            String actual = ae.getMessage();
            String msg = "Expected message to start with \"" + expected 
                    + "\", was \"" + actual + "\"";
            assert actual.startsWith(expected) : msg;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + Asserters.DEFAULT_TEST_DELTA 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentByMoreThanDefaultVarianceDefaultMessage() {
        double some = RANDOM.nextDouble();
        double other = some + THOUSAND_TIMES_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some + " differs from " + other 
                + " by more than " + Asserters.DEFAULT_TEST_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectButIsSame() {
        BigInteger number = new BigInteger(72, RANDOM);
        boolean failOccurred = false;
        try {Asserters.assertDifferent(number, number, 
                EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Expected " 
                    + number.toString() + " to be different from " 
                    + number.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + number.toString() 
                + " is different from " + number.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testDifferentObject() {
        int r = RANDOM.nextInt(128);
        int g = RANDOM.nextInt(128);
        int b = RANDOM.nextInt(128);
        Color some = new Color(r, g, b);
        Color other = some.brighter();
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some.toString() + " is different from " 
                + other.toString() + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectButIsSameDefaultMessage() {
        LocalDate date = LocalDate.now();
        boolean failOccurred = false;
        try {Asserters.assertDifferent(date, date);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected "  + date.toString() 
                    + " to be different from " + date.toString();
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + date.toString() 
                + " is different from " + date.toString() 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testDifferentObjectDefaultMessage() {
        int r = RANDOM.nextInt(128);
        int g = RANDOM.nextInt(128);
        int b = RANDOM.nextInt(128);
        Color some = new Color(r, g, b);
        Color other = some.brighter();
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + some.toString() + " is different from " 
                + other.toString() + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentIntArraysButAreSame() {
        int length = RANDOM.nextInt(8) + 2;
        int[] arrayA = new int[length];
        int[] arrayB = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt() + i;
            arrayA[i] = number;
            arrayB[i] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Arrays " 
                    + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) 
                + " are different should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentIntArraysDifferInLength() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        int[] some = new int[lenA];
        int[] other = new int[lenB];
        for (int i = 0; i < lenA; i++) {
            int number = RANDOM.nextInt() + i;
            some[i] = number;
            other[i] = number;
        }
        for (int j = lenA; j < lenB; j++) {
            int number = RANDOM.nextInt() * j;
            other[j] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentIntArraysSameLengthDifferInOneElement() {
        int length = RANDOM.nextInt(8) + 2;
        int[] some = new int[length];
        int[] other = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt() + i;
            some[i] = number;
            other[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        other[changeIndex]++;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentIntArraysButAreSameDefaultMessage() {
        int length = RANDOM.nextInt(8) + 2;
        int[] arrayA = new int[length];
        int[] arrayB = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt() + i;
            arrayA[i] = number;
            arrayB[i] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays " + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) 
                + " are different should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentIntArraysDifferInLengthDefaultMessage() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        int[] some = new int[lenA];
        int[] other = new int[lenB];
        for (int i = 0; i < lenA; i++) {
            int number = RANDOM.nextInt() + i;
            some[i] = number;
            other[i] = number;
        }
        for (int j = lenA; j < lenB; j++) {
            int number = RANDOM.nextInt() * j;
            other[j] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentIntArraysSameLengthDiffOneElementDefMsg() {
        int length = RANDOM.nextInt(8) + 2;
        int[] some = new int[length];
        int[] other = new int[length];
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt() + i;
            some[i] = number;
            other[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        other[changeIndex]++;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysButAreSame() {
        int length = RANDOM.nextInt(8) + 2;
        double[] arrayA = new double[length];
        double[] arrayB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            arrayA[i] = number;
            arrayB[i] = number + HALF_LOCAL_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB, LOCAL_DELTA, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Arrays " 
                    + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different beyond variance " + LOCAL_DELTA 
                    + " as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " are different beyond variance " 
                + LOCAL_DELTA + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysDifferInLength() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        double[] some = new double[lenA];
        double[] other = new double[lenB];
        for (int i = 0; i < lenA; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_LOCAL_DELTA;
        }
        for (int j = lenA; j < lenB; j++) {
            double number = RANDOM.nextDouble() * j;
            other[j] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA,  
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) + " are different beyond variance " 
                + LOCAL_DELTA + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysSameLengthDifferInOneElement() {
        int length = RANDOM.nextInt(8) + 2;
        double[] some = new double[length];
        double[] other = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_LOCAL_DELTA;
        }
        int changeIndex = RANDOM.nextInt(length);
        other[changeIndex] += TWICE_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA,  
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) + " are different beyond variance " 
                + LOCAL_DELTA + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysButAreSameDefaultDelta() {
        int length = RANDOM.nextInt(8) + 2;
        double[] arrayA = new double[length];
        double[] arrayB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            arrayA[i] = number;
            arrayB[i] = number + HALF_DEFAULT_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Arrays " 
                    + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different beyond variance " 
                    + Asserters.DEFAULT_TEST_DELTA + " as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " are different beyond variance " 
                + Asserters.DEFAULT_TEST_DELTA + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysDifferInLengthDefaultDelta() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        double[] some = new double[lenA];
        double[] other = new double[lenB];
        for (int i = 0; i < lenA; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_DEFAULT_DELTA;
        }
        for (int j = lenA; j < lenB; j++) {
            double number = RANDOM.nextDouble() * j;
            other[j] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) + " are different beyond variance " 
                + Asserters.DEFAULT_TEST_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysSameLenDiffOneElemDefVar() {
        int length = RANDOM.nextInt(8) + 2;
        double[] some = new double[length];
        double[] other = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_DEFAULT_DELTA;
        }
        int changeIndex = RANDOM.nextInt(length);
        other[changeIndex] += THOUSAND_TIMES_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) + " are different beyond variance " 
                + Asserters.DEFAULT_TEST_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysButAreSameDefaultMessage() {
        int length = RANDOM.nextInt(8) + 2;
        double[] arrayA = new double[length];
        double[] arrayB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            arrayA[i] = number;
            arrayB[i] = number + HALF_LOCAL_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays " + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different beyond variance " + LOCAL_DELTA 
                    + " as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " are different beyond variance " 
                + LOCAL_DELTA + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysDifferInLengthDefaultMessage() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        double[] some = new double[lenA];
        double[] other = new double[lenB];
        for (int i = 0; i < lenA; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_LOCAL_DELTA;
        }
        for (int j = lenA; j < lenB; j++) {
            double number = RANDOM.nextDouble() * j;
            other[j] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different beyond variance " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysSameLenDiffOneElemDefMsg() {
        int length = RANDOM.nextInt(8) + 2;
        double[] some = new double[length];
        double[] other = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_LOCAL_DELTA;
        }
        int changeIndex = RANDOM.nextInt(length);
        other[changeIndex] += TWICE_LOCAL_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, LOCAL_DELTA);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different beyond variance " + LOCAL_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysButAreSameDefVarDefMsg() {
        int length = RANDOM.nextInt(8) + 2;
        double[] arrayA = new double[length];
        double[] arrayB = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            arrayA[i] = number;
            arrayB[i] = number + HALF_DEFAULT_DELTA;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays " + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different beyond variance " 
                    + Asserters.DEFAULT_TEST_DELTA + " as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " are different beyond variance " 
                + Asserters.DEFAULT_TEST_DELTA + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysDifferInLengthDefVarDefMsg() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        double[] some = new double[lenA];
        double[] other = new double[lenB];
        for (int i = 0; i < lenA; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_DEFAULT_DELTA;
        }
        for (int j = lenA; j < lenB; j++) {
            double number = RANDOM.nextDouble() * j;
            other[j] = number;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) + " are different beyond variance " 
                + Asserters.DEFAULT_TEST_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentDoubleArraysSameLenDiffOneElemDefVarMsg() {
        int length = RANDOM.nextInt(8) + 2;
        double[] some = new double[length];
        double[] other = new double[length];
        for (int i = 0; i < length; i++) {
            double number = RANDOM.nextDouble() + i;
            some[i] = number;
            other[i] = number + HALF_DEFAULT_DELTA;
        }
        int changeIndex = RANDOM.nextInt(length);
        other[changeIndex] += THOUSAND_TIMES_DEFAULT_DELTA;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) + " are different beyond variance " 
                + Asserters.DEFAULT_TEST_DELTA 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectArraysButAreSame() {
        int length = RANDOM.nextInt(8) + 2;
        Color[] arrayA = new Color[length];
        Color[] arrayB = new Color[length];
        for (int i = 0; i < length; i++) {
            Color color = new Color(RANDOM.nextInt());
            arrayA[i] = color;
            arrayB[i] = color;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Arrays " 
                    + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) 
                + " are different should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectArraysDifferInLength() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        LocalDateTime[] some = new LocalDateTime[lenA];
        LocalDateTime[] other = new LocalDateTime[lenB];
        for (int i = 0; i < lenA; i++) {
            LocalDateTime time = LocalDateTime.now().plusMinutes(i);
            some[i] = time;
            other[i] = time;
        }
        for (int j = lenA; j < lenB; j++) {
            LocalDateTime time = LocalDateTime.now().minusMinutes(j);
            other[j] = time;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectArrayDespiteSameLength() {
        int length = RANDOM.nextInt(8) + 2;
        BigInteger[] arrayA = new BigInteger[length];
        BigInteger[] arrayB = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            arrayA[i] = number;
            arrayB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        arrayA[changeIndex] = arrayB[changeIndex].add(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Given that " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " differ at index " + changeIndex 
                + ", asserting that they're different should not have failed";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectArrayNoNPEForNulls() {
        int length = RANDOM.nextInt(3) + 2;
        BigInteger[] arrayA = new BigInteger[length];
        BigInteger[] arrayB = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            BigInteger number = new BigInteger(72 + i, RANDOM);
            arrayA[i] = number;
            arrayB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        arrayA[changeIndex] = null;
        boolean npeOccurred = false;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (NullPointerException npe) {
            npeOccurred = true;
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msgA = "The null in " + Arrays.toString(arrayA) 
                + " should not have caused NPE";
        assert !npeOccurred : msgA;
        String msgB = "Given that " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " differ at index " + changeIndex 
                + ", asserting that they're different should not have failed";
        assert !failOccurred : msgB;
    }
    
    @Test
    public void testAssertDifferentObjectArraysButAreSameDefaultMessage() {
        int length = RANDOM.nextInt(8) + 2;
        Color[] arrayA = new Color[length];
        Color[] arrayB = new Color[length];
        for (int i = 0; i < length; i++) {
            Color color = new Color(RANDOM.nextInt());
            arrayA[i] = color;
            arrayB[i] = color;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Arrays " + Arrays.toString(arrayA) + " and " 
                    + Arrays.toString(arrayB) 
                    + " are not different as asserted";
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) 
                + " are different should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectArraysDifferInLengthDefaultMessage() {
        int lenA = RANDOM.nextInt(8) + 2;
        int lenB = lenA + RANDOM.nextInt(4) + 1;
        LocalDateTime[] some = new LocalDateTime[lenA];
        LocalDateTime[] other = new LocalDateTime[lenB];
        for (int i = 0; i < lenA; i++) {
            LocalDateTime time = LocalDateTime.now().plusMinutes(i);
            some[i] = time;
            other[i] = time;
        }
        for (int j = lenA; j < lenB; j++) {
            LocalDateTime time = LocalDateTime.now().minusMinutes(j);
            other[j] = time;
        }
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(some, other);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting " + Arrays.toString(some) + " and " 
                + Arrays.toString(other) 
                + " are different should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectArrayDespiteSameLengthDefMsg() {
        int length = RANDOM.nextInt(8) + 2;
        BigInteger[] arrayA = new BigInteger[length];
        BigInteger[] arrayB = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            arrayA[i] = number;
            arrayB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        arrayA[changeIndex] = arrayB[changeIndex].add(BigInteger.ONE);
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Given that " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " differ at index " + changeIndex 
                + ", asserting that they're different should not have failed";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertDifferentObjectArrayNoNPEForNullsDefaultMessage() {
        int length = RANDOM.nextInt(3) + 2;
        BigInteger[] arrayA = new BigInteger[length];
        BigInteger[] arrayB = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            BigInteger number = new BigInteger(72 + i, RANDOM);
            arrayA[i] = number;
            arrayB[i] = number;
        }
        int changeIndex = RANDOM.nextInt(length);
        arrayA[changeIndex] = null;
        boolean npeOccurred = false;
        boolean failOccurred = false;
        try {
            Asserters.assertDifferent(arrayA, arrayB);
        } catch (NullPointerException npe) {
            npeOccurred = true;
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msgA = "The null in " + Arrays.toString(arrayA) 
                + " should not have caused NPE";
        assert !npeOccurred : msgA;
        String msgB = "Given that " + Arrays.toString(arrayA) + " and " 
                + Arrays.toString(arrayB) + " differ at index " + changeIndex 
                + ", asserting that they're different should not have failed";
        assert !failOccurred : msgB;
    }
    
    @Test
    public void testAssertContainsButDoesNot() {
        int size = RANDOM.nextInt(16) + 4;
        BigInteger[] array = new BigInteger[size];
        BigInteger number = new BigInteger(84, RANDOM);
        for (int i = 0; i < size; i++) {
            number = number.add(BigInteger.valueOf(i));
            array[i] = number;
        }
        BigInteger notPresent = number.negate();
        boolean failOccurred = false;
        String arrayStr = Arrays.toString(array);
        String absentStr = notPresent.toString();
        try {
            Asserters.assertContains(notPresent, array, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected element " + absentStr + " to be in " 
                    + arrayStr;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + notPresent.toString() + " is in " 
                + arrayStr + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertContains() {
        System.out.println("assertContains");
        int size = RANDOM.nextInt(8) + 2;
        LocalDate[] array = new LocalDate[size];
        LocalDate date = LocalDate.now();
        int powerOfNegativeOne = -1;
        for (int i = 0; i < size; i++) {
            powerOfNegativeOne *= -1;
            date = date.plusDays(i * powerOfNegativeOne);
            array[i] = date;
        }
        LocalDate present = array[RANDOM.nextInt(size)];
        boolean failOccurred = false;
        try {
            Asserters.assertContains(present, array, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + present.toString() + " is in " 
                + Arrays.toString(array) + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsButDoesNotDefaultMessage() {
        int size = RANDOM.nextInt(16) + 4;
        BigInteger[] array = new BigInteger[size];
        BigInteger number = new BigInteger(84, RANDOM);
        for (int i = 0; i < size; i++) {
            number = number.add(BigInteger.valueOf(i));
            array[i] = number;
        }
        BigInteger notPresent = number.negate();
        boolean failOccurred = false;
        String arrayStr = Arrays.toString(array);
        String absentStr = notPresent.toString();
        try {
            Asserters.assertContains(notPresent, array);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected element " + absentStr + " to be in " 
                    + arrayStr;
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + notPresent.toString() + " is in " 
                + arrayStr + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsDefaultMessage() {
        int size = RANDOM.nextInt(8) + 2;
        LocalDate[] array = new LocalDate[size];
        LocalDate date = LocalDate.now();
        int powerOfNegativeOne = -1;
        for (int i = 0; i < size; i++) {
            powerOfNegativeOne *= -1;
            date = date.plusDays(i * powerOfNegativeOne);
            array[i] = date;
        }
        LocalDate present = array[RANDOM.nextInt(size)];
        boolean failOccurred = false;
        try {
            Asserters.assertContains(present, array);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + present.toString() + " is in " 
                + Arrays.toString(array) + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertListContainsButDoesNot() {
        Character.UnicodeScript[] array = Character.UnicodeScript.values();
        List<Character.UnicodeScript> intermediate = Arrays.asList(array);
        List<Character.UnicodeScript> list = new ArrayList<>(intermediate); 
        int removalIndex = RANDOM.nextInt(list.size());
        Character.UnicodeScript removed = list.remove(removalIndex);
        System.out.println("Successfully removed " + removed.toString() 
                + " from list of Unicode scripts");
        boolean failOccurred = false;
        try {
            Asserters.assertContains(removed, list, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected element " + removed.toString() + " to be in " 
                    + list.toString();
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + removed.toString() + " is in " 
                + list.toString() + " should have failed the test";
        assert failOccurred : msg;
    }

    @Test
    public void testAssertListContains() {
        DayOfWeek[] days = DayOfWeek.values();
        List<DayOfWeek> list = Arrays.asList(days);
        Locale locale = Locale.getDefault();
        for (DayOfWeek day : days) {
            boolean failOccurred = false;
            try {
                Asserters.assertContains(day, list, 
                        EXAMPLE_ASSERTION_MESSAGE_PART);
            } catch (AssertionError ae) {
                failOccurred = true;
            }
            String msg = "Asserting that list of days contains " 
                    + day.getDisplayName(TextStyle.FULL, locale) 
                    + " should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    @Test
    public void testAssertListContainsButDoesNotDefaultMessage() {
        Character.UnicodeScript[] array = Character.UnicodeScript.values();
        List<Character.UnicodeScript> intermediate = Arrays.asList(array);
        List<Character.UnicodeScript> list = new ArrayList<>(intermediate); 
        int removalIndex = RANDOM.nextInt(list.size());
        Character.UnicodeScript removed = list.remove(removalIndex);
        System.out.println("Successfully removed " + removed.toString() 
                + " from list of Unicode scripts");
        boolean failOccurred = false;
        try {
            Asserters.assertContains(removed, list);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected element " + removed.toString() 
                    + " to be in " + list.toString();
            String actual = ae.getMessage();
            System.out.println("\"" + actual + "\"");
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + removed.toString() + " is in " 
                + list.toString() + " should have failed the test";
        assert failOccurred : msg;
    }

    @Test
    public void testAssertListContainsDefaultMessage() {
        DayOfWeek[] days = DayOfWeek.values();
        List<DayOfWeek> list = Arrays.asList(days);
        Locale locale = Locale.getDefault();
        for (DayOfWeek day : days) {
            boolean failOccurred = false;
            try {
                Asserters.assertContains(day, list);
            } catch (AssertionError ae) {
                failOccurred = true;
            }
            String msg = "Asserting that list of days contains " 
                    + day.getDisplayName(TextStyle.FULL, locale) 
                    + " should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    private static Set<Currency> pickOutHistoricals(Set<Currency> currencies) {
        Set<Currency> historicals = new HashSet<>();
        for (Currency currency : currencies) {
            String displayName = currency.getDisplayName();
            if (displayName.contains("19") || displayName.contains("20")) {
                historicals.add(currency);
            }
        }
        return historicals;
    }
    
    @Test
    public void testAssertSetContainsButDoesNot() {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        Set<Currency> historicals = pickOutHistoricals(currencies);
        currencies.removeAll(historicals);
        for (Currency historical : historicals) {
            boolean failOccurred = false;
            try {
                Asserters.assertContains(historical, currencies, 
                        EXAMPLE_ASSERTION_MESSAGE_PART);
            } catch (AssertionError ae) {
                failOccurred = true;
                String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                        + ". Expected element " + historical.toString() 
                        + " to be in " + currencies.toString();
                String actual = ae.getMessage();
                System.out.println("\"" + actual + "\"");
                String msg = "Expected \"" + expected + "\" but was \"" + actual 
                        + "\"";
                assert expected.equals(actual) : msg;
            }
            String msg = "Asserting that set contains " 
                    + historical.getDisplayName() + " (" 
                    + historical.getCurrencyCode() 
                    + ") after it was removed should've failed the test";
            assert failOccurred : msg;
        }
    }

    @Test
    public void testAssertSetContains() {
        Set<Color> set = new HashSet<>();
        Color prevColor = Color.BLACK;
        Color nextColor = new Color(RANDOM.nextInt());
        do {
            set.add(nextColor);
            prevColor = nextColor;
            nextColor = nextColor.brighter();
        } while (!prevColor.equals(nextColor));
        for (Color color : set) {
            boolean failOccurred = false;
            try {
                Asserters.assertContains(color, set, 
                        EXAMPLE_ASSERTION_MESSAGE_PART);
            } catch (AssertionError ae) {
                failOccurred = true;
            }
            String msg = "Asserting that " + color.toString() + " is in " 
                    + set.toString() + " should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    @Test
    public void testAssertSetContainsButDoesNotDefaultMessage() {
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        Set<Currency> historicals = pickOutHistoricals(currencies);
        currencies.removeAll(historicals);
        for (Currency historical : historicals) {
            boolean failOccurred = false;
            try {
                Asserters.assertContains(historical, currencies);
            } catch (AssertionError ae) {
                failOccurred = true;
                String expected = "Expected element " + historical.toString() 
                        + " to be in " + currencies.toString();
                String actual = ae.getMessage();
                System.out.println("\"" + actual + "\"");
                String msg = "Expected \"" + expected + "\" but was \"" + actual 
                        + "\"";
                assert expected.equals(actual) : msg;
            }
            String msg = "Asserting that set contains " 
                    + historical.getDisplayName() + " (" 
                    + historical.getCurrencyCode() 
                    + ") after it was removed should've failed the test";
            assert failOccurred : msg;
        }
    }

    @Test
    public void testAssertSetContainsDefaultMessage() {
        Set<Color> set = new HashSet<>();
        Color prevColor = Color.WHITE;
        Color nextColor = new Color(RANDOM.nextInt());
        do {
            set.add(nextColor);
            prevColor = nextColor;
            nextColor = nextColor.darker();
        } while (!prevColor.equals(nextColor));
        for (Color color : set) {
            boolean failOccurred = false;
            try {
                Asserters.assertContains(color, set);
            } catch (AssertionError ae) {
                failOccurred = true;
            }
            String msg = "Asserting that " + color.toString() + " is in " 
                    + set.toString() + " should not have failed the test";
            assert !failOccurred : msg;
        }
    }
    
    @Test
    public void testAssertArrayContainsSameButDoesNot() {
        Thread.State[] arrayA = Thread.State.values();
        Thread.State[] arrayB = new Thread.State[arrayA.length];
        System.arraycopy(arrayA, 1, arrayB, 0, arrayA.length - 1);
        String arrayAStr = Arrays.toString(arrayA);
        String arrayBStr = Arrays.toString(arrayB);
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected array to contain " + arrayAStr 
                    + " but actually contained " + arrayBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + arrayAStr 
                + " contains the same elements as " + arrayBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    private static IntSummaryStatistics makeRandomSummary(int n) {
        IntSummaryStatistics summary = new IntSummaryStatistics();
        for (int i = 0; i < n; i++) {
            summary.accept(RANDOM.nextInt());
        }
        return summary;
    }
    
    @Test
    public void testAssertContainsSame() {
        System.out.println("assertContainsSame");
        int size = RANDOM.nextInt(3) + 2;
        IntSummaryStatistics[] arrayA = new IntSummaryStatistics[size];
        IntSummaryStatistics[] arrayB = new IntSummaryStatistics[size + 1];
        for (int i = 0; i < size; i++) {
            int n = i + RANDOM.nextInt(16) + 4;
            IntSummaryStatistics summary = makeRandomSummary(n);
            arrayA[i] = summary;
            arrayB[size - i - 1] = summary;
        }
        arrayB[size] = arrayA[size - 1];
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(arrayA, arrayB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println(ae.getMessage());
        }
        String arrAStr = Arrays.toString(arrayA);
        String arrBStr = Arrays.toString(arrayB);
        String msg = "Asserting that " + arrAStr + " and " + arrBStr 
                + " contain the same elements should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertArrayContainsSameButDoesNotDefaultMessage() {
        Thread.State[] arrayA = Thread.State.values();
        Thread.State[] arrayB = new Thread.State[arrayA.length];
        System.arraycopy(arrayA, 1, arrayB, 0, arrayA.length - 1);
        String arrayAStr = Arrays.toString(arrayA);
        String arrayBStr = Arrays.toString(arrayB);
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(arrayA, arrayB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected array to contain " + arrayAStr 
                    + " but actually contained " + arrayBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + arrayAStr 
                + " contains the same elements as " + arrayBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsSameDefaultMessage() {
        int size = RANDOM.nextInt(3) + 2;
        IntSummaryStatistics[] arrayA = new IntSummaryStatistics[size];
        IntSummaryStatistics[] arrayB = new IntSummaryStatistics[size + 1];
        for (int i = 0; i < size; i++) {
            int n = i + RANDOM.nextInt(16) + 4;
            IntSummaryStatistics summary = makeRandomSummary(n);
            arrayA[i] = summary;
            arrayB[size - i - 1] = summary;
        }
        arrayB[size] = arrayA[size - 1];
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(arrayA, arrayB);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println(ae.getMessage());
        }
        String arrAStr = Arrays.toString(arrayA);
        String arrBStr = Arrays.toString(arrayB);
        String msg = "Asserting that " + arrAStr + " and " + arrBStr 
                + " contain the same elements should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertListContainsSameButDoesNot() {
        List<NumericShaper.Range> listA 
                = Arrays.asList(NumericShaper.Range.values());
        List<NumericShaper.Range> listB = new ArrayList<>(listA);
        listB.remove(0);
        String listAStr = listA.toString();
        String listBStr = listB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(listA, listB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected list to contain " + listAStr 
                    + " but actually contained " + listBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + listAStr 
                + " contains the same elements as " + listBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertListContainsSame() {
        DayOfWeek[] days = DayOfWeek.values();
        List<DayOfWeek> listA = Arrays.asList(days);
        List<DayOfWeek> listB = new ArrayList<>(listA);
        Collections.shuffle(listB, RANDOM);
        listB.add(LocalDate.now().getDayOfWeek());
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(listA, listB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println(ae.getMessage());
        }
        String msg = "Asserting that " + listA.toString() + " and " 
                + listB.toString() 
                + " contain the same elements should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertListContainsSameButDoesNotDefaultMessage() {
        List<NumericShaper.Range> listA 
                = Arrays.asList(NumericShaper.Range.values());
        List<NumericShaper.Range> listB = new ArrayList<>(listA);
        listB.remove(1);
        String listAStr = listA.toString();
        String listBStr = listB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(listA, listB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected list to contain " + listAStr 
                    + " but actually contained " + listBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + listAStr 
                + " contains the same elements as " + listBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertListContainsSameDefaultMessage() {
        DayOfWeek[] days = DayOfWeek.values();
        List<DayOfWeek> listA = Arrays.asList(days);
        List<DayOfWeek> listB = new ArrayList<>(listA);
        Collections.shuffle(listB, RANDOM);
        listB.add(LocalDate.now().getDayOfWeek());
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(listA, listB);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println(ae.getMessage());
        }
        String msg = "Asserting that " + listA.toString() + " and " 
                + listB.toString() 
                + " contain the same elements should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertSetContainsSameButDoesNot() {
        int size = RANDOM.nextInt(16) + 4;
        Set<BigInteger> setA = new HashSet<>(size);
        TreeSet<BigInteger> setB = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            setA.add(number);
            setB.add(number);
        }
        setA.add(setB.first().negate());
        String setAStr = setA.toString();
        String setBStr = setB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(setA, setB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected set to contain " + setAStr 
                    + " but actually contained " + setBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + setAStr 
                + " contains the same elements as " + setBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertSetContainsSame() {
        Set<Currency> someSet = Currency.getAvailableCurrencies();
        Set<Currency> sameSet = new TreeSet<>((a, b) 
                -> a.getCurrencyCode().compareTo(b.getCurrencyCode()));
        sameSet.addAll(someSet);
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(someSet, sameSet, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + someSet.toString() 
                + " contains the same elements as " + sameSet.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertSetContainsSameButDoesNotThoughSameSize() {
        int size = RANDOM.nextInt(8) + 2;
        Set<LocalDateTime> setA = new HashSet<>(size);
        Set<LocalDateTime> setB = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            setA.add(LocalDateTime.now().minusHours(i));
            setB.add(LocalDateTime.now().plusHours(i));
        }
        String setAStr = setA.toString();
        String setBStr = setB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(setA, setB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected set to contain " + setAStr 
                    + " but actually contained " + setBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + setAStr 
                + " contains the same elements as " + setBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertSetContainsSameButDoesNotDefaultMessage() {
        int size = RANDOM.nextInt(16) + 4;
        Set<BigInteger> setA = new HashSet<>(size);
        TreeSet<BigInteger> setB = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            setA.add(number);
            setB.add(number);
        }
        setA.add(setB.first().negate());
        String setAStr = setA.toString();
        String setBStr = setB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(setA, setB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected set to contain " + setAStr 
                    + " but actually contained " + setBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + setAStr 
                + " contains the same elements as " + setBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertSetContainsSameDefaultMessage() {
        Set<Currency> someSet = Currency.getAvailableCurrencies();
        Set<Currency> sameSet = new TreeSet<>((a, b) 
                -> a.getCurrencyCode().compareTo(b.getCurrencyCode()));
        sameSet.addAll(someSet);
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(someSet, sameSet);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + someSet.toString() 
                + " contains the same elements as " + sameSet.toString() 
                + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertSetContainsSameButDoesNotThoughSameSizeDefMsg() {
        int size = RANDOM.nextInt(8) + 2;
        Set<LocalDateTime> setA = new HashSet<>(size);
        Set<LocalDateTime> setB = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            setA.add(LocalDateTime.now().minusHours(i));
            setB.add(LocalDateTime.now().plusHours(i));
        }
        String setAStr = setA.toString();
        String setBStr = setB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSame(setA, setB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected set to contain " + setAStr 
                    + " but actually contained " + setBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + setAStr 
                + " contains the same elements as " + setBStr 
                + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsSameOrderButDiffersInLength() {
        int listALength = RANDOM.nextInt(8) + 2;
        int listBLength = listALength + RANDOM.nextInt(8) + 2;
        List<IntSummaryStatistics> listA = new ArrayList<>(listALength);
        List<IntSummaryStatistics> listB = new ArrayList<>(listBLength);
        for (int i = 0; i < listALength; i++) {
            IntSummaryStatistics stats = makeRandomSummary(2 * i + 1);
            listA.add(stats);
            listB.add(stats);
        }
        for (int j = listALength; j < listBLength; j++) {
            IntSummaryStatistics stats = makeRandomSummary(j);
            listB.add(stats);
        }
        String listAStr = listA.toString();
        String listBStr = listB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSameOrder(listA, listB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected list to contain " + listAStr 
                    + " in that order but actually contained " + listBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + listAStr 
                + " contains the same elements as " + listBStr 
                + " in the same order should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsSameOrder() {
        System.out.println("assertContainsSameOrder");
        int len = RANDOM.nextInt(16) + 4;
        List<BigInteger> someList = new ArrayList<>(len);
        List<BigInteger> sameList = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            someList.add(number);
            sameList.add(number);
        }
        String someListAStr = someList.toString();
        String sameListBStr = sameList.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSameOrder(someList, sameList, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + someListAStr 
                + " contains the same elements as " + sameListBStr 
                + " in the same order should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsSameOrderButDiffersInOrder() {
        int len = RANDOM.nextInt(8) + 2;
        BigDecimal max = BigDecimal.valueOf(len);
        List<BigDecimal> listA = new ArrayList<>(len);
        List<BigDecimal> listB = new LinkedList<>();
        BigDecimal origin = BigDecimal.valueOf(RANDOM.nextDouble() + 1);
        BigDecimal previous = origin;
        for (BigDecimal x = BigDecimal.ZERO; x.compareTo(max) < 0; 
                x = x.add(BigDecimal.ONE)) {
            BigDecimal number = previous.multiply(origin).add(x);
            listA.add(number);
            listB.add(0, number);
            previous = number;
        }
        String listAStr = listA.toString();
        String listBStr = listB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSameOrder(listA, listB, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Expected list to contain " + listAStr 
                    + " in that order but actually contained " + listBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + listAStr 
                + " contains the same elements as " + listBStr 
                + " in the same order should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsSameOrderButDiffersInLengthDefaultMessage() {
        int listALength = RANDOM.nextInt(8) + 2;
        int listBLength = listALength + RANDOM.nextInt(8) + 2;
        List<IntSummaryStatistics> listA = new ArrayList<>(listALength);
        List<IntSummaryStatistics> listB = new ArrayList<>(listBLength);
        for (int i = 0; i < listALength; i++) {
            IntSummaryStatistics stats = makeRandomSummary(2 * i + 1);
            listA.add(stats);
            listB.add(stats);
        }
        for (int j = listALength; j < listBLength; j++) {
            IntSummaryStatistics stats = makeRandomSummary(j);
            listB.add(stats);
        }
        String listAStr = listA.toString();
        String listBStr = listB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSameOrder(listA, listB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected list to contain " + listAStr 
                    + " in that order but actually contained " + listBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + listAStr 
                + " contains the same elements as " + listBStr 
                + " in the same order should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsSameOrderDefaultMessage() {
        int len = RANDOM.nextInt(16) + 4;
        List<BigInteger> someList = new ArrayList<>(len);
        List<BigInteger> sameList = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            BigInteger number = new BigInteger(64 + i, RANDOM);
            someList.add(number);
            sameList.add(number);
        }
        String someListAStr = someList.toString();
        String sameListBStr = sameList.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSameOrder(someList, sameList);
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        String msg = "Asserting that " + someListAStr 
                + " contains the same elements as " + sameListBStr 
                + " in the same order should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertContainsSameOrderButDiffersInOrderDefaultMessage() {
        int len = RANDOM.nextInt(8) + 2;
        BigDecimal max = BigDecimal.valueOf(len);
        List<BigDecimal> listA = new ArrayList<>(len);
        List<BigDecimal> listB = new LinkedList<>();
        BigDecimal origin = BigDecimal.valueOf(RANDOM.nextDouble() + 1);
        BigDecimal previous = origin;
        for (BigDecimal x = BigDecimal.ZERO; x.compareTo(max) < 0; 
                x = x.add(BigDecimal.ONE)) {
            BigDecimal number = previous.multiply(origin).add(x);
            listA.add(number);
            listB.add(0, number);
            previous = number;
        }
        String listAStr = listA.toString();
        String listBStr = listB.toString();
        boolean failOccurred = false;
        try {
            Asserters.assertContainsSameOrder(listA, listB);
        } catch (AssertionError ae) {
            failOccurred = true;
            String expected = "Expected list to contain " + listAStr 
                    + " in that order but actually contained " + listBStr;
            String actual = ae.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that " + listAStr 
                + " contains the same elements as " + listBStr 
                + " in the same order should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertPrintout() {
        System.out.println("assertPrintOut");
        String currTimeStr = LocalDateTime.now().toString();
        String expected = "assertPrintOut() test ran on " + currTimeStr;
        boolean failOccurred = false;
        String actual = "Initial value";
        try {
            actual = Asserters.assertPrintOut(s -> s.contains(currTimeStr), 
                    () -> {
                        System.out.println(expected);
                    }, EXAMPLE_ASSERTION_MESSAGE_PART).replace("\n", "")
                            .replace("\r", "");
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println(ae.getMessage());
        }
        String eqMsg = "Expected \"" + expected + "\" but was \"" + actual 
                + "\"";
        assert expected.equals(actual) : eqMsg;
        String msg = "Asserting that \"" + expected + "\" contains \"" 
                + currTimeStr + "\" should not have failed the test";
        assert !failOccurred : msg;        
    }
    
    @Test
    public void testAssertPrintoutButDoesNot() {
        String example = "Example that will not be printed out correctly"
                .toLowerCase().replace(" ", "");
        String modified = example.toUpperCase();
        String regex = "\\p{javaLowerCase}{" + example.length() + "}";
        String expected = "Expecting \"" + modified + "\" to match regex \"" 
                + regex + "\"";
        boolean failOccurred = false;
        try {
            Asserters.assertPrintOut(s -> s.matches(regex), () -> {
                System.out.println(modified);
            }, expected);
        } catch (AssertionError ae) {
            failOccurred = true;
            String actual = ae.getMessage();
            String msg = "Expecting \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting that \"" + modified + "\" matches \"" + regex 
                + "\" should have failed the test";
        assert failOccurred : msg;        
    }
    
    @Test
    public void testAssertPrintOutWrapsUnexpectedExceptionWithMessage() {
        boolean exceptionOccurred = false;
        String msg = "FOR TESTING PURPOSES ONLY";
        int n = RANDOM.nextInt();
        try {
            Asserters.assertPrintOut(s -> !s.isEmpty(), () -> {
                System.out.println(n / 0);
            }, msg);
        } catch (RuntimeException re) {
            exceptionOccurred = true;
            Throwable cause = re.getCause();
            assert cause instanceof ArithmeticException 
                    : "RuntimeException should wrap ArithmeticException";
            String excMsg = re.getMessage();
            assert !excMsg.isEmpty() : "Exception message should not be empty";
        }
        String excOccurMsg = "Lambda that tries to print out " + n 
                + " divided by 0 should've cause exception";
        assert exceptionOccurred : excOccurMsg;
    }
    
    @Test
    public void testAssertThrows() {
        System.out.println("assertThrows");
        String msg = "Division by zero should cause ArithmeticException";
        ArithmeticException ae = Asserters.assertThrows(() -> {
            System.out.println("3 divided by 0 equals " + (3 / 0));
        }, ArithmeticException.class, msg);
        assert ae != null : "Returned object should not be null";
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
            String errMsg = ae.getMessage();
            System.out.println("\"" + errMsg + "\"");
            String checkMsg 
                    = "Failure should report expected exception";
            assert errMsg.contains("ArithmeticException") : checkMsg;
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
    public void testAssertThrowsSavesWrongExceptionThrown() {
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            IllegalArgumentException iae = Asserters.assertThrows(() -> {
                throw expected;
            }, IllegalArgumentException.class, 
                    "Throwing wrong exception on purpose");
            System.out.println("Thrown exception is of type " 
                    + iae.getClass().getName());
        } catch (AssertionError ae) {
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
    }
    
    @Test
    public void testAssertThrowsDefaultMessage() {
        ArithmeticException ae = Asserters.assertThrows(() -> {
            System.out.println("5 divided by 0 equals " + (5 / 0));
        }, ArithmeticException.class);
        assert ae != null : "Returned object should not be null";
        String msg = "Division by zero should cause ArithmeticException";
        assert ae instanceof ArithmeticException : msg;
    }

    @Test
    public void testAssertThrowsButDoesNotThrowAnyDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if no exception throws";
        try {
            Asserters.assertThrows(() -> {
                System.out.println(msg);
            }, ArithmeticException.class);
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            assert errMsg != null : "Error message should not be null";
            assert !errMsg.isEmpty() : "Error message should not be empty";
            System.out.println("\"" + errMsg + "\"");
            String checkMsg 
                    = "Failure should report expected exception";
            assert errMsg.contains("ArithmeticException") : checkMsg;
        }
        assert failOccurred : msg;
    }

    @Test
    public void testAssertThrowsButThrowsWrongTypeDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if wrong exception type thrown";
        try {
            RuntimeException re = Asserters.assertThrows(() -> {
                throw new RuntimeException("Sorry, wrong type exception");
            }, ArithmeticException.class);
            System.out.println("Thrown exception is of type " 
                    + re.getClass().getName());
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            assert errMsg != null : "Error message should not be null";
            assert !errMsg.isEmpty() : "Error message should not be empty";
            System.out.println("\"" + errMsg + "\"");
            String checkMsg 
                    = "Failure should report expected, unexpected exceptions";
            assert errMsg.contains("ArithmeticException") : checkMsg;
            assert errMsg.contains("RuntimeException") : checkMsg;
        }
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertThrowsSavesWrongExceptionThrownDefaultMessage() {
        boolean failOccurred = false;
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            IllegalArgumentException iae = Asserters.assertThrows(() -> {
                throw expected;
            }, IllegalArgumentException.class);
            System.out.println("Thrown exception is of type " 
                    + iae.getClass().getName());
        } catch (AssertionError ae) {
            failOccurred = true;
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
        assert failOccurred : "Wrong type should've failed test";
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
    public void testAssertDoesNotThrowSavesThrown() {
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            Asserters.assertDoesNotThrow(() -> {
                throw expected;
            }, "Throwing exception on purpose");
        } catch (AssertionError ae) {
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
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
    
    @Test
    public void testAssertDoesNotThrowButDoesDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Assertion should fail if exception thrown";
        try {
            Asserters.assertDoesNotThrow(() -> {
                throw new RuntimeException("Sorry");
            });
        } catch (AssertionError ae) {
            failOccurred = true;
            String errMsg = ae.getMessage();
            assert errMsg != null : "Error message should not be null";
            assert !errMsg.isEmpty() : "Error message should not be empty";
            System.out.println("\"" + errMsg + "\"");
            String checkMsg = "Failure should report unexpected exception";
            assert errMsg.contains("RuntimeException") : checkMsg;
        }
        assert failOccurred : msg;
    }

    @Test
    public void testAssertDoesNotThrowSavesThrownDefaultMessage() {
        RuntimeException expected 
                = new RuntimeException("For testing purposes");
        try {
            Asserters.assertDoesNotThrow(() -> {
                throw expected;
            });
        } catch (AssertionError ae) {
            Throwable actual = ae.getCause();
            String msg = "Expected " + expected.toString() + " but got " 
                    + actual;
            assert expected.equals(actual) : msg;
        }
    }

    @Test
    public void testAssertDoesNotThrowDefaultMessage() {
        boolean failOccurred = false;
        String msg = "Operation should not cause any exception";
        try {
            Asserters.assertDoesNotThrow(() -> {
                System.out.println("This should not cause any exception");
            });
        } catch (AssertionError ae) {
            failOccurred = true;
        }
        assert !failOccurred : msg;
    }
    
    @Test
    public void testAssertTimeoutButRunsOver() {
        int milliseconds = RANDOM.nextInt(4096) + 1024;
        Duration duration = Duration.of(milliseconds, ChronoUnit.MILLIS);
        TimeoutExceptionRecorder handler = new TimeoutExceptionRecorder();
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                System.out.println("Starting at 0");
                Asserters.assertTimeout(() -> {
                    long counter = 0L;
                    do {
                        counter++;
                    } while (counter != 0L);
                    System.out.println("Got back to 0");
                }, duration, EXAMPLE_ASSERTION_MESSAGE_PART);
            }
            
        };
        thread.setUncaughtExceptionHandler(handler);
        boolean failOccurred = false;
        System.out.println("Testing assertTimeout() for " + milliseconds 
                + " milliseconds...");
        try {
            thread.start();
            Thread.sleep(milliseconds + TIMEOUT_GRACE_PERIOD_MILLISECONDS);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        Throwable t = handler.record;
        if (t != null) {
            assert t instanceof AssertionError 
                    : "Uncaught throwable should be an assertion error";
            failOccurred = true;
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART 
                    + ". Procedure took longer than allotted duration " 
                    + duration.toString();
            String actual = t.getMessage();
            String msg = "Expecting \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Asserting excessive counting could occur in less than " 
                + duration.toString() + " should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testAssertTimeout() {
        System.out.println("assertTimeout");
        int milliseconds = RANDOM.nextInt(4096) + 1024;
        Duration duration = Duration.of(milliseconds, ChronoUnit.MILLIS);
        TimeoutExceptionRecorder handler = new TimeoutExceptionRecorder();
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                System.out.println("Starting at 0");
                Asserters.assertTimeout(() -> {
                    int counter = 0;
                    int max = milliseconds / 10;
                    do {
                        counter++;
                    } while (counter < max);
                    System.out.println("Reached " + max);
                }, duration, EXAMPLE_ASSERTION_MESSAGE_PART);
            }
            
        };
        thread.setUncaughtExceptionHandler(handler);
        boolean failOccurred = false;
        System.out.println("Testing assertTimeout() for " + milliseconds 
                + " milliseconds...");
        try {
            thread.start();
            Thread.sleep(TIMEOUT_GRACE_PERIOD_MILLISECONDS);
            boolean stillRunning = thread.isAlive();
            assert !stillRunning 
                    : "Quick counting test should've finished already";
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        Throwable t = handler.record;
        if (t instanceof AssertionError) {
            failOccurred = true;
        }
        String msg = "Asserting quick counting could occur in less than " 
                + duration.toString() + " should not have failed the test";
        assert !failOccurred : msg;
    }
    
    @Test
    public void testTimedTestsCanHaveOtherAssertions() {
        System.out.println("assertTimeout");
        int milliseconds = RANDOM.nextInt(4096) + 1024;
        Duration duration = Duration.of(milliseconds, ChronoUnit.MILLIS);
        String expected = "Message from an assertion other than timeout";
        TimeoutExceptionRecorder handler = new TimeoutExceptionRecorder();
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                System.out.println("Starting at 0");
                Asserters.assertTimeout(() -> {
                    int counter = 0;
                    int max = milliseconds / 10;
                    do {
                        counter++;
                    } while (counter < max);
                    System.out.println("Reached " + max);
                    assert counter == 0 : expected;
                }, duration, EXAMPLE_ASSERTION_MESSAGE_PART);
            }
            
        };
        thread.setUncaughtExceptionHandler(handler);
        boolean failOccurred = false;
        System.out.println("Testing assertTimeout() for " + milliseconds 
                + " milliseconds...");
        try {
            thread.start();
            Thread.sleep(TIMEOUT_GRACE_PERIOD_MILLISECONDS);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        Throwable t = handler.record;
        if (t instanceof AssertionError) {
            failOccurred = true;
            String actual = t.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Different assertion should have failed the test";
        assert failOccurred : msg;
    }
    
    @Test
    public void testTimedTestsCanHaveExceptions() {
        System.out.println("assertTimeout");
        int milliseconds = RANDOM.nextInt(4096) + 1024;
        Duration duration = Duration.of(milliseconds, ChronoUnit.MILLIS);
        String expected = "Message from an exception";
        TimeoutExceptionRecorder handler = new TimeoutExceptionRecorder();
        Thread thread = new Thread() {
            
            @Override
            public void run() {
                int max = milliseconds / 10;
                System.out.println("Starting at " + max);
                Asserters.assertTimeout(() -> {
                    int counter = max;
                    do {
                        counter--;
                    } while (counter > 0);
                    System.out.println("Reached 0");
                    throw new ArithmeticException(expected);
                }, duration, EXAMPLE_ASSERTION_MESSAGE_PART);
            }
            
        };
        thread.setUncaughtExceptionHandler(handler);
        boolean exceptionOccurred = false;
        System.out.println("Testing assertTimeout() for " + milliseconds 
                + " milliseconds...");
        try {
            thread.start();
            Thread.sleep(TIMEOUT_GRACE_PERIOD_MILLISECONDS);
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
        Throwable t = handler.record;
        if (t instanceof RuntimeException) {
            exceptionOccurred = true;
            Throwable wrapper = t.getCause();
            assert wrapper != null : "Reported exception should wrap another";
            Throwable wrapped = wrapper.getCause();
            assert wrapped != null : "Wrapped exception should not be null";
            String actual = wrapped.getMessage();
            String msg = "Expected \"" + expected + "\" but was \"" + actual 
                    + "\"";
            assert expected.equals(actual) : msg;
        }
        String msg = "Exception from timed test should have been reported";
        assert exceptionOccurred : msg;
    }
    
    private static class TimeoutExceptionRecorder 
            implements Thread.UncaughtExceptionHandler {
        
        private Throwable record = null;
        
        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            record = throwable;
        }
        
    }
    
}
