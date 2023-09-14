# Functional Fun

## Overview
The goal of this kata is to practice refactoring [imperative code to a more declarative](https://dev.to/ruizb/declarative-vs-imperative-4a7l) style.

Java 8 introduced the Streams API which provides support for [functional](https://dev.to/ruizb/introduction-179d) programming.

## Instructions

If you look at [ReadingProcessor.java](./src/ReadingProcessor.java) you will find that there is a
"for" loop that goes through an array of "readings".

The loop ignores some readings, changes some readings (converts Celsius to Fahrenheit),
and then finally groups the readings by "type" into arrays in a new object.

Your job is to replace the imperative "for" loop and "if" statements with a call to the Java Streams API such as
[filter, map, reduce](https://belief-driven-design.com/functional-programm-with-java-map-filter-reduce-77e479bd73e/),

There are tests to allow you to refactor with safety. You can assume that the tests cover the behaviour we want to preserve, so if the tests keep running you're gold.

### Bonus
You'll notice that there is a skipped test `should not mutate readings`

The current code changes the readings that are passed to it. Changing your inputs is a bad practice because it can cause unpredictable problems in other parts of the code that might rely on the readings.

Once you have refactored a bit do you see an opportunity to not mutate the reading? If so, stop ignoring the test and write new code to fix the problem.

## How to run the code

This kata requires Java version 17 or greater.

To install libraries `mvn clean install`

To run tests `mvn test` or execute directly from IntelliJ.


# Facilitators Notes

The intention of this kata is to practice the refactoring that Martin Fowler calls [Replace Loop With Pipeline](https://refactoring.com/catalog/replaceLoopWithPipeline.html) [(example)](https://martinfowler.com/articles/refactoring-pipelines.html)


## Guidance for mob.sh

Mob.sh is a simple GO script that facilitates mobbing and pairing using a git
branch as a go-between. It aims to be very simple in its implementation.

### TL;DR

- `brew install remotemobprogramming/brew/mob`
- `mob start -b [unique_name]` starts a mob session with a uniquely named
  coordination branch
- `mob next` to hand control off to the next pilot
- `mob start -b [unique_name]` to pick-up control for the next pilot
- `mob done` when you're done to drop all changes (squashed, staged) ready to
  commit to the branch you started in

### Setting Up

Ensure you have mob.sh installed:

```
brew install remotemobprogramming/brew/mob
```

### Usage

The person starting off the mob session starts in the master branch of the
repository and types:

```
mob start -b (choose a unique name)
```

_Note: It's important when there may be many simultaneous mob/pairing sessions
within a repository to specify a unique name for your particular mob session.
This determines a unique branch name for coordination that won't clash with
other sessions. The branch name used for the mob session will end up looking
something like `mob/master/story123456`._

For a unique name, choose something that suits you:

- a story number
- a handle
- something random or made up
- no spaces or punctuation!

This places you in a special branch in which all mobbing takes place until your
session is complete.

There is no need to make discreet commits - commits along the mob branch are
squashed and presented at the end as a set of uncommitted files in the branch in
which you started.

When the first pilot is ready to hand the session off to a co-pilot, they issue
the command:

```
mob next
```

This will create a work-in-progress commit, push the changes up to the git repo,
and will then be ready for the next pilot to take control.

The next pilot takes control with:

```
mob start -b (the same unique name you started with)
```

And this is how it goes as you bounce control between the pilots in your mobbing
session. When the session is done, the last pilot issues:

```
mob done
```

This will squash all work-in-progress commits along the mob branch and present
them as uncommited changes to the master. It's then just a normal commit to
master for all the work completed by everyone in the mob.

For example:

```
git commit -am "describe the work done here, tag a jira ticket"
git push
```

And that's it!

Happy Mobbing / Pairing!

Please direct any questions to [John Borys](mailto:john.borys@rbc.com) or [Shawn Button](mailto:shawn.button@rbc.com).