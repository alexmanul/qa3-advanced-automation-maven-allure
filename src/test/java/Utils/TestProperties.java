package Utils;

//import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

////@Log4j
public class TestProperties {

    private static void setEnvironment() {
        if (System.getProperty("env") == null)
            System.setProperty("env", "test");
        if (System.getProperties().getProperty("port") == null) {
            System.setProperty("port", "");
        }
    }

    public static String getProperty(String property) {
        Properties properties = new Properties();
        setEnvironment();
        String propertyFile = System.getProperties().getProperty("env") + ".properties";
        try {
            FileInputStream in = new FileInputStream(propertyFile);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ////log.debug("Property key: " + property + ", property value: " + properties.getProperty(property));
        return properties.getProperty(property);
    }
}
