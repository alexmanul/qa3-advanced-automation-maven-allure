package Steps;

import MYSQL.SQLHelper;
import Utils.CommonApproach.Pages;
import Utils.DriverSingleton;
import Utils.Screenshots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class Hooks {
    static String featureName;
    static String scenarioName;
    private static ExtentTest extentTest;
    final SQLHelper sqlHelper = new SQLHelper();
    private final SharedContext context;
    private WebDriver driver;
    private ExtentReports extent;

    public Hooks(SharedContext context) {
        this.context = context;
    }

    @Before("@UI")
    public void before(Scenario scenario) {
        driver = DriverSingleton.getInstance();
        Screenshots screen = new Screenshots(driver);
        scenarioName = screen.getNameOfScenario(scenario);
        featureName = screen.getNameOfFeature(scenario);
        extent = DriverSingleton.getExtent();
        extentTest = DriverSingleton.getExtentTest(featureName, scenarioName);
        Pages.clearCache();
        Pages.setWebDriverAndWait();
        context.scenario = scenario;
    }

    @After("@UI")
    public void tearDown(Scenario scenario) {
        extent.endTest(extentTest);
        extent.flush();
        DriverSingleton.destroySingletonInstance();
    }

    @After("@DropDBTableAgents")
    public void dropDBTableAgents() throws IOException, SQLException {
        String query = "DROP TABLE AGENTS";
        Connection connection = sqlHelper.createConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        log.info("Database is dropped");
        connection.close();
    }

    @Before("@CreateDBTableAgents")
    public void createDBTableAgents() throws IOException, SQLException {
        sqlHelper.readSQLFromFile();
        log.info("Database table 'AGENTS' is created");
    }
}
