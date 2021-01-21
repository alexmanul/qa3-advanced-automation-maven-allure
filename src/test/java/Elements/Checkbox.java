package Elements;

import Utils.DriverSingleton;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class Checkbox extends UIElement {

    public Checkbox(WebDriver driver, By by) {
        super(driver, by);
    }

    public static Checkbox get(By by) {
        return new Checkbox(DriverSingleton.getInstance(), by);
    }

    public void check() {
        if (!isChecked()) {
            getElement().click();
        }
        assertThat(isChecked()).as("").isTrue();
    }

    public void uncheck() {
        if (isChecked()) {
            getElement().click();
        }
        assertThat(isChecked()).as("").isFalse();
    }

    public boolean isChecked() {
        waitEverythingIsLoaded();
        boolean checked = getElement().isSelected();
        // log.debug ("Chechkbox is checked " + checked);
        return checked;
    }
}
