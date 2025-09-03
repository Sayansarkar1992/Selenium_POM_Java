package common.Listeners;

import BaseTests.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import common.ExtentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        String methodName = result.getMethod().getMethodName();
        String screenshotPath = captureScreenshot(driver, methodName);

        test.get().fail(result.getThrowable());
        try {
            test.get().addScreenCaptureFromPath(screenshotPath, methodName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("⏭️ Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String captureScreenshot(WebDriver driver, String methodName) {
        String dirPath = System.getProperty("user.dir") + "/screenshots";
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + timeStamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(path);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}

