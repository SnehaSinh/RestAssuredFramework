package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ExtentReport {
    public static ExtentReports extentReports;


    public static ExtentReports createInstance(String filename, String reportname, String documentTitle) {
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(filename);
        extentSparkReporter.config().setReportName(reportname);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setEncoding("utf-8");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        return extentReports;
    }

    public static String getReportTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formatedTime = dateTimeFormatter.format(localDateTime);
        return "Test Report" + formatedTime + ".html";


    }

    public static void customizedLogsPassDetails(String log) {
        ReportSetup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));

    }

    public static void customizedLogsFailedDetails(String log) {
        ReportSetup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));

    }

    public static void customizedLogsExceptionDetails(String log) {
        ReportSetup.extentTest.get().fail(log);

    }

    public static void customizedLogsInfoDetails(String log) {
        ReportSetup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));

    }

    public static void customizedLogsJSON(String json) {
        ReportSetup.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));

    }

    public static void customizedLogsWarningDetails(String log) {
        ReportSetup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));

    }

    public static void customizedheadersTable(List<Header> headersList) {
        String[][] headersArray = headersList.stream().map(header -> new String[]{header.getName(), header.getValue()})
                .toArray(String[][]::new);
        ReportSetup.extentTest.get().info(MarkupHelper.createTable(headersArray));
    }


}
