package pl.example.pdfboxmgr.generator;

import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.example.pdfboxmgr.database.DocumentDataEntity;
import pl.example.pdfboxmgr.database.DocumentDataRepository;

@Slf4j
@Component
public class DataFromDBPDFGenerator {

    private final DocumentDataRepository documentDataRepository;

    @Autowired
    public DataFromDBPDFGenerator(DocumentDataRepository documentDataRepository) {
        this.documentDataRepository = documentDataRepository;
    }

    public void generatePdfWithDataFromDatabase(String fileName) {
        var fullPath = PDF_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            var page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var contentStream = new PDPageContentStream(document, page);

            var data = fetchDataFromDatabase();

            var yPosition = 750;
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
            log.error("Error generating PDF with data from db", e);
        }
    }

    private List<String> fetchDataFromDatabase() {
        return documentDataRepository.findAll().stream()
            .map(DocumentDataEntity::getContent)
            .collect(Collectors.toList());
    }
}
