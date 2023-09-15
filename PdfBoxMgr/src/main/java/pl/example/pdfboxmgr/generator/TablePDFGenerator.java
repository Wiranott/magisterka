package pl.example.pdfboxmgr.generator;

import static java.util.Arrays.fill;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

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
public class TablePDFGenerator {

    public void generatePdfWithTable(String fileName, List<String[]> data) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var contentStream = new PDPageContentStream(document, page);

            var margin = 50;
            var tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            var yPosition = 750;
            var rowHeight = 20;
            var cols = data.get(0).length;
            var tableLength = tableWidth / (float) cols;
            var colWidths = new float[cols];
            fill(colWidths, tableLength);

            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    var text = row[i];
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.beginText();
                    contentStream.moveTextPositionByAmount(margin + colWidths[i] * i, yPosition);
                    contentStream.drawString(text);
                    contentStream.endText();
                }
                yPosition -= rowHeight;
            }

            contentStream.close();
            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with table", e);
        }
    }
}