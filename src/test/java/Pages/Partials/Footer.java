package Pages.Partials;

import Elements.Icon;
import Utils.CommonApproach.ElementId;
import org.openqa.selenium.By;

public interface Footer {

    @ElementId(id = "FOOTER_JAVAGURU_LOGO")
    default Icon jgLogo() {
        return Icon.get(By.cssSelector("#footer > section > div > div:nth-child(1) > div > img"));
    }

    @ElementId(id = "FOOTER_PULS_LV_COUNTER")
    default Icon pulsCounter() {
        return Icon.get(By.id("_puls.lv_27303"));
    }

    @ElementId(id = "FOOTER_TOP_LV_COUNTER")
    default Icon topCounter() {
        return Icon.get(By.cssSelector("#footer > div > a"));
    }

    @ElementId(id = "FOOTER_ICON_FACEBOOK")
    default Icon footerIconFacebook() {
        return Icon.get(By.cssSelector("#footer > section > div > div:nth-child(2) > div > div > a.facebook > i"));
    }

    @ElementId(id = "FOOTER_ICON_LINKEDIN")
    default Icon footerIconLinkedIn() {
        return Icon.get(By.cssSelector(" #footer > section > div > div:nth-child(2) > div > div > a.linkedin > i"));
    }
}
