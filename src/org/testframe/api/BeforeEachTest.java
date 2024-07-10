package org.testframe.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for procedures to run right before each test. This can be 
 * useful for tasks like ensuring certain mutable objects meet certain 
 * conditions prior to a test. If no such tasks are needed, this annotation is 
 * not needed.
 * <p>For example, in <code>SavingsAccountTest</code>, you might want to ensure 
 * a <code>SavingsAccount</code> object has a certain minimum balance prior to a 
 * test, and deposit enough money to cover any shortfall.</p>
 * <p>A procedure with this annotation ought to give some indication that it has 
 * successfully completed its assigned set-up tasks, such as by writing a 
 * message to that effect to the console.</p>
 * <p>A procedure with this annotation should be public but not static. It will 
 * run after any procedures annotated {@link BeforeAllTests}, but before any 
 * procedures annotated {@link Test}, {@link AfterEachTest} or {@link 
 * AfterAllTests}. That is guaranteed because I have written tests for this in 
 * the test class for {@link org.testframe.engine.TestRunner}.</p>
 * <p>One test class may have more than one procedure annotated 
 * <code>@BeforeEachTest</code>. But that's probably more trouble than it's 
 * worth.</p>
 * <p>In any case it's preferable for a test class to only have one procedure 
 * with this annotation, if any. Such a procedure may make calls to private 
 * helper procedures.</p>
 * <p>You may use any identifier the compiler allows, but it's recommended that 
 * it be something that clearly indicates what the procedure does, such as 
 * <code>setUp()</code> or <code>setUpTest()</code>.</p>
 * @since 1.0
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeEachTest {

}
