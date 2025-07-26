package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.en.*;
import pages.RegistrationPage;
import utils.BaseClass;
import utils.ConfigReader;
import utils.ExtentReportManager;

public class RegistrationButtonSteps {

    WebDriver driver = BaseClass.getDriver();
    RegistrationPage regPage = new RegistrationPage(driver);
    ExtentTest test = ExtentReportManager.getTest();


    @When("the user clicks the register button without filling the form")
    public void user_clicks_register_without_data() {
        regPage.clickRegister();
        test.log(Status.INFO, "Clicked the register button without filling any data.");
    }

    @When("the user enters all the fiels with valid data")
    public void enter_all_valid_fields_from_properties() {
        regPage.setStudentName(ConfigReader.getProperty("studentName"));
        regPage.setMobileNumber(ConfigReader.getProperty("mobile"));
        regPage.setEmailId(ConfigReader.getProperty("email"));
        regPage.setCgpa(ConfigReader.getProperty("cgpa"));
        regPage.setDepartment(ConfigReader.getProperty("department"));
        regPage.setBacklogCount(ConfigReader.getProperty("backlog"));

        test.log(Status.INFO, "Filled all fields using valid data from property file.");
    }
}
