package org.testframe.api.random;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.testframe.api.Asserters.*;
import org.testframe.api.Test;

public class PseudorandomnessTest {
    
    private static final char ASCII_PRINT_START = ' ';
    
    private static final char ASCII_PRINT_STOP = '\u007F';
    
    private static final int NUMBER_OF_BITS = Integer.BYTES * 8;
    
    private static final Random LOCAL_RANDOM = new Random();
    
    private int bitSource;
    
    private boolean getNextBooleanOffBitSource() {
        int leastSignificantBit = this.bitSource & 1;
        this.bitSource >>= 1;
        return leastSignificantBit == 1;
    }
    
    private static int[] makeIntArray(int len) {
        long source = System.currentTimeMillis();
        long adjust = len > 8 * Long.BYTES ? ~source : 0L;
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = (int) source;
            source >>= 1;
            source += adjust;
            source -= 1431655764;
            source += i;
        }
        return array;
    }
    
    @Test
    public void testGiveNumbersCalledUponRefreshInterval() {
        int[] nums = makeIntArray(Pseudorandomness.REFRESH_INTERVAL);
        MockProvider provider = new MockProvider(nums);
        Pseudorandomness instance = new Pseudorandomness(provider);
        int lastNum = 0;
        int numberOfCallsSoFar = 0;
        while (numberOfCallsSoFar < Pseudorandomness.REFRESH_INTERVAL) {
            lastNum = instance.nextInt();
            numberOfCallsSoFar++;
        }
        String msg = "After giving number " + lastNum 
                + " and REFRESH_INTERVAL - 1 others, next call should refresh";
        assertDoesNotThrow(() -> {
            int nextNum = instance.nextInt();
            System.out.println("Successfully got random number " + nextNum);
        }, msg);
    }

    @Test
    public void testNextBoolean() {
        System.out.println("nextBoolean");
        int numberA = (int) System.currentTimeMillis();
        int numberB = (int) (System.currentTimeMillis() >> NUMBER_OF_BITS);
        int[] nums = {numberA, numberB};
        ExternalRandomnessProvider provider = new MockProvider(nums);
        Pseudorandomness instance = new Pseudorandomness(provider);
        this.bitSource = numberA;
        String msgPart = "Given position from right ";
        String msgAPart = " in " + numberA + " (A), expected ";
        for (int i = 0; i < NUMBER_OF_BITS; i++) {
            boolean expected = this.getNextBooleanOffBitSource();
            boolean actual = instance.nextBoolean();
            String msg = msgPart + i + msgAPart + expected;
            assert expected == actual : msg;
        }
        this.bitSource = numberB;
        String msgBPart = " in " + numberB + " (B), expected ";
        for (int j = 0; j < NUMBER_OF_BITS; j++) {
            boolean expected = this.getNextBooleanOffBitSource();
            boolean actual = instance.nextBoolean();
            String msg = msgPart + j + msgBPart + expected;
            assert expected == actual : msg;
        }
    }
    
    @Test
    public void testFlipCoin() {
        System.out.println("flipCoin");
        long millis = System.currentTimeMillis();
        int number = (int) ((millis >> NUMBER_OF_BITS) ^ millis);
        int[] nums = {number};
        ExternalRandomnessProvider provider = new MockProvider(nums);
        Pseudorandomness instance = new Pseudorandomness(provider);
        int expHeadsOrTails = Integer.bitCount(number);
        int expTailsOrHeads = NUMBER_OF_BITS - expHeadsOrTails;
        int[] actualHeadsAndTailsCount = {0, 0};
        for (int i = 0; i < NUMBER_OF_BITS; i++) {
            CoinSide side = instance.flipCoin();
            actualHeadsAndTailsCount[side.ordinal()]++;
        }
        boolean result = (actualHeadsAndTailsCount[0] == expHeadsOrTails 
                && actualHeadsAndTailsCount[1] == expTailsOrHeads)
                || (actualHeadsAndTailsCount[0] == expTailsOrHeads 
                        && actualHeadsAndTailsCount[1] == expHeadsOrTails);
        String msg = "Expected " + expHeadsOrTails + " heads and " 
                + expTailsOrHeads + " tails or vice-versa, got " 
                + Arrays.toString(actualHeadsAndTailsCount);
        assert result : msg;
    }
    
    @Test
    public void testNextInt() {
        System.out.println("nextInt");
        int[] nums = new int[Pseudorandomness.REFRESH_INTERVAL];
        for (int i = 0; i < Pseudorandomness.REFRESH_INTERVAL; i++) {
            nums[i] = i;
        }
        MockProvider provider = new MockProvider(nums);
        Pseudorandomness instance = new Pseudorandomness(provider);
        for (int j = 0; j < Pseudorandomness.REFRESH_INTERVAL; j++) {
            int expected = nums[j];
            int actual = instance.nextInt();
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public void testNextIntBounded() {
        int len = Long.SIZE;
        int[] nums = new int[len];
        long source = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            nums[i] = (int) source;
            source >>= 1;
        }
        MockProvider provider = new MockProvider(nums);
        CallTrackingPseudorandomness tracker 
                = new CallTrackingPseudorandomness(provider);
        int minimum = 0;
        int maximum = 1 << 14;
        String msgPart = " should be at least " + minimum 
                + " but not more than " + maximum;
        for (int expected = 0; expected < len; expected++) {
            String callMsg = "As bounded nextInt() has been called " + expected 
                    + " times, unbounded nextInt() should've been called also";
            int actual = tracker.nextIntCallsSoFar;
            assertEquals(expected, actual, callMsg);
            int number = tracker.nextInt(maximum);
            String msg = "Number " + number + msgPart;
            assertInRange(minimum, number, maximum, msg);
        }
    }

    @Test
    public void testNextPowerOfTwo() {
        System.out.println("nextPowerOfTwo");
        Set<Integer> expected = new HashSet<Integer>(NUMBER_OF_BITS - 1);
        int power = 1 << 30;
        while (power > 0) {
            expected.add(power);
            power >>= 1;
        }
        Set<Integer> actual = new HashSet<Integer>(NUMBER_OF_BITS - 1);
        int[] nums = makeIntArray(Pseudorandomness.REFRESH_INTERVAL);
        MockProvider provider = new MockProvider(nums);
        Pseudorandomness instance = new Pseudorandomness(provider);
        int totalNumberOfCalls = NUMBER_OF_BITS * NUMBER_OF_BITS;
        int callsSoFar = 0;
        while (callsSoFar < totalNumberOfCalls) {
            int claimedPower = instance.nextPowerOfTwo();
            actual.add(claimedPower);
            String msg = "Number " + claimedPower + " should be a power of two";
            assertEquals(Integer.highestOneBit(claimedPower), claimedPower, 
                    msg);
            callsSoFar++;
        }
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNextASCIIChar() {
        System.out.println("nextASCIIChar");
        int[] nums = new int[ASCII_PRINT_STOP - ASCII_PRINT_START];
        int currNum = 0;
        Set<Character> expected = new HashSet<>();
        for (char ch = ASCII_PRINT_START; ch < ASCII_PRINT_STOP; ch++) {
            currNum <<= Byte.SIZE;
            currNum += ch;
            nums[ch - ASCII_PRINT_START] = currNum;
            expected.add(ch);
        }
        int size = expected.size();
        Set<Character> actual = new HashSet<>(size);
        int numberOfCalls = 32 * size;
        MockProvider provider = new MockProvider(nums);
        CallTrackingPseudorandomness instance 
                = new CallTrackingPseudorandomness(provider);
        for (int i = 0; i < numberOfCalls; i++) {
            actual.add(instance.nextASCIIChar());
        }
        assertEquals(expected, actual);
        assert instance.nextIntCallsSoFar > 0 
                : "nextInt() should've been called at least once";
    }
    
    @Test
    public void testNextASCIICharSeqRejectsNegativeLength() {
        int floor = -256;
        int badLen = ((int) System.currentTimeMillis()) | floor;
        MockProvider provider = new MockProvider(makeIntArray(Pseudorandomness
                .REFRESH_INTERVAL));
        Pseudorandomness instance = new Pseudorandomness(provider);
        String msg = "Bad length " + badLen 
                + " for nextASCIICharSeq should cause exception";
        Throwable t = assertThrows(() -> {
            String badResult = instance.nextASCIICharSeq(badLen);
            System.out.println(msg + ", not give bad result \"" + badResult 
                    + "\"");
        }, NegativeArraySizeException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }
    
    @Test
    public void testNextASCIICharSeqLengthZero() {
        MockProvider provider = new MockProvider(makeIntArray(Pseudorandomness
                .REFRESH_INTERVAL));
        Pseudorandomness instance = new Pseudorandomness(provider);
        assertEquals("", instance.nextASCIICharSeq(0), 
                "Length 0 should give empty String");
    }
    
    private static void assertAllPrintingASCIICharacters(String s) {
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            String msg = "Character '" + ch + "' in \"" + s 
                    + "\" should be a printing character";
            assert ch >= ASCII_PRINT_START : msg;
            assert ch < ASCII_PRINT_STOP : msg;
        }
    }
    
    @Test
    public void testNextASCIICharSeq() {
        System.out.println("nextASCIICharSeq");
        int lengthThreshold = 20;
        int size = lengthThreshold * lengthThreshold;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = i;
        }
        MockProvider provider = new MockProvider(nums);
        CallTrackingPseudorandomness instance 
                = new CallTrackingPseudorandomness(provider);
        int expected = 0;
        for (int j = 1; j < lengthThreshold; j++) {
            String s = instance.nextASCIICharSeq(j);
            String lenMsg = "\"" + s + "\" should be of length " + j;
            assertEquals(j, s.length(), lenMsg);
            assertAllPrintingASCIICharacters(s);
            expected += j;
        }
        int actual = instance.nextASCIICharCallsSoFar;
        String msg = "nextASCIIChar() should've been called " + expected 
                + " times";
        assertEquals(expected, actual, msg);
    }
    
    @Test
    public void testNextASCIICharSeqRejectsNegativeMinLength() {
        MockProvider provider = new MockProvider(makeIntArray(Pseudorandomness
                .REFRESH_INTERVAL));
        Pseudorandomness instance = new Pseudorandomness(provider);
        int badMinLength = -LOCAL_RANDOM.nextInt(1024) - 1;
        int maxLength = -badMinLength + 1;
        String msg = "Bad minimum " + badMinLength + " and maximum " 
                + maxLength + " should cause an exception";
        Throwable t = assertThrows(() -> {
            String badResult = instance.nextASCIICharSeq(badMinLength, 
                    maxLength);
            System.out.println(msg + ", not give result \"" + badResult 
                    + "\"");
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }
    
    @Test
    public void testNextASCIICharSeqRejectsNegativeMaxLength() {
        MockProvider provider = new MockProvider(makeIntArray(Pseudorandomness
                .REFRESH_INTERVAL));
        Pseudorandomness instance = new Pseudorandomness(provider);
        int badMaxLength = -LOCAL_RANDOM.nextInt(1024) - 1;
        int minLength = -badMaxLength - 1;
        String msg = "Bad maximum " + badMaxLength + " and minimum " 
                + minLength + " should cause an exception";
        Throwable t = assertThrows(() -> {
            String badResult = instance.nextASCIICharSeq(minLength, 
                    badMaxLength);
            System.out.println(msg + ", not give result \"" + badResult 
                    + "\"");
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }
    
    @Test
    public void testNextASCIICharSeqRejectsBadMinMaxCombination() {
        MockProvider provider = new MockProvider(makeIntArray(Pseudorandomness
                .REFRESH_INTERVAL));
        Pseudorandomness instance = new Pseudorandomness(provider);
        int minLength = LOCAL_RANDOM.nextInt(1024) + 1;
        int badMaxLength = minLength - 1;
        String msg = "Minimum " + minLength + " and bad maximum " 
                + badMaxLength + " should cause an exception";
        Throwable t = assertThrows(() -> {
            String badResult = instance.nextASCIICharSeq(minLength, 
                    badMaxLength);
            System.out.println(msg + ", not give result \"" + badResult 
                    + "\"");
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }

    @Test
    public void testNextASCIICharSeqMinAndMaxLen() {
        int minLength = LOCAL_RANDOM.nextInt(8) + 2;
        int maxLength = minLength + LOCAL_RANDOM.nextInt(8) + 2;
        int range = maxLength - minLength;
        int numberOfCalls = 2 * range * range + range;
        int[] nums = new int[numberOfCalls];
        for (int i = 0; i < numberOfCalls; i++) {
            nums[i] = i;
        }
        MockProvider provider = new MockProvider(nums);
        Pseudorandomness instance = new Pseudorandomness(provider);
        Set<String> strings = new HashSet<String>(numberOfCalls);
        Set<Integer> lengths = new HashSet<Integer>(range);
        String msgPart = "\" should have at least " + minLength 
                + " characters and at most " + maxLength + " characters";
        for (int i = 0; i < numberOfCalls; i++) {
            String s = instance.nextASCIICharSeq(minLength, maxLength);
            assertAllPrintingASCIICharacters(s);
            System.out.println("\"" + s + "\"");
            int actual = s.length();
            String msg = "String \"" + s + msgPart;
            assertInRange(minLength, actual, maxLength, msg);
            strings.add(s);
            lengths.add(actual);
        }
        int minimum = 3 * numberOfCalls / 5;
        int actual = strings.size();
        String msg = "Set should have at least " + minimum 
                + " distinct ASCII character sequences";
        assertMinimum(minimum, actual, msg);
        int expected = range + 1;
        String lenMsg = "Expected " + expected + " different lengths";
        assertEquals(expected, lengths.size(), lenMsg); 
    }
    
    private static class MockProvider extends ExternalRandomnessProvider {
        
        private int[] numbers;
        
        @Override
        public int[] giveNumbers(int amount) {
            int[] array = new int[amount];
            int len = Math.min(amount, this.numbers.length);
            for (int i = 0; i < len; i++) {
                array[i] = this.numbers[i];
            }
            return array;
        }

        @Override
        public int[] giveNumbers(int amount, int minimum, int maximum) 
                throws IOException {
            int[] array = {};
            return array;
        }

        @Override
        public boolean haveNotExceededQuota() throws IOException {
            return false;
        }
        
        public MockProvider(int[] nums) {
            this.numbers = nums;
        }
        
    }
    
    private static class CallTrackingPseudorandomness extends Pseudorandomness {
        
        private static final long serialVersionUID = 1L;
        
        int nextASCIICharCallsSoFar = 0;
        
        int nextIntCallsSoFar = 0;

        @Override
        public char nextASCIIChar() {
            this.nextASCIICharCallsSoFar++;
            return super.nextASCIIChar();
        }
        
        @Override
        public int nextInt() {
            this.nextIntCallsSoFar++;
            return super.nextInt();
        }
        
        public CallTrackingPseudorandomness(MockProvider provider) {
            super(provider);
        }
        
    }
    
}
