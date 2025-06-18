import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IOS {
    private RemoteWebDriver driver;
    private String Status = "Passed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = "jayak";
        String authkey = "dVeaZxDvDpANKUhNP0uPAqlcaLQg5vQmNdP6iJF78WtAimkdiF";
        String hub = "@mobile-hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "ios");
        caps.setCapability("deviceName", "iPhone 15 Pro");
        caps.setCapability("platformVersion", "17");
        caps.setCapability("isRealMobile", true);
        caps.setCapability("build", "Testing 1");
        caps.setCapability("name", m.getName() + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");
        //   caps.setCapability("region", "eu");
        //  caps.setCapability("devicelog", true);
        //  caps.setCapability("autoAcceptAlerts", true);
        //    caps.setCapability("autoGrantPermissions", true);
        //    caps.setCapability("geoLocation", "US");
        // caps.setCapability("dedicatedProxy", true);

        driver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey + hub), caps);
    }

    @Test
    public void basicTest() throws InterruptedException {
        driver.get("https://staging.wecheer.me/loyalty");
        WebDriverWait wait = new WebDriverWait(driver, 20);

        System.out.println("URL at start: " + driver.getCurrentUrl());

        // Open the menu

        Thread.sleep(2000);  // Allow some time for navigation

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }
}