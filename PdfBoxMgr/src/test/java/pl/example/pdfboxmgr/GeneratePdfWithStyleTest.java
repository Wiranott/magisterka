package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.StyledPdfGenerator;

@SpringBootTest
public class GeneratePdfWithStyleTest {

    @Autowired
    private StyledPdfGenerator styledPdfGenerator;

    @Test
    void shouldGenerateStyledPdf() {
        var fileName = "styledPDF.pdf";
        styledPdfGenerator.generateStyledPdf(fileName, 3);

        File file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}