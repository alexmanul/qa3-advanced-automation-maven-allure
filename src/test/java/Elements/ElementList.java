package Elements;

import Utils.DriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class ElementList extends UIElement {

    public ElementList(WebDriver driver, By by) {
        super(driver, by);
    }

    public static ElementList get(By by) {
        return new ElementList(DriverSingleton.getInstance(), by);
    }

    public int getItemCount() {
        waitPageIsLoaded();
        int elementCount = 0;
        try {
            waitForAllElementsArePresent(5);
            waitForAllElementsAreVisible(5);
            scrollToElement();
            elementCount = getElements().size();
        } catch (Exception ignored) {
        }
        log.debug("Element count is " + elementCount);
        return elementCount;
    }

    @Override
    public String getValue() {
        log.error("Value is not defined");
        return null;
    }
}
