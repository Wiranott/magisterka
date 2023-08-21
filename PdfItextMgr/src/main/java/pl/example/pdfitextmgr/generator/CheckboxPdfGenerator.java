package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAcroForm;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RadioCheckField;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckboxPdfGenerator {

    public void generatePdfWithCheckbox(String fileName, float xPosition, float yPosition, boolean isChecked) {
        var fullPath = BASE_PATH + fileName;

        try {
            var document = new Document(PageSize.A4);
            var writer = PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            var form = writer.getAcroForm();
            var checkbox = new RadioCheckField(writer, new Rectangle(xPosition, yPosition, xPosition + 20, yPosition + 20), "SampleCheckBox",
                "Yes");
            checkbox.setChecked(isChecked);
            PdfFormField checkboxField = checkbox.getCheckField();
            form.addFormField(checkboxField);

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating PDF with checkbox", e);
        }
    }
}