package org.example.exercises.arithmetic;

import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class FractionTest {
    
    private static final Random RANDOM = new Random();
    
    @Test
    public void testToString() {
        System.out.println("toString");
        int numer = RANDOM.nextInt(4096) + 4;
        int denom = numer + 1;
        Fraction fraction = new Fraction(numer, denom);
        String expected = numer + "/" + denom;
        String actual = fraction.toString().replace(" ", "");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testConstructorRejectsDenominatorZero() {
        int numer = RANDOM.nextInt(1024) + 16;
        String msg = "Denominator 0 for numerator " + numer 
                + " should cause ArithmeticException";
        RuntimeException re = assertThrows(() -> {
            Fraction badFraction = new Fraction(numer, 0);
            System.out.println("Should not have been able to create " 
                    + badFraction.toString());
        }, ArithmeticException.class, msg);
        String excMsg = re.getMessage();
        assert excMsg != null : "Message should not be null";
    }

    @Test
    public void testConstructorRejectsDenominatorLongMin() {
        int numer = RANDOM.nextInt(1024) + 16;
        String msg = "Denominator " + Long.MIN_VALUE + " for numerator " + numer 
                + " should cause ArithmeticException";
        RuntimeException re = assertThrows(() -> {
            Fraction badFraction = new Fraction(numer, Long.MIN_VALUE);
            System.out.println("Should not have been able to create " 
                    + badFraction.toString());
        }, ArithmeticException.class, msg);
        String excMsg = re.getMessage();
        assert excMsg != null : "Message should not be null";
    }

}
