package pl.example.pdfboxmgr.generator;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.stereotype.Service;

@Service
public class EditableTextFieldsPdfGenerator {

    public static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    public void generatePdfWithEditableTextFields(String fileName, int numberOfFields) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDAcroForm acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            for (int i = 0; i < numberOfFields; i++) {
                PDTextField textField = new PDTextField(acroForm);
                textField.setPartialName("Field" + i);
                PDAnnotationWidget widget = textField.getWidgets().get(0);
                PDRectangle rect = new PDRectangle(50, 750 - i * 50, 200, 20); // Ustawienie pozycji i rozmiaru pola tekstowego
                widget.setRectangle(rect);
                widget.setPage(page);
                page.getAnnotations().add(widget);
                acroForm.getFields().add(textField);
            }

            document.save(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}