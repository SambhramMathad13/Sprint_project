package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    // features = "src/test/resources/features/register_button.feature",    // uncomment this for running tests for registration button in student registration form and comment the above line.
    glue = {"stepdefinitions","hooks"},
    plugin = {"pretty", "html:target/cucumber-report.html"},
    // plugin = {"pretty", "html:target/cucumber-reportForButton.html"},    // uncoment this line to get reports for register button and comment the above line
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
