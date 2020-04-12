package Utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private WebDriver driver;
    ExtentReports extent;
    static ExtentTest extentTest;
    public static String featureName;
    public static String scenarioName;
    Screenshots screen;
}
