package pl.example.pdfitextmgr.generator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TextPdfGenerator {

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