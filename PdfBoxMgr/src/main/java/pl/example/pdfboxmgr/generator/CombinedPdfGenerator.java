package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PdfConfig.BASE_PATH;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CombinedPdfGenerator {

    @Autowired
    private ImagePdfGenerator imagePdfGenerator;

    @Autowired
    private TextPdfGenerator textPdfGenerator;

    @Autowired
    private EditableTextFieldsPdfGenerator editableTextFieldsPdfGenerator;

    public void generateCombinedPdf(String fileName, String imagePath, String text, int numberOfFields) {
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            imagePdfGenerator.addImageToDocument(document, page, imagePath, 50, 650);

            textPdfGenerator.addTextToDocument(document, page, text, 50, 600);

            editableTextFieldsPdfGenerator.addEditableTextFieldsToDocument(document, page, numberOfFields);

            document.save(BASE_PATH + fileName);
        } catch (IOException e) {
            log.error("Error generating combined PDF", e);
        }
    }
}