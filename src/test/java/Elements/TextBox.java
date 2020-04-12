package Elements;

import Utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBox extends UIElement {

    public TextBox(WebDriver driver, By by) {
        super(driver, by);
    }

    public static TextBox get(By by) {
        return new TextBox(DriverSingleton.getInstance(), by);
    }

    public void setValue(String string) {
        fillTextBox(string);
    }

    public void sendKeys(String string) {
        getElement().sendKeys(string);
    }

}
