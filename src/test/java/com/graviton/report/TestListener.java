package com.graviton.report;

import com.aventstack.extentreports.Status;
import com.graviton.tests.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

import static com.graviton.report.ExtentLogger.pass;
import static com.graviton.report.ExtentLogger.skip;
import static com.graviton.report.ExtentManager.getExtentTest;
import static com.graviton.report.ExtentReport.*;

public class TestListener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite){
        initReport();
    }

    @Override
    public void onFinish(ISuite suite){
        flushReport();
    }

    @Override
    public void onTestStart(ITestResult result){
        createTestCase(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result){
        pass(result.getMethod().getMethodName() + " has passed ");
    }

    @Override
    public void onTestFailure(ITestResult result){
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest)testClass).getDriver();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        getExtentTest().log(Status.FAIL, "Test Failed", getExtentTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult result){
        skip(result.getMethod().getMethodName() + " has Skipped ");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult){

    }
}
