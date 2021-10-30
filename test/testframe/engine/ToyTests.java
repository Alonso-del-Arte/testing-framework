package testframe.engine;

import testframe.api.AfterAllTests;
import testframe.api.AfterEachTest;
import testframe.api.BeforeAllTests;
import testframe.api.BeforeEachTest;
import testframe.api.Test;

public class ToyTests {
    
    private static boolean relay(boolean flag) {
        return flag;
    }
    
    @BeforeAllTests
    public void setUpClass() {
        // TODO: Figure out how to report that this was invoked
    }
    
    @BeforeEachTest
    public void setUp() {
        // TODO: Figure out how to report that this was invoked
    }
    
    private void recordTestIsRunning() {
        // TODO: Figure out how to report that this was invoked
    }
    
    @Test
    public void testThatShouldPass() {
        recordTestIsRunning();
        String msg = "This test should be reported as passing";
        System.out.println(msg);
        assert relay(true) : msg;
    }
    
    @Test
    public void testThatShouldFail() {
        recordTestIsRunning();
        String msg = "This test should be reported as failing";
        System.out.println(msg);
        assert relay(false) : msg;
    }
    
    // TODO: Write test that should be skipped
    
    @Test
    public void testThatShouldCauseError() {
        recordTestIsRunning();
        String msg 
                = "This test should be reported as having caused an error";
        System.out.println(msg);
        throw new RuntimeException(msg);
    }
    
    @AfterEachTest
    public void tearDown() {
        // TODO: Figure out how to report that this was invoked
    }
    
    @AfterAllTests
    public void tearDownClass() {
        // TODO: Figure out how to report that this was invoked
    }
    
}

