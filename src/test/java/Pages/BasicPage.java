package Pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasicPage {

    public void clickButton(String text) {
        $(By.xpath("//div[text()='" + text + "']")).click();
    }

    public void clickJoinButton() {
        $(By.xpath("//*[@id=\"__next\"]/section[1]/main/div/div[3]/div/div/div[1]/div[2]/button")).click();
    }

    public void clickButtonSpan(String text) {
        $(By.xpath("//span[text()='" + text + "']/..")).click();
    }

    public void contentIsVisible(String text) {
        $(By.xpath("//*[text()='" + text + "']")).shouldBe(Condition.visible);
    }
}
