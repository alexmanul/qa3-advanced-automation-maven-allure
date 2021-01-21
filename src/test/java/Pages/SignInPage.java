package Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SignInPage {
    private final SelenideElement loginInput = $(By.cssSelector("#email"));
    private final SelenideElement passwordInput = $(By.cssSelector("#password"));
    private final SelenideElement loginLink = $(By.xpath("//*[@id=\"__next\"]/section[1]/main/div/div[2]/div/div/div/div[3]/form/div[5]/div/button"));

    public void inputLogin(String text) {
        this.loginInput.val(text);
    }

    public void inputPassword(String text) {
        this.passwordInput.val(text);
    }

    public void login() {
        this.loginLink.click();
    }

}
