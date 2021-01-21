package Elements;

import Utils.DriverSingleton;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class RadioButton extends UIElement {

    public RadioButton(WebDriver driver, By by) {
        super(driver, by);
    }

    public static RadioButton get(By by) {
        return new RadioButton(DriverSingleton.getInstance(), by);
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
