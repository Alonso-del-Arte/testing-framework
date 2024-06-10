package org.testframe.api.random;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class ExpandedRandom extends Random {

    private static final long serialVersionUID = 4553887127401760416L;
    
    /**
     * Gives a pseudorandom power of two.
     * @return A power of two. One of 1, 2, 4, 8, 16, 32, 64, 128, ..., 
     * 1073741824.
     */
    public abstract int nextPowerOfTwo();
    
    /**
     * Flips a coin pseudorandomly.
     * @return Either {@link CoinSide#HEADS} or {@link CoinSide#TAILS}.
     */
    public abstract CoinSide flipCoin();
    
    /**
     * Pseudorandomly picks a printing ASCII character. The space is considered 
     * printing, the tab is not.
     * @return An ASCII character. For example, 'm'.
     */
    public abstract char nextASCIIChar();
    
    /**
     * Gives a pseudorandom sequence of printing ASCII characters of a specified 
     * length. 
     * @param length How long the sequence of ASCII characters should be. Should 
     * not be negative. May be 0, but there's not much point to that. For 
     * example, 7.
     * @return A pseudorandom <code>String</code> with <code>length</code> ASCII 
     * characters. For example, "t.lm8Q<". 
     * @throws NegativeArraySizeException If <code>length</code> is negative.
     */
    public abstract String nextASCIICharSeq(int length);
    
    /**
     * Gives a pseudorandom sequence of printing ASCII characters.
     * @param minLength The minimum length of the sequence. May be 0 but must 
     * not be negative. Must not be greater than <code>maxLength</code>. For 
     * example, 3.
     * @param maxLength The maximum length of the sequence. Must not be less 
     * than <code>minLength</code>. For example, 7.
     * @return A sequence of printing ASCII characters of at least 
     * <code>minLength</code> but not more than <code>maxLength</code>. For 
     * example, "_aZ6WW".
     * @throws IllegalArgumentException If either <code>minLength</code> or 
     * <code>maxLength</code> is negative, or if the former is greater than the 
     * latter.
     */
    public abstract String nextASCIICharSeq(int minLength, int maxLength);
    
    // TODO: Remember how this was supposed to differ from nextASCIICharSeq
    public abstract String nextString();
    
    public abstract OffsetDateTime nextDateTime();
    
    public abstract <E> E nextObject(E[] array);

    public abstract <E> E nextObject(List<E> list);

    public abstract <E> E nextObject(Set<E> set);

}
