package testframe.api.random;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import testframe.api.BeforeAllTests;
import testframe.api.Test;
import static testframe.api.Asserters.*;

/**
 * Tests of the FallbackRandomnessProvider class.
 * @author Alonso del Arte
 */
public class FallbackRandomnessProviderTest {
    
    @Test
    public void test() {
        fail("HAVEN'T WRITTEN TESTS YET");
    }
    
    private class MockRandomnessProvider extends ExternalRandomnessProvider {
        
        private boolean active = true;
        
        int callCount = 0;
        
        void activate() {
            this.active = true;
        }
        
        void deactivate() {
            this.active = false;
        }

        @Override
        public int[] giveNumbers(int amount, int minimum, int maximum) 
                throws IOException {
            this.callCount++;
            if (this.active) {
                int diff = maximum - minimum;
                int[] numbers = new int[amount];
                for (int i = 0; i < amount; i++) {
                    numbers[i] = (i % diff) + minimum;
                }
                return numbers;
            } else {
                String excMsg = "Mock external provider has been deactivated";
                throw new IOException(excMsg);
            }
        }

        @Override
        public boolean haveNotExceededQuota() {
            return false;
        }
        
    }

}
