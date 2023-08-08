package pl.example.pdfboxmgr;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
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
        var fileName = "comboBoxPDF.pdf";
        var options = asList("Opcja 1", "Opcja 2", "Opcja 3");
        pdfGenerator.generatePdfWithComboBox(fileName, options);
    }

    @Test
    void shouldGeneratePdfWithTable() {
        var fileName = "tablePDF.pdf";
        List<String[]> data = new ArrayList<>();

        String[] row1 = {"John", "Doe", "john@example.com"};
        String[] row2 = {"Jane", "Smith", "jane@example.com"};
        String[] row3 = {"Michael", "Johnson", "michael@example.com"};

        data.add(row1);
        data.add(row2);
        data.add(row3);
        pdfGenerator.generatePdfWithTable(fileName, data);
    }
}
