package org.testframe.api.random;

import static org.testframe.api.Asserters.*;

import java.io.IOException;

import org.testframe.api.Test;
import org.testframe.api.random.ExternalRandomnessProvider;
import org.testframe.api.random.Pseudorandomness;

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
        MockProvider provider = new MockProvider(nums);
        Pseudorandomness instance = new Pseudorandomness(provider);
        this.bitSource = numberA;
        String msgPart = "Given position from right ";
        String msgAPart = " in " + numberA + ", expected ";
        for (int i = 0; i < NUMBER_OF_BITS; i++) {
            boolean expected = this.getNextBooleanOffBitSource();
            boolean actual = instance.nextBoolean();
            String msg = msgPart + i + msgAPart + expected;
            assert expected == actual : msg;
        }
        this.bitSource = numberB;
        String msgBPart = " in " + numberB + ", expected ";
        for (int j = 0; j < NUMBER_OF_BITS; j++) {
            boolean expected = this.getNextBooleanOffBitSource();
            boolean actual = instance.nextBoolean();
            String msg = msgPart + j + msgBPart + expected;
            assert expected == actual : msg;
        }
    }
    
    private static class MockProvider extends ExternalRandomnessProvider {
        
        private int[] numbers;

        @Override
        public int[] giveNumbers(int amount) throws IOException {
            int[] array = new int[amount];
            for (int i = 0; i < amount; i++) {
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
    
}
