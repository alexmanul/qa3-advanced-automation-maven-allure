package Pages;

import Elements.Button;
import Utils.TestDataReader;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePage {

    public final RemoteWebDriver driver;
    public final WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = (RemoteWebDriver) driver;
        this.wait = wait;
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
        new Button(driver, By.cssSelector("")).waitPageIsLoaded();
    }

    public void switchBrowserTab(int browserTabNumber) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(browserTabNumber - 1));
    }

    public void manualWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
