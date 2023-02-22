package by.teachmeskills.util.testng;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        System.out.printf("======================================== STARTING TEST %s ========================================%n", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.printf("======================================== FINISHED TEST %s Duration: %ss ========================================%n", result.getName(),
                          getExecutionTime(result));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.printf("======================================== SKIPPING TEST %s ========================================%n", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    private long getExecutionTime(ITestResult result) {
        return TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis());
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n", result.getName(),
                          getExecutionTime(result));
        takeScreenshot(result);
    }

    private void takeScreenshot(ITestResult result) {
        ITestContext context = result.getTestContext();
        try {
            WebDriver driver = (WebDriver) context.getAttribute("driver");
            if (driver != null) {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.moveFile(scrFile,
                                   new File(System.getProperty("user.dir") + "/src/test/resources/screenshots/"
                                                    + result.getMethod().getMethodName() + ".png"));
            }
        } catch (NoSuchSessionException | IllegalStateException | IOException ex) {
            System.out.println("Screenshot was not created because of error:\n" + ex.getLocalizedMessage());
        }
    }
}
