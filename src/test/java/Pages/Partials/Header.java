package Pages.Partials;

import Elements.Button;
import Elements.Icon;
import Elements.Label;
import Utils.CommonApproach.ElementId;
import Utils.DriverSingleton;
import org.openqa.selenium.By;

public interface Header {

    @ElementId(id = "HEADER_EMAIL_LABEL")
    default Label topEmail() {
        return new Label(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#wrap > section.top-bar > div > div.top-links.lftflot > h6:nth-child(2)"));
    }

    @ElementId(id = "HEADER_PHONE_LABEL")
    default Label topPhone() {
        return new Label(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#wrap > section.top-bar > div > div.top-links.lftflot > h6:nth-child(3)"));
    }

    @ElementId(id = "HEADER_QUESTION_BUTTON")
    default Button topAskQuestionButton() {
        return new Button(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#wrap > section.top-bar > div > div.top-links.rgtflot > a.inlinelb.topbar-contact"));
    }

    @ElementId(id = "HEADER_MOODLE_BUTTON")
    default Button topMoodleLinkButton() {
        return new Button(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#wrap > section.top-bar > div > div.top-links.rgtflot > a:nth-child(2)"));
    }

    @ElementId(id = "HEADER_LANGUAGE_BUTTON")
    default Button topLanguageLinkButton() {
        return new Button(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("#top_lang_bar > li > a"));
    }

    @ElementId(id = "HEADER_ICON_FACEBOOK")
    default Icon topIconInstagram() {
        return new Icon(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("div.top-links.lftflot > div > a.facebook > i"));
    }

    @ElementId(id = "HEADER_ICON_LINKEDIN")
    default Icon topIconLinkedIn() {
        return new Icon(DriverSingleton.getInstance(), DriverSingleton.getExplicitWait(),
                By.cssSelector("div.top-links.lftflot > div > a.linkedin > i"));
    }

}
