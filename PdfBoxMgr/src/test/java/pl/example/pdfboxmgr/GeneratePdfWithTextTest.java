package pl.example.pdfboxmgr;

import static java.util.Collections.nCopies;
import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfBoxConfig.PDF_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.TextPdfGenerator;

@SpringBootTest
public class GeneratePdfWithTextTest {

    @Autowired
    private TextPdfGenerator textPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideTextData")
    void shouldGeneratePdfWithText(String fileName, String text) {
        textPdfGenerator.generatePdfWithText(fileName, text);

        var file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideTextData() {
        return of(
            Arguments.of("shortTextPDF.pdf", "This is a short sample text in the PDF file!"),
            Arguments.of("longTextPDF.pdf", String.join(" ", nCopies(500, "This is a very long sample text in the PDF file!")))
        );
    }
}