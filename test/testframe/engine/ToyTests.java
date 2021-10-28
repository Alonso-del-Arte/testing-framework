package testframe.engine;

import testframe.api.AfterAllTests;
import testframe.api.AfterEachTest;
import testframe.api.BeforeAllTests;
import testframe.api.BeforeEachTest;
import testframe.api.Test;

public class ToyTests {
    
    static String status = "The ToyTests class has been loaded";
    
    static final String BEFORE_ALL_STATUS = "setUpClass() has run";

    static final String BEFORE_EACH_STATUS = "setUp() has run";
    
    static final String DURING_STATUS = "A test is running";

    static final String AFTER_EACH_STATUS = "tearDown() has run";

    static final String AFTER_ALL_STATUS = "tearDownClass() has run";
    
    static int beforeAllCounter = 0;
    
    static int beforeEachCounter = 0;
    
    static int duringCounter = 0;
    
    static int afterEachCounter = 0;
    
    static int afterAllCounter = 0;

    private static boolean relay(boolean flag) {
        return flag;
    }
    
    private static void reportStatus() {
        System.out.println(status);
    }
    
    @BeforeAllTests
    public void setUpClass() {
        beforeAllCounter++;
        status = BEFORE_ALL_STATUS;
        reportStatus();
    }
    
    @BeforeEachTest
    public void setUp() {
        String msg = "setUpClass() should have run once already";
        assert beforeAllCounter == 1 : msg;
        beforeEachCounter++;
        status = BEFORE_EACH_STATUS;
        reportStatus();
    }
    
    private void recordTestIsRunning() {
        duringCounter++;
        String msg = "setUp() should've run as many times as tests run so far";
        assert duringCounter == beforeEachCounter : msg;
        status = DURING_STATUS;
        reportStatus();
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
        status = DURING_STATUS;
        String msg = "This test should be reported as failing";
        System.out.println(msg);
        assert relay(false) : msg;
    }
    
    // TODO: Write test that should be skipped
    
    @Test
    public void testThatShouldCauseError() {
        recordTestIsRunning();
        status = DURING_STATUS;
        String msg 
                = "This test should be reported as having caused an error";
        System.out.println(msg);
        throw new RuntimeException(msg);
    }
    
    @AfterEachTest
    public void tearDown() {
        afterEachCounter++;
        String msg = "tearDown() should've run once for each test so far";
        assert duringCounter == afterEachCounter : msg;
        status = AFTER_EACH_STATUS;
        reportStatus();
    }
    
    @AfterAllTests
    public void tearDownClass() {
        afterAllCounter++;
        status = AFTER_ALL_STATUS;
        reportStatus();
    }
    
}

