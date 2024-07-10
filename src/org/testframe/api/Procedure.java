package org.testframe.api;

/**
 * Parameter type for lambdas that don't return a result but may throw 
 * exceptions.
 * @since 1.0
 * @author Alonso del Arte
 */
@FunctionalInterface
public interface Procedure {
    
    void execute() throws Exception;

}
