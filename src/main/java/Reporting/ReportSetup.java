package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ReportSetup implements ITestListener {

    public static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        String filename = ExtentReport.getReportTimeStamp();
        String filepath = System.getProperty("user.dir") + "\\Reports\\" + filename;
        extentReports = ExtentReport.createInstance(filepath, "API Automation Report", "Automation Reports");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest("Test" + result.getTestClass().getName() + "-" + result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestFailure(ITestResult result) {
        ExtentReport.customizedLogsFailedDetails(result.getThrowable().getMessage());
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",", "<br>");
        String formattedStackTrace =
                "<details>\n" +
                        "<summary> Click to see the Exception Details</summary>\n" +
                        "" + stackTrace + "\n" +
                        "</details>\n";
        ExtentReport.customizedLogsExceptionDetails(formattedStackTrace);
    }
}

