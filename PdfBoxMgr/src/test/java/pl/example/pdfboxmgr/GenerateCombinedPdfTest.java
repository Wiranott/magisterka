package pl.example.pdfboxmgr;

import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfBoxConfig.PDF_PATH;
import static pl.example.pdfboxmgr.config.PdfBoxConfig.IMAGE_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.CombinedPdfGenerator;

@SpringBootTest
public class GenerateCombinedPdfTest {

    @Autowired
    private CombinedPdfGenerator combinedPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideCombinedData")
    void shouldGenerateCombinedPdf(String fileName, String imagePath, String text, int numberOfFields) {
        combinedPdfGenerator.generateCombinedPdf(fileName, imagePath, text, numberOfFields);

        var file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideCombinedData() {
        return of(
            Arguments.of("combinedPDF1.pdf", IMAGE_PATH, "Sample text 1", 5),
            Arguments.of("combinedPDF2.pdf", IMAGE_PATH, "Sample text 2", 10)
        );
    }
}