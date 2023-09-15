package pl.example.pdfboxmgr;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.example.pdfboxmgr.config.PDFBoxConfig.PDF_PATH;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.pdfboxmgr.generator.TablePDFGenerator;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GeneratePDFWithTableTest {

    @Autowired
    private TablePDFGenerator tablePdfGenerator;

    @ParameterizedTest
    @MethodSource("provideTableData")
    void shouldGeneratePdfWithTable(String fileName, List<String[]> data) {
        tablePdfGenerator.generatePdfWithTable(fileName, data);

        var file = new File(PDF_PATH + fileName);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    private static Stream<Arguments> provideTableData() {
        return Stream.of(
            Arguments.of("pdfWithTable1.pdf", Arrays.asList(
                new String[]{"Name", "Age", "Email"},
                new String[]{"John Doe", "25", "john@example.com"},
                new String[]{"Jane Smith", "30", "jane@example.com"}
            )),
            Arguments.of("pdfWithTable2.pdf", Arrays.asList(
                new String[]{"Title", "Author", "ISBN"},
                new String[]{"The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"},
                new String[]{"Moby Dick", "Herman Melville", "9781853260087"},
                new String[]{"Country", "Capital", "Population"},
                new String[]{"USA", "Washington, D.C.", "331 million"},
                new String[]{"UK", "London", "68 million"},
                new String[]{"Country", "Capital", "Population"},
                new String[]{"USA", "Washington, D.C.", "331 million"},
                new String[]{"UK", "London", "68 million"},
                new String[]{"Canada", "Ottawa", "38 million"},
                new String[]{"Australia", "Canberra", "25 million"},
                new String[]{"India", "New Delhi", "1.366 billion"},
                new String[]{"Name", "Age", "Email"},
                new String[]{"John Doe", "25", "john@example.com"},
                new String[]{"Jane Smith", "30", "jane@example.com"}
            ))
        );
    }
}
