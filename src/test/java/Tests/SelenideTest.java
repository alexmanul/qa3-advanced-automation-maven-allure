package Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;


public class SelenideTest {

    @Test
    public void TEST_SELENIDE_01() {
        System.setProperty("selenide.browser", "Chrome");
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.browserSize = "360x720";
        open("https://www.ss.lv");
        sleep(500);
        closeWindow();
    }

    @Test
    public void TEST_SELENIDE_02() {
        System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.browserSize = "600x800";
        open("https://www.1a.lv");
        sleep(500);
        closeWindow();
    }

}