package Tests;

import MYSQL.SQLHelper;
import lombok.extern.log4j.Log4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

//@Log4j
public class SQLTest {

    final String user = "test_user";
    final String password = "123456";
    final SQLHelper sqlHelper = new SQLHelper();

    @Before
    public void createDBTableAgents() throws IOException, SQLException {
        sqlHelper.readSQLFromFile();
//        log.info("Database table 'AGENTS' is created");
    }

    @After
    public void dropDBTableAgents() throws IOException, SQLException {
        String query = "DROP TABLE AGENTS";
        Connection connection = sqlHelper.createConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
//        log.info("Database is dropped");
        connection.close();
    }

    /**
     * Task 1 – Simple validation
     * First of all check the Database what it is inside it
     * Create a test where we find agent with name Lucida and assert all his data (Check his id, name, city, phone number)
     * Can be done through assert or SoftAsserts
     */
    @Test
    public void TEST_DB_01() throws SQLException {
        String query = "SELECT * FROM AGENTS WHERE AGENTS.AGENT_NAME = 'Lucida'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qa3", user, password);
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        resultSet.next();
        assertThat(resultSet.getString(1)).isEqualTo("A012");
        assertThat(resultSet.getString(2)).isEqualTo("Lucida");
        assertThat(resultSet.getString(3)).isEqualTo("San Jose");
        assertThat(resultSet.getString(4)).isEqualTo("0.12");
        assertThat(resultSet.getString(5)).isEqualTo("044-52981425");
        assertThat(resultSet.getString(6)).isEqualTo("");
        con.close();
    }

    /**
     * Task 2 – Add/Remove data
     * Create a test where we insert Agent – Test from Riga and PhoneNumber - 778-32556178
     * Create a test where we update Agent – Mukesh. Change that he is from Bangladesh and PhoneNumber - 777-11111111
     */
    @Test
    public void TEST_DB_02() throws SQLException {
        String insertQuery = "INSERT INTO AGENTS VALUES ('A013', 'Test', 'Riga', '0.66', '778-32556178', 'Latvia');";
        String revertQuery = "DELETE FROM AGENTS where AGENT_CODE = 'A013';";
        String findQuery = "SELECT * FROM AGENTS WHERE AGENTS.AGENT_NAME = 'Test'";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qa3", user, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(insertQuery);

        ResultSet resultSet = stmt.executeQuery(findQuery);
        resultSet.next();

        assertThat(resultSet.getString(1)).isEqualTo("A013");
        assertThat(resultSet.getString(2)).isEqualTo("Test");
        assertThat(resultSet.getString(3)).isEqualTo("Riga");
        assertThat(resultSet.getString(5)).isEqualTo("778-32556178");

        stmt.executeUpdate(revertQuery);
        con.close();
    }

    @Test
    public void TEST_DB_03() throws SQLException {
        String updateQuery =
                "UPDATE AGENTS SET WORKING_AREA = 'Bangladesh', PHONE_NO = '777-11111111' WHERE AGENT_NAME = 'Mukesh'";

        String revertQuery =
                "UPDATE AGENTS SET WORKING_AREA = 'Mumbai', PHONE_NO = '029-12358964' WHERE AGENT_NAME = 'Mukesh'";

        String findQuery = "SELECT * FROM qa3.agents WHERE agents.agent_name = 'Mukesh'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/qa3", user, password);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(updateQuery);

        ResultSet resultSet = stmt.executeQuery(findQuery);
        resultSet.next();

        assertThat(resultSet.getString(3)).isEqualTo("Bangladesh");
        assertThat(resultSet.getString(5)).isEqualTo("777-11111111");
        stmt.executeUpdate(revertQuery);
        con.close();
    }

}
