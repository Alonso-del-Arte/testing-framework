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

Remember that the class under test can have assertions, preferably with plain 
Java Assert statements (it would not be good form for the class under test to 
call anything in a testing framework). Assertions in a production class may or 
may not be of use to you.

## Writing the test class

The test class should have the same name as the class to be tested but with 
"`Test`" at the end. For `Fraction` this would be `FractionTest`. That's not 
required by this testing framework, though.

From the point of view of the Java Virtual Machine, the class under test should 
be in the same package as the test class, but from the point of view of the IDE, 
they ought to be in separate folders, with the test class under Test Packages.

At the very minimum, a test class needs to use a testing framework's `@Test` 
annotation in order for the test runner to pick up any tests to run, and that 
applies to this testing framework. As with any other framework, you can use the 
full path for each test, but it's generally best to import the annotation.

This testing framework provides static procedures you can use to write 
assertions in the `Asserters` class. A blanket static import is recommended even 
if you only foresee using `assertEquals()`.

Thus a test class might start out looking like this:

```
package org.example.exercises.arithmetic;

import testframe.api.Test;
import static testframe.api.Asserters.*;

public class FractionTest {
}    
```

But this testing framework does not specifically require the use of the 
assertion procedures it provides. Anything that throws an `AssertionError` for 
failing tests will do, such as a plain Java Assert or something from a 
third-party library like AssertJ.

Depending on your preference, you may at this point put in Import statements for 
other things you foresee your test class will need. For example, if you're 
working on a file chooser, you might want to have imports for `java.io.File` and 
related classes from the `java.io` package.

### Writing a test

Writing tests with this testing framework is a lot like writing tests with JUnit 
or TestNG. A test is annotated with the `@Test` annotation. Like in JUnit 4, 
tests must be public or they will not be picked up by the test runner.

Tests may be static rather than instance procedures. I find it interesting that 
in Salesforce Apex testing tests are required to be static. In this Java testing 
framework, you're welcome to make tests static if you want, but you don't have 
to.

Tests may be functions that return results, but I can't think of any legitimate 
reason I'd want to do that.

A test should contain an assertion that causes an `AssertionError` if a certain 
condition is false. Most tests will probably rely on `assertEquals()`. For 
example:

```
    @Test
    public void testToString() {
        System.out.println("toString");
        Fraction fraction = new Fraction(1, 2);
        String expected = "1/2";
        String actual = fraction.toString().replace(" ", "");
        assertEquals(expected, actual);
    }
```

In this example I would prefer a test that comes up with a pseudorandom integer 
for the numerator, and maybe some prime number for the denominator, but this is 
good enough for the example.

A test that causes an exception is considered neither passing nor failing by 
this testing framework. This means that if a unit under test 

* *is* supposed to throw a given exception to pass the test, that particular 
exception should be caught by the test, and if it doesn't occur, an `AssertionError` should be thrown.
* is *not* supposed to throw a given exception to pass the test, that particular 
exception should be caught by the test and wrapped in an `AssertionError` to be thrown.

Here's an example of the former:

```
```

(finish writing)

Here's an example of the latter:

```
```

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

> Test results for org.example.exercises.arithmetic.FractionTest
testToString FAILED Expected = 1/2. Actual = org.example.exercises.arithmetic.Fraction@135fbaa4

(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)

> Passed: 2. Failed: 1. Skipped: 0. Caused an error: 0
Total: 3

(finish writing)
(finish writing)
(finish writing)
(finish writing)
(finish writing)

## Using this testing framework with Scala, Kotlin

(finish writing)
