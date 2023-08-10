package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;
import static pl.example.pdfboxmgr.config.PdfConfig.IMAGE_PATH;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.ImagePdfGenerator;

@SpringBootTest
public class GeneratePdfWithImageTest {

    @Autowired
    private ImagePdfGenerator imagePdfGenerator;

    @Test
    void shouldGeneratePdfWithImage() {
        var fileName = "pdfWithImage.pdf";
        imagePdfGenerator.generatePdfWithImage(fileName, IMAGE_PATH);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}