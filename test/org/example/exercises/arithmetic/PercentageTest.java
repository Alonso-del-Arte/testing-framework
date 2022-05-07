package org.example.exercises.arithmetic;

import java.util.Random;

import static testframe.api.Asserters.*;
import testframe.api.Test;

public class PercentageTest {

    private static final Random RANDOM = new Random();
    
    @Test
    public void testToString() {
        System.out.println("toString");
        int n = RANDOM.nextInt(100);
        Percentage percentage = new Percentage(n);
        String expected = n + ".0%";
        String actual = percentage.toString();
        assertEquals(expected, actual);
    }
    
}
