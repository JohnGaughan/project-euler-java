## Project Euler solutions written by John Gaughan

This project contains my answers to Project Euler. My work is completely unaffiliated with Project Euler.

You can visit the Advent of Code web site here: <https://projecteuler.net/>.

This is a living project that I will update as new code challenges are released, or I go back and finish previous problems.

## Why release this?

This is one way to show my coding abilities to the world both for professional and personal reasons. It makes it easy to collaborate with other developers by
being able to view my code: perhaps someone has an idea to help me improve.

## Technical details

This is an Eclipse project. I know people love to hate on it, but modern versions are fine even if it is not the latest flavor of the month.

I settled on Java 15. It is not LTS, but supports some features that are useful to make some solutions code concise.

The programs are implemented as separate Java classes implementing a common interface. There is a common JUnit test harness, with a Test-annotated method for
each individual problem. To run the code, simply run the test harness as a JUnit test or use the SolverValidations run configuration which does the same
thing.

Each problem is in a self-contained Java class, possibly with nested classes. This does clutter the code a little, but I think the tradeoff of
having the entire program just a scroll wheel away actually makes it _more_ readable.

Programs rely mostly on core Java, with a small number of dependencies managed using Maven. The "Euler Test" launch configuration will run the Maven goals
that acquire dependencies, compile the code, and run JUnit tests. My goal is to minimize work being done outside of each solution class, with the main
exception being generation of prime numbers.

Since so many problems rely on prime numbers and in such quantities that it can take quite a bit of time to generate them, this is done via a Prime Provider
class that uses a sieve algorithm to generate them. They are then written in binary form to a file in the file system, and read using Java NIO's mapping
which is extremely fast.

## Who am I?

My name is John Gaughan, and I am a professional software developer living in Ohio, USA. My email address is <john@coffeecode.us> and my personal blog is at
<https://coffeecode.us/>.

## Licenses

This project is licensed under the GNU GPL v3. See the LICENSE file for the complete text.

Library dependencies have their own copyright and open source licenses separate from and compatible with this creative work.

Input data in this project is the IP of Project Euler, and reproduced here in compliance with their license:
[Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)](https://creativecommons.org/licenses/by-nc-sa/4.0/). Copyright details are located
here: <https://projecteuler.net/copyright>
