package org.testframe.api.random;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    
    private static final int NUMBER_OF_INT_BITS = Integer.BYTES * 8;
    
    static final int REFRESH_INTERVAL = 100;
    
    private final ExternalRandomnessProvider randomProvider;
    
    private int[] integers = {};
    
    private int index = 0;
    
    private int boolsBitSource;
    
    private int boolBitsUsed = 0;
    
    // TODO: Write tests for this
    @Override
    public int nextPowerOfTwo() {
        return -1;
    }
    
    @Override
    public boolean nextBoolean() {
        int bit = this.boolsBitSource & 1;
        this.boolBitsUsed++;
        if (this.boolBitsUsed == NUMBER_OF_INT_BITS) {
            this.index++;
            this.boolsBitSource = this.integers[this.index];
            this.boolBitsUsed = 0;
        } else {
            this.boolsBitSource >>= 1;
        }
        return bit == 1;
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

    // TODO: Write tests for this
    @Override
    public <E> E nextObject(E[] array) {
        return null;
    }

    // TODO: Write tests for this
    @Override
    public <E> E nextObject(List<E> list) {
        return null;
    }

    // TODO: Write tests for this
    @Override
    public <E> E nextObject(Set<E> set) {
        return null;
    }

    public Pseudorandomness(ExternalRandomnessProvider provider) {
        this.randomProvider = provider;
        try {
            this.integers = this.randomProvider.giveNumbers(3);
            this.boolsBitSource = this.integers[0];
        } catch (IOException ioe) {
            RuntimeException re = new RuntimeException(ioe);
            throw re;
        }
    }

}
