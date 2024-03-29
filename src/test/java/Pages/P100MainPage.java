package Pages;

import Elements.Button;
import Pages.Partials.Footer;
import Pages.Partials.Header;
import Pages.Partials.Menu;
import Pages.Partials.PreFooter;
import Utils.CommonApproach.ElementId;
import Utils.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class P100MainPage extends BasePage implements Menu, Header, PreFooter, Footer {

    public P100MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void navigateToPortal() {
        driver.navigate().to(TestProperties.getProperty("url"));
        driver.manage().window().maximize();
    }

    @ElementId(id = "P100_REGISTER_BUTTON")
    public Button topAskQuestionButton() {
        return new Button(driver, wait, By.cssSelector("div > div > div > div > div > a"));
    }
}