package pl.example.pdfboxmgr.generator;

import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.springframework.stereotype.Service;

@Service
public class ComboboxPdfGenerator {

    private static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    public void generatePdfWithComboBox(String fileName, List<String> options) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDAcroForm acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            PDComboBox comboBox = new PDComboBox(acroForm);
            comboBox.setPartialName("SampleComboBox");
            comboBox.setOptions(options);

            PDAnnotationWidget widget = comboBox.getWidgets().get(0);
            PDRectangle rect = new PDRectangle(50, 750, 200, 20);
            widget.setRectangle(rect);
            widget.setPage(page);
            page.getAnnotations().add(widget);
            acroForm.getFields().add(comboBox);

            document.save(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
