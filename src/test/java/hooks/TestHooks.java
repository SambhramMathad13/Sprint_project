package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;
import utils.ExtentReportManager;

import java.io.File;
import java.io.IOException;

public class TestHooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentReportManager.createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        WebDriver driver = BaseClass.getDriver();

        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String screenshotDir = "screenshots";
            new File(screenshotDir).mkdirs();
            String fileName = scenario.getName().replaceAll(" ", "_") + ".png";
            String fullPath = screenshotDir + "/" + fileName;
            String relativePathForReport = "../screenshots/" + fileName;

            File dest = new File(fullPath);
            try {
                FileUtils.copyFile(src, dest);
                ExtentReportManager.getTest()
                    .fail("Scenario failed. Screenshot attached.")
                    .addScreenCaptureFromPath(relativePathForReport);
            } catch (IOException e) {
                ExtentReportManager.getTest()
                    .fail("Could not attach screenshot: " + e.getMessage());
            }

        } else {
            ExtentReportManager.getTest().pass("Scenario passed successfully.");
        }

        ExtentReportManager.getExtentReports().flush();
        BaseClass.closeDriver();
    }
}