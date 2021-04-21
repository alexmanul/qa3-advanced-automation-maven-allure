package Elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class Checkbox extends UIElement {

    public Checkbox(WebDriver driver, WebDriverWait wait, By by) {
        super(driver, wait, by);
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
        log.debug("Chechkbox is checked " + checked);
        return checked;
    }
}
