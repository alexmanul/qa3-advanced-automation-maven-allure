package Pages.Partials;

import Elements.Button;
import Elements.Icon;
import Utils.CommonApproach.ElementId;
import org.openqa.selenium.By;

public interface Menu {

    @ElementId(id = "MENU_JAVAGURU_LOGO")
    default Icon logo() {
        return Icon.get(By.cssSelector("#header > div > div.col-md-3.col-sm-3.logo-wrap > div > a:nth-child(1) > img"));
    }

    @ElementId(id = "MENU_COURSES_OPTION")
    default Button menu_courses() {
        return Button.get(By.cssSelector("ul#nav>li#menu-item-10223"));
    }

    @ElementId(id = "MENU_COURSES_SUBMENU")
    default Button SUBmenu_courses() {
        return Button.get(By.cssSelector("#menu-item-10223>ul.sub-menu"));
    }

    @ElementId(id = "MENU_TIMETABLE_OPTION")
    default Button menu_timetable() {
        return Button.get(By.cssSelector("ul#nav>li#menu-item-10578"));
    }

    @ElementId(id = "MENU_REGISTER_OPTION")
    default Button menu_register() {
        return Button.get(By.cssSelector("ul#nav>li#menu-item-10304"));
    }

    @ElementId(id = "MENU_FAQ_OPTION")
    default Button menu_faq() {
        return Button.get(By.cssSelector("ul#nav>li#menu-item-10235"));
    }

    @ElementId(id = "MENU_NEWS_OPTION")
    default Button menu_news() {
        return Button.get(By.cssSelector("ul#nav>li#menu-item-10212"));
    }

    @ElementId(id = "MENU_ABOUT_US_OPTION")
    default Button menu_about_us() {
        return Button.get(By.cssSelector("ul#nav>li#menu-item-12320"));
    }

    @ElementId(id = "MENU_ABOUT_US_SUBMENU")
    default Button submenu_about_us() {
        return Button.get(By.cssSelector("#menu-item-12320>ul"));
    }
}