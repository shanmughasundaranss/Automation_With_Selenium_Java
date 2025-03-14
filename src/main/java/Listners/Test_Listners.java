package Listners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Browser_Factory.Browser_Drivers.driver;
import static ReUsable_Codes.Reusable_Library.Get_Value_From_Property_File;


public class Test_Listners implements ITestListener {

    public static   String screenshotDir;

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("Test started: " + result.getName(), true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Test passed: " + result.getName(), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("Test failed: " + result.getName(), true);
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            Reporter.log("Failure reason: " + throwable.getMessage(), true);
        }
        Reporter.log("Calling takeScreenshot method", true);
        takeScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test skipped: " + result.getName(), true);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Reporter.log("Test failed but within success percentage: " + result.getName(), true);
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("Test suite started: " + context.getName(), true);
        deleteOldScreenshots();
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("Test suite finished: " + context.getName(), true);
    }

    private void deleteOldScreenshots() {

        try {
             screenshotDir = Get_Value_From_Property_File("Screen_Shot_Folder_Location");
            String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            File directory = new File(screenshotDir);

            if (directory.exists()) {
                File[] files = directory.listFiles((dir, name) -> name.endsWith(".png") && !name.contains(todayDate));
                if (files != null) {
                    for (File file : files) {
                        if (file.delete()) {
                            Reporter.log("Deleted old screenshot: " + file.getName(), true);
                        } else {
                            Reporter.log("Failed to delete old screenshot: " + file.getName(), true);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void
    takeScreenshot(String testName) {
        Reporter.log("Attempting to take screenshot for test: " + testName, true);
        if (driver == null) {
            Reporter.log("WebDriver not initialized", true);
            return;
        }


        try {
            Files.createDirectories(Paths.get(screenshotDir));
        } catch (IOException e) {
            Reporter.log("Failed to create screenshot directory: " + e.getMessage(), true);
            return;
        }

        // Capture screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Generate timestamp
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);

            // Saving the screenshot in the screenshots folder
            String screenshotPath = screenshotDir + "\\" + testName + "_" + timestamp + ".png";
            FileHandler.copy(srcFile, new File(screenshotPath));
            Reporter.log("Screenshot saved at: " + screenshotPath, true);

            // Add screenshot to TestNG report
            String relativePath = "ScreenShot/" + testName + "_" + timestamp + ".png";
            Reporter.log("<a href='" + relativePath + "'> <img src='" + relativePath + "' height='100' width='100'/> </a>", true);
        } catch (IOException e) {
            Reporter.log("Failed to save screenshot: " + e.getMessage(), true);
        }
    }
}