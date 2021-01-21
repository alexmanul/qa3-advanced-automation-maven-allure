package Utils.CommonApproach;

import Pages.BasePage;
import Pages.P100MainPage;
import Pages.P101RegistrationPage;
import Steps.BaseSteps;
import Utils.DriverSingleton;
import Utils.TestProperties;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Pages extends BaseSteps {
    private final static String MENU = "MENU";
    private final static String P100 = "P100";
    private final static String P101 = "P101";

    private final static Map<String, BasePage> cache = new HashMap<>();
    private static RemoteWebDriver driver;
    private static WebDriverWait wait;

    private static void getPageObjectForSpecificPage(String screen) throws Exception {
        switch (screen) {
            case "P100" -> cache.put(P100, new P100MainPage(driver, wait));
            case "P101" -> cache.put(P101, new P101RegistrationPage(driver, wait));
            default -> throw new Exception("Page object is not found for the " + screen);
        }
    }

    public static BasePage getCachedPageObject(String page) throws Exception {
        log.debug("Cached pages count is " + cache.size());
        if (!cache.containsKey(page)) {
            getPageObjectForSpecificPage(page);
        }
        return cache.get(page);
    }

    public static void clearCache() {
        log.debug("Clearing cache");
        cache.clear();
    }

    public static void setWebDriverAndWait() {
        if (driver == null || driver.getSessionId() == null) {
            driver = DriverSingleton.getInstance();
            wait = new WebDriverWait(driver, Integer.parseInt(TestProperties.getProperty("selenium.explicit.wait")));
        }
    }
}