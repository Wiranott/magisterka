package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StyledPdfGenerator {

    public void generateStyledPdf(String fileName, int numberOfPages) {
        var fullPath = BASE_PATH + fileName;
        try {
            var document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            var font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);

            for (int i = 0; i < numberOfPages; i++) {
                document.newPage();
                var paragraph = new Paragraph("This is page " + (i + 1), font);
                paragraph.setSpacingBefore(700 - i * 50);
                document.add(paragraph);
            }

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF with generate styled", e);
        }
    }
}
