package Utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DriverSingleton {
    private static RemoteWebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest extentTest;

    private static Map<String, ExtentTest> extentTestDictionary;

    private static void setInstance() {
        try {
            driver = new RemoteWebDriver(new URL(getSeleniumGridURL()), BrowserProperties.getCapabilities());

            String reportsFileToSave = System.getProperty("user.dir")
                    + File.separator + TestProperties.getProperty("screenshots.folder1")
                    + File.separator + TestProperties.getProperty("screenshots.folder2")
                    + File.separator + TestProperties.getProperty("report.html");

            extent = new ExtentReports(reportsFileToSave, false);
            extentTestDictionary = new HashMap<String, ExtentTest>();

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait
                    (Integer.parseInt(TestProperties.getProperty("selenium.implicit.wait")), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout
                    (Integer.parseInt(TestProperties.getProperty("selenium.page.wait")), TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout
                    (Integer.parseInt(TestProperties.getProperty("selenium.js.wait")), TimeUnit.SECONDS);

            printOutBrowserInfo();
            printSessionID();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RemoteWebDriver getInstance() {
        if (driver == null) {
            setInstance();
        }
        return driver;
    }

    public static ExtentTest getExtentTest(String featureName, String scenarioName) {
        String extentTestKey = featureName + "//" + scenarioName;
        if (extentTestDictionary.containsKey(extentTestKey)) {
            extentTest = extentTestDictionary.get(extentTestKey);
        } else {
            extentTest = extent.startTest(extentTestKey, scenarioName);
            extentTestDictionary.put(extentTestKey, extentTest);
        }
        return extentTest;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void destroySingletonInstance() {
        if (driver == null) {
            return;
        }
        driver.quit();
        driver = null;
    }

    private static void printOutBrowserInfo() {
        Capabilities capabilities = driver.getCapabilities();
        log.info("Browser: " + capabilities.getBrowserName());
        log.info("Browser version: " + capabilities.getVersion());
        log.info("Browser platform: " + capabilities.getPlatform());
    }

    private static void printSessionID() {
        log.info("Session ID: " + getInstance().getSessionId());
    }

    public static String getSeleniumGridURL() {
        return TestProperties.getProperty("selenium.grid.local");
    }
}
