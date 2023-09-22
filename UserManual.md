# How to Use This Testing Framework

Aside from the fact that this testing framework has no integration with any 
integrated development environment (IDE), at least not yet anyway, using this 
testing framework is a lot like using JUnit or TestNG.

Most of the examples will be drawn from 
`org.example.exercises.arithmetic.Fraction` and 
`org.example.exercises.arithmetic.FractionTest` (these were split off to a 
separate repository, Examples for Testing Framework).

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

Thus a test class might start out looking something like this:

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
to (it would make for one less thing to change if you're copying over from 
Salesforce Apex).

Tests may be functions that return results, but I can't think of any legitimate 
reason I'd want to do that. Test classes may have private helper functions that 
the test procedures call on as needed.

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

#### Testing for exceptions

A test that causes an exception is considered neither passing nor failing by 
this testing framework. This means that if a unit under test 

* *is* supposed to throw a given exception to pass the test, that particular 
exception should be caught by the test, and if it doesn't occur, an `AssertionError` should be thrown.
* is *not* supposed to throw a given exception to pass the test, that particular 
exception should be caught by the test and wrapped in an `AssertionError` to be thrown.

Here's an example of the former:

```
    @Test
    public void testRejectDenominatorZero() {
        try {
            Fraction badFraction = new Fraction(1, 0);
            String errMsg = "Wrongly created " + badFraction.toString();
            throw new AssertionError(errMsg);
        } catch (ArithmeticException ae) {
            System.out.println("Correctly rejected denominator zero");
        } catch (RuntimeException re) {
            String errMsg = re.getClass().getName() 
                    + " is the wrong exception for denominator zero";
            throw new AssertionError( 
    }
```

To pass this test, the `Fraction` constructor needs to reject 0 for a 
denominator by throwing an `ArithmeticException`, which is then caught by the 
test's first Catch block.

Here's a sketch for an example of the latter, this one's from the test class 
for `ArrayBackedList<E>`:

```
    @Test
    public void testAddCanExpandCapacity() {
        int size = // get a small pseudorandom positive integer
        // create list of some type E with size as initial capacity
        // fill list to initial capacity
        boolean exceptionOccurred = false;
        try {
            E oneMore = // make some new instance of E
            list.add(oneMore);
        } catch (RuntimeException re) {
            exceptionOccurred = true;
        }
        String msg = "List of " + size 
                + " elements should have expanded for one more element";
        assert !exceptionOccurred : msg;
    }
```

When we write this test, the list class should be far enough along to fill the 
backing array to the initial capacity, but not yet able to expand the backing 
array when it becomes necessary to do so.

So it should fail this test because an `ArrayIndexOutOfBoundsException` occurs 
when the test tries to add `oneMore`. Then we implement the list's ability to 
expand its backing array, and the test passes because no exception is thrown.

In either case, I would like any `AssertionError` object to include the 
triggering exception as its cause (available through the `getCause()` function). 
But then that makes our tests longer and more repetitive.

First time I wrote a test with Try-Catch with JUnit 4, I liked it. But then it 
quickly got old. And I never much liked the expected exception attribute in 
JUnit 4.

It took me a while to warm up to the JUnit 5 way of testing for exceptions, but 
by the time I started working on this testing framework, I had come to like it, 
and decided I would do it that way here. Well, not quite exactly, but the 
underlying idea is the same.

It greatly simplifies testing units that should throw a given exceptions and 
units that shouldn't throw any exception. The test for the `Fraction` 
constructor rejecting denominator 0 is only three lines shorter, but it does 
more:

```
    @Test
    public void testRejectDenominatorZero() {
        ArithmeticException ae = assertThrows(() -> {
            Fraction badFraction = new Fraction(1, 0);
            System.out.println("Should not have been able to create " 
                    + badFraction.toString());
        }, ArithmeticException.class, "Denominator 0 should be rejected");
        String excMsg = ae.getMessage();
        assert excMsg != null : "Message should not be null";
    }
```

We don't need to write a Catch for `RuntimeException` because `assertThrows()` 
will take care of notifying us if any exception other than an 
`ArithmeticException` occurs.

Likewise, `assertDoesNotThrow()` simplifies the writing of a test that asserts a 
given kind of call causes no exceptions.

```
    @Test
    public void testAddCanExpandCapacity() {
        int size = // get a small pseudorandom positive integer
        // create list of some type E with size as initial capacity
        // fill list to initial capacity
        E oneMore = // make some new instance of E
        String msg = "List of " + size 
                + " elements should have expanded for one more element";
        assertDoesNotThrow(() -> {
            list.add(oneMore);
        }, msg);
    }
```

(finish writing)

### Designating test class set-up, tear-down

As much as it is practical to do so, we should write immutable classes with pure 
functions. Mutable classes are still have their time and place in Java, and to 
test them it's sometimes necessary to 

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

If you want to use `assertThrows()` or `assertDoesNotThrow()` in Scala, note 
that the syntax has some important differences that go beyond the much-maligned 
semicolons, which you can leave in if you want. Assuming you have `apply()` 
defined in the `Fraction` companion object:

```
    val ae = assertThrows(() => {
      val badFraction = Fraction(1, 0)
      println("Should not have been able to create " +
          badFraction.toString())
    }, classOf[ArithmeticException], "Denominator 0 should be rejected")
```

The two little details that trip me up every now and then:

* Instead of `() -> {}` you have to write `() => {}`.
* Instead of `Exception.class`, you have to write `classOf[Exception]`.

The other differences either are due to overall differences between Java and 
Scala (e.g., the more generally available type inference) or are purely 
superficial (e.g., the preference for two spaces instead of four for the 
indentation interval).
 
A consequence of semicolon inference (finish writing) operators 
