# Testing Framework

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

## Release history

* Early users beta, December 30, 2023. This was very bare bones.
* 0.95, March 30, 2024. This was much closer to usable.
* 1.0, July 10, 2024. Pretty much everything I wanted the first official release 
to have.
* 1.01, March 3, 2025. I discovered `assertInRange()` was missing the customized 
messages for parameters of type `double`, so I patched that.
