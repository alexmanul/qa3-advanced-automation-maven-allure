package Steps;

import Utils.CommonApproach.Pages;
import Utils.DriverSingleton;
import Utils.Screenshots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    static String featureName;
    static String scenarioName;
    private static ExtentTest extentTest;
    private WebDriver driver;
    private ExtentReports extent;
    private Screenshots screen;

    @Before
    public void before(Scenario scenario) {
        driver = DriverSingleton.getInstance();
        screen = new Screenshots();
        scenarioName = screen.getNameOfScenario(scenario);
        featureName = screen.getNameOfFeature(scenario);
        extent = DriverSingleton.getExtent();
        extentTest = DriverSingleton.getExtentTest(featureName, scenarioName);
        Pages.clearCache();
        Pages.setWebDriverAndWait();
    }

    @After
    public void tearDown(Scenario scenario) {
        extent.endTest(extentTest);
        extent.flush();
        DriverSingleton.destroySingletonInstance();
    }
}
