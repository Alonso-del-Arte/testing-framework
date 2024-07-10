package org.testframe.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for test procedures. A test procedure should be public but not 
 * static. The test procedure's name doesn't have to start with 
 * "<code>test</code>" but that is strongly recommended for the sake of clarity, 
 * especially for tests of overridden functions like <code>equals()</code>.
 * <p>I'm aware that in JUnit 5 test procedures can be package private. If we 
 * want to enable that, it could be done, it's probably a matter of making 
 * changes to the test runner. But I prefer not to run roughshod over access 
 * levels if it's not absolutely necessary.</p>
 * <p>I'm also aware that in TestNG the test annotation can be applied to the 
 * whole test class, in which case an individual test procedure doesn't need the 
 * annotation, unless it needs a timeout or expected exceptions attribute. I'm 
 * open to the idea of making this annotation assignable at the class level.</p>
 * <p>In this testing framework, this annotation doesn't have any attributes and 
 * I don't think those will be necessary. Use {@link Asserters#assertTimeout} or
 * {@link Asserters#assertThrows}.</p>
 * @since 1.0
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

}
