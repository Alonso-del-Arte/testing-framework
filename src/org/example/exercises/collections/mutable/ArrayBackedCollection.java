package org.example.exercises.collections.mutable;

import java.util.Iterator;

abstract class ArrayBackedCollection<E> implements Iterable<E> {

    public static final int DEFAULT_INITIAL_CAPACITY = 16;

    Object[] elements;

    int nextUp = 0;

    final void expandCapacity() {
        int largerSize = 3 * this.elements.length / 2;
        Object[] largerArray = new Object[largerSize];
        System.arraycopy(this.elements, 0, largerArray, 0,
                this.elements.length);
        this.elements = largerArray;
    }

    // TODO: Write tests for this
    public boolean add(E element) {
        return false;
    }

    // TODO: Write tests for this
    public boolean contains(E element) {
        return false;
    }

    // TODO: Write tests for this
    public boolean remove(E element) {
        return false;
    }

    // TODO: Write tests for this
    public int size() {
        return Integer.MIN_VALUE;
    }

    // TODO: Write tests for this
    public boolean isEmpty() {
        return false;
    }

    // TODO: Write tests for this
    public void clear() {
        //
    }

    // TODO: Write tests for this
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            // TODO: Write tests for this
            @Override
            public boolean hasNext() {
                return false;
            }

            // TODO: Write tests for this
            @Override
            public E next() {
                return null;
            }

        };
    }

    // TODO: Write tests for this
    public ArrayBackedCollection(ArrayBackedCollection<? 
                                 extends E> collection) {
        this(20);
    }

    // TODO: Write tests for this
    public ArrayBackedCollection(int initialCapacity) {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

}
