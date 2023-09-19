package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.IMAGE_PATH;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.File;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfboxmgr.generator.ImagePDFGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithImageTest {

    @Autowired
    private ImagePDFGenerator pdfGenerator;

    @ParameterizedTest
    @MethodSource("provideImageParameters")
    void shouldGeneratePdfWithImage(String imagePath, String fileName, int numberOfPages) {
        pdfGenerator.generatePdfWithImage(fileName, imagePath, numberOfPages);

        var file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideImageParameters() {
        return IntStream.range(0, 20)
            .mapToObj(i -> Arguments.of(IMAGE_PATH, "imagePDF.pdf", 10000));
    }
}