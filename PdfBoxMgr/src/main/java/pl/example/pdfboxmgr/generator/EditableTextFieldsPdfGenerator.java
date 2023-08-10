package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PdfBoxConfig.PDF_PATH;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EditableTextFieldsPdfGenerator {

    private static final int MARGIN = 50;
    private static final int FIELD_HEIGHT = 20;
    private static final int SPACING = 10;
    private static final int MAX_FIELDS_PER_PAGE = (int) ((PDRectangle.A4.getHeight() - 2 * MARGIN) / (FIELD_HEIGHT + SPACING));

    public void generatePdfWithEditableTextFields(String fileName, int numberOfFields) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {

            var acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            var fieldsAdded = 0;
            while (fieldsAdded < numberOfFields) {
                var page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                for (int i = 0; i < MAX_FIELDS_PER_PAGE && fieldsAdded < numberOfFields; i++) {
                    var textField = new PDTextField(acroForm);
                    textField.setPartialName("Field" + fieldsAdded);
                    var widget = textField.getWidgets().get(0);
                    var rect = new PDRectangle(MARGIN, PDRectangle.A4.getHeight() - MARGIN - (i * (FIELD_HEIGHT + SPACING)),
                        PDRectangle.A4.getWidth() - 2 * MARGIN, FIELD_HEIGHT);
                    widget.setRectangle(rect);
                    widget.setPage(page);
                    page.getAnnotations().add(widget);
                    acroForm.getFields().add(textField);
                    fieldsAdded++;
                }
            }

            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with editable text fields", e);
        }
    }

    public void addEditableTextFieldsToDocument(PDDocument document, PDPage page, int numberOfFields) {
        try {
            var acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            for (int i = 0; i < numberOfFields; i++) {
                var textField = new PDTextField(acroForm);
                textField.setPartialName("Field" + i);
                var widget = textField.getWidgets().get(0);
                var rect = new PDRectangle(50, 750 - i * 50, 200, 20);
                widget.setRectangle(rect);
                widget.setPage(page);
                page.getAnnotations().add(widget);
                acroForm.getFields().add(textField);
            }
        } catch (IOException e) {
            log.error("Error adding editable text fields to PDF", e);
        }
    }
}