package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TextPDFGenerator {

    private static final int MARGIN = 50;
    private static final int FONT_SIZE = 12;

    public void generatePdfWithText(String fileName, String text) {
        var fullPath = BASE_PATH + fileName;
        try {
            var document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            var font = FontFactory.getFont(FontFactory.HELVETICA, FONT_SIZE);
            var width = PageSize.A4.getWidth() - 2 * MARGIN;
            var lines = splitTextIntoLines(text, width, font);

            for (String line : lines) {
                document.add(new Paragraph(line, font));
            }

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF with text", e);
        }
    }

    private List<String> splitTextIntoLines(String text, float width, Font font) {
        List<String> lines = new ArrayList<>();
        var words = text.split(" ");
        var line = new StringBuilder();

        for (String word : words) {
            if (font.getBaseFont().getWidthPoint(line + word, FONT_SIZE) < width) {
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


    public void addTextToDocument(Document document, String text, float x, float y) {
        try {
            Paragraph paragraph = new Paragraph(text);
            paragraph.setIndentationLeft(x);
            paragraph.setSpacingBefore(y);
            document.add(paragraph);
        } catch (DocumentException e) {
            log.error("Error adding text to PDF", e);
        }
    }
}