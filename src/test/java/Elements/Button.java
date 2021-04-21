package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button extends UIElement {


    public Button(WebDriver driver, WebDriverWait wait, By by) {
        super(driver, wait, by);
    }

    public void click() {
        waitAndClick();
    }
}
