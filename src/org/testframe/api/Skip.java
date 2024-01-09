package org.testframe.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a test annotated with {@link Test} should be skipped. Note 
 * that this annotation has no effect on a procedure that does not also have the 
 * <code>@Test</code> annotation.
 * @author Alonso del Arte
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Skip {

}
