package pl.example.pdfboxmgr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.CheckboxPdfGenerator;

@SpringBootTest
public class GeneratePdfWitchCheckboxTest {

    @Autowired
    private CheckboxPdfGenerator checkboxPdfGenerator;

    @Test
    void shouldGeneratePdfWithCheckbox() {
        var fileName = "checkboxPDF.pdf";
        checkboxPdfGenerator.generatePdfWithCheckbox(fileName);
    }
}