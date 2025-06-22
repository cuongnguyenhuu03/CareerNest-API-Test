package careerNest.listener;

import careerNest.helper.LogUtils;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends AllureTestNg implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        LogUtils.info("======== Test Suite started: " + context.getName() + " ========");
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("======== Test Suite finished: " + context.getName() + " ========");
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("---------- Test started: " + result.getMethod().getMethodName() + " ----------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test PASSED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test FAILED: " + result.getMethod().getMethodName());
        LogUtils.error("Reason: " + result.getThrowable());
        // Optional: Ghi response hoặc chụp log JSON ở đây nếu dùng Rest Assured
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("Test SKIPPED: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}
