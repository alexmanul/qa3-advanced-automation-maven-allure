package MYSQL;

//import lombok.extern.log4j.Log4j;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLQueryCatalog {

    public String updateAgentsByAgentName(String tableDB, String key, String newValue, String name) {
        String query = "UPDATE " + tableDB + " SET " + key + " = '" + newValue + "' WHERE AGENT_NAME = '" + name + "'";
        log.debug(query);
        return query;
    }

    public String findAllByAgentName(String tableDB, String name) {
        String query = "SELECT * FROM " + tableDB + " WHERE AGENT_NAME = '" + name + "'";
        log.debug(query);
        return query;
    }

    public String insertNewAgent(String tableDB, String agentCode, String agentName, String workingArea,
                                 String commission, String phoneNo, String country) {
        String query = "INSERT INTO " + tableDB + " VALUES (" +
                "'" + agentCode + "', " +
                "'" + agentName + "', " +
                "'" + workingArea + "', " +
                "'" + commission + "', " +
                "'" + phoneNo + "', " +
                "'" + country + "')";
        log.debug(query);
        return query;
    }

    public String insertNewEntity(String tableDB, String values) {
        String query = "INSERT INTO " + tableDB + " VALUES (" + values + ")";
        log.debug(query);
        return query;
    }

    public String deleteNewEntityByAgentName(String tableDB, String name) {
        String query = "DELETE FROM " + tableDB + " WHERE AGENT_NAME = '" + name + "'";
        log.debug(query);
        return query;
    }

}
