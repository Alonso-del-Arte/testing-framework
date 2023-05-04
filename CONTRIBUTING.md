This project will probably be open to Hacktoberfest 2023.

# Guidelines for Hacktoberfest 2023 Contributors

I will definitely consider pull requests associated with an issue and maybe 
consider pull requests associated with a TODO (considered an Action Item in 
NetBeans).

Contributors should have a proper Java IDE (most are available for free), like 
Apache NetBeans. I'm actually using Eclipse for this project; since I don't like 
Eclipse's JUnit integration, I don't feel like I'm missing much. Use whatever 
build tool your IDE provides, do not impose your choice on other contributors.

This project uses Java 8. Contributors may use later versions of Java provided 
they don't use features not available in Java 8. No existing testing framework 
is to be used for this project, except as references or models.

Remember that the goal of this project is not to replace an existing testing 
framework, or even to try to do that, but to understand how a testing framework 
can be made from scratch through test-driven development (TDD).

To understand how this testing framework works, I suggest you first look at 
`TestRunner` and `TestRunnerTest`, in the `testframe.engine` package.

Once you've got the project loaded in your favorite IDE with the necessary 
project settings (such as compiler output directory for IntelliJ IDEA), run
`TestRunnerTest`'s `main()` to make sure `TestRunner` is working properly.

Once you're sure that `TestRunner` is working properly, or at least that it 
correctly picks up `@Test` annotations, you can use it to run `AssertersTest` by 
calling `TestRunner`'s `main()` with the command line argument 
`testframe.api.AssertersTest` (but invoke it through your IDE to let it take 
care of the class path for you, e.g., in Eclipse, Run &rarr; Run 
Configurations...).

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
a step of the TDD cycle (fail, pass, refactor). That can be a pain in the neck 
&mdash; and I don't expect it for `com.example.exercises`, 
`org.example.exercises` or even `testframe.api` or `testframe.engine`. But 
remember: one push can contain multiple commits.

A note about branch management: there will be no Javadoc in HTML pages in the 
main branch, only the Javadoc comments in the Java source. Accepted pull 
requests will be merged to the main branch. Then, as needed, I will run the 
Javadoc tool to create the HTML pages to put in the gh-pages branch.

## Style guidelines

* The main package is `testframe`. Do not rename it `org.testframe`, even though 
in the future I might make that change if I see that it's required by Maven.
* Access levels will be respected as much as possible. This means, for one 
thing, that for tests to be discoverable by `TestRunner`, they must be public, 
not package private like in JUnit 5.
* No blanket import statements except for static imports or when absolutely 
everything in a given package is imported.
* Column width is 80.
* Opening curly braces should not be aligned with closing curly braces. If 
necessary to stay within the column width, indent `extends`, `implements`, a 
parameter, etc.
* Never split a constant or variable declaration across lines in such a way that 
the type is separated from the identifier, e.g., "`String msg`" should all be on the same line.
* Since semicolons are almost always mandatory in Java, when necessary to split 
expressions with operators, an operator should start the next line. For example, 
if it's necessary to split "`start + variance;`" across lines because the plus 
sign falls on column 80, "`start`" should stay on its original line and the next 
line should be "`+ variance;`" (with the proper indentation, of course).
* Prefer spaces to tabs. One annoying side effect of my choice of Eclipse for 
this project is that Eclipse uses tabs by default. I tried to replace all the 
tabs, but there might be stragglers.
* Indentation is four spaces per scope level. When breaking a long line to 
comply with the column width threshold, indent an additional four spaces. For 
example, if line 24 starts on column 9 and runs to column 90, split it so the 
new line 25 starts on column 17. 
* There should never be a stylish name for this testing framework. It should be 
referred to as "this testing framework" or "this framework."
* Although this testing framework does not require test procedures to be named 
starting with "`test`", that is highly recommended for the sake of clarity. The 
only exemption is `TestRunnerTest`, even though that one doesn't actually use 
the `@Test` annotation.
* If it doesn't have To Do comments, it should have Javadoc. Files in 
`com.example.exercises` and `org.example.exercises` may optionally have Hint 
comments. Any other type of comment should be removed in refactoring commits, if 
such a comment even makes it into a fail or pass commit. Only exemption for now 
are the comments in `Asserters` explaining what that static class will not 
provide.
* Each checked-in test class should provide some output that is distinct from 
that given by `TestRunner`. If nothing else occurs to you, simply have it output 
the names of the units being tested (like in automatically generated JUnit or 
TestNG tests in NetBeans).
* Given my preference for shallow inheritance hierarchies, I'm more concerned 
with whether a unit returns a result or not. Therefore, at least in the Javadoc, 
units that return a result will be referred to as "functions" and units that 
don't return a result will be referred to as "procedures." The term "method" 
will only be used in connection to Java reflection.
* As much as possible, inheritance hierarchies should be limited to four levels, 
e.g., `Object` extended by an abstract class that is extended by a concrete 
class that maybe is extended by a concrete subclass. Interfaces should not go 
beyond one level of inheritance unless absolutely necessary.
* As much as is practical, use what's available in the Java Development Kit 
(JDK). That means, for example, that if we don't have a reason to extend 
`AssertionError` (like in JUnit), we won't. Or that if you find something in the 
JDK that does the job of `testframe.api.Procedure`, we should refactor to use 
that instead, and deprecate accordingly.
* Deprecated classes, functions and procedures should be annotated with 
`@Deprecated`, as well as a Javadoc `@deprecated` tag explaining what to use 
instead.
* Do not delete anything deprecated that existed in a prior merged commit. I 
will decide when to delete deprecated items and when.
* However, do feel free to delete private items (including nested or inner 
classes) if they're not necessary to pass the pertinent tests.
* As much as possible, adhere to the principles in *Building Maintainable 
Software: Java Edition* by Joost Visser et al, while keeping in mind that that 
book allows for slight deviations from its guidelines.
* Also note that the tests in `TestRunnerTest` and `AssertersTest` by necessity 
need to be verbose, including a lot of "boilerplate" that will be rendered 
unnecessary for other test classes once `TestRunner`, `Asserters` and the 
various annotations are fully functional.
