import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

public class AndroidApp {

    String userName = System.getenv("LT_USERNAME") == null ? "" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-android" : System.getenv("LT_APP_ID");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");



    AppiumDriver driver;

    @Test
    @Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build", "Java TestNG");
            capabilities.setCapability("name", platform + " " + device + " " + version);
            capabilities.setCapability("deviceName", "Pixel 8 Pro");
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("devicelog", true);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("app", "lt://APP1016037841744106704404146");
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("autoAcceptAlerts", true);
            capabilities.setCapability("smartUI.project", "Figma-App");
            capabilities.setCapability("smartUI.build", "Execution-1");
            capabilities.setCapability("smartUI.cropStatusBar", true);
           capabilities.setCapability("smartUI.cropFooter", true);



            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";

            System.out.println(hub);
            driver = new AppiumDriver(new URL(hub), capabilities);
            System.out.println(hub);

            MobileElement color = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/color");
            //Changes color to pink
            color.click();
            Thread.sleep(1000);
            //Back to orginal color
            color.click();

            // SmartUI Screenshot capturing command
            ((JavascriptExecutor)driver).executeScript("smartui.takeScreenshot=homepage.png");
            System.out.println("Executed");

            driver.quit();
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace();
        }

    }
}

//npx smartui upload-figma-app designs.json