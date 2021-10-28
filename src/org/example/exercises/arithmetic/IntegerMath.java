package org.example.exercises.arithmetic;

/**
 * Like <code>java.lang.Math</code>, but focusing on integer arithmetic.
 * @author ???
 */
public class IntegerMath {
    
    // TODO: Decide on what gcd(0, 0) should be; test accordingly
    /**
     * Gives the greatest common divisor (GCD) of two 64-bit integers using the 
     * Euclidean algorithm. The algorithm is implemented recursively, but that 
     * should never cause a stack overflow even in the worst case of two 
     * consecutive Fibonacci numbers.
     * @param a One of two integers. Need not be greater or smaller than 
     * <code>b</code>. For example, &minus;49.
     * @param b Another integer, though it may be the same as <code>a</code>. 
     * For example, 42.
     * @return The GCD of <code>a</code> and <code>b</code>.
     */
    public static long euclideanGCD(long a, long b) {
        if (b == 0L) {
            return Math.abs(a);
        } else {
            return euclideanGCD(b, a % b);
        }
    }
    
    // TODO: Write tests for this
    public long[] primeFactors(long n) {
        long[] factors = {0};
        return factors;
    }
    
    // TODO: Write tests for this
    public byte moebiusMu(long n) {
        return Byte.MIN_VALUE;
    }
    
    // TODO: Write tests for this
    public long eulerPhi(long n) {
        return Long.MIN_VALUE;
    }

}
