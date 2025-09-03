package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Automation Test Report");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Sayan");

        return extent;
    }
}
