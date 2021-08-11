# Web application Testing with cucumber framework using Selenium and Java

This is the test automation framework developed for web application Orange HRM. This automation framework is developed using selenium, cucumber, java and maven.

## Project Description

It is a behavior driven development (BDD) approach to write automation test script to test Web applications. It enables you to write and execute automated acceptance/unit tests. It is cross-platform, open source and free. Automate your test cases with minimal coding.


## Project structure

```python
Project Name

├───driver
├───src
│   ├───main
│   │   ├───java
│   │   │    ├───feature
│   │   │    ├───pages
│   │   │    ├───stepDefination
│   │   │    └───utility
│   │   │
│   │   ├───-resources
│   │       ├───properties
│   │       ├───screenshots
│   │       ├───config
│   └───test
│       └───java
│           └───testRunner
├───target
│   ├───classes
│   ├───allure-results
│   ├───allure-reports
│   ├───cucumber-reports
│   ├───maven-archiver
│   ├───maven-status
│   │   └───maven-compiler-plugin
│   │       ├───compile
│   │       │   └───default-compile
│   │       └───testCompile
│   │           └───default-testCompile
│   ├───surefire-reports
│   └───test-classes
│       └───testRunner
```

```python

1) src/main/java/feature - Cucumber features files
2) src/main/java/pages - All page object classes
3) src/main/java/stepDefination - Cucumber step defination class
4) src/main/java/utility - Utility classes
5) src/test/java/testRunner - Cucumber test runner class
```

## Writing test cases
The cucumber features goes in the features library and should have the ".feature" extension.

You can start out by looking at src/main/java/feature. You can extend this feature or make your own features using some of the predefined steps that comes with selenium-cucumber.

## Application under test
You can find the application under test in configuration url

## HTML Reporting
I have incorporated Allure HTML reporting tool into the framework, to create the reports once the is executed you can run below command

```python
allure generate --clean
```

## Execute test cases in the framework
Navigate to your project directory from terminal and run following commands 

```python
mvn clean test
```
