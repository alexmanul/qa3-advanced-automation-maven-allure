package Tests;

import org.junit.Before;
import org.junit.Test;
import testUI.Configuration;

import static testUI.Configuration.androidAppPath;
import static testUI.Configuration.automationName;
import static testUI.UIOpen.open;

public class TestUITest {

    @Before
    public void setAndroidPlatform() {
        Configuration.automationType = Configuration.ANDROID_PLATFORM;
        automationName = "UiAutomator1";
    }

    @Test
    public void TEST_TESTUI_01() {
        androidAppPath = "src/test/resources/app/LoginApp.apk";
        open();

//       E(By.id("textInputEditTextEmail")).
//               driver.pressKey(new KeyEvent(AndroidKey.ENTER));
//               .click().sendKeys("myemail@email.com");
//                .given()
//                .waitFor(5)
//                .untilIsVisible()
//                .then()
//                .getMobileElement()
//
//
//        MobileElement find2 = (MobileElement) E(By.id("textInputEditTextPassword"))
//                .given().waitFor(5).untilIsVisible().then().setValueJs("myemail@email.com");

//                sendKeys("myemail@email.com");


//        driver.pressKey(new                KeyEvent(AndroidKey.ENTER));
//        E(By.id("appCompatButtonLogin")).click();
//        driver.HideKeyboard();
    }

//    @Test
//    public void TEST_TESTUI_02() {
//        open("https://ru.aliexpress.com/");
//    }
}
