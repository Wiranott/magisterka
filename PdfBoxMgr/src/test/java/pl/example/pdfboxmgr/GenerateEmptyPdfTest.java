package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.EmptyPdfGenerator;

@SpringBootTest
public class GenerateEmptyPdfTest {

    @Autowired
    private EmptyPdfGenerator emptyPdfGenerator;

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 50})
    void shouldGeneratePdfWithGivenNumberOfPages(int numberOfPages) throws IOException {
        var fileName = "emptyPDF_" + numberOfPages + ".pdf";
        emptyPdfGenerator.generateEmptyPdf(fileName, numberOfPages);

        var document = PDDocument.load(new File(BASE_PATH + fileName));
        assertEquals(numberOfPages, document.getNumberOfPages());
        document.close();
    }
}