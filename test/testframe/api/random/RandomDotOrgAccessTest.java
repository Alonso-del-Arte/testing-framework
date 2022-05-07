package testframe.api.random;

import java.util.HashSet;
import java.util.Set;

import testframe.api.BeforeAllTests;
import testframe.api.Test;
import static testframe.api.Asserters.*;

import java.io.IOException;

/**
 * Tests of the RandomDotOrgAccess class. We take it on faith that 
 * RandomDotOrgAccess will connect to random.org for the numbers, but check that 
 * the numbers meet our requirements for distribution and range.
 */
public class RandomDotOrgAccessTest {
    
    private int[] retrievedNumbers = {};
    
    public int expectedLength = 1000;
    
    public int expectedMinimum = -2000;
    
    public int expectedMaximum = 2000;
    
    @BeforeAllTests
    public void setUpClass() throws IOException {
        ExternalRandomnessProvider provider = new RandomDotOrgAccess();
        this.retrievedNumbers = provider.giveNumbers(expectedLength, 
                expectedMinimum, expectedMaximum);
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
        for (Integer number : this.retrievedNumbers) {
            set.add(number);
        }
        int expected = 800;
        int actual = set.size();
        String msg = "Expected at least " + expected + " distinct numbers, got " 
                + actual;
        assert expected <= actual : msg;
    }

}
