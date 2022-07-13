package testframe.api.random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import testframe.api.Test;
import static testframe.api.Asserters.*;

/**
 * Tests of the Pseudorandom class.
 * @author Alonso del Arte
 */
public class PseudorandomTest {

    private static final char PRINTABLE_ASCII_SPAN_BEGIN = ' ';

    private static final char PRINTABLE_ASCII_SPAN_END = '~';

    private static final int PRINTABLE_ASCII_SPAN = PRINTABLE_ASCII_SPAN_END
            - PRINTABLE_ASCII_SPAN_BEGIN + 1;

    private static final long RANDOM_SEED
            = Double.doubleToLongBits(Math.random());

    private static final Random RANDOM = new Random(RANDOM_SEED);

    @Test
    public void testNextIntPositiveBound() {
        int bound = 100;
        int hypotheticalOccurAverage = 10;
        int maxOccurrencePerNumber = 2 * hypotheticalOccurAverage;
        int callTotal = hypotheticalOccurAverage * bound;
        int[] occurrences = new int[bound];
        for (int i = 0; i < callTotal; i++) {
            int n = Pseudorandom.nextInt(bound);
            String boundMsg = "Pseudorandom number " + n
                    + " should be at least 0 but not equal to nor more than "
                    + bound;
            assert 0 <= n && n < bound : boundMsg;
            int occurCount = ++occurrences[n];
            String occurMsg = "Pseudorandom number " + n + " occurs "
                    + occurCount + " times, should not occur more than "
                    + maxOccurrencePerNumber + " times in " + callTotal
                    + " calls";
            assert occurCount <= maxOccurrencePerNumber : occurMsg;
        }
    }

//    @Test
    public void testFlipCoin() {
        final int tries = 1000;
        final int minimumExpectation = 3 * tries / 10;
        final int maximumExpectation = 7 * tries / 10;
        int headCount = 0;
        int tailCount = 0;
        for (int i = 0; i < tries; i++) {
            CoinSide side = Pseudorandom.flipCoin();
            if (side.equals(CoinSide.HEADS)) {
                headCount++;
            } else {
                tailCount++;
            }
        }
        String msg = "Flipped heads " + headCount + " times, tails " + tailCount
                + " times, should be at least " + minimumExpectation
                + " times, at most " + maximumExpectation + " times";
        assert headCount >= minimumExpectation : msg;
        assert headCount <= maximumExpectation : msg;
        assert tailCount >= minimumExpectation : msg;
        assert tailCount <= maximumExpectation : msg;
    }

    @Test
    public void testNextASCIICharGivesASCIICharacter() {
        int span = 2 * PRINTABLE_ASCII_SPAN;
        for (int i = 0; i < span; i++) {
            char ch = Pseudorandom.nextASCIIChar();
            String msg = "Character '" + ch + "' should be at least '"
                    + PRINTABLE_ASCII_SPAN_BEGIN + "' but not more than '"
                    + PRINTABLE_ASCII_SPAN_END + "'";
            assert PRINTABLE_ASCII_SPAN_BEGIN <= ch
                    && ch <= PRINTABLE_ASCII_SPAN_END : msg;
        }
    }

//    @Test
//    public void testNextASCIIChar() {
//        System.out.println("nextASCIIChar");
//        Set<Character> characters = new HashSet<>(PRINTABLE_ASCII_SPAN);
//        for (int i = 0; i < PRINTABLE_ASCII_SPAN; i++) {
//            characters.add(Pseudorandom.nextASCIIChar());
//        }
//        int expected = 55 * PRINTABLE_ASCII_SPAN / 100;
//        int actual = characters.size();
//        String msg = "Calling nextASCIIChar() " + PRINTABLE_ASCII_SPAN
//                + " times gave " + actual
//                + " distinct character(s), ought to be at least " + expected
//                + " distinct";
//        assert expected <= actual : msg;
//    }
//
//    @Test
//    public void testNextASCIICharSeqOneParamGivesRightLength() {
//        for (int expected = 2; expected < 80; expected++) {
//            String s = Pseudorandom.nextASCIICharSeq(expected);
//            int actual = s.length();
//            String msg = "Pseudorandom String \"" + s
//                    + "\" should be of length " + expected;
//            assertEquals(expected, actual, msg);
//        }
//    }
//
//    @Test
//    public void testNextASCIICharSeq() {
//        System.out.println("nextASCIICharSeq");
//        fail("Haven't written test yet");
//    }

}
