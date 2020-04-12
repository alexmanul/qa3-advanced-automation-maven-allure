package Elements;

import Utils.TestDataReader;
import Utils.TestProperties;
//import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

////@Log4j
public abstract class UIElement {
    private final By by;
    private final WebDriverWait wait;
    private final WebDriver driver;
    private WebElement element;


    public UIElement(WebDriver driver, By by) {
        this.by = by;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Integer.parseInt(TestProperties.getProperty("selenium.explicit.wait")));
    }

    protected WebElement getElement() {
        return element == null ? driver.findElement(by) : element;
    }

    protected List<WebElement> getElements() {
        return driver.findElements(by);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// WAITS ///////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void waitForElementPresents() {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitForElementVisible() {
        wait.until(ExpectedConditions.visibilityOf(getElement()));
    }

    protected void waitForElementClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitForAllElementsArePresent(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected void waitForAllElementsAreVisible(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitPageIsLoaded() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String documentState = (String) js.executeScript("return document.readyState");
                return documentState.equals("complete");
            }
        });
    }

    public void waitUntilElementAttributeToBe(String attribute, String value) {
        wait.until(ExpectedConditions.attributeToBe(getElement(), attribute, value));
    }

    public void waitUntilElementAttributeToContains(String attribute, String value) {
        wait.until(ExpectedConditions.attributeContains(getElement(), attribute, value));
    }

    protected void waitEverythingIsLoaded() {
        waitPageIsLoaded();
        waitForElementPresents();
        waitForElementVisible();
    }

    public void waitElementIsEnabled() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isEnabled();
            }
        });
    }

    ///////////////////
    //////  //////
    ///////////////////

    public String getValue() {
        waitEverythingIsLoaded();
        scrollToElement();
        String result = getElement().getText();
        ////log.debug(result);
        return result;
    }

    public String getAttribute(String attribute) {
        waitPageIsLoaded();
        waitForElementPresents();
        String value = getElement().getAttribute(attribute).trim();
        ////log.debug(value);
        return value;
    }


    protected void scrollToElement() {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        long windowHeight = (Long) je.executeScript("return window.innerHeight");
        ////log.debug("Inner height " + windowHeight);
        // Element position before scroll
        Point p1 = getElement().getLocation();
        je.executeScript("arguments[0].scrollIntoView();", getElement());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Element position after scroll
        Point p2 = getElement().getLocation();
        if (p2.getY() - p1.getY() > 200) {
            je.executeScript("window.scrollBy(0, " + (-windowHeight / 4) + ");");
        }
    }

    private void scrollAndClick() {
        boolean clickable = false;
        JavascriptExecutor je = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            try {
                getElement().click();
                clickable = true;
                return;
            } catch (Exception e) {
                je.executeScript("window.scrollBy(0, -50)");
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////// BOOLEANS /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isDisplayed() {
        waitPageIsLoaded();
        waitForElementPresents();
        boolean isDisplayed = getElement().isDisplayed();
        ////log.info("Element is displayed " + isDisplayed);
        return isDisplayed;
    }

    public boolean isPresents() {
        waitPageIsLoaded();
        int elementCount = driver.findElements(by).size();
        return elementCount > 0;
    }

    public boolean isEnabled() {
        waitPageIsLoaded();
        waitForElementPresents();
        boolean isDisplayed = getElement().isDisplayed();
        return getElement().isEnabled();

    }

    public boolean isNotDisplayed() {
        boolean isNotVisible;
        isNotVisible = !isPresents() || !isDisplayed();
        ////log.info("Element is not displayed " + isNotVisible);
        return isNotVisible;
    }

    ////////////////

    protected void waitAndClick() {
        waitEverythingIsLoaded();
        waitForElementVisible();
        scrollToElement();
        scrollAndClick();
        waitPageIsLoaded();
    }

    public void click() {
        waitAndClick();
    }

    protected void fillTextBox(String input) {
        waitEverythingIsLoaded();
        scrollToElement();
        // Temporary workaround for validation error message - could be deleted
        if (input.isEmpty()) {
            getElement().sendKeys(Keys.SPACE);
            getElement().sendKeys(Keys.CONTROL, "a");
            getElement().sendKeys(Keys.DELETE);

        } else {
            getElement().clear();
            getElement().sendKeys(TestDataReader.getDataFromFile(input));
        }
        waitPageIsLoaded();
    }
}