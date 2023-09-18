package pl.example.pdfboxmgr.generator;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import pl.example.pdfboxmgr.config.PDFBoxConfig;

@Slf4j
@Service
public class SectionedPDFGenerator {

    public void generatePdfWithSections(String fileName, String title, String[] sectionTitles, String[] sectionContents) {
        var fullPath = PDFBoxConfig.PDF_PATH + fileName;

        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            var yPosition = PDRectangle.A4.getHeight() - 50;
            var margin = 50;

            if (title != null && !title.isEmpty()) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.beginText();
                contentStream.newLineAtOffset(margin, yPosition);
                contentStream.showText(title);
                contentStream.endText();
                yPosition -= 30;

                for (int i = 0; i < sectionTitles.length; i++) {
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin, yPosition);
                    contentStream.showText(sectionTitles[i]);
                    contentStream.endText();
                    yPosition -= 20;

                    contentStream.setFont(PDType1Font.HELVETICA, 14);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(margin + 10, yPosition); // Indent content a bit
                    contentStream.showText(sectionContents[i]);
                    contentStream.endText();
                    yPosition -= 20;
                }
            }

            contentStream.close();
            document.save(fullPath);
        } catch (Exception e) {
            log.error("Error generating PDF with sections", e);
        }
    }
}