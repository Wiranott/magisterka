package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.SectionedPdfGenerator;

@SpringBootTest
public class GeneratePdfWithSectionsTest {

    private static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    @Autowired
    private SectionedPdfGenerator sectionedPdfGenerator;

    @Test
    void shouldGeneratePdfWithSections() {
        var fileName = "sectionedPDF.pdf";
        sectionedPdfGenerator.generatePdfWithSections(fileName, "Sekcja 1", "Sekcja 2", "Sekcja 3");

        File file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}