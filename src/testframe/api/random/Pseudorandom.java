package testframe.api.random;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

// TODO: Write static wrappers for Pseudorandomness's functions
public class Pseudorandom {
    
    private static final char PRINTABLE_ASCII_SPAN_BEGIN = ' ';

    private static final char PRINTABLE_ASCII_SPAN_END = '~';

    private static final int PRINTABLE_ASCII_SPAN = PRINTABLE_ASCII_SPAN_END
            - PRINTABLE_ASCII_SPAN_BEGIN + 1;

    private static final Random RANDOM  
            = new Pseudorandomness(new RandomDotOrgAccess());
    
    // TODO: Write tests for this
    public static CoinSide flipCoin() {
//        if (RANDOM.nextBoolean()) {
            return CoinSide.HEADS; 
//        } else {
//            return CoinSide.TAILS;
//        }
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
     * "KYY>xld?J[Zw", "aWG[Q B:_3aY" and "gk|0++euub&v".
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

    public static int nextPowerOfTwo() {
        int shift = RANDOM.nextInt(31);
        return 1 << shift;
    }
    
    /**
     * Chooses an element of an array pseudorandomly.
     * @param <E> The type of the elements in the array. For example, 
     * <code>java.time.DayOfWeek</code>.
     * @param array An array of objects of type <code>E</code>. For example, 
     * <code>MONDAY</code>, <code>TUESDAY</code>, <code>WEDNESDAY</code>, 
     * <code>THURSDAY</code>, <code>FRIDAY</code>.
     * @return One element of the array, chosen pseudorandomly. For example, 
     * <code>TUESDAY</code>.
     */
    public static <E> E nextObject(E[] array) {
        int index = RANDOM.nextInt(array.length);
        return array[index];
    }

    // TODO: Write tests for this
    public static <E> E nextObject(List<E> list) {
        return list.get(0);
    }

    // TODO: Write tests for this
    public static <E> E nextObject(Set<E> set) {
        Iterator<E> iterator = set.iterator();
        return iterator.next();
    }
    
}
