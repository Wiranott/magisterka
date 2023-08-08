package pl.example.pdfboxmgr;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.ComboboxPdfGenerator;

@SpringBootTest
public class GeneratePdfWitchComboboxTest {

    @Autowired
    private ComboboxPdfGenerator comboboxPdfGenerator;

    @Test
    void shouldGeneratePdfWithComboBox() {
        var fileName = "comboBoxPDF.pdf";
        var options = asList("Opcja 1", "Opcja 2", "Opcja 3");
        comboboxPdfGenerator.generatePdfWithComboBox(fileName, options);
    }
}