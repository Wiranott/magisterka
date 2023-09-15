package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TextPDFGenerator {

    private static final int MARGIN = 50;
    private static final int FONT_SIZE = 12;
    private static final float LINE_HEIGHT = 1.5f * FONT_SIZE;

    public void generatePdfWithText(String fileName, String text) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE);

            var width = page.getMediaBox().getWidth() - 2 * MARGIN;
            var font = PDType1Font.HELVETICA;
            var lines = splitTextIntoLines(text, width, font);

            var yPosition = page.getMediaBox().getHeight() - MARGIN;
            for (String line : lines) {
                contentStream.beginText();
                contentStream.newLineAtOffset(MARGIN, yPosition);
                contentStream.showText(line);
                contentStream.endText();
                yPosition -= LINE_HEIGHT;
            }

            contentStream.close();
            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with text", e);
        }
    }

    private List<String> splitTextIntoLines(String text, float width, PDFont font) throws IOException {
        List<String> lines = new ArrayList<>();
        var words = text.split(" ");
        var line = new StringBuilder();

        for (String word : words) {
            if (font.getStringWidth(line.toString() + word) * FONT_SIZE / 1000 < width) {
                line.append(word).append(" ");
            } else {
                lines.add(line.toString());
                line = new StringBuilder(word).append(" ");
            }
        }

        if (!line.isEmpty()) {
            lines.add(line.toString());
        }

        return lines;
    }

    public void addTextToDocument(PDDocument document, PDPage page, String text, float x, float y) {
        try {
            var contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            log.error("Error adding text to PDF", e);
        }
    }
}
