package Utils;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;
import java.util.ArrayList;

public class PDFReader extends ArrayList<PDDocument> implements AutoCloseable {

    public static PDFReader createFrom(PDDocument pdDocument) throws IOException {
        Splitter splitter = new Splitter();
        PDFReader pdDocuments = new PDFReader();
        pdDocuments.addAll(splitter.split(pdDocument));
        return pdDocuments;
    }

    @Override
    public void close() throws IOException {
        for (PDDocument pdDocument : this) {
            pdDocument.close();
        }
    }
}
