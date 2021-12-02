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

Jenkins file is added 
### Setup your Github Credentials via SSH in Jenkins. 
1. Open Git Bash , and enter command given below
 ```ssh-keygen -t ed25519 -C "<yourgithubemail>"```
2. Follow instructions provided by gitbash.
3. Copy the private key 
4. Login to your GitHub account , navigate to settings > SSH & GPG Keys
5. Add a new SSH key
6. Enter name for SSH key
7. paste the Private key
8. Go to jenkins > Credentials > Add Credential
9. Select Credential Type as 'SSH'
10. Paste the public key and Click Generate.

