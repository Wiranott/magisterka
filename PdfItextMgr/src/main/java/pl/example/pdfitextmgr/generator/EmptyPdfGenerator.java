package pl.example.pdfitextmgr.generator;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmptyPdfGenerator {

    public void generateEmptyPdf(String fileName, int numberOfPages) {
        if (numberOfPages <= 0) {
            log.error("Liczba stron powinna być większa niż 0");
            throw new IllegalArgumentException("Liczba stron powinna być większa niż 0");
        }

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            for (int i = 0; i < numberOfPages; i++) {
                document.newPage();
            }
            document.close();
        } catch (Exception e) {
            log.error("Błąd podczas generowania pustego PDF", e);
            throw new RuntimeException("Błąd podczas generowania pustego PDF", e);
        }
    }
}