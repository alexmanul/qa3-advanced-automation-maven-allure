package Pages;

import Elements.Label;
import Pages.Partials.Footer;
import Pages.Partials.Header;
import Pages.Partials.Menu;
import Pages.Partials.PreFooter;
import Utils.CommonApproach.Identificator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S101RegistrationPage extends BasePage implements Menu, Header, PreFooter, Footer {

    public S101RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Identificator(id = "P101_SCREEN_TITLE")
    public Label S101ScreenTitle() {
        return Label.get(By.cssSelector("div > h1"));
    }

    @Identificator(id = "P101_SCREEN_SUBTITLE_1")
    public Label S101ScreenSubTitle1() {
        return Label.get(By.cssSelector("#wrap > section:nth-child(7) > div > section > div > div > div > div.sub-title > h4"));
    }

    @Identificator(id = "P101_JG_BONUSES_CARD_1")
    public Label S101OurBonuses1() {
        return Label.get(By.cssSelector("div.vc_row.wpb_row.vc_inner.vc_row-fluid > div:nth-child(1) > div > div > article > p"));
    }

    @Identificator(id = "P101_JG_BONUSES_CARD_2")
    public Label S101OurBonuses2() {
        return Label.get(By.cssSelector("div.vc_row.wpb_row.vc_inner.vc_row-fluid > div:nth-child(2) > div > div > article > p"));
    }

    @Identificator(id = "P101_JG_BONUSES_CARD_3")
    public Label S101OurBonuses3() {
        return Label.get(By.cssSelector("div.vc_row.wpb_row.vc_inner.vc_row-fluid > div:nth-child(3) > div > div > article > p"));
    }

    @Identificator(id = "P101_JG_BONUSES_CARD_4")
    public Label S101OurBonuses4() {
        return Label.get(By.cssSelector("div.vc_row.wpb_row.vc_inner.vc_row-fluid > div:nth-child(4) > div > div > article > p"));
    }

}