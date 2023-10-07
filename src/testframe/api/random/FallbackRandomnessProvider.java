package testframe.api.random;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

public class FallbackRandomnessProvider extends ExternalRandomnessProvider {
    
    final ExternalRandomnessProvider primaryProvider;
    
    final Logger errorLogger;
    
    final Random fallback = new Random(System.currentTimeMillis());

    // TODO: Write tests for this
    @Override
    public int[] giveNumbers(int amount, int minimum, int maximum) {
        try {
            return this.primaryProvider.giveNumbers(amount, minimum, maximum);
        } catch (IOException ioe) {
            int[] numbers = new int[amount];
            return numbers;
        }
    }

    /**
     * Tells whether or not the quota has been exceeded.
     * @return False always. Even if the primary's quota has been exceeded, the
     * quota for the fallback should never be exceeded.
     */
    @Override
    public boolean haveNotExceededQuota() {
        return false;
    }
    
    // TODO: Write tests for this
    public FallbackRandomnessProvider(ExternalRandomnessProvider external, 
            Logger logger) {
        this.primaryProvider = external;
        this.errorLogger = logger;
    }

}
