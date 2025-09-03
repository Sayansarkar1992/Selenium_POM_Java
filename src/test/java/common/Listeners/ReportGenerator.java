package common.Listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportGenerator implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        try {
            String dirPath = System.getProperty("user.dir") + "/test_reports";
            File directory = new File(dirPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String reportPath = System.getProperty("user.dir") + "/test_reports/CustomTestNGReport.html";
            BufferedWriter writer = new BufferedWriter(new FileWriter(reportPath));

            writer.write("<html><head><title>TestNG Custom Report</title></head><body>");
            writer.write("<h1>Test Execution Summary</h1>");

            for (ISuite suite : suites) {
                writer.write("<h2>Suite: " + suite.getName() + "</h2>");
                Map<String, ISuiteResult> suiteResults = suite.getResults();

                for (ISuiteResult result : suiteResults.values()) {
                    ITestContext context = result.getTestContext();
                    writer.write("<h3>Test: " + context.getName() + "</h3>");
                    writer.write("<table border='1'>");
                    writer.write("<tr><th>Test Method</th><th>Status</th><th>Screenshot</th><th>Start Time</th><th>End Time</th></tr>");

                    for (ITestResult tr : context.getPassedTests().getAllResults()) {
                        writeRow(writer, tr, "PASS", null);
                    }

                    for (ITestResult tr : context.getFailedTests().getAllResults()) {
                        String screenshotPath = getScreenshotPath(tr);
                        writeRow(writer, tr, "FAIL", screenshotPath);
                    }

                    for (ITestResult tr : context.getSkippedTests().getAllResults()) {
                        writeRow(writer, tr, "SKIP", null);
                    }

                    writer.write("</table><br>");
                }
            }

            writer.write("</body></html>");
            writer.close();
            System.out.println("✅ Custom report generated: " + reportPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeRow(BufferedWriter writer, ITestResult result, String status, String screenshotPath) throws IOException {
        writer.write("<tr>");
        writer.write("<td>" + result.getMethod().getMethodName() + "</td>");
        writer.write("<td>" + status + "</td>");

        if (screenshotPath != null) {
            writer.write("<td><a href='" + screenshotPath + "' target='_blank'>View</a><br>");
            writer.write("<img src='" + screenshotPath + "' width='200'/></td>");
        } else {
            writer.write("<td>-</td>");
        }

        writer.write("<td>" + new Date(result.getStartMillis()) + "</td>");
        writer.write("<td>" + new Date(result.getEndMillis()) + "</td>");
        writer.write("</tr>");
    }

    private String getScreenshotPath(ITestResult result) {
        // Screenshot name must match the pattern used in ScreenshotListener
        String methodName = result.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(result.getEndMillis());
        String fileNamePattern = methodName + "_" + timestamp + ".png";

        File screenshotDir = new File(System.getProperty("user.dir") + "/test_reports/screenshots");
        File[] screenshots = screenshotDir.listFiles();

        if (screenshots != null) {
            for (File file : screenshots) {
                if (file.getName().contains(methodName)) {
                    return "screenshots/" + file.getName(); // Relative path
                }
            }
        }
        return null;
    }

}
