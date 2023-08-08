package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.ImagePdfGenerator;

@SpringBootTest
public class GeneratePdfWithImage {

    private static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";


    @Autowired
    private ImagePdfGenerator imagePdfGenerator;

    @Test
    void shouldGeneratePdfWithImage() {
        var fileName = "pdfWithImage.pdf";
        var imagePath = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\images\\pelican.jpg";
        imagePdfGenerator.generatePdfWithImage(fileName, imagePath);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}