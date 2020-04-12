package Pages;

import Elements.Button;
import Pages.Partials.Footer;
import Pages.Partials.Header;
import Pages.Partials.Menu;
import Pages.Partials.PreFooter;
import Utils.CommonApproach.Identificator;
import Utils.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S100MainPage extends BasePage implements Menu, Header, PreFooter, Footer {

    public S100MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void navigateToPortal() {
        driver.navigate().to(TestProperties.getProperty("url"));
        driver.manage().window().maximize();
    }

    @Identificator(id = "P100_REGISTER_BUTTON")
    public Button topAskQuestionButton() {
        return Button.get(By.cssSelector("div > div > div > div > div > a"));
    }
}