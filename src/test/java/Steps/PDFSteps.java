package Steps;

import Pages.BasePage;
import Utils.CustomAssertions;
import Utils.DriverSingleton;
import Utils.PDFReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.*;

@Slf4j
public class PDFSteps {

    protected final WebDriver driver = DriverSingleton.getInstance();
    protected final WebDriverWait wait = DriverSingleton.getExplicitWait();
    private final Context context;
    protected BasePage basePage = new BasePage(driver, wait);

    public PDFSteps(Context context) {
        this.context = context;
    }

    @And("^I delete '(.*)' file from the downloads directory$")
    public void deleteFileFromDownloadsDirectory(String filename) {
        String home = System.getProperty("user.home");
        File file = new File(home + "/" + "downloads" + "/" + filename);
        file.delete();
    }

    @And("^I download '(.*)' file directly from server$")
    public void downloadFileDirectlyFromServer(String filename) {
        //
    }

    @And("^I verify PDF file contains following data$")
    public void seePDFFileContainsFollowingData(DataTable table) throws Exception {
        File file = new File("sources/PDF-IC-Basic-Invoice-Template.pdf");
        log.debug(file.getPath());
        String expectedTextAsOneString = "";
        Collection<String> pageContents = null;
        List<String> actualPDFContents = new ArrayList<>();

        try (
                PDDocument pdf = PDDocument.load(file);
                PDFReader pages = PDFReader.createFrom(pdf)
        ) {
            PDFTextStripper pdfStripper = new PDFTextStripper();

            for (Map<String, String> row : basePage.getTableAsStringMaps(table)) {
                int page = Integer.parseInt(row.get("PAGE")) - 1;
                log.debug("PDF page number: " + page);
                String expected = row.get("LABEL") + " " + row.get("CONTENT");
                log.debug("Expected content in PDF page: " + expected);

                String actualPDFPage = pdfStripper.getText(pages.get(page)).replaceAll("\\s+", " ");
                log.debug(actualPDFPage);

                CustomAssertions.assertThatContains(actualPDFPage, expected,
                        "PDF dpcument data is wrong for " + row.get("CONTENT"));

                expectedTextAsOneString = expectedTextAsOneString.concat(expected + " ");
                log.debug("A whole PDF content - during being concatenated: " + expectedTextAsOneString);
                actualPDFContents.add(actualPDFPage);
                pageContents = new TreeSet<>(actualPDFContents);
            }
            context.pageContents = pageContents;
            context.expectedTextAsOneString = expectedTextAsOneString;
        }


    }


}
