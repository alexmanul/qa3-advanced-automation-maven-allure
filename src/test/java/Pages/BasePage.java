package Pages;

import Elements.Button;
import Utils.AdvancedString;
import Utils.TestDataReader;
import Utils.Utils;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePage {

    public RemoteWebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = (RemoteWebDriver) driver;
        this.wait = wait;
    }

    public BasePage() {

    }

    // ASSERTIONS

    public void assertThatEquals(String actual, String expected) {
        assertThat(actual)
                .isEqualTo(TestDataReader.getDataFromFile(Utils.getSpecificDate(expected)));
    }

    public void assertThatEquals(String actual, String expected, String exceptionMessage) {
        assertThat(actual).as(exceptionMessage)
                .isEqualTo(TestDataReader.getDataFromFile(Utils.getSpecificDate(expected)));
    }

    public void assertThatEquals(int actual, int expected, String exceptionMessage) {
        assertThatEquals(String.valueOf(actual), Utils.getSpecificDate(String.valueOf(expected)), exceptionMessage);
    }

    public void waitPageIsLoaded() {
        new Button(driver, wait, By.cssSelector("")).waitPageIsLoaded();
    }

    public void switchBrowserTab(int browserTabNumber) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(browserTabNumber - 1));
    }

    public void manualWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param table DataTable
     * @return stringMapList
     */
    public List<Map<String, String>> getTableAsStringMaps(DataTable table) {
        List<Map<AdvancedString, AdvancedString>> advancedStringMapList = table.asMaps(AdvancedString.class, AdvancedString.class);
        List<Map<String, String>> stringMapList = new ArrayList<>();
        for (Map<AdvancedString, AdvancedString> map : advancedStringMapList) {
            Map<String, String> stringMap = new HashMap<>();
            for (Map.Entry<AdvancedString, AdvancedString> entry : map.entrySet()) {
                stringMap.put(entry.getKey().toString(), entry.getValue().toString());
            }
            stringMapList.add(stringMap);
        }
        return stringMapList;
    }

    public List<List<String>> getTableAsStringLists(DataTable table) {
        List<List<AdvancedString>> advancedStringLists = table.asLists(AdvancedString.class);
        List<List<String>> stringLists = new ArrayList<>();
        for (List<AdvancedString> advancedStringList : advancedStringLists) {
            List<String> stringList = new ArrayList<>();
            for (AdvancedString advancedString : advancedStringList) {
                stringList.add(advancedString.toString());
            }
            stringLists.add(stringList);
        }
        return stringLists;
    }
}
