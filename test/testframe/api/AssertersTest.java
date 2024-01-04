package testframe.api;

import java.awt.Color;
import java.awt.font.NumericShaper;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

/**
 * Tests of the Asserters class. These are more elegant than the tests of 
 * testframe.engine.TestRunner, but still more clunky than the tests that can be
 * written once Asserters is fully tested and proven.
 * @author Alonso del Arte
 */
public class AssertersTest {
    
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
        assert failOccurred : "fail() should have occurred when called";
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
        double nearNumber = someNumber + LOCAL_DELTA / 2;
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
    
    // TODO: Reassess this test consider that it failed for 
    // Asserting 0.993872748424878 is equal to 1.0016852484248782 within 
    // variance 0.0078125 should not have failed the test. According to the 
    // Windows 10 Calculator, the variance here is 0.0078125000000002.
//    @Test
    public void testAssertEqualsDoubleJustAtVariance() {
        double someNumber = RANDOM.nextDouble();
        double variance = 0.0078125;
        double nearNumber = someNumber + variance;
        boolean failOccurred = false;
        try {
            Asserters.assertEquals(someNumber, nearNumber, variance, 
                    EXAMPLE_ASSERTION_MESSAGE_PART);
        } catch (AssertionError ae) {
            failOccurred = true;
            System.out.println("\"" + ae.getMessage() + "\"");
        }
        String message = "Asserting " + someNumber + " is equal to " 
                + nearNumber + " within variance " + variance 
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
    
    // TODO: Test in which two Object[] same length but all nulls
  
    // TODO: Write more tests for assertEquals() for arrays

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
            String expected = EXAMPLE_ASSERTION_MESSAGE_PART + ". Value " 
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
            String expected = "Value " + aboveMaximum 
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
            String msgStandardPart = "Value " + aboveMaximum 
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
            String expected = "Value " + aboveMaximum 
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
    
    // TODO: Write tests for assertInRange()
    
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
        Set<Currency> historicals = new HashSet<Currency>();
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
    
    // TODO: Write tests for assertContainsSame(arrays)
    
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
        List<DayOfWeek> listB = new ArrayList<DayOfWeek>(listA);
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
    
}
