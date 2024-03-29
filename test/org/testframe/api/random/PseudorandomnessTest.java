package org.testframe.api.random;

import static org.testframe.api.Asserters.*;

import java.io.IOException;

import org.testframe.api.Test;
import org.testframe.api.random.ExternalRandomnessProvider;
import org.testframe.api.random.Pseudorandomness;

public class PseudorandomnessTest {
    
    private static class MockProvider extends ExternalRandomnessProvider {

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
        
    }
    
    private static class OfflinePseudorandomness extends Pseudorandomness {
        
        @Override
        int[] connectToExternalRandomnessProvider() throws IOException {
            String excMsg = "Simulating the Internet connection is unavailable";
            throw new IOException(excMsg);
        }
        
        OfflinePseudorandomness(ExternalRandomnessProvider provider) {
            super(provider);
        }

    }

}
