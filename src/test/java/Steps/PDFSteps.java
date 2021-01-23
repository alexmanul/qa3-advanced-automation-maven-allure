package Steps;

import Pages.BasePage;
import Utils.CustomAssertions;
import Utils.PDFReader;
import Utils.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Slf4j
public class PDFSteps {

    private final Context context;
    private final BasePage basePage = new BasePage();

    public PDFSteps(Context context) {
        this.context = context;
    }

    @And("^I delete '(.*)' file from the downloads directory$")
    public void deleteFileFromDownloadsDirectory(String filename) {
        String home = System.getProperty("user.home");
        File file = new File(home + "/" + "downloads" + "/" + filename);
        if (file.exists()) {
            boolean delete = file.delete();
            log.info("Was file deleted? " + delete);
            Assert.assertTrue(delete);
        }
    }

    @And("^I download file directly from server$")
    public void downloadFileDirectlyFromServer() throws MalformedURLException {
        String home = System.getProperty("user.home");
        String uniqueSuffix = UUID.randomUUID().toString();
        String filename = uniqueSuffix + "invoice" + ".pdf";
        // Replace placeholder to actual url when it is needed
        URL fileURL = new URL("url");
        String path = home + "/" + "downloads" + "/" + filename;
        Utils.saveFile(fileURL, path, context.token);
    }

    @And("^I verify PDF file contains following data$")
    public void verifyPDFFileContainsFollowingData(DataTable table) throws Exception {
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

    @And("^I verify PDF file content is equal to expected result$")
    public void verifyPDFFileContentIsEqualToExpectedResult() {
        Collection<String> pageContents = new TreeSet<>(context.pageContents);
        String actualContentAsOneSAtring = "";

        for (String s : pageContents) {
            actualContentAsOneSAtring = actualContentAsOneSAtring.concat(s + " ");
        }

        String expectedResult = context.expectedTextAsOneString.replaceAll(" {2}", " ").trim();
        String actualResult = actualContentAsOneSAtring.replaceAll(" {2}", " ").trim();
        log.info("Expected result: " + expectedResult);
        log.info("Actual result: " + actualResult);

        CustomAssertions.assertThatEquals(actualResult, expectedResult, "PDF file data is wrong");
    }

}
