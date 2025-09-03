package common.Listeners;

import BaseTests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Get driver from test class
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            String methodName = result.getName();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = methodName + "_" + timestamp + ".png";

            String dirPath = System.getProperty("user.dir") + "/test_reports/screenshots";
            File directory = new File(dirPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(dirPath + "/" + screenshotName);

            try {
                FileUtils.copyFile(srcFile, destFile);
                System.out.println("📸 Screenshot saved: " + destFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
