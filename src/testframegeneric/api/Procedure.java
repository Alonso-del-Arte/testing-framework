package testframegeneric.api;

/**
 * Parameter type for lambdas that don't return a result but may throw 
 * exceptions.
 * @author Alonso del Arte
 */
@FunctionalInterface
public interface Procedure {
	
	void execute() throws Exception;

}
