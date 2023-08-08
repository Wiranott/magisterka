package pl.example.pdfboxmgr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.TextPdfGenerator;

@SpringBootTest
public class GeneratePdfWithTextTest {

    @Autowired
    private TextPdfGenerator textPdfGenerator;

    @Test
    void shouldGeneratePdfWithText() {
        var fileName = "textPDF.pdf";
        var sampleText = "This is sample text in the PDF file!";
        textPdfGenerator.generatePdfWithText(fileName, sampleText);
    }
}