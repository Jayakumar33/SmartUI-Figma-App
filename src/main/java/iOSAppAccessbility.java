import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URL;

public class iOSAppAccessbility {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-ios" : System.getenv("LT_APP_ID");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void iOSApp1(String device, String version, String platform) {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build", "Java TestNG");
            capabilities.setCapability("name", platform + " " + device + " " + version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", app_id); //Enter your app url
            capabilities.setCapability("visual", true);
            capabilities.setCapability("iosLiveInteraction", true);
            capabilities.setCapability("accessibility", true);
            capabilities.setCapability("network", true);

            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";
            driver = new AppiumDriver(new URL(hub), capabilities);

            WebDriverWait Wait = new WebDriverWait(driver, 30);

            //Changes the color of the text
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("color"))).click();
            Thread.sleep(1000);

            //Changes the text to "Proverbial"
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Text"))).click();
            Thread.sleep(1000);

            // Command to scan accessbility scan
            driver.executeScript("lambda-accessibility-scan");

            //Toast will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("toast"))).click();
            Thread.sleep(1000);

            //Notification will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("notification"))).click();
            Thread.sleep(4000);

            // Command to scan accessbility scan
            driver.executeScript("lambda-accessibility-scan");

            //Opens the geolocation page
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("geoLocation"))).click();
            Thread.sleep(4000);

            //Takes back
            driver.navigate().back();

            //Takes to speedtest page
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("speedTest"))).click();
            Thread.sleep(4000);
            driver.navigate().back();

            // Command to scan accessbility scan
            driver.executeScript("lambda-accessibility-scan");

            //Opens the browser
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Browser"))).click();
            Thread.sleep(1000);
            MobileElement url = (MobileElement) driver.findElementByAccessibilityId("url");
            url.click();
            url.sendKeys("https://www.lambdatest.com");
            Wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("find"))).click();
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace();
        }

    }
}
