package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

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
public class ImagePDFGenerator {

    public void generatePdfWithImage(String fileName, String imagePath, int numberOfPages) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var pdImage = PDImageXObject.createFromFile(imagePath, document);

            for (int i = 0; i < numberOfPages; i++) {
                var page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                var contentStream = new PDPageContentStream(document, page);

                float scale = 2f / 3;
                float imageWidth = pdImage.getWidth() * scale;
                float imageHeight = pdImage.getHeight() * scale;

                var xPosition = (PDRectangle.A4.getWidth() - imageWidth) / 2;
                var yPosition = (PDRectangle.A4.getHeight() - imageHeight) / 2;

                contentStream.drawImage(pdImage, xPosition, yPosition, imageWidth, imageHeight);
                contentStream.close();
            }

            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with image", e);
        }
    }


    public void addImageToDocument(PDDocument document, PDPage page, String imagePath, float x, float y) {
        try {
            var pdImage = PDImageXObject.createFromFile(imagePath, document);
            var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.drawImage(pdImage, x, y, (float) pdImage.getWidth() / 3, (float) pdImage.getHeight() / 3);
            contentStream.close();
        } catch (IOException e) {
            log.error("Error adding image to PDF", e);
        }
    }
}