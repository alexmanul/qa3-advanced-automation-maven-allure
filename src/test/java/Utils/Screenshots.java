package Utils;

import Steps.SharedContext;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Screenshots {

    private final String screenshotDirectory = System.getProperty("user.dir")
            + File.separator + TestProperties.getProperty("screenshots.folder1")
            + File.separator + TestProperties.getProperty("screenshots.folder1");

    WebDriver driver;
    private SharedContext context;

    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    public Screenshots(WebDriver driver, SharedContext context) {
        this.driver = driver;
        this.context = context;
    }

    public Screenshot makeScreenshot(WebDriver driver) {
        Screenshot screenshot = new AShot()
                .shootingStrategy
                        (ShootingStrategies.viewportRetina(1000, 0, 0, 1))
                .takeScreenshot(driver);
        // log.debug(screenshot);
        return screenshot;
    }

    public BufferedImage getScreenshot() {
        Screenshot screenshot = new AShot()
                .shootingStrategy
                        (ShootingStrategies.viewportRetina(1000, 0, 0, 1))
                .takeScreenshot(driver);
        return screenshot.getImage();
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

    public String generateScreenshotName(String dir, String prefix, String pageName) {
        String fileName = prefix + pageName + ".png";
        // log.debug ("Screenshot file name: "+ fileName);
        return screenshotDirectory + File.separator + dir + File.separator + fileName;
    }

    public void embedScreenshot(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        byte[] bytes = baos.toByteArray();
        context.scenario.embed(bytes, "image/png");
    }
}
