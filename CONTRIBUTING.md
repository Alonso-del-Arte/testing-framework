This project is open to Hacktoberfest 2021.

# Guidelines for Hacktoberfest 2021 Contributors

I will definitely consider pull requests associated with an issue and maybe 
consider pull requests associated with a TODO (considered an Action Item in 
NetBeans).

Contributors should have a proper Java IDE (most are available for free), like 
Apache NetBeans. I'm actually using Eclipse for this project; since I don't like 
Eclipse's JUnit integration, I don't feel like I'm missing much.

This project uses Java 8. Contributors may use later versions of Java provided 
they don't use features not available in Java 8. No existing testing framework 
is to be used for this project.

Remember that the goal of this project isn't to replace an existing testing 
framework, or even to try to do that.

Except for `org.example.demo`, everything in `com.example` and `org.example` is 
meant to be exercises. Finished classes in those packages are not meant to be 
checked in. This will eventually be enforced by the Git Ignore.

As much as possible, commits to the demonstration packages should be one each to 
a step of the TDD cycle (fail, pass, refactor). That's a pain in the neck and I 
don't expect it for any other packages in this project.

## Style guidelines

* No blanket import statements except for static imports or when everything in a 
given package is imported.
* Column width is 80.
* Although this testing framework does not require test procedures to be named 
starting with `test`, that is highly recommended for the sake of clarity. The 
only exemption is `TestRunnerTest`, even though that one doesn't actually use 
the `@Test` annotation.
* If it doesn't have To Do comments, it should have Javadoc. Any other type of 
comment should be removed in refactoring commits, if it even makes into a fail 
or pass commit.
* Each checked in test class should provide some output that is distinct from 
that given by `TestRunner`.
* Given my preference for shallow inheritance hierarchies, I'm more concerned 
with whether a unit returns a result or not. Therefore, at least in the Javadoc, 
units that return a result will be referred to as functions and units that don't 
return a result will be referred to as procedures.
* As much as possible, inheritance hierarchies should be limited to four levels, 
e.g., `Object` extended by an abstract class that is extended by a concrete 
class.
* As much as possible, adhere to the principles in *Building Maintainable 
Software: Java Edition* by Joost Visser et al, while keeping in mind that that 
book allows for slight deviations.
* Also note that the tests in `TestRunnerTest` and `AssertersTest` by necessity 
need to be verbose, including a lot of "boilerplate" that will be rendered 
unnecessary for other test classes by the fully functional `TestRunner` and 
`Asserters`.