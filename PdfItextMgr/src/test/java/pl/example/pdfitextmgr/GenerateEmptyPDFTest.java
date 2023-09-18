package pl.example.pdfitextmgr;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.EmptyPDFGenerator;

@SpringBootTest
public class GenerateEmptyPDFTest {

    @Autowired
    private EmptyPDFGenerator emptyPdfGenerator;

    @ParameterizedTest
    @MethodSource("provideEmptyPdfData")
    void shouldGenerateEmptyPdf(String fileName, int numberOfPages) {
        emptyPdfGenerator.generateEmptyPdf(fileName, numberOfPages);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideEmptyPdfData() {
        return range(0, 20)
            .mapToObj(i -> Arguments.of("emptyPDF1.pdf", 100000));
    }
}