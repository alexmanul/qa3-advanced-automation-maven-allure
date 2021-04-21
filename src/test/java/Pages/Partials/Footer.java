package Pages.Partials;

import Elements.Icon;
import Utils.CommonApproach.ElementId;
import Utils.DriverSingleton;
import org.openqa.selenium.By;

public interface Footer {

    @ElementId(id = "FOOTER_JAVAGURU_LOGO")
    default Icon jgLogo() {
        return new Icon(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#footer > section > div > div:nth-child(1) > div > img"));
    }

    @ElementId(id = "FOOTER_PULS_LV_COUNTER")
    default Icon pulsCounter() {
        return new Icon(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.id("_puls.lv_27303"));
    }

    @ElementId(id = "FOOTER_TOP_LV_COUNTER")
    default Icon topCounter() {
        return new Icon(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#footer > div > a"));
    }

    @ElementId(id = "FOOTER_ICON_FACEBOOK")
    default Icon footerIconFacebook() {
        return new Icon(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#footer > section > div > div:nth-child(2) > div > div > a.facebook > i"));
    }

    @ElementId(id = "FOOTER_ICON_LINKEDIN")
    default Icon footerIconLinkedIn() {
        return new Icon(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector(" #footer > section > div > div:nth-child(2) > div > div > a.linkedin > i"));
    }
}
