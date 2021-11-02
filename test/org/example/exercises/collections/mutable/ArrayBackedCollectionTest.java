package org.example.exercises.collections.mutable;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class ArrayBackedCollectionTest {

    private static class ArrayBackedCollectionImpl<E> 
            extends ArrayBackedCollection<E> {

        public ArrayBackedCollectionImpl(int initialCapacity) {
            super(initialCapacity);
            // TODO Auto-generated constructor stub
        }
        
    }
    
}
