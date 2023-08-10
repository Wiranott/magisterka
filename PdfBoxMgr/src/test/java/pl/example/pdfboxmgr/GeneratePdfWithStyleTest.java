package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfBoxConfig.PDF_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfboxmgr.generator.StyledPdfGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePdfWithStyleTest {

    @Autowired
    private StyledPdfGenerator styledPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideStyleData")
    void shouldGenerateStyledPdf(String fileName, int styleLevel) {
        styledPdfGenerator.generateStyledPdf(fileName, styleLevel);

        File file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideStyleData() {
        return Stream.of(
            Arguments.of("styledPDF1.pdf", 1),
            Arguments.of("styledPDF2.pdf", 2),
            Arguments.of("styledPDF3.pdf", 3),
            Arguments.of("styledPDF4.pdf", 4)
        );
    }
}