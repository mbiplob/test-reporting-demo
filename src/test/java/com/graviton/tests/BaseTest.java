package com.graviton.tests;

import com.graviton.utils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static String baseURL= "https://demo.broadleafcommerce.org/";
    public WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    @BeforeClass
    public void setUp(){
        Log.info("Opening Browser...");
        initilization();
    }

    @AfterClass
    public void tearDown(){
        Log.info("Closing Browser...");
        driver.quit();
    }

    //opening browser
    public void initilization(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--headless");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get(baseURL);

    }
}
