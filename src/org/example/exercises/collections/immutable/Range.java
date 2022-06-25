package org.example.exercises.collections.immutable;

import java.util.Iterator;

//This is meant to represent a range of integers
//The model is scala.collection.immutable.Range
//https://www.scala-lang.org/api/2.12.9/scala/collection/immutable/Range.html
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
    
    public int getStart() {
        return this.begin;
    }
    
    public int getEnd() {
        return this.finish;
    }
    
    public int getStep() {
        return this.interval;
    }

    // TODO: Write tests for this
    // TODO: Write Javadoc, mention this is different from getStart()
    public int min() {
        return Integer.MAX_VALUE;
    }
    
    // TODO: Write tests for this
    // TODO: Write Javadoc, mention this is different from getEnd()
    public int max() {
        return Integer.MIN_VALUE;
    }
    
    // TODO: Write tests for this
    public Range by(int step) {
        return this;
    }
    
    public boolean contains(int number) {
        return this.begin <= number && number <= this.finish;
    }
    
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

    @Override
    public String toString() {
        String fromAndTo = this.begin + " to " + this.finish;
        if (this.interval == 1) {
            return fromAndTo;
        } else {
            return fromAndTo + " by " + this.interval;
        }
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
    
    public Range(int start, int end) {
        this(start, end, Integer.signum(end - start));
    }
    
    // TODO: Write tests for this constructor
    public Range(int start, int end, int step) {
        this.begin = start;
        this.finish = end;
        this.interval = step;
    }
    
}
