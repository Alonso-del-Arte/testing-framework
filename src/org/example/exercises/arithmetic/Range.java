package org.example.exercises.arithmetic;

import java.util.Iterator;

// This is meant to represent a range of integers
// The model is scala.collection.immutable.Range
// https://www.scala-lang.org/api/2.12.9/scala/collection/immutable/Range.html
public class Range implements Iterable<Integer> {
    
    private final int begin, finish, interval;
    
    // TODO: Write tests for this
    // It should be essentially a get by index
    public int apply(int index) {
        return 0;
    }
    
    // TODO: Write tests for this
    public int length() {
        return -1;
    }
    
    // TODO: Write tests for this
    public int min() {
        return Integer.MAX_VALUE;
    }
    
    // TODO: Write tests for this
    public int max() {
        return Integer.MIN_VALUE;
    }
    
    // TODO: Write tests for this
    public Range by(int step) {
        return this;
    }
    
    // TODO: Write tests for this
    public boolean contains(int number) {
        return false;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }
    
    @Override
    public String toString() {
        return this.begin + " to " + this.finish + " by " + this.interval;
    }
    
    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return false;
    }
    
    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return 0;
    }
    
    // TODO: Write tests for this chained constructor
    // It should infer a step of +/-1.
    public Range(int start, int end) {
        this(start, end, 0);
    }
    
    // TODO: Write tests for this constructor
    public Range(int start, int end, int step) {
        this.begin = start;
        this.finish = end;
        this.interval = step;
    }
    
    private class RangeIterator implements Iterator<Integer> {
        
        // TODO: Write tests for this
        @Override
        public boolean hasNext() {
            return false;
        }
        
        // TODO: Write tests for this
        @Override
        public Integer next() {
            return Integer.MIN_VALUE;
        }
        
    }
    
}
