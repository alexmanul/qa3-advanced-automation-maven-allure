package Steps;

import MYSQL.SQLHelper;
import MYSQL.SQLQueryCatalog;
import Utils.TestProperties;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
//import lombok.extern.log4j.Log4j;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

////@Log4j
public class DatabaseSteps {

    final SQLHelper sqlHelper = new SQLHelper();
    final SQLQueryCatalog sqlQueryCatalog = new SQLQueryCatalog();
    final Statement statement = sqlHelper.createStatement();

    public DatabaseSteps() throws SQLException {
    }

    @And("I verify '(.*)' table entity with '(.*)' agent name contains values")
    public void iVerifyTableInDBContainsExpectedValues(String tableDB, String name, DataTable table) throws Exception {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        String findQuery = sqlQueryCatalog.findAllByAgentName(tableDB, name);
        ResultSet resultSet = statement.executeQuery(findQuery);
        resultSet.next();

        for (Map<String, String> row : data) {
            String key = row.get("TABLE_KEY");
            String newValue = row.get("NEW_VALUE");
            assertThat(resultSet.getString(key)).isEqualTo(newValue);
        }
    }

    @And("I update '(.*)' table entity with '(.*)' agent name with new values")
    public void iUpdateTableInDBWithNewValues(String tableDB, String name, DataTable table) throws Exception {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String key = row.get("TABLE_KEY");
            String newValue = row.get("NEW_VALUE");
            String updateQuery = sqlQueryCatalog.updateAgentsByAgentName(tableDB, key, newValue, name);
            statement.executeUpdate(updateQuery);
        }
    }

    @And("I update '(.*)' table entity with '(.*)' agent name contains values")
    public void iUpdateTableInDBWithNewAgent(String tableDB, String name, DataTable table) throws Exception {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        List<String> newAgent = new ArrayList<>();
        for (Map<String, String> row : data) {
            String key = row.get("TABLE_KEY");
            String value = row.get("NEW_VALUE");
            newAgent.add("'" + value + "'");
        }

        String newValues = newAgent.toString().replaceAll("[{}\\[\\]]", "");
        //log.info(newValues);
        String insertQuery = sqlQueryCatalog.insertNewEntity(tableDB, newValues);
        statement.executeUpdate(insertQuery);
    }

    @And("I delete from '(.*)' table newly created entity with '(.*)' name")
    public void iUpdateTableInDBWithNewAgent(String tableDB, String name) throws Exception {
        String insertQuery = sqlQueryCatalog.deleteNewEntityByAgentName(tableDB, name);
        statement.executeUpdate(insertQuery);
    }

    @And("I create '(.*)' table in database")
    public void iUpdateTableInDBWithNewAgent(String tableDB) throws Exception {
        String fileName = TestProperties.getProperty("sql.create.table." + tableDB.toLowerCase() + "");
        //log.info(fileName);
        Connection connection = sqlHelper.createConnection();
        //log.debug("Connection established...");

        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader(fileName));

        sr.runScript(reader);
        sr.closeConnection();
    }
}
