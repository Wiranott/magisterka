package pl.example.pdfboxmgr.generator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class TablePdfGenerator {

    public static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";


    public void generatePdfWithTable(String fileName, List<String[]> data) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float margin = 50;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = 750;
            float tableHeight = 200;
            float rowHeight = 20;
            int cols = data.get(0).length;
            float tableLength = tableWidth / (float) cols;
            float[] colWidths = new float[cols];
            Arrays.fill(colWidths, tableLength);

            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    String text = row[i];
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
            e.printStackTrace();
        }
    }

}
