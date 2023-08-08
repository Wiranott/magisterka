package pl.example.pdfboxmgr.generator;

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
public class CheckboxPdfGenerator {

    private static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    public void generatePdfWithCheckbox(String fileName) {
        var fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            var checkBox = new PDCheckBox(acroForm);
            checkBox.setPartialName("SampleCheckBox");
            var widget = checkBox.getWidgets().get(0);
            var rect = new PDRectangle(50, 750, 20, 20);
            widget.setRectangle(rect);
            widget.setPage(page);
            page.getAnnotations().add(widget);
            acroForm.getFields().add(checkBox);

            checkBox.check();

            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with checkbox", e);
        }
    }
}
