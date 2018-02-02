Scrooge Coin Assignment
=======================

Score: 95% (the remaining 5% are the bonus which I did not try to solve yet).

There are tests for this but I was not able to run them. I do have JUnit
installed. Here is what I tried roughly:

```bash
ws=$(pwd)
ln -s ../cryptocurrency-course-materials/assignment1/test
cd test
# not sure if that's really needed
export JAVA_HOME=/usr/share/java
export JUNIT_HOME=/usr/share/java
# tell java where our classes are
export CLASSPATH=/usr/share/java/junit.jar:${ws}:.
javac *.java
java IsValidTest # does not work
```
