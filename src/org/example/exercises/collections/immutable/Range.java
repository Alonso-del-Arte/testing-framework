package org.example.exercises.collections.immutable;

import java.util.Iterator;

public class Range implements Iterable<Integer> {
    
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            // TODO: Write tests for this
            @Override
            public boolean hasNext() {
                return false;
            }

            // TODO: Write tests for this
            @Override
            public Integer next() {
                return 0;
            }

        };
    }

}
