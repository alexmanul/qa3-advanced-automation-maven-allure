package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dropdown extends UIElement {

    public Dropdown(WebDriver driver, WebDriverWait wait, By by) {
        super(driver, wait, by);
    }
}
