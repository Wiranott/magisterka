package pl.example.pdfboxmgr;

import java.util.ArrayList;
import java.util.List;
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
        var fileName = "tablePDF.pdf";
        List<String[]> data = new ArrayList<>();

        String[] row1 = {"John", "Doe", "john@example.com"};
        String[] row2 = {"Jane", "Smith", "jane@example.com"};
        String[] row3 = {"Michael", "Johnson", "michael@example.com"};

        data.add(row1);
        data.add(row2);
        data.add(row3);
        tablePdfGenerator.generatePdfWithTable(fileName, data);
    }
}