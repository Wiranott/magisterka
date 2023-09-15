package pl.example.pdfitextmgr.generator;

import static pl.example.pdfitextmgr.config.PDFiTextConfig.BASE_PATH;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.example.pdfitextmgr.database.DocumentDataEntity;
import pl.example.pdfitextmgr.database.DocumentDataRepository;

@Slf4j
@Component
public class DataFromDBPDFGenerator {

    private final DocumentDataRepository documentDataRepository;

    @Autowired
    public DataFromDBPDFGenerator(DocumentDataRepository documentDataRepository) {
        this.documentDataRepository = documentDataRepository;
    }

    public void generatePdfWithDataFromDatabase(String fileName) {
        var fullPath = BASE_PATH + fileName;
        try {
            var document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(fullPath));
            document.open();

            var data = fetchDataFromDatabase();
            for (String text : data) {
                document.add(new Paragraph(text));
            }

            document.close();
        } catch (Exception e) {
            log.error("Error generating PDF with data from db", e);
        }
    }

    private List<String> fetchDataFromDatabase() {
        return documentDataRepository.findAll().stream()
            .map(DocumentDataEntity::getContent)
            .collect(Collectors.toList());
    }
}
