package pl.example.pdfboxmgr.generator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import pl.example.pdfboxmgr.database.DocumentDataEntity;
import pl.example.pdfboxmgr.database.DocumentDataRepository;

@Service
public class DataFromDBPdfGenerator {

    private final DocumentDataRepository documentDataRepository;

    private static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    public DataFromDBPdfGenerator(DocumentDataRepository documentDataRepository) {
        this.documentDataRepository = documentDataRepository;
    }

    public void generatePdfWithDataFromDatabase(String fileName) {
        String fullPath = BASE_PATH + fileName;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            var contentStream = new PDPageContentStream(document, page);

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