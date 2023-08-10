package pl.example.pdfitextmgr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import com.itextpdf.text.pdf.PdfReader;
import java.io.IOException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.EmptyPdfGenerator;

@SpringBootTest
public class GenerateEmptyPdfTest {

    @Autowired
    private EmptyPdfGenerator emptyPdfGenerator;

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 100, 500})
    void shouldGeneratePdfWithGivenNumberOfPages(int numberOfPages) throws IOException {
        var fileName = "emptyPDF_" + numberOfPages + ".pdf";
        var fullPath = BASE_PATH + fileName;
        emptyPdfGenerator.generateEmptyPdf(fileName, numberOfPages);

        PdfReader reader = new PdfReader(fullPath);
        assertEquals(numberOfPages, reader.getNumberOfPages());
        reader.close();
    }
}