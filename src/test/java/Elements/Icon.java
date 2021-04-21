package Elements;

import Utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Icon extends UIElement {

    public Icon(WebDriver driver, WebDriverWait wait, By by) {
        super(driver, wait, by);
    }

    public void click() {
        waitAndClick();
    }

    public void waitForVisibility() {
        waitForElementPresents();
        waitForElementVisible();
    }
}