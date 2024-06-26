package org.testframe.api.random;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

/**
 * Provides numbers and other data that can be relied on to be at least 
 * pseudorandom if not truly random. The idea is that instances of this class 
 * will try to connect to an external source like Random.org, which is said to 
 * provide truly random numbers. If there's any problem making the connection or 
 * interpreting the response, a local pseudorandom number generator could be  
 * used as a fallback.
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
    
    private int asciiSource = 0;
    
    /**
     * Gives a pseudorandomly chosen power of two.
     * @return A pseudorandomly chosen power of two. For example, 1048576 = 
     * 2<sup>20</sup>. Smallest possible return value is 1, highest is 
     * 1073741824 = 2<sup>30</sup>. 
     */
    @Override
    public int nextPowerOfTwo() {
        int shift = this.nextInt(31);
        return 1 << shift;
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
    
    /**
     * Flips a coin, so to speak. This function is closely related to {@link 
     * #nextBoolean()}, though we don't guarantee that heads will map to either 
     * true or false, nor tails to the other.
     * @return Either heads or tails.
     */
    @Override
    public CoinSide flipCoin() {
        boolean choseHeads = this.nextBoolean();
        if (choseHeads) {
            return CoinSide.HEADS;
        } else {
            return CoinSide.TAILS;
        }
    }

    /**
     * Gives a pseudorandomly chosen printing ASCII character. Non-printing 
     * characters like end of transmission and data link escape are excluded 
     * from consideration. The horizontal and vertical tabs are also excluded, 
     * as well as carriage return and line feed.
     * @return A pseudorandomly chosen ASCII character. For example, 'a'. May 
     * also be the space ' '.
     */
    @Override
    public char nextASCIIChar() {
        if (this.asciiSource == 0) {
            this.asciiSource = this.nextInt();
        }
        int candidate;
        do {
            candidate = this.asciiSource & 127;
            this.asciiSource >>= 8;
        } while (candidate == 127);
        if (candidate < ' ') {
            candidate += ' ';
        }
        return (char) candidate;
    }

    /**
     * Gives a pseudorandom sequence of printing ASCII characters. 
     * @param length How long the sequence of ASCII characters should be. Should 
     * not be negative. May be 0, but there's not much point to that. For 
     * example, 7.
     * @return A pseudorandom <code>String</code> with <code>length</code> ASCII 
     * characters. For example, "z7_ arm". 
     * @throws NegativeArraySizeException If <code>length</code> is negative.
     */
    @Override
    public String nextASCIICharSeq(int length) {
        if (length < 0) {
            String excMsg = "Negative length " + length + " is not valid";
            throw new NegativeArraySizeException(excMsg);
        }
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = this.nextASCIIChar();
        }
        return new String(array);
    }

    // TODO: Write tests for this
    @Override
    public String nextASCIICharSeq(int minLength, int maxLength) {
        int diff = maxLength - minLength;
        if (minLength < 0 || diff < 0) {
            String excMsg = "Minimum length " + minLength 
                    + " and maximum length " + maxLength + " are not valid";
            throw new IllegalArgumentException(excMsg);
        }
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
    
    private void refresh() {
        try {
            this.integers = this.randomProvider.giveNumbers(REFRESH_INTERVAL);
            this.index = 0;
        } catch (IOException ioe) {
            RuntimeException re = new RuntimeException(ioe);
            throw re;
        }
    }
    
    @Override
    public int nextInt() {
        if (this.index == REFRESH_INTERVAL) {
            this.refresh();
        }
        return this.integers[this.index++];
    }

    // TODO: Write tests for this
    @Override
    public int nextInt(int bound) {
        int candidate = this.nextInt() % bound;
        if (candidate < 0) {
            candidate += bound;
        }
        return candidate;
    }

    public Pseudorandomness(ExternalRandomnessProvider provider) {
        this.randomProvider = provider;
        try {
            this.integers = this.randomProvider.giveNumbers(REFRESH_INTERVAL);
            this.boolsBitSource = this.integers[0];
        } catch (IOException ioe) {
            RuntimeException re = new RuntimeException(ioe);
            throw re;
        }
    }

}
