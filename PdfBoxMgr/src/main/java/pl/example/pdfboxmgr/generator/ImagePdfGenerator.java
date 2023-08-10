package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImagePdfGenerator {

    public void generatePdfWithImage(String fileName, String imagePath) {
        var fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var pdImage = PDImageXObject.createFromFile(imagePath, document);
            var contentStream = new PDPageContentStream(document, page);

            contentStream.drawImage(pdImage, 50, 400, (float) pdImage.getWidth() / 3, (float) pdImage.getHeight() / 3);
            contentStream.close();

            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with image", e);
        }
    }

}