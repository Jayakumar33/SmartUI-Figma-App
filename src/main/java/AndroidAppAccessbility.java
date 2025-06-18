import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;

public class AndroidAppAccessbility {

    String userName = System.getenv("LT_USERNAME") == null ? "jayak" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "dVeaZxDvDpANKUhNP0uPAqlcaLQg5vQmNdP6iJF78WtAimkdiF" : System.getenv("LT_ACCESS_KEY"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://proverbial-android" : System.getenv("LT_APP_ID");      //Enter your LambdaTest App ID at the place of lt://proverbial-android
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

//    // Secondary Account.
//    String userName = "jaimech61";
//    String accessKey = "ClE0cJUhgpNLhilx5JxB811hFwry66rdg5Sy2W61y1tc8lSm0p";




    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build", "AccessbilityDemo-App");
            capabilities.setCapability("name", platform + " " + device + " " + version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", "lt://APP1016060381745593333862555");
            capabilities.setCapability("accessibility", true);
            capabilities.setCapability("network", true);


            System.out.println(userName);

            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";

            driver = new AppiumDriver(new URL(hub), capabilities);
            System.out.println(hub);


            Thread.sleep(10000);

            // Command to scan accessbility scan
            driver.executeScript("lambda-accessibility-scan");

            driver.quit();
        } catch (Exception e) {
            driver.quit();
            e.printStackTrace();
        }

    }
}
