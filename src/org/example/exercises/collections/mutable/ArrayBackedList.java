package org.example.exercises.collections.mutable;

import java.util.Iterator;

public class ArrayBackedList<E> implements Iterable<E> {
    
    private static final int DEFAULT_STARTING_CAPACITY = 16;

    private Object[] elements;

    public boolean add(E element) {
        return true;
    }

    // TODO: Write test for this
    public void add(E element, int index) {
        this.add(element);
    }

    public E get(int index) {
        if (index < 0) {
            String excMsg = "Index " + index 
                    + " is not valid, should be 0 or positive";
            throw new IndexOutOfBoundsException(excMsg);
        }
        return null;
    }

    // TODO: Write test for this
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write test for this
    public boolean remove(E element) {
        return false;
    }

    // TODO: Write test for this
    public E remove(int index) {
        return null;
    }

    // TODO: Write test for this
    public int size() {
        return -1;
    }

    // TODO: Write test for this
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write test for this
    public void clear() {
        //
    }
    
    // TODO: Write test for this
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            
            @Override
            public boolean hasNext() {
                return false;
            }
            
            @Override
            public E next() {
                return null;
            }
            
        };
    }

    public ArrayBackedList() {
        this(DEFAULT_STARTING_CAPACITY);
    }
    
    public ArrayBackedList(int initialCapacity) {
        if (initialCapacity < 0) {
            String excMsg = "Capacity " + initialCapacity 
                    + " not valid, should be positive";
            throw new IllegalArgumentException(excMsg);
        }
    }

}
