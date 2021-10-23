package org.example.exercises.arithmetic;

// This is meant to represent a range of integers
// The model is scala.collection.immutable.Range
// https://www.scala-lang.org/api/2.12.9/scala/collection/immutable/Range.html
public class Range {
	
	private final int begin, finish, interval;
	
	// TODO: Write tests for this constructor
	public Range(int start, int end) {
		this(start, end, 0);
	}
	
	// TODO: Write tests for this constructor
	public Range(int start, int end, int step) {
		this.begin = start;
		this.finish = end;
		this.interval = step;
	}
	
}
