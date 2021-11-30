## Automation Framework
[![Build Status](https://github.com/anoopsimon/java-endtoend-fw/workflows/Build/badge.svg)](https://github.com/anoopsimon/Build/actions)

A Simple framework built using Java-Selenium 4 - Cucumber to automate web , mobile applications

### Libraries:
- Selenium WebDriver 4.*.*
- Cucumber JVM 6.*.*
- Java 1.8.*
- Fast Excel

### Run tests from CI tools
Simply run command via CI tool of your choice.

```mvn clean install exec:java -Dexec.classpathScope=test -Dexec.args="--tags @demo" -Denvironment=uat```


