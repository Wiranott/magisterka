package pl.example.pdfboxmgr.generator;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmptyPdfGenerator {

    private static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    public void generateEmptyPdf(String fileName, int numberOfPages) {
        var fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            for (int i = 0; i < numberOfPages; i++) {
                var page = new PDPage(PDRectangle.A4);
                document.addPage(page);
            }
            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating empty PDF", e);
        }
    }
}
