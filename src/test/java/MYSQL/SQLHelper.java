package MYSQL;

import Utils.TestProperties;
//import lombok.extern.log4j.Log4j;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//@Log4j
public class SQLHelper {

    final String URL = "jdbc:mysql://127.0.0.1:3306/qa3";
    final String USER = "test_user";
    final String PASSWORD = "123456";

    public Connection createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Statement createStatement() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        return con.createStatement();
    }


    public void executeSimpleQuery(String simpleQuery) throws SQLException {
        Statement statement = createStatement();
        statement.executeQuery(simpleQuery);
    }

    public void executeUpdateQuery(String updateQuery) throws SQLException {
        Statement statement = createStatement();
        statement.executeUpdate(updateQuery);
    }

    // READ SQL FROM FILE
    public void readSQLFromFile() throws SQLException, FileNotFoundException {
        String fileName = TestProperties.getProperty("sql.create.table.agents");
        //log.info(fileName);
        Connection connection = createConnection();
        //log.debug("Connection established...");

        ScriptRunner sr = new ScriptRunner(connection);
        Reader reader = new BufferedReader(new FileReader(fileName));

        sr.runScript(reader);
        sr.closeConnection();
    }
}
