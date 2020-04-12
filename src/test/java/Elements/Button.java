package Elements;

import Utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends UIElement {

    public Button(WebDriver driver, By by) {
        super(driver, by);
    }

    public static Button get(By by) {
        return new Button(DriverSingleton.getInstance(), by);
    }
}
