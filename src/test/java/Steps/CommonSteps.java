package Steps;

import Elements.Button;
import Elements.Checkbox;
import Elements.RadioButton;
import Elements.UIElement;
import Pages.BasePage;
import Pages.P100MainPage;
import Utils.CommonApproach.ElementReader;
import Utils.CustomAssertions;
import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class CommonSteps extends BaseSteps {

    private final BasePage basePage = new BasePage(driver, wait);
    private final P100MainPage mainPage = new P100MainPage(driver, wait);
    Scenario scenario;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////// COMMON APPROACH ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^I see expected result by executing '(.*)' method on the '(.*)' page$")
    public void ISeeExpectedResultByExecutingMethod(String method, String page) throws Exception {
        new ElementReader(page).executeMethod(method);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// NAVIGATION ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^I navigate to JAVAGURU.LV website$")
    public void INavigateToJGWebsite() {
        mainPage.navigateToPortal();
        mainPage.waitPageIsLoaded();
    }

    @And("^I navigate to '(.*)' page with '(.*)' device$")
    public void INavigateToPage(String url, String device) {
        driver.navigate().to(url);
        if (device.equals("MOBILE")) {
            driver.manage().window().setSize(new Dimension(400, 720));
        } else {
            driver.manage().window().maximize();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// ASSERTIONS ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^I see browser title is '(.*)'$")
    public void ISeeBrowserTitle(String title) {
        CustomAssertions.assertThatEquals(driver.getTitle(), title, "Browser title is wrong");
    }

    // SEE - DO NOT SEE

    @And("^I see '(.*)' element on the '(.*)' page$")
    public void ISeeElementOnThePage(String element, String page) throws Exception {
        new ElementReader(page).getUIElement(element, UIElement.class).isDisplayed();
    }

    @And("^I do not see '(.*)' element on the '(.*)' page$")
    public void IDoNotSeeElementOnThePage(String title, String page) throws Exception {
        new ElementReader(page).getUIElement(title, UIElement.class).isNotDisplayed();
    }

    // EQUALS

    @And("^I see text '(.*)' for '(.*)' element on the '(.*)' page$")
    public void ISeeTextForElementOnThePage(String text, String element, String page) throws Exception {
        CustomAssertions.assertThatEquals(new ElementReader(page).getUIElement(element, UIElement.class).getValue(),
                text, "Element " + element + " text is wrong");
    }

    @And("^I see text '(.*)' for element by '(.*)' number on the '(.*)' page$")
    public void ISeeTextForElementByNumberOnThePage(String text, int number, String element, String page) throws Exception {
        CustomAssertions.assertThatEquals(new ElementReader(page).getUIElementWithVariables(element, UIElement.class, number).getValue(),
                text, "Text is wrong for element " + element + " and index " + number);
    }

    // CONTAINS

    @And("^I see text for '(.*)' element contains text '(.*)' on the '(.*)' page$")
    public void ISeeTextForElementContainsTextOnThePage(String element, String text, String page) throws Exception {
        CustomAssertions.assertThatContains(new ElementReader(page).getUIElement(element, UIElement.class).getValue(),
                text, "Element " + element + " text is wrong");
    }

    @And("^I see text for '(.*)' element by '(.*)' number contains text '(.*)' on the '(.*)' page$")
    public void ISeeTextForElementByNumberContainsTextOnThePage(String element, int number, String text, String page) throws Exception {
        CustomAssertions.assertThatContains(new ElementReader(page).getUIElementWithVariables(element, UIElement.class, number).getValue(),
                text, "Text is wrong for element " + element + " and index " + number);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// ASSERTIONS ///////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^I see following elements on the '(.*)' page$")
    public void ISeeFollowingElementsOnThePage(String page, DataTable table) throws Exception {
        List<Map<String, String>> data = table.asMaps();
        for (Map<String, String> row : data) {
            CustomAssertions.assertThatTrue(
                    new ElementReader(page).getUIElement(row.get("ELEMENT"), UIElement.class).isDisplayed(),
                    "Element " + row.get("ELEMENT") + " is not displayed");
        }

        if (data.get(0).size() > 1) {
            for (Map<String, String> row : data) {
                if (row.get("LABEL").startsWith("*")) {
                    String label = row.get("LABEL").replace("*", "");
                    CustomAssertions.assertThatContains(
                            new ElementReader(page).getUIElement(row.get("ELEMENT"), UIElement.class).getValue(),
                            label, "Element " + row.get("ELEMENT") + " label is wrong");
                } else if (!row.get("LABEL").isEmpty()) {
                    CustomAssertions.assertThatEquals(
                            new ElementReader(page).getUIElement(row.get("ELEMENT"), UIElement.class).getValue(),
                            row.get("LABEL"), "Element " + row.get("ELEMENT") + " label is wrong");
                }
            }
        }

    }

    @And("^I see '(.*)' element is '(.*)' on the '(.*)' page$")
    public void ISeeElementIsConditionOnThePage(String element, String condition, String page) throws Exception {
        switch (condition.toLowerCase()) {
            case "enabled":
                if (!button(page, element).isEnabled()) {
                    assertThat(new ElementReader(page).getUIElement(element, Button.class).getAttribute("class"))
                            .as("Button is disabled").doesNotContain("disabled");
                }
                break;
            case "disabled":
                if (button(page, element).isEnabled()) {
                    assertThat(new ElementReader(page).getUIElement(element, Button.class).getAttribute("class"))
                            .as("Button is enabled").contains("disabled");
                }
            case "checked":
                assertThat(new ElementReader(page).getUIElement(element, Checkbox.class).isChecked()).isTrue();
            case "unchecked":
                assertThat(new ElementReader(page).getUIElement(element, Checkbox.class).isChecked()).isFalse();
            case "selected":
                assertThat(new ElementReader(page).getUIElement(element, RadioButton.class).isChecked()).isTrue();
            case "not selected":
                assertThat(new ElementReader(page).getUIElement(element, RadioButton.class).isChecked()).isFalse();
            default:
                throw new Exception("Case is not defined for " + condition);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// CLICKS ///////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^I click on '(.*)' element on the '(.*)' page$")
    public void IClickOnIcon(String title, String page) throws Exception {
        new ElementReader(page).getUIElement(title, UIElement.class).click();
    }

    @And("^I click on '(.*)' element containing '(.*)' text on the '(.*)' page$")
    public void IClickOnElementContainingTextOnThePage(String element, String text, String page) throws Exception {
        new ElementReader(page).getUIElementWithVariables(element, UIElement.class, text).click();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// FILLINGS /////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// ACTIONS //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^I wait for '(.*)' seconds$")
    public void IWaitForSomeSeconds(int seconds) throws Exception {
        basePage.manualWait(seconds);
    }

    @And("^I refresh the page$")
    public void IRefreshThePage() {
        driver.navigate().refresh();
    }

    @And("^I switch to '(.*)' browser tab$")
    public void ISwitchToBrowserTab(int tabNumber) {
        basePage.switchBrowserTab(tabNumber);
    }

    @And("^I see page url is '(.*)'$")
    public void ISeePageUrl(String url) {
        basePage.waitPageIsLoaded();
        CustomAssertions.assertThatEquals(driver.getCurrentUrl(), url, "Page URL is wrong");
    }


}