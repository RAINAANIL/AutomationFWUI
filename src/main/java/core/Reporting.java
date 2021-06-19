package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Reporting {

    public static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTestThreadSafe = new ThreadLocal<>();

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

    public static synchronized void testFailed() {
        if (getThreadSafeTest() != null) {
            Markup markup = MarkupHelper.createLabel("TEST FAILED: " + getThreadSafeTest().toString(), ExtentColor.RED);
            getThreadSafeTest().fail(markup);
            endTest();
        }
    }
}
