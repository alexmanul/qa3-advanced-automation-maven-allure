package Pages.Partials;

import Elements.Icon;
import Elements.Label;
import Utils.CommonApproach.ElementId;
import org.openqa.selenium.By;

public interface PreFooter {

    @ElementId(id = "FOOTER_ADDRESS_ICON")
    default Icon addressIcon() {
        return Icon.get(By.cssSelector("#pre-footer > section > div > div:nth-child(1) > div > i"));
    }

    @ElementId(id = "FOOTER_ADDRESS_LABEL")
    default Label address() {
        return Label.get(By.cssSelector("#pre-footer > section > div > div:nth-child(1) > div > span"));
    }

    @ElementId(id = "FOOTER_EMAIL_ICON")
    default Icon emailIcon() {
        return Icon.get(By.cssSelector("#pre-footer > section > div > div:nth-child(2) > div > i"));
    }

    @ElementId(id = "FOOTER_EMAIL_LABEL")
    default Label email() {
        return Label.get(By.cssSelector("#pre-footer > section > div > div:nth-child(2) > div > span"));
    }

    @ElementId(id = "FOOTER_PHONE_ICON")
    default Icon phoneIcon() {
        return Icon.get(By.cssSelector("#pre-footer > section > div > div:nth-child(3) > div > i"));
    }

    @ElementId(id = "FOOTER_PHONE_LABEL")
    default Label phone() {
        return Label.get(By.cssSelector("#pre-footer > section > div > div:nth-child(3) > div > span"));
    }

}
