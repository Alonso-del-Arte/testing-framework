package org.example.exercises.arithmetic;

public class Fraction {
	
	private final long numerator, denominator;
	
	// TODO: Write tests for this
	public long getNumerator() {
		return Long.MAX_VALUE;
	}
	
	// TODO: Write tests for this
	public long getDenominator() {
		return Long.MIN_VALUE;
	}
	
	// TODO: Write tests for this
	public Fraction plus(Fraction addend) {
		return this;
	}
	
	// TODO: Write tests for this
	public Fraction negate() {
		return this;
	}
	
	// TODO: Write tests for this
	public Fraction minus(Fraction subtrahend) {
		return this;
	}
	
	// TODO: Write tests for this
	public Fraction times(Fraction multiplicand) {
		return this;
	}
	
	// TODO: Write tests for this, including reciprocal of zero
	public Fraction reciprocal() {
		return this;
	}
	
	// TODO: Write tests for this, including division by zero
	public Fraction divides() {
		return this;
	}
	
	// TODO: Write tests for this
	@Override
	public String toString() {
		return this.numerator + " \\ " + this.denominator;
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
	
	public Fraction(long numer, long denom) {
		this.numerator = numer;
		this.denominator = denom;
	}

}
