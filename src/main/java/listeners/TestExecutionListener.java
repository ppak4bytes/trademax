package listeners;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;


public class TestExecutionListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
       try{makeScreenshot(iTestResult.getMethod().getMethodName());}
       catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try{makeScreenshot(iTestResult.getMethod().getMethodName());}
        catch (IOException e){e.printStackTrace();}
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Attachment(value = "{methodName}", type = "image/png")
    private byte[] makeScreenshot(String methodName) throws IOException {
        File selenideScreenshot = Screenshots.takeScreenShotAsFile();
        return selenideScreenshot !=null ? Files.toByteArray(selenideScreenshot) : new byte[]{};
    }
}
