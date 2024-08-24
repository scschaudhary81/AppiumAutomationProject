package Code;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;



public class GenericDriver {



    AndroidDriver driver;



        public AndroidDriver getDriver(){
        return this.driver;
        }
       public GenericDriver(String deviceName,String udid,String platformName,String platformVersion,String appActivity,String appPackage,String automationName){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium:deviceName",deviceName);
            capabilities.setCapability("appium:udid",udid);
            capabilities.setCapability("platformName",platformName);
            capabilities.setCapability("appium:platformVersion",platformVersion);
            capabilities.setCapability("appium:appPackage",appPackage);
            capabilities.setCapability("appium:appActivity",appActivity);
            capabilities.setCapability("appium:automationName",automationName);
           capabilities.setCapability("appium:newCommandTimeout", "10000");


            try {
                URL url = new URL("http://127.0.0.1:4723/");
                this.driver = new AndroidDriver(url,capabilities);
            }catch (MalformedURLException exception){
                exception.printStackTrace();
            }

        }

        public void stopDriver(){
           this.driver.quit();
        }
}
