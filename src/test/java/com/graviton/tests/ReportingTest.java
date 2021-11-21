package com.graviton.tests;

import com.graviton.report.ExtentLogger;
import com.graviton.utils.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReportingTest extends BaseTest{

    @Test
    public void demoTest(){
        Log.info("Hello World...");
        Assert.assertEquals("Hello","Hello");
        ExtentLogger.info("JD vai is from MN");
    }
}
