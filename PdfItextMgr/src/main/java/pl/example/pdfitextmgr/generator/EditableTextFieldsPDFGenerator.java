package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EditableTextFieldsPDFGenerator {

    public void generatePdfWithEditableTextFields(String fileName, int numberOfFields) {
        var fullPath = BASE_PATH + fileName;

        try {
            var document = new Document();
            var writer = PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            for (int i = 0; i < numberOfFields; i++) {
                TextField textField = new TextField(writer, new Rectangle(50, 750 - i * 30, 250, 770 - i * 30), "field" + i);
                writer.addAnnotation(textField.getTextField());
            }

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF with editable text fields", e);
        }
    }

    public void addEditableTextFieldsToDocument(PdfWriter writer, int numberOfFields) {
        try {
            for (int i = 0; i < numberOfFields; i++) {
                var textField = new TextField(writer, new Rectangle(50, 750 - i * 30, 250, 770 - i * 30), "field" + i);
                writer.addAnnotation(textField.getTextField());
            }
        } catch (DocumentException | IOException e) {
            log.error("Error adding editable text fields to PDF", e);
        }
    }
}