# How to Use This Testing Framework

Aside from the fact that this testing framework has no integration with any 
integrated development environment (IDE), at least not yet anyway, using this 
testing framework is a lot like using JUnit or TestNG.

Most of the examples will be drawn from 
`org.example.exercises.arithmetic.Fraction` and 
`org.example.exercises.arithmetic.FractionTest`.

## Starting to write the class to be tested

I strongly recommend putting in a stub for the class to be tested (in Source 
Packages). We should not be too worried about warnings at this point, but errors 
diminish your IDE's ability to be helpful to you. But if you want to be an 
absolute purist about it, you can certainly start with the test class instead.

If a class is to have explicit constructors, it might be a good idea to have the 
corresponding constructor stubs. For example, 

```
package org.example.exercises.arithmetic;

public class Fraction {
    
    private final int numerator, denominator;
    
    // TODO: Write tests
    public Fraction(int numer, int denom) {
        this.numerator = numer;
        this.denominator = denom;
    }

}
```

## Writing the test class

The test class should have the same name as the class to be tested but with 
"`Test`" at the end. For `Fraction` this would be `FractionTest`. From the point 
of view of the Java Virtual Machine, the class under test should be in the same 
package as the test class, but from the point of view of the IDE, they ought to 
be in separate folders, with the test class under Test Packages.

At the very minimum, a test class needs to use a testing framework's `@Test` 
annotation in order for the test runner to pick up any tests to run, and that 
applies to this testing framework. As with any other framework, you can use the 
full path for each test, but it's generally best to import the annotation.

This testing framework provides (finish writing) 

### Writing a test

(finish writing)
(finish writing)
(finish writing)
(finish writing)

### Designating test class set-up, tear-down

(finish writing)

### Designating test set-up, tear-down

(finish writing)

### Designating a test to be skipped

The annotation `@Skip` is intended to be used for this purpose, but `TestRunner` 
does not yet have the ability to skip tests. For now, the only way to skip a 
test is to comment out or delete its `@Test` annotation.

## Running the tests

(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)
