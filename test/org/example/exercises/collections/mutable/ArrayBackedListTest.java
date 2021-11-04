package org.example.exercises.collections.mutable;

import java.sql.DataTruncation;
import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class ArrayBackedListTest {
    
    private static final Random RANDOM = new Random();
    
    @Test
    public void testConstructorRejectsNegativeCapacity() {
        int badCapacity = -RANDOM.nextInt(128) - 1;
        String msg = "Capacity " + badCapacity 
                + " should cause IllegalArgumentException";
        Throwable t = assertThrows(() -> {
            ArrayBackedList<DataTruncation> list 
                    = new ArrayBackedList<>(badCapacity);
            System.out.println("Should not have been able to create " 
                    + list.toString() + " with initial capacity " 
                    + badCapacity);
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}
