This project will probably be open to Hacktoberfest 2022.

# Guidelines for Hacktoberfest 2022 Contributors

I will definitely consider pull requests associated with an issue and maybe 
consider pull requests associated with a TODO (considered an Action Item in 
NetBeans).

Contributors should have a proper Java IDE (most are available for free), like 
Apache NetBeans. I'm actually using Eclipse for this project; since I don't like 
Eclipse's JUnit integration, I don't feel like I'm missing much.

This project uses Java 8. Contributors may use later versions of Java provided 
they don't use features not available in Java 8. No existing testing framework 
is to be used for this project, except as references or models.

Remember that the goal of this project is not to replace an existing testing 
framework, or even to try to do that, but to understand how a testing framework 
can be made from scratch through test-driven development (TDD).

To understand how this works, I suggest you first look at `TestRunner` and 
`TestRunnerTest`, in the `testframe.engine` package.

Run `TestRunnerTest`'s `main()` to make sure `TestRunner` is working properly.

Once you're sure that `TestRunner` is working properly, or at least that it 
correctly picks up `@Test` annotations, you can use it to run `AssertersTest` by 
calling `TestRunner`'s `main()` with the command line argument 
`testframe.api.AssertersTest`.

If `Asserters` has all the assertions you need for a particular test class, you 
can go ahead and use it to write new test classes. But if not, maybe you can 
develop the assertions you need.

But remember that this framework will not provide `assertTrue()`, 
`assertFalse()`, `assertNull()` or `assertNotNull()`. Use a plain Java `assert` 
for those, or import a specialized assertions library like AssertJ (but don't 
pull request to add any dependencies to this project besides the JDK).

As for `assertArrayEquals()`, that will be provided as overloads of 
`assertEquals()`, much like in TestNG.

Except for `org.example.demo`, everything in `com.example` and `org.example` is 
meant to be exercises. Finished classes in those packages are not meant to be 
checked in. This will eventually be enforced by the Git Ignore.

As much as possible, commits to the demonstration packages should be one each to 
a step of the TDD cycle (fail, pass, refactor). That's a pain in the neck and I 
don't expect it for `com.example.exercises`, `org.example.exercises` or even 
`testframe.api` or `testframe.engine`.

## Style guidelines

* No blanket import statements except for static imports or when absolutely 
everything in a given package is imported.
* Column width is 80.
* Prefer spaces to tabs. One annoying side effect of my choice of Eclipse for 
this project is that Eclipse uses tabs by default. I tried to replace all the 
tabs, but there might be stragglers.
* Although this testing framework does not require test procedures to be named 
starting with `test`, that is highly recommended for the sake of clarity. The 
only exemption is `TestRunnerTest`, even though that one doesn't actually use 
the `@Test` annotation.
* If it doesn't have To Do comments, it should have Javadoc. Files in 
`com.example.exercises` and `org.example.exercises` may optionally have Hint 
comments. Any other type of comment should be removed in refactoring commits, if 
such a comment even makes into a fail or pass commit. Only exemption for now are 
the comments in `Asserters` explaining what that static class will not provide.
* Each checked in test class should provide some output that is distinct from 
that given by `TestRunner`. If nothing else occurs to you, simply have it output 
the names of the units being tested (like in automatically generated JUnit or 
TestNG tests in NetBeans).
* Given my preference for shallow inheritance hierarchies, I'm more concerned 
with whether a unit returns a result or not. Therefore, at least in the Javadoc, 
units that return a result will be referred to as functions and units that don't 
return a result will be referred to as procedures. The term "method" will only 
be used in connection to Java reflection.
* As much as possible, inheritance hierarchies should be limited to four levels, 
e.g., `Object` extended by an abstract class that is extended by a concrete 
class that maybe is extended by a concrete subclass.
* As much as is practical, use what's available in the Java Development Kit 
(JDK). That means, for example, that if we don't have a reason to extend 
`AssertionError` (like in JUnit), we won't. Or that if you find something in the 
JDK that does the job of `testframe.api.Procedure`, we should use that instead.
* As much as possible, adhere to the principles in *Building Maintainable 
Software: Java Edition* by Joost Visser et al, while keeping in mind that that 
book allows for slight deviations.
* Also note that the tests in `TestRunnerTest` and `AssertersTest` by necessity 
need to be verbose, including a lot of "boilerplate" that will be rendered 
unnecessary for other test classes once `TestRunner`, `Asserters` and the 
various annotations are fully functional.
