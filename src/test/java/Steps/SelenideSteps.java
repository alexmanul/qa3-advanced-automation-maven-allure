package Steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class SelenideSteps {


    @And("I set following properties and navigate to '(.*)' url")
    public void ISetFollowingPropertiesAndNavigarteToURL(String url, DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        String property = data.get(0).get("VALUE");
        String value = data.get(1).get("VALUE");
        String screenSize = data.get(2).get("VALUE");

        System.setProperty(property, value);
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.browserSize = screenSize;
        open(url);
        sleep(500);
    }

}
