package pl.example.pdfitextmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.EmptyPdfGenerator;

@SpringBootTest
public class GenerateEmptyPdfTest {

    @Autowired
    private EmptyPdfGenerator emptyPdfGenerator;

    @Test
    void shouldGenerateEmptyPdf() {
        var fileName = "emptyPDF.pdf";
        emptyPdfGenerator.generateEmptyPdf(fileName);

        File file = new File(fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}