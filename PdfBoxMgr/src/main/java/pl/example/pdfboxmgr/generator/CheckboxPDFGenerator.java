package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckboxPDFGenerator {

    public void generatePdfWithCheckbox(String fileName, float xPosition, float yPosition, boolean isChecked) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            var checkBox = new PDCheckBox(acroForm);
            checkBox.setPartialName("SampleCheckBox");
            var widget = checkBox.getWidgets().get(0);
            var rect = new PDRectangle(xPosition, yPosition, 20, 20);
            widget.setRectangle(rect);
            widget.setPage(page);
            page.getAnnotations().add(widget);
            acroForm.getFields().add(checkBox);

            if (isChecked) {
                checkBox.check();
            } else {
                checkBox.unCheck();
            }

            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with checkbox", e);
        }
    }
}