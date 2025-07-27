package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import org.openqa.selenium.By;

public class RegistrationPage {

    WebDriver driver;
    WebDriverWait wait;
    //Constructor
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 sec wait
    }

    //Locators
    
    By txt_studentName_loc = By.xpath("//input[@id='studentName']");
    By txt_mobileNumber_loc = By.xpath("//input[@id='mobileNumber']");
    By txt_emailId_loc = By.xpath("//input[@id='emailId']");
    By num_cgpa_loc = By.xpath("//input[@id='cgpa']");
    By txt_dept_loc = By.xpath("//input[@id='deptName']");
    By num_backlog_loc = By.xpath("//input[@id='backlogCount']");
    By btn_register_loc = By.xpath("//button[@id='register']");
    By btn_student_loc = By.xpath("//*[@id='student']");
    By btn_faculty_loc = By.xpath("//*[@id='faculty']");
    By btn_registerLink_loc = By.xpath("//*[@id='registerLink']");
    
    
    By error_msg = By.xpath("//*[@id='error']");
    By result_msg = By.xpath("//*[@id='result']");
    
    //Action methods
    public void setStudentName(String studentName){
        driver.findElement(txt_studentName_loc).sendKeys(studentName);
    }

    public void setMobileNumber(String mobileNumber){
        driver.findElement(txt_mobileNumber_loc).sendKeys(mobileNumber);
    }

    public void setEmailId(String emailId){
        driver.findElement(txt_emailId_loc).sendKeys(emailId);
    }

    public void setCgpa(String cgpa){
        driver.findElement(num_cgpa_loc).sendKeys(cgpa);
    }

    public void setDepartment(String department){
        driver.findElement(txt_dept_loc).sendKeys(department);
    }

    public void setBacklogCount(String backlogCount){
        driver.findElement(num_backlog_loc).sendKeys(backlogCount);
    }
    
    public void clickRegister(){
        driver.findElement(btn_register_loc).click();
    }
    public void clickStudent(){
        wait.until(ExpectedConditions.elementToBeClickable(btn_student_loc)).click();
    }
    
    public void clickFaculty(){
        wait.until(ExpectedConditions.elementToBeClickable(btn_faculty_loc)).click();
    }
    public void clickregisterLink(){
        wait.until(ExpectedConditions.elementToBeClickable(btn_registerLink_loc)).click();
    }


    public String getDisplayedMessage() {
    try {
        Thread.sleep(1000);

        String errorText = driver.findElement(error_msg).getText().trim();
        String resultText = driver.findElement(result_msg).getText().trim();

        if (!errorText.isEmpty()) {
            return errorText;
        } else if (!resultText.isEmpty()) {
            return resultText;
        } else {
            return ""; // No message present
        }

    } catch (Exception e) {
        return "";
    }
}
}