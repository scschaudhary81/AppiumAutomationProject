package Tests;

import Code.CalculatorApp;
import Code.GenericDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class AppiumTest {
    GenericDriver genericDriver;
    Properties properties;
    String deviceName;
    String uDid;
    String platformName;
    String platformVersion;
    String appPackage;
    String appActivity;
    String automationName;


    @BeforeTest
    public void startTest() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("Config.properties"));
        deviceName = properties.getProperty("deviceName");
        uDid = properties.getProperty("uDid");
        platformName = properties.getProperty("platformName");
        platformVersion = properties.getProperty("platformVersion");
        appPackage = properties.getProperty("appPackage");
        appActivity = properties.getProperty("appActivity");
        automationName  = properties.getProperty("automationName");
        genericDriver = new GenericDriver(deviceName, uDid, platformName, platformVersion, appActivity, appPackage,automationName);
    }

    @AfterMethod
    public void afterMethod() {
        genericDriver.stopDriver();
    }

    @Test
    public void Test1() {
        genericDriver.getDriver().findElement(AppiumBy.xpath(String.format(CalculatorApp.button,"9"))).click();
        genericDriver.getDriver().findElement(AppiumBy.xpath(String.format(CalculatorApp.button,"Division"))).click();
        genericDriver.getDriver().findElement(AppiumBy.xpath(String.format(CalculatorApp.button,"2"))).click();
        genericDriver.getDriver().findElement(AppiumBy.xpath(String.format(CalculatorApp.button,"Calculation"))).click();
        String result = genericDriver.getDriver().findElement(AppiumBy.androidUIAutomator(CalculatorApp.resultDisplay)).getText().replace(" Calculation result","");
        Assert.assertEquals(Double.valueOf(result),4.5,"Division value is not matching");
    }


}
