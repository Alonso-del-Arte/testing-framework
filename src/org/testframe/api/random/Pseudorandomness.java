package org.testframe.api.random;

import java.io.IOException;
import java.time.OffsetDateTime;
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
class Pseudorandomness extends ExpandedRandom {

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
    
    // TODO: Write tests for this
    @Override
    public CoinSide flipCoin() {
        return CoinSide.HEADS;
    }

    // TODO: Write tests for this
    @Override
    public char nextASCIIChar() {
        return '?';
    }

    // TODO: Write tests for this
    @Override
    public String nextASCIICharSeq(int length) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String nextASCIICharSeq(int minLength, int maxLength) {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String nextString() {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public OffsetDateTime nextDateTime() {
        return OffsetDateTime.now();
    }

    public Pseudorandomness(ExternalRandomnessProvider provider) {
        this.randomProvider = provider;
    }

}
