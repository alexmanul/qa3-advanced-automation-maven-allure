package Elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class RadioButton extends UIElement {

    public RadioButton(WebDriver driver, WebDriverWait wait, By by) {
        super(driver, wait, by);
    }

    public void click() {
        waitAndClick();
    }

    public boolean isChecked() {
        waitEverythingIsLoaded();
        boolean checked = getElement().isSelected();
        log.debug("Chechkbox is checked " + checked);
        return checked;
    }
}
