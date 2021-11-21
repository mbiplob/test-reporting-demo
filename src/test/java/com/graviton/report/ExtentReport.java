package com.graviton.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.util.Objects;

public class ExtentReport {
    private ExtentReport(){

    }

    public static ExtentTest test;
    private static ExtentReports extent;

    public static void initReport(){
        if(Objects.isNull(extent)){
            extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report/extent/demo-extent-report.html").viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY}).apply();
            extent.attachReporter(sparkReporter);
            sparkReporter.config().setReportName("Graviton demo test report");
            sparkReporter.config().setDocumentTitle("Graviton demo test report");
            extent.setSystemInfo("Author", "Graviton Technologies");
        }
    }

    public static void createTestCase(String testCaseName){
        test = extent.createTest(testCaseName);
        ExtentManager.setExtentTest(test);
    }

    public static void flushReport(){
        extent.flush();
    }

}
