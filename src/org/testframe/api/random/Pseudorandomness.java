package org.testframe.api.random;

import java.io.IOException;
import java.util.Random;

/**
 * Provides numbers and other data that can be relied on to be at least 
 * pseudorandom if not truly random. The idea is that instances of this class 
 * will try to connect to an external source like Random.org, which is said to 
 * provide truly random numbers. If there's any problem making the connection or 
 * interpreting the response, a local pseudorandom number generator will be used 
 * as a fallback.
 * @author Alonso del Arte
 */
class Pseudorandomness extends Random {

    private static final long serialVersionUID = 4553879963396210688L;
    
    static final int REFRESH_INTERVAL = 100;
    
    private final ExternalRandomnessProvider randomProvider;
    
    private int[] integers = {};
    
    int[] connectToExternalRandomnessProvider() throws IOException {
        int[] numbers = {};
        return numbers;
    }
    
    // TODO: Write tests for this
    @Override
    public boolean nextBoolean() {
        return false;
    }
    
    public Pseudorandomness(ExternalRandomnessProvider provider) {
        this.randomProvider = provider;
    }

}
