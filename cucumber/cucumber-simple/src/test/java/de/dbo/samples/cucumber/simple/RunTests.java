package de.dbo.samples.cucumber.simple;

import org.junit.runner.RunWith;

/*
 * about format:
 * the pretty format below: http://cukes.info/gherkin.html
 * HOW-TO publish in by Jenkins:
 * https://github.com/masterthought/jenkins-cucumber-jvm-reports-plugin-java
 */

import cucumber.junit.Cucumber;
 
@RunWith(Cucumber.class)
@Cucumber.Options(format={"pretty", "html:target/cucumber"})
public class RunTests {
	
}
