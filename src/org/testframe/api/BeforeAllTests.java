package org.testframe.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for procedures to run before any tests have run. This can be 
 * useful for tasks like creating files for the tests to use or for opening 
 * Internet connections that the tests will need. If no such tasks are needed in 
 * a test class, this annotation is not necessary.
 * <p>A procedure with this annotation ought to give some indication that it has 
 * successfully completed its assigned set-up tasks, such as by writing a 
 * message to that effect to the console.</p>
 * <p>A procedure with this annotation should be public but not static. It will 
 * run before any procedures annotated {@link BeforeEachTest}, {@link Test}, 
 * {@link AfterEachTest} or {@link AfterAllTests}. That is guaranteed because 
 * I have written tests for this in the test class for {@link 
 * org.testframe.engine.TestRunner}.</p>
 * <p>One test class may have more than one procedure annotated 
 * <code>@BeforeAllTests</code>. I make no guarantees as to what order multiple 
 * procedures with this annotation will run in, other that they won't run after 
 * procedures with the other annotations from this package unless they are 
 * explicitly called directly. Nor do I make any promises that later releases of 
 * this framework will behave in the same way with multiple annotations of this 
 * kind in the same test class.</p>
 * <p>For that reason, if a test class has multiple annotations of this kind, 
 * they should not depend on each other to run in any particular order.</p>
 * <p>In any case it's preferable for a test class to only have one procedure 
 * with this annotation, if any. Such a procedure may make calls to private 
 * helper procedures.</p>
 * <p>You may use any identifier the compiler allows, but it's recommended that 
 * it be something that clearly indicates what the procedure does, such as 
 * <code>setUpClass()</code>.</p>
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BeforeAllTests {

}
