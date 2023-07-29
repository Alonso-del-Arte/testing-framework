package testframe.engine;

import testframe.api.Skip;
import testframe.api.Test;

/**
 * Toy tests to check the "-sort" command line option for TestRunner. Three each 
 * are provided of tests that should pass, tests that should fail, tests that 
 * should cause an error and tests that should be skipped, but they're provided 
 * so as to be interleaved rather than in order.
 * @author Alonso del Arte
 */
public class YetMoreToyTests {

    @Test
    public void testThatShouldPassA() {
        String msg = "This test should be reported as passing (A)";
        System.out.println(msg);
    }
    
    @Test
    public void testThatShouldFailA() {
        String msg = "This test should be reported as failing (A)";
        System.out.println(msg);
        assert false : msg;
    }
    
    @Skip @Test
    public void testThatShouldBeSkippedA() {
        String msg = "This test should be skipped";
        System.out.println(msg);
        assert false : msg;
    }
    
    
    @Test
    public void testThatShouldCauseErrorA() {
        String msg 
                = "This test should be reported as having caused an error (A)";
        System.out.println(msg);
        throw new RuntimeException(msg);
    }
    
    @Test
    public void testThatShouldPassB() {
        String msg = "This test should be reported as passing (B)";
        System.out.println(msg);
    }
    
    @Test
    public void testThatShouldFailB() {
        String msg = "This test should be reported as failing (B)";
        System.out.println(msg);
        assert false : msg;
    }
    
    @Skip @Test
    public void testThatShouldBeSkippedB() {
        String msg = "This test should be skipped (B)";
        System.out.println(msg);
        assert false : msg;
    }
    
    
    @Test
    public void testThatShouldCauseErrorB() {
        String msg 
                = "This test should be reported as having caused an error (B)";
        System.out.println(msg);
        throw new RuntimeException(msg);
    }
    
    @Test
    public void testThatShouldPassC() {
        String msg = "This test should be reported as passing (C)";
        System.out.println(msg);
    }
    
    @Test
    public void testThatShouldFailC() {
        String msg = "This test should be reported as failing (C)";
        System.out.println(msg);
        assert false : msg;
    }
    
    @Skip @Test
    public void testThatShouldBeSkippedC() {
        String msg = "This test should be skipped (C)";
        System.out.println(msg);
        assert false : msg;
    }
    
    
    @Test
    public void testThatShouldCauseErrorC() {
        String msg 
                = "This test should be reported as having caused an error (C)";
        System.out.println(msg);
        throw new RuntimeException(msg);
    }
    
}
