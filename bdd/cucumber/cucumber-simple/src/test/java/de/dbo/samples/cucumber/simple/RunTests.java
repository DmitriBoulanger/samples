package de.dbo.samples.cucumber.simple;

import org.junit.runner.RunWith;

/*
 * about format below:
 * the pretty format below: http://cukes.info/gherkin.html
 * HOW-TO publish by Jenkins:
 * https://github.com/masterthought/jenkins-cucumber-jvm-reports-plugin-java
 */

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
 
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, format = {"pretty", "html:target/cucumber", "rerun:target/rerun.txt"})
public class RunTests {
	
}
