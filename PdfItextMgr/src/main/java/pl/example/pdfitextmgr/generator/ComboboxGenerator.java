package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComboboxGenerator {

    public void generatePdfWithComboBox(String fileName, List<String> options) {
        var fullPath = BASE_PATH + fileName;

        try {
            var document = new Document(PageSize.A4);
            var writer = PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            var comboBox = PdfFormField.createCombo(writer, false, options.toArray(new String[0]), 0);
            comboBox.setFieldName("SampleComboBox");
            comboBox.setWidget(new Rectangle(50, 750, 250, 770), PdfName.HIGHLIGHT);
            comboBox.setFlags(PdfAnnotation.FLAGS_PRINT);

            writer.addAnnotation(comboBox);

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF with combobox", e);
        }
    }
}