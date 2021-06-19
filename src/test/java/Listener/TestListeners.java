package Listener;

import core.Reporting;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;


public class TestListeners implements ITestListener {
    ITestContext context;

    public void onTestStart(ITestResult result) {
        System.out.println("STart");
        Test test = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        Reporting.startTest(context.getName(), test.description());


    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Success");
    }

    public void onTestFailure(ITestResult result) {
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        System.out.println(context.getSuite().getName() + "  suite Started");
    }

    public void onFinish(ITestContext context) {
        System.out.println(context.getSuite().getName() + "  suite completed");
    }
}
