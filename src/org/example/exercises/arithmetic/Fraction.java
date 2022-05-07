package org.example.exercises.arithmetic;

public class Fraction {
    
    // HINT: Use IntegerMath.euclideanGCD() to
    // put fractions in lowest terms
    
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
    public double getNumericApproximation() {
        return Double.NaN;
    }
    
    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
    
    // TODO: Write tests for this
    @Override
    public boolean equals(Object obj) {
        return false;
    }
    
    // TODO: Write tests for this
    @Override
    public int hashCode() {
        return (int) (this.numerator + this.denominator);
    }
    
    // TODO: Write tests for this
    public Fraction(long numer) {
        this(numer, -1);
    }
    
    public Fraction(long numer, long denom) {
        if ((denom & Long.MAX_VALUE) == 0) {
            String excMsg = "Denominator 0 is not valid";
            throw new ArithmeticException(excMsg);
        }
        this.numerator = numer;
        this.denominator = denom;
    }

}
