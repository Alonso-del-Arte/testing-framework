package testframegeneric.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for test procedures. A test procedure should be public but not 
 * static. I'm aware that in TestNG the test annotation can be applied to the 
 * whole test class, in which case an individual test procedure doesn't need the 
 * annotation, unless it needs a timeout or expected exceptions attribute. I'm 
 * open to the idea of making this annotation assignable at the class level.
 * <p>In this testing framework, this annotation doesn't have any attributes and 
 * I don't think those will be necessary. Use {@link Asserters#assertTimeout} or
 * {@link Asserters#assertThrows}.</p> 
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

}
