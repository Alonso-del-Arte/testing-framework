package org.example.exercises.collections.mutable;

import java.sql.DataTruncation;
import java.util.Random;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class ArrayBackedListTest {
    
    private static final Random RANDOM = new Random();
    
    @Test
    public void testAdd() {
        System.out.println("add");
        ArrayBackedList<String> list = new ArrayBackedList<>();
        String msg = "Should be able to add this message to the list";
        boolean opResult = list.add(msg);
        assert opResult : msg;
    }
    
    @Test
    public void testGetRejectsNegativeIndex() {
        ArrayBackedList<DataTruncation> list 
                = new ArrayBackedList<DataTruncation>();
        int badIndex = -RANDOM.nextInt(128) - 1;
        String msg = "Should not be able to get element at index " + badIndex;
        Throwable t = assertThrows(() -> {
            DataTruncation element = list.get(badIndex);
            System.out.println("Should not have been able to get " 
                    + element.toString() + " from index " + badIndex);
        }, IndexOutOfBoundsException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }
    
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
