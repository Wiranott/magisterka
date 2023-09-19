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
import pl.example.pdfitextmgr.generator.ImagePDFGenerator;

@SpringBootTest
public class GeneratePDFWithImageTest {

    @Autowired
    private ImagePDFGenerator imagePdfGenerator;

    @ParameterizedTest
    @MethodSource("provideImageParameters")
    public void shouldGeneratePdfWithImage(String fileName, int numberOfPages) {
        imagePdfGenerator.generatePdfWithImage(fileName, numberOfPages);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideImageParameters() {
        return range(0, 20)
            .mapToObj(i -> Arguments.of("imagePDF.pdf", 10000));
    }
}