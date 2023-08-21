package pl.example.pdfitextmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import java.io.File;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfitextmgr.generator.ImagePdfGenerator;

@SpringBootTest
public class GeneratePdfWithImageTest {

    @Autowired
    private ImagePdfGenerator imagePdfGenerator;

    @ParameterizedTest
    @MethodSource("provideImageParameters")
    public void shouldGeneratePdfWithImage(String fileName) {
        imagePdfGenerator.generatePdfWithImage(fileName);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<String> provideImageParameters() {
        return Stream.of(
            "imagePDF1.pdf"
        );
    }
}