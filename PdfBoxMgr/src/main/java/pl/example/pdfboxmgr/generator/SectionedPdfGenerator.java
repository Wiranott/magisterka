package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PdfBoxConfig.PDF_PATH;

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

    private static final float PAGE_HEIGHT = PDRectangle.A4.getHeight();
    private static final float MARGIN = 50;
    private static final float LINE_SPACING = 30;
    private static final float FONT_SIZE = 18;

    public void generatePdfWithSections(String fileName, List<String> sections) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, FONT_SIZE);

            var yPosition = PAGE_HEIGHT - MARGIN;

            for (String section : sections) {
                if (yPosition < MARGIN) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, FONT_SIZE);
                    yPosition = PAGE_HEIGHT - MARGIN;
                }

                contentStream.beginText();
                contentStream.newLineAtOffset(MARGIN, yPosition);
                contentStream.showText(section);
                contentStream.endText();
                yPosition -= LINE_SPACING;
            }

            contentStream.close();
            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with sections", e);
        }
    }
}