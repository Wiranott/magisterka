package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SectionedPdfGenerator {

    public void generatePdfWithSections(String fileName, List<String> sections) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);

            float yPosition = 750;
            for (String section : sections) {
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText(section);
                contentStream.endText();
                yPosition -= 30;
            }

            contentStream.close();
            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with sections", e);
        }
    }
}