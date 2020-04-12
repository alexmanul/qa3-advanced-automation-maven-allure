package Utils;

import Steps.BaseSteps;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Log4j
public class TestDataReader extends BaseSteps {

    /**
     * @param key - String input. If String starts with '@', value will be fetched from test data JSON file
     *            To fetch data from JSON file required to use JSON pointer
     *            If string not starts with '@', original value will be used
     *            Example: String '@/test/1' -> Data will be searched in test data file and the value will be returned
     *            Example: String 'Test' -> Original value 'Test' will be returned
     * @return String - Test data value from JSON or original value
     * @throws Exception 1. FileNotFoundException - in case if test data file is not found
     *                   2. NullPointerException - in case if JSON pointer path is wrong
     */
    public static String getDataFromFile(String key) {
        String result = key;
        JSONObject jsonObject = null;
        String testDataRelativePath = getTestDataSourceFileName();
        try {
            byte[] data = Files.readAllBytes(Paths.get(testDataRelativePath));
            jsonObject = new JSONObject(new String(data, "UTF-8"));
        } catch (Exception e) {
            //log.error("Test data json file is not found");
        }

        Pattern pattern = Pattern.compile("(@/[\\S]+)");
        Matcher matcher = pattern.matcher(key);

        if (matcher.find()) {
            try {
                jsonObject.query(matcher.group(1).substring(1));
            } catch (NullPointerException npe) {
                throw new NullPointerException("Json key " + key + " is not found in " + getTestDataSourceFileName());
            }
            result = result.replace(matcher.group(1), jsonObject.query(matcher.group(1).substring(1)).toString());
            while (matcher.find()) {
                result = result.replace(matcher.group(1), jsonObject.query(matcher.group(1).substring(1)).toString());
            }
        }
        //log.debug("Original value: " + key + ", Converted value: " + result);
        return result;
    }

    private static String getTestDataSourceFileName() {
        return getTestDataRelativePath("translations", "translations.json");
    }

    /**
     * Method to generate relative path for test data file
     *
     * @param folder - Folder where file is stored
     * @param file   - File name
     * @return String - Test data relative path
     */
    private static String getTestDataRelativePath(String folder, String file) {
        List<String> test = new ArrayList<>();
        test.add("src");
        test.add("test");
        test.add("resources");
        test.add("testData");
        test.add(folder);
        test.add(file);
        String result = String.join(File.separator, test);
        //log.info("Test data file relative path is " + result);
        return result;
    }

    /**
     * @param string
     * @return String - Test data value from JSON or original value
     */
    public String getMultipleValuesFromFile(String string) {
        String result = string;
        JSONObject jsonObject = null;

        String testDataFileRelativePath = getTestDataSourceFileName();

        try {
            jsonObject = new JSONObject(new String(Files.readAllBytes(Paths.get(testDataFileRelativePath))));
        } catch (Exception e) {
            //log.error("Test data JSON file is not found");
        }

        //log.info("Original value: " + string + ", Converted value: " + result);
        return result;
    }

    public List<String> getMultipleTestDataFromFile(String testData) {
        List<String> convertedValueList = new ArrayList<>();
        String[] originalValueArray = testData.split("&&&");

        for (String value : originalValueArray) {
            convertedValueList.add(getDataFromFile(value));
        }
        //log.info("Converted test data value list is " + convertedValueList);
        return convertedValueList;
    }
}
