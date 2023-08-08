package pl.example.pdfboxmgr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.EmptyPdfGenerator;

@SpringBootTest
public class GenerateEmptyPdfTest {

    @Autowired
    private EmptyPdfGenerator emptyPdfGenerator;

    @Test
    void shouldGenerateEmptyPdf() {
        var fileName = "emptyPDF.pdf";
        emptyPdfGenerator.generateEmptyPdf(fileName, 5);
    }
}