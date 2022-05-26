package org.example.exercises.collections.mutable;

import java.util.Iterator;

public class ArrayBackedList<E> implements Iterable<E> {
    
    private static final int DEFAULT_STARTING_CAPACITY = 16;

    private Object[] elements;
    
    private int nextUp = 0;

    public boolean add(E element) {
        this.elements[this.nextUp] = element;
        this.nextUp++;
        return true;
    }

    // TODO: Write test for this
    public void add(E element, int index) {
        this.add(element);
    }

    public E get(int index) {
        if (index < 0 || index >= this.nextUp) {
            String excMsg = "Index " + index 
                    + " is not valid, should not be negative nor more than " 
                    + (this.nextUp - 1);
            throw new IndexOutOfBoundsException(excMsg);
        }
        @SuppressWarnings("unchecked")
        E element = (E) this.elements[index];
        return element;
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
        this.elements = new Object[initialCapacity];
    }

}
