# Testing Framework

NOTE: I have decided to upgrade this project to Java 21. I want this testing 
framework to be at least one LTS version behind the latest LTS version, but Java 
8 is too far now. The release after 1.01 will require Java 21.

----

The idea isn't to supplant JUnit or any other existing unit testing framework, 
but to understand how a testing framework could be created from scratch through 
test-driven development (TDD) without recourse to any existing testing 
framework.

For this project, we acknowledge the existence of frameworks like TestNG, and we 
do use ideas from those frameworks, but we won't be using those frameworks 
directly (except maybe as a formality in NetBeans to get the proper separation 
between Source Packages and Test Packages started).

For instructions on how to use this testing framework, see 
[How to Use This Framework](UserManual.md). For instructions on how to 
contribute to this project, see [CONTRIBUTING.md](CONTRIBUTING.md).

Note that although we've upgraded to Java 21, the releases 0.9, 0.95 and 1.0 and 
1.01 were all compiled with Java 8.

Obviously this testing framework is a test dependency and should be put in test 
libraries rather than source libraries. However, other items in the 
`org.testframe` namespace, like the warnings library, are meant to be source 
libraries. Those will be provided in separate JARs.

I originally used Eclipse for this project, but now I've decided I will not use 
any integrated development environment (IDE) for it. I will use a combination of 
Brackets and Vim to edit the source files, and compile and run on the command 
line.

## Release history

* Early users beta, December 30, 2023. This was very bare bones.
* 0.95, March 30, 2024. This was much closer to usable.
* 1.0, July 10, 2024. Pretty much everything I wanted the first official release 
to have.
* 1.01, March 3, 2025. I discovered `assertInRange()` was missing the customized 
messages for parameters of type `double`, so I patched that.

## Known issues

By doing more work on this project on the command line, I have discovered some 
issues that are just not issues when using this testing framework in an IDE.

* When the test runner can't find the test class, it reports it couldn't find 
the test class, but then it also reports that 0 tests passed, 0 tests failed, 0 
tests were skipped and 0 tests caused errors. This is confusing and I intend to 
correct this once I decide on a better way for the test runner to behave in this 
case.
* On macOS, the test runner seems to get stuck after running more than 300 
tests. It does report the results, but the command prompt doesn't return 
immediately like it does when running a test class with fewer tests. I haven't 
yet figured out why this is.
* On an older computer that doesn't run as fast as it used to, such as a 
computer that originally ran Windows 7, was upgraded to Windows 10 and can't be 
"upgraded" to Windows 11, tests with `assertTimeout()` might be brittle, failing 
then passing without changes to the class under test. I'm not sure if this is an 
issue that I can fix.
