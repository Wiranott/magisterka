package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PdfBoxConfig.PDF_PATH;

import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComboboxPdfGenerator {

    public void generatePdfWithComboBox(String fileName, List<String> options) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            var comboBox = new PDComboBox(acroForm);
            comboBox.setPartialName("SampleComboBox");
            comboBox.setOptions(options);

            var widget = comboBox.getWidgets().get(0);
            var rect = new PDRectangle(50, 750, 200, 20);
            widget.setRectangle(rect);
            widget.setPage(page);
            page.getAnnotations().add(widget);
            acroForm.getFields().add(comboBox);

            document.save(fullPath);
        } catch (IOException e) {
            log.error("Error generating PDF with combobox", e);
        }
    }
}