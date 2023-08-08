package pl.example.pdfboxmgr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.EditableTextFieldsPdfGenerator;

@SpringBootTest
public class GeneratePdfWitchEditableTextFieldsTest {

    @Autowired
    private EditableTextFieldsPdfGenerator editableTextFieldsPdfGenerator;

    @Test
    void shouldGeneratePdfWithEditableTextFields() {
        var fileName = "textFieldsPDF.pdf";
        editableTextFieldsPdfGenerator.generatePdfWithEditableTextFields(fileName, 2);
    }
}