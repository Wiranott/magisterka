package pl.example.pdfitextmgr.generator;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import org.springframework.stereotype.Service;

@Service
public class EmptyPdfGenerator {

    public void generateEmptyPdf(String fileName) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph("This is an empty PDF document."));
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        } finally {
            document.close();
        }
    }
}
