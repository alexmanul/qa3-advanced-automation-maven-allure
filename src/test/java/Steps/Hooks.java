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
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Hooks {
    static String featureName;
    static String scenarioName;
    private static ExtentTest extentTest;
    private WebDriver driver;
    private ExtentReports extent;
    private Screenshots screen;
    final SQLHelper sqlHelper = new SQLHelper();

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

    @After("@DropDBTableAgents")
    public void dropDBTableAgents() throws IOException, SQLException {
        String query = "DROP TABLE AGENTS";
        Connection connection = sqlHelper.createConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        //log.info("Database is dropped");
        connection.close();
    }

    @Before("@CreateDBTableAgents")
    public void createDBTableAgents() throws IOException, SQLException {
        sqlHelper.readSQLFromFile();
        //log.info("Database table 'AGENTS' is created");
    }
}
