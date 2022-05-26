package org.example.exercises.collections.mutable;

import java.math.BigInteger;
import java.sql.DataTruncation;
import java.time.LocalDateTime;
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
    public void testGetRejectsExcessiveIndex() {
        int size = ArrayBackedCollection.DEFAULT_INITIAL_CAPACITY 
                + RANDOM.nextInt(32);
        ArrayBackedList<Integer> list = new ArrayBackedList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        int badIndex = size + RANDOM.nextInt(128) + 1;
        String msg = "Should not be able to get element at index " + badIndex 
                + " from list with only " + size + " elements";
        Throwable t = assertThrows(() -> {
            Integer element = list.get(badIndex);
            System.out.println("Should not have been able to get " 
                    + element.toString() + " from index " + badIndex);
        }, IndexOutOfBoundsException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }
    
    @Test
    public void testGet() {
        System.out.println("get");
        BigInteger start = new BigInteger(72, RANDOM);
        int size = 2 * ArrayBackedCollection.DEFAULT_INITIAL_CAPACITY + 1;
        ArrayBackedList<BigInteger> list 
                = new ArrayBackedList<BigInteger>(size);
        for (int i = 0; i < size; i++) {
            BigInteger number = start.add(BigInteger.valueOf(i));
            list.add(number);
        }
        for (int j = 0; j < size; j++) {
            BigInteger expected = start.add(BigInteger.valueOf(j));
            BigInteger actual = list.get(j);
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public void testAddCanExpandCapacity() {
        int size = RANDOM.nextInt(64) + 16;
        ArrayBackedList<LocalDateTime> list = new ArrayBackedList<>(size);
        LocalDateTime now = LocalDateTime.now();
        for (int i = size; i > 0; i--) {
            list.add(now.minusMinutes(i));
        }
        String msg = "List of " + size 
                + " elements should have expanded for one more element";
        assertDoesNotThrow(() -> {
            list.add(now);
        }, msg);
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
