package Utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BrowserProperties extends DesiredCapabilities {

    private static BrowserProperties desiredCapabilities = null;

    private BrowserProperties() {
    }

    public static BrowserProperties getCapabilities() throws Exception {
        if (desiredCapabilities == null) {
            desiredCapabilities = new BrowserProperties();
            setCapabilities();
        }
        return desiredCapabilities;
    }

    private static void setCapabilities() throws Exception {
        if (System.getProperty("browser") == null) System.setProperty("browser", "chrome");
//        log.info
        switch (System.getProperty("browser")) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-web-security");
                Map<String, Object> prefs = new HashMap<>();
                String path = "C:+" + File.separator + "SeleniumGrid"; // Path to node on server side
                prefs.put("download.default_directory", path);
                chromeOptions.setExperimentalOption("prefs", prefs);
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                break;
            default:
                throw new Exception("Browser is not defined");
        }

        // Capability required to trigger JavaGuruPortal node
//        desiredCapabilities.setCapability("applicationName", "JavaGuruPortal");
        desiredCapabilities.setCapability("browserName", System.getProperty("browser"));
        desiredCapabilities.setCapability("version", "");
        desiredCapabilities.setCapability("platform", "");
//        desiredCapabilities.setCapability("platform", "Headless");
    }

}
