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

    public static String nextASCIICharSeq(int length) {
        char[] characters = new char[length];
        for (int i = 0; i < length; i++) {
            characters[i] = 'a';
        }
        return new String(characters);
    }

    // TODO: Write tests for this
    public static String nextASCIICharSeq(int minLength, int maxLength) {
        return "NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public static int nextInt() {
        return 0;
    }

    // TODO: Write tests for this
    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    // TODO: Write tests for this
    public static int nextPowerOfTwo() {
        return -3;
    }
    
    // TODO: Write tests for this
    public static <E> E nextObject(E[] array) {
        return null;
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
