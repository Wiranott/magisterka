package pl.example.pdfitextmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.EmptyPdfGenerator;

@SpringBootTest
public class GenerateEmptyPdfTest {

    @Autowired
    private EmptyPdfGenerator emptyPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideEmptyPdfData")
    void shouldGenerateEmptyPdf(String fileName, int numberOfPages) {
        emptyPdfGenerator.generateEmptyPdf(fileName, numberOfPages);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideEmptyPdfData() {
        return Stream.of(
            Arguments.of("emptyPDF1.pdf", 1),
            Arguments.of("emptyPDF2.pdf", 5),
            Arguments.of("emptyPDF3.pdf", 100),
            Arguments.of("emptyPDF4.pdf", 500)
        );
    }
}