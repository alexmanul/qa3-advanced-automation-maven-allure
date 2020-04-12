package Elements;

import Utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Dropdown extends UIElement {

    public Dropdown(WebDriver driver, By by) {
        super(driver, by);
    }

    public static Dropdown get(By by) {
        return new Dropdown(DriverSingleton.getInstance(), by);
    }

}
