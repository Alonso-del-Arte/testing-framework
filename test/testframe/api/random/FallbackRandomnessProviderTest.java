package testframe.api.random;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import testframe.api.BeforeAllTests;
import testframe.api.Test;
import static testframe.api.Asserters.*;

/**
 * Tests of the FallbackRandomnessProvider class.
 * @author Alonso del Arte
 */
public class FallbackRandomnessProviderTest {
    
    @Test
    public void testFallbackTriesPrimaryFirst() {
        MockRandomnessProvider mock = new MockRandomnessProvider();
        mock.activate();
        Logger logger = Logger.getLogger("Tests");
        FallbackRandomnessProvider provider 
                = new FallbackRandomnessProvider(mock, logger);
        provider.giveNumbers(10, 0, 9);
        String msg = "Mock provider should've been called at least once";
        assert mock.callCount > 0 : msg;
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
            if (this.active) {
                this.callCount++;
                int diff = maximum - minimum;
                int[] numbers = new int[amount];
                for (int i = 0; i < amount; i++) {
                    numbers[i] = minimum + (i % diff);
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
