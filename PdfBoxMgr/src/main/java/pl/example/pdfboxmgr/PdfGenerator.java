package pl.example.pdfboxmgr.generator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.example.pdfboxmgr.DocumentData.DocumentDataEntity;
import pl.example.pdfboxmgr.DocumentData.DocumentDataRepository;

@Service
public class PdfGenerator {

    @Autowired
    private DocumentDataRepository documentDataRepository;

    public static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    public void generateEmptyPdf(String fileName, int numberOfPages) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            for (int i = 0; i < numberOfPages; i++) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
            }
            document.save(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePdfWithText(String fileName, String text) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();

            document.save(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePdfWithCheckbox(String fileName) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDAcroForm acroForm = new PDAcroForm(document);
            document.getDocumentCatalog().setAcroForm(acroForm);

            PDCheckBox checkBox = new PDCheckBox(acroForm);
            checkBox.setPartialName("SampleCheckBox");
            PDAnnotationWidget widget = checkBox.getWidgets().get(0);
            PDRectangle rect = new PDRectangle(50, 750, 20, 20);
            widget.setRectangle(rect);
            widget.setPage(page);
            page.getAnnotations().add(widget);
            acroForm.getFields().add(checkBox);

            checkBox.check();

            document.save(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void generatePdfWithTable(String fileName, List<String[]> data) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float margin = 50;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = 750;
            float tableHeight = 200;
            float rowHeight = 20;
            int cols = data.get(0).length;
            float tableLength = tableWidth / (float) cols;
            float[] colWidths = new float[cols];
            Arrays.fill(colWidths, tableLength);

            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    String text = row[i];
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.beginText();
                    contentStream.moveTextPositionByAmount(margin + colWidths[i] * i, yPosition);
                    contentStream.drawString(text);
                    contentStream.endText();
                }
                yPosition -= rowHeight;
            }

            contentStream.close();
            document.save(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generatePdfWithDataFromDatabase(String fileName) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Połącz się z bazą danych i pobierz dane
            List<String> data = fetchDataFromDatabase();

            float yPosition = 750;
            for (String text : data) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.moveTextPositionByAmount(50, yPosition);
                contentStream.drawString(text);
                contentStream.endText();
                yPosition -= 20;
            }

            contentStream.close();
            document.save(fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> fetchDataFromDatabase() {
        return documentDataRepository.findAll().stream()
            .map(DocumentDataEntity::getContent)
            .collect(Collectors.toList());
    }
}
