package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfboxmgr.database.DocumentDataEntity;
import pl.example.pdfboxmgr.database.DocumentDataRepository;
import pl.example.pdfboxmgr.generator.DataFromDBPdfGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithDataFromDBTest {

    private static final String BASE_PATH = "C:\\Users\\mateu\\OneDrive\\Dokumenty\\magisterka\\magisterka\\PdfBoxMgr\\src\\main\\resources\\pdfs\\";

    @Mock
    private DocumentDataRepository documentDataRepository;

    @InjectMocks
    private DataFromDBPdfGenerator pdfGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGeneratePdfWithDataFromDatabase() {
        var mockData = Arrays.asList(
            new DocumentDataEntity(1L, "Dane 1"),
            new DocumentDataEntity(2L, "Dane 2")
        );
        Mockito.when(documentDataRepository.findAll()).thenReturn(mockData);

        var fileName = "dateFromDBPDF.pdf";
        pdfGenerator.generatePdfWithDataFromDatabase(fileName);

        File file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        file.delete();
    }
}
