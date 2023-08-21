package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PdfItextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
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
        var fullPath = BASE_PATH + fileName;

        try {
            var document = new Document(PageSize.A4);
            var writer = PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            imagePdfGenerator.addImageToDocument(document, imagePath, 50, 650);

            textPdfGenerator.addTextToDocument(document, text, 50, 600);

            editableTextFieldsPdfGenerator.addEditableTextFieldsToDocument(writer, numberOfFields);

            document.close();
        } catch (DocumentException | IOException e) {
            log.error("Error generating combined PDF", e);
        }
    }
}