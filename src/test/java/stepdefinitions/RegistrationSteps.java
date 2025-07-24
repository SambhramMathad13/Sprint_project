package stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.RegistrationPage;
import pages.LoginPage;
import utils.BaseClass;
import utils.ConfigReader;

public class RegistrationSteps {
    WebDriver driver = BaseClass.getDriver();
    RegistrationPage regPage = new RegistrationPage(driver);

    @Given("the user is on the registration page")
    
    public void the_user_is_on_registration_page() {
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl);

        // Login steps using POM
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername(ConfigReader.getProperty("username"));
        loginPage.setPassword(ConfigReader.getProperty("password"));
        loginPage.clickSignIn();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        regPage.clickFaculty();
                try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        regPage.clickStudent();
                try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        regPage.clickregisterLink();
        System.out.println("User is now on registration page after login.");
    }

    @When("the user enters {string} into the student name field")
    public void the_user_enters_into_student_name(String name) {
        regPage.setStudentName(name);
    }

    @And("fills all other fields with valid data")
    public void fill_other_fields() {
        regPage.setMobileNumber("9876543210");
        regPage.setEmailId("test@example.com");
        regPage.setCgpa("8.5");
        regPage.setDepartment("CSE");
        regPage.setBacklogCount("1");
    }


    @And("clicks the register button")
    public void clicks_register_button() {
        regPage.clickRegister();
    }

@Then("the result should be {string}")
public void validate_dynamic_result(String expectedMessage) {
    String actualMessage = regPage.getDisplayedMessage();

    System.out.println("Expected: " + expectedMessage +"||");
    System.out.println("Actual: " + actualMessage +"||");

    Assert.assertTrue(actualMessage.startsWith(expectedMessage),
    "Expected message to start with: '" + expectedMessage + "', but got: '" + actualMessage + "'");
}
}
