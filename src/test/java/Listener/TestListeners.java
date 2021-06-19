package Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {

    public void onTestStart(ITestResult result) {
        System.out.println("STart");
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
