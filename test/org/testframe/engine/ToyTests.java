package org.testframe.engine;

import java.util.logging.Logger;

import org.testframe.api.AfterAllTests;
import org.testframe.api.AfterEachTest;
import org.testframe.api.BeforeAllTests;
import org.testframe.api.BeforeEachTest;
import org.testframe.api.Skip;
import org.testframe.api.Test;

public class ToyTests {
    
    private static final String TEST_CLASS_NAME 
            = "org.testframe.engine.ToyTests";
    
    private static final Logger INVOCATION_LOGGER 
            = Logger.getLogger(TEST_CLASS_NAME);
    
    @BeforeAllTests
    public void setUpClass() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@BeforeAllTests");
    }
    
    @BeforeEachTest
    public void setUp() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@BeforeEachTest");
    }
    
    @Test
    public void testThatShouldPass() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Test");
        String msg = "This test should be reported as passing";
        System.out.println(msg);
    }
    
    @Test
    public void testThatShouldFail() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Test");
        String msg = "This test should be reported as failing";
        System.out.println(msg);
        assert false : msg;
    }
    
    @Skip @Test
    public void testThatShouldBeSkipped() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Skip @Test");
        String msg = "This test should be skipped";
        System.out.println(msg);
        assert false : msg;
    }
    
    
    @Test
    public void testThatShouldCauseError() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@Test");
        String msg 
                = "This test should be reported as having caused an error";
        System.out.println(msg);
        throw new RuntimeException(msg);
    }
    
    @AfterEachTest
    public void tearDown() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@AfterEachTest");
    }
    
    @AfterAllTests
    public void tearDownClass() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@AfterAllTests");
    }
    
}

