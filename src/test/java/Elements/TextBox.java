package Elements;

import Utils.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextBox extends UIElement {

    public TextBox(WebDriver driver, WebDriverWait wait, By by) {
        super(driver, wait, by);
    }

    public void setValue(String string) {
        fillTextBox(string);
    }

    public void sendKeys(String string) {
        getElement().sendKeys(string);
    }

}
