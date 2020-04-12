package Utils;

import io.cucumber.core.api.Scenario;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import java.io.File;

public class Screenshots {

    public ru.yandex.qatools.ashot.Screenshot makeScreenshot(WebDriver driver) {
        ru.yandex.qatools.ashot.Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportRetina(1000, 0, 0, (float) 1.235))
                .takeScreenshot(driver);
        return screenshot;
    }

    public void createFolders(String pathToFile) {
        File fileDiff = new File(pathToFile);
        fileDiff.getParentFile().mkdirs();
    }

    public String getNameOfScenario(Scenario scenario) {
        return scenario.getId().substring(scenario.getId().lastIndexOf(";") + 1).toLowerCase();
    }

    public String getNameOfFeature(Scenario scenario) {
        return scenario.getId().split("\\;")[0].toLowerCase();
    }

}
