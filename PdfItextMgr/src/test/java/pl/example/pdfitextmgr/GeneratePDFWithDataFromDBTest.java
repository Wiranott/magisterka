package pl.example.pdfitextmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfitextmgr.generator.DataFromDBPDFGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithDataFromDBTest {

    @Autowired
    private DataFromDBPDFGenerator pdfGenerator;

    @Test
    void shouldGeneratePdfWithDataFromDatabase() {
        var fileName = "dateFromDBPDF.pdf";
        pdfGenerator.generatePdfWithDataFromDatabase(fileName);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}