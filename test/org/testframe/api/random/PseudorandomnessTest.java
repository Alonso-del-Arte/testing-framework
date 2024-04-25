package org.testframe.api.random;

import java.io.IOException;
import java.util.Arrays;

import static org.testframe.api.Asserters.*;
import org.testframe.api.Test;

public class PseudorandomnessTest {
    
    private static final int NUMBER_OF_BITS = Integer.BYTES * 8;
    
    private int bitSource;
    
    private boolean getNextBooleanOffBitSource() {
        int leastSignificantBit = this.bitSource & 1;
        this.bitSource >>= 1;
        return leastSignificantBit == 1;
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
    
    private static class MockProvider extends ExternalRandomnessProvider {
        
        private int[] numbers;

        @Override
        public int[] giveNumbers(int amount) {
            int[] array = new int[amount];
            int providedLen = this.numbers.length;
            for (int i = 0; i < providedLen; i++) {
                array[i] = this.numbers[i];
            }
            for (int j = providedLen; j < amount; j++) {
                array[j] = 0;
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
    
}
