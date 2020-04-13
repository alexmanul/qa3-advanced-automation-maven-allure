package Tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.sleep;

public class AppiumTest {

    DesiredCapabilities capabilities;
    WebDriver driver;

    @Before
    public void setCapabilities() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S9");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
    }

    @Test
    public void TEST_APPIUM_01() throws MalformedURLException {
        capabilities.setCapability("app", "C:\\AUTOMATION\\APPIUM\\LoginApp.apk");
        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        System.out.println("App launched");

        driver.findElement(By.id("textInputEditTextEmail")).sendKeys("myemail@email.com");
        driver.findElement(By.id("textInputEditTextPassword")).sendKeys("myemail@email.com");
        driver.findElement(By.id("appCompatButtonLogin")).click();
    }

    @Test
    public void TEST_APPIUM_02() {
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("chromedriverExecutable", "C:\\AUTOMATION\\chromedriver.exe");

        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(url, capabilities);
        driver.get("https://ru.aliexpress.com/");

        driver.findElement(By.xpath("//input[@class='_2Emsu']")).click();
        driver.findElement(By.xpath("//input[@class='_2Emsu']")).sendKeys("tattoo");

        driver.findElement(By.xpath("//input[@class='_2Emsu']")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        sleep(3000);
        driver.findElementsByXPath("//span[@tabindex='-1']").get(11).click();
        driver.findElementsByXPath("//input[@type='number']").get(0).sendKeys("10");
        driver.findElementsByXPath("//input[@type='number']").get(1).sendKeys("200");
        driver.findElementsByXPath("//li[@tabindex='-1']").get(7).click();
        sleep(5000);
    }
}
