package org.example.exercises.collections.mutable;

public class ArrayBackedSet<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] elements;

    // TODO: Write test for this
    public boolean add(E element) {
        return false;
    }

    // TODO: Write test for this
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write test for this
    public boolean remove(E element) {
        return true;
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

    public ArrayBackedSet() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayBackedSet(int initialCapacity) {
        if (initialCapacity < 0) {
            String excMsg = "Capacity " + initialCapacity 
                    + " not valid, should be positive";
            throw new IllegalArgumentException(excMsg);
        }
        this.elements = new Object[initialCapacity];
    }

}
