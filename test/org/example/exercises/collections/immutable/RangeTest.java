package org.example.exercises.collections.immutable;

import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class RangeTest {
    
    private static final Random RANDOM = new Random();
    
    @Test
    public void testToString() {
        System.out.println("toString");
        int start = 2 * RANDOM.nextInt(256) + 1;
        int end = start + 2 * RANDOM.nextInt(256) + 2;
        int step = 2;
        Range range = new Range(start, end, step);
        String expected = start + " to " + end + " by " + step;
        String actual = range.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringOmitsStepOne() {
        int start = RANDOM.nextInt(1024) + 1;
        int end = start + RANDOM.nextInt(1024) + 1;
        Range range = new Range(start, end, 1);
        String expected = start + " to " + end;
        String actual = range.toString();
        assertEquals(expected, actual);
    }

}
