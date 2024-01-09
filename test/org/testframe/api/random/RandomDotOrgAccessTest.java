package org.testframe.api.random;

import static org.testframe.api.Asserters.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.testframe.api.BeforeAllTests;
import org.testframe.api.Test;
import org.testframe.api.random.ExternalRandomnessProvider;
import org.testframe.api.random.RandomDotOrgAccess;

/**
 * Tests of the RandomDotOrgAccess class. We take it on faith that 
 * RandomDotOrgAccess will connect to random.org for the numbers, but check that 
 * the numbers meet our requirements for distribution and range.
 * @author Alonso del Arte
 */
public class RandomDotOrgAccessTest {
    
    private int[] retrievedNumbers = {};
    
    public int expectedLength = 1000;
    
    public int expectedMinimum = -2000;
    
    public int expectedMaximum = 2000;
    
    @BeforeAllTests
    public void setUpClass() throws IOException {
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        this.retrievedNumbers = provider.giveNumbers(this.expectedLength, 
               this.expectedMinimum, this.expectedMaximum);
    }
    
    @Test
    public void testGiveNumbersRejectsNegativeAmount() {
        int badAmount = -1;
        String msg = "giveNumbers() should reject " + badAmount 
                + ", too low for amount";
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        Throwable t = assertThrows(() -> {
            provider.giveNumbers(badAmount, 0, 1);
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println(excMsg);
    }
    
    @Test
    public void testGiveNumbersRejectsExcessiveAmount() {
        int badAmount = 10001;
        String msg = "giveNumbers() should reject " + badAmount 
                + ", too high for amount";
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        Throwable t = assertThrows(() -> {
            provider.giveNumbers(badAmount, 0, 1);
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println(excMsg);
    }
    
    @Test
    public void testGiveNumbersRejectsBadMinimum() {
        int badMinimum = -1000000001;
        String msg = "giveNumbers() should reject " + badMinimum 
                + ", too low for minimum";
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        Throwable t = assertThrows(() -> {
            provider.giveNumbers(1, badMinimum, 0);
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println(excMsg);
    }
    
    @Test
    public void testGiveNumbersRejectsBadMaximum() {
        int badMaximum = 1000000001;
        String msg = "giveNumbers() should reject " + badMaximum 
                + ", too high for maximum";
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        Throwable t = assertThrows(() -> {
            provider.giveNumbers(1, 0, badMaximum);
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println(excMsg);
    }
    
    @Test
    public void testGiveNumbersRejectsMinimumHigherThanMaximum() {
        int badMinimum = 10;
        int badMaximum = badMinimum - 1;
        String msg = "giveNumbers() should reject maximum " + badMaximum 
                + " that is higher than minimum " + badMinimum;
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        Throwable t = assertThrows(() -> {
            provider.giveNumbers(1, badMinimum, badMaximum);
        }, IllegalArgumentException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println(excMsg);
    }
    
    @Test
    public void testLength() {
        int actualLength = this.retrievedNumbers.length;
        assertEquals(expectedLength, actualLength);
    }
    
    @Test
    public void testDiffLength() throws IOException {
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        int expected = 10;
        int actual = provider.giveNumbers(expected, 1, 10).length;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDistinctness() {
        Set<Integer> set = new HashSet<Integer>();
        for (int number : this.retrievedNumbers) {
            set.add(number);
        }
        int expected = 800;
        int actual = set.size();
        String msg = "Expected at least " + expected + " distinct numbers, got " 
                + actual;
        System.out.println(msg);
        assert expected <= actual : msg;
    }
    
    @Test
    public void testRange() {
        for (int number : this.retrievedNumbers) {
            String msg = number + " should be between " + this.expectedMinimum 
                    + " and " + this.expectedMaximum;
            assert number >= this.expectedMinimum 
                    && number <= this.expectedMaximum : msg;
        }
    }
    
    @Test
    public void testIntervals() {
        Set<Integer> intervals = new HashSet<Integer>();
        for (int i = 1; i < this.retrievedNumbers.length; i++) {
            int difference = this.retrievedNumbers[i] 
                    - this.retrievedNumbers[i - 1];
            intervals.add(difference);
        }
        int expected = 700;
        int actual = intervals.size();
        String msg = "Expected at least " + expected 
                + " distinct intervals, got " + actual;
        System.out.println(msg);
        assert expected <= actual : msg;
    }

}
