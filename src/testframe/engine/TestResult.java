package testframe.engine;

import java.lang.reflect.Method;

public class TestResult {
    
    private final Method testProcedure;
    
    private final TestResultStatus testStatus;
    
    private final Throwable testStackTraceHolder;
    
    public Method getProcedure() {
        return this.testProcedure;
    }
    
    public TestResultStatus getStatus() {
        return this.testStatus;
    }
    
    public boolean hasStackTrace() {
        return false;// this.testStackTraceHolder == null;
    }
    
    // TODO: Write tests for this
    public Throwable getInformation() {
        return null;// this.testStackTraceHolder;
    }
    
    public TestResult(Method procedure, TestResultStatus status, 
            Throwable information) {
        this.testProcedure = procedure;
        this.testStatus = status;
        this.testStackTraceHolder = information;
    }

}
