package pl.example.pdfboxmgr;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.IMAGE_PATH;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.CombinedPDFGenerator;

@SpringBootTest
public class GenerateCombinedPDFTest {

    @Autowired
    private CombinedPDFGenerator combinedPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideCombinedData")
    void shouldGenerateCombinedPdf(String fileName, String imagePath, String text, int numberOfFields) {
        combinedPdfGenerator.generateCombinedPdf(fileName, imagePath, text, numberOfFields);

        var file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideCombinedData() {
        return range(0, 20)
            .mapToObj(i -> Arguments.of("combinedPDF.pdf", IMAGE_PATH, "Sample text", 10));
    }
}