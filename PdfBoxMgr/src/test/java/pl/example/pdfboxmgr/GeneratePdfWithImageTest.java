package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfBoxConfig.PDF_PATH;
import static pl.example.pdfboxmgr.config.PdfBoxConfig.IMAGE_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfboxmgr.generator.ImagePdfGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePdfWithImageTest {

    @Autowired
    private ImagePdfGenerator pdfGenerator;

    @ParameterizedTest
    @MethodSource("provideImageTestData")
    void shouldGeneratePdfWithImage(String imagePath, float xPosition, float yPosition, String fileName) {
        pdfGenerator.generatePdfWithImage(fileName, imagePath, xPosition, yPosition);

        var file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideImageTestData() {
        return Stream.of(
            Arguments.of(IMAGE_PATH, 50f, 750f, "imagePDF1.pdf"),
            Arguments.of(IMAGE_PATH, 100f, 700f, "imagePDF2.pdf"),
            Arguments.of(IMAGE_PATH, 250f, 10f, "imagePDF3.pdf")
        );
    }
}