package Elements;

import Utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Icon extends UIElement {

    public Icon(WebDriver driver, By by) {
        super(driver, by);
    }

    public static Icon get(By by) {
        return new Icon(DriverSingleton.getInstance(), by);
    }

    public void click() {
        waitAndClick();
    }

    public void waitForVisibility() {
        waitForElementPresents();
        waitForElementVisible();
    }
}