package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StyledPdfGenerator {

    public void generateStyledPdf(String fileName, int numberOfPages) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            for (int i = 0; i < numberOfPages; i++) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700 - i * 50);
                contentStream.showText("This is page " + (i + 1));
                contentStream.endText();
                contentStream.close();
            }
            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with generate styled", e);
        }
    }
}
