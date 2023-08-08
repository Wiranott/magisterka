package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.generator.PdfGenerator.BASE_PATH;

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
import pl.example.pdfboxmgr.DocumentData.DocumentDataEntity;
import pl.example.pdfboxmgr.DocumentData.DocumentDataRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithDataFromDBTest {

    @Mock
    private DocumentDataRepository documentDataRepository;

    @InjectMocks
    private pl.example.pdfboxmgr.generator.PdfGenerator pdfGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGeneratePdfWithDataFromDatabase() {
        var mockData = Arrays.asList(
            new DocumentDataEntity("Dane 1"),
            new DocumentDataEntity("Dane 2")
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
