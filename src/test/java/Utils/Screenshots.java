package Utils;

import Steps.Context;
import io.cucumber.core.api.Scenario;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
public class Screenshots {

    private final String screenshotDirectory = System.getProperty("user.dir")
            + File.separator + TestProperties.getProperty("screenshots.folder1")
            + File.separator + TestProperties.getProperty("screenshots.folder2");
    private final ShootingStrategy RETINA = ShootingStrategies.viewportRetina(1000, 0, 0, 1);
    private final ShootingStrategy SIMPLE = ShootingStrategies.simple();
    WebDriver driver;
    private Context context;

    public Screenshots(WebDriver driver) {
        this.driver = driver;
    }

    public Screenshots(WebDriver driver, Context context) {
        this.driver = driver;
        this.context = context;
    }

    public Screenshot makeScreenshot(WebDriver driver) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(RETINA)
                .takeScreenshot(driver);
        log.debug(String.valueOf(screenshot));
        return screenshot;
    }

    public BufferedImage getScreenshot() {
        Screenshot screenshot = new AShot()
                .shootingStrategy(SIMPLE)
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
        log.info("Screenshot file name: " + fileName);
        return screenshotDirectory + File.separator + dir + File.separator + fileName;
    }

    public void embedScreenshot(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        byte[] bytes = baos.toByteArray();
        context.scenario.embed(bytes, "image/png");
    }
}
