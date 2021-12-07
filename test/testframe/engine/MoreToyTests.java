package testframe.engine;

import java.util.logging.Logger;

import testframe.api.AfterAllTests;
import testframe.api.AfterEachTest;
import testframe.api.BeforeAllTests;
import testframe.api.BeforeEachTest;
import testframe.api.Test;

/**
 * Just two more toy tests, one that should pass and one that should fail. The 
 * goal here, though, is to make sure each test class can have multiple class 
 * set-ups and tear-downs, as well as multiple test set-ups and tear-downs. 
 * Generally it makes more sense for each test class to have only one of each of 
 * those, if any at all. But it's much easier to allow multiple ones of these 
 * instead of enforcing uniqueness in each test class.
 * @author Alonso del Arte
 */
public class MoreToyTests {

    private static final String TEST_CLASS_NAME 
            = "testframe.engine.MoreToyTests";
    
    private static final Logger INVOCATION_LOGGER 
            = Logger.getLogger(TEST_CLASS_NAME);
    
    @BeforeAllTests
    public void setUpClassAlpha() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@BeforeAllTests");
        System.out.println("setUpClassAlpha");
    }
    
    @BeforeAllTests
    public void setUpClassBeta() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@BeforeAllTests");
        System.out.println("setUpClassBeta");
    }
    
    @BeforeEachTest
    public void setUpAlpha() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@BeforeEachTest");
        System.out.println("setUpAlpha");
    }
    
    @BeforeEachTest
    public void setUpBeta() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@BeforeEachTest");
        System.out.println("setUpBeta");
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
    
    @AfterEachTest
    public void tearDownAlpha() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@AfterEachTest");
        System.out.println("tearDownAlpha");
    }
    
    @AfterEachTest
    public void tearDownBeta() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@AfterEachTest");
        System.out.println("tearDownBeta");
    }
    
    @AfterAllTests
    public void tearDownClassAlpha() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@AfterAllTests");
        System.out.println("tearDownClassAlpha");
    }
    
    @AfterAllTests
    public void tearDownClassBeta() {
        INVOCATION_LOGGER.entering(TEST_CLASS_NAME, "@AfterAllTests");
        System.out.println("tearDownClassBeta");
    }
    
}
