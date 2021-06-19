package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import freemarker.template.SimpleDate;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting {

    public static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTestThreadSafe = new ThreadLocal<ExtentTest>();
    public static File reportFile = null;

    static {
        createReporter();
    }


    public static ExtentReports createReporter() {
        if (extentReports == null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy_HHmmss");

            String filName = "executionReports/TestExecutionReport_" + formatter.format(new Date()) + ".html";
            reportFile = new File(filName);

            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportFile);
            extentSparkReporter.config().setReportName(Config.getValue("reportName"));
            extentSparkReporter.config().setDocumentTitle("documentTitle");
            extentSparkReporter.config().setEncoding("encoding");
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setTimelineEnabled(true);

            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);
            extentReports.setSystemInfo("Author", Config.getValue("author"));
            extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.vesion"));
            extentReports.setSystemInfo("Application Environment", Config.getValue("env"));

        }
        return extentReports;
    }

    public static ExtentTest startTest(String name, String description) {
        ExtentTest extentTest = extentReports.createTest(name, description);
        extentTestThreadSafe.set(extentTest);
        return extentTestThreadSafe.get();
    }

    public static ExtentTest getThreadSafeTest() {
        return extentTestThreadSafe.get();
    }

    public static synchronized void endTest() {
        if (extentTestThreadSafe.get() != null)
            getThreadSafeTest().getExtent().flush();
    }

    public static synchronized void pass(String message) {
        if (getThreadSafeTest() != null)
            getThreadSafeTest().pass(message);
    }

    public static synchronized void fail(String message) {
        if (getThreadSafeTest() != null)
            getThreadSafeTest().fail(message);
    }

    public static synchronized void fail(String message, Throwable th) {
        if (getThreadSafeTest() != null)
            getThreadSafeTest().fail(message);
        getThreadSafeTest().fail("Exception Thrown : " + th);
    }

    public static synchronized void warn(String message) {
        if (getThreadSafeTest() != null)
            getThreadSafeTest().warning(message);
    }

    public static synchronized void skip(String message) {
        if (getThreadSafeTest() != null)
            getThreadSafeTest().skip(message);
    }

    public static synchronized void testPassed() {
        if (getThreadSafeTest() != null) {
            Markup markup = MarkupHelper.createLabel("TEST PASSED: " + getThreadSafeTest().toString(), ExtentColor.GREEN);
            getThreadSafeTest().pass(markup);
            endTest();
        }
    }

    public static synchronized void testSkipped() {
        if (getThreadSafeTest() != null) {
            Markup markup = MarkupHelper.createLabel("TEST WARNING: " + getThreadSafeTest().toString(), ExtentColor.AMBER);
            getThreadSafeTest().warning(markup);
            endTest();
        }
    }

    public static synchronized void testFailed(Throwable th) {
        if (getThreadSafeTest() != null) {
            Markup markup = MarkupHelper.createLabel("TEST FAILED: " + getThreadSafeTest().toString(), ExtentColor.RED);
            getThreadSafeTest().fail(markup);
            String base64ScreenShpt=Browser.takeScreenshot();
            getThreadSafeTest().fail(th,MediaEntityBuilder.createScreenCaptureFromBase64String(base64ScreenShpt).build());
            endTest();
        }
    }


    public static void passOrFail(Boolean result, String message) {
        if (result)
            pass(message);
        else {
            fail(message);
        }
    }
}
