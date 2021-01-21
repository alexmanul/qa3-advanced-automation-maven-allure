package Steps;

import Utils.Screenshots;
import Utils.TestProperties;
import io.cucumber.java.en.And;
import org.junit.Assert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenshotSteps extends BaseSteps {

    private final SharedContext context;

    public ScreenshotSteps(SharedContext context) {
        this.context = context;
    }

    @And("^I take base screenshot of '(.*)' page and store as expected result$")
    public void TakeBaseScreenshotAndStoreAsExpectedResult(String page) throws Exception {
        Screenshots screen = new Screenshots(driver, context);
        String baseFile = screen.generateScreenshotName("base", "base-", page);
        screen.createFolders(baseFile);
        // log.info("Name of base file: "+ baseFile);
        ImageIO.write(screen.getScreenshot(), "PNG", new File(baseFile));
    }

    @And("^I take actual screenshot of '(.*)' page and check vs expected result$")
    public void TakeActualScreenshotAndCheckVsExpectedResult(String page) throws Exception {
        Screenshots screen = new Screenshots(driver, context);
        String actualFile = screen.generateScreenshotName("actual", "actual-", page);
        screen.createFolders(actualFile);
        ImageIO.write(screen.getScreenshot(), "PNG", new File(actualFile));
        // log.info("Name of actual file: "+ actualFile);

        String baseFile = screen.generateScreenshotName("base", "base-", page);
        BufferedImage baseImage = ImageIO.read(new File(baseFile));
        BufferedImage actualImage = ImageIO.read(new File(actualFile));

        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff diff = imageDiffer.makeDiff(baseImage, actualImage);
        diff.withDiffSizeTrigger(Integer.parseInt(TestProperties.getProperty("number.of.accepted.different.pixels")));

        String transparentMarkedImage = screen.generateScreenshotName("fails", "diff1-transparent_", page);
        screen.createFolders(transparentMarkedImage);

        if (diff.getDiffSize() > 0) {
            ImageIO.write(baseImage, "PNG",
                    new File(screen.generateScreenshotName("fails", "base-copy-", page)));
            ImageIO.write(diff.getTransparentMarkedImage(), "PNG", new File(transparentMarkedImage));
            ImageIO.write(diff.getMarkedImage(), "PNG",
                    new File(screen.generateScreenshotName("fails", "diff2-marked_", page)));

            screen.embedScreenshot(baseImage);
            screen.embedScreenshot(actualImage);
            screen.embedScreenshot(diff.getTransparentMarkedImage());
            screen.embedScreenshot(diff.getMarkedImage());
            // log.info("Number of different pixels: " + diff.getDiffSize());
        }
        Assert.assertFalse("Images " + page + " are not same", diff.hasDiff());
    }
}
