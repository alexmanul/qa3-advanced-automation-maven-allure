package Pages.Partials;

import Elements.Icon;
import Utils.CommonApproach.Identificator;
import org.openqa.selenium.By;

public interface Footer {

    @Identificator(id = "FOOTER_JAVAGURU_LOGO")
    default Icon jgLogo() {
        return Icon.get(By.cssSelector("#footer > section > div > div:nth-child(1) > div > img"));
    }

    @Identificator(id = "FOOTER_PULS_LV_COUNTER")
    default Icon pulsCounter() {
        return Icon.get(By.id("_puls.lv_27303"));
    }

    @Identificator(id = "FOOTER_TOP_LV_COUNTER")
    default Icon topCounter() {
        return Icon.get(By.cssSelector("#footer > div > a"));
    }

    @Identificator(id = "FOOTER_ICON_FACEBOOK")
    default Icon footerIconFacebook() {
        return Icon.get(By.cssSelector("#footer > section > div > div:nth-child(2) > div > div > a.facebook > i"));
    }

    @Identificator(id = "FOOTER_ICON_LINKEDIN")
    default Icon footerIconLinkedIn() {
        return Icon.get(By.cssSelector(" #footer > section > div > div:nth-child(2) > div > div > a.linkedin > i"));
    }
}
