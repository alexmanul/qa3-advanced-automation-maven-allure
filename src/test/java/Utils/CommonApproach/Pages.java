package Utils.CommonApproach;

import Pages.BasePage;
import Pages.S100MainPage;
import Pages.S101RegistrationPage;
import Steps.BaseSteps;
import Utils.DriverSingleton;
import Utils.TestProperties;
//import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

////@Log4j
public class Pages extends BaseSteps {
    private final static String MENU = "MENU";
    private final static String P100 = "P100";
    private final static String P101 = "P101";

    private final static Map<String, BasePage> cache = new HashMap<>();
    private static RemoteWebDriver driver;
    private static WebDriverWait wait;

    private static void getPageObjectForSpecificPage(String screen) throws Exception {
        switch (screen) {
            case "P100":
                cache.put(P100, new S100MainPage(driver, wait));
                return;
            case "P101":
                cache.put(P101, new S101RegistrationPage(driver, wait));
                return;
            default:
                throw new Exception("Page object is not found for the " + screen);
        }
    }

    public static BasePage getCachedPageObject(String page) throws Exception {
        ////log.debug("Cached pages count is " + cache.size());
        if (!cache.containsKey(page)) {
            getPageObjectForSpecificPage(page);
        }
        return cache.get(page);
    }

    public static void clearCache() {
        ////log.debug("Clearing cache");
        cache.clear();
    }

    public static void setWebDriverAndWait() {
        if (driver == null || driver.getSessionId() == null) {
            driver = DriverSingleton.getInstance();
            wait = new WebDriverWait(driver, Integer.parseInt(TestProperties.getProperty("selenium.explicit.wait")));
        }
    }
}