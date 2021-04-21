package Elements;

import Utils.TestDataReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Slf4j
public abstract class UIElement {
    private final WebDriverWait wait;
    private final WebDriver driver;
    private By by;
    private WebElement element;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// CONSTRUCTORS /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public UIElement(WebDriver driver, WebDriverWait wait, By by) {
        this.driver = driver;
        this.wait = wait;
        this.by = by;
    }

    public UIElement(WebDriver driver, WebDriverWait wait, WebElement element) {
        this.driver = driver;
        this.wait = wait;
        this.element = element;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected WebElement getElement() {
        WebElement webElement = null;
        waitPageIsLoaded();
        for (int i = 0; i < 5; i++) {
            try {
                webElement = element == null ? driver.findElement(by) : element;
                // Any action with element is required to catch the stale element exception
                webElement.isDisplayed();
                break;
            } catch (StaleElementReferenceException e) {
                log.error(e.getMessage());
                sleep(100);
            }
        }
        return webElement;
    }

    protected List<WebElement> getElements() {
        return driver.findElements(by);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// WAITS ////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void waitForElementVisible() {
        for (int iteration = 0; iteration < 5; iteration++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(getElement()));
                break;
            } catch (StaleElementReferenceException ex) {
                log.error(ex.getMessage());
                sleep(100);
            }
        }
    }

    public void waitForElementPresents() {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
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

    protected void waitForElementInvisible(int seconds) {
        sleep(300);
        if (isPresents()) {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
    }

    protected void waitForElementPresents(int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    // Method to wait until page is fully loaded
    public void waitPageIsLoaded() {
        int i = 0;
        while (i < 2) {
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    String documentState = "";
                    try {
                        documentState = (String) js.executeScript("return document.readyState");
                        log.debug("Document state is: " + documentState);
                    } catch (JavascriptException e) {
                        log.error(e.getMessage());
                    }
                    return documentState.equals("complete");
                }
            });
            i++;
            sleep(20);
        }
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
        wait.until((ExpectedCondition<Boolean>) driver -> isEnabled());
    }

    public void waitElementIsDisabled() {
        wait.until((ExpectedCondition<Boolean>) driver -> !isEnabled());
    }

    public void waitUntilElementCountToBeMore(int elementCountToBe) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, elementCountToBe));
    }

    public void waitUntilElementCountToBeLess(int elementCountToBe) {
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(by, elementCountToBe));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// ACTIONS //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void waitAndClick() {
        waitEverythingIsLoaded();
        waitForElementVisible();
        scrollAndClick();
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
            for (int i = 0; i < 5; i++) {
                try {
                    getElement().clear();
                    getElement().sendKeys(TestDataReader.getDataFromFile(input));
                    break;
                } catch (StaleElementReferenceException e) {
                    log.error(e.getMessage());
                    sleep(100);
                }
            }
        }
        waitPageIsLoaded();
    }


    public void click() {
        waitAndClick();
    }


    /**
     * Global method. Used for every WebElement
     *
     * @return String
     */
    public String getValue() {
        waitEverythingIsLoaded();
        log.info(getText());
        return getText();
    }

    public String getAttribute(String attribute) {
        waitPageIsLoaded();
        waitForElementPresents();
        String value = getElement().getAttribute(attribute).trim();
        log.debug("Attribute: '" + attribute + "', value is: '" + value + " '");
        return value;
    }

    protected void scrollToElement() {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView();", getElement());
    }

    //  protected void scrollToElement() {
    //      JavascriptExecutor je = (JavascriptExecutor) driver;
    //      long windowHeight = (Long) je.executeScript("return window.innerHeight");
    //      log.debug("Inner height " + windowHeight);
    //      // Element position before scroll
    //      Point p1 = getElement().getLocation();
    //      je.executeScript("arguments[0].scrollIntoView();", getElement());
    //      try {
    //          sleep(100);
    //      } catch (InterruptedException e) {
    //          e.printStackTrace();
    //      }
    //      // Element position after scroll
    //      Point p2 = getElement().getLocation();
    //      if (p2.getY() - p1.getY() > 200) {
    //          je.executeScript("window.scrollBy(0, " + (-windowHeight / 4) + ");");
    //      }
    //  }

    // Scroll is required due to element could be overlapped by application header
    private void scrollAndClick() {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        // If element is not clickable then scroll to element, else return
        try {
            getElement().click();
            return;
        } catch (Exception e) {
            scrollToElement();
        }
        // If element was not clickable then scroll to the element
        Point location = getElement().getLocation();
        for (int i = 0; i < 5; i++) {
            try {
                getElement().click();
                return;
            } catch (Exception e) {
                if (i != 4) {
                    log.debug("Element is not clickable, scrolling up for 50 px");
                    location = location.moveBy(0, -100);
                    je.executeScript("window.scrollTo(0," + location.getY() + ")");
                } else {
                    log.error("Element is not clickable");
                }
            }
        }
    }

    public void clickOutsideElement(int x, int y) {
        waitPageIsLoaded();
        waitForElementPresents();
        Actions builder = new Actions(driver);
        builder.moveToElement(getElement(), x, y).click().build().perform();
    }

    public String getValueByJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitEverythingIsLoaded();
        String result = js.executeScript("return arguments[0].value", getElement()).toString();
        log.debug("Text content from UI: " + result);
        return result;
    }

    // Solution to avoid stale element exception
    protected String getText() {
        String result = null;
        for (int i = 0; i < 5; i++) {
            try {
                result = getElement().getText();
                break;
            } catch (StaleElementReferenceException e) {
                log.error("Element is not clickable");
                sleep(100);
            }
        }
        log.debug("Text content from UI: " + result);
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// CHANGE WEB ELEMENT ///////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected WebElement getFollowingSibling() {
        return getElement().findElement(By.xpath("//following-sibling::*"));
    }

    protected WebElement getParentElement() {
        return getElement().findElement(By.xpath(".."));
    }

    protected WebElement getPrecedingSibling() {
        return getElement().findElement(By.xpath("//preceding-sibling::*[1]"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// BOOLEANS /////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean isDisplayed() {
        waitPageIsLoaded();
        waitForElementPresents();
        boolean isDisplayed = false;
        for (int i = 0; i < 5; i++) {
            try {
                isDisplayed = getElement().isDisplayed();
                break;
            } catch (StaleElementReferenceException e) {
                log.error("Element is stale " + e.getMessage());
                sleep(100);
            }
        }
        log.debug("Element is displayed " + isDisplayed);
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
        return getElement().isEnabled();

    }

    public boolean isNotDisplayed() {
        boolean isNotVisible;
        isNotVisible = !isPresents() || !isDisplayed();
        log.debug("Element is not displayed " + isNotVisible);
        return isNotVisible;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}