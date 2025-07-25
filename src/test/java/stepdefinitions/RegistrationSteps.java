package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.*;
import pages.RegistrationPage;
import pages.LoginPage;
import utils.BaseClass;
import utils.ConfigReader;
import utils.ExtentReportManager;

public class RegistrationSteps {
    WebDriver driver = BaseClass.getDriver();
    RegistrationPage regPage = new RegistrationPage(driver);
    ExtentTest test = ExtentReportManager.getTest();

    @Given("the user is on the registration page")
    public void the_user_is_on_registration_page() {
        try {
            String baseUrl = ConfigReader.getProperty("baseUrl");
            driver.get(baseUrl);

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setUsername(ConfigReader.getProperty("username"));
            loginPage.setPassword(ConfigReader.getProperty("password"));
            loginPage.clickSignIn();

            Thread.sleep(3000);

            regPage.clickFaculty();
            Thread.sleep(1000);

            regPage.clickStudent();
            Thread.sleep(1000);

            regPage.clickregisterLink();

            test.log(Status.PASS, "User navigated to the registration page successfully.");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to reach registration page: " + e.getMessage());
            Assert.fail("Navigation failed: " + e.getMessage());
        }
    }

    @When("the user enters {string} into the student name field")
    public void the_user_enters_into_student_name(String name) {
        regPage.setStudentName(name);
        test.log(Status.INFO, "Entered student name: " + name);
    }

    @And("fills all other fields with valid data")
    public void fill_other_fields() {
        regPage.setMobileNumber("9876543210");
        regPage.setEmailId("test@example.com");
        regPage.setCgpa("8.5");
        regPage.setDepartment("CSE");
        regPage.setBacklogCount("1");

        test.log(Status.INFO, "Filled other fields with valid data.");
    }

    @And("clicks the register button")
    public void clicks_register_button() {
        regPage.clickRegister();
        test.log(Status.INFO, "Clicked the register button.");
    }


    @Then("the result should be {string}")
public void validate_dynamic_result(String expectedMessage) {
    String actualMessage = regPage.getDisplayedMessage();

    test.log(Status.INFO, "Expected: " + expectedMessage);
    test.log(Status.INFO, "Actual: " + actualMessage);

    try {
        Assert.assertTrue(actualMessage.startsWith(expectedMessage),
            "Expected message to start with: '" + expectedMessage + "', but got: '" + actualMessage + "'");
        test.log(Status.PASS, "Validation Passed. Actual message starts with expected message.");
    } catch (AssertionError e) {
        test.log(Status.FAIL, "Validation Failed: " + e.getMessage());
        throw e; // rethrow to make sure the test still fails
    }
}
}
