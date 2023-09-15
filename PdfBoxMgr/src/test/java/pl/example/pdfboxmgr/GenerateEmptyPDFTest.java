package pl.example.pdfboxmgr;

import static java.util.stream.IntStream.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.EmptyPDFGenerator;

@SpringBootTest
public class GenerateEmptyPDFTest {

    @Autowired
    private EmptyPDFGenerator emptyPdfGenerator;

    @ParameterizedTest
    @MethodSource("providePdfData")
    void shouldGeneratePdfWithGivenNumberOfPages(String fileName, int numberOfPages) throws IOException {
        emptyPdfGenerator.generateEmptyPdf(fileName, numberOfPages);

        var document = PDDocument.load(new File(PDF_PATH + fileName));
        assertEquals(numberOfPages, document.getNumberOfPages());
        document.close();
    }

    private static Stream<Arguments> providePdfData() {
        return range(0, 100)
            .mapToObj(i -> Arguments.of("emptyPDF1.pdf", 1));
    }
}