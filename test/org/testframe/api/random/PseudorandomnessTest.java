package org.testframe.api.random;

import static org.testframe.api.Asserters.*;

import java.io.IOException;

import org.testframe.api.Test;
import org.testframe.api.random.ExternalRandomnessProvider;
import org.testframe.api.random.Pseudorandomness;

public class PseudorandomnessTest {
    
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
