package org.testframe.api.random;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

// TODO: Write static wrappers for Pseudorandomness's functions
public class Pseudorandom {
    
    private static final char PRINTABLE_ASCII_SPAN_BEGIN = ' ';

    private static final char PRINTABLE_ASCII_SPAN_END = '~';

    private static final int PRINTABLE_ASCII_SPAN = PRINTABLE_ASCII_SPAN_END
            - PRINTABLE_ASCII_SPAN_BEGIN + 1;

    // TODO: Change back to Pseudorandomness, or to FallbackRandomnessProvider
    private static final Random RANDOM = new Random();
//            = new Pseudorandomness(new RandomDotOrgAccess());
    
    /**
     * Tosses a coin, metaphorically.
     * @return Either {@link CoinSide#HEADS} or {@link CoinSide#TAILS}.
     */
    public static CoinSide flipCoin() {
        if (RANDOM.nextBoolean()) {
            return CoinSide.HEADS; 
        } else {
            return CoinSide.TAILS;
        }
    }
    
    public static char nextASCIIChar() {
        return (char) (' ' + RANDOM.nextInt(PRINTABLE_ASCII_SPAN));
    }

    /**
     * Comes up with a <code>String</code> instance consisting of ASCII printing 
     * characters. This means the <code>String</code> won't contain any ASCII 
     * control characters like Data Link Escape (U+0010) or Delete (U+007F).
     * @param length How long to make the <code>String</code>. For example, 12.
     * @return A <code>String</code> instance of ASCII printing characters. Note 
     * that the straight single and double quote can occur in the results. 
     * Examples for <code>length</code> being 12: "]Vv36l"o'eVA", 
     * "KYY>xld?J[Zw", "aWG[Q B:_3aY" and "gk|0++euub&amp;v".
     * @throws NegativeArraySizeException If <code>length</code> is negative.
     */
    public static String nextASCIICharSeq(int length) {
        char[] characters = new char[length];
        for (int i = 0; i < length; i++) {
            characters[i] = (char) (32 + RANDOM.nextInt(95));
        }
        return new String(characters);
    }

    // TODO: Write tests for this
    public static String nextASCIICharSeq(int minLength, int maxLength) {
        return "NOT IMPLEMENTED YET?";
    }

    // TODO: Write tests for this
    public static int nextInt() {
        return 0;
    }

    /**
     * Gives a pseudorandomly chosen integer up to a given bound.
     * @param bound The bound to go up to. For example, 100.
     * @return An integer equal to at least 0 but not equal to 
     * <code>bound</code> but not greater than that. For example, 22.
     */
    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * Gives a pseudorandomly chosen power of two. The number is always 
     * positive, it's never <code>Integer.MIN_VALUE</code>.
     * @return A power of 2, at least 1, at most 1073741824, which is 
     * 2<sup>30</sup>. For example, 524288, which is 2<sup>19</sup>.
     */
    public static int nextPowerOfTwo() {
        int shift = RANDOM.nextInt(31);
        return 1 << shift;
    }
    
    /**
     * Chooses an element of an array pseudorandomly. With enough calls, every 
     * element of the array should be given at least once.
     * @param <E> The type of the elements in the array. For example, 
     * <code>java.time.DayOfWeek</code>.
     * @param array An array of objects of type <code>E</code>. For example, 
     * <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>, 
     * <code>THURSDAY</code>, <code>FRIDAY</code> and two nulls, corresponding 
     * to the <code>DayOfWeek.values()</code> array with <code>SUNDAY</code> and 
     * <code>SATURDAY</code> changed to null.
     * @return One element of the array, chosen pseudorandomly. For example, 
     * <code>WEDNESDAY</code>. May be null, since an array can contain nulls.
     * @throws NoSuchElementException If the array has no elements.
     */
    public static <E> E nextObject(E[] array) {
        int len = array.length;
        if (len == 0) {
            String excMsg = "Array should have at least one element";
            throw new NoSuchElementException(excMsg);
        }
        int index = RANDOM.nextInt(len);
        return array[index];
    }

    /**
     * Chooses an element of a list pseudorandomly. With enough calls, every 
     * element of the list should be given at least once.
     * @param <E> The type of the elements in the list. For example, 
     * <code>java.time.DayOfWeek</code>.
     * @param list A list of objects of type <code>E</code>. For example,  
     * <code>TUESDAY</code>, <code>SUNDAY</code>, <code>FRIDAY</code>, 
     * <code>WEDNESDAY</code>, <code>MONDAY</code>, <code>SATURDAY</code>, 
     * <code>THURSDAY</code>, <code>TUESDAY</code>, etc. (these are the days of 
     * the day of the week of February 29 in the leap years since the start of 
     * Unix time on through the year 2024).
     * @return One element of the list, chosen pseudorandomly. For example, 
     * <code>SATURDAY</code>. May be null, since a list can contain nulls.
     * @throws NoSuchElementException If the list has no elements.
     */
    public static <E> E nextObject(List<E> list) {
        int len = list.size();
        if (len == 0) {
            String excMsg = "List should have at least one element";
            throw new NoSuchElementException(excMsg);
        }
        int index = RANDOM.nextInt(list.size());
        return list.get(index);
    }

    /**
     * Chooses an element of a set pseudorandomly. With enough calls, every 
     * element of the set should be given at least once.
     * @param <E> The type of the elements in the list. For example, 
     * <code>java.lang.Integer</code>.
     * @param set A list of objects of type <code>E</code>. For example, {0, 1,
     * 4, 9, 16, 25, 6, 19, 21, 10, 24, 15} (these are the squares modulo 30).
     * @return One element of the set, chosen pseudorandomly. For example, 19. 
     * May be null, since a set in Java is allowed to have one null.
     * @throws NoSuchElementException If the set has no elements.
     */
    public static <E> E nextObject(Set<E> set) {
        List<E> list = new ArrayList<E>(set);
        return nextObject(list);
    }
    
    private Pseudorandom() {
    }
    
}
