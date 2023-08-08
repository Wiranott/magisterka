package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.generator.TablePdfGenerator.BASE_PATH;

import java.io.File;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.pdfboxmgr.generator.TablePdfGenerator;

@SpringBootTest
public class GeneratePdfWithTableTest {

    @Autowired
    private TablePdfGenerator tablePdfGenerator;

    @Test
    void shouldGeneratePdfWithTable() {
        var fileName = "pdfWithTable.pdf";
        var data = Arrays.asList(
            new String[]{"Name", "Age", "Email"},
            new String[]{"John Doe", "25", "john@example.com"},
            new String[]{"Jane Smith", "30", "jane@example.com"}
        );
        tablePdfGenerator.generatePdfWithTable(fileName, data);

        var file = new File(BASE_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}