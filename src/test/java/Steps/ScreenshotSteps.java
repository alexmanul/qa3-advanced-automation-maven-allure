package Steps;

import Utils.Screenshots;
import Utils.TestProperties;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Slf4j
public class ScreenshotSteps extends BaseSteps {

    private final Context context;

    public ScreenshotSteps(Context context) {
        this.context = context;
    }

    @And("^I take sample screenshot of '(.*)' page and store as expected result$")
    public void TakeBaseScreenshotAndStoreAsExpectedResult(String page) throws Exception {
        Screenshots screen = new Screenshots(driver, context);
        String sampleFile = screen.generateScreenshotName("sample", "sample-", page);
        screen.createFolders(sampleFile);
        log.info("Name of sample file: " + sampleFile);
        ImageIO.write(screen.getScreenshot(), "PNG", new File(sampleFile));
    }

    @And("^I take actual screenshot of '(.*)' page and check vs expected result$")
    public void TakeActualScreenshotAndCheckVsExpectedResult(String page) throws Exception {
        Screenshots screen = new Screenshots(driver, context);
        String actualFile = screen.generateScreenshotName("actual", "actual-", page);
        screen.createFolders(actualFile);
        ImageIO.write(screen.getScreenshot(), "PNG", new File(actualFile));
        log.info("Name of actual file: " + actualFile);

        String sampleFile = screen.generateScreenshotName("sample", "sample-", page);
        BufferedImage sampleImage = ImageIO.read(new File(sampleFile));
        BufferedImage actualImage = ImageIO.read(new File(actualFile));

        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff diff = imageDiffer.makeDiff(sampleImage, actualImage);
        diff.withDiffSizeTrigger(Integer.parseInt(TestProperties.getProperty("number.of.accepted.different.pixels")));

        if (diff.getDiffSize() > 0) {
            String transparentMarkedImage = screen.generateScreenshotName("fails", "diff1-transparent_", page);
            screen.createFolders(transparentMarkedImage);

            ImageIO.write(sampleImage, "PNG",
                    new File(screen.generateScreenshotName("fails", "base-copy-", page)));
            ImageIO.write(diff.getTransparentMarkedImage(), "PNG", new File(transparentMarkedImage));
            ImageIO.write(diff.getMarkedImage(), "PNG",
                    new File(screen.generateScreenshotName("fails", "diff2-marked_", page)));

            screen.embedScreenshot(sampleImage);
            screen.embedScreenshot(actualImage);
            screen.embedScreenshot(diff.getTransparentMarkedImage());
            screen.embedScreenshot(diff.getMarkedImage());
            log.info("Number of different pixels: " + diff.getDiffSize());
        }
        Assert.assertFalse("Images " + page + " are not same", diff.hasDiff());
    }
}
