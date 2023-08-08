package pl.example.pdfboxmgr;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

public class PdfGeneratorTest {

    private final pl.example.pdfboxmgr.generator.PdfGenerator pdfGenerator = new pl.example.pdfboxmgr.generator.PdfGenerator();

    @Test
    void shouldGenerateEmptyPdf() {
        var fileName = "emptyPDF.pdf";
        pdfGenerator.generateEmptyPdf(fileName, 5);
    }

    @Test
    void shouldGeneratePdfWithText() {
        var fileName = "textPDF.pdf";
        var sampleText = "This is sample text in the PDF file!";
        pdfGenerator.generatePdfWithText(fileName, sampleText);
    }

    @Test
    void shouldGeneratePdfWithCheckbox() {
        var fileName = "checkboxPDF.pdf";
        pdfGenerator.generatePdfWithCheckbox(fileName);
    }

    @Test
    void shouldGeneratePdfWithEditableTextFields() {
        var fileName = "textFieldsPDF.pdf";
        pdfGenerator.generatePdfWithEditableTextFields(fileName, 2);
    }

    @Test
    void shouldGeneratePdfWithComboBox() {
        var options = asList("Opcja 1", "Opcja 2", "Opcja 3");
        var fileName = "comboBoxPDF.pdf";
        pdfGenerator.generatePdfWithComboBox(fileName, options);
    }
}
